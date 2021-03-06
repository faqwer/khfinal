<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css"
	href="/finaltp/css/layerPopup.css">
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="/finaltp/js/layerPopup.js"></script>
<script type="text/javascript">
window.onload = function() {
	$(document).ready(function(){
		//검은 막 띄우기
		$('.openMask').click(function(e){
			e.preventDefault();
		    var maskdiv = document.createElement("div");
		    maskdiv.setAttribute("id", "mask");
		    
		    var windowdiv = document.createElement("div");
		    windowdiv.setAttribute("class", "window");
		    windowdiv.setAttribute("id", "window");
		    windowdiv.setAttribute("style", "background-color:red;width:200px;height:200px;");
		    
		    var close=document.createElement("input");
		    close.setAttribute("type", "button");
		    close.setAttribute("class", "close");
		    close.setAttribute("value", "닫기 버튼");
		    
		    var subject=document.createElement("input");
		    subject.setAttribute("type", "text");
		    subject.setAttribute("name", "subject");
		    subject.setAttribute("placeholder", "예: @박 @일 @여행등등");
		    
		    var startday=document.createElement("input");
		    startday.setAttribute("type", "text");
		    startday.setAttribute("name", "startday");
		    startday.setAttribute("placeholder", "출발날짜");
		    
		    var thema=document.createElement("input");
		    thema.setAttribute("type", "text");
		    thema.setAttribute("name", "thema");
		    thema.setAttribute("placeholder", "테마");
		    
		    var start=document.createElement("input");
		    start.setAttribute("type", "button");
		    start.setAttribute("onclick", "javascript:window.location.href='planwrite.do'");
		    start.setAttribute("value", "플래너 시작");
		 
		    windowdiv.appendChild(close);
		    windowdiv.appendChild(subject);
		    windowdiv.appendChild(startday);
		    windowdiv.appendChild(thema);
		    windowdiv.appendChild(start);
		 
		    var layerPopupscript=document.createElement("script");
		    layerPopupscript.setAttribute("src","/finaltp/js/layerPopup.js");
		    layerPopupscript.setAttribute("id","script");
		    
		    var element = document.getElementsByTagName("body")[0];
		    element.appendChild(maskdiv);
		    element.appendChild(windowdiv);
		   	element.appendChild(layerPopupscript); 
		    wrapWindowByMask();
		});
	})
}
</script>
</head>
<body>

	<a href="#" class="openMask" style="background-color: red;">플래너</a>
<h3>index</h3>
<c:set var="username" value="${sessionScope.userid}" />
<c:if test="${empty userid }">
		<p><a href="login.do">Login</a> | <a href="idCheck.do">Join</a></p>
</c:if>

<c:if test="${!empty userid }">
		<p>${name}님 접속중입니다. | <a href="logout.do">로그아웃</a></p>
</c:if> 

<a href="kakao.do">카톡 로그인</a><br>
<a href="naver.do">네이버 로그인</a><br>
<a href="sky.do">skyscanner</a>

환율/날씨 스위스 헝가리만 돌아감 나머지 아직 추가 안함
<form action="con.do">
		<select id="contry" name="contry">
			<option value="EUR" selected="selected">유로화</option>
			<option value="NOK">노르웨이</option>
			<option value="CHF/Bern">스위스</option>
			<option value="HUF/Budapest">헝가리</option>
			<option value="DKK">덴마크</option>
		</select> <input type="submit" value="확인">
</form>
	<ul>
		<li><a href="noticeList.do">공지사항</a></li>
		<li><a href="faqList.do">FAQ</a></li>
		<li><a href="accList.do">동행 게시판</a></li>
		<li><a href="reviewList.do">여행기 게시판</a></li>
		<li><a href="#">1:1문의</a></li>
	</ul>
</body>
</html>