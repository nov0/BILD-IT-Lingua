/**
 * @author Bojan Aleksic
 * Select category function
 */

$(".select-category li > a").click(function() {
    $(".category").text(this.innerHTML);
    $(".selected-category").val(this.innerHTML);
});