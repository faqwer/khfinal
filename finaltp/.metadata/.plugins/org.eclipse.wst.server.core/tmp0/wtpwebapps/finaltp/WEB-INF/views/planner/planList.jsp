<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#coverimgdiv{
	background-size: 150px 200px; 
	height:200px; 
	width:150px;
}
#planContent{
	float: left;
}
</style>
</head>
<body>
<%@ include file="/header.jsp" %>
	<c:forEach var="i" begin="0" end="${fn:length(plist)-1 }" step="1">
	<div id="planContent" onclick="javascript:location.href='planContent.do?planner_idx=${plist[i].planner_idx}'">
		<div id="coverimgdiv" style="background-image: url('${plist[i].coverimg }');">
		</div>
		<div>
			<img id="pfimg" src="${mlist[i].profile_img}" width="30" height="30">
			<span style="width: 110px; height: 30px; margin: 0px auto; text-align: center;">${plist[i].subject}</span>
		</div>
	</div>
	<c:if test="${(i+1)%5 eq 0 }">
	<br style="clear: both;">
	</c:if>
	</c:forEach>
	<p style="clear: both;">${paging }<p>
	
<%@ include file="/footer.jsp" %>
</body>
</html>