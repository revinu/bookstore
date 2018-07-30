<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<html>
<head>
    <title>分类列表</title>
    <script>
        function del(id) {
            layer.confirm('确定删除该分类?', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.ajax({
                    url: '${adminPath}/category/remove',
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
                });
            });
        };

        function add() {
            layer.prompt({title: '请输入分类名', formType: 0}, function (text, index) {
                layer.close(index);
                $.ajax({
                    url: '${adminPath}/category/add',
                    type: 'post',
                    cache: false,
                    data: {"name": text},
                    dataType: 'json',
                    success: function (data) {
                        layer.msg(data.msg);
                        setTimeout(function () {
                            location.reload();
                        }, 1000);
                    }
                });
            });
        }

        function edit(id) {
            layer.prompt({title: '请输入分类名', formType: 0}, function (text, index) {
                layer.close(index);
                $.ajax({
                    url: '${adminPath}/category/edit',
                    type: 'post',
                    cache: false,
                    data: {"id": id, "name": text},
                    dataType: 'json',
                    success: function (data) {
                        layer.msg(data.msg);
                        setTimeout(function () {
                            location.reload();
                        }, 1000);
                    }
                });
            });
        }
    </script>
</head>
<body>
<form class="form-inline" id="searchForm">
    <input type="hidden" name="page.curPage" value="${category.page.curPage}" id="curPage">
    <input type="hidden" name="page.pageSize" value="${category.page.pageSize}" id="pageSize">
    <input type="hidden" name="page.totalCount" value="${category.page.totalCount}" id="totalCount">
    <input type="hidden" name="page.pageCount" value="${category.page.pageCount}" id="pageCount">
    <div class="form-group">
        <label for="name">分类名</label>
        <input type="text" class="form-control" name="name" value="${category.name}" id="name">
    </div>
    <button class="btn btn-default">查询</button>
</form>
<table class="table table-bordered table-condensed table-hover">
    <thead>
    <tr>
        <th>ID</th>
        <th>分类名</th>
        <th>
            <button class="btn btn-default" onclick="add();">
                添加分类
            </button>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="category">
        <tr>
            <td>${category.id}</td>
            <td>${category.name}</td>
            <td>
                <button class="btn btn-default" onclick="edit('${category.id}');">
                    编辑
                </button>
                <button class="btn btn-default" onclick="del('${category.id}');">
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
