<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TP</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/PopupCalendar.js"></script>
<script
	src='//production-assets.codepen.io/assets/editor/live/console_runner-079c09a0e3b9ff743e39ee2d5637b9216b3545af0de366d4b9aad9dc87e26bfd.js'></script>
<script
	src='//production-assets.codepen.io/assets/editor/live/events_runner-73716630c22bbc8cff4bd0f07b135f00a0bdc5d14629260c3ec49e5606f98fdd.js'></script>
<script
	src='//production-assets.codepen.io/assets/editor/live/css_live_reload_init-2c0dc5167d60a5af3ee189d570b1835129687ea2a61bee3513dee3a50c115a77.js'></script>
<meta charset='UTF-8'>
<meta name="robots" content="noindex">
<link rel="shortcut icon" type="image/x-icon"
	href="//production-assets.codepen.io/assets/favicon/favicon-8ea04875e70c4b0bb41da869e81236e54394d63638a1ef12fa558a4a835f1164.ico" />
<link rel="mask-icon" type=""
	href="//production-assets.codepen.io/assets/favicon/logo-pin-f2d2b6d2c61838f7e76325261b7195c27224080bc099486ddd6dccb469b8e8e6.svg"
	color="#111" />
<link rel="canonical"
	href="https://codepen.io/sara_bianchi94/pen/LLOVYy?depth=everything&limit=all&order=popularity&page=77&q=image&show_forks=false" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
i {
	font-size: 12px;
	padding-right: 10px;
	color: #aece61;
}

/* Footer */
.footer {
	position: relative;
	background-color: #092746;
	color: #7d7975;
	padding: 95px 0 50px;
	font-size: 16px;
}

.footer p {
	line-height: 26px;
	color: #fff;
}

.footer h5 {
	font-size: 22px;
	font-weight: 700;
	color: #aece61;
	position: relative;
	padding-bottom: 16px;
}

.footer h5:after {
	content: '';
	display: block;
	margin: 5px 0 0;
	width: 40%;
	height: 1px;
	background-color: #1e1d22;
}

.footer ul {
	list-style: none;
	line-height: 35px;
	margin: 0px;
	color: #fff;
}

.footer a {
	color: #;
}

.footer a:hover {
	color: #aece61;
}

/* Footer Logo */
.foot-logo {
	margin: 0px 0px 18px 0px;
	padding: 0;
}

/*footer bottom */
.footer-bottom {
	padding-top: 10px;
	padding-bottom: 25px;
	border-top: 1px solid #2f2f33;
	background: #1e1d22;
}

.copyright-text p {
	color: #7d7975;
	margin-top: 18px;
	margin-bottom: 0;
	font-size: 15px;
}

.copyright-text a {
	color: #7d7975;
	margin: 0px 10px 0px 10px;
}

.copyright-text a:hover {
	color: #aece61;
}
/* Social Icons */
.social-icons {
	margin: 0;
	padding: 0;
	font-size: 10px;
}

.social {
	margin: 7px 7px 7px 0px;
	color: #fff;
}

#social-fb:hover {
	color: #3B5998;
	transition: all .25s;
}

#social-tw:hover {
	color: #4099FF;
	transition: all .25s;
}

#social-gp:hover {
	color: #d34836;
	transition: all .25s;
}

#social-em:hover {
	color: #f39c12;
	transition: all .25s;
}

.footer-four>img {
	width: 80%;
}

<!--
-->
@import
	url("https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css")
	;

body {
	width: 1200px;
	margin: 0px auto;
}

header {
	background: url(/finaltp/img/메인.jpg) repeat;
	width: 1200px;
	height: 500px;
	background-color: #060;
	color: #fff;
	text-align: center;
	text-shadow: 0 1px 3px rgba(0, 0, 0, .5);
}

.header {
	width: 1200px;
	height: 100%;
	min-height: 100%;
	background: rgba(0, 0, 0, 0.2)
		url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAMAAAACCAYAAACddGYaAAAAD0lEQVQIW2NkQABjRmQOAAM+AGkQsDBSAAAAAElFTkSuQmCC)
		repeat;
	-webkit-box-shadow: inset 0 0 100px rgba(0, 0, 0, .5);
	box-shadow: inset 0 0 100px rgba(0, 0, 0, .5);
}

.navbar-brand {
	margin-left: -50px;
	margin-top: -20px;
	font-size: 24px;
	font-weight: bold;
	color: #fff;
	font-style: italic;
}

.inner {
	padding: 30px;
}

.masthead-nav>li {
	display: inline-block;
}

.masthead-nav>li+li {
	margin-left: 20px;
}

.masthead-nav>li>a {
	padding-right: 0;
	padding-left: 0;
	font-size: 18px;
	font-weight: bold;
	color: #fff;
	/* IE8 proofing */
	color: rgba(255, 255, 255, .95);
	border-bottom: 2px solid transparent;
}

.masthead-nav>li>a:hover, .masthead-nav>li>a:focus {
	background-color: transparent;
	color: #fff;
	border-bottom-color: #fff;
}

.masthead-nav>.active>a, .masthead-nav>.active>a:hover, .masthead-nav>.active>a:focus
	{
	color: #fff;
	border-bottom-color: #fff;
}

@media ( min-width : 768px) {
	.navbar-brand {
		float: left;
	}
	.masthead-nav {
		float: right;
	}
}

@media ( max-width : 768px) {
	.navbar-toggle {
		background: #000 !important;
	}
}

.tabStrip {
	background: #f3eee9;
	border: none;
	padding-left: 0;
	padding-right: 0;
}

.grey {
	background: grey;
}

.blue {
	background: #00BFFF;
}

.orange {
	background: #FF8C00;
}

.green {
	background: #6B8E23;
}

.tabStrip ul li a {
	color: #fff;
}

.tabStrip ul li a:hover {
	border: none;
	background: none;
}

.planner_text {
	position: absolute;
	top: 140px;
	left: 225px;
	width: 700px;
}

header .btn-link {
	border: 1px solid #2F3440;
	margin-top: 20px;
	font-size: 21px;
	letter-spacing: 1px;
	color: #2F3440;
	text-shadow: 0px 0px 1px #222;
	box-shadow: 0px 0px 1px #000;
}

header .btn-link:hover {
	text-decoration: none;
	color: #eee;
	border: 1px solid #eee;
	transition: all 0.7s;
	text-shadow: 0px 0px 3px #000;
}

h1 {
	font-family: 'Arbutus', cursive;
	text-align: center;
	font-size: 61px;
	color: #F8CA4D;
	text-shadow: 0px 0px 4px #222;
}

p {
	text-align: center;
	font-size: 21px;
}

table {
	width: 1200px;
	height: 200px;
}

#txtDate {
	border: 0px;
	width: 150px;
}

#ta {
	width: 100%;
	height: 50px;
	text-align: center;
}

#td1 {
	color: white;
	width: 700px;
	font-size: 17px;
}

#td2 {
	color: white;
	width: 620px;
	height: 50px;
	font-size: 17px;
	text-align: center;
}

#se {
	width: 75px;
	height: 30px;
	margin-left: 80px;
	margin-bottom: 15px;
}

#se2 {
	width: 75px;
	height: 30px;
}

#se3 {
	width: 75px;
	height: 30px;
}

#sele {
	width: 100px;
	height: 30px;
	margin-left: 25px;
}

#search {
	margin-left: 440px;
	margin-top: 10px;
	padding: 7px 19px 7px 19px;
	border: 1px solid #dedede;
	background: #444;
	align: center;
	color: #202020;
	letter-spacing: 1px;
	color: white;
	border-color: white;
	border-radius: 5px;
}

#search:hover {
	background: white;
	color: black;
	border-color: #BDBDBD;
}

.back {
	height: 100%;
	weight: 100%;
	background-image: url("/finaltp/img/div.png");
}

/*  background-color: #f2f2f2;*/
}
.searchsear {
	width: 30px;
	height: 30px;
	background-image: url("/finaltp/img/search.PNG");
}

<!--
-->
.fade-carousel .carousel-inner .item.active .hero {
	opacity: 1;
	Similar snippets: See More 2.0K 0 Slider with white overlay 6.4K 4
		Bootstrap Navbar and Slider Overlay Text 5.9K 8 Background Image
		Overlay 5.3K 3 Background Overlay with text Comments:

 
 
 Follow@bootsnipp Tweet Bootsnipp.com © 2015 Dan's Tools| Site Privacy
		policy| Advertise| Featured snippets are MIT license. .wrap{text-align:
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		 center;
}

.inner {
	width: 150px;
	height: 150px;
	border: 1px solid #2e2e2e;
	display: inline-block;
}
<!--
-->
</style>

</head>
<body>
	<header>
		<div class="container header inner">
			<nav class="navbar">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#myNavbar">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand" href="#"><img
							src="/finaltp/img/mainLogo.png"></a>
						<div class="planner_text">
							<h1>Trevel Planner</h1>
							<p>travel is the only thing you</p>
							<a class="btn btn-link" href="#">PLANNER START </a>
						</div>
					</div>
					<div class="collapse navbar-collapse" id="myNavbar">
						<ul class="nav navbar-nav navbar-right masthead-nav">
							<li><a href="routeList.do"> Route</a></li>
							<li><a href="planList.do"> Planner</a></li>
							<li><a href="accList.do"> Accompany</a></li>
							<li><a href="reviewList.do"> Travel</a></li>
							<li><a href="#"> Customer</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-user"></span>
									Sign Up</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
									Login</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
	</header>

	<!--  -->
	<div class="back"
		style="border: 1px solid black; width: 1200px; height: 370px; float: left;">
		<table
			style="width: 620px; float: left; margin-left: 73px; margin-top: 50px; background-color: white;">
			<tr>

				<td colspan="2"
					style="background: #00BFFF; width: 100%; height: 50px; text-align: center;"
					id="td1"><img src="/finaltp/img/airplane.png"> 항공권</td>
			</tr>
			<tr>
				<td colspan="2" style="height: 70px; width: 320px;"><b
					style="margin-left: 80px;">출발지</b> <b style="margin-left: 215px;">도착지</b>
					<br> <input type="text"
					style="width: 200px; height: 30px; margin-left: 80px; margin-right: 10px;">
					<img src="/finaltp/img/arrow.PNG" style="margin-bottom: 5px;">


					<input type="text"
					style="width: 200px; height: 30px; margin-left: 10px;"></td>
			</tr>
			<tr>
				<td colspan="2" style="height: 70px;"><b
					style="margin-left: 80px;">출국일</b> <b style="margin-left: 215px;">귀국일</b>
					<br>
					<div
						style="border: 1px solid black; width: 200px; float: left; border-color: #BDBDBD; margin-left: 80px;">
						<input id="txtDate" type="text" class="inputWithImge" /> <img
							src="/finaltp/img/calendar.png" alt="Calendar!"
							onclick="SetDate();" />
					</div> <img src="/finaltp/img/arrow.PNG"
					style="margin-bottom: 5px; margin-left: 13px;">

					<div
						style="border: 1px solid black; width: 200px; heigth: 20px; border-color: #BDBDBD; margin-right: 77px; float: right;">
						<input id="txtDate" type="text" class="inputWithImge" /> <img
							src="/finaltp/img/calendar.png" alt="Calendar!"
							onclick="SetDate();" />
					</div></td>
			</tr>
			<tr>
				<td style="height: 70px;"><b
					style="margin-left: 77px; margin-bottom: 10px;"> <img
						src="/finaltp/img/person.PNG" style="margin-bottom: 5px;">인원
				</b><b style="margin-left: 190px;"><img src="/finaltp/img/seat.PNG"
						style="margin-bottom: 5px;"> 좌석</b><br>
				<select id="se">
						<option>성인1명</option>
						<option>성인2명</option>
						<option>성인3명</option>
						<option>성인4명</option>
						<option>성인5명</option>
				</select> <select id="se2">
						<option>아동0명</option>
						<option>아동1명</option>
						<option>아동2명</option>
						<option>아동3명</option>
						<option>아동4명</option>
						<option>아동5명</option>
				</select> <select id="se3">
						<option>유아0명</option>
						<option>유아1명</option>
						<option>유아2명</option>
						<option>유아3명</option>
						<option>유아4명</option>
						<option>유아5명</option>
				</select> <select id="sele">
						<option>이코노미석</option>
						<option>프리미엄 일반석</option>
						<option>비즈니스석</option>
						<option>일등식</option>
				</select><img src="/finaltp/img/search.PNG" style="margin-left: 40px;"
					onclick="javascript:location.href='#'"></td>
			</tr>
			<!-- <tr>
			
				<td colspan="2"><input type="submit" value="검색하기" id="search"></td>
			</tr> -->
		</table>

		<table
			style="width: 400px; height: 270px; background-color: white; float: left; margin-right: 60px; margin-top: 50px; margin-left: 30px;">
			<tr>
				<td colspan="2" style="background: #FF8C00;" id="td2"><img
					src="/finaltp/img/flag.PNG"> 환율</td>
			</tr>
			<tr>
				<td>aaa</td>
			</tr>
		</table>
	</div>

	<!-- <div class="container-fluid tabStrip">
  <ul class="nav nav-tabs text-center">
    <li class="col-sm-3 grey"><a href="#"><i class="fa fa-truck fa-2x col-sm-12"></i><span class="col-sm-12">Packers and Movers</span></a></li>
    <li class="col-sm-3 orange"><a href="#"><i class="fa fa-ship fa-2x col-sm-12"></i><span class="col-sm-12">Cargo</span></a></li>
    <li class="col-sm-3 blue"><a href="#"><i class="fa fa-building fa-2x col-sm-12"></i><span class="col-sm-12">Warehouse</span></a></li>
    <li class="col-sm-3 green"><a href="#"><i class="fa fa-building fa-2x col-sm-12"></i><span class="col-sm-12">Transport</span></a></li>
  </ul>
</div> -->


	Best Plan
	<div class="container"
		style="text-align: center; padding-top: 10px; margin: 0auto; width: 1200px; height: 330px; border: 1px solid;">
		<div
			style="border: 1px solid; display: inline-block; width: 350px; height: 280px; margin: 15px;">
		</div>
		<div
			style="border: 1px solid; display: inline-block; width: 350px; height: 280px; margin: 15px;">
		</div>
		<div
			style="border: 1px solid; display: inline-block; width: 350px; height: 280px; margin: 15px;">
		</div>
	</div>
	여행기
	<div class="container"
		style="text-align: center; padding-top: 10px; margin: 0auto; width: 1200px; height: 330px; border: 1px solid;">
		<div
			style="border: 1px solid; display: inline-block; width: 350px; height: 280px; margin: 15px;">
		</div>
		<div
			style="border: 1px solid; display: inline-block; width: 350px; height: 280px; margin: 15px;">
		</div>
		<div
			style="border: 1px solid; display: inline-block; width: 350px; height: 280px; margin: 15px;">
		</div>
	</div>



	<!-- footer -->


	<div class="footer-section">
		<div class="footer">
			<div class="container">
				<div class="col-md-4 footer-one">
					<div class="foot-logo">
						<img
							src="http://smarthead.ancorathemes.com/wp-content/uploads/2016/12/footer_logo.png">
					</div>

					<p>Providing Life Changing Experiences Through Education. Class
						That Fit Your Busy Life. Closer to Home</p>
					<div class="social-icons">
						<a href="https://www.facebook.com/"><i id="social-fb"
							class="fa fa-facebook-square fa-3x social"></i></a> <a
							href="https://twitter.com/"><i id="social-tw"
							class="fa fa-twitter-square fa-3x social"></i></a> <a
							href="https://plus.google.com/"><i id="social-gp"
							class="fa fa-google-plus-square fa-3x social"></i></a> <a
							href="mailto:bootsnipp@gmail.com"><i id="social-em"
							class="fa fa-envelope-square fa-3x social"></i></a>
					</div>
				</div>
				<div class="col-md-2 footer-two">
					<h5>Quick Links</h5>
					<ul>
						<li><a href="#"> About Us</a></li>
						<li><a href="#"> Our News</a></li>
						<li><a href="#"> Our Services</a></li>
						<li><a href="#"> Contact Us</a></li>
					</ul>

				</div>
				<div class="col-md-2 footer-three">
					<h5>Services</h5>
					<ul>
						<li><a href="#"> About Us</a></li>
						<li><a href="#"> Our News</a></li>
						<li><a href="#"> Our Services</a></li>
						<li><a href="#"> Contact Us</a></li>
					</ul>

				</div>
				<div class="col-md-4 footer-four">
					<h5>Contact Us</h5>
					<img
						src="http://iacademy.mikado-themes.com/wp-content/uploads/2017/05/footer-img-1.png">

				</div>






				<div class="clearfix"></div>
			</div>
		</div>

	</div>
	<div class="footer-bottom">
		<div class="container">
			<div class="row">
				<div class="col-sm-6 ">
					<div class="copyright-text">
						<p>CopyRight © 2017 Digital All Rights Reserved</p>
					</div>
				</div>
				<!-- End Col -->
				<div class="col-sm-6  ">
					<div class="copyright-text pull-right">
						<p>
							<a href="#">Home</a> | <a href="#">Privacy</a> |<a href="#">Terms
								& Conditions</a> | <a href="#">Refund Policy</a>
						</p>
					</div>

				</div>
				<!-- End Col -->
			</div>
		</div>
	</div>
</body>
</html>