<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<style type="text/css">
/* @import
	url("https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css")
	; */


header {
	background-color: black;
	/* background: url(/finaltp/img/메인.jpg) repeat; */
	width: 100%;
	height: 110px;
	color: #fff;
	margin: 0px auto;
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


</style>
</head>

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
						
					</div>
					<div class="collapse navbar-collapse" id="myNavbar">
						<ul class="nav navbar-nav navbar-right masthead-nav">
							<li><a href="#"> Route</a></li>
							<li><a href="#"> Planner</a></li>
							<li><a href="#"> Accompany</a></li>
							<li><a href="#"> Travel</a></li>
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

</html>