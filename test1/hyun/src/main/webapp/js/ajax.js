window.onload = function() {
	var httpRequest = null;
	function getXMLHttpRequest() {
		if (window.ActiveXObject) {
			return new ActiveXObject("Msxml2.XMLHTTP");
		} else if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else {
			return null;
		}
	}
	function show(e) { //요청함수
		httpRequest = getXMLHttpRequest();
		httpRequest.onreadystatechange = showResult;
		httpRequest.open("GET", "ajax.do?test=faqwer", true);
		httpRequest.send(null);
	}

	$(document).ready(function() {
		$('#ajax_test').click(show);
	})

	function showResult() { //응답함수 , 콜백함수
		if(httpRequest.readyState==4){
			if(httpRequest.status==200){
				alert('200');
				var data=httpRequest.responseText;
				alert(data);
			}
			
		}
	}
}