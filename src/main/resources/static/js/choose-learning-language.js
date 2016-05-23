/**
 * @author Bojan Aleksic
 * Select learning language inside Lingua ticket
 * and category
 */

$(document).ready(function() {
	$(".select-language li > a").click(function() {
        $(".select-practice-lang").text(this.innerHTML);
    });

	$(".select-category li > a").click(function() {
	    $(".category").text(this.innerHTML);
	});
});
