<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

</head>
<body>

<a id="kakao-login-btn"></a>
<a href="http://developers.kakao.com/logout">로그아웃</a>
<script type='text/javascript'>
  //<![CDATA[
    // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('5bf8da268064bb3fa058e938cfa28524');
    // 카카오 로그인 버튼을 생성합니다.
    Kakao.Auth.createLoginButton({
      container: '#kakao-login-btn',
      success: function(authObj) {
    	  // 로그인 성공시 API 호출?
    	Kakao.API.request({
    		url:'/v1/user/me',
    		success: function(res) {
                alert(JSON.stringify(res));
                location.href="result.do?id="+res.kaccount_email
                       	+"&name="+res.properties.nickname
                        +"&PROFILE_IMG="+res.properties.profile_image;
                
    		},
    		fail: function(error) {
    			alert(JSON.stringify(error));
    		}
    	});
      },
      fail: function(err) {
         alert(JSON.stringify(err));
      }
    });
  //]]>
</script>
</body>
</html>