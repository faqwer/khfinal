
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#routeContentTable {
	text-align: center;
}
</style>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	function commentBtn(bbs_idx) {
		var content = $('#commentbox_' + bbs_idx).val();
		var page = "route/routeContent"
		var param = "content=" + content + "&bbs_idx=" + bbs_idx + "&page="
				+ page;
		alert(param);
		$.ajax({
			type : "post",
			url : "commentWrite.do",
			data : param,
			success : function(result) {
				console.log(result);
				$('#commentbox').val("");
				listRoute();
			}
		})

	}

/* 	function listRoute() {
		$.ajax({
			type : "post",
			url : "routeContent.do",
			success : function(result) {
				$('#routeContent').html(result);
				console.log(result);
			}
		});
	} *///목록 가져오는거
</script>
</head>
<body>
	<form name="routeContent">
		
			<table id="routeContentTable" border="1" cellspacing="0"
				width="1000px" align="center">
				<tr>
					<td colspan="5">${mainList.subject}</td>
				</tr>
				<tr>
					<td colspan="5" height="300px">${mainList.content}</td>
				</tr>
				<tr>
					<td width="100">하트</td>
					<td width="800" colspan="3"><input type="text" name="댓글"
						id="commentbox_${mainList.bbs_idx}" size="120"></td>

					<td width="100"><input type="button" value="댓글 등록"
						id="commentBtn" onclick="commentBtn('${mainList.bbs_idx}')"></td>
				</tr>
				<tr>
					<c:if test="${!empty mainList.replylist}">
						<c:forEach var="reply" items="${mainList.replylist}">
						<tr>
							<div id="comment">
							<td width="100px"><div id="commentImg">이미지</div></td>
							<td width="100px"><c:url var="writerUrl" value="mypage.do">
									<c:param name="id">${reply.writerid}</c:param>
								</c:url>
								<a href="${writerUrl}">${reply.writerid}</a></td>
							<td width="800px" colspan="3">${reply.content}<br>${reply.writedate}</td>

							</div>
							</tr>
						</c:forEach>
						
					</c:if>

				</tr>
			</table>
	</form>
</body>
</html>