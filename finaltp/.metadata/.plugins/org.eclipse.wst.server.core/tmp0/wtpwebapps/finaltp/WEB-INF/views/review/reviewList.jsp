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
	<table align="center">
		<c:if test="${!empty sessionScope.userid}">
			<tr>
				<td colspan="3"><a href="reviewWrite.do">포스트 작성</a></td>
			</tr>
		</c:if>
		<c:if test="${empty mainList}">
			<tr>
				<td>등록된 게시물이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="dto" items="${mainList}">
			<c:url var="reviewUrl" value="readNum.do">
				<c:param name="bbs_idx">${dto.bbs_idx}</c:param>
				<c:param name="category">review</c:param>
			</c:url>
			<tr>
				<td>
					<table border="1" cellspacing="0" height="200" width="700">
						<tr>
							<c:choose>
								<c:when test="${empty dto.reviewdto.coverimg}">
									<td width="200" height="140" align="center"><img src="img/noimage.jpg" width="200" height="140"></td>
								</c:when>
								<c:otherwise>
									<td width="200" height="140" align="center"><img src="img/${dto.reviewdto.coverimg}" width="200" height="140"></td>
								</c:otherwise>
							</c:choose>
							<td align="center"><a href="${reviewUrl}">${dto.subject}</a><br>${dto.writedate}<br>추천수 ${dto.recommendNum}<br>조회수 ${dto.reviewdto.readnum}</td>
						</tr>
					</table>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td align="center">${pageStr}</td>
		</tr>
	</table>
</body>
</html>