
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="/finaltp/css/layerPopup.css">
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script src="/finaltp/js/layerPopup.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<c:forEach var="dto" items="${member }">
	<div id="mask"></div>
	<form name="edit" action="memberEdit.do" >
		<div class="window" id="window">
			<input type="hidden" name="id" id="id" value="${dto.id }">
			<p align="right">
				<input class="close"type="button" value="X">
			</p>
			<p align="center">
				<img src="img/testimg.jpg" width="150" height="150" name="">
			</p>
			<p align="center">
				<input type="password" name="ppwd" placeholder="현재 비밀번호">
			</p>
			<p align="center">
				<input type="password" name="npwd" placeholder="변경 할 비밀번호">
			</p>
			<p align="center">
				<input type="password" name="npwd2" placeholder="변경 할 비밀번호 확인">
			</p>
			<p align="center">
				<input type="submit" value="수정하기">
			</p>
	</form>
	</div>

		<table width="800" align="center" height="800">
			<tr>
				<td>
					<table>
						<tr>
							<td width="300">
								<table>
									<tr>
										<td><table border="1" cellspacing="0" width="220"
												height="400">
												<tr>
													<td align="center" height="230"><c:if
															test="${dto.profile_img==null}">
															<img src="img/testimg.jpg" width="80" height="100">
														</c:if> <br> ${dto.id }</td>
												</tr>
												<tr>
													<td align="center" height="70"><input type="button"
														value="팔로워"><input type="button" value="팔로잉"><br>
														<a href="#" class="openMask">회원 정보 수정</a> / <a
														href="memberoutForm.do">회원 탈퇴</a></td>
												</tr>
											</table></td>
									</tr>
									<tr>
										<td><table border="1" cellspacing="0" width="220"
												height="160">
												<tr>
													<td align="center">MY PLANNER</td>
												</tr>
												<tr>
													<td align="center">내가 작성한 글</td>
												</tr>
												<tr>
													<td align="center">북마크</td>
												</tr>
											</table></td>
									</tr>
								</table>
							</td>
							<td width="500"><table border="1" cellspacing="0"
									width="580" height="560">
									<tr>
										<td>본문내용</td>
									</tr>
								</table></td>
						</tr>
					</table>
				</td>
			</tr>

		</table>

	</c:forEach>

</body>
</html>