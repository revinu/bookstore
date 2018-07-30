<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/taglib.jsp" %>
<html>
<head>
    <title>菜单</title>
    <link rel="StyleSheet" href="${staticPath}/css/admin/dtree.css" type="text/css"/>
    <link rel="stylesheet" href="${staticPath}/css/admin/left.css" type="text/css"/>
</head>
<body>
<div class="dtree">
    <span style="line-height: 30px;">
        <a href="javascript: d.openAll();">展开所有</a> | <a href="javascript: d.closeAll();">关闭所有</a>
    </span>
    <script type="text/javascript" src="${staticPath}/js/admin/dtree.js"></script>
    <script type="text/javascript">
        d = new dTree('d');
        d.add('01', -1, '系统菜单树');
        d.add('0101', '01', '用户管理', '', '', 'mainIframe');
        d.add('010101', '0101', '用户管理', '${adminPath}/user', '', 'mainIframe');
        d.add('0102', '01', '分类管理', '', '', 'mainIframe');
        d.add('010201', '0102', '分类管理', '${adminPath}/d/category', '', 'mainIframe');
        d.add('0104', '01', '图书管理');
        d.add('010401', '0104', '图书管理', '${adminPath}/book', '', 'mainIframe');
        document.write(d);
    </script>
</div>
</body>
</html>
