<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>jQuery UI Autocomplete - Default functionality</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">	
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>	
<script>
/*
 Economy : 일반석
 PreminumEconomy : 프리미엄 일반석
 Business : 비즈니스석
 First : 일등석
 
 성인 : adults
 유/소아 : children

 */

function go() {

    var p1 = document.forms[0];
    var contry1 = p1.tags.value;
    var contry2 = p1.tags2.value;
    
    var type= p1.type.value;
    
    var con1 = contry1.substring(contry1.indexOf("(")+1,contry1.indexOf("(")+4);	
    var con2 = contry2.substring(contry2.indexOf("(")+1,contry2.indexOf("(")+4);
    
   	var go_date = p1.go_date.value;
   	go_date = go_date.replace(/\-/g,'');
   	
    if(type=="go"){
    	var url = "https://www.skyscanner.co.kr/transport/flights/"+con1+"/"+con2+"/"+go_date+"?adults=2&children=0&adultsv2=2&childrenv2=&infants=0&cabinclass=economy&rtn=0&preferdirects=false&outboundaltsenabled=false&inboundaltsenabled=false&ref=home#results";
		window.alert(url);	
    	window.open(url,"","");	
    }else if(type="return"){
       	var re_date = p1.re_date.value;
       	re_date = re_date.replace(/\-/g,'');
       	
    	var url = "https://www.skyscanner.co.kr/transport/flights/"+con1+"/"+con2+"/"+go_date+"/"+re_date+"?adults=2&children=0&adultsv2=2&childrenv2=&infants=0&cabinclass=economy&rtn=1&preferdirects=false&outboundaltsenabled=false&inboundaltsenabled=false&ref=flights_seo_landing_page#results";
		window.alert(url);	
    	window.open(url,"","");	
    }

}

function ty() {
    var p1 = document.forms[0];
    var type= p1.type.value;
    
    if(type=="return")
    {
    	var re='<input type="date" name="re_date" id="re_date">';
    	  
    	document.getElementById('re');
        document.getElementById('re').innerHTML = re;  
    }else if(type=="go"){
    	document.getElementById('re');
        document.getElementById('re').innerHTML = ""; 
    }

}
$( function() {	
    var availableTags = [
		"인천 (ICN)",
		"김포 (GMP)", 
		"모스크바 (MOW)",
		"상트페테르부르크 - 풀코보 (LED)",
		"런던 (LON) (영국)",
		"프랑크푸르트 (FRA)",
		"파리 (PAR) (프랑스)",
		"밀라노 (MIL)",
		"마드리드 (MAD)",
		"비엔나 (VIE)",
		"뮌헨 (MUC)",
		"취리히 (ZRH)",
		"로마 (ROM) (이탈리아)",	
		"헬싱키 -반타 (HEL) ",
		"암스테르담 스키폴 (AMS) ",
		"리용 (LYS) (프랑스) ",
		"바르셀로나 (BCN) (스페인) ",	
		"오슬로 (OSL) ",
		"글래스고 (GLA) (영국) ",
		"베를린 (BER) (독일) ",	
		"코오롱 (CGN) ",
		"스톡홀름 (STO) (스웨덴) ",	
		"니스 (NCE) (프랑스) ",
		"더블린 (DUB) (아일랜드) ",	
		"노보시비르스크 (OVB) ",
		"아테네인터내셔널 (ATH) ",	
		"브뤼셀 (BRU) ",
		"함부르크 (HAM) ",	
		"소치 (AER) ",
		"벨파스트 (BFS) ",	
		"마르세유 (MRS) ",
		"테네리페 (TCI) ",	
		"에카테린버그 (SVX) ",
		"팔마 마요르카 (PMI) ",	
		"팔레르모 (PMO) ",
		"글랜카나리아 라팔마 (LPA) ",	
		"스타방거 (SVG) ",
		"제네바 (GVA) ",	
		"에르칸 (ECN) ",
		"뒤셀도르프 (DUS) ",	
		"크라스노다르 (KRR) ",
		"에든버러 (EDI) ",	
		"나폴리인터내셔널 (NAP) ",
		"툴루즈 (TLS) ",	
		"보르도 (BOD) ",
		"카타니아폰타나로사 (CTA)",	
		"코펜하겐 (CPH)",
		"베르겐 (BGO)",	
		"트롬소 (TOS)",
		"스투트가르트 (STR) (독일)",	
		"리스본 (LIS)",
		"트론헤임 (TRD)"
    ];
    $( "#tags" ).autocomplete({
      source: availableTags
    });
  } );
  $( function() {	
    var availableTags = [
		"인천 (ICN)",
		"김포 (GMP)", 
		"모스크바 (MOW)",
		"상트페테르부르크 - 풀코보 (LED)",
		"런던 (LON) (영국)",
		"프랑크푸르트 (FRA)",
		"파리 (PAR) (프랑스)",
		"밀라노 (MIL)",
		"마드리드 (MAD)",
		"비엔나 (VIE)",
		"뮌헨 (MUC)",
		"취리히 (ZRH)",
		"로마 (ROM) (이탈리아)",	
		"헬싱키 -반타 (HEL) ",
		"암스테르담 스키폴 (AMS) ",
		"리용 (LYS) (프랑스) ",
		"바르셀로나 (BCN) (스페인) ",	
		"오슬로 (OSL) ",
		"글래스고 (GLA) (영국) ",
		"베를린 (BER) (독일) ",	
		"코오롱 (CGN) ",
		"스톡홀름 (STO) (스웨덴) ",	
		"니스 (NCE) (프랑스) ",
		"더블린 (DUB) (아일랜드) ",	
		"노보시비르스크 (OVB) ",
		"아테네인터내셔널 (ATH) ",	
		"브뤼셀 (BRU) ",
		"함부르크 (HAM) ",	
		"소치 (AER) ",
		"벨파스트 (BFS) ",	
		"마르세유 (MRS) ",
		"테네리페 (TCI) ",	
		"에카테린버그 (SVX) ",
		"팔마 마요르카 (PMI) ",	
		"팔레르모 (PMO) ",
		"글랜카나리아 라팔마 (LPA) ",	
		"스타방거 (SVG) ",
		"제네바 (GVA) ",	
		"에르칸 (ECN) ",
		"뒤셀도르프 (DUS) ",	
		"크라스노다르 (KRR) ",
		"에든버러 (EDI) ",	
		"나폴리인터내셔널 (NAP) ",
		"툴루즈 (TLS) ",	
		"보르도 (BOD) ",
		"카타니아폰타나로사 (CTA)",	
		"코펜하겐 (CPH)",
		"베르겐 (BGO)",	
		"트롬소 (TOS)",
		"스투트가르트 (STR) (독일)",	
		"리스본 (LIS)",
		"트론헤임 (TRD)"
    ];
    $( "#tags2" ).autocomplete({
      source: availableTags
    });
  } );
  </script>
</head>
<body>
<form action="test.do">
<div id="type">
<input type="radio" name="type" value="go" checked="checked" onchange="ty()"><label>편도</label>
<input type="radio" name="type" value="return" onchange="ty()"><label>왕복</label>
</div>

	<div id="div1" class="ui-widget">
		<label for="tags">출발지: </label> <input name="tags" id="tags">
		<label for="tags2">도착지: </label> <input name="tags2" id="tags2">
		<br>
		<input type="date" name="go_date" id="go_date">
		<div id="re">
		</div>
	</div>
	<input type="button" value="확인" onclick="go()">
</form>

</body>
</html>