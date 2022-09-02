<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>board</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css">
    <style type="text/css">
        .paging {
            color: blue;
            text-decoration: none;
        }

        .currentPaging {
            color: red;
            font-weight: bold;
            text-decoration: underline;
        }

        td > a:link {
            color: black;
            text-decoration: none;
        }

        td > a:visited {
            color: black;
            text-decoration: none;
        }

        td > a:hover {
            color: green;
            text-decoration: underline;
        }

        td > a:active {
            color: black;
            text-decoration: none;
        }
    </style>
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
                <tr>
                    <td>${list.boardIdx}</td>
                    <td class="title"><a href="/board/${list.boardIdx}">${list.title}</a></td>
                    <td>${list.hitCnt}</td>
                    <td>${list.createdDatetime}</td>
                        <%--                <td><fmt:formatDate value="${list.createdDatetime}" pattern="yyyy.MM.dd HH:mm:ss"/></td>--%>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${list.size() == 0}">
            <td colspan="4">조회된 결과가 없습니다.</td>
        </c:if>
        </tbody>
    </table>
    <div style="text-align: center">
        <c:if test="${pageMaker.criteria.pageNum > pageMaker.pageCount}">
            <a class="paging" href="/board?pageNum=${pageMaker.startPage - 1}">이전</a>
        </c:if>

        <c:forEach var="i" begin="${pageMaker.startPage}" end="${pageMaker.endPage}" step="1">
            <c:if test="${i == pageMaker.criteria.pageNum}">
                <a class="currentPaging" href="/board?pageNum=${i}">${i}</a>
            </c:if>
            <c:if test="${i != pageMaker.criteria.pageNum}">
                <a class="paging" href="/board?pageNum=${i}">${i}</a>
            </c:if>
        </c:forEach>

        <c:if test="${pageMaker.endPage < pageMaker.realEnd}">
            <a class="paging" href="/board?pageNum=${pageMaker.endPage + 1}">다음</a>
        </c:if>
    </div>
    <a href="/board/write" class="btn">글 쓰기</a>
</div>
</body>
</html>
