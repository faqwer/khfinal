<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function noticeCheck() {
		if(document.noticeRevise.subject.value == '') {
			alert('제목을 작성해 주세요.');
		} else if(document.noticeRevise.content.value == '') {
			alert('내용을 작성해 주세요.');
		} else {
			document.noticeRevise.submit();
		}
	}
</script>
</head>
<body>
<h2>공지사항 작성</h2>
<form name="noticeRevise" method="post" action="adminNoticeRevise.do">
	<input type="hidden" name="bbs_idx" value="${mainList.bbs_idx}">
	<table>
		<tr>
			<th>제목</th>
			<td><input type="text" name="subject" value="${mainList.subject}"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="5" cols="45" name="content">${mainList.content}</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="button" value="작성" onclick="noticeCheck()">
				<input type="button" value="취소">
			</td>
		</tr>
	</table>
</form>
</body>
</html>