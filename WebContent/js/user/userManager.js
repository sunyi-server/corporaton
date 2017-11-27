/*function check() {

	if (form1.user_name.value == "") {
		alert("请输入用户名！");
		return false;
	}
	if (form1.user_nickname.value == "") {
		alert("请输入昵称！");
		return false;
	}
	if (form1.user_username.value == "") {
		alert("请输入账号！");
		return false;
	}
	if (form1.user_password.value == "") {
		alert("请输入密码！");
		return false;
	}
	if (form1.user_birthday.value == "") {
		alert("请输入出生日期！");
		return false;
	}
	if (form1.user_introduce.value == "") {
		alert("请输入签名！");
		return false;
	}
	if (form1.user_sex.value == "") {
		alert("请选择性别！");
		return false;
	}
	if (form1.user_telephone.value == "") {
		alert("请输入联系方式！");
		return false;
	}
	if (form1.user_address.value == "") {
		alert("请输入地址！");
		return false;
	}
	if (form1.user_role.value == "") {
		alert("请输入角色！");
		return false;
	}
	if (form1.logo.value == "") {
		alert("请上传头像！");
		return false;
	}
	
	
}*/



function updateUser(event) {
	var user_id = event.value;
	ajaxUpdateUser("/corporation/user_inputUser", user_id);
}
var xmlHttp;
function ajaxUpdateUser(url, user_id) {
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlHttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlHttp.open("post", url, true);
	var formData = new FormData();
	formData.append("user_id", user_id);
	xmlHttp.send(formData);
	xmlHttp.onreadystatechange = ajaxUpdateUserBack;
}
function ajaxUpdateUserBack() {
	 
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		var result = xmlHttp.responseText;
		result = JSON.parse(result);

		var user_id = document.getElementById("user_id_update");
		var user_name = document.getElementById("user_name_update");
		var user_nickname_update = document
				.getElementById("user_nickname_update");
		var user_username_update = document
				.getElementById("user_username_update");
		var user_password_update = document
				.getElementById("user_password_update");
		var user_birthday_update = document
				.getElementById("user_birthday_update");
		var user_introduce_update = document
				.getElementById("user_introduce_update");
		var user_address_update = document
				.getElementById("user_address_update");
		var user_role_update = document.getElementById("user_role_update");
		var user_logo_update = document.getElementById("user_logo_update");
		var user_telephone_update = document
				.getElementById("user_telephone_update");
		var user_sex_man = document.getElementById("user_sex_man");
		var user_sex_woman = document.getElementById("user_sex_woman");
		if (result.user_sex == "1") {
			user_sex_man.checked = true;
		} else {
			user_sex_woman.checked = true;
		}
		user_name.value = result.user_name;
		user_nickname_update.value = result.user_nickname;
		user_username_update.value = result.user_username;
		user_password_update.value = result.user_password;
		user_birthday_update.value = result.user_birthday;
		user_introduce_update.value = result.user_introduce;
		user_address_update.value = result.user_address;
		user_role_update.value = result.user_role;
		user_telephone_update.value = result.user_telephone;
		user_id.value = result.user_id;

		// alert(result.user_logo);
		alert(result.user_logo);
		 document.getElementById("user_logo_show").setAttribute("src",result.user_logo);
	}

}