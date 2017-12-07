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

#listForm {
	width: 800px;
	height: 600px;
	border: 1px solid;
}

#listImg {
	width: 60px;
	height: 60px;
	border: 1px solid;
}

#cate {
	width: 80px;
	height: 20px;
	float: left;
}

#con {
	float: right;
	width: 700px;
	height: 500px;
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
</style>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		document.getElementById(id)
		$('#writeBtn').click(function() {
			var nation = $('#nation').val();
			var content = $('#content').val();
			var param = 'nation=' + nation + '&content=' + content;

			$.ajax({
				type : "post",
				url : "accWrite.do",
				data : param,
				success : function() {
					alert('작성이 완료되었습니다.');
					listAcc();
				}
			})
		});
	});

	function commentBtn(bbs_idx) {
		var content = $('#commentbox_'+bbs_idx).val();
		var param = "content=" + content + "&bbs_idx=" + bbs_idx;
		alert(param);
		$.ajax({
			type : "post",
			url : "commentWrite.do",
			data : param,
			success : function(result) {
				console.log(result);
				$('#commentbox').val("");
				listAcc();
			}
		})

	}
	
	function reviseClick(bbs_idx,content) {
		document.getElementById('listContent_' + bbs_idx).innerHTML = "";
		var listContentWriteForm = document.createElement('textarea');
		listContentWriteForm.setAttribute("rows", 5);
		listContentWriteForm.setAttribute("cols", 45);
		listContentWriteForm.innerHTML = content;

		document.getElementById('listContent_' + bbs_idx).appendChild(listContentWriteForm);
		
		var listContentComfirmBtn = document.createElement('input');
		listContentComfirmBtn.setAttribute("type", "button");
		listContentComfirmBtn.setAttribute("id", "listContentBtn_" + bbs_idx);
		listContentComfirmBtn.setAttribute("value", "수정");
		listContentComfirmBtn.setAttribute("onclick", "contentReviseAjax()");
		
		document.getElementById('listContent_' + bbs_idx).appendChild(listContentComfirmBtn);
	}
	
	function contentReviseAjax() {
		alert('test');
	}
	
	function deleteClick(bbs_idx) {
		var param = "bbs_idx=" + bbs_idx;
		alert(param);
		$.ajax({
			type : "post",
			url : "accdelete.do",
			data : param,
			success : function(result) {
				if(${result > 0}) {
					alert('삭제 성공');
				} else {
					alert('삭제 실패');
				}
				listAcc();
			}
		})
	}

	function listAcc() {
		$.ajax({
			type : "post",
			url : "accList.do",
			success : function(result) {
				$('#mainForm').html(result);
				console.log(result);
			}
		});
	}
</script>
</head>
<body>
	<div id="mainForm">
		<h2>동행 게시판</h2>
		<div id="writeForm">
			<div id="writerImg">이미지</div>
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
			<div id="listForm_${dto.bbs_idx}">
				<div id="listImg"></div>
				<div id="cate">${dto.accdto.country}</div>
				<div id="con" style="border: 1px solid;">
					<c:url var="userUrl" value="mypage.do">
						<c:param name="id">${dto.userid}</c:param>
					</c:url>
					<ul>
						<li><h6>
								<a href="${userUrl}">${dto.userid}</a>
							</h6></li>
						<li id="listContent_${dto.bbs_idx}">${dto.content}</li>
					</ul>
					
					<c:if test="${dto.userid == sessionScope.userid}">
					<div id="statusChange">
						<a href="javascript:reviseClick(${dto.bbs_idx},'${dto.content}');">수정</a>&nbsp;&nbsp;&nbsp;
						<a href="javascript:deleteClick(${dto.bbs_idx});">삭제</a>
					</div>
					
					</c:if>
					<input type="text" id="commentbox_${dto.bbs_idx}" size="50"> <input
						type="button" value="완료" id="commentBtn"
						onclick="commentBtn('${dto.bbs_idx}')">

					<c:if test="${!empty dto.replylist}">
						<c:forEach var="reply" items="${dto.replylist}">
							<div id="comment" style="border: 1px solid;">
								<div id="commentImg">이미지</div>
								<div>
									<c:url var="writerUrl" value="mypage.do">
										<c:param name="id">${reply.writerid}</c:param>
									</c:url>
									<ul>
										<li><a href="${writerUrl}">${reply.writerid}</a>&nbsp;${reply.content}</li>
										<li>${reply.writedate}</li>
									</ul>
								</div>
							</div>
						</c:forEach>
					</c:if>
				</div>
			</div>
			<br>
		</c:forEach>
		${pageStr}
	</div>
</body>
</html>