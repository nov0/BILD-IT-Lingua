/**
 * @author Bojan Aleksic
 * Load Tickets for Overview and Flipcard practice, using AJAX's load() method
 */

$(document).ready(function() {

    $("#start-practice-submit").click(function() {

    	/* Set item for practice to check later if practice is aborted
    	 * or left before it ends */
    	localStorage.setItem("practiceStarted", true);

        var from = $("#input-from").val();
        var category = $("#input-category").val();
        var speed = $("#slider").val();
        var order = $("#input-order").val();
        var millisec;
        var currentSliderValue;
        window.userHasTickets = "";

	    var stackSize;

        var scrollSpeed = Number(speed);
        // Control variable for enabling and disabling keyboard shortcuts slider control.
        // This variable excludes RIGHT and LEFT arrow key shortcut on flipcard practice.
        var overviewPractice = false;

        var message = "";
        var color = 'success';
    	var icon = 'glyphicon glyphicon-ok';

        var colorError = 'danger';
    	var iconError = 'glyphicon glyphicon-warning-sign';

        if(speed === "1") {
	        millisec = 5000;
		} else if(speed === "2") {
			millisec = 10000;
		} else if(speed === "3") {
			millisec = 15000;
		}

        /* Check if logged user has tickets if he select "from = me",
         * and if there is tickets available in specific category by specific language 
         * if he select "everyone" */
        if(from === "me" || from == "everyone") {
        	$.ajax({
        		url : "user-has-tickets",
        		type : "GET",
        		data : { 
        			from : from,
        			category : category
        		},
        		async : false,
        		success : function(response) {
        			window.userHasTickets = response;
        		}
        	});
        }
        
        if(window.userHasTickets === "me-category-all-language=false"
        	|| window.userHasTickets === "everyone-category-all-language=false") {
        	message = /*[[#{slider.speed.message.one}]]*/ "No tickets for specified language available.";
    		showNotification(message, colorError, iconError);
        } else if(window.userHasTickets === "me-specified-category-language=false"
        	|| window.userHasTickets === "everyone-specified-category-language=false") {
        	message = /*[[#{slider.speed.message.one}]]*/ "No tickets for specified category in this language available";
    		showNotification(message, colorError, iconError);
        } else {
        	if(speed != 0) {
        		loadOverview();
        	} else {
        		loadFlipcard();
        	}
        }

        /*
         * @author Novislav Sekulic
         * Method for changing speed of practice via keyboard shortcuts.
         * LEFT arrow key is to slow down by 5s,
         * RIGHT arrof key is for speed up by 5s.
         */
        $(window).keydown(function(e) {
        	if(overviewPractice) {
        		// if LEFT arrow is pressed, slow down by 5s
        		if((e.keyCode || e.which) == 37) {
        			if(scrollSpeed >= 2) {
        				scrollSpeed--;
        				currentSliderValue = scrollSpeed.toString();
        				changeSpeed(currentSliderValue);
        			}

        		// if RIGHT arrow is pressed, speed up for 5s
        		} else if((e.keyCode || e.which) == 39) {
        			if(scrollSpeed <= 2) {
        				scrollSpeed++;
        				currentSliderValue = scrollSpeed.toString();
        				changeSpeed(currentSliderValue);
        			}
        		}
        	}
		}); // end keydown
		
        /* Method for changing speed of practice. */
		function changeSpeed(sliderValue) {
			if(sliderValue === "1") {
		        millisec = 5000;
		        message = /*[[#{slider.speed.message.one}]]*/ "Slider speed changed to 5 seconds.";
		        showNotification(message, color, icon);
			} else if(sliderValue === "2") {
				millisec = 10000;
				message = /*[[#{slider.speed.message.two}]]*/ "Slider speed changed to 10 seconds.";
		        showNotification(message, color, icon);
			} else if(sliderValue === "3") {
				millisec = 15000;
				message = /*[[#{slider.speed.message.three}]]*/ "Slider speed changed to 15 seconds.";
		        showNotification(message, color, icon);
			}
	    }

        /* Overview function */
        function loadOverview() {
        	// Enabling keyboard shortcuts slider control.
        	overviewPractice = true;
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
    				scrollSpeed = Number(currentSliderValue);
    				changeSpeed(currentSliderValue);
    			});
    			
	            if(currentSliderValue != undefined) {
	            	/* Change slider ticker value based on user's click */
	    		    $("#slider").attr("data-slider-value", currentSliderValue);
	            }

	            /* Get size of the Stack List */
    		    stackSize = $("#stack-size").val();

	            console.log("speed: " + speed);
	            console.log("m/s: " + millisec);
	            console.log("stack size: " + stackSize);

	            /* Set interval */
	            var timeout = setTimeout(loadOverview, millisec);

	            /* Check if Stack List is empty, if true, terminate Interval */
	            if(stackSize === "0") {
	            	clearTimeout(timeout);
	            	practiceIsOver();
		        }
    		});
        }

        /* Flipcard function */
        function loadFlipcard() {
        	// Disableing keyboard shortcuts slider control.
        	overviewPractice = false;
        	$("#practice-lingua").load("fragments/flipcard-practice.html", {
                from : from,
                category : category,
                order : order
            }, 
            function(response, status, xhr) {
            	if(status == "error") {
            		console.log("Error occurred");
            	}
            	stackSize = $("#stack-size").val();
            	console.log("stack size in flipcard: " + stackSize);
            	if(stackSize === "0") {
            		practiceIsOver();
            	}
            });
        }

        /* Function for letting the user know that practice is over */
        function practiceIsOver() {
        	setTimeout(function() {
        		$("#practice-over-modal").modal();
        	}, millisec);
        }

    });
});
