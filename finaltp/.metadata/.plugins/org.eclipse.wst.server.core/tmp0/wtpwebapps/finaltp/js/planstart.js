function create(){
	
	var mask=document.createElement('div');
	mask.setAttribute('id','mask');
	
	var planstartdiv=document.createElement('div');
	planstartdiv.setAttribute('id','planstartdiv');
	planstartdiv.setAttribute('class','window');
	
	var p1=document.createElement('p');
	p1.setAttribute('align','right');
	
	var close=document.createElement('input');
	close.setAttribute('class','close');
	close.setAttribute('type','button');
	close.setAttribute('value','X');
	
	p1.appendChild(close);
	
	var p2=document.createElement('p');
	p2.setAttribute('align','center');
	
	var subject=document.createElement('input');
	subject.setAttribute('id','subject');
	subject.setAttribute('type','text');
	subject.setAttribute('placeholder','예: @박 @일 @여행등등');

	p2.appendChild(subject);
	
	var p3=document.createElement('p');
	p3.setAttribute('align','center');
	
	var startday=document.createElement('input');
	startday.setAttribute('id','startday');
	startday.setAttribute('readonly','readonly');
	startday.setAttribute('placeholder','startday');
	
	var thema=document.createElement('select');
	thema.setAttribute('id','thema');
	
	var option1=document.createElement('option');
	var text = document.createTextNode('맛집투어');
	option1.appendChild(text);
	
	var option2=document.createElement('option');
	var text = document.createTextNode('명소관광');
	option2.appendChild(text);

	var option3=document.createElement('option');
	var text = document.createTextNode('액티비티');
	option3.appendChild(text);

	var option4=document.createElement('option');
	var text = document.createTextNode('미술관/박물관 투어');
	option4.appendChild(text);

	var option5=document.createElement('option');
	var text = document.createTextNode('가족여행');
	option5.appendChild(text);
	
	thema.appendChild(option1);
	thema.appendChild(option2);
	thema.appendChild(option3);
	thema.appendChild(option4);
	thema.appendChild(option5);

	p3.appendChild(startday);
	p3.appendChild(thema);
	
	var p4=document.createElement('p');
	p4.setAttribute('align','center');
	
	var startbt=document.createElement('input');
	startbt.setAttribute('id','bt1');
	startbt.setAttribute('type','button');
	startbt.setAttribute('value','start');
	
	p4.appendChild(startbt);
	
	var calendardiv=document.createElement('div');
	calendardiv.setAttribute('id','calendardiv');

	
	planstartdiv.appendChild(p1);	
	planstartdiv.appendChild(p2);	
	planstartdiv.appendChild(p3);	
	planstartdiv.appendChild(p4);
	planstartdiv.appendChild(calendardiv);
	
	var script=document.createElement('script');
	script.setAttribute('id','script');
	script.setAttribute('src','/finaltp/js/layerPopup.js');
	
	var element=document.getElementsByTagName("body")[0];
	element.appendChild(mask);
	element.appendChild(planstartdiv);
	element.appendChild(script);
}