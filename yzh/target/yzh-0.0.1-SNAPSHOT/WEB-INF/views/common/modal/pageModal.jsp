<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<html>
<style type="text/css">
    .pagination li a {
        cursor: pointer;
    }
</style>
<head>
    <title>分页</title>
    <script>
        $(function () {
            var curPage = parseInt($("#curPage").val());
            var pageCount = parseInt($("#pageCount").val());

            var pPage = curPage - 1;
            var nPage = curPage + 1;
            var pagination = $(".pagination");
            if (curPage <= 1) {
                pagination.append("<li class='disabled'><a>上一页</a></li>");
            } else {
                pagination.append("<li><a data-id='" + pPage + "'>上一页</a></li>");
            }
            pagination.append("<li class='disabled'><a data-id='" + curPage + "'>" + curPage + "</a></li>");
            if (curPage >= pageCount) {
                pagination.append("<li class='disabled'><a>下一页</a></li>");
            } else {
                pagination.append("<li><a data-id='" + nPage + "'>下一页</a></li>");
            }
            $(".pagination li a").click(function () {
                if (!$(this).parent().hasClass("disabled")) {
                    var pageNo = $(this).attr("data-id");
                    $("#curPage").val(pageNo);
                    $("#searchForm").submit();
                }
            });
        });
    </script>
</head>
<body>
<div style="clear: both;text-align: center;">
    <nav aria-label="Page navigation">
        <ul class="pagination">
        </ul>
    </nav>
</div>
</body>
</html>
