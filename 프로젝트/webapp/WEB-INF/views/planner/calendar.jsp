<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css" >
.cale{
	padding: 0px;width:150px;height:210px;
	margin:0 auto;
	font-size: 9pt;
}
.cale td {
	font-size: 9pt;
	text-align: center;
}

.cale a#yeara {
	cusor: pointer;
	color: #000000;
	text-decoration: none;
	font-size: 9pt;
	line-height: 150%;
}

.cale a#yeara:HOVER,.cale a#yeara:ACTIVE {
	color: #F28011;
	text-decoration: underline;
}

.cale #day:HOVER{
	background: blue;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
function got(num,year,month){
	$.ajax({
		url:'calendar.do',
        dataType:'html',
        type:'GET',
        data:{'year':year,'month':month+num},
        success:function(data){
            $('#calendardiv').html(data);
        }
	});
}
</script>
</head>
<body>
<div class="cale">
	<table width="140" border="0" cellpadding="1" cellspacing="2" style="text-align: center;">
		<tr height="30">
			<td align="center">
			<a id="yeara"onclick="javascript:got(-1,${year },${month })">◀</a> 
			<b><span id="year">${year}</span> 年 <span id="month">${month }</span> 月</b> 
			<a id="yeara"onclick="javascript:got(1,${year },${month })">▶</a></td>
		</tr>
	</table>

	<table width="140" border="0" cellpadding="2" cellspacing="1"
		bgcolor="#cccccc">
		<tr height="10">
			<td align="center" bgcolor="#e6e4e6"><font color="red">일</font></td>
			<td align="center" bgcolor="#e6e4e6">월</td>
			<td align="center" bgcolor="#e6e4e6">화</td>
			<td align="center" bgcolor="#e6e4e6">수</td>
			<td align="center" bgcolor="#e6e4e6">목</td>
			<td align="center" bgcolor="#e6e4e6">금</td>
			<td align="center" bgcolor="#e6e4e6"><font color="red">토</font></td>
		</tr>
		<c:set var="newLine" value="0" />
		<tr height='10'>
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
					<c:set var="fc" value="(${newLine == 0})?'red':(${newLine==6}?'red':'black')"></c:set>
					<c:set var="bg" value="#ffffff"></c:set>
					<td id="day" align='center' bgcolor=" ${bg } ">
						<font color="${fc }">${i }</font>
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
	</div>
</body>
</html>