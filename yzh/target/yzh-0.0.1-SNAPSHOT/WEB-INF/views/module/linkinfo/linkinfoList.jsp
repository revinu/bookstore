<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<html>
<head>
    <title>收货地址管理</title>
    <link rel="stylesheet" href="${staticPath}/css/index/common.css" type="text/css">
</head>
<body>
<%@ include file="/WEB-INF/views/index/header.jsp" %>
<div class="container-fluid">
    <div class="row">
        <c:choose>
            <c:when test="${list.size() > 0}">
                <div class="col-md-10 col-md-offset-1">
                    <h3 class="category-title">收货地址管理</h3>
                    <table class="table table-bordered table-condensed table-hover">
                        <thead>
                        <tr>
                            <th>地址</th>
                            <th>邮编</th>
                            <th>姓名</th>
                            <th>联系方式</th>
                            <th>
                                <c:if test="${list.size() < 5}">
                                    <button class="btn btn-default"
                                            onclick="location.href='${root}/linkInfo/addForm'">
                                        添加
                                    </button>
                                </c:if>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="linkinfo">
                            <tr>
                                <td>${linkinfo.address}</td>
                                <td>${linkinfo.zipCode}</td>
                                <td>${linkinfo.name}</td>
                                <td>${linkinfo.mobile}</td>
                                <td>
                                    <button class="btn btn-default"
                                            onclick="location.href='${root}/linkInfo/form?id=${linkinfo.id}'">
                                        编辑
                                    </button>
                                    <button class="btn btn-default"
                                            onclick="del('${root}/linkInfo/delete','${linkinfo.id}','项');">
                                        删除
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <span class="col-md-12" style="font-size: 20px;">
                    暂无收货地址，点击<a href="${root}/linkInfo/addForm" style="color: blue;">添加</a>
                </span>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<div style="position: absolute;bottom: 5px;">
    <%@ include file="/WEB-INF/views/index/footer.jsp" %>
</div>
</body>
<script type="text/javascript" src="${staticPath}/js/index.js"></script>
</html>