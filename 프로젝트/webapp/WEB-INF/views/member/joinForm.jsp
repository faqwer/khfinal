<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
var checklist =new Array(3);
for(var i =0;i<checklist.length;i++)
{
   checklist[i] = false;
   //0.비밀번호 1.이름 2.약관동의여부
}

function pwCheck() {

    var p1 = document.forms[0];
    var pw1 = p1.pwd1.value;
    var pw2 = p1.pwd2.value;
    if(pw1.length<4 || pw2.length<4 || pw1==null || pw2 == null)
    {
    	document.getElementById('pwCheck').style.color = "red";
        document.getElementById('pwCheck').innerHTML = "비밀번호가 4자리 이상 되어야합니다.";
        checklist[0] = false;
    }
    else if(pw1.length>=4 && pw2.length>=4)
    {
	    if (pw1 != pw2) 
	    {
	       document.getElementById('pwCheck').style.color = "red";
	       document.getElementById('pwCheck').innerHTML = "비밀번호가 같지 않습니다";
	       checklist[0] = false;
	       
	    } else {
	       document.getElementById('pwCheck');
	       document.getElementById('pwCheck').innerHTML = "";
	       
	       document.getElementById('pwd').value = pw1;

	       checklist[0] = true;
	    }
    }
 }
 
 function nameCheck() {
	 var name = document.getElementById('name').value;
	 
	 if(name!=null||name!=""){
		 checklist[1] = true;
	 } else {
		 checklist[1] = false;
	 }
 }
 
 function ckCheck() {
	 var ck = document.getElementById('ck').value;
	 
	 if(ck=='on'){
		 checklist[2] = true;
	 } else {
		 checklist[2] = false;
	 }
 }
    
function check()
{
   var check = true;
   for(var i = 0;i<checklist.length;i++)
   {
      if(checklist[i]==false)
       {
         window.alert('회원 정보를 확인하세요');
         check = false;
         break;
       }      
   }
   if(check)
      {
      document.joinfm.submit();
      }
}


function no(){      
   location.href='../index.jsp';
}

</script>
</head>
<c:set var="id" value="${id}" />
<body>
	<h3>회원가입</h3>

	<form name="joinfm" action="join.do" method="post">
		<table>
		<tr>
			<th>ID(Email)</th>
			<td>${id}<input type="hidden" name="id" value="${id}"></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="text" name="pwd1" onkeyup="pwCheck()"></td>
		</tr>
		<tr>
			<th>비밀번호 확인</th>
			<td><input type="text" name="pwd2" onkeyup="pwCheck()"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="hidden" name="pwd" id="pwd"><div id="pwCheck"></div></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" id="name" onkeyup = "nameCheck()"></td>
		</tr>
		<tr>
			<th>이용약관</th>
			<td><input type="checkbox" id = "ck" onchange="ckCheck()"> </td>
		</tr>
		<tr>
			<th></th>
			<td><input type="text" value="약관"></td>
		</tr>
		</table>
		<input type="button" onclick="check()" value="가입"><input type="reset" value="다시입력">
	</form>
</body>
</html>