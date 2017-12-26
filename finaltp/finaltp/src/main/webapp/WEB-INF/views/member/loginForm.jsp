<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<c:choose>
<c:when test="${empty cookie.saveid.value }">
	<c:set var="saveid" value=""/>
	<c:set var="state" value=""/>
</c:when>
<c:otherwise>
	<c:set var="saveid" value="${cookie.saveid.value }"/>
	<c:set var="state" value="checked"/>
</c:otherwise>
</c:choose>

<body>
로그인 화면
<form name="loginfm" action="login.do" method="post">
<label>ID(email)</label> <input type="text" name="id" value="${saveid }"><br>
<label>PWD</label> <input type="password" name="pwd">
<br>

<input type="checkbox" name="saveid" ${state }>ID저장 <input type="button" value="비밀번호 찾기" onclick="">
<br>
<input type="submit" value="LOGIN"> 
</form>

<br>
<input type="button" value="네이버 로그인" onclick="">
</body>
</html>