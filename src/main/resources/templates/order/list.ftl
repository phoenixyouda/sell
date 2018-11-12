<html>
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-bordered table-hover">
                <thead>
                <tr>
                    <th>
                        编号
                    </th>
                    <th>
                        买家姓名
                    </th>
                    <th>
                        买家电话
                    </th>
                    <th>
                        买家地址
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
                    <th colspan="2">
                        操作
                    </th>
                </tr>
                </thead>
                <tbody>
                <#list orderDTOPage.content as orderDTO>
                <tr>
                    <td>
                        1
                    </td>
                    <td>
                    ${orderDTO.buyerName}
                    </td>
                    <td>
                    ${orderDTO.buyerPhone}
                    </td>
                    <td>
                    ${orderDTO.buyerAddress}
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
                        取消
                    </td>
                    <td>
                        完结
                    </td>

                </tr>
                </#list>
                </tbody>
            </table>
        </div>

    <#--分页-->
        <div class="col-md-12 column">
            <ul class="pagination pull-right">

            <#if currPage lte 1>
                <li class="disabled">
                    <a href="#">上一页</a>
                </li>
            <#else>
                <li>
                    <a href="${request.contextPath}/seller/order/list?page=${currPage-1}&size=${size}">上一页</a>
                </li>
            </#if>

            <#list 1..orderDTOPage.getTotalPages() as index>
                <#if index==currPage>
                    <li class="disabled">
                        <a href="#">${index}</a>
                    </li>
                <#else>
                    <li>
                        <a href="${request.contextPath}/seller/order/list?page=${index}&size=${size}">${index}</a>
                    </li>
                </#if>

            </#list>

            <#if currPage gte orderDTOPage.getTotalPages()>
                <li class="disabled">
                    <a href="#">下一页</a>
                </li>
            <#else>
                <li>
                    <a href="${request.contextPath}/seller/order/list?page=${currPage+1}&size=${size}">下一页</a>
                </li>
            </#if>

            </ul>
        </div>
    </div>
</div>
</body>
</html>