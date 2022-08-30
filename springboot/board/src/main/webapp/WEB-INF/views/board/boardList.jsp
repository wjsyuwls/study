<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>board</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css">
</head>
<body>
<div class="container">
    <h2>게시글 목록</h2>
    <table class="board_list">
        <colgroup>
            <col width="15%"/>
            <col width="*"/>
            <col width="15%"/>
            <col width="20%"/>
        </colgroup>
        <thead>
        <tr>
            <th scope="col">글번호</th>
            <th scope="col">제목</th>
            <th scope="col">조회수</th>
            <th scope="col">작성일</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${list.size() > 0}">
            <c:forEach var="list" items="${list}">
                <td>${list.boardIdx}</td>
                <td class="title"><a href="/board/openBoardDetail?boardIdx=${list.boardIdx}">${list.title}</a></td>
                <td>${list.hitCnt}</td>
                <td>${list.createdDatetime}</td>
<%--                <td><fmt:formatDate value="${list.createdDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>--%>
            </c:forEach>
        </c:if>
        <c:if test="${list.size() == 0}">
            <td colspan="4">조회된 결과가 없습니다.</td>
        </c:if>
        </tbody>
    </table>
    <a href="/board/openBoardWrite" class="btn">글 쓰기</a>
</div>
</body>
</html>
