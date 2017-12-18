<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<br>
<table border="1" width=800 height=200 id="table">
		<thead align="center">
			<tr>
				<th></th>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>사유</th>
				<th>신고자</th>
				<th>처리여부</th>
			</tr>
		</thead>
		<tbody align="center">
			<c:if test="${empty reportList}">
				<tr>
					<td colspan="7" align="center">게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach var="dto" items="${reportList}">
			<c:url var="reportUrl" value="adminReportProcessForm.do">
				<c:param name="bbs_idx">${dto.bbs_idx}</c:param>
				<c:param name="writer_idx">${dto.writer_idx}</c:param>
			</c:url>
				<c:if test="${dto.userid != 'admin'}">
				<tr>
					<td><input type="checkbox" id="statusDefer" value="${dto.bbs_idx}"></td>
					<td>${dto.bbs_idx}</td>
					<td>${dto.userid}</td>
					<td>${dto.writer_subject}</td>
					<td>${dto.reason}</td>
					<td>${dto.reportid}</td>
					<c:choose>
						<c:when test="${dto.status == 'no'}">
							<td><a href="${reportUrl}">미처리</a></td>
						</c:when>
						<c:otherwise>
							<td>처리완료</td>
						</c:otherwise>
					</c:choose>
				</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	<div align="center" style="margin-top: 8px;">
		<input type="submit" id="bbsDelete" value="삭제">
	</div>
	<div align="center" style="margin-top: 8px;">${pageStr}</div>