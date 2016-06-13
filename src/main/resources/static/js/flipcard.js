/**
 * @author Goran Arsenic
 * 
 */
$(document).ready(function() {
	$("#button-flip").click(function(){
		 $('.card').toggleClass('flipped');
		 $(this).blur();
	});
});
$(document).keypress(function(e) {
    if(e.which == 32) {
    	$('.card').toggleClass('flipped');
    }
});