<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<html>
<head>
    <title>图书信息修改</title>
    <script src="${staticPath}/js/form.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            $("#publishDate").change(function () {
                var input = $(this);
                var publishDate = input.val();
                if (publishDate && publishDate != '') {
                    var date = new Date(Date.parse(publishDate.replace(/-/g, "/")));
                    $("#ActuallyPublishDate").val(date);
                }
            });
        });

        function edit() {
            $("#mainForm").ajaxSubmit({
                url: '${adminPath}/book/edit',
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
    </script>
</head>
<body>
<form class="form-horizontal" id="mainForm" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${book.id}">
    <div class="form-group">
        <label class="control-label col-sm-1" for="name">书名</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" name="name" value="${book.name}" id="name" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="author">作者</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" name="author" value="${book.author}" id="author">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="price">价格</label>
        <div class="col-sm-2">
            <input type="number" class="form-control" name="price" value="${book.price}" id="price" required>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="publishDate">发布日期</label>
        <div class="col-sm-2">
            <input type="date" class="form-control" id="publishDate"
                   value="<fmt:formatDate value="${book.publishDate}" pattern="yyyy-MM-dd"/>"
            required>
            <input type="hidden" class="form-control" name="publishDate" id="ActuallyPublishDate"
                   value="<fmt:formatDate value="${book.publishDate}" pattern="yyyy/MM/dd"/>">
        </div>
    </div>
    <div class="form-group">
        <label for="type" class="control-label col-sm-1"></label>
        <div class="checkbox col-sm-2" id="type">
            <label>
                <input type="checkbox" name="isShelves" id="isShelves" value="1"
                       <c:if test="${book.isShelves == 1}">checked</c:if>
                >上架
            </label>
            <label>
                <input type="checkbox" name="isHot" id="isHot" value="1"
                       <c:if test="${book.isHot == 1}">checked</c:if>
                >热门
            </label>
        </div>
    </div>
    <div class="form-group">
        <label for="category" class="control-label col-sm-1">分类</label>
        <div class="checkbox col-sm-2" id="category">
            <select class="form-control" name="category">
                <c:forEach items="${fns:listByType(categoryType)}" var="dict">
                    <option value="${dict.key}"
                            <c:if test="${book.category == dict.key}">selected</c:if>
                    >${dict.value}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="col-sm-offset-1" style="margin-top: 15px;">
        <button type="button" class="btn btn-default" onclick="edit();">修改</button>
        <button type="button" class="btn btn-default" onclick="history.back(-1);">返回</button>
    </div>
</form>
</body>
</html>
