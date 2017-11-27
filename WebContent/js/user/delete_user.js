var delete_user_id;

function delete_user() {
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				message = xhr.responseText;

			

				window.location = "/corporation/user_page_list_userInfo?page_list_userInfo.pageIndex=1";

			} else {
				toastr.error(xhr.status);
				stop_load();
			}
		}
	}

	var formData = new FormData();

	formData.append("user_id", delete_user_id);
	/*alert(delete_user_id);*/
	xhr.open("POST", "/corporation/user_delete");

	xhr.send(formData);

	

	$('#model_delete_userInfo').modal('hide');
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
				stop_load();
			}
		}
	}

	var formData = new FormData();

	formData.append("message.message_id", delete_message_id);

	xhr.open("POST", "/corporation/corInfo/corInfo_deleteMessage");

	xhr.send(formData);

	

	$('#model_delete_message').modal('hide');
}