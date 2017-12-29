<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="../kakao.do" method="POST">
<input type="text" name="id" value="<%= request.getParameter("id") %>">
<input type="text" name="name" value="<%= request.getParameter("name") %>">
<input type="text" name="pimg" value="<%= request.getParameter("pimg") %>">
<img alt="" src="<%= request.getParameter("pimg") %>">
<input type="submit" value="다음">
</form>
<form>

</form>

</body>
</html>