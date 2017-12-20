
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	
$(function(){
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "routeTextAreaContentRevise",
        sSkinURI: "se2/SmartEditor2Skin.html",
        htParams : {
            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseToolbar : true,            
            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer : true,    
            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger : true,
        },
        fOnAppLoad : function(){
        	oEditors.getById["routeTextAreaContentRevise"].exec("SET_IR", [""]);
        	oEditors.getById["routeTextAreaContentRevise"].exec("PASTE_HTML", ['${mainList.content}']);
        } 
    });
    
    //전송버튼 클릭이벤트
    $("#savebuttonRouteRevise").click(function(){
        
        var content = oEditors.getById["routeTextAreaContentRevise"].exec("UPDATE_CONTENTS_FIELD", []);
        // 이부분에 에디터 validation 검증
         
        //폼 submit
        $("#routeRevise").submit();
    })
})

	// textArea에 이미지 첨부
 	function pasteHTML(filepath){
		if(${empty mainList.routedto.coverimg}) {
			document.routeRevise.coverimg.value = filepath;
			coverimg = filepath;
		}
 	    var sHTML = '<img src="/finaltp/img/'+filepath+'">';
 	    oEditors.getById["routeTextAreaContentRevise"].exec("PASTE_HTML", [sHTML]);
 	}
</script>
<style>
#routeContentTable {
	text-align: center;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
	<form name="routeRevise" id="routeRevise" method="post" action="adminRouteRevise.do">
		<input type="hidden" name="bbs_idx" value="${mainList.bbs_idx}">
		<input type="hidden" name="coverimg" value="${mainList.routedto.coverimg}">
		<table id="routeContentTable" border="1" cellspacing="0"
			width="900px" align="center">
			<tr>
				<th>테마</th>
				<td><select name="thema">
						<option value="맛집투어" ${mainList.routedto.thema == '맛집투어' ? 'selected = "selected"' : ''}>맛집투어</option>
						<option value="명소관광" ${mainList.routedto.thema == '명소관광' ? 'selected = "selected"' : ''}>명소관광</option>
						<option value="액티비티" ${mainList.routedto.thema == '액티비티' ? 'selected = "selected"' : ''}>액티비티</option>
						<option value="미술관/박물관 투어" ${mainList.routedto.thema == '미술관/박물관 투어' ? 'selected = "selected"' : ''}>미술관/박물관 투어</option>
						<option value="가족여행" ${mainList.routedto.thema == '가족여행' ? 'selected = "selected"' : ''}>가족여행</option>
				</select></td>
			</tr>
			<tr>
				<th>제목</th>
				<td colspan="5">
				<input type="text" name="subject" value="${mainList.subject}">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="4" style="width: 766px;">
					<textarea
						name="routeTextAreaContentRevise" id="routeTextAreaContentRevise" rows="10" cols="100"
						style="width: 766px; height: 412px;">
			    	</textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="button" id="savebuttonRouteRevise" value="수정">
				<input type="button" value="목록으로" onclick="javascript:location.href='adminBbs.do?sel=route&status=normal&cp=1'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>