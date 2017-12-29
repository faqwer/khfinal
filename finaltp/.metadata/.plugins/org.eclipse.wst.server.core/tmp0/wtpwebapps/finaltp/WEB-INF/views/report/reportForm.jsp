<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	function reportCancel() {
		window.self.close();
	}
</script>
</head>
<body>
<h2>신고</h2>
<form name="reportForm" action="report.do">
	<input type="hidden" name="bbs_idx" value="${param.bbs_idx}">
	<input type="hidden" name="writer_idx" value="${param.writer_idx}">
	<input type="hidden" name="userid" value="${param.userid}">
	<table>
		<tr>
			<td>
				사유선택
				<select name="reportReasonSelect">
					<option value="부적절한 홍보 게시글">부적절한 홍보 게시글</option>
					<option value="음란성 또는 청소년에게 부적합한 게시글">음란성 또는 청소년에게 부적합한 게시글</option>
					<option value="기타">기타</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<textarea rows="5" cols="45" name="reportReasonTextArea"></textarea>
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="신고">
				<input type="button" value="취소" onclick="reportCancel()">
			</td>
		</tr>
	</table>
</form>
</body>
</html>