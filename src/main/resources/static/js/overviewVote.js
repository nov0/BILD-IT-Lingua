/**
 * @author Bojan Aleksic
 * Vote rating handler
 */

$(document).ready(function() {

	var message = "";
	var colorError = "danger";
	var iconError = "glyphicon glyphicon-warning-sign";
	var colorSuccess = 'success';
	var iconSuccess = 'glyphicon glyphicon-ok';

	/* Likes */
	$("#ticket-vote-up").click(function() {
		var id = $("#ticket-id-overview").val();
		$.post("add-like", {
			id : id
		})
		.done(function(response) {
			if(response === "already-voted") {
				message = " You have already submitted your vote for this ticket.";
				showNotification(message, colorError, iconError);
			} else if(response === "your-own-ticket") {
				message = " You can't vote on your own ticket.";
				showNotification(message, colorError, iconError);
			} else {
				$("#likes-value").text(response);
				message = " You successfully voted.";
				showNotification(message, colorSuccess, iconSuccess);
			}
		});
	});

	/* Dislikes */
	$("#ticket-vote-down").click(function() {
		var id = $("#ticket-id-overview").val();
		$.post("add-dislike", {
			id : id
		})
		.done(function(response) {
			if(response === "already-voted") {
				message = " You have already submitted your vote for this ticket.";
				showNotification(message, colorError, iconError);
			} else if(response === "your-own-ticket") {
				message = " You can't vote on your own ticket.";
				showNotification(message, colorError, iconError);
			} else {
				$("#dislikes-value").text(response);
				message = " You successfully voted.";
				showNotification(message, colorSuccess, iconSuccess);
			}
		});
	});

});