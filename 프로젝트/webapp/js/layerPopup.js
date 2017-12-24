
	function wrapWindowByMask(){
		//화면의 높이와 너비를 구한다.
		var maskHeight = $(document).height();  
		var maskWidth = $(window).width();  

		//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
		$('#mask').css({'width':maskWidth,'height':maskHeight});  

		//애니메이션 효과 - 일단 1초동안 까맣게 됐다가 80% 불투명도로 간다.
		$('#mask').fadeIn(1000);      
		$('#mask').fadeTo("slow",0.8);    
		
		//윈도우 같은 거 띄운다.
		$('.window').show();
	}

	$(document).ready(function(){
		//검은 막 띄우기
		//닫기 버튼을 눌렀을 때
		$('.window .close').click(function (e) {  
		    //링크 기본동작은 작동하지 않도록 한다.
		    e.preventDefault();  
		  /*  $('#mask, .window').hide();*/
			$('#calendardiv').hide();
		    var body=document.getElementsByTagName("body")[0];
		    var planstartdiv=document.getElementById("planstartdiv");
		    var script=document.getElementById("script");
		    var mask=document.getElementById("mask");
		    body.removeChild(mask);
		    body.removeChild(planstartdiv);
		    body.removeChild(script);
		});       

		//검은 막을 눌렀을 때
		$('#mask').click(function () {  
		    /*$(this).hide();  
		    $('.window').hide(); */
			$('#calendardiv').hide();
		    var body=document.getElementsByTagName("body")[0];
		    var planstartdiv=document.getElementById("planstartdiv");
		    var script=document.getElementById("script");
		    var mask=document.getElementById("mask");
		    body.removeChild(mask);
		    body.removeChild(planstartdiv);
		    body.removeChild(script);
		});      
	});