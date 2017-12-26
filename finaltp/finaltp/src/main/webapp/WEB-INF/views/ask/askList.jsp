<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function askWrite() {
		if(${sessionScope.userid == null}) {
			alert('로그인 후 이용가능 합니다.');
		} else {
			location.href="askWrite.do";
		}
	}
	
	function userCheck(writerid, gopage) {
		if(${sessionScope.userid == 'admin'}) {
			location.href = gopage;
		} else {
			if('${sessionScope.userid}' == writerid) {
				location.href = gopage;
			} else {
				alert('비밀글 입니다.');
			}
		}
			
		
	}
</script>
</head>
<body>
<h2>1:1문의</h2>
<table border="1" cellspacing="0">
	<tr>
		<th>순번</th>	
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
	</tr>
	<c:if test="${empty mainList}">
		<tr>
			<td colspan="4" align="center">등록된 게시물이 없습니다.</td>
		</tr>
	</c:if>
	<c:forEach var="dto" items="${mainList}">
		<c:url var="askUrl" value="askContent.do">
			<c:param name="bbs_idx">${dto.bbs_idx}</c:param>
		</c:url>
		<tr>
			<td>${dto.bbs_idx}</td>
			<c:choose>
				<c:when test="${dto.secret == 'yes'}">
					<td>
					<c:forEach begin="0" end="${dto.lev}" step="1">
						&nbsp;&nbsp;
					</c:forEach>
					<a href="javascript:userCheck('${dto.userid}', '${askUrl}');">비밀글 입니다.</a></td>
				</c:when>
				<c:otherwise>
					<td>
					<c:forEach begin="0" end="${dto.lev}" step="1">
						&nbsp;&nbsp;
					</c:forEach>
					<a href="${askUrl}">${dto.subject}</a></td>
				</c:otherwise>
			</c:choose>
			<td>${dto.userid}</td>
			<td>${dto.writedate}</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="4" align="center">${pageStr}</td>
	</tr>
</table>
<a href="javascript:askWrite()">글쓰기</a>
</body>
</html>