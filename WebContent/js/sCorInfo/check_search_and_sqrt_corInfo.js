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

function search_corInfo() {
	var input_name = document.getElementById("input_name");
	var select_corporation_status = document.getElementById("select_corporation_status");

	var url = "corInfo/corInfo_page_list_checkCorInfo?searchCorInfoList.check=0&page_list_corInfo.pageIndex=1&searchCorInfoList.sqrt="
	+ sqrt
	+ "&searchCorInfoList.sqrt_sc="
	+ sqrt_sc
	+ "&searchCorInfoList.name="
	+ input_name.value;

	window.location = url;

}