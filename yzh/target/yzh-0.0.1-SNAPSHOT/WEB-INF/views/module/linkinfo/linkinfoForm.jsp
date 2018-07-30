<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<html>
<head>
    <title>修改收货地址</title>
    <link rel="stylesheet" href="${staticPath}/css/index/common.css" type="text/css">
    <script type="text/javascript">
        function add() {
            $.post('${root}/linkInfo/edit', $("#editForm").serialize(), function (data) {
                alert(data.msg);
                if (data.code == 200) {
                    setTimeout(function () {
                        location.href = '${root}/linkInfo';
                    }, 1000);
                }
            }, 'json');
        }
    </script>
</head>
<body>
<%@ include file="/WEB-INF/views/index/header.jsp" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3 col-md-offset-1">
            <form class="form-horizontal" id="editForm">
                <input type="hidden" name="id" value="${linkinfo.id}">
                <div class="form-group">
                    <label for="address" class="control-label col-sm-3">收货地址</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="address" value="${linkinfo.address}" id="address">
                    </div>
                </div>
                <div class="form-group">
                    <label for="zipCode" class="control-label col-sm-3">邮编</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="zipCode" value="${linkinfo.zipCode}" id="zipCode">
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="control-label col-sm-3">收件人</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="name" value="${linkinfo.name}" id="name">
                    </div>
                </div>
                <div class="form-group">
                    <label for="mobile" class="control-label col-sm-3">联系方式</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" name="mobile" value="${linkinfo.mobile}" id="mobile">
                    </div>
                </div>
                <div class="col-md-1 col-md-offset-9">
                    <button class="btn btn-default" type="button" onclick="add();">修改</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div style="position: absolute;bottom: 0px;">
    <%@ include file="/WEB-INF/views/index/footer.jsp" %>
</div>
</body>
<script type="text/javascript" src="${staticPath}/js/index.js"></script>
</html>