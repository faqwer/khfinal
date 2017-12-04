
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="userid" value="${sessionScope.userid}"/>
<form align="center" name="memberoutForm" action="memberOut.do?id=${userid }">
<input type="hidden" name="id" value="${userid}">
<h2>회 원 탈 퇴</h2>

${userid }님 정말 회원 탈퇴 하겠습니까?
<br>

<h4></h4><input type="submit" value="예">
<input type="button" value="아니요"></h4>
</form>
</body>
</html>