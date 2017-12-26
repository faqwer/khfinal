<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	function faqWrite() {
		location.href="adminFaqWrite.do";
	}
</script>
<br>
<table border="1" width=480 height=200 id="table">
		<thead align="center">
			<tr>
				<th></th>
				<th>번호</th>
				<th>제목</th>
			</tr>
		</thead>
		<tbody align="center">
			<c:if test="${empty faqList}">
				<tr>
					<td colspan="5" align="center">게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach var="dto" items="${faqList}">
				<c:url var="faqUrl" value="adminFaqContent.do">
					<c:param name="faq_idx">${dto.faq_idx}</c:param>
				</c:url>
				<tr>
					<td><input type="checkbox" id="statusDefer"
						value="${dto.faq_idx}"></td>
					<td>${dto.faq_idx}</td>
					<td><a href="${faqUrl}">${dto.question}</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div align="center" style="margin-top: 8px;">
		<input type="submit" id="bbsWrite" value="글쓰기" onclick="faqWrite()">
		<input type="submit" id="bbsDelete" value="삭제">
	</div>
	<div align="center" style="margin-top: 8px;">${pageStr}</div>