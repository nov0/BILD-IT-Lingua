/**
 * @Author Bojan Aleksic
 * Language Locale Internationalization
 * Change language within drop-down menu, using JS and
 * locale from cookies
 */

var lang = "";
var flag = "";

if(document.cookie.indexOf("LOCALE=sr") > -1) {
	lang = "Serbian";
	flag = "images/serbia-flag.png";
} else if(document.cookie.indexOf("LOCALE=en") > -1) {
	lang = "English";
	flag = "images/eng-flag.png";
} else if(document.cookie.indexOf("LOCALE=bs") > -1) {
	lang = "Bosnian";
	flag = "images/bosnia-flag.png";
} else if(document.cookie.indexOf("LOCALE=hr") > -1) {
	lang = "Croatian";
	flag = "images/croatia-flag.png";
} else {
	lang = "English";
	flag = "images/eng-flag.png";
}

$("#selected-language").css({"background": "url('" + flag + "') no-repeat", "background-position": "-2px 2px"});
$("#selected-language").text(lang);
