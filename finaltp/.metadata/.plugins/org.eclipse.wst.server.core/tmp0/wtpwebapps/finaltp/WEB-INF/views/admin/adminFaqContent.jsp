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
	<h2>FAQ 본문</h2>
	<table>
			<tr>
				<th>질문</th>
				<td>${faqList.question}</td>
			</tr>
			<tr>
				<th>답변</th>
				<td>${faqList.answer}</td>
			</tr>
			<tr>
				<td align="center">
					<a href="adminFaqRevise.do?faq_idx=${faqList.faq_idx}">수정</a>
					<a href="adminBbs.do?sel=faq&status=normal&cp=1">목록으로</a>
				</td>
			</tr>
	</table>
</body>
</html>