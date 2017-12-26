<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function imageURL(input) {
	   
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
           document.getElementById('preview').src = e.target.result;
        }
       reader.readAsDataURL(input.files[0]);
    }
}
    
</script>
</head>
<body>
	<h3>회원가입</h3>

	<form name="joinfm" action="join.do" method="post" enctype="Multipart/form-data">
		<fieldset id="img">
			<table id="preview_t">
				<tr>
					<td><img id="preview" src="/finaltp/testimg/base.png" width=150px height=150px /></td>
				</tr>
				<tr>
					<td><input type="file" name="file" id="preview_up" onchange="imageURL(this)" /></td>
				</tr>
			</table>
		</fieldset>
		<br> 
		<label>이름</label><input type="text" name="name"> <br>
		<label>아이디</label><input type="text" name="id"> <br>
		<label>비밀번호</label><input type="password" name="pwd"> <br>
		<label>비밀번호 확인</label><input type="password" name="pwd_ck"> <br>
		<input type="submit" value="가입"><input type="reset" value="다시입력">
	</form>
</body>
</html>