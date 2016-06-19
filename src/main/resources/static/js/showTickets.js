/**
 * @author Bojan Aleksic
 * Load tickets with Infinitive Scroll design
 */

$(document).ready(function() {

    $("#preloader-language").hide();

	/* Select category */
	$(".select-category li > a").click(function() {
		$(".category").text(this.innerHTML);
		$(".selected-category").val($(this).attr("id"));
	});

	// get ID value of active class
	var urlRequest = $('.btn.active').attr('id');
	
	window.totalPages = "";

	/* 
	 * When user is logged in, populate page with all tickets by default 
	 * Edit: Infinite Scroll - implemented
	 */
	if(urlRequest === "ticket-all") {
		var page = 0;
		loadTicketsInitially(page);
		page++;
		/* Invoke this function every time user scrolls */
		$(window).scroll(function() {
			var scrollTop = $(window).scrollTop();
			var docHeight = $(document).height();
			var winHeight = $(window).height();
			/* If end of the document is reached... */
			if(scrollTop >= (docHeight - winHeight)) {
				$("#preloader").show();
				if(page < window.totalPages) {
					loadTicketsWithScroll(page);
					page++;
				}
				$("#preloader").hide();
			}
		});
	}

	/* Load tickets to the tickets-content selector, using AJAX's load() method */
	$(".btn-tickets").click(function() {
		$("#preloader").show();
		$(this).addClass('active').siblings().removeClass('active');
		urlRequest = $('.btn.active').attr('id');
		$(".tickets-content").load("fragments/get-tickets.html", { 
			"urlData" : urlRequest 
		});
	});
	
	/* Function for loading tickets initially without scrolling */
	function loadTicketsInitially(page) {
		$(".tickets-content").load("fragments/get-tickets.html", { 
			"urlData" : urlRequest,
			"page" : page
		},
		function(response, status, xhr) {
			if(status == "error") {
				console.log("Error occurred.");
			}
			/* Retrieve number of pages from the model */
			window.totalPages = $("#total-pages").val();
		});
	}

	/* Function for loading tickets while scrolling */
	function loadTicketsWithScroll(page) {
		/* Avoid replacing the same content all over again */
		$(".tickets-wrapper").append($("<div>").load("fragments/get-tickets.html .ticket-container", { 
			"urlData" : urlRequest,
			"page" : page
		},
		function(response, status, xhr) {
			if(status == "error") {
				console.log("Error occurred.");
			}
		}));
	}

});
