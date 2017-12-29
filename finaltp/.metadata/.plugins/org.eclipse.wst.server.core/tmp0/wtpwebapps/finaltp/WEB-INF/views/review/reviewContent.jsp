<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
	border: 1px solid black;
}

li {
	list-style: none;
}

#commentImg {
	border: 1px solid;
	width: 50px;
	height: 50px;
	float: left;
}

#commentWrite {
	margin-left: 95px;
}

.statusChange {
	text-align: right;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
//댓글 추가 버튼
	function commentBtn(bbs_idx) {
		var content = $('#commentbox_'+bbs_idx).val();
		var param = "content=" + content + "&bbs_idx=" + bbs_idx;
		
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

		// 신고 함수
		function reportAccContent(bbs_idx, writer_idx, userid) {
			var param = "bbs_idx=" + bbs_idx + "&writer_idx=" + writer_idx + "&userid=" + userid;
			if(${sessionScope.userid == null}) {
				alert('로그인 후 이용가능합니다.');	
			} else {
				window.open('reportForm.do?' + param, 'reportForm', 'width=500, height=500');	
			}
		}
		
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
		
		// 댓글 보기 함수
		function morePage(bbs_idx) {
			if($('.btn_' + bbs_idx).attr("value") == "댓글보기") {
				$('.comment_'+bbs_idx).css("display", "block");
				$('.btn_' + bbs_idx).attr("value", "접기");
			} else {
				$('.comment_'+bbs_idx).css("display", "none");
				$('.btn_' + bbs_idx).attr("value", "댓글보기");
			}
		}
		
		// 게시판 목록 함수
		function listAcc(bbs_idx) {
			var param = "bbs_idx=" + bbs_idx;
			$.ajax({
				type : "post",
				url : "reviewContent.do",
				data : param,
				success : function(result) {
					$('.mainForm').html(result);
				}
			});
		}
</script>
</head>
<body>
	<div class="mainForm">
		<h2>후기 게시판 본문</h2>
		<table border="1">
			<tr>
				<th>제목</th>
				<td>${mainList.subject}</td>
				<td class="statusChange"><script>
					console.log('userid : ${mainList.userid}');
				</script> <c:if test="${mainList.userid == sessionScope.userid}">
						<a href="reviewRevise.do?bbs_idx=${mainList.bbs_idx}">수정</a>
						<a href="reviewDelete.do?bbs_idx=${mainList.bbs_idx}">삭제</a>
					</c:if></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="2">${mainList.content}</td>
			</tr>
			<tr>
				<td></td>
				<td colspan="2">
					<a href="javascript:reportAccContent(${mainList.bbs_idx}, ${mainList.writer_idx}, '${sessionScope.userid}');">신고</a>&nbsp;&nbsp;&nbsp;
					<c:if test="${!empty loginUser}">
						<c:choose>
							<c:when test="${mainList.recommend == 'OK'}">
								<input type="button" value="추천" onclick="recommendUp(${mainList.bbs_idx}, ${mainList.writer_idx}, ${loginUser.member_idx})">
							</c:when>
							<c:when test="${mainList.recommend == 'NO'}">
								<input type="button" value="추천취소" onclick="recommendDown(${mainList.bbs_idx}, ${mainList.writer_idx}, ${loginUser.member_idx})">
							</c:when>
						</c:choose>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<div id="reviewCommentWrite">
						<input type="text" id="commentbox_${mainList.bbs_idx}" size="50">
						<input type="button" value="완료" id="reviewCommentBtn"
							onclick="commentBtn('${mainList.bbs_idx}')">
					</div>
				</td>
			</tr>
			<tr>
				<c:if test="${!empty mainList.replylist}">
					<tr>
						<td><input type="button" value="댓글보기"
							class="btn_${mainList.bbs_idx}"
							onclick="morePage(${mainList.bbs_idx})"></td>
					</tr>
					<c:forEach var="reply" items="${mainList.replylist}">
						<tr>
							<td colspan="3">
								<div class="comment_${mainList.bbs_idx}" style="display: none">
									<div id="commentImg">
										<img src="${reply.profileImg}"
											style="width: 50px; height: 50px;">
									</div>
									<div>
										<c:url var="writerUrl" value="mypage.do">
											<c:param name="id">${reply.writerid}</c:param>
										</c:url>
										<ul class="lists">
											<li><a href="${writerUrl}">${reply.writerid}</a></li>
											<li id="comment_${reply.reply_idx}">${reply.content}</li>
											<li><h6>${reply.writedate}</h6> <c:if
													test="${reply.writerid == sessionScope.userid}">
														&nbsp;&nbsp;&nbsp;'
													<a href="javascript:commentRevise(${reply.reply_idx},${mainList.bbs_idx},'${reply.content}')">수정</a>&nbsp;
													<a href="javascript:commentDelete(${reply.reply_idx},${mainList.bbs_idx})">삭제</a>
												</c:if></li>
										</ul>
									</div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tr>
		</table>
	</div>
</body>
</html>