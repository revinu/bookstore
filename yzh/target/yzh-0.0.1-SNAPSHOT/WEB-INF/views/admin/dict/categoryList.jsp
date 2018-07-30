<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<html>
<head>
    <title>分类列表</title>
    <script>
        function add() {
            layer.prompt({title: '请输入分类值(正整数)', formType: 0}, function (key, keyWin) {
                if (/^[1-9][0-9]*$/.test(key)) {
                    layer.close(keyWin);
                    layer.prompt({title: '请输入分类名', formType: 0}, function (value, valueWin) {
                        layer.close(valueWin);
                        $.ajax({
                            url: '${adminPath}/d/category/add',
                            type: 'post',
                            cache: false,
                            data: {"key": key, "value": value},
                            dataType: 'json',
                            success: function (data) {
                                layer.msg(data.msg);
                                if (data.code == 200) {
                                    setTimeout(function () {
                                        location.reload();
                                    }, 1000);
                                }
                            }
                        });
                    });
                } else {
                    layer.msg("请输入正整数");
                }
            });
        }

        function edit(id) {
            layer.prompt({title: '请输入分类名', formType: 0}, function (value, index) {
                layer.close(index);
                $.ajax({
                    url: '${adminPath}/d/category/edit',
                    type: 'post',
                    cache: false,
                    data: {"id": id, "value": value},
                    dataType: 'json',
                    success: function (data) {
                        layer.msg(data.msg);
                        if (data.code == 200) {
                            setTimeout(function () {
                                location.reload();
                            }, 1000);
                        }
                    }
                });
            });
        }
    </script>
</head>
<body>
<form class="form-inline" id="searchForm" action="${adminPath}/d/category" method="post">
    <input type="hidden" name="page.curPage" value="${dict.page.curPage}" id="curPage">
    <input type="hidden" name="page.pageSize" value="${dict.page.pageSize}" id="pageSize">
    <input type="hidden" name="page.totalCount" value="${dict.page.totalCount}" id="totalCount">
    <input type="hidden" name="page.pageCount" value="${dict.page.pageCount}" id="pageCount">
    <div class="form-group">
        <label for="key">分类值</label>
        <input type="number" class="form-control" name="key" value="${dict.key}" id="key">
    </div>
    <div class="form-group">
        <label for="value">分类名</label>
        <input type="text" class="form-control" name="value" value="${dict.value}" id="value">
    </div>
    <button class="btn btn-default">查询</button>
</form>
<table class="table table-bordered table-condensed table-hover">
    <thead>
    <tr>
        <th>分类值</th>
        <th>分类名</th>
        <th>
            <button class="btn btn-default" onclick="add();">
                添加分类
            </button>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="dict">
        <tr>
            <td>${dict.key}</td>
            <td>${dict.value}</td>
            <td>
                <button class="btn btn-default" onclick="edit('${dict.id}');">
                    编辑
                </button>
                <button class="btn btn-default" onclick="del('${adminPath}/d/remove','${dict.id}','分类');">
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
