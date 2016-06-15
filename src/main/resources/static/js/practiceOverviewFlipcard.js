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
        var url = "";
        var speedOrOrder = "";

        if(speed != 0) {
        	url = "fragments/overview-practice.html";
        	speedOrOrder = speed;
        } else {
        	url = "fragments/flipcard-practice.html";
        	speedOrOrder = order;
        }
        
        $("#practice-lingua").load(url, {
            from : from,
            category : category,
            speedOrOrder : speedOrOrder
        }, 
        function(response, status, xhr) {
        	if(status == "error") {
        		console.log("Error occurred");
        	}
        });

    });

});
