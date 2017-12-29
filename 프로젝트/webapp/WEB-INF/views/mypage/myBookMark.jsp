
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="mypageBookmark">
<h2>BookMarkTest</h2>
	<c:if test="${empty list}">
		<h2 align="center">북마크 한 게시글이 없습니다.</h2>
	</c:if>
	<c:forEach var="plan" items="${list}">
		<table border="1" cellspacing="0" height="200" width="300">
			<tr>
				<td height="140" align="center"><img src="img/${plan.coverimg}"
					height="140"></td>
			</tr>
			<tr>
				<td align="center"><a href="planContent.do=?planner_idx=${plan.planner_idx}">${plan.subject}</a><br>${plan.writedate}<br>좋아요
					/${plan.readnum}</td>
			</tr>
			
		</table>
	</c:forEach>
<p align="center">${pageStr}</p>
</div>