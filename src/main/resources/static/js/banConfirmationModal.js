/**
 * @author Bojan Aleksic
 * User's ban handling
 */

$(document).ready(function() {

	/* Obtain boolean values from the model and assign them to the variables */
	var entryBan = $("#is-adding-ban").val();
	var loginBan = $("#is-login-ban").val();
	var votingBan = $("#is-voting-ban").val();

	var imgBanAdding = '<img src="images/ban-icon.png" style="position: absolute; right: -5px; top: -5px;" />';
	var imgBanLogin = '<img src="images/ban-icon.png" style="position: absolute; right: -6px; top: -5px;" />';
	var imgBanVoting = '<img src="images/ban-icon.png" style="position: absolute; right: -1px; top: -5px;" />';

	/* Check if user is banned for adding new tickets, assign ban-class if so */
	if(entryBan === "true") {
		$("#new-entry-icon").html(imgBanAdding);
		$("#new-entry-icon").removeClass("ban-icons").addClass("ban-icons-red");
	}

	/* Check if user is banned for login, assign ban-class if so */
	if(loginBan === "true") {
		$("#login-ban-icon").html(imgBanLogin);
		$("#login-ban-icon").removeClass("ban-icons").addClass("ban-icons-red");
	}

	/* Check if user is banned for voting, assign ban-class if so */
	if(votingBan === "true") {
		$("#voting-ban-icon").html(imgBanVoting);
		$("#voting-ban-icon").removeClass("ban-icons").addClass("ban-icons-red");
	}

	/* Create pointer for glyphicons on hover */
	$(".glyph-hover").hover(function() {
		$(this).css("cursor", "pointer");
	});

	/* When user click on "New Entry" button... */
	$("#new-entry-icon").click(function() {
		/* ...set entryBan to true if it was previously false, otherwise do the opposite */
		if ($(this).hasClass("ban-icons")) {
			$(this).html(imgBanAdding);
			$(this).removeClass("ban-icons").addClass("ban-icons-red");
			entryBan = true;
		} else {
			$("#new-entry-icon img").remove();
			$(this).removeClass("ban-icons-red").addClass("ban-icons");
			entryBan = false;
		}
	});

	$("#login-ban-icon").click(function() {
		if ($(this).hasClass("ban-icons")) {
			$(this).html(imgBanLogin);
			$(this).removeClass("ban-icons").addClass("ban-icons-red");
			loginBan = true;
		} else {
			$("#login-ban-icon img").remove();
			$(this).removeClass("ban-icons-red").addClass("ban-icons");
			loginBan = false;
		}
	});

	$("#voting-ban-icon").click(function() {
		if ($(this).hasClass("ban-icons")) {
			$(this).html(imgBanVoting);
			$(this).removeClass("ban-icons").addClass("ban-icons-red");
			votingBan = true;
		} else {
			$("#voting-ban-icon img").remove();
			$(this).removeClass("ban-icons-red").addClass("ban-icons");
			votingBan = false;
		}
	});

	/* When submitting the ban button, send boolean values along with the user's ID to the controller
	 * and handle request further there */
	$("#ban-submit").click(function(event) {
		event.preventDefault();
		var userId = $("#ban-user-id").val();
		$.post("ban-submit", {
			id : userId,
			entryBan : entryBan,
			loginBan : loginBan,
			votingBan : votingBan
		})
		.done(function() {
			localStorage.setItem('user-ban-status-change', true);
			location.reload();
		});
		
		localStorage.setItem("tabId", $(".li-tickets.active").attr('id'));
	});

	$('.modal').on('hidden.bs.modal', function() {
		$(this).removeData();
	});

});