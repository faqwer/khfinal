<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div style="height: 175px; width: 600px; overflow: auto;">
	<table>
		<tbody style="height: 180px;">
			<c:forEach var="dto" items="${list }">
			<tr style="height: 20px;">
				<td style="width: 100px;">${dto.user_id }</td>
				<td style="width: 390px;">${dto.content }</td>
				<td style="width: 100px;">${dto.writedate }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div style="height: 20px; width: 600px;">
	<input id="replyContent" style="width: 490px;" type="text">
	<input id="addreplybt" style="width: 100px;" type="button" value="입력" onclick="addpreply()">
</div>
