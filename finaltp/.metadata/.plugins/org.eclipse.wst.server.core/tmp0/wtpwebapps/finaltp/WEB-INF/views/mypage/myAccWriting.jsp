
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>myAccWriting</h2>

	<table border="1" cellspacing="0" width="500">
		<tr>
			<td>여행기</td>
			<td colspan="2">동행 구하기</td>

		</tr>
		<c:forEach var="dto" items="${mainList}">
		<tr>

			<td>${dto.accdto.country}</td>
			<td>${dto.content}</td>
			<td><input type="button" value="삭제"></td>
		</tr>
</c:forEach>
	</table>

</body>
</html>