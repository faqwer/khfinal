var myCenter = new google.maps.LatLng(37.250943, 127.028344);
	var map;
	var service;
	var infoWindow;
	var marker;
	var d_height=Number(0);
	function initialize() {
		var mapProp = {
			center : myCenter,
			zoom : 1,
			mapTypeId : google.maps.MapTypeId.terrain
		};
		map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
		infoWindow = new google.maps.InfoWindow();
		service = new google.maps.places.PlacesService(map);
	}

	function nearbySearch(place,type) {
		var request = {
			location : place.geometry.location,
			radius : '50000',
			type : [type] 
		};

		service.nearbySearch(request, callback);
	}
	function showplacesearch(search, regionplace) {
		var request;
		if(regionplace!=null){
			request = {
				location : regionplace.geometry.location,
				radius : '50000',
				keyword : search
			};
			service.nearbySearch(request, showplacecallback);
		}else{
			request = {
				query : search
			};
			service.textSearch(request, showplacecallback);
		}
		
	}
	
	function showplacecallback(results, status) {
		if (status == google.maps.places.PlacesServiceStatus.OK) {
			for (var i = 0; i < results.length; i++) {
				var place = results[i];
				var request = {
					  placeId: place.place_id
				};
				service.getDetails(request, detailscallback);
			}
		}
	}

	function callback(results, status,pagination) {
		if (status == google.maps.places.PlacesServiceStatus.OK) {
			for (var i = 0; i < results.length; i++) {
				var place = results[i];
				var request = {
					  placeId: place.place_id
				};
				service.getDetails(request, detailscallback);
			}

		    
		    if (pagination.hasNextPage) {
		    	$('#listaddbt').click(function(e){
				    e.preventDefault();  
				    pagination.nextPage();
		    	});
				/*$('#placediv').scroll(function(e) {
				    e.preventDefault();  
					var w_height = $('#placediv').height();  // 브라우저 크기
					var s_height = d_height - w_height;
				    var currentScroll = $('#placediv').scrollTop();
			    	console.log(s_height+','+currentScroll);
				    
				    if ((s_height - currentScroll)<1) {
						pagination.nextPage();
				   	}
				});*/
			}
		}
	}
	function detailscallback(place, status) {
		  if (status == google.maps.places.PlacesServiceStatus.OK) {
				addList(place);
				d_height=d_height+52;
		  }
		}
	function removeList(){
	    var plandiv3=document.getElementById("plandiv3");
	    var placediv=document.getElementById("placediv");
	    plandiv3.removeChild(placediv);
	    var placediv=document.createElement('div');
	    placediv.setAttribute('id','placediv');
	    plandiv3.appendChild(placediv);
	    d_height=Number(0);
	}
	function addList(place){
		var placediv=document.getElementById("placediv");
		var listdiv=document.createElement('div');
		listdiv.setAttribute('id','listdiv');
	 	listdiv.setAttribute('onmouseenter','javascript:mouseenter(this)');
		listdiv.setAttribute('onmouseleave','javascript:mouseleave(this)'); 
		listdiv.setAttribute('draggable','true');
		listdiv.setAttribute('ondragstart','clonedrag(this,event)');
		
		var div1=document.createElement('div');
		div1.setAttribute('id','photodiv');
		
		var photos = place.photos;
		var img;
		if (photos) {
			img=photos[0].getUrl({'maxWidth': 50, 'maxHeight': 50});
			var icon=document.createElement('img');
			icon.setAttribute('src',img);
			icon.setAttribute('id','icon');
			icon.setAttribute('width','100%');
			icon.setAttribute('height','100%');
			div1.appendChild(icon);
		}
		
		var div2=document.createElement('div');
		div2.setAttribute('id','datadiv');

		var name=document.createElement('p');
		name.setAttribute('id','name');
		var nametext = document.createTextNode(place.name);
		name.appendChild(nametext);
		div2.appendChild(name);
		
		var address=document.createElement('p');
		address.setAttribute('id','address');
		var addresstext = document.createTextNode(place.formatted_address);
		address.appendChild(addresstext);
		div2.appendChild(address);
		
		if(place.rating!=null){
			var rating=document.createElement('p');
			var ratingtext = document.createTextNode(place.rating);
			rating.appendChild(ratingtext);
			div2.appendChild(rating);
		}
		
		var place_lat=document.createElement('input');
		place_lat.setAttribute('id','place_lat');
		place_lat.setAttribute('type','hidden');
		place_lat.setAttribute('value',place.geometry.location.lat());
		div2.appendChild(place_lat);
		
		var place_lng=document.createElement('input');
		place_lng.setAttribute('id','place_lng');
		place_lng.setAttribute('type','hidden');
		place_lng.setAttribute('value',place.geometry.location.lng());
		div2.appendChild(place_lng);
		
		var place_Id=document.createElement('input');
		place_Id.setAttribute('id','place_Id');
		place_Id.setAttribute('type','hidden');
		place_Id.setAttribute('value',place.place_id);
		div2.appendChild(place_Id);
		
		listdiv.appendChild(div1);
		listdiv.appendChild(div2);
		placediv.appendChild(listdiv);
	}
	function removeMarker() {
		marker.setMap(null);
	}
	function addMarker(data) {
		marker = new google.maps.Marker({
			map : map,
			position : data.place_location,
			icon : {
				url : '/finaltp/img/marker/nomal.png',
				anchor : new google.maps.Point(20, 20),
				scaledSize : new google.maps.Size(30, 30)
			}
		}); 
		infoWindow.setContent(data.place_data);
		infoWindow.open(map, marker);
	}
	google.maps.event.addDomListener(window, 'load', initialize);