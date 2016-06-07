/**
 * @author Bojan Aleksic
 */

$(document).ready(function() {

//	$("#preloader-language").hide();

// 	$(document).ajaxStart(function() {
// 		$("#preloader").show();
// 	});

// 	$(document).ajaxComplete(function() {
// 	    $("#preloader").hide();
// 	});

	/* Set/change language */
	$(".select-language li > a").click(function() {

		var languageTitle = this.innerHTML;

		$.ajax({
			url: 'set-foreign-language',
			type: "POST",
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			dataType: "json",
			data: languageTitle,
			success: function(response) {
				$(".select-practice-lang").text(response.languageTitle);
			}
		});
	});

	var selectedCategory = "";

	/* Select category */
	$(".select-category li > a").click(function() {
		$(".category").text(this.innerHTML);
		selectedCategory = this.innerHTML;
		$(".selected-category").val(selectedCategory);
	});

	// get ID value of active class
//	var urlRequest = $('.btn.active').attr('id');

	/* When user is logged in, populate page with all tickets by default */
//	if(urlRequest == "ticket-all") {
//		$.getJSON(urlRequest, getTickets);
//	}

	/* Get active class on click in button group, and send request to the controller */
//	$('.btn-tickets').click(function() {
//
//		$("#preloader").show();
//		$(".ticket-container").hide();
//
//		// set active class on clicked btn
//		$(this).addClass('active').siblings().removeClass('active');
//		urlRequest = $('.btn.active').attr('id');
//
//		$.getJSON(urlRequest, getTickets);
//
//	});

	/* Pass data-parsed object from the JSON server to the function */
	function getTickets(data) {
		$("#preloader").hide();
		$(".ticket-container").show();

		/* Iterate through data object (list) */
		$.each(data.ticketsList, function(index, ticket) {

				if(urlRequest === "ticket-deleted") {
					/* Set deleted tickets to red color (Bootstrap's alert-danger style) */
					$('.ticket-container').addClass('alert-danger').siblings().removeClass('alert-warning');
				}

				$('[data-toggle="tooltip"]').tooltip();

				editTicket(ticket.id, ticket.textDomestic, ticket.textForeign, ticket.category);

			});

		}

		/* Get clicked ticket for edit by ID */
		function editTicket(ticketId, textDomestic, textForeign, category) {
			$(".ticket-edit").click(function() {
				var id = $(this).attr("id");
				if(ticketId == id) {
					$(".domestic #domestic").text(textDomestic);
					$(".foreign #foreign").text(textForeign);
					$(".category").text(category);
					document.getElementById("ticket-id-hidden").value = id;
				}
			});
		}

		/* Load tickets to the tickets-content selector, using AJAX's load() method */
		$(".btn-tickets").click(function() {
			$(this).addClass('active').siblings().removeClass('active');
			var urlRequest = $('.btn.active').attr('id');
			$(".tickets-content").load("fragments/get-tickets.html", { "urlData" : urlRequest });
		});

	});
