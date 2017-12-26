<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script>
	function noticeWrite() {
		location.href="adminNoticeWrite.do";
	}
</script>
<br>
<table border="1" width=480 height=200 id="table">
		<thead align="center">
			<tr>
				<th></th>
				<th>번호</th>
				<th>제목</th>
				<th>날짜</th>
				<th>작성자</th>
			</tr>
		</thead>
		<tbody align="center">
			<c:if test="${empty mainList}">
				<tr>
					<td colspan="5" align="center">게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach var="dto" items="${mainList}">
				<c:url var="noticeUrl" value="adminNoticeContent.do">
					<c:param name="bbs_idx">${dto.bbs_idx}</c:param>
				</c:url>
				<tr>
					<td><input type="checkbox" id="statusNormal"
						value="${dto.bbs_idx}"></td>
					<td>${dto.bbs_idx}</td>
					<td><a href="${noticeUrl}">${dto.subject}</a></td>
					<td>${dto.writedate}</td>
					<td>${dto.userid}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div align="center" style="margin-top: 8px;">
		<input type="submit" id="normalDeferChange" value="상태변경">
		<input type="submit" id="bbsWrite" value="글쓰기" onclick="noticeWrite()">
	</div>
	<div align="center" style="margin-top: 8px;">${pageStr}</div>