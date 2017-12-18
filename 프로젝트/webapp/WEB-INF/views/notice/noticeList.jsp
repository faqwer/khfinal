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
<h2>공지사항</h2>
<table>
	<thead>
	<tr>
		<th>순번</th>
		<th>제목</th>
		<th>작성일자</th>
	</tr>
	</thead>
	<tbody>
		<c:if test="${empty noticeList}">
			<tr>
				<td colspan="3">
					등록된 게시물이 없습니다.
				</td>
			</tr>
		</c:if>
		<c:forEach var="dto" items="${noticeList}">
			<c:url var="noticeUrl" value="noticeContent.do">
				<c:param name="idx">${dto.bbs_idx}</c:param>
			</c:url>
			<tr>
				<td>${dto.bbs_idx}</td>
				<td><a href=${noticeUrl}>${dto.subject}</a></td>
				<td>${dto.writedate}</td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="3" align="center">
				${pageStr}
			</td>
		</tr>
	</tfoot>
</table>
</body>
</html>