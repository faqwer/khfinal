
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach var="dto" items="${member }">
	<table width="800" align="center" height="800">
		<tr>
			<td>
				<table>
					<tr>
						<td width="300">
							<table>
								<tr>
									<td><table border="1" cellspacing="0" width="300"
											height="400">
											<tr>
												<td align="center" height="230">
													<c:if test="${dto.profile_img==null}">
														<img src="img/testimg.jpg" width="80" height="100">
													</c:if>
												<br>
													${dto.id }
												</td>
											</tr>
											<tr>
												<td align="center" height="70"><input type="button"
													value="팔로워"><input type="button" value="팔로잉"><br>
													<a>회원 정보 수정</a> / <a href="memberoutForm.do">회원 탈퇴</a></td>
											</tr>
										</table></td>
								</tr>
								<tr>
									<td><table border="1" cellspacing="0" width="300"
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
						<td width="500"><table border="1" cellspacing="0" width="500"
								height="560">
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