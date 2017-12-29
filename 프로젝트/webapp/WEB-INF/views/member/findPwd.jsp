<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="findfm" action="findPwd.do" method="post">
아이디 : <input type="email" name="email" placeholder="email(ex, id@gmail.com)" required> <br>
이름 : <input type="text" name="name" required> <br>
<input type="submit" value="비밀번호 찾기">
</form>
비밀번호 찾기 -(페이지 이동)-> 임시 비밀번호를 (아이디)로 발송하였습니다. 컨트롤러에서 이메일 발송 및 임시 비밀번호(랜덤값) 디비에 저장. > 확인버튼 클릭시 팦업창 종료.
</body>
</html>