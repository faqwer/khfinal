<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function faqCheck() {
		if(document.faqRevise.question.value == '') {
			alert('제목을 작성해 주세요.');
		} else if(document.faqRevise.answer.value == '') {
			alert('내용을 작성해 주세요.');
		} else {
			document.faqRevise.submit();
		}
	}
</script>
</head>
<body>
<h2>FAQ 수정</h2>
<form name="faqRevise" method="post" action="adminFaqRevise.do">
	<input type="hidden" name="faq_idx" value="${faqList.faq_idx}">
	<table>
		<tr>
			<th>질문</th>
			<td><input type="text" name="question" value="${faqList.question}"></td>
		</tr>
		<tr>
			<th>답변</th>
			<td><textarea rows="5" cols="45" name="answer">${faqList.answer}</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="작성" onclick="faqCheck()">
				<input type="button" value="취소" onclick="javascript:location.href='adminBbs.do?sel=faq&status=normal&cp=1'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>