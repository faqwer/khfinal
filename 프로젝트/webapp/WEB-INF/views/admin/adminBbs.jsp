<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
	width: 1200px;
	margin: 0px auto;
}

* {
	margin: 0;
	padding: 0;
}

.bbsContainer {
	width: 800px;
	height : 40px;
	margin: 0px auto;
	margin-bottom: 40px;
}
.statusContainer {
	width: 800px;
	height : 40px;
	margin: 0px auto;
}
.mainForm {
	margin-top: 10px;
	width: 800px;
	margin: 0px auto;
}
ul.status li {
	list-style-type: none;
	width: 100px;
	height: 40px;
	float: left;
}

ul.status li a {
	outline: none;
	background:
		url("http://cfile25.uf.tistory.com/image/17710F454FEE42C1326F65");
	display: block;
	color: blue;
	line-height: 40px;
	text-align: center;
}

ul.bbsList {
	padding: 0;
}

ul.bbsList li {
	list-style-type: none;
	width: 100px;
	height: 40px;
	float: left;
}

ul.bbsList li a {
	outline: none;
	background:
		url("http://cfile25.uf.tistory.com/image/17710F454FEE42C1326F65");
	display: block;
	color: blue;
	line-height: 40px;
	text-align: center;
}

ul.bbsList li a.selected {
	background:
		url("http://cfile7.uf.tistory.com/image/18710F454FEE42C133F20E");
	text-decoration: none;
	color: #333;
	cursor: default;
}

ul.status li a.selected {
	background:
		url("http://cfile7.uf.tistory.com/image/18710F454FEE42C133F20E");
	text-decoration: none;
	color: #333;
	cursor: default;
}

ul.panel {
	clear: both;
	border: 1px solid #9FB7D4;
	border-top: none;
	padding: 0;
}

ul.panel li {
	list-style-type: none;
	padding: 10px;
	text-indent: 1em;
	color: #333;
}

h2 {
	text-align: center;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		var sel = "";
		var status = "";
		var cp = "";
		if(window.location.search == '') {
			sel = "planner";
			status = "normal";
			cp = 1;
		} else {
			var parameter = window.location.search;
			var firstSp = parameter.split("?");
			var secondSp = firstSp[1].split("&");
			var thirdSp = secondSp[0].split("=");
			var fourthSp = secondSp[1].split("=");
			var fifthSp = secondSp[2].split("=");
			sel = thirdSp[1];
			status = fourthSp[1];
			cp = fifthSp[1];
		}
		if(sel == 'planner') {
			$('.planner').addClass("selected");
			if(status == 'normal') {
				$('.normal').addClass("selected");
			} else {
				$('.defer').addClass("selected");
			}
		} else if(sel == 'acc') {
			$('.acc').addClass("selected");
			if(status == 'normal') {
				$('.normal').addClass("selected");
			} else {
				$('.defer').addClass("selected");
			}
		} else if(sel == 'review') {
			$('.review').addClass("selected");
			if(status == 'normal') {
				$('.normal').addClass("selected");
			} else {
				$('.defer').addClass("selected");
			}
		} else if(sel == 'route') {
			$('.route').addClass("selected");
			if(status == 'normal') {
				$('.normal').addClass("selected");
			} else {
				$('.defer').addClass("selected");
			}
		} else if(sel == 'ask') {
			$('.ask').addClass("selected");
			if(status == 'normal') {
				$('.normal').addClass("selected");
			} else {
				$('.defer').addClass("selected");
			}
		} else if(sel == 'faq') {
			$('.faq').addClass("selected");
			if(status == 'normal') {
				$('.normal').addClass("selected");
			} else {
				$('.defer').addClass("selected");
			}
		} else if(sel == 'notice') {
			$('.notice').addClass("selected");
			if(status == 'normal') {
				$('.normal').addClass("selected");
			} else {
				$('.defer').addClass("selected");
			}
		} else {
			$('.report').addClass("selected");
			if(status == 'normal') {
				$('.normal').addClass("selected");
			} else {
				$('.defer').addClass("selected");
			}
		}
		listBbs(sel, status, cp);
	})
	
	$(function() {
		$('.planner').click(function() {
			$('.bbsContainer li a').removeClass("selected");
			$(this).addClass("selected");
			$('.statusContainer li a').removeClass("selected");
			$('.normal').addClass("selected");
			
		})
		
		$('.acc').click(function() {
			$('.bbsContainer li a').removeClass("selected");
			$(this).addClass("selected");
			$('.statusContainer li a').removeClass("selected");
			$('.normal').addClass("selected");
			listBbs('acc', 'normal', 1);
			
		})
		
		$('.review').click(function() {
			$('.bbsContainer li a').removeClass("selected");
			$(this).addClass("selected");
			$('.statusContainer li a').removeClass("selected");
			$('.normal').addClass("selected");
			listBbs('review', 'normal', 1);
		})
		
		$('.route').click(function() {
			$('.bbsContainer li a').removeClass("selected");
			$(this).addClass("selected");
			$('.statusContainer li a').removeClass("selected");
			$('.normal').addClass("selected");
			listBbs('route', 'normal', 1);
		})
		
		$('.ask').click(function() {
			$('.bbsContainer li a').removeClass("selected");
			$(this).addClass("selected");
			$('.statusContainer li a').removeClass("selected");
			$('.normal').addClass("selected");
			listBbs('ask', 'normal', 1);
		})
		
		$('.report').click(function() {
			$('.bbsContainer li a').removeClass("selected");
			$(this).addClass("selected");
			$('.statusContainer li a').removeClass("selected");
			$('.normal').addClass("selected");
			listBbs('report', 'normal', 1);
		})
		
		$('.normal').click(function() {
			$('.defer').removeClass("selected");
			$('.normal').addClass("selected");
			var test = $('.bbsContainer li a[class*=selected]').attr('class').split(" ");
			listBbs(test[0], 'normal', 1);
			
		})
		
		$('.defer').click(function() {
			$('.normal').removeClass("selected");
			$('.defer').addClass("selected");
			var test = $('.bbsContainer li a[class*=selected]').attr('class').split(" ");
			listBbs(test[0], 'defer', 1);
		})
		
		// 동행 일반 -> 보류 전환
		$('.mainForm').on('click', '#normalDeferChange', function() {
			var data = "";
			var param = "";
			var test = $('.bbsContainer li a[class*=selected]').attr('class').split(" ");
			var sel = test[0];
			$("input[id=statusNormal]:checked").each(function() {
				if (data == "") {
					data = $(this).val();
				} else {
					data = data + "," + $(this).val();
				}
			});
			var param = "sel=" + sel + "&status=normal&statusChange=" + data;
			$.ajax({
				type : "get",
				url : "normalDeferMove.do",
				data : param,
				success : function(result) {
					$('.mainForm').html(result);
				}
			})
		})
		
		// 동행 보류 -> 일반 전환
		$('.mainForm').on('click', '#deferNormalChange', function() {
			var data = "";
			var param = "";
			var test = $('.bbsContainer li a[class*=selected]').attr('class').split(" ");
			var sel = test[0];
			$("input[id=statusDefer]:checked").each(function() {
				if (data == "") {
					data = $(this).val();
				} else {
					data = data + "," + $(this).val();
				}
			});
			var param = "sel=" + sel + "&status=defer&statusChange=" + data;
			$.ajax({
				type : "get",
				url : "deferNormalMove.do",
				data : param,
				success : function(result) {
					$('.mainForm').html(result);
				}
			})
		})
		
		// 게시글 삭제
		$('.mainForm').on('click', '#bbsDelete', function() {
			var data = "";
			var param = "";
			var test = $('.bbsContainer li a[class*=selected]').attr('class').split(" ");
			var sel = test[0];
			$("input[id=statusDefer]:checked").each(function() {
				if (data == "") {
					data = $(this).val();
				} else {
					data = data + "," + $(this).val();
				}
			});
			
			if(sel == 'report') {
				var param = "sel=" + sel+ "&status=normal&statusChange=" + data;
				$.ajax({
					type : "get",
					url : "adminBbsDelete.do",
					data : param,
					success : function(result) {
						$('.mainForm').html(result);
					}
				})
			} else {
				var param = "sel=" + sel+ "&status=defer&statusChange=" + data;
				$.ajax({
					type : "get",
					url : "adminBbsDelete.do",
					data : param,
					success : function(result) {
						$('.mainForm').html(result);
					}
				})
			}
			
		})
	})
	
	function listBbs(bbsSel, status, cp) {
		var param = "sel=" + bbsSel + "&status=" + status + "&cp=" + cp;
		
		$.ajax({
			type : "get",
			url : "adminBbsList.do",
			data : param,
			success : function(result) {
				$('.mainForm').html(result);
			}
		})
	}
</script>
</head>
<body>
<h2>게시판 관리</h2>
	<div class="bbsContainer" style="margin-top: 15px">
		<ul class="bbsList">
			<li><a class="planner">Planner</a></li>
			<li><a class="acc">동행구하기</a></li>
			<li><a class="review">후기</a></li>
			<li><a class="route">경로추천</a></li>
			<li><a class="ask">1:1문의</a></li>
			<li><a class="faq">FAQ</a></li>
			<li><a class="notice">공지사항</a></li>
			<li><a class="report">신고</a></li>
		</ul>
	</div>
	<div class="statusContainer">
		<ul class="status">
			<li><a class="normal">일반</a></li>
			<li><a class="defer">보류</a></li>
		</ul>
	</div>
	<div class="mainForm"></div>
</body>
</html>