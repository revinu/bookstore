<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Language" content="zh-cn">
    <title>首页</title>
    <link rel="stylesheet" href="${staticPath}/css/admin/index.css" type="text/css">
</head>
<iframe src="${root}/frame/top" scrolling="NO" id="top-iframe"></iframe>
<div id="left">
    <iframe src="${root}/frame/left" scrolling="NO" id="left-iframe"></iframe>
</div>
<div id="right">
    <iframe src="${root}/frame/welcome" scrolling="YES" id="right-iframe" name="mainIframe"></iframe>
</div>
<iframe src="${root}/frame/bottom" scrolling="NO" id="bottom-iframe"></iframe>
</html>
