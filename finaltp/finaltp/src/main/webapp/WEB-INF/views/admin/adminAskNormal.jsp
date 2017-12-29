<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<br>
<table border="1" width=600 height=200 id="table">
		<thead align="center">
			<tr>
				<th></th>
				<th>번호</th>
				<th>제목</th>
				<th>날짜</th>
				<th>작성자</th>
				<th>답변여부</th>
			</tr>
		</thead>
		<tbody align="center">
			<c:if test="${empty askList}">
				<tr>
					<td colspan="6" align="center">게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:forEach var="dto" items="${askList}">
			<c:url var="askUrl" value="askContent.do">
				<c:param name="bbs_idx">${dto.bbs_idx}</c:param>
			</c:url>
				<c:if test="${dto.userid != 'admin'}">
				<tr>
					<td><input type="checkbox" id="statusNormal" value="${dto.bbs_idx}"></td>
					<td>${dto.bbs_idx}</td>
					<td>${dto.subject}</td>
					<td>${dto.writedate}</td>
					<td>${dto.userid}</td>
					<c:choose>
						<c:when test="${dto.ask_status == 'no'}">
							<td><a href="${askUrl}">미답변</a></td>
						</c:when>
						<c:otherwise>
							<td>답변완료</td>
						</c:otherwise>
					</c:choose>
				</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	<div align="center" style="margin-top: 8px;">
		<input type="submit" id="normalDeferChange" value="상태변경">
	</div>
	<div align="center" style="margin-top: 8px;">${pageStr}</div>