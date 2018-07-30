<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<html>
<head>
    <title>用户列表</title>
    <script>
        function del(id) {
            layer.confirm('确定删除该用户?', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.ajax({
                    url: '${adminPath}/user/delete',
                    type: 'post',
                    cache: false,
                    data: {"id": id},
                    dataType: 'json',
                    success: function (data) {
                        layer.msg(data.msg);
                        setTimeout(function () {
                            location.reload();
                        }, 1000);
                    }
                })
            });
        };
    </script>
</head>
<body>
<form class="form-inline" id="searchForm">
    <input type="hidden" name="page.curPage" value="${user.page.curPage}" id="curPage">
    <input type="hidden" name="page.pageSize" value="${user.page.pageSize}" id="pageSize">
    <input type="hidden" name="page.totalCount" value="${user.page.totalCount}" id="totalCount">
    <input type="hidden" name="page.pageCount" value="${user.page.pageCount}" id="pageCount">
    <div class="form-group">
        <label for="username">用户名</label>
        <input type="text" class="form-control" name="username" value="${user.username}" id="username">
    </div>
    <div class="form-group">
        <label for="name">姓名</label>
        <input type="text" class="form-control" name="name" value="${user.name}" id="name">
    </div>
    <button class="btn btn-default">查询</button>
</form>
<table class="table table-bordered table-condensed table-hover">
    <thead>
    <tr>
        <th>用户名</th>
        <th>姓名</th>
        <th>性别</th>
        <th>手机号</th>
        <th>类型</th>
        <th>
            <button class="btn btn-default" onclick="location.href='${adminPath}/user/addForm'">
                添加用户
            </button>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="user">
        <tr>
            <td>${user.username}</td>
            <td>${user.name}</td>
            <td>
                <c:choose>
                    <c:when test="${user.sex == 0}">
                        男
                    </c:when>
                    <c:otherwise>
                        女
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${user.mobile}</td>
            <td>
                <c:choose>
                    <c:when test="${user.type == 0}">
                        管理员
                    </c:when>
                    <c:otherwise>
                        普通用户
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <button class="btn btn-default" onclick="location.href='${adminPath}/user/form?id=${user.id}'">
                    编辑
                </button>
                <button class="btn btn-default" onclick="del('${user.id}');">
                    删除
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="/WEB-INF/views/common/modal/pageModal.jsp"/>
</body>
</html>
