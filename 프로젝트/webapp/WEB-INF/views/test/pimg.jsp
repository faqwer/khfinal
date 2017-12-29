<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

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
<c:set var="id" value="${sessionScope.userid }" />
<form action="pimg.do" method="post"  enctype="Multipart/form-data">
<input type="text" id="id" name="id" value="${id}">
<fieldset id="img">
			<table id="preview_t">
				<tr>
					<td><img id="preview" src="/finaltp/img/base.png" width=150px height=150px /></td>
				</tr>
				<tr>
					<td><input type="file" name="file" id="preview_up" onchange="imageURL(this)" /></td>
				</tr>
			</table>
		</fieldset>


<input type="submit" value="저장">
</form>

</body>
</html>