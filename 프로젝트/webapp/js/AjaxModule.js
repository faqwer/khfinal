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

function sendRequest(url, param, callback, method) {

   httpRequest = getXMLHttpRequest();
   console.log(param);
   var httpMethod = method ? method : 'GET';
   console.log(httpMethod);
   if (httpMethod != 'GET' && httpMethod != 'POST') {
      httpMethod = 'GET';
   }

   var httpParam = (param == null || param == '') ? null : param;
   var httpUrl = url;
   if (httpMethod == 'GET' && httpParam != null) {
      httpUrl = httpUrl + '?' + httpParam;
   }
   httpRequest.onreadystatechange = callback;
   httpRequest.open(httpMethod, httpUrl, true);
   httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

   console.log(httpMethod == 'POST' ? httpParam : null);
   httpRequest.send(httpMethod == 'POST' ? httpParam : null);

}