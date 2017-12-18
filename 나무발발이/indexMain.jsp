<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="/finaltp/css/layerPopup.css">
	<link rel="stylesheet" type="text/css"
	href="/finaltp/css/planstart.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="/finaltp/js/layerPopup.js"></script>
<script src="/finaltp/js/planstart.js"></script>
<script>
window.onload=function(){  

	$(document).ready(function() {

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
			    var startday_year=date.getFullYear();
			    var startday_month=date.getMonth()+1;
			    var startday_day=date.getDate();
			    var thema=$('#thema').val();
			    
				window.location.href="planwriteForm.do?startday_year="+startday_year+"&startday_month="+startday_month+"&startday_day="+startday_day+"&subject="+subject+"&thema="+thema;
			});
		});
	});
}
</script>
</head>
<body>
	<a href="#" class="openMask" style="background-color: red;">플래너</a>
	
</body>
</html>