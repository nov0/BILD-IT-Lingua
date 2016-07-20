/** 
 * @author Goran Arsenic
 */

$(document).ready(function(){

	var userId = 2;//$("user-id").val();
	var message = "";
	var icon = "glyphicon glyphicon-warning-sign";
	
	/* unban add new entry*/
	$("#ban-add-new-yes").dblclick(function() {
		$("#ban-add-new-yes").addClass("hide");
		newEntryBan();
	});
	/* ban add new entry*/
	$("#ban-add-new").dblclick(function() {
		$("#ban-add-new-yes").removeClass("hide");
		newEntryBan();
	});
	/* unban login */
	$("#ban-login-yes").dblclick(function() {
		$("#ban-login-yes").addClass("hide");
		loginBan();
	});
	/* ban login */
	$("#ban-login").dblclick(function() {
		$("#ban-login-yes").removeClass("hide");
		loginBan();
	});
	/* unban vote */
	$("#ban-vote-yes").dblclick(function() {
		$("#ban-vote-yes").addClass("hide");
		voteBan();
	});
	/* ban vote */
	$("#ban-vote").dblclick(function() {
		$("#ban-vote-yes").removeClass("hide");
		voteBan();
	});
	
	function newEntryBan() {
		$.post("new-entry-ban", {
			userId : userId
		})
		.done(function(response) {
			if(response) {
				message = "User is successfuly baned - Add new entey";
				showNotification(message, colorRed, icon);
			} else {
				message = "User is successfuly unbaned - Add new entey";
				showNotification(message, colorGreen, iconOk);
			}
		});
	};
	function loginBan() {
		$.post("login-ban", {
			userId : userId
		})
		.done(function(response) {
			if(response) {
				message = "User is successfuly baned - Login";
				showNotification(message, colorRed, icon);
			} else {
				message = "User is successfuly unbaned - Login";
				showNotification(message, colorGreen, iconOk);
			}
		});
	};
	function voteBan() {
		$.post("vote-ban", {
			userId : userId
		})
		.done(function(response) {
			if(response) {
				message = "User is successfuly baned - Vote";
				showNotification(message, colorRed, icon);
			} else {
				message = "User is successfuly unbaned - Vote";
				showNotification(message, colorGreen, iconOk);
			}
		});
	};
});