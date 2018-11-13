<html>
<#include "../common/head.ftl"/>
<body>
<div id="wrapper" class="toggled">
<#--边栏sidebar-->
<#include "../common/nav.ftl">

    <div id="page-content-wrapper">
        <div class="container-fluid">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <table class="table table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th>
                            订单编号
                        </th>

                        <th>
                            总金额
                        </th>
                        <th>
                            订单状态
                        </th>
                        <th>
                            支付状态
                        </th>
                        <th>
                            创建时间
                        </th>
                        <th>
                            修改时间
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                        ${orderDTO.orderId}
                        </td>
                        <td>
                        ${orderDTO.orderAmount}
                        </td>
                        <td>
                        ${orderDTO.getOrderStatusEnum().message}
                        </td>
                        <td>
                        ${orderDTO.getPayStatusEnum().message}
                        </td>
                        <td>
                        ${orderDTO.createTime}
                        </td>
                        <td>
                        ${orderDTO.updateTime}
                        </td>
                    </tr>

                    </tbody>
                </table>
                <table class="table table-bordered table-condensed">
                    <thead>
                    <tr>
                        <th>
                            编号
                        </th>
                        <th>
                            商品
                        </th>
                        <th>
                            图片
                        </th>
                        <th>
                            单价
                        </th>
                        <th>
                            数量
                        </th>
                        <th>
                            小计
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list orderDTO.orderDetailList as orderDetail>
                    <tr>
                        <td>
                        ${orderDetail_index}
                        </td>
                        <td>
                        ${orderDetail.productName}
                        </td>
                        <td>
                        ${orderDetail.productIcon}
                        </td>
                        <td>
                        ${orderDetail.productPrice}
                        </td>
                        <td>
                        ${orderDetail.productQuantity}
                        </td>
                        <td>${orderDetail.productPrice*orderDetail.productQuantity}</td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        <#if orderDTO.orderStatus==0>
            <div class="col-md-12 column">
                <a href="${request.contextPath}/seller/order/finish?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>
                <a href="${request.contextPath}/seller/order/cancel?orderId=${orderDTO.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
            </div>
        </#if>
        </div>
    </div>
    </div>
</div>
</body>
</html>