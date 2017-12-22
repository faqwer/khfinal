
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="routeList">

<table width="900" height="400" align="center">

<tr>

<c:forEach var="dto" items="${mainList}">
<div id="listForm_${dto.bbs_idx}">
		<td>
			<table border="1" cellspacing="0" height="200" width="300">
				<tr>
					<td height="140" align="center"><a href="routeContent.do?bbs_idx=${dto.bbs_idx}"><img src="img/${dto.routedto.coverimg}" height="140"></a></td>
				</tr>
				<tr>
					<td align="center"><a href="routeContent.do?bbs_idx=${dto.bbs_idx}">${dto.subject}</a><br>${dto.writedate}<br>추천수 / ${dto.routedto.readnum}</td>
				</tr>
			</table>
		</td>
		</div>
</c:forEach>

	</tr>

</table>
${pageStr}
</form>
</body>
</html>