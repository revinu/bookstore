<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<html>
<head>
    <title>图书列表</title>
    <script type="text/javascript">
        $(function () {
            layui.use('upload', function () {
                var upload = layui.upload;
                upload.render({
                    elem: '#bookImage',
                    auto: false,
                    choose: function (obj) {
                        if (confirm("确定修改?")) {
                            $("#imageForm").ajaxSubmit({
                                url: '${adminPath}/book/updateImage',
                                type: 'post',
                                cache: false,
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
                        }
                        ;
                    }
                });
            });
        });
    </script>
</head>
<body>
<form class="form-inline" id="searchForm">
    <input type="hidden" name="page.curPage" value="${book.page.curPage}" id="curPage">
    <input type="hidden" name="page.pageSize" value="${book.page.pageSize}" id="pageSize">
    <input type="hidden" name="page.totalCount" value="${book.page.totalCount}" id="totalCount">
    <input type="hidden" name="page.pageCount" value="${book.page.pageCount}" id="pageCount">
    <div class="form-group">
        <label for="name">书名</label>
        <input type="text" class="form-control" name="name" value="${book.name}" id="name">
    </div>
    <div class="form-group">
        <label for="author">作者</label>
        <input type="text" class="form-control" name="author" value="${book.author}" id="author">
    </div>
    <button class="btn btn-default">查询</button>
</form>
<table class="table table-bordered table-condensed table-hover">
    <thead>
    <tr>
        <th>图片</th>
        <th>书名</th>
        <th>作者</th>
        <th>价格</th>
        <th>发布日期</th>
        <th>上架时间</th>
        <th>是否热门</th>
        <th>是否上架</th>
        <th>类别</th>
        <th>
            <button class="btn btn-default" onclick="location.href='${adminPath}/book/addForm'">
                发布图书
            </button>
        </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="book">
        <tr>
            <td><img src="${root}${book.image}" height="100px;" width="auto;"></td>
            <td>${book.name}</td>
            <td>${book.author}</td>
            <td>&yen;${book.price}</td>
            <td><fmt:formatDate value="${book.publishDate}" pattern="yyyy-MM-dd"/></td>
            <td><fmt:formatDate value="${book.shelvesTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td>
                <c:choose>
                    <c:when test="${book.isHot == 1}">
                        是
                    </c:when>
                    <c:otherwise>
                        否
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${book.isShelves == 1}">
                        是
                    </c:when>
                    <c:otherwise>
                        否
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${fns:getValue('category',book.category ,"" )}</td>
            <td>
                <button class="btn btn-default" onclick="location.href='${adminPath}/book/form?id=${book.id}'">
                    编辑
                </button>
                <button class="btn btn-default" onclick="del('${adminPath}/book/delete','${book.id}','图书');">
                    删除
                </button>
                <form id="imageForm" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${book.id}">
                    <button type="button" class="btn btn-default" id="bookImage">
                        <i class="layui-icon">&#xe67c;</i>修改图片
                    </button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="/WEB-INF/views/common/modal/pageModal.jsp"/>
</body>
<script src="${staticPath}/js/form.js" type="text/javascript"></script>
</html>
