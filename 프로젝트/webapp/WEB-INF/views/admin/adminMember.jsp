<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

.container {
	width: 500px;
	margin: 50px auto;
}

.mainForm {
	width: 500px;
	margin: 50px auto;
}

ul.tab {
	padding: 0;
}

ul.tab li {
	list-style-type: none;
	width: 100px;
	height: 40px;
	float: left;
}

ul.tab li a {
	outline: none;
	background:
		url("http://cfile25.uf.tistory.com/image/17710F454FEE42C1326F65");
	display: block;
	color: blue;
	line-height: 40px;
	text-align: center;
}

ul.tab li a.selected {
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		var type = "";
		var cp = "";
		if(window.location.search == '') {
			type = "all";
			cp = 1;
		} else {
			var parameter = window.location.search;
			var firstSp = parameter.split("?");
			var secondSp = firstSp[1].split("&");
			var thirdSp = secondSp[0].split("=");
			var fourthSp = secondSp[1].split("=");
			type = thirdSp[1];
			cp = fourthSp[1];
		}
		listMember(type, cp);
	})
	
	$(function() {
		$('.memberTab1').click(function() {
				var param = "type=all";
				
				$.ajax({
					type : "get",
					url : "adminAllMemberList.do",
					data : param,
					success : function(result) {
						$('.mainForm').html(result);
					}
				})
	
		})
	
		$('.memberTab2').click(function() {
			var param = "type=normal";
			
			$.ajax({
				type : "get",
				url : "adminNormalMemberList.do",
				data : param,
				success : function(result) {
					$('.mainForm').html(result);
				}
			})
		})
	
		$('.memberTab3').click(function() {
			var param = "type=black";
			$.ajax({
				type : "get",
				url : "adminBlackMemberList.do",
				data : param,
				success : function(result) {
					$('.mainForm').html(result);
				}
			})
				
		})
		
		$('.mainForm').on('click', '#idSearchAllMember', function() {
			var sel = $('#selAllMember').val();
			var text = $('#textAllMember').val();
			
			var param = "sel=" + sel + "&text=" + text + "&type=all";
			
			$.ajax({
				type : "get",
				url : "adminMemberSearch.do",
				data : param,
				success : function(result) {
					console.log(result);
					$('.mainForm').html(result);
				}
			})
		})
		
		$('.mainForm').on('click', '#allMemberOut', function() {
			var data = "";
			var param = "";
			$("input[id=allMemberCheck]:checked").each(function() {
				if (data == "") {
					data = $(this).val();
				} else {
					data = data + "," + $(this).val();
				}
			});
			param = "id=" + data + "&type=all";
			$.ajax({
				type : "get",
				url : "adminMemberOut.do",
				data : param,
				success : function(result) {
					$('.mainForm').html(result);
				}
			})
		})
		
		$('.mainForm').on('click', '#normalBlackMove', function() {
			var data = "";
			var param = "";
			$("input[id=normalMemberCheck]:checked").each(function() {
				if (data == "") {
					data = $(this).val();
				} else {
					data = data + "," + $(this).val();
				}
			});
			param = "id=" + data + "&type=normal";
			$.ajax({
				type : "get",
				url : "normalBlackMove.do",
				data : param,
				success : function(result) {
					$('.mainForm').html(result);
				}
			})
		})
		
		$('.mainForm').on('click', '#normalMemberOut', function() {
			var data = "";
			var param = "";
			$("input[id=normalMemberCheck]:checked").each(function() {
				if (data == "") {
					data = $(this).val();
				} else {
					data = data + "," + $(this).val();
				}
			});
			param = "id=" + data + "&type=normal";
			$.ajax({
				type : "get",
				url : "adminMemberOut.do",
				data : param,
				success : function(result) {
					$('.mainForm').html(result);
				}
			})
		})
		
		$('.mainForm').on('click', '#blackNormalMove', function() {
			var data = "";
			var param = "";
			$("input[id=blackMemberCheck]:checked").each(function() {
				if (data == "") {
					data = $(this).val();
				} else {
					data = data + "," + $(this).val();
				}
			});
			param = "id=" + data + "&type=black";
			$.ajax({
				type : "get",
				url : "blackNormalMove.do",
				data : param,
				success : function(result) {
					$('.mainForm').html(result);
				}
			})
		})
		
		$('.mainForm').on('click', '#blackMemberOut', function() {
			var data = "";
			var param = "";
			$("input[id=blackMemberCheck]:checked").each(function() {
				if (data == "") {
					data = $(this).val();
				} else {
					data = data + "," + $(this).val();
				}
			});
			param = "id=" + data + "&type=black";
			$.ajax({
				type : "get",
				url : "adminMemberOut.do",
				data : param,
				success : function(result) {
					$('.mainForm').html(result);
				}
			})
		})
	})
	
	function listMember(type, cp) {
			var param = "type=" + type + "&cp=" + cp;
			if(type == 'all') {
				$.ajax({
					type : "get",
					url : "adminAllMemberList.do",
					data : param,
					success : function(result) {
						console.log(result);
						$('.mainForm').html(result);
					}
				})
			} else if(type == 'normal') {
				$.ajax({
					type : "get",
					url : "adminNormalMemberList.do",
					data : param,
					success : function(result) {
						console.log(result);
						$('.mainForm').html(result);
					}
				})
			} else {
				$.ajax({
					type : "get",
					url : "adminBlackMemberList.do",
					data : param,
					success : function(result) {
						console.log(result);
						$('.mainForm').html(result);
					}
				})
			}
			
		}
</script>
</head>
<body>
	<h2>회원관리</h2>	
	<div class="container" style="margin-top: 15px">
		<ul class="tab">
			<li><a class="memberTab1">전체</a></li>
			<li><a class="memberTab2">일반</a></li>
			<li><a class="memberTab3">블랙</a></li>
		</ul>
	</div>
	<div class="mainForm"></div>
</body>
</html>