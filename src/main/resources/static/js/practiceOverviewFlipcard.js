/**
 * @author Bojan Aleksic
 * Load Tickets for Overview and Flipcard practice, using AJAX's load() method
 */

$(document).ready(function() {

    $("#start-practice-submit").click(function() {

        var from = $("#input-from").val();
        var category = $("#input-category").val();
        var speed = $("#slider").val();
        var order = $("#input-order").val();
        var millisec = 500;

        if(speed === "1") {
        	millisec = 3000;
		} else if(speed === "2") {
			millisec = 5000;
		} else if(speed === "3") {
			millisec = 10000;
		}

        if(speed !== "0") {
        	/* Load overview practice fragment with time interval on user's choice */
        	var timer = window.setInterval(function() {
        		$("#practice-lingua").load("fragments/overview-practice.html", {
        			from : from,
    		        category : category,
    		        speed : speed
    		    },
    			function(response, status, xhr) {
    		    	/* Initially read and set speed value based on practice-lingua properties */
    		    	$("#slider").attr("data-slider-value", speed);
    		    	
    		    	$(".slider-handle").on("click", function() {
    		    		console.log("handle slider clicked!");
    		    	});

    		    	/* Get size of the Stack List */
    		    	var stackSize = $("#stack-size").val();

		            if(status == "error") {
		            	console.log("Error occurred");
		            } else {
		            	console.log("speed: " + speed);
		            	console.log("millisec: " + millisec);
		            	/* Check if Stack List is empty, if true, terminate Interval */
		            	if(stackSize === "0") {
			            	clearInterval(timer);
			            }
		            }
		        });
    		}, millisec);
        } else {
        	$("#practice-lingua").load("fragments/flipcard-practice.html", {
                from : from,
                category : category,
                order : order
            }, 
            function(response, status, xhr) {
            	if(status == "error") {
            		console.log("Error occurred");
            	}
            });
        }
    });

});
