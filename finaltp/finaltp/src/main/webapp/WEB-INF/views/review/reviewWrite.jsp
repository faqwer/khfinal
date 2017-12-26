<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Style-Type" content="text/css">

<title>네이버 :: Smart Editor 2 &#8482;</title>
<!-- Smart Editor -->
<script type="text/javascript"
	src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="se2/js/HuskyEZCreator.js"
	charset="utf-8"></script>
	
<!-- Smart Editor -->
<script type="text/javascript">
//전역변수선언
	var oEditors = [];
	var coverimg = "";
	var coverCnt = 0;
	
$(function(){
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "textAreaContent",
        sSkinURI: "se2/SmartEditor2Skin.html",
        htParams : {
            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseToolbar : true,            
            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer : true,    
            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger : true,
        }
    });
     
    //전송버튼 클릭이벤트
    $("#savebutton").click(function(){
        //id가 smarteditor인 textarea에 에디터에서 대입
        var content = oEditors.getById["textAreaContent"].exec("UPDATE_CONTENTS_FIELD", []);
        // 이부분에 에디터 validation 검증
         
        //폼 submit
        $("#frm").submit();
    })
})

	// textArea에 이미지 첨부
 	function pasteHTML(filepath){
		
		if(coverCnt == 0) {
			document.frm.coverimg.value = filepath;
			coverimg = filepath;
			coverCnt++;
		}
 	    var sHTML = '<img src="/finaltp/img/'+filepath+'">';
 	    oEditors.getById["textAreaContent"].exec("PASTE_HTML", [sHTML]);
 	}
</script>
</head>
<body>
	<form action="reviewWrite.do" method="post" id="frm" name="frm">
		<input type="hidden" name="coverimg" value="">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" size="73"></td>
				<th>테마</th>
				<td>
					<select name="thema">
						<option value="맛집투어">맛집투어</option>
						<option value="명소관광">명소관광</option>
						<option value="액티비티">액티비티</option>
						<option value="미술관/박물관 투어">미술관/박물관 투어</option>
						<option value="가족여행">가족여행</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="4" style="width: 766px;">
					<textarea name="textAreaContent" id="textAreaContent" rows="10"
						cols="100" style="width: 766px; height: 412px;">
			    	</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<input type="button" id="savebutton" value="작성"/>
					<input type="button" id="cancelbutton" value="취소"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>