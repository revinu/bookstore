<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<html>
<head>
    <title>添加图书</title>
    <script src="${staticPath}/js/form.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            layui.use('upload', function () {
                var upload = layui.upload;
                upload.render({
                    elem: '#bookImage',
                    auto: false
                });
            });

            $("#publishDate").change(function () {
                var input = $(this);
                var publishDate = input.val();
                if (publishDate && publishDate != '') {
                    var date= new Date(Date.parse(publishDate.replace(/-/g,  "/")));
                    $("#ActuallyPublishDate").val(date);
                }
            });
        });

        function sub() {
            var publishDate = $("#publishDate").val();
            if(!publishDate || publishDate === '') {
                layer.msg("请填写发布日期");
                return false;
            }
            var file = $("input[name='file']").val();
            if (!file || file == null) {
                layer.msg("请添加图片");
                return false;
            }
            $("#mainForm").ajaxSubmit({
                url: '${adminPath}/book/add',
                type: 'post',
                cache: false,
                dataType: 'json',
                success: function (data) {
                    layer.msg(data.msg);
                    if (data.code == 200) {
                        setTimeout(function () {
                            location.href = '${adminPath}/book';
                        }, 1000);
                    }
                }
            });
        }
    </script>
</head>
<body>
<form class="form-horizontal" id="mainForm" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label class="control-label col-sm-1" for="name">书名</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" name="name" id="name">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="author">作者</label>
        <div class="col-sm-2">
            <input type="text" class="form-control" name="author" id="author">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="price">价格</label>
        <div class="col-sm-2">
            <input type="number" class="form-control" name="price" id="price">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-1" for="publishDate">发布日期</label>
        <div class="col-sm-2">
            <input type="date" class="form-control" id="publishDate">
            <input type="hidden" class="form-control" name="publishDate" id="ActuallyPublishDate">
        </div>
    </div>
    <div class="form-group">
        <label for="type" class="control-label col-sm-1"></label>
        <div class="checkbox col-sm-2" id="type">
            <label>
                <input type="checkbox" name="isShelves" id="isShelves" value="1" checked>上架
            </label>
            <label>
                <input type="checkbox" name="isHot" id="isHot" value="1">热门
            </label>
        </div>
    </div>
    <div class="form-group">
        <label for="category" class="control-label col-sm-1">分类</label>
        <div class="checkbox col-sm-2" id="category">
            <select class="form-control" name="category">
                <c:forEach items="${fns:listByType(categoryType)}" var="dict">
                    <option value="${dict.key}">${dict.value}</option>
                </c:forEach>
            </select>
        </div>
    </div>
   <button type="button" class="btn btn-default col-sm-offset-1" id="bookImage">
        <i class="layui-icon">&#xe67c;</i>选择图片
    </button>
    <%--<div class="form-group" style="margin-top: 15px;">
        <label class="control-label col-sm-1" for="remark">说明</label>
        <div class="col-sm-2">
            <textarea class="form-control" name="remark" id="remark"
            rows="4"></textarea>
        </div>
    </div>--%>
    <div class="col-sm-offset-1" style="margin-top: 15px;">
        <button type="button" class="btn btn-default" onclick="sub();">发布</button>
        <button type="button" class="btn btn-default" onclick="history.back(-1);">返回</button>
    </div>
</form>
</body>
</html>
