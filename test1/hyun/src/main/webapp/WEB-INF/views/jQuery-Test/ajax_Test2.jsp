<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/hyun/js/AjaxModule.js"></script>
<script>
	window.onload=function(){
		/* var rootNode=document.documentElement;
		alert(rootNode.nodeName);
		var bodyNode=rootNode.lastChild;
		alert(bodyNode.nodeName);
		var spanNode=bodyNode.firstChild.nextSibling;
		alert(spanNode.nodeName);
		var spanTextNode=spanNode.lastChild;
		alert(spanTextNode.nodeName); 
		var spanNode3=document.getElementById('c');
		alert(spanNode3.nodeName);
		var spanTest3=spanNode3.lastChild;
		alert(spanTest3.nodeName); */
		var fontNode=document.getElementById('fn');
		alert(fontNode.getAttribute('color'));
		fontNode.setAttribute('color','blue');
		alert(fontNode.getAttribute('color'));
		fontNode.setAttribute('color','red');
		alert(fontNode.getAttribute('color'));
		fontNode.removeAttribute('color');
		var newSpan=document.createElement('span');
		var newSpanText=document.createTextNode('newSpan!');
		newSpan.setAttribute('id','b');
		newSpan.appendChild(newSpanText);
		var spanNode=document.getElementById('a');
		spanNode.appendChild(newSpan);
		$(".addb").click(function(){
			alert('asd');
			var asd=document.getElementById('asd');
			alert(asd.nodeName);
			var newp=document.createElement('p');
			var newpText=document.createTextNode('pp');
			newp.appendChild(newpText);
			asd.appendChild(newp);
		});
	}
</script>
</head>
<body>
	<span id="a">spanA</span>
	<p>Ajax<span id="b">spanB</span></p>
	<div><p>p</p><span id="c">spanC</span></div>
	<font id="fn">asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd</font>
	<form>form</form>
	<input type="button" class="addb" value="추가">
	<p id="asd"></p>
</body>
</html>
