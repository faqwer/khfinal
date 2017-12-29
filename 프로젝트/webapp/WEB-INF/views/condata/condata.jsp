<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
 <head>
  <meta charset="UTF-8"/>
  <title>JSON 파싱</title>
 
  <style> </style>
  
  <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
  
  <script>
  
  </script>
</head>
<body>
json
<br>


<c:set var="array" value="${ar}" />
<c:forEach var="ex" items="${array}">
${ex.name} <br>
${ex.rate}
</c:forEach>

<br>

weather
<br>

<img alt="${wt}" src="http://openweathermap.org/img/w/${icon}.png">
${temp }



</body>
</html>