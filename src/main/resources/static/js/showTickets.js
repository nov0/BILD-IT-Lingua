/**
 * @author Bojan Aleksic
 */

$(document).ready(function() {

    $("#preloader-language").hide();

    $(document).ajaxStart(function() {
        $("#preloader").show();
    });

 	$(document).ajaxComplete(function() {
 	    $("#preloader").hide();
 	});

 	// get ID value of active class
	var urlRequest = $('.btn.active').attr('id');

	/* When user is logged in, populate page with all tickets by default */
	if(urlRequest == "ticket-all") {
		$(".tickets-content").load("fragments/get-tickets.html", { "urlData" : urlRequest });
	}

	/* Load tickets to the tickets-content selector, using AJAX's load() method */
	$(".btn-tickets").click(function() {
		$("#preloader").show();
//		$(".ticket-container").hide();
		$(this).addClass('active').siblings().removeClass('active');
		urlRequest = $('.btn.active').attr('id');
		$(".tickets-content").load("fragments/get-tickets.html", { "urlData" : urlRequest });
	});

});
