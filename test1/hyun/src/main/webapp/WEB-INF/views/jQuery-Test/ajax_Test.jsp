<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/hyun/js/AjaxModule.js"></script>
<script>
	
	function show() {
		var p1 = document.all.p1;
		//alert(p1.innerHTML);
		p1.innerHTML = '<font color="blue">앗앗앗</font>';
		var text = document.fm.text.value;
		var param = 'text=' + text;
		sendRequest('ajax.do', param, showResult, 'GET');
	}
	function showResult() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var data = httpRequest.responseText;
				var div = document.all.newNotice;
				div.innerHTML = data;
			}
		}
	}
	function showResult2() {
		if (httpRequest.readyState == 4) {
			if (httpRequest.status == 200) {
				var data = httpRequest.responseText;
				alert(data);
				var div = document.all.test;
				div.innerHTML = data;
			}
		}
	}
	function show2() {
		sendRequest('notice.do', null, showResult, 'GET');
	}
	function show3() {
		sendRequest('test.do', null, showResult2, 'GET');
	}
	window.onload = function() {
		timeDisplay();

		$('.testr').click(function(){
			$('#test').html('');
			$.ajax({
				url:'notice.do',
                dataType:'html',
                type:'GET',
                data:null,
                success:function(data){
                    $('#test').html(data);
                }
			});
			
		});
		
		$(document).ready(function() {
			$('#notice').click(show2);
		})
	}
	function timeDisplay() {
		document.all.timer.innerHTML = new Date().getSeconds();
		window.setTimeout('timeDisplay()', 1000);
	}
</script>
</head>
<body>
	<h2>Notice</h2>
	<div id="test"></div>
	<input type="button" class="testr" value="test">
	<div id="newNotice"></div>
	<input type="button" id="notice" value="notice">
	<p>
		<span id="timer"></span> time
	</p>
	<form name="fm">
		<input type="text" name="text">
		<p id="p1">
			<b>ajax</b><br> <font color="red">????????</font>
		</p>
		<input type="button" value="ajax" id="ajax_test" onclick="show()">
		${test }
	</form>
</body>
</html>
