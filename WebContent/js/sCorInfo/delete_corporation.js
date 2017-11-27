var delete_corporation_id;

function delete_corporation() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				message = xhr.responseText;

				toastr.success("删除成功");

				window.location = "/corporation/corInfo/corInfo_page_list_corInfo?page_list_corInfo.pageIndex=1";

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	var formData = new FormData();

	formData.append("corInfo.corporation_id", delete_corporation_id);

	xhr.open("POST", "/corporation/corInfo/corInfo_deleteCorInfo");

	xhr.send(formData);

	

	$('#model_delete_corInfo').modal('hide');
}

var delete_message_id;

function delete_message() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				message = xhr.responseText;

				toastr.success("删除成功");

				window.location = "/corporation/corInfo/corInfo_page_list_message?page_list_corInfo.pageIndex=1";

			} else {
				toastr.error(xhr.status);
			}
		}
	}

	var formData = new FormData();

	formData.append("message.message_id", delete_message_id);

	xhr.open("POST", "/corporation/corInfo/corInfo_deleteMessage");

	xhr.send(formData);

	$('#model_delete_message').modal('hide');
}