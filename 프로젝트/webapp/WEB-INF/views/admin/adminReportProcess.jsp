<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function check() {
		if(document.adminReportProcess.report.checked) {
			document.adminReportProcess.admin_content.style.visibility = "visible";
		} else {
			document.adminReportProcess.admin_content.style.visibility = "hidden";
		}
	}
</script>
</head>
<body>
<h2>신고 처리</h2>
<form name="adminReportProcess" action="adminReportProcess.do">
	<input type="hidden" name="bbs_idx" value="${requestScope.bbs_idx}">
	<input type="hidden" name="writer_idx" value="${requestScope.writer_idx}">
	<table>
		<tr>
			<td><input type="checkbox" name="report" value="yes" onclick="check()">신고 처리유무</td>
		</tr>
		<tr>
			<td><textarea rows="5" cols="45" name="admin_content" style="visibility: hidden;"></textarea></td>
		</tr>
		<tr>
			<td>
			<input type="submit" value="처리">
			<input type="button" value="목록으로" onclick="javascript:location.href='adminBbs.do?sel=report&status=normal&cp=1'">
			</td>
		</tr>
	</table>
</form>
</body>
</html>