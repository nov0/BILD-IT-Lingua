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

	$(".select-category li > a").click(function() {
		$(".category").text(this.innerHTML);
		selectedCategory = this.innerHTML;
		$(".selected-category").val(selectedCategory);
	});

	$('[data-toggle="tooltip"]').tooltip();

	// get ID value of active class
	var urlRequest = $('.btn.active').attr('id');

	/* When user is logged in, populate page with all tickets by default */
	if(urlRequest == "ticket-all") {
		$.getJSON(urlRequest, getTickets);
	}

	/* Get active class on click in button group, and send request to the controller */
	$('.btn-tickets').click(function() {

		$("#preloader").show();
		$(".ticket-container").hide();

		// set active class on clicked btn
		urlRequest = $('.btn.active').attr('id');
		$(this).addClass('active').siblings().removeClass('active');

		$.getJSON(urlRequest, getTickets);

	});

	/* Pass data-parsed object from the JSON server to the function */
	function getTickets(data) {

		$("#preloader").hide();
		$(".ticket-container").show();

		/* This is the parent div in which all content will be populated */
		$(".my-lingua-content").html("");

		/* Iterate through data object (list) */
		$.each(data.ticketsList, function(index, ticket) {

			var ticketClickable = document.createElement("a");
			ticketClickable.setAttribute("href", "#");
			ticketClickable.setAttribute("id", "ticket-edit");
			ticketClickable.setAttribute("data-toggle", "modal");
			ticketClickable.setAttribute("data-target", "#gridSystemModal2");

				var ticketContainerDiv = document.createElement("div");
				ticketContainerDiv.setAttribute("class", "ticket-container");

					var ticketHeaderDiv = document.createElement("div");
					ticketHeaderDiv.setAttribute("class", "row ticket-header");

						var dateCreatedDiv = document.createElement("div");
						dateCreatedDiv.setAttribute("class", "col-md-2");
							var glyphiconDateSpan = document.createElement("span");
							glyphiconDateSpan.setAttribute("class", "glyphicon glyphicon-time");
							var dateCreatedSpan = document.createElement("span");
							dateCreatedSpan.setAttribute("id", "date-created");
							dateCreatedSpan.appendChild(document.createTextNode(ticket.dateCreated));

							dateCreatedDiv.appendChild(glyphiconDateSpan);
							dateCreatedDiv.appendChild(dateCreatedSpan);

						var categoryDiv = document.createElement("div");
						categoryDiv.setAttribute("class", "col-md-6");
							var glyphiconCategorySpan = document.createElement("span");
							glyphiconCategorySpan.setAttribute("class", "glyphicon glyphicon-tag");
							var categoryTextSpan = document.createElement("span");
							categoryTextSpan.setAttribute("class", "category-ticket");
							categoryTextSpan.setAttribute("data-toggle", "tooltip");
							categoryTextSpan.setAttribute("title", "Category");
							categoryTextSpan.appendChild(document.createTextNode(ticket.category));

							categoryDiv.appendChild(glyphiconCategorySpan);
							categoryDiv.appendChild(categoryTextSpan);

						var likeDislikeDiv = document.createElement("div");
						likeDislikeDiv.setAttribute("class", "col-md-4 like-dislike-btn");
							var likeSpan = document.createElement("span");
							likeSpan.setAttribute("class", "glyphicon glyphicon-thumbs-up");
							likeSpan.setAttribute("data-toggle", "tooltip");
							likeSpan.setAttribute("title", "Like");
							likeDislikeDiv.appendChild(likeSpan);

							var likeSpanVotes = document.createElement("span");
							likeSpanVotes.setAttribute("class", "like-vote-value");
							likeSpanVotes.appendChild(document.createTextNode(ticket.votesUp[0].voteValue));
							likeDislikeDiv.appendChild(likeSpanVotes);

							var dislikeSpan = document.createElement("span");
							dislikeSpan.setAttribute("class", "glyphicon glyphicon-thumbs-down");
							dislikeSpan.setAttribute("data-toggle", "tooltip");
							dislikeSpan.setAttribute("title", "Dislike");
							likeDislikeDiv.appendChild(dislikeSpan);

							var dislikeSpanVotes = document.createElement("span");
							dislikeSpanVotes.appendChild(document.createTextNode(ticket.votesDown[0].voteValue));
							likeDislikeDiv.appendChild(dislikeSpanVotes);

						ticketHeaderDiv.appendChild(dateCreatedDiv);
						ticketHeaderDiv.appendChild(categoryDiv);
						ticketHeaderDiv.appendChild(likeDislikeDiv);

					ticketContainerDiv.appendChild(ticketHeaderDiv);

					var domesticForeignDiv = document.createElement("div");
					domesticForeignDiv.setAttribute("class", "row domestic-foreign-row");
					var domesticDiv = document.createElement("div");
					domesticDiv.setAttribute("id", "text-domestic");
					domesticDiv.setAttribute("class", "col-md-6 domestic-foreign-text");
					domesticDiv.appendChild(document.createTextNode(ticket.textDomestic));
					domesticForeignDiv.appendChild(domesticDiv);

					var foreignDiv = document.createElement("div");
					foreignDiv.setAttribute("id", "text-foreign");
					foreignDiv.setAttribute("class", "col-md-6 domestic-foreign-text");
					foreignDiv.appendChild(document.createTextNode(ticket.textForeign));
					domesticForeignDiv.appendChild(foreignDiv);

				ticketContainerDiv.appendChild(domesticForeignDiv);

// 				$(".my-lingua-content").append(ticketContainerDiv);
				ticketClickable.appendChild(ticketContainerDiv);
				$(".my-lingua-content").append(ticketClickable);

			});

		}

	});
