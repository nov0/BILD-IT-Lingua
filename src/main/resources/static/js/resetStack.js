/**
 * @author Bojan Aleksic
 * Check if localStorage for practiceStarted has been set,
 * if so, send AJAX's request to clear the Stack inside the PracticeController.
 */

$(document).ready(function() {

	if(localStorage.getItem("practiceStarted")) {
		$.get("reset-practice")
		.fail(function() {
			console.log("Reset practice failed.");
		});
		localStorage.clear();
	}

});