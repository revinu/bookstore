<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<%--导航条--%>
<div class="container-fluid" style="margin-top: 15px;">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${root}">首页</a>
                <c:forEach items="${fns:listByType(categoryType)}" var="dict">
                    <a class="navbar-brand" href="${root}/book/category/${dict.key}">${dict.value}</a>
                </c:forEach>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="menuID" class="nav navbar-nav">
                </ul>
                <form class="navbar-form navbar-right" role="search" action="${root}/search" method="post">
                    <div class="form-group">
                        <input type="text" class="form-control" name="name" placeholder="${book.name}">
                    </div>
                    <button type="submit" class="btn btn-default">搜索</button>
                </form>
            </div>
        </div>
    </nav>
</div>