
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#mainTable {
	text-align: center;
}

#bestplan {
	text-align: left;
}

#trip {
	text-align: left;
}

.main_img {
	position: relative;
	/*float:left;  optional */
}

.main_img .planner_text {
	position: absolute;
	top: 175px;
	/* in conjunction with left property, decides the text position */
	left: 150px;
	width: 700px; /* optional, though better have one */
}
</style>
</head>
<body>
	<table id="mainTable" align="center" width="1200px" border="1"
		cellspacing="0" height="500px">
		<tr>
			<td colspan="3">
				<div class="main_img">
					<img src="img/Paris_EiffelTower.jpg" width="1200px" height="350px">
					<div class="planner_text">
						<input type="button" value="플래너 시작하기">
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2" width="700px" align="center">항공권 조회<br>
				<table width="300px">
					<tr>
						<td><input type="text" name="출발지"><br><input type="text" name="도착지"></td>
						<td><input type="button" value="출발날짜"></td>
						<td><input type="button" value="도착 날짜"></td>
						<td><table>
							<td><input type="button" value="성인"></td>
							<td><input type="button" value="아동"></td>
							<td><input type="button" value="유아"></td>
						</table>
						<select id="seat">
							<option>이코노미</option>
							<option>비즈니스</option>
							<option>퍼스트</option>
						</select></td>
						<td><input type="submit" value="검색"></td>
					</tr>
				</table>
			</td>
			<td width="300px">환율</td>
		</tr>
		<tr>
			<td colspan="3">경로 추천 이미지 전환</td>
		</tr>
		<tr>
			<td colspan="3" id="bestplan">best plan</td>
		</tr>
		<tr>
			<td>1위</td>
			<td>2위</td>
			<td>3위</td>
		</tr>
		<tr>
			<td colspan="3" id="trip">best 여행기</td>
		</tr>
		<tr>
			<td>1위</td>
			<td>2위</td>
			<td>3위</td>
		</tr>
	</table>
</body>
</html>