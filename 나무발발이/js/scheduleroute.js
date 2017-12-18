var schedulemarkerArray = new Array();
var schedulemarker;
var myTrips=new Array();
var myTrip=new Array();
var flightPath=new Array();
var myTriprow=0;
var myTripcol=0;
var flightPath_length=0;
var myTripsck=0;
var color=['red','blue','gray','green','black'];
var colorck=0;
var startLocation;
var endLocation;
var directionsDisplay;
var directionsService;
var control;
var onChangeHandler = function() {
	  calculateAndDisplayRoute(directionsService, directionsDisplay);
	}; 
function showroutesearch(){
	$('#routesearch1').hide();
	$('#routesearch').show();
	routemap.controls[google.maps.ControlPosition.TOP_CENTER].push(control);
	  calculateAndDisplayRoute(directionsService, directionsDisplay);
}
function wrapWindowByMask(){
	//화면의 높이와 너비를 구한다.
	var maskHeight = $(document).height();  
	var maskWidth = $(window).width();  

	//마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
	$('#mask').css({'width':maskWidth,'height':maskHeight});  

	//애니메이션 효과 - 일단 1초동안 까맣게 됐다가 80% 불투명도로 간다.
	$('#mask').fadeIn(1000);      
	$('#mask').fadeTo("slow",0.8);    
	
	//윈도우 같은 거 띄운다.
	$('#routesearch1').show();
}
function moneyopen(object){
	var listdiv=$(object).parent('div')[0];
	if($(listdiv).find('#moneydiv').css("display")=="none"){
		$(listdiv).find('#moneydiv').show();
	}else if($(listdiv).find('#moneydiv').css("display")=="block"){
		$(listdiv).find('#moneydiv').hide();
	}
}
function routesearch(object){
	var parentid=$(object).parent().parent().attr('id');
	var parentidArr=parentid.split('-');
	var tbd = document.getElementById('tbd');
	var tbd_tr = tbd.getElementsByTagName('tr');
	var startck=0;
	for(var i=(Number(parentidArr[2])-2);i< tbd_tr.length; i++){
		var string=parentidArr[0]+'-'+parentidArr[1]+'-'+i;
		var scan=document.getElementById(string);
		if($(scan).has('#listdiv').length){
			var listdiv=$(scan).find('#listdiv')[0];
			var lat=$(listdiv).find('#datadiv').find('#place_lat').val();
			var lng=$(listdiv).find('#datadiv').find('#place_lng').val();
			if(startck==0){
				startLocation=new google.maps.LatLng(lat,lng);
				startck=1;
			}else{endLocation=new google.maps.LatLng(lat,lng);
				startck=0;
				break;
			}
		}
	}
	control = document.createElement('div');
    control.setAttribute('id','floating-panel');
    var strong = document.createElement('strong');
	var text = document.createTextNode('travelMode:');
	strong.appendChild(text);
	var select=document.createElement('select');
	select.setAttribute('id','travelMode');
	select.addEventListener('change', onChangeHandler);
	var option1=document.createElement('option');
	option1.setAttribute('value','DRIVING');
	var text = document.createTextNode('DRIVING');
	option1.appendChild(text);
	var option2=document.createElement('option');
	option1.setAttribute('value','BICYCLING');
	var text = document.createTextNode('BICYCLING');
	option2.appendChild(text);
	var option3=document.createElement('option');
	option1.setAttribute('value','TRANSIT');
	var text = document.createTextNode('TRANSIT');
	option3.appendChild(text);
	var option4=document.createElement('option');
	option1.setAttribute('value','WALKING');
	var text = document.createTextNode('WALKING');
	option4.appendChild(text);
	select.appendChild(option1);
	select.appendChild(option2);
	select.appendChild(option3);
	select.appendChild(option4);
	control.appendChild(strong);
	control.appendChild(select);
	document.getElementById('routesearch1').appendChild(control);
	initMap();
	wrapWindowByMask();
}

function initMap() {
	directionsDisplay = new google.maps.DirectionsRenderer;
	directionsService = new google.maps.DirectionsService;
    routemap = new google.maps.Map(document.getElementById('routemap'), {
      zoom: 7,
      center: startLocation,
	  mapTypeId : google.maps.MapTypeId.terrain
    });
    var rightpanel=document.getElementById('right-panel');
    if(rightpanel.getElementsByTagName('div').length){
    	var div=rightpanel.getElementsByTagName('div')[0];
    	rightpanel.removeChild(div);
    }
    directionsDisplay.setMap(routemap);
    directionsDisplay.setPanel(rightpanel);
  }
function calculateAndDisplayRoute(directionsService, directionsDisplay) {
    google.maps.event.trigger(routemap, 'resize');
    var travelMode = document.getElementById('travelMode').value;
    directionsService.route({
      origin: {lat:startLocation.lat(),lng:startLocation.lng()},
      destination: {lat:endLocation.lat(),lng:endLocation.lng()},
      travelMode: travelMode
    }, function(response, status) {
      if (status === 'OK') {
        directionsDisplay.setDirections(response);
      } else {
        window.alert('조회된 정보가 없습니다.');
      }
    });
  }
function route(scheduleroute){
	deleteOverlays();
	var tbd = document.getElementById('tbd');
	var thd = document.getElementById('thd');
	var thd_th = thd.getElementsByTagName('th');
	var tbd_tr = tbd.getElementsByTagName('tr');
	if(scheduleroute=="ALL"){
		for(var j=0;j<thd_th.length-1;j++){
			for(var i=0;i< tbd_tr.length; i++){
				var string='schedule-'+j+'-'+i;
				schedule=document.getElementById(string);
				if($(schedule).has('#listdiv').length){
					var listdiv=$(schedule).find('#listdiv');
					var lat=$(listdiv).find('#datadiv').find('#place_lat').val();
					var lng=$(listdiv).find('#datadiv').find('#place_lng').val();
					place_location=new google.maps.LatLng(lat,lng);
					if(myTripcol!=1){
						myTrip[0]=place_location;
						myTripcol=myTripcol+1;
					}else{
						myTrip[1]=place_location;;
						myTrips[myTriprow]=[myTrip[0],myTrip[1]];
						myTriprow=myTriprow+1;
						for(q in myTrip){
							myTrip[q]=null;
						}
						myTrip.length=0;
						myTrip[0]=place_location;
					}
					addschedulemarker(place_location,color[colorck]);
				}
			}colorck++;
		}colorck=0;
	}else{
		for(var i=0;i< tbd_tr.length; i++){
			var string=scheduleroute+'-'+i;
			schedule=document.getElementById(string);
			if($(schedule).has('#listdiv').length){
				var listdiv=$(schedule).find('#listdiv');
				var lat=$(listdiv).find('#datadiv').find('#place_lat').val();
				var lng=$(listdiv).find('#datadiv').find('#place_lng').val();
				place_location=new google.maps.LatLng(lat,lng);
				if(myTripcol!=1){
					myTrip[0]=place_location;
					myTripcol=myTripcol+1;
				}else{
					myTrip[1]=place_location;
					myTrips[myTriprow]=[myTrip[0],myTrip[1]];
					myTriprow=myTriprow+1;
					for(j in myTrip){
						myTrip[j]=null;
					}
					myTrip.length=0;
					myTrip[0]=place_location;
				}
				addschedulemarker(place_location,color[colorck]);
			}
		}
	}
	showOverlays();
}

function addschedulemarker(place_location,colorcol) {
	schedulemarker = new google.maps.Marker({
		position : place_location,
		icon : {
			url : 'https://developers.google.com/maps/documentation/javascript/images/circle.png',
			anchor : new google.maps.Point(10, 10),
			scaledSize : new google.maps.Size(10, 17)
		}
	}); 
	schedulemarkerArray.push(schedulemarker);
	for(;myTripsck<myTrips.length;myTripsck++){
		if(myTrips[myTripsck].length==2){
			flightPath[flightPath_length]=new google.maps.Polyline({
				path:myTrips[myTripsck],
				strokeColor:colorcol,
				strokeOpacity:0.8,
				strokeWeight:1
			});
			flightPath_length++;
		}
	}
}

function showOverlays() {
	if (schedulemarkerArray) {
		 for (i in schedulemarkerArray) {
			 schedulemarkerArray[i].setMap(map);
		 }
	}
	for(var j=0;j<flightPath.length;j++){
		flightPath[j].setMap(map);
	}
}

function deleteOverlays() {
	if (schedulemarkerArray) {
		 for (i in schedulemarkerArray) {
			 schedulemarkerArray[i].setMap(null);
			 schedulemarkerArray[i]=null;
		 }
		 schedulemarkerArray.length = 0;
	}
	for(var i=0;i<myTrips.length;i++){
		myTrips[i]=null;
	}
	for(var j=0;j<flightPath.length;j++){
		flightPath[j].setMap(null);
		flightPath[j]=null;
	}
	flightPath.length=0;
	flightPath_length=0;
	myTrips.length=0;
	myTriprow=0;
	myTripcol=0;
	myTripsck=0;
}