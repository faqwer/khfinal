<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
		fontNode.setAttribute('color','blue');
		fontNode.setAttribute('color','red');
		fontNode.removeAttribute('color');
		var newSpan=document.createElement('span');
		var newSpanText=document.createTextNode('newSpan!');
		newSpan.setAttribute('id','b');
		newSpan.appendChild(newSpanText);
		var spanNode=document.getElementById('a');
		spanNode.appendChild(newSpan);
		var count=0; 
		
		$(".addb").click(function(){
			count++;
			var newItem=document.createElement('div');
			newItem.setAttribute('id','item_'+count);
			newItem.innerHTML="새롭게추가된 아이템["+count+"]<input type='button' class='removeb' value='삭제'>";
			
			var itemList=document.getElementById('itemList');
			itemList.appendChild(newItem);
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
	<p id="itemList"></p>
</body>
</html>
