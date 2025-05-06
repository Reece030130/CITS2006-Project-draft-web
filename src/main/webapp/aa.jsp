<%@ page import="java.util.List" %>
<%@ page import="com.example.restaurant.domain.Menu" %>
<%--
  Created by IntelliJ IDEA.
  User: yhz
  Date: 2022/11/15
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${sessionScope.lists}" var="list" varStatus="s">
    ${list.mname};
</c:forEach>
</body>
</html>
