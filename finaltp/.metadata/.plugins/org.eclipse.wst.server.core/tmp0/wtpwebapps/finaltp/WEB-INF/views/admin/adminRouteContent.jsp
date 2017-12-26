
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#routeContentTable {
	text-align: center;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<form name="routeContent">
		<table id="routeContentTable" border="1" cellspacing="0"
			width="1000px" align="center">
			<tr>
				<th>테마</th>
				<td>${mainList.routedto.thema}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="5">${mainList.subject}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="5" height="300px">${mainList.content}</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="button" value="수정" onclick="javascript:location.href='adminRouteRevise.do?bbs_idx=${mainList.bbs_idx}'">
				<input type="button" value="목록으로" onclick="javascript:location.href='adminBbs.do?sel=route&status=normal&cp=1'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>