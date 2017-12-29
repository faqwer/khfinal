
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="mypageContent">
<h2>Follower Test</h2>
<table border="1" cellspacing="0">
<c:if test="${empty mlist }">
	<tr>
		<td align="center"">
			나를 팔로우 하는 사람이 없어요!
		</td>
	</tr>
</c:if>
	<c:forEach var="dto" items="${mlist }">
	<tr>
		<td align="center">
		<a href="mypage.do?member_idx=${useridx}">
		<img src="${dto.profile_img }"></a>
		<br><a href="mypage.do?member_idx=${useridx}">
		${dto.id }</a></td>
	</tr>
	</c:forEach>
</table>
<p align="center">${pageStr}</p>
</div>
</body>
</html>