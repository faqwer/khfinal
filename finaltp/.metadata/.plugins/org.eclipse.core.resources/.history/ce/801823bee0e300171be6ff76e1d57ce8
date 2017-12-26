
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
	console.log($(object).attr('id'));
}