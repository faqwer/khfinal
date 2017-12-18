<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>FAQ</h2>
<c:if test="${empty faqList}">
	<div>등록된 게시글이 없습니다.</div>
</c:if>
<c:forEach var="dto" items="${faqList}">
	<div>
		<div id="question">
			-&nbsp;&nbsp;${dto.question}
		</div>
		<div id="answer">
			${dto.answer}
		</div>
	</div>
</c:forEach>
</body>
</html>