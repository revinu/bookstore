<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<html>
<head>
    <title>我的购物车</title>
    <link rel="stylesheet" href="${staticPath}/css/index/common.css" type="text/css">
    <script type="text/javascript">
        function place() {
            var idInputs = $("input[type='checkbox'][name='id']:checked");
            if (!idInputs || idInputs.length == 0) {
                alert("请勾选购买下单项");
                return;
            }
            var linkinfoId = $("#linkinfo").find("option:selected").val();
            if (!linkinfoId) {
                alert("请选择收货地址");
                return;
            }
            var itemIds = "";
            for (var i = 0; i < idInputs.length; i++) {
                if (i == idInputs.length - 1) {
                    itemIds += idInputs[i].value;
                } else {
                    itemIds += idInputs[i].value + ",";
                }
            }
            layer.load(1, {
                shade: [0.1, '#fff'],
                time: 3000
            });
            alert("模拟支付...");
            $.post('${root}/order/place', {"itemIds": itemIds, "linkinfoId": linkinfoId}, function (data) {
                layer.msg(data.msg);
                if (data.code == 200) {
                    setTimeout(function () {
                        location.reload();
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
        <c:choose>
            <c:when test="${not empty list}">
                <div class="col-md-10 col-md-offset-1">
                    <h3 class="category-title">清单</h3>
                    <table class="table table-bordered table-condensed table-hover">
                        <thead>
                        <tr>
                            <th></th>
                            <th>图片</th>
                            <th>书名</th>
                            <th>作者</th>
                            <th>单价</th>
                            <th>数量</th>
                            <th>总价</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="item">
                            <c:choose>
                                <c:when test="${item.book.isShelves == 0}">
                                    <tr style="background-color: #cccccc">
                                        <td>已下架</td>
                                        <td><img src="${root}${item.book.image}" height="96px;" width="84px;"></td>
                                        <td>${item.book.name}</td>
                                        <td>${item.book.author}</td>
                                        <td>&yen;${item.book.price}</td>
                                        <td>${item.num}</td>
                                        <td>&yen;${item.totalPrice}</td>
                                        <td>
                                            <button class="btn btn-default"
                                                    onclick="del('${root}/item/delete','${item.id}','项');">
                                                移除
                                            </button>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td><input type="checkbox" name="id" value="${item.id}"></td>
                                        <td>
                                            <a href="${root}/book/detail/${item.book.id}">
                                                <img src="${root}${item.book.image}" height="96px;" width="84px;">
                                            </a>
                                        </td>
                                        <td>${item.book.name}</td>
                                        <td>${item.book.author}</td>
                                        <td>&yen;${item.book.price}</td>
                                        <td>${item.num}</td>
                                        <td>&yen;${item.totalPrice}</td>
                                        <td>
                                            <button class="btn btn-default"
                                                    onclick="del('${root}/item/delete','${item.id}','项');">
                                                移除
                                            </button>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-md-6">
                        <div class="input-group">
                            <span class="input-group-addon">银行卡号</span>
                            <select class="form-control">
                                <option>只做模拟，可不选</option>
                                <option>123123123123123123</option>
                                <option>123125614811223123</option>
                                <option>194131124811223123</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="input-group">
                            <span class="input-group-addon">支付密码</span>
                            <input type="text" class="form-control" placeholder="只做模拟，可不填">
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="input-group" style="margin-top: 15px;">
                            <span class="input-group-addon">选择收货地址</span>
                            <select class="form-control" id="linkinfo">
                                <c:forEach items="${linkInfoList}" var="linkinfo">
                                    <option value="${linkinfo.id}">
                                        [收件人：${linkinfo.name}，联系方式：${linkinfo.mobile}，收件地址：${linkinfo.address}]
                                    </option>
                                </c:forEach>
                            </select>
                            <span class="input-group-btn">
                                <button class="btn btn-danger" type="button" onclick="place();">下单结算</button>
                            </span>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <a href="${root}" class="col-md-12" style="font-size: 20px;">
                    购物车空空如也，前去挑选商品
                </a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<c:choose>
    <c:when test="${not empty list}">
        <%@ include file="/WEB-INF/views/index/footer.jsp" %>
    </c:when>
    <c:otherwise>
        <div style="position: absolute;bottom: 5px;">
            <%@ include file="/WEB-INF/views/index/footer.jsp" %>
        </div>
    </c:otherwise>
</c:choose>
</body>
<script type="text/javascript" src="${staticPath}/js/index.js"></script>
</html>