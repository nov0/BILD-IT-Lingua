/**
 * @author Bojan Aleksic
 * Load Tickets for Overview practice using AJAX's load() method
 */

$(document).ready(function() {

    $("#start-practice-submit").click(function() {

    	$("#practice-form").hide();
    	
        var from = $("#input-from").val();
        var category = $("#input-category").val();
        var speed = $("#slider").val();

        console.log("from: " + from);
        console.log("category: " + category);
        console.log("speed: " + speed);
        
        $("#practice-lingua").load("fragments/overview-practice.html", {
            from : from,
            category : category,
            speed : speed
        }, function(response, status, xhr) {
        	if(status == "error") {
        		console.log("Error occurred");
        	} else {
//        		var obj = $.parseJSON(response);
        		console.log("success");
        	}
        });

//        $.getJSON("practice", {
//        	from : from,
//        	category : category,
//        	speed : speed
//        })
//        .done(function(response) {
//        	$("#practice-lingua").html("");
//        	var frag = document.createElement("div");
//        	frag.setAttribute("th:include", "fragments/overview-practice :: overview");
//        	$("#practice-lingua").append(frag);
//        	var tickets = [];
//        	$.each(response.tickets, function(index, ticket) {
//        		tickets.push(ticket);
//        	});
//        	$("#text-domestic").text(tickets[0].textDomestic);
//        })
//        .fail(function() {
//        	console.log("fail");
//        });

    });

});
