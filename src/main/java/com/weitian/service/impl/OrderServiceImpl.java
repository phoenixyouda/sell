package com.weitian.service.impl;

import com.weitian.convert.OrderDetail2CatDTOConverter;
import com.weitian.convert.OrderMaster2OrderDTOConverter;
import com.weitian.dao.OrderDetailDao;
import com.weitian.dao.OrderMasterDao;
import com.weitian.dto.CartDTO;
import com.weitian.dto.OrderDTO;
import com.weitian.entity.OrderDetail;
import com.weitian.entity.OrderMaster;
import com.weitian.entity.ProductInfo;
import com.weitian.enums.OrderStatusEnum;
import com.weitian.enums.PayStatusEnum;
import com.weitian.enums.ResultEnum;
import com.weitian.exception.SellException;
import com.weitian.service.OrderService;
import com.weitian.service.ProductInfoService;
import com.weitian.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018-11-08.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterDao orderMasterDao;
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 完结订单
     * @param orderId
     * @return
     */
    @Override
    public OrderDTO finish(String orderId) {
        //查询订单
        OrderMaster orderMaster=orderMasterDao.findById( orderId ).orElse( null );
        if(null==orderMaster){
            log.info( "【订单查询】,订单不存在，orderId={}",orderId );
            throw new SellException( ResultEnum.ORDER_NOT_EXIST );
        }
        orderMaster.setOrderStatus( OrderStatusEnum.FINISH.getCode() );
        orderMasterDao.save( orderMaster );
        return OrderMaster2OrderDTOConverter.convert( orderMaster );
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */

    @Override
    public OrderDTO cancel(String orderId) {
        //查询订单
        OrderMaster orderMaster=orderMasterDao.findById( orderId ).orElse( null );
        if(null==orderMaster){
            log.info( "【订单查询】,订单不存在，orderId={}",orderId );
            throw new SellException( ResultEnum.ORDER_NOT_EXIST );
        }
        orderMaster.setOrderStatus( OrderStatusEnum.CANCEL.getCode() );
        List<OrderDetail> orderDetailList=orderDetailDao.findByOrderIdEquals( orderId );
        if(orderDetailList.size()==0){
            log.info( "【订单详情不存在】,orderId={}",orderId );
            throw new SellException( ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        //返回商品库存
        productInfoService.increaseStock( OrderDetail2CatDTOConverter.convert( orderDetailList ) );
        //修改订单状态为已取消
        orderMasterDao.save( orderMaster );
        return OrderMaster2OrderDTOConverter.convert( orderMaster );
    }

    //查询订单详情
    @Override
    public OrderDTO findByOrderId(String orderId) {
        OrderMaster orderMaster=orderMasterDao.findById( orderId ).orElse( null );
        if(null==orderMaster){
            log.info( "【订单不存在】,orderId={}",orderId );
            throw new SellException( ResultEnum.ORDER_NOT_EXIST );
        }
        OrderDTO orderDTO= OrderMaster2OrderDTOConverter.convert( orderMaster );

        List<OrderDetail> orderDetailList=orderDetailDao.findByOrderIdEquals( orderId );
        if(orderDetailList.size()==0){
            log.info( "【订单详情不存在】,orderId={}",orderId );
            throw new SellException( ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        orderDTO.setOrderDetailList( orderDetailList );
        return orderDTO;
    }

    //查询所有订单
    @Override
    public Page<OrderDTO> findAll(Pageable pageable) {
        Page<OrderMaster> orderMasterList=orderMasterDao.findAll(pageable);
        if(null==orderMasterList){
            log.info( "【暂无订单】,{}",orderMasterList );
            throw new SellException( ResultEnum.ORDER_IS_EMPTY );
        }
        List<OrderDTO> orderDTOList= OrderMaster2OrderDTOConverter.convert(orderMasterList.getContent());
        PageImpl<OrderDTO> page=new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterList.getTotalElements());
        return page;
    }

    //创建订单
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        BigDecimal priceAmount=new BigDecimal( BigInteger.ZERO);
        String orderMasterId= KeyUtil.getUniqueKey();
        //获取orderdetail
        List<OrderDetail> detailList=orderDTO.getOrderDetailList();
        //查询商品信息，计算总价
        for(OrderDetail detail:detailList){
            ProductInfo productInfo=productInfoService.findOne( detail.getProductId() );
            if(null==productInfo){
                log.error( "【商品不存在】,productId:{}",detail.getProductId() );
                throw  new SellException( ResultEnum.PRODUCT_NOT_EXIST );

            }
            OrderDetail orderDetail=new OrderDetail();
            BeanUtils.copyProperties( productInfo,orderDetail );
            orderDetail.setProductQuantity( detail.getProductQuantity() );
            priceAmount= productInfo.getProductPrice().multiply( new BigDecimal(  detail.getProductQuantity() )).add( priceAmount );

            String orderDetailKey=KeyUtil.getUniqueKey();
            orderDetail.setOrderId( orderMasterId );
            orderDetail.setDetailId( orderDetailKey );
            //增加orderdetail
            orderDetailDao.save( orderDetail );
        }
        //获取ordermaster
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties( orderDTO,orderMaster );
        orderMaster.setOrderId( orderMasterId );
        orderMaster.setOrderAmount( priceAmount );
        orderMaster.setOrderStatus( OrderStatusEnum.NEW.getCode() );
        orderMaster.setPayStatus( PayStatusEnum.wait.getCode() );

        //增加ordermaster
        orderMasterDao.save( orderMaster );
        //减商品库存
        List<CartDTO> cartDTOList=orderDTO.getOrderDetailList().stream().map( e-> new CartDTO(e.getProductId(),e.getProductQuantity()) ).collect( Collectors.toList() );
        productInfoService.decreaseStock( cartDTOList );

        orderDTO.setOrderId(orderMasterId  );
        return orderDTO;

    }
}
