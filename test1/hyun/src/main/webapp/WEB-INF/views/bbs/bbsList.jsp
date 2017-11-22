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
	<c:set var="list" value="${list }" />
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>날짜</th>
			</tr>
		</thead>
		<tfoot>
			<td colspan="4" align="center">${paging }</td>
			<td><input type="button" value="글쓰기"
				onclick="javascript:window.location.href='bbsWrite.do'"></td>
		</tfoot>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="5" align="center">등록된 게시글이 없음</td>
				</tr>
			</c:if>
			<c:forEach var="arr" items="${list }">
				<tr>
					<td>${arr.idx }</td>
					<td>${arr.subject }</td>
					<td>${arr.writer }</td>
					<td>${arr.readnum }</td>
					<td>${arr.writedate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
