
// --------------------------------------------------------
function showCorporationLogo(img) {

	var seeButton = img.value;

	showImg("/corporation/corInfo/corInfo_showCorporaionLogo", seeButton);

}
function showImg(url, seeButton) {
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		httpUpload = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		httpUpload = new ActiveXObject("Microsoft.XMLHTTP");
	}
	httpUpload.open("POST", url, true);
	httpUpload.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded');

	var data = "corporation_id=" + seeButton;
	httpUpload.send(data);
	httpUpload.onreadystatechange = httpAddBack;
}
function httpAddBack() {
	if (httpUpload.readyState == 4 && httpUpload.status == 200) {
		var result = httpUpload.responseText;
		result = JSON.parse(result);

		document.getElementById("Show_Corporation_Logo").setAttribute(
				"src",
				"corInfo_getCorporationLogo?imgName="
						+ result.corporation_logo);

	}
}