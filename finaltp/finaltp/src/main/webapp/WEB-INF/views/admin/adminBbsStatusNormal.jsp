<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<br>
<table border="1" width=480 height=200 id="table">
		<thead align="center">
			<tr>
				<th></th>
				<th>번호</th>
				<th>내용</th>
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
				<tr>
					<td><input type="checkbox" id="statusNormal"
						value="${dto.bbs_idx}"></td>
					<td>${dto.bbs_idx}</td>
					<td>${dto.content}</td>
					<td>${dto.writedate}</td>
					<td>${dto.userid}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div align="center" style="margin-top: 8px;">
		<input type="submit" id="normalDeferChange" value="상태변경">
	</div>
	<div align="center" style="margin-top: 8px;">${pageStr}</div>