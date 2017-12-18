<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/*  div {
	border: 1px solid black; 
	
} */
body {
	width: 1280px;
	margin: 30 auto;
}

#calendardiv {
	display: none;
	position: absolute;
	left: 20px;
	top: 40px;
	z-index: 3;
	background-color: red;
	width: 150px;
	height: 210px;
	text-align: center;
	margin: 0 auto;
}

#plandiv1 {
	text-align: center;
	width: 1200px;
}

#plandiv2 {
	float: left;
	width: 300px;
}

#plandiv4 {
	float: left;
	width: 900px;
}

#plandiv3 {
	float: left;
	width: 300px;
}

#placediv {
	width: 300px;
	height: 500px;
}

#googleMap {
	width: 900px;
	height: 300px;
}

#listdiv {
	width: 300px;
	height: 50px;
	clear: both;
	overflow: visible;
}

#datadiv {
	width: 65%;
	height: 50px;
	align: center;
	float: right;
	font-size: 10px;
	overflow: hidden;
	z-index: 900;
}

#photodiv {
	width: 30%;
	height: 50px;
	align: center;
	float: left;
}

#schedule {
	width: 900px;
}

#tbd td {
	border: 1px solid black;
}

#tbd .on-screen {
	width: 250px;
	height: 25px;
	overflow: visible;
}

.off-screen {
	display: none;
}

#tbd .axis {
	width: 100px;
	height: 12px;
}

#tbd .hidden {
	display: none;
}

table {
	border-collapse: collapse;
	margin-bottom: 10px;
}

#thd a {
	display: inline-block;
	padding: 3px 5px;
	margin-right: 10px;
	font-family: Tahoma;
	background: #ccc;
	color: #000;
	text-decoration: none;
}
#routesearch1 {
	display: none;
	position: absolute;
	left: 100px;
	top: 100px;
	width: 800px;
	height: 600px;
	z-index: 2;
}
#routesearch {
	display: none;
	position: absolute;
	left: 100px;
	top: 100px;
	width: 800px;
	height: 600px;
	z-index: 2;
}

#mask {
	position: absolute;
	z-index: 1;
	background-color: #000;
	display: none;
	left: 0;
	top: 0;
}

#routemap {
	height: 600px;
}
/* Optional: Makes the sample page fill the window. */
#floating-panel {
	position: absolute;
	top: 10px;
	left: 25%;
	z-index: 5;
	background-color: #fff;
	padding: 5px;
	border: 1px solid #999;
	text-align: center;
	font-family: 'Roboto', 'sans-serif';
	line-height: 30px;
	padding-left: 10px;
}

#right-panel {
	font-family: 'Roboto', 'sans-serif';
	line-height: 30px;
	padding-left: 10px;
}

#right-panel select, #right-panel input {
	font-size: 15px;
}

#right-panel select {
	width: 100%;
}

#right-panel i {
	font-size: 12px;
}

#right-panel {
	height: 100%;
	float: right;
	width: 390px;
	background-color: white;
	overflow: auto;
}

#routemap {
	margin-right: 400px;
}

#floating-panel {
	background: #fff;
	padding: 5px;
	font-size: 14px;
	font-family: Arial;
	border: 1px solid #ccc;
	box-shadow: 0 2px 2px rgba(33, 33, 33, 0.4);
}

@media print {
	#routemap {
		height: 500px;
		margin: 0;
	}
	#right-panel {
		float: none;
		width: auto;
	}
}
</style>

<script id="Jquery"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script id="googleMapApi" 
	src="http://maps.googleapis.com/maps/api/js?libraries=places&key=AIzaSyBpqDLf8-KIWjhRytNm6bpjloE4qYkFaGk"></script>
<script src="/finaltp/js/draganddrop.js"></script>
<script src="/finaltp/js/googlemap.js"></script>
<script src="/finaltp/js/planwrite.js"></script>
<script type="text/javascript">
date = new Date(${startday_year},${startday_month-1},${startday_day});
var nextdate=date;
var dates=new Array();
var datecount=0;
dates.push(date);
var viewlist=3;
var rel=0;
var element=null;
	window.onload = function() {
		$(document).ready(
				function() {
					add_td();
					$('#startday').click(function(e) {
						e.preventDefault();
						$('#calendardiv').show();
						$.ajax({
							url : 'calendar.do',
							dataType : 'html',
							type : 'GET',
							data : null,
							success : function(data) {
								$('#calendardiv').html(data);
							}
						});
					});

					$(document).on(
							"click",
							".cale #day",
							function(e) {
								e.preventDefault();
								var year = $('.cale #year').text();
								var month = $('.cale #month').text();
								var day = $(this).find('font').text();
								date = new Date(year, month - 1, day);
								$('#startday').val(
										date.getFullYear() + '-'
												+ (date.getMonth() + 1) + '-'
												+ date.getDate());
								$('#calendardiv').hide();
							});
					$('#regionsearch').keypress(function(e) {
						if (e.keyCode == 13) {
							if ( $("#listdiv").length > 0 ) {
								removeList();
							}
							var search;
							var region;
							var regionplace;
							if ($('#regionsearch').val() != '') {
								region = $('#regionsearch').val();
								var request = {
									query : region
								};
								service.textSearch(request,regioncallback);
								function regioncallback(results, status) {
									if (status == google.maps.places.PlacesServiceStatus.OK) {
										regionplace = results[0];
										nearbySearch(regionplace);
									}
								}
							}else{
								alert('지역을 입력하세요');
							}
						}
					});
					$('#showplacesearch').keypress(function(e) {
						if (e.keyCode == 13) {
							if ( $("#listdiv").length > 0 ) {
								removeList();
							}
							var search=null;
							var region=null;
							var regionplace=null;
							if ($('#regionsearch').val() != '') {
								region = $('#regionsearch').val();
								var request = {
									query : region
								};
								service.textSearch(request,regioncallback);
								function regioncallback(results, status) {
									if (status == google.maps.places.PlacesServiceStatus.OK) {
										regionplace = results[0];
										if ($('#showplacesearch').val() == '') {
											alert('장소을 입력하세요');
											return;
										}
										search = $('#showplacesearch').val();
										showplacesearch(search,regionplace);
									}
								}
							}else{
								if ($('#showplacesearch').val() == '') {
									alert('장소을 입력하세요');
									return;
								}
								search = $('#showplacesearch').val();
								showplacesearch(search,regionplace);
							}
							/* map.addListener('idle', textSearch(search)); */
						}
					});
					$('#add-schedule').click(function(e){
						add_td();
					});
					$('#delete-schedule').click(function(e){
						del_col();
					});
					$(function(){
					    var $win = $(window);
					    var top = $(window).scrollTop(); // 현재 스크롤바의 위치값을 반환합니다.
					 
					    /*사용자 설정 값 시작*/
					    var speed          = 1000;     // 따라다닐 속도 : "slow", "normal", or "fast" or numeric(단위:msec)
					    var easing         = 'linear'; // 따라다니는 방법 기본 두가지 linear, swing
					    var $layer         = $('#plandiv3'); // 레이어 셀렉팅
					    var layerTopOffset = 0;   // 레이어 높이 상한선, 단위:px
					    $layer.css('position', 'absolute');
					    /*사용자 설정 값 끝*/
					 
					    // 스크롤 바를 내린 상태에서 리프레시 했을 경우를 위해
					    if (top > 50 ){
					        $win.scrollTop(layerTopOffset+top);
					    }
					    else{
					        $win.scrollTop(0);
					    }
					    $layer.animate({"top":400 }, {duration:speed, easing:easing, queue:false});
					    //스크롤이벤트가 발생하면
					    $(window).scroll(function(){
					        if ($win.scrollTop() < 400) {
					            yPosition = 400;
					        }else{
						        yPosition = $win.scrollTop();
					        }
					        $layer.animate({"top":yPosition }, {duration:speed, easing:easing, queue:false});
					    });
					});
					$('#mask').click(function () {  
					    $(this).hide();
						$('#routesearch').hide();
						$('#routesearch1').hide();
					});  
				});
	}
</script>
</head>
<body>
	<div id="calendardiv"></div>
	<form action="planwrite.do" method="post">
		<div id="plandiv1">
			<img id="coverimg" src="">
			<p style="align: center;">
				<span id="subject">${subject }</span> <input id="subject_update"
					type="button" value="수정">
			</p>
			<p style="align: right;">
				<input id="coverimg_update" type="button" value="수정">
			</p>
		</div>
		<div id="plandiv2">
			<p>
				<input id="save" type="button" value="저장"> <input
					id="cancel" type="button" value="취소">
			</p>
			<p>
				<a id="moneybook">가계부</a> <a id="checklist">체크리스트</a>
			</p>
			<div>
				<p>여행시작일</p>
				<p>
					<input id="startday" type="text" readonly="readonly"
						value="${startday_year}-${startday_month}-${startday_day}">
				</p>
				<p>테마</p>
				<p>
					<select id="thema">
						<option ${thema eq '맛집투어'?'selected':'' }>맛집투어</option>
						<option ${thema eq '명소관광'?'selected':'' }>명소관광</option>
						<option ${thema eq '액티비티'?'selected':'' }>액티비티</option>
						<option ${thema eq '미술관/박물관 투어'?'selected':'' }>미술관/박물관 투어</option>
						<option ${thema eq '가족여행'?'selected':'' }>가족여행</option>
					</select>
				</p>
			</div>
			
		</div>
		
		<div id="plandiv4">
			<p>
				<input id="plan_story_subject" type="text">
			</p>
			<p>
				<textarea id="plan_story_content" rows="" cols=""></textarea>
			</p>
			<div id="googleMap"></div>
			<div id="schedule">
				<div id="scheduleday">
					<table>
						<thead id="thd">
							<tr>
								<th>
									<p><a id="ALL" onclick="javascript:scheduleclick(this);">ALL</a></p>
									<p><input type="button" id="add-schedule" value="+"><input type="button" id="delete-schedule" value="-"></p>
								</th>
							</tr>
						</thead>
						<tbody id="tbd">
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">06:00AM</span></td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">07:00</span>
								</td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">08:00</span>
								</td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">09:00</span>
								</td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">10:00</span>
								</td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">11:00</span>
								</td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">12:00PM</span></td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">01:00</span>
								</td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">02:00</span>
								</td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">03:00</span>
								</td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">04:00</span>
								</td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">05:00</span>
								</td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">06:00</span>
								</td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">07:00</span>
								</td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">08:00</span>
								</td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">09:00</span>
								</td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">10:00</span>
								</td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">11:00</span>
								</td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
							<tr>
								<td class="axis" rowspan="2"><span class="time-label">12:00AM</span></td>
							</tr>
							<tr>
								<td class="hidden"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div id="plandiv3">
			<div>
				<p>
					<input id="regionsearch" type="text" placeholder="지역검색">
				</p>
				<p>
					<input id="showplacesearch" type="text" placeholder="명소검색">
				</p>
				<p>
					<input id="showplace" type="button" value="명소"> <input
						id="restaurant" type="button" value="식당"> <input
						id="lodge" type="button" value="숙박">
				</p>
			</div>
			<div id="placediv"></div>
		</div>
		<div id="routesearch1">
			<input value="선택" type="button" onclick="showroutesearch()">
		</div>
		<div id="routesearch">
		    <div id="right-panel"></div>
   		 	<div id="routemap"></div>
		</div>
		<div id="mask"></div>
	</form>
<script id="schedulescript" src="/finaltp/js/scheduleroute.js"></script>
<script id="pagingscript" src="/finaltp/js/tablePaging.js"></script>
</body>
</html>