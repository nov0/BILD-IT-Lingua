/**
 * @Author Bojan Aleksic
 * Language Internationalization
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
} else if(document.cookie.indexOf("LOCALE=en") < 0 || document.cookie.indexOf("LOCALE=sr") < 0) {
	lang = "English";
	flag = "images/eng-flag.png";
}

$("#chosen-lang").css({"background": "url('" + flag + "') no-repeat", "background-position": "-2px 2px"});
$("#chosen-lang").text(lang);
