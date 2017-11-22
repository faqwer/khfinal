window.onload=function(){
	(function($){            
    	$('ul.foo').mouseenter( function() {
        	$('li.over', this).css('background-color','red');
    	});
	})(jQuery)

	jQuery('ul.foo').mouseleave(function() {
		jQuery('li.over',this).css('background-color','blue');
	})
	
	jQuery(document.body).css( "background-color", "green" );
	
}