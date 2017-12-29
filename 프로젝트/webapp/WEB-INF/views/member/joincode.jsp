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
<c:set var="ok" value="${sessionScope.joinCode }" />
<form action="joincode.do" method="post">
<input type="hidden" name="ok" id="ok" value="${ok}">
<input type="text" name="joincode" id="joincode" placeholder="join code">
<input type="submit" value="인증">
</form>
</body>
</html>