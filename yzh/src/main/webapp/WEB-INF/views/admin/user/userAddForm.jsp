<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<html>
<head>
    <title>添加用户</title>
    <script>
        function formSubmit() {
            $.ajax({
                url: '${adminPath}/user/add',
                type: 'post',
                cache: false,
                data: $("#mainForm").serialize(),
                dataType: 'json',
                success: function (data) {
                    layer.msg(data.msg);
                    if (data.code == 200) {
                        setTimeout(function () {
                            location.href = '${adminPath}/user';
                        }, 1000);
                    }
                }
            });
        }
    </script>
</head>
<body>
<form class="form-horizontal" id="mainForm">
    <div class="form-group">
        <label class="control-label col-sm-1" for="username">用户名</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" name="username" id="username">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="password">密码</label>
        <div class="col-sm-2">
            <input type="password" class="form-control" name="password" id="password">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="rePassword">重复密码</label>
        <div class="col-sm-2">
            <input type="password" class="form-control" name="rePassword" id="rePassword">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="name">姓名</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" name="name" id="name">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="mobile">手机号</label>
        <div class="col-sm-2">
            <input type="number" class="form-control" name="mobile" id="mobile">
        </div>
    </div>
    <div class="form-group">
        <label for="sex" class="control-label col-sm-1">性别</label>
        <div class="col-sm-2" id="sex">
            <label class="radio-inline">
                <input type="radio" name="sex" value="0" checked>男
            </label>
            <label class="radio-inline">
                <input type="radio" name="sex" value="1">女
            </label>
        </div>
    </div>
    <div class="form-group">
        <label for="type" class="control-label col-sm-1">管理员权限</label>
        <div class="checkbox col-sm-2" id="type">
            <label>
                <input type="checkbox">开启
            </label>
        </div>
    </div>
    <div class="col-sm-offset-1">
        <button type="button" class="btn btn-default" onclick="formSubmit();">添加</button>
        <button type="button" class="btn btn-default" onclick="history.back(-1);">返回</button>
    </div>
</form>
</body>
</html>
