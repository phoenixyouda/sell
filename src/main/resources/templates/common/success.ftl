<html>
<#include "head.ftl"/>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-info">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    操作成功! ${url}
                </h4> <strong>${message!""}</strong> <a href="${url}" class="alert-link">三秒后返回</a>
            </div>
        </div>
    </div>
</div>
<script language="JavaScript">
    setTimeout("location.href='${url}'",3000)
</script>
</body>
</html>