
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
	<h2>myAccWriting</h2>

	<table border="1" cellspacing="0" width="500">
		<tr>
			<td><a href="#" id="myWriting" onclick="javascript:show(1)">여행기</a></td>
			<td><a href="#" id="myAccWriting" onclick="show2(1)">동행구하기</a></td>
		</tr>
		<c:if test="${empty mainList }">
			<tr>
				<td colspan="2" align="center">등록된 동행 글이 없습니다. 동행을 찾아보세요!<br> <input
					type="button" value="+" id="makeAcc">
				</td>
			</tr>
		</c:if>
		<c:forEach var="dto" items="${mainList}">
			<tr>

				<td>${dto.accdto.country}</td>
				<td><a href="accList.do">${dto.content}</a></td>
			</tr>
		</c:forEach>
	</table>
<p align="center">${pageStr}</p>
</body>
</html>