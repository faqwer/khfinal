<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

#writeForm {
	width: 800px;
	border: 1px solid;
}

#writerImg {
	float: left;
	width: 100px;
	height: 100px;
	border: 1px solid;
}

#category {
	float: left;
}

#accContentInfo {
	border: 1px solid;
	float: left;
}

#accContentInfo img {
	width: 60px; height: auto;
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

</style>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		// 작성 버튼 클릭 시 이벤트
		$('#writeBtn').click(function() {
			var nation = $('#nation').val();
			var content = $('#content').val();
			var param = 'nation=' + nation + '&content=' + content;
			if('${sessionScope.userid  == null}') {
				alert('로그인 후 이용가능합니다.');
				$('#content').val("");
			} else {
				$.ajax({
					type : "post",
					url : "accWrite.do",
					data : param,
					success : function() {
						listAcc();
					}
				})
			}
		});
	});

	// 댓글 추가 버튼
	function commentBtn(bbs_idx) {
		var content = $('#commentbox_'+bbs_idx).val();
		var param = "content=" + content + "&bbs_idx=" + bbs_idx;
		if('${sessionScope.userid == null}') {
			alert('로그인 후 이용가능합니다.');
			$('#commentbox_'+bbs_idx).val("");
		} else {
			$.ajax({
				type : "post",
				url : "commentWrite.do",
				data : param,
				success : function(result) {
					$('#commentbox').val("");
					listAcc();
				}
			})
		}
	}
	
	// 본문 수정 함수
	function reviseClick(bbs_idx,content) {
		document.getElementById('listContent_' + bbs_idx).innerHTML = "";
		var listContentWriteForm = document.createElement('textarea');
		listContentWriteForm.setAttribute("id", "reviseContent_" + bbs_idx);
		listContentWriteForm.setAttribute("rows", 5);
		listContentWriteForm.setAttribute("cols", 45);
		listContentWriteForm.innerHTML = content;

		document.getElementById('listContent_' + bbs_idx).appendChild(listContentWriteForm);
		
		var listContentComfirmBtn = document.createElement('input');
		listContentComfirmBtn.setAttribute("type", "button");
		listContentComfirmBtn.setAttribute("id", "listContentBtn_" + bbs_idx);
		listContentComfirmBtn.setAttribute("value", "수정");
		listContentComfirmBtn.setAttribute("onclick", "contentReviseAjax("+bbs_idx+")");
		
		document.getElementById('listContent_' + bbs_idx).appendChild(listContentComfirmBtn);
	}
	
	// 본문 수정 콜백 함수
	function contentReviseAjax(bbs_idx) {
		var content = $('#reviseContent_' + bbs_idx).val();
		var param = "bbs_idx=" + bbs_idx + "&content=" + content;
		
		$.ajax({
			type : "post",
			url : "accRevise.do",
			data : param,
			success : function(result) {
				listAcc();
			}
		})
	}
	
	// 본문 삭제 함수
	function deleteClick(bbs_idx) {
		var param = "bbs_idx=" + bbs_idx;
		alert(param);
		$.ajax({
			type : "post",
			url : "accDelete.do",
			data : param,
			success : function(result) {
				listAcc();
			}
		})
	}
	
	// 댓글 수정 함수
	function commentRevise(reply_idx, content) {
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
		commentWriteBtn.setAttribute("onclick", "commentReviseAjax("+reply_idx+")");
		
		comment.appendChild(commentWriteBtn);
	}
	
	// 댓글 수정 콜백 함수
	function commentReviseAjax(reply_idx) {
		var content = $('#commentForm_' + reply_idx).val();
		var param = "reply_idx=" + reply_idx + "&content=" + content;
		
		$.ajax({
			type : "post",
			url : "commentRevise.do",
			data : param,
			success : function(result) {
				listAcc();
			}
		})
	}
	
	// 댓글 삭제 함수
	function commentDelete(reply_idx) {
		var param = "reply_idx=" + reply_idx;
		
		$.ajax({
			type : "post",
			url : "commentDelete.do",
			data : param,
			success : function(result) {
				listAcc();
			}
		})
	}
	
	// 신고 함수
	function reportAccContent(bbs_idx, writer_idx, userid) {
		var param = "bbs_idx=" + bbs_idx + "&writer_idx=" + writer_idx + "&userid=" + userid;
		if('${sessionScope.userid == null}') {
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
				listAcc();
			}
		})
	}
	
	function recommendDown(bbs_idx, writer_idx, user_idx) {
		
		param = "bbs_idx=" + bbs_idx + "&writer_idx=" + writer_idx + "&user_idx=" + user_idx;
		
		$.ajax({
			type : "post",
			url : "recommendDown.do",
			data : param,
			success : function(result) {
				listAcc();
			}
		})
	}

	// 게시판 목록 함수
	function listAcc() {
		$.ajax({
			type : "post",
			url : "accList.do",
			success : function(result) {
				$('#mainForm').html(result);
			}
		});
	}
	
	function morePage(bbs_idx) {
		if($('.btn_' + bbs_idx).attr("value") == "댓글보기") {
			$('.comment_'+bbs_idx).css("display", "block");
			$('.btn_' + bbs_idx).attr("value", "접기");
		} else {
			$('.comment_'+bbs_idx).css("display", "none");
			$('.btn_' + bbs_idx).attr("value", "댓글보기");
		}
	}
</script>
</head>
<body>
	<div id="mainForm">
		<h2>동행 게시판</h2>
		<div id="writeForm">
			<div id="writerImg"><img src="${loginUser.profile_img}" style="width: 100px; height: 100px;"></div>
			<div>
				국가 <select id="nation">
					<option value="프랑스">프랑스</option>
					<option value="영국">영국</option>
					<option value="독일">독일</option>
					<option value="아일랜드">아일랜드</option>
					<option value="포르투갈">포르투갈</option>
					<option value="스페인">스페인</option>
					<option value="노르웨이">노르웨이</option>
					<option value="덴마크">덴마크</option>
					<option value="벨기에">벨기에</option>
					<option value="스위스">스위스</option>
					<option value="이탈리아">이탈리아</option>
					<option value="스웨덴">스웨덴</option>
					<option value="핀란드">핀란드</option>
					<option value="폴란드">폴란드</option>
					<option value="체코">체코</option>
					<option value="그리스">그리스</option>
					<option value="벨라루스">벨라루스</option>
					<option value="우크라이나">우크라이나</option>
					<option value="루마니아">루마니아</option>
					<option value="크로아티아">크로아티아</option>
					<option value="네덜란드">네덜란드</option>
					<option value="룩셈부르크">룩셈부르크</option>
					<option value="불가리아">불가리아</option>
					<option value="슬로베나아">슬로베니아</option>
					<option value="슬로바키아">슬로바키아</option>
					<option value="오스트리아">오스트리아</option>
					<option value="헝가리">헝가리</option>
					<option value="세르비아">세르비아</option>
				</select>
			</div>
			<div>
				<textarea rows="10" cols="45" id="content"></textarea>
			</div>
			<div>
				<input type="button" value="완료" id="writeBtn"> <input
					type="reset" value="다시작성">
			</div>
		</div>
		<br>
		<c:forEach var="dto" items="${mainList}">
			<div id="accContentDiv" style="border: 1px solid black; width: 700px;">
				<div id="accContentInfo"><img src="${dto.profileImg}" style="">
					<br>${dto.accdto.country}<br>
					<a href="javascript:reportAccContent(${dto.bbs_idx}, ${dto.writer_idx}, '${sessionScope.userid}');">신고</a>&nbsp;&nbsp;&nbsp;
					<c:if test="${!empty loginUser}">
						<c:choose>
							<c:when test="${dto.recommend == 'OK'}">
								<input type="button" value="추천" onclick="recommendUp(${dto.bbs_idx}, ${dto.writer_idx}, ${loginUser.member_idx})">
							</c:when>
							<c:otherwise>
								<input type="button" value="추천취소" onclick="recommendDown(${dto.bbs_idx}, ${dto.writer_idx}, ${loginUser.member_idx})">
							</c:otherwise>
						</c:choose>
					</c:if>
				</div>
				<div id="category">
				</div>
				<div class="accContent_${dto.bbs_idx}" style="width: 700px; margin-left: 10px;">
					<c:url var="userUrl" value="mypage.do">
						<c:param name="id">${dto.userid}</c:param>
					</c:url>
					<ul>
						<li><h6>
								<a href="${userUrl}">${dto.userid}</a>
							</h6></li>
						<li id="listContent_${dto.bbs_idx}">${dto.content}</li>
						<li>${dto.writedate}</li>
					</ul>
					
					<c:if test="${dto.userid == sessionScope.userid}">
					<div id="statusChange">
						<a href="javascript:reviseClick(${dto.bbs_idx},'${dto.content}');">수정</a>&nbsp;&nbsp;&nbsp;
						<a href="javascript:deleteClick(${dto.bbs_idx});">삭제</a>
					</div>
					</c:if>
					<div id="commentWrite">
						<input type="text" id="commentbox_${dto.bbs_idx}" size="50"> <input
							type="button" value="완료" id="commentBtn"
							onclick="commentBtn('${dto.bbs_idx}')">
					</div>
					<c:if test="${!empty dto.replylist}">
						<c:forEach var="reply" items="${dto.replylist}">
							<div class="comment_${dto.bbs_idx}" style="display: none">
								<div id="commentImg"><img src="${reply.profileImg}" style="width: 50px; height: 50px;"></div>
								<div>
									<c:url var="writerUrl" value="mypage.do">
										<c:param name="id">${reply.writerid}</c:param>
									</c:url>
									<ul class="lists" >
										<li><a href="${writerUrl}">${reply.writerid}</a></li>
										<li id="comment_${reply.reply_idx}">${reply.content}</li>
										<li><h6>${reply.writedate}</h6>
											<c:if test="${reply.writerid == sessionScope.userid}">
												&nbsp;&nbsp;&nbsp;<a href="javascript:commentRevise(${reply.reply_idx},'${reply.content}')">수정</a>&nbsp;<a href="javascript:commentDelete(${reply.reply_idx})">삭제</a>
											</c:if>
										</li>
									</ul>
								</div>
							</div>
						</c:forEach>
						<br><input type="button" value="댓글보기" class="btn_${dto.bbs_idx}" onclick="morePage(${dto.bbs_idx})">
					</c:if>
				</div>
			</div>
			<br>
		</c:forEach>
		<div style="clear: both;">${pageStr}</div>
	</div>
</body>
</html>