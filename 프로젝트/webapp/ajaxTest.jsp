<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/finaltp/js/AjaxModule.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
window.onload=function(){  
   $(document).ready(function() {
      $('#test2').click(function (e) { 
         $('#jquerytest').show();
         $.ajax({
            url:'Test.jsp',
               dataType:'html',
               type:'GET',
               data:null,
               success:function(data){
                   $('#jquerytest').html(data);
               }
         });
      });
   });
}
function show() {
   sendRequest('Test.jsp', null, showResult, 'GET');
}
function showResult() {
   if (httpRequest.readyState == 4) {
      if (httpRequest.status == 200) {
         var data = httpRequest.responseText;
         var scripttest=document.getElementById('scripttest');
          scripttest.setAttribute("style","display: block;");
         scripttest.innerHTML=data;
      }
   }
}
</script>
</head>
<body>

<input id="test1" type="button" value="scripttest" onclick="show()">
<input id="test2" type="button" value="jquerytest" >
<div id="scripttest" style="display: none;">

</div>
<div id="jquerytest" style="display: none;">

</div>

</body>
</html>
</html>