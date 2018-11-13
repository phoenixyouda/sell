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
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>
                                产品编号
                            </th>
                            <th>
                                产品名称
                            </th>
                            <th>
                                产品价格
                            </th>
                            <th>
                                当前库存
                            </th>
                            <th>
                                产品描述
                            </th>
                            <th>
                                图片
                            </th>
                            <th>
                                产品类目
                            </th>
                            <th>
                                状态
                            </th>
                            <th>
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productPage.content as product>
                        <tr>
                            <td>
                                ${product.productId}
                            </td>
                            <td>
                            ${product.productName}
                            </td>
                            <td>
                            ${product.productPrice}
                            </td>
                            <td>
                            ${product.productStock}
                            </td>
                            <td>
                            ${product.productDescription}
                            </td>
                            <td>
                            <img src="${product.productIcon}" width="40" height="40"/>
                            </td>
                            <td>
                            ${product.categoryType}
                            </td>
                            <td>
                            ${product.getProductStatusEnum().message}
                            </td>
                            <td>
                                <#if product.productStatus==2>
                                <a href="${request.contextPath}/seller/product/up?productId=${product.productId}">上架</a>
                                <#else>
                                    <a href="${request.contextPath}/seller/product/down?productId=${product.productId}3456">下架</a>
                                </#if>
                            </td>


                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页-->
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">

                    <#if page lte 1>
                        <li class="disabled">
                            <a href="#">上一页</a>
                        </li>
                    <#else>
                        <li>
                            <a href="${request.contextPath}/seller/product/list?page=${page-1}&size=${size}">上一页</a>
                        </li>
                    </#if>

                    <#list 1..productPage.getTotalPages() as index>
                        <#if index==page>
                            <li class="disabled">
                                <a href="#">${index}</a>
                            </li>
                        <#else>
                            <li>
                                <a href="${request.contextPath}/seller/product/list?page=${index}&size=${size}">${index}</a>
                            </li>
                        </#if>

                    </#list>

                    <#if page gte productPage.getTotalPages()>
                        <li class="disabled">
                            <a href="#">下一页</a>
                        </li>
                    <#else>
                        <li>
                            <a href="${request.contextPath}/seller/product/list?page=${page+1}&size=${size}">下一页</a>
                        </li>
                    </#if>

                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>