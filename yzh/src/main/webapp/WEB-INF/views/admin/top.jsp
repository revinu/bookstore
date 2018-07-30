<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="${staticPath}/css/admin/top.css" type="text/css">
    <script type="text/javascript" src="${staticPath}/js/admin/top.js"></script>
</HEAD>
<body>
<div class="header-img">
    <img src="${root}/image/admin/top_100.jpg" width="100%" height="100%">
</div>
<div class="header-bar">
    <time></time>
    <a href="${adminPath}/logout" target="_parent" class="logout">
        注销
    </a>
    <span class="username">
        用户名：${adminUser.username}
    </span>
</div>
</body>
</html>
