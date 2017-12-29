<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<c:set var="id" value="${id}"/>
<c:if test="${empty id}">
<c:set var="id" value=""/>
</c:if>

<body>
<form action="sendMail.do" method="POST">
<label>ID(Email)</label>
<input type="email" value="${id }" name="email" id="email" placeholder="email(ex, id@gmail.com)" class="form-control" aria-describedby="emailHelp">
<input type="submit" value="인증">
</form>
</body>
</html>