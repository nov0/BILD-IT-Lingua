/**
 * @author Bojan Aleksic
 */

$(document).ready(function() {

	var entryBan;
	var loginBan;
	var votingBan;

	$(".glyph-hover").hover(function() {
		$(this).css("cursor", "pointer");
	});

	$("#new-entry-icon").click(function() {
		if ($(this).hasClass("ban-icons")) {
			$(this).removeClass("ban-icons").addClass("ban-icons-red");
			entryBan = true;
		} else {
			$(this).removeClass("ban-icons-red").addClass("ban-icons");
			entryBan = false;
		}
	});

	$("#login-ban-icon").click(function() {
		if ($(this).hasClass("ban-icons")) {
			$(this).removeClass("ban-icons").addClass("ban-icons-red");
			loginBan = true;
		} else {
			$(this).removeClass("ban-icons-red").addClass("ban-icons");
			loginBan = false;
		}
	});

	$("#voting-ban-icon").click(function() {
		if ($(this).hasClass("ban-icons")) {
			$(this).removeClass("ban-icons").addClass("ban-icons-red");
			votingBan = true;
		} else {
			$(this).removeClass("ban-icons-red").addClass("ban-icons");
			votingBan = false;
		}
	});

	$("#ban-submit").click(function(event) {
		event.preventDefault();
		var userId = $("#ban-user-id").val();
		$.post("ban-submit", {
			id : userId,
			entryBan : entryBan,
			loginBan : loginBan,
			votingBan : votingBan
		});
	});

});