<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<br>
	<form>
		<select name="sel" id="selNormalMember">
			<option value="">선택</option>
			<option value="ID">ID</option>
			<option value="이름">이름</option>
		</select>
		<input type="text" id="textMember"
			style="width: 70px; height: 19px; margin-left: 85px; margin-top: 10px;">
		<input type="button" name="search" value="검색" id="idSearchNormalMember"
			style="width: 40px; height: 24px;">
	</form>
	<table border="1" width=480 height=200 id="table">
		<thead align="center">
			<tr>
				<th></th>
				<th>아이디</th>
				<th>이름</th>
				<th>날짜</th>
				<th>회원등급</th>
			</tr>
		</thead>
		<tbody align="center">
			<c:forEach var="dto" items="${memberList}">
				<c:if test="${empty memberList}">
					<tr>
						<td colspan="4" align="center">조건에 맞는 회원이 없습니다.</td>
					</tr>
				</c:if>
				<tr>
					<td><input type="checkbox" id="normalMemberCheck" value="${dto.id}"></td>
					<td>${dto.id}</td>
					<td>${dto.name}</td>
					<td>${dto.joindate}</td>
					<td>${dto.lev}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div align="center" style="margin-top: 8px;">
		<input type="submit" id="normalBlackMove" value="블랙리스트">
		<input type="submit" id="normalMemberOut" value="탈퇴">
	</div>
	<div align="center" style="margin-top: 8px;">${pageStr}</div>