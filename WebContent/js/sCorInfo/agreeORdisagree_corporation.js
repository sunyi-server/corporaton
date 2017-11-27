/*
 * 同意该社团入驻
 */

var agree_corporation_id;

function agree_corporation() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				message = xhr.responseText;

				toastr.success("已同意");

				window.location = "/corporation/corInfo/corInfo_page_list_corInfo?page_list_corInfo.pageIndex=1&searchCorInfoList.sqrt=corporation_gmt_modified&searchCorInfoList.sqrt_sc=desc&searchCorInfoList.check=&searchCorInfoList.name=";

			} else {
				toastr.error(xhr.status);
				
			}
		}
	}

	var formData = new FormData();

	formData.append("corInfo.corporation_id", agree_corporation_id);

	xhr.open("POST", "/corporation/corInfo/corInfo_updatecorInfoStatusById");

	xhr.send(formData);

	

	$('#model_agree_corInfo').modal('hide');
}
/*
 * 不同意该社团入驻
 */
var disagree_corporation_id;
function disagree_corporation() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				message = xhr.responseText;

				toastr.success("已拒绝");

				window.location = "/corporation/corInfo/corInfo_page_list_corInfo?page_list_corInfo.pageIndex=1&searchCorInfoList.sqrt=corporation_gmt_modified&searchCorInfoList.sqrt_sc=desc&searchCorInfoList.check=&searchCorInfoList.name=";

			} else {
				toastr.error(xhr.status);
				
			}
		}
	}
	var input_message_content = document.getElementById("input_message_content");
	var formData = new FormData();

	formData.append("corInfo.corporation_id", disagree_corporation_id);
	formData.append("message.message_content", input_message_content.value);

	xhr.open("POST", "/corporation/corInfo/corInfo_saveCorMessage");

	xhr.send(formData);

	$('#model_disagree_corInfo').modal('hide');
}

/*
*
*/