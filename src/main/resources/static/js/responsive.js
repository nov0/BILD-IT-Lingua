/**
 * @author Goran Arsenic
 */
$( document ).ready(function() {
	
       function showSize() {
       var width = $(window).width();
        
       if(width < 770) {
           hideButtons();
       }else {
           showButtons();
           hideMenu();
       }
     
        }
    
   function hideButtons() {
        $(".login-header").hide();
        $("#menuSign").show();
    }
    function showButtons() {
        $(".login-header").show();
        $("#menuSign").hide();
    }
    
    $("#plus").click(showMenu);
    $("#minus").click(hideMenu);
    
    function showMenu() {
        $("#menu").show();
        $("#plus").hide();
        $("#minus").show();
    }
    function hideMenu() {
        $("#menu").hide();
        $("#plus").show();
        $("#minus").hide();
    }
    $(document).ready(showSize);
    $(window).resize(showSize);

});
