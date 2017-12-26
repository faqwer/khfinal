<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
h2 {
	text-align: center;
}
#faqContent {
	border: 1px solid #C6C6C6;
	width: 680px;
	height: 100px;
	margin: 0px auto;
	
}

#question {
	margin-left: 5px;
	margin-top: 5px;
	margin: 0px auto;
	font-weight: bold;
}

#answer {
	margin-top: 5px;
}
</style>
</head>
<body>
<h2>FAQ</h2>
<c:if test="${empty faqList}">
	<div>등록된 게시글이 없습니다.</div>
</c:if>
<c:forEach var="dto" items="${faqList}">
	<div id="faqContent">
		<div id="question">
			-&nbsp;&nbsp;${dto.question}
		</div>
		<div id="answer">
			${dto.answer}
		</div>
	</div>
	<br>
</c:forEach>
</body>
</html>