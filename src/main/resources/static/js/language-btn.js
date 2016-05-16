var lang = location.search;
var flag = "";
if(lang === "?lang=en") {
	lang = "English";
	flag = "images/eng-flag.png";
} else if(lang === "?lang=sr") {
	lang = "Serbian";
	flag = "images/serbia-flag.png";
} else if(lang !== "?lang=sr" || lang !== "?lang=en") {
	lang = "";
}
$("#chosen-lang").css({"background": "url('" + flag + "') no-repeat", "background-position": "-2px 2px"});
$("#chosen-lang").text(lang);