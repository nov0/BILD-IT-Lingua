/**
 * @author Goran Arsenic
 * 
 */
$(document).ready(function() {
	function flipCard() {
		$('.card').toggleClass('flipped');
	}
	$("#button-flip").click(function() {
		flipCard();
		$(this).blur();
	});
	
	$(document).keypress(function(e) {
		console.log("spaceee");
		if ((e.keyCode || e.which) == 32) {
			flipCard();
			
		} 
	});
});



