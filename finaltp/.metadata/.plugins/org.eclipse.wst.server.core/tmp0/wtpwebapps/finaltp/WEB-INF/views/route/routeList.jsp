
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
<form name=routeList action="routeList.do">
<c:forEach var="list" items="${list }">
<table width="900" height="400" align="center">
	<tr>
	<c:forEach var="list" items="${list }">
		<td>
			<table border="1" cellspacing="0" height="200" width="300">
				<tr>
					<td height="140" align="center"><img src="${list.coverimg}"></td>
				</tr>
				<tr>
					<td align="center">${list. }<br>시간<br>${list.recommend} / ${list.readnum }</td>
				</tr>
			</table>
		</td>
		</c:forEach>
		<td>
		<table border="1" cellspacing="0" height="200" width="300">
				<tr>
					<td height="140" align="center">사진</td>
				</tr>
				<tr>
					<td align="center">제목<br>시간<br>추천수/조회수</td>
				</tr>
			</table>
		</td>
		<td>
		<table border="1" cellspacing="0" height="200" width="300">
				<tr>
					<td height="140" align="center">사진</td>
				</tr>
				<tr>
					<td align="center">제목<br>시간<br>추천수/조회수</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
		<table border="1" cellspacing="0" height="200" width="300">
				<tr>
					<td height="140" align="center">사진</td>
				</tr>
				<tr>
					<td align="center">제목<br>시간<br>추천수/조회수</td>
				</tr>
			</table>
		</td>
		<td>
		<table border="1" cellspacing="0" height="200" width="300">
				<tr>
					<td height="140" align="center">사진</td>
				</tr>
				<tr>
					<td align="center">제목<br>시간<br>추천수/조회수</td>
				</tr>
			</table>
		</td>
		<td>
		<table border="1" cellspacing="0" height="200" width="300">
				<tr>
					<td height="140" align="center">사진</td>
				</tr>
				<tr>
					<td align="center">제목<br>시간<br>추천수/조회수</td>
				</tr>
			</table>
		</td>		
	</tr>
</table>
</c:forEach>
</form>
</body>
</html>