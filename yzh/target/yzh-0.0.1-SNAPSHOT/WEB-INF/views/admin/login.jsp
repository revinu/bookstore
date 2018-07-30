<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<html>
<head>
    <title>登录</title>
    <link href="${staticPath}/css/admin/login.css" rel="stylesheet"/>
    <script>
        function login() {
            var loginForm = $("#loginForm");
            var username = $("#username").val();
            var password = $("#password").val();
            if (!username || username === ""
                || !password || password === "") {
                layer.msg("用户名或密码不能为空");
                return;
            }
            $.ajax({
                url: "${adminPath}/login",
                type: "post",
                cache: false,
                data: loginForm.serialize(),
                dataType: "json",
                success: function (data) {
                    layer.msg(data.msg);
                    if (data.code == 200) {
                        setTimeout(function () {
                            top.location.href = "${adminPath}/index";
                        }, 1000);
                    }
                }
            })
        };
    </script>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 col-md-offset-4 login-div">
            <form class="form-horizontal" id="loginForm">
                <div class="form-group">
                    <label for="username" class="control-label col-md-3">用户名</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" name="username" id="username">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="control-label col-md-3">密码</label>
                    <div class="col-md-8">
                        <input type="password" class="form-control" name="password" id="password">
                    </div>
                </div>
                <div class="col-md-2 col-md-offset-8">
                    <button class="btn btn-primary" type="button" onclick="login();">登录</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
