<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<html>
<head>
    <title>编辑用户信息</title>
    <script>
        function formSubmit() {
            $.ajax({
                url: '${adminPath}/user/edit',
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
    <input type="hidden" name="id" value="${user.id}">
    <div class="form-group">
        <label class="control-label col-sm-1" for="username">用户名</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" name="username" value="${user.username}" id="username">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="password">密码</label>
        <div class="col-sm-2">
            <input type="password" class="form-control" name="password" value="${user.password}" id="password"
                   placeholder="不修改密码请勿填">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="rePassword"
               placeholder="不修改密码请不勿填">重复密码</label>
        <div class="col-sm-2">
            <input type="password" class="form-control" name="rePassword" id="rePassword">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="name">姓名</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" name="name" value="${user.name}" id="name">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="mobile">手机号</label>
        <div class="col-sm-2">
            <input type="number" class="form-control" name="mobile" value="${user.mobile}" id="mobile">
        </div>
    </div>
    <div class="form-group">
        <label for="sex" class="control-label col-sm-1">性别</label>
        <div class="col-sm-2" id="sex">
            <label class="radio-inline">
                <input type="radio" name="sex" value="0"
                       <c:if test="${user.sex == 0}">checked</c:if>
                >男
            </label>
            <label class="radio-inline">
                <input type="radio" name="sex" value="1"
                       <c:if test="${user.sex == 1}">checked</c:if>
                >女
            </label>
        </div>
    </div>
    <div class="form-group">
        <label for="type" class="control-label col-sm-1">管理员权限</label>
        <div class="checkbox col-sm-2" id="type">
            <label>
                <input type="checkbox"
                       <c:if test="${user.type == 1}">checked</c:if>
                >开启
            </label>
        </div>
    </div>
    <div class="col-sm-offset-1">
        <button type="button" class="btn btn-default" onclick="formSubmit();">修改</button>
        <button type="button" class="btn btn-default" onclick="history.back(-1);">返回</button>
    </div>
</form>
</body>
</html>
