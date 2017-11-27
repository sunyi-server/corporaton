function checkImg() {
	var input_corporation_logo = document.getElementById("img").value;

	if (input_corporation_logo == null || input_corporation_img == "") {
		return true;

	} else if (!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG|)$/
			.test(input_corporation_logo)) {

		toastr.success("图片类型必须是.gif,jpeg,jpg,png中的一种");

		return false;
	}

}

function save_corporation() {
	if (checkImg()) {
	} else {
		return;
	}

	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				message = xhr.responseText;
				toastr.success("保存成功");
				window.location = "/corporation/corInfo/corInfo_page_list_corInfo?page_list_corInfo.pageIndex=1";
			} else {
				/* toastr.error(message); */
			}

		} else {
			/* toastr.error(xhr.status); */
		}
	}

	var input_corporation_name = document
			.getElementById("input_corporation_name");

	// 只简单的判断社团名的长度

	var corporation_name = input_corporation_name.value;
	if (corporation_name.length <= 0) {
		toastr.error("社团名不能为空");
		return false;
	}
	var input_corporation_introduce = document
			.getElementById("input_corporation_introduce");

	var input_corporation_apply_man = document
			.getElementById("input_corporation_apply_man");

	var input_corporation_apply_time = document
			.getElementById("input_corporation_apply_time");

	var input_corporation_leader_id = document
			.getElementById("input_corporation_leader_id");
	var input_corporation_logo = document
			.getElementById("input_corporation_logo");
	var input_corporation_type = document
			.getElementById("input_corporation_type");
	var checkbox_corporation_solidify = document
			.getElementById("checkbox_corporation_solidify");
	var select_corporation_status = document
			.getElementById("select_corporation_status");
	xhr.open("POST", "/corporation/corInfo/corInfo_page_save_corInfo");

	var formData = new FormData();

	formData.append("corInfo.corporation_name", input_corporation_name.value);

	formData.append("corInfo.corporation_introduce",
			input_corporation_introduce.value);
	formData.append("corInfo.corporation_apply_man",
			input_corporation_apply_man.value);
	formData.append("corInfo.corporation_apply_time",
			input_corporation_apply_time.value);

	formData.append("corInfo.corporation_leader_id",
			input_corporation_leader_id.value);
	formData.append("corInfo.corporation_type", input_corporation_type.value);

	if (checkbox_corporation_solidify.checked) {
		formData.append("corInfo.corporation_solidify", 0);
	} else {
		formData.append("corInfo.corporation_solidify", 1);
	}

	formData.append("corInfo.corporation_status",
			select_corporation_status.value);

	if (input_corporation_logo.files[0] != null) {
		// 这里不是直接赋值给对象，这里给了文件类型的corporation_logo
		formData.append("corporation_logo", input_corporation_logo.files[0]);
	}

	xhr.send(formData);

}

function update_corporation() {
	if (checkImg()) {

	} else {

		return;
	}

	var input_corporation_id = document.getElementById("input_corporation_id");

	var input_corporation_name = document
			.getElementById("input_corporation_name");

	var input_corporation_introduce = document
			.getElementById("input_corporation_introduce");

	var input_corporation_apply_man = document
			.getElementById("input_corporation_apply_man");

	var input_corporation_apply_time = document
			.getElementById("input_corporation_apply_time");

	var input_corporation_leader_id = document
			.getElementById("input_corporation_leader_id");
	var input_corporation_logo = document
			.getElementById("input_corporation_logo");
	var input_corporation_type = document
			.getElementById("input_corporation_type");
	var checkbox_corporation_solidify = document
			.getElementById("checkbox_corporation_solidify");
	var checkbox_corporation_status = document
			.getElementById("checkbox_corporation_status");

	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				window.location = "/corporation/corInfo/corInfo_page_list_corInfo?page_list_corInfo.pageIndex=1";
			} else {
				/* toastr.error(message); */
			}

		} else {
			/* toastr.error(xhr.status); */
		}
	}

	xhr.open("POST", "/corporation/corInfo/corInfo_update_corInfo");

	var formData = new FormData();
	formData.append("corInfo.corporation_id", input_corporation_id.value);

	formData.append("corInfo.corporation_name", input_corporation_name.value);

	formData.append("corInfo.corporation_introduce",
			input_corporation_introduce.value);
	formData.append("corInfo.corporation_apply_man",
			input_corporation_apply_man.value);
	formData.append("corInfo.corporation_apply_time",
			input_corporation_apply_time.value);

	formData.append("corInfo.corporation_leader_id",
			input_corporation_leader_id.value);
	formData.append("corInfo.corporation_type", input_corporation_type.value);

	if (checkbox_corporation_solidify.checked) {
		formData.append("corInfo.corporation_solidify", 0);
	} else {
		formData.append("corInfo.corporation_solidify", 1);
	}

	formData.append("corInfo.corporation_status",
			select_corporation_status.value);

	if (input_corporation_logo.files[0] != null) {
		// 这里不是直接赋值给对象，这里给了文件类型的corporation_img
		formData.append("corporation_logo", input_corporation_logo.files[0]);
	}

	xhr.send(formData);

}

function update_message() {
	var hidden_message_id = document.getElementById("hidden_message_id");
	var disabled_corporation_name = document
			.getElementById("disabled_corporation_name");
	var input_message_content = document
			.getElementById("input_message_content");
	var hidden_corporation_id = document
			.getElementById("hidden_corporation_id");
	var hidden_message_gmt_create = document
			.getElementById("hidden_message_gmt_create");
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		var message;
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				toastr.success("修改成功");
				window.location = "/corporation/corInfo/corInfo_page_list_message?page_list_corInfo.pageIndex=1";
			} else {
				/* toastr.error(message); */
			}

		} else {
			/* toastr.error(xhr.status); */
		}
	}

	xhr.open("POST", "/corporation/corInfo/corInfo_updateMessage");

	var formData = new FormData();
	formData.append("message.message_id", hidden_message_id.value);
	formData
			.append("message.corporation_name", disabled_corporation_name.value);

	formData.append("message.message_content", input_message_content.value);

	formData.append("message.corporation_id", hidden_corporation_id.value);
	formData.append("message.message_gmt_create",
			hidden_message_gmt_create.value);
	xhr.send(formData);

}