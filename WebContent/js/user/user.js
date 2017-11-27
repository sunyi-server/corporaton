window.onload = start_sqrt;
var sqrt;
var sqrt_sc;

function start_sqrt() {
	var span_sqrt = document.getElementById("span_sqrt");
	var span_sqrt_sc = document.getElementById("span_sqrt_sc");

	if (span_sqrt.innerHTML == "") {
		sqrt = "corporation_gmt_create";
		sqrt_sc = "asc";
	} else {

		sqrt = span_sqrt.innerHTML;
		sqrt_sc = span_sqrt_sc.innerHTML;
	}

}

function click_sqrt(click_div) {
	if (sqrt_sc == "desc") {
		sqrt_sc = "asc";
	} else {
		sqrt_sc = "desc";
	}
	sqrt = click_div.id;
	search_corInfo();
}

function search_corInfo(mypage) {
	var page = 1;
	if (mypage != null) {
		page = mypage;

	}
	var url = "/corporation/user_page_list_userInfo?page_list_userInfo.pageIndex=" + page;

	window.location = url;

}