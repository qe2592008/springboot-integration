<#assign base = request.contextPath/>
<!DOCTYPE HTML>
<HTML>
<HEAD>
    <TITLE>测试首页</TITLE>
    <base id="base" href="${base}">
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />
    <script src="/webjars/jquery/3.1.1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
</HEAD>
<BODY>
${base}<br/>
${name}
</BODY>
</HTML>