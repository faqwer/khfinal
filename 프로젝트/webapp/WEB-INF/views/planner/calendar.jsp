<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
* {
	padding: 0px;
	margin: 0px;
} /* 브라우저별 기본 여백 차이가 있기에 작성한다. */
body {
	font-size: 9pt;
}

td {
	font-size: 9pt;
}

a {
	cusor: pointer;
	color: #000000;
	text-decoration: none;
	font-size: 9pt;
	line-height: 150%;
}

a:HOVER, a:ACTIVE {
	font-size: 9pt;
	color: #F28011;
	text-decoration: underline;
}
</style>
</head>
<body>
	<table width="210" border="0" cellpadding="1" cellspacing="2">
		<tr height="30">
			<td align="center"><a
				href="calendar.do?year=${year}&month=${month-1 }">◀</a> <b>${year}
					年 ${month } 月</b> <a href="calendar.do?year=${year}&month=${month+1 }">▶</a></td>
		</tr>
	</table>

	<table width="210" border="0" cellpadding="2" cellspacing="1"
		bgcolor="#cccccc">
		<tr height="25">
			<td align="center" bgcolor="#e6e4e6"><font color="red">일</font></td>
			<td align="center" bgcolor="#e6e4e6">월</td>
			<td align="center" bgcolor="#e6e4e6">화</td>
			<td align="center" bgcolor="#e6e4e6">수</td>
			<td align="center" bgcolor="#e6e4e6">목</td>
			<td align="center" bgcolor="#e6e4e6">금</td>
			<td align="center" bgcolor="#e6e4e6"><font color="blue">토</font></td>
		</tr>
		<c:set var="newLine" value="0" />
		<tr height='25'>
			<c:forEach var="j" begin="1" end="7" step="1">
				<c:if test="${j<w }">
					<td bgcolor='#ffffff'>&nbsp;</td>
					<c:set var="newLine" value="${newLine+1 }"></c:set>
				</c:if>
			</c:forEach>
			<c:set var="i" value="1"></c:set>
			<c:set var="fc"></c:set>
			<c:set var="bg"></c:set>
			<c:forEach var="j" begin="1" end="35" step="1">
				<c:if test="${j <= end }">
					<c:set var="fc" value="(${newLine == 0})?'red':(${newLine==6}?'blue':'#000000')"></c:set>
					<c:set var="bg" value="#ffffff"></c:set>
					<td align='center' bgcolor=" ${bg } ">
						<font color="${fc }">
							${i }
						</font>
					</td>
					<c:set var="newLine" value="${newLine+1 }"></c:set>
					<c:if test="${(newLine eq 7) &&(i ne end) }">
						</tr>
						<tr height='25'>
						<c:set var="newLine" value="0"></c:set>
					</c:if>
					<c:set var="i" value="${i+1 }"></c:set>
				</c:if>
			</c:forEach>
			<c:forEach var="j" begin="1" end="7" step="1">
				<c:if test="${newLine>0 && newLine<7 }">
					<td bgcolor='ffffff'>&nbsp;</td>
					<c:set var="newLine" value="${newLine+1 }"></c:set>
				</c:if>
			</c:forEach>
		</tr>
	</table>
</body>
</html>