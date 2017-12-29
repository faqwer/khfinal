
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
// 추천 함수
function recommendUp(bbs_idx, writer_idx, user_idx) {
	param = "bbs_idx=" + bbs_idx + "&writer_idx=" + writer_idx + "&user_idx=" + user_idx;
	
	$.ajax({
		type : "post",
		url : "recommendUp.do",
		data : param,
		success : function(result) {
			listAcc(bbs_idx);
		}
	})
}

// 추천 취소 함수
function recommendDown(bbs_idx, writer_idx, user_idx) {
	
	param = "bbs_idx=" + bbs_idx + "&writer_idx=" + writer_idx + "&user_idx=" + user_idx;
	
	$.ajax({
		type : "post",
		url : "recommendDown.do",
		data : param,
		success : function(result) {
			listAcc(bbs_idx);
		}
	})
}


//댓글 추가 버튼
function commentBtnClick(bbs_idx) {
	var content = $('#commentbox_'+bbs_idx).val();
	var param = "content=" + content + "&bbs_idx=" + bbs_idx;
	console.log('test');
	if(${sessionScope.userid == null}) {
		alert('로그인 후 이용가능합니다.');
		$('#commentbox_'+bbs_idx).val("");
	} else {
		$.ajax({
			type : "post",
			url : "commentWrite.do",
			data : param,
			success : function(result) {
				$('#commentbox').val("");
				listAcc(bbs_idx);
			}
		})
	}
}

// 댓글 수정 함수
	function commentRevise(reply_idx, bbs_idx, content) {
		var comment = document.getElementById('comment_' + reply_idx);
		comment.innerHTML = "";
		var commentWriteForm = document.createElement('input');
		commentWriteForm.setAttribute("type", "text");
		commentWriteForm.setAttribute("id", "commentForm_" + reply_idx);
		commentWriteForm.setAttribute("size", "70");
		commentWriteForm.setAttribute("value", content);
		
		comment.appendChild(commentWriteForm);
		
		var commentWriteBtn = document.createElement('input');
		commentWriteBtn.setAttribute("type", "button");
		commentWriteBtn.setAttribute("id", "commentBtn_" + reply_idx);
		commentWriteBtn.setAttribute("value", "수정");
		commentWriteBtn.setAttribute("onclick", "commentReviseAjax("+reply_idx+", "+bbs_idx+")");
		
		comment.appendChild(commentWriteBtn);
	}
	
	// 댓글 수정 콜백 함수
	function commentReviseAjax(reply_idx, bbs_idx) {
		var content = $('#commentForm_' + reply_idx).val();
		var param = "reply_idx=" + reply_idx + "&content=" + content;
		
		$.ajax({
			type : "post",
			url : "commentRevise.do",
			data : param,
			success : function(result) {
				listAcc(bbs_idx);
			}
		})
	}
	
	// 댓글 삭제 함수
	function commentDelete(reply_idx, bbs_idx) {
		var param = "reply_idx=" + reply_idx;
		
		$.ajax({
			type : "post",
			url : "commentDelete.do",
			data : param,
			success : function(result) {
				listAcc(bbs_idx);
			}
		})
	}

	// 게시판 목록 함수
	function listAcc(bbs_idx) {
		var param = "bbs_idx=" + bbs_idx;
		$.ajax({
			type : "get",
			url : "routeContent.do",
			data : param,
			success : function(result) {
				$('.mainForm').html(result);
			}
		});
	}
</script>
<style>
#routeContentTable {
	text-align: center;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js">
	</script>
</head>
<body class="mainForm">
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
				<td width="100">
					<c:if test="${!empty loginUser}">
						<c:choose>
							<c:when test="${mainList.recommend == 'OK'}">
								<input type="button" value="추천"
									onclick="recommendUp(${mainList.bbs_idx}, ${mainList.writer_idx}, ${loginUser.member_idx})">
							</c:when>
							<c:when test="${mainList.recommend == 'NO'}">
								<input type="button" value="추천취소"
									onclick="recommendDown(${mainList.bbs_idx}, ${mainList.writer_idx}, ${loginUser.member_idx})">
							</c:when>
						</c:choose>
					</c:if>
				</td>
				<td width="800" colspan="3"><input type="text" name="댓글"
					id="commentbox_${mainList.bbs_idx}" size="120"></td>

				<td width="100"><input type="button" value="댓글 등록"
					id="commentBtn" onclick="commentBtnClick(${mainList.bbs_idx})"></td>
			</tr>
			<tr>
				<c:if test="${!empty mainList.replylist}">
					<c:forEach var="reply" items="${mainList.replylist}">
						<tr>
							<div id="comment">
								<td width="100px"><div id="commentImg">이미지</div></td>
								<td width="100px"><c:url var="writerUrl" value="mypage.do">
										<c:param name="id">${reply.writerid}</c:param>
									</c:url> <a href="${writerUrl}">${reply.writerid}</a></td>
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