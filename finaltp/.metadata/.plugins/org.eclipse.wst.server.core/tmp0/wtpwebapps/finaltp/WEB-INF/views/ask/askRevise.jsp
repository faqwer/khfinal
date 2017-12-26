<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function reviseCheck() {
		if(document.askRevise.subject.value == '') {
			alert('제목을 입력하세요');
		} else if(document.askRevise.content.value == '') {
			alert('내용을 입력하세요');
		} else {
			document.askRevise.submit();
		}
	}
</script>
</head>
<body>
<h2>1:1문의 본문 수정</h2>
<form name="askRevise" method="post" action="askRevise.do">
	<input type="hidden" name="bbs_idx" value="${mainList.bbs_idx}">
	<table border="1" cellspacing="0">
		<tr>
			<th>제목</th>
			<td><input type="text" name="subject" value="${mainList.subject}"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
			<textarea rows="5" cols="45" name="content">${mainList.content}</textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="button" name="reviseBtn" value="수정" onclick="reviseCheck()">
			<input type="button" name="contentBtn" value="취소" onclick="javascript:location.href='askContent.do?bbs_idx=${mainList.bbs_idx}'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>