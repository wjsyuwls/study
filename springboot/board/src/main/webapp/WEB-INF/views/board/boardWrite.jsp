<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>board</title>
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css">
</head>
<body>
<div class="container">
    <h2>게시글 등록</h2>
    <form id="frm" name="frm" method="post" action="/board/insertBoard" enctype="multipart/form-data">
        <table class="board_detail">
            <tr>
                <td>제목</td>
                <td><input type="text" id="title" name="title"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <textarea id="contents" name="contents"></textarea>
                </td>
            </tr>
        </table>
        <input type="file" id="files" name="files" multiple="multiple">
        <input type="submit" id="submit" value="저장" class="btn">
    </form>
</div>
</body>
</html>
