<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MBTI 테스트</title>
<style>
    .question {
        margin-bottom: 15px;
        padding: 10px;
        border: 1px solid #ddd;
        background: #f9f9f9;
    }
    .optional label {
        margin-right: 10px;
    }
</style>
</head>
<body>

	<div class="title">
		 MBTI 검사
	<div>
	<br>

<form action="/board/mbtiAction.do" method="POST">



	<c:forEach var="board" items="${boardList}">
		<div class="question">
		    ${board.boardComment}
		</div>

		<input type="hidden" name="type_${board.boardNum}" value="${board.boardType}">
		<input type="hidden" name="boardNum" value="${board.boardNum}">
	
	    <div class="optional">
	    <span>그렇다</span>
	        <label><input type="radio" name="option_${board.boardNum}" value="1" required></label>
	        <label><input type="radio" name="option_${board.boardNum}" value="2" required></label>
	        <label><input type="radio" name="option_${board.boardNum}" value="3" required></label>
	        <label><input type="radio" name="option_${board.boardNum}" value="4" required></label>
	        <label><input type="radio" name="option_${board.boardNum}" value="5" required></label>
	        <label><input type="radio" name="option_${board.boardNum}" value="6" required></label>
	        <label><input type="radio" name="option_${board.boardNum}" value="7" required>아니다</label>
	    </div>
	    <br>
	

	
</c:forEach>

	<input type="hidden" name="page" value="${curtPage}">
    <input type="submit" value="${curtPage == maxPages ? '제출' : '다음'}">
</form>

</body>
</html>
