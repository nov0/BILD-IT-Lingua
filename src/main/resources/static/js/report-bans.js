/**
 * @author Mladen Todorovic
 * @author Novislav Sekulic
 */
$(document).ready (function() {
	
	$(".ban-user").click(function() {
		
		$(this).addClass('active').siblings().removeClass('active');
		var activeId = $('.ban-user.active').attr('id');
		$("#banned-users-id").attr("value", activeId);
		
	});
	
	$("#download-banned-users").click(function(event) {
		event.preventDefault();
		$("#form-banned-users").submit();
		showMessageDownloadReport();
		
	});
	
	$(".dropdown-language-select").click(function() {
		$(".selected-language-users").text(this.innerHTML);
		$("#input-language-users").attr("value", this.innerHTML);
	}); // end click .dropdown-language-select
	
	$("#download-users").click(function(evnt) {
		evnt.preventDefault();
		$("#form-users").submit();
		showMessageDownloadReport();
	}); // end click #download-users
	
});