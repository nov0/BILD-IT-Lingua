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
        var millisec;
        var currentSliderValue;

        var message = "";
        var color = 'success';
    	var icon = 'glyphicon glyphicon-ok';

        if(speed === "1") {
	        millisec = 3000;
		} else if(speed === "2") {
			millisec = 5000;
		} else if(speed === "3") {
			millisec = 10000;
		}

        if(speed != 0) {
        	loadOverview();
        } else {
        	loadFlipcard();
        }

        /* Overview function */
        function loadOverview() {
        	/* Load overview practice fragment with time interval on user's choice */
        	$("#practice-lingua").load("fragments/overview-practice.html", {
        		from : from,
    		    category : category,
    		    speed : speed
    		},
    		function(response, status, xhr) {

    			if(status == "error") {
    				console.log("Error occurred");
		        }

    			/* Initially read and set speed value based on practice-lingua properties */
    		    var slider = $("#slider").attr("data-slider-value", speed);

    			/* Change slider speed */
    			$("#slider-ticker").click(function() {
    				currentSliderValue = $(".slider-handle").attr("aria-valuenow");
    				if(currentSliderValue === "1") {
    			        millisec = 3000;
    			        message = /*[[#{slider.speed.message.one}]]*/ "Slider speed changed to 3 seconds.";
    			        showNotification(message, color, icon);
    				} else if(currentSliderValue === "2") {
    					millisec = 5000;
    					message = /*[[#{slider.speed.message.two}]]*/ "Slider speed changed to 5 seconds.";
    			        showNotification(message, color, icon);
    				} else if(currentSliderValue === "3") {
    					millisec = 10000;
    					message = /*[[#{slider.speed.message.three}]]*/ "Slider speed changed to 10 seconds.";
    			        showNotification(message, color, icon);
    				}
    		    });

	            if(currentSliderValue != undefined) {
	            	/* Change slider ticker value based on user's click */
	    		    $("#slider").attr("data-slider-value", currentSliderValue);
	            }

    		    /* Get size of the Stack List */
    		    var stackSize = $("#stack-size").val();

	            console.log("speed: " + speed);
	            console.log("m/s: " + millisec);
	            console.log("stack size: " + stackSize);

	            /* Set interval */
	            var timeout = setTimeout(loadOverview, millisec);

	            /* Check if Stack List is empty, if true, terminate Interval */
	            if(stackSize === "0") {
	            	clearTimeout(timeout);
		        }
    		});
        }

        /* Flipcard function */
        function loadFlipcard() {
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
