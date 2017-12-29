<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function reWriteCheck() {
		if(document.adminAskReWrite.subject.value == '') {
			alert('제목을 입력하세요.');
		} else if(document.adminAskReWrite.content.value == '') {
			alert('내용을 입력하세요.');
		} else {
			document.adminAskReWrite.submit();
		}
	}
</script>
</head>
<body>
<h2>1:1문의 답변</h2>
<form name="adminAskReWrite" action="adminAskReWrite.do">
	<input type="hidden" name="bbs_idx" value="${requestScope.bbs_idx}">
	<input type="hidden" name="ref" value="${requestScope.ref}">
	<input type="hidden" name="lev" value="${requestScope.lev}">
	<input type="hidden" name="sunbun" value="${requestScope.sunbun}">
	<input type="hidden" name="secret" value="${requestScope.secret}">
	<input type="hidden" name="sel" value="ask">
	<input type="hidden" name="status" value="normal">
	<table>
		<tr>
			<th>제목</th>
			<td><input type="text" name="subject">re)</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="5" cols="45" name="content"></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="button" value="답변" onclick="reWriteCheck()">
			<input type="button" value="목록으로" onclick="javascript:location.href='adminBbs.do?sel=ask&status=normal&cp=1'">
		</tr>
	</table>
</form>
</body>
</html>