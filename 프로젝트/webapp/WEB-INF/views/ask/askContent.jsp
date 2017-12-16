<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url var="askReWriteUrl" value="adminAskReWriteForm.do">
	<c:param name="bbs_idx">${mainList.bbs_idx}</c:param>
	<c:param name="ref">${mainList.askdto.ref}</c:param>
	<c:param name="lev">${mainList.askdto.lev}</c:param>
	<c:param name="sunbun">${mainList.askdto.sunbun}</c:param>
	<c:param name="secret">${mainList.askdto.secret}</c:param>
</c:url>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function adminCheck() {
		if(${sessionScope.userid != 'admin'}) {
			alert('작성 권한이 없습니다.');
		} else {
			location.href="${askReWriteUrl}";
		}
	}
</script>
</head>
<body>
<h2>1:1문의 본문</h2>
<table border="1" cellspacing="0">
	<tr>
		<td colspan="2" align="right">
		<c:if test="${mainList.userid == sessionScope.userid}">
		<a href="askRevise.do?bbs_idx=${mainList.bbs_idx}">수정</a> <a href="askDelete.do?bbs_idx=${mainList.bbs_idx}">삭제</a>
		</c:if>
		</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${mainList.subject}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${mainList.content}</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
		<input type="button" name="reWriteBtn" value="답변" onclick="adminCheck()">
		<input type="button" name="listBtn" value="목록" onclick="javascript:location.href='askList.do'">
		</td>
	</tr>
</table>
</body>
</html>