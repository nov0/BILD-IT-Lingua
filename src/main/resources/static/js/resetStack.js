/**
 * @author Bojan Aleksic
 */

$(document).ready(function() {
	
	if(localStorage.getItem("practiceStarted")) {
		$.post("reset-practice");
	}
	
});