<html>
<#include "head.ftl"/>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-danger">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    失败!
                </h4> <strong>${message!""}!</strong>  <a href="${url}" class="alert-link">3秒后跳转</a>
            </div>
        </div>
    </div>
</div>
<script language="JavaScript">
    setTimeout("location.href='${url}'",3000)
</script>
</body>
</html>