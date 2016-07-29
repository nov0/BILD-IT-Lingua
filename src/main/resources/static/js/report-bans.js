/**
 * @author Mladen Todorovic
 */
$(document).ready (function() {
	
	$(".ban-user").click(function() {
		
		$(this).addClass('active').siblings().removeClass('active');
		var activeId = $('.ban-user.active').attr('id');
		$("#banned-users-id").attr("value", activeId);
		
	});
	
	$("#download-banned-users").click(function(event) {
		
		event.preventDefault();
		var message = /*[[#{report.downloaded}]]*/ 'Report successfully downloaded.';
		$("#form-banned-users").submit();
		showNotification(message, "success", "glyphicon glyphicon-ok");
		
	});
	
});