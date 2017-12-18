<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="http://maps.googleapis.com/maps/api/js?libraries=places&key=AIzaSyDm4locFJLs9GfPhTFuk-mAZpcc_WyZOf4"></script>
<script src="/finaltp/js/googlemap.js"></script>
<script src="/finaltp/js/draganddrop.js"></script>
<script>
var year = 2017;
var month = 12;
var day = 10;
date = new Date(year, month - 1, day);
var nextdate=date;
var dates=new Array();
var datecount=0;
dates.push(date);
var viewlist=3;
var rel=0;
var element=null;

window.onload = function() {
	$(document).ready(function() {
		add_td();
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

	});
}
</script>
<style type="text/css">

div {
	border: 1px solid black; 
	
}
p{
	margin:0 auto;
}
#listdiv{
	width: 300px;
	height: 50px;
	clear:both;
	overflow:hidden;
}
#datadiv{
	width: 65%;
	height: 50px;
	align:center;
	float:right;
	font-size:10px;
	overflow: hidden;
}
#photodiv{
	width: 30%;
	height: 50px;
	align:center;
	float:left;
}
#googleMap {
	width: 500px;
	height: 300px;
}
#placediv{
	clear:both;
	width: 310px;
	height: 500px;
	overflow:auto;
}
#schedule{
	width: 900px;
}
#tbd td{
	border: 1px solid black; 
}
#tbd .on-screen{
	width: 250px;
	height: 25px;
	overflow: visible;
}
.off-screen {
	display: none;
}
#tbd .axis{
	width: 100px;
	height: 12px;
}
#tbd .hidden{
	display: none;
}
table {
	border-collapse:collapse;
	margin-bottom: 10px;
}
#thd a{
	display: inline-block;
	padding: 3px 5px;
	margin-right: 10px;
	font-family:Tahoma;
	background: #ccc;
	color: #000;
	text-decoration: none;
}
</style>
</head>
<body>
	<div id="googleMap"></div>
	<input id="regionsearch" type="text" placeholder="지역검색">
	<input id="showplacesearch" type="text" placeholder="명소검색">
	<div id="placediv"></div>
	<div id="schedule">
				<div id="scheduleday">
					<table>
						<thead id="thd">
							<tr>
								<th>
									<p><a>ALL</a></p>
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
<script id="pagingscript"src="/finaltp/js/tablePaging.js"></script>
</body>
</html>