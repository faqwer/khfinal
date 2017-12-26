<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function writeCheck() {
		if(document.askWrite.subject.value == '') {
			alert('제목을 입력하세요.');
		} else if(document.askWrite.content.value == '') {
			alert('내용을 입력하세요.');
		} else {
			document.askWrite.submit();
		}
	}
</script>
</head>
<body>
<h2>1:1문의 작성</h2>
<form name="askWrite" method="post" action="askWrite.do">
	<table border="1" cellspacing="0">
		<tr>
			<th>제목</th>
			<td><input type="text" name="subject"></td>
			<td><input type="checkbox" name="secret" value="yes">비밀글</td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="2"><textarea rows="10" cols="45" name="content"></textarea></td>
		</tr>
		<tr>
			<td>
			<input type="button" name="writeSubmit" value="작성" onclick="writeCheck()">
			<input type="button" name="cancelSubmit" value="취소" onclick="javascript:location.href='askList.do'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>