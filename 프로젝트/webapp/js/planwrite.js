
function mouseenter(object){
	var lat=$(object).find('#datadiv').find('#place_lat').val();
	var lng=$(object).find('#datadiv').find('#place_lng').val();
	place_location=new google.maps.LatLng(lat,lng);
	var place_data=$(object).html();
	var data={place_location:place_location,place_data:place_data};
	addMarker(data);
}
function mouseleave(object){
	removeMarker();
}
function scheduleclick(object){
	route($(object).attr('id'));
}
function plancontinue(){
	$('#savediv').hide();
	$('#mask').hide();
}
function onlyNumber(obj) {
	 str = obj.value;
     len = str.length;
     ch = str.charAt(0);
     for (i = 0; i < len; i++) {
        ch = str.charAt(i);
        if ((ch >= '0' && ch <= '9')) {
           continue;
        } else {
           obj.value = "";
           break;
        }
     }
}
function imageURL(input) {
    
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
           document.getElementById('plandiv1').setAttribute('style','background-image: url('+e.target.result+');')
        }
       reader.readAsDataURL(input.files[0]);
    }
}

