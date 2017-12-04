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

<h3>index</h3>
<c:set var="userid" value="${sessionScope.userid}" />
<c:if test="${empty userid }">
		<p><a href="login.do">Login</a> | <a href="join.do">Join</a></p>
</c:if>

<c:if test="${!empty userid }">
		<p><a href="mypage.do?id=${userid}">${name}</a>님 접속중입니다. | <a href="logout.do">로그아웃</a></p>
</c:if>

</body>
</html>