
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="/finaltp/js/AjaxModule.js"></script>

</head>
<body>
	<div id="mypageContent">
		<h2>myWriting</h2>

		<table>
			<tr>
				<td><a href="#" id="myWriting" onclick="javascript:show(1)">여행기</a></td>
				<td><a href="#" id="myAccWriting" onclick="show2(1)">동행구하기</a></td>
				<td></td>
			</tr>
			<tr>
				<c:if test="${empty mainList }">
					<table border="1" cellspacing="0" height="200" width="300">
						<tr>
							<td align="center">새로운 여행기를 작성해보세요!<br> <input
								type="button" value="+" id="makeReview">
							</td>
						</tr>
					</table>
				</c:if>
				<c:forEach var="dto" items="${mainList }">
					<td>
						<table border="1" cellspacing="0" height="200" width="300">
							<tr>
								<td height="140" align="center"><a href="reviewContent.do?bbs_idx=${dto.bbs_idx}"><img
										src="img/testimg.jpg" height="140"></a></td>
							</tr>
							<tr>
								<td align="center"><a href="reviewContent.do?bbs_idx=${dto.bbs_idx}">${dto.subject}</a><br>${dto.writedate}</td>
							</tr>
						</table>
					</td>
				</c:forEach>

			</tr>
		</table>
		<p align="center">${pageStr}</p>
	</div>
</body>
</html>