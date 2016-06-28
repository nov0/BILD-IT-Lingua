/**
 * @author Novislav Sekulic
 * 
 */
$(document).ready(function() {

	// setting default learning language (foreign) to English
	$("select#foreign").find("option:contains('English')").attr("selected", true);
		// calling function to disable fields
		disableSelect();
		
		$("select").change(function() {
			disableSelect();
		}); // end change

		/**
		 * Function for disabling select fields. You can't select same
		 * language in domestic and foreign select option. When you select
		 * language option in one field another option is instantly
		 * disabled.
		 */
		function disableSelect() {
			var domestic = $("select#domestic").val();
			var foreign = $("select#foreign").val();
			
			// removing all disabled option form domestic selection
			$("select#domestic").find("option:disabled").removeAttr("disabled");
			// disabling language option that currently selected in another select option
			$("select#domestic").find("option:contains(" + foreign + ")").attr("disabled", true);

			$("select#foreign").find("option:disabled").removeAttr("disabled");
			$("select#foreign").find("option:contains(" + domestic + ")").attr("disabled", true);
		} // end disableSelect
}); // end ready
