<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>공통 에러 페이지</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css">
</head>
<body>
<body>
<p>공통 에러 페이지</p>
<p>${exception}</p>
<c:forEach var="list" items="${exception.getStackTrace()}">
    <ul>${list.toString()}</ul>
</c:forEach>
</body>
</body>
</html>
