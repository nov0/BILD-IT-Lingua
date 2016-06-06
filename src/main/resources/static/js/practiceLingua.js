/**
 * Practice Lingua script
 */
$(document).ready(function() {
    /*use this as default*/
	$("#input-from").val("me");
	$("#input-order").val(null);
	
	/*overview button*/
    $("#overview-button").click(function() {
        $(this).addClass("active");
        $("#flipcard-button").removeClass("active");
        $("#order").addClass("hide");
        $("#speed").removeClass("hide");
        $("#input-order").val(null);
        $("#slider").val("3");
    });
    /*flipcard button*/
    $("#flipcard-button").click(function() {
        $(this).addClass("active");
        $("#overview-button").removeClass("active");
        $("#speed").addClass("hide");
        $("#order").removeClass("hide");
        $("#slider").val(null);
        $("#input-order").val("domestic");
    });
    
    /*me button*/
    $("#me-button").click(function() {
        $(this).addClass("active");
        $("#everyone-button").removeClass("active");
        $("#input-from").val("me");
    });
    /*everyone button*/
    $("#everyone-button").click(function() {
        $(this).addClass("active");
        $("#me-button").removeClass("active");
        $("#input-from").val("everyone");
    }); 
    
    /*domestic button*/
    $("#domestic-button").click(function() {
        $(this).addClass("active");
        $("#foreign-button").removeClass("active");
        $("#input-order").val("domestic");
    }); 
    /*foreign button*/
    $("#foreign-button").click(function() {
        $(this).addClass("active");
        $("#domestic-button").removeClass("active");
        $("#input-order").val("foreign");
    });
    
    /***** Select category by Bojan Aleksic ******/ 
    var selectedCategory = "";
	$(".select-category li > a").click(function() {
		$(".category").text(this.innerHTML);
		selectedCategory = this.innerHTML;
		$("#input-category").val(selectedCategory);
	});
    /*********** Select category end *************/
});