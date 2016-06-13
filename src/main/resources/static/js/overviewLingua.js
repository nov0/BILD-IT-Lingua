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
        var order = $("#input-order").val();
        
        if(speed != 0) {
        	$("#practice-lingua").load("fragments/overview-practice.html", {
                from : from,
                category : category,
                speed : speed
            }, function(response, status, xhr) {
            	if(status == "error") {
            		console.log("Error occurred");
            	} else {
            		console.log("success overview");
            	}
            });
        } else {
        	$("#practice-lingua").load("fragments/flipcard-practice.html", {
                from : from,
                category : category,
                order : order
            }, function(response, status, xhr) {
            	if(status == "error") {
            		console.log("Error occurred");
            	} else {
            		console.log("success flipcard");
            	}
            });
        }
        
        

    });

});
