/**
 * @author Bojan Aleksic
 * Change/set learning language
 */

$(".select-language li > a").click(function() {
    var languageTitle = this.innerHTML;
	$.ajax({
		url: 'set-foreign-language',
		type: "POST",
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		dataType: "json",
		data: languageTitle,
		success: function(response) {
			$(".select-practice-lang").text(response.languageTitle);
		}
	});
});