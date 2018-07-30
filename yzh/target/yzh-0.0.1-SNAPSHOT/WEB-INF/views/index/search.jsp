<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<html>
<head>
    <title>图书商城</title>
    <link rel="stylesheet" href="${staticPath}/css/index/index.css" type="text/css">
    <link rel="stylesheet" href="${staticPath}/css/index/common.css" type="text/css">
</head>
<body>
<%@ include file="/WEB-INF/views/index/header.jsp" %>
<%--轮播条—广告图--%>
<div class="container-fluid">
    <div class="row">
        <h3 class="category-title">全部结果</h3>
        <c:choose>
            <c:when test="${not empty list}">
                <c:forEach items="${list}" var="book">
                    <div class="col-sm-2">
                        <div class="thumbnail">
                            <a href="${root}/book/detail/${book.id}">
                                <img src="${root}${book.image}" class="item-img">
                            </a>
                            <h4 class="item-name">${book.name}</h4>
                            <h4 class="item-price">&yen;${book.price}</h4>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h3 class="col-md-12" style="color: red;">
                    无结果
                </h3>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<%@ include file="/WEB-INF/views/index/footer.jsp" %>
</body>
<script type="text/javascript" src="${staticPath}/js/index.js"></script>
</html>