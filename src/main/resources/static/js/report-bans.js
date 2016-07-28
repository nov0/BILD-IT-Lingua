/**
 * @author Mladen Todorovic
 */
$(document).ready (function() {
	
	$(".btn-primary").click(function() {
		
		$(this).addClass('active').siblings().removeClass('active');
		var activeId = $('.btn.active').attr('id');
		$("#banned-users-id").attr("value", activeId);
		
	});
	
	$("#download-banned-users").click(function(event) {
		
		event.preventDefault();
		var message = /*[[#{report.downloaded}]]*/ 'Report successfully downloaded.';
		$("#form-banned-users").submit();
		showNotification(message, "success", "glyphicon glyphicon-ok");
		
	});
	
});