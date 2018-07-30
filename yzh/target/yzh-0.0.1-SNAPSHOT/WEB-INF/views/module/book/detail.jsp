<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<html>
<head>
    <title>图书商城</title>
    <link rel="stylesheet" href="${staticPath}/css/detail.css" type="text/css">
    <link rel="stylesheet" href="${staticPath}/css/index/common.css" type="text/css">
    <script type="text/javascript">
        function cal(addNum) {
            var num = parseInt($("#num").val());
            var price = ${book.price};
            num += addNum;
            if (num == 1) {
                $("#minus").attr("onclick", "");
            } else {
                $("#minus").attr("onclick", "cal(-1);");
            }
            $("#num").val(num);
            $("#total").html("&yen;" + (num * price));
        }

        function addToCart(id) {
            var num = $("#num").val();
            $.post('${root}/item/addToCart', {"bookId": id, "num": num}, function (data) {
                layer.msg(data.msg);
                if (data.code == 200) {
                    setTimeout(function () {
                        location.href = "${root}/item/cart";
                    }, 1000);
                }
            }, "json");
        }
    </script>
</head>
<body>
<%@ include file="/WEB-INF/views/index/header.jsp" %>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
            <img src="${root}${book.image}" class="detail-img">
        </div>
        <div class="col-md-9">
            <c:if test="${book.isHot == 1}">
                <h3 class="detail-hot">热门书籍</h3>
            </c:if>
            <h3 class="detail-text">${fns:getValue(categoryType,book.category ,"" )}</h3>
            <h3 class="detail-name">${book.name}</h3>
            <h3 class="detail-text">发布日期：<fmt:formatDate value="${book.publishDate}" pattern="yyyy-MM-dd"/></h3>
            <h3 class="detail-text">作者：${book.author}</h3>
            <h3 class="detail-text">上架时间：<fmt:formatDate value="${book.shelvesTime}" pattern="yyyy-MM-dd"/></h3>
            <h3 class="detail-text">单价：<span class="detail-price">&yen;${book.price}</span></h3>
            <div class="input-group col-md-2">
                <span class="input-group-addon" id="minus">-</span>
                <input type="text" class="form-control" name="num" value="1" id="num">
                <span class="input-group-addon" onclick="cal(1);">+</span>
            </div>
            <h3 class="detail-text">总计：<span class="detail-price" id="total">&yen;${book.price}</span></h3>
            <c:if test="${not empty ordinaryUser}">
                <button class="btn btn-danger addIntoCart" onclick="addToCart('${book.id}');">加入购物车</button>
            </c:if>
        </div>
    </div>
</div>
<div class="detail-footer">
    <%@ include file="/WEB-INF/views/index/footer.jsp" %>
</div>
</body>
<script type="text/javascript" src="${staticPath}/js/index.js"></script>
</html>