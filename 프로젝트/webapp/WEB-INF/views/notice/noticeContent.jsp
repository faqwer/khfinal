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
	<h2>공지사항 본문</h2>
	<table>
			<tr>
				<th>제목</th>
				<td>${bbsContent.subject}</td>
			</tr>
			<tr>
				<th>본문</th>
				<td>${bbsContent.content}</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><a href="noticeList.do">목록으로</a>
				</td>
			</tr>
	</table>
</body>
</html>