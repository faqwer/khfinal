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
<link rel="stylesheet" type="text/css"
   href="/finaltp/css/planstart.css">
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="/finaltp/js/layerPopup.js"></script>
<script src="/finaltp/js/planstart.js"></script>
<script type="text/javascript">
window.onload = function() {
	$(document).ready(function(){
		//검은 막 띄우기
		$('.openMask').click(function(e){
          e.preventDefault();  
          create();
          wrapWindowByMask();
          var date;
          $('#planstartdiv #startday').click(function (e) {    
             e.preventDefault();  
            $('#planstartdiv #calendardiv').show();
            $.ajax({
               url:'calendar.do',
                  dataType:'html',
                  type:'GET',
                  data:null,
                  success:function(data){
                      $('#planstartdiv #calendardiv').html(data);
                  }
            });
         });
		});
         $(document).on("click", ".cale #day", function(e){
             e.preventDefault(); 
             var year=$('.cale #year').text();
             var month=$('.cale #month').text();
             var day=$(this).find('font').text();
             date=new Date(year,month-1,day);
             $('#planstartdiv #startday').val(date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate());
            $('#planstartdiv #calendardiv').hide();
         });
         
         $('#bt1').click(function(e){
             e.preventDefault(); 
             var subject=$('#subject').val();
             if($('#subject').val()==''){
                alert('제목을 입력하세요!');
                return;
             }
             if(date==null){
                alert('날짜를 선택하세요!');
                return;
             }
             var startday=date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
             var thema=$('#thema').val();
             
            window.location.href="planwriteForm.do?startday="+startday+"&subject="+subject+"&thema="+thema;
         });

	})
}
</script>
</head>
<body>

<a href="#" class="openMask" style="background-color: red;">플래너</a>
<h3>index</h3>
<c:set var="userid" value="${sessionScope.userid}" />
<c:if test="${empty userid }">
		<p><a href="login.do">Login</a> | <a href="join.do">Join</a></p>
</c:if>

<c:if test="${!empty userid }">
		<p><a href="mypage.do?id=${userid}">${name}</a>님 접속중입니다. | <a href="logout.do">로그아웃</a></p>
</c:if>
<ul>
	<li><a href="noticeList.do">공지사항</a></li>
	<li><a href="faqList.do">FAQ</a></li>
	<li><a href="accList.do">동행 게시판</a></li>
	<li><a href="planList.do">Plan 게시판</a></li>
	<li><a href="reviewList.do">여행기 게시판</a></li>
	<li><a href="#">1:1문의</a></li>
	<li><a href="routeList.do">경로 추천</a></li>
</ul>
<input type="date">
<table border="1" cellspacing="0" align="center" width="1200px" >
		<tr>
			<td colspan="3" id="bestplan">best plan</td>
		</tr>
		<tr>
			<td>1위</td>
			<td>2위</td>
			<td>3위</td>
		</tr>
		<tr>
			<td colspan="3" id="trip">best 여행기</td>
		</tr>
		<tr>
			<td>1위</td>
			<td>2위</td>
			<td>3위</td>
		</tr>
</table>
</body>
</html>