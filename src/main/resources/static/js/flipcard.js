/**
 * @author Goran Arsenic
 * @edited Novislav Sekulic
 * 
 */
$(document).ready(function() {
	$("#button-flip").click(function() {
		flipCard();
		$(this).blur();
	}); // end click
	
	$(window).keydown(function(e) {
		
		// if SPACE key pressed, flip card
		if ((e.keyCode || e.which) == 32) {
			flipCard();
		// if ENTER key is pressed, go to next card
		} else if ((e.keyCode || e.which) == 13) {
			nextTicket();
		// if UP arrow is pressed, vote up
		} else if((e.keyCode || e.which) == 38) {
			$("#ticket-vote-up").click();
		// if DOWN arrow is pressed, vote down
		} else if((e.keyCode || e.which) == 40) {
			$("#ticket-vote-down").click();
		}
	}); // end keydown
	
	function flipCard() {
		$('.card').toggleClass('flipped');
	}
});
