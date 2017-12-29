
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="/finaltp/js/AjaxModule.js"></script>

</head>
<body>
<div id="mypageContent">
	<h2>myWriting</h2>

	<table>
		<tr>
			<td><a href="#" id="myWriting" onclick="javascript:show()">여행기</a></td>
			<td><a href="#" id="myAccWriting" onclick="show2()">동행구하기</a></td>
			<td></td>
		</tr>
		<tr>
			
				<c:forEach var="dto" items="${mainList }">
					<td>
						<table border="1" cellspacing="0" height="200" width="300">
							<tr>
								<td height="140" align="center"><a><img
										src="img/testimg.jpg" height="140"></a></td>
							</tr>
							<tr>
								<td align="center"><a href="routeContent.do?idx=">${dto.subject}</a><br>${dto.writedate}</td>
							</tr>
						</table>
					</td>
				</c:forEach>
		
		</tr>
	</table>
	</div>
</body>
</html>