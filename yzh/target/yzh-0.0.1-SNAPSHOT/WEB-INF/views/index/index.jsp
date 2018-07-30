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
    <div id="myCarousel" class="carousel slide col-md-9" data-interval="3000">
        <!-- 轮播索引指标 -->
        <ol class="carousel-indicators">
            <c:forEach items="${newestBooksList}" var="book" varStatus="status">
                <li data-target="#myCarousel" data-slide-to="${status.index}"
                    <c:if test="${status.index == 1}">class="active"</c:if>
                ></li>
            </c:forEach>
        </ol>
        <!-- 轮播项 -->
        <div class="carousel-inner">
            <c:forEach items="${newestBooksList}" var="book" varStatus="status">
                <div class="item
                    <c:if test="${status.index == 1}">active</c:if>
                  ">
                    <a href="${root}/book/detail/${book.id}">
                        <img src="${root}${book.image}" class="crousel-img">
                    </a>
                </div>
            </c:forEach>
        </div>
        <!-- 左右按钮 -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <%--登录注册、购物车--%>
    <div class="col-md-3 user-center">
        <%--标签页--%>
        <c:choose>
            <c:when test="${empty ordinaryUser}">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#loginFormPane" data-toggle="tab">登录</a></li>
                    <li><a href="#registerFormPane" data-toggle="tab">注册</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="loginFormPane">
                            <%--登录表单--%>
                        <form class="form-horizontal" action="${root}/login" id="loginForm">
                            <div class="form-group">
                                <label for="username1" class="control-label col-sm-3">用户名</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="username" id="username1">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password1" class="control-label col-sm-3">密码</label>
                                <div class="col-sm-9">
                                    <input type="password" class="form-control" name="password" id="password1">
                                </div>
                            </div>
                            <div class="col-sm-3 col-sm-offset-9">
                                <button class="btn btn-default" type="button" id="login-btn">登录</button>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane" id="registerFormPane">
                            <%--注册表单--%>
                        <form class="form-horizontal" action="${root}/register" id="registerForm">
                            <div class="form-group">
                                <label for="username2" class="control-label col-sm-3">用户名</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" name="username" id="username2">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password2" class="control-label col-sm-3">密码</label>
                                <div class="col-sm-9">
                                    <input type="password" class="form-control" name="password" id="password2">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="rePassword2" class="control-label col-sm-3">重复密码</label>
                                <div class="col-sm-9">
                                    <input type="password" class="form-control" name="rePassword" id="rePassword2">
                                </div>
                            </div>
                            <div class="col-sm-3 col-sm-offset-9">
                                <button class="btn btn-default" type="button" id="register-btn">注册</button>
                            </div>
                        </form>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <%--修改密码表单--%>
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#resetFormPane" data-toggle="tab">修改密码</a></li>
                    <li><a href="${root}/item/cart">
                        <span class="glyphicon glyphicon-shopping-cart"></span>
                    </a></li>
                    <li><a href="${root}/linkInfo">收货地址</a></li>
                    <li><a href="${root}/logout">注销</a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="resetFormPane">
                        <form class="form-horizontal" action="${root}/reset" id="resetForm">
                            <div class="form-group">
                                <label for="oldPassword3" class="control-label col-sm-4">原始密码</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" name="oldPassword" id="oldPassword3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password3" class="control-label col-sm-4">密码</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" name="password" id="password3">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="rePassword3" class="control-label col-sm-4">重复密码</label>
                                <div class="col-sm-8">
                                    <input type="password" class="form-control" name="rePassword" id="rePassword3">
                                </div>
                            </div>
                            <div class="col-sm-3 col-sm-offset-9">
                                <button class="btn btn-default" type="button" id="reset-btn">修改</button>
                            </div>
                        </form>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<%--最新--%>
<div class="container-fluid">
    <div class="row">
        <h3 class="category-title">最新上架</h3>
        <div class="col-md-2 book-advert">
            <img src="${root}/image/advert/hot.jpg" class="advert-img"/>
        </div>
        <c:forEach items="${newestBooksList}" var="book">
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
    </div>
</div>
<%--热门--%>
<div class="container-fluid">
    <div class="row">
        <h3 class="category-title">热门书籍</h3>
        <c:forEach items="${hottestBooksList}" var="book">
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
    </div>
</div>
<%@ include file="/WEB-INF/views/index/footer.jsp" %>
</body>
<script type="text/javascript" src="${staticPath}/js/index.js"></script>
</html>