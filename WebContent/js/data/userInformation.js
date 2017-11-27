function stimulateFile()
{
	var changeUserLogo = document.getElementById("changeUserLogo");
	changeUserLogo.click();
}

function changeLogo()
{
	var changeUserLogo = document.getElementById("changeUserLogo");
	var url = window.URL.createObjectURL(changeUserLogo.files[0]);
	var userLogo = document.getElementById("userLogo");
	var f = document.getElementById("changeUserLogo").value;
	 if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(f))
     {
		    if (changeUserLogo.outerHTML) {
		    	changeUserLogo.outerHTML = changeUserLogo.outerHTML;
	         } else { // FF(包括3.5)
	        	 changeUserLogo.value = "";
	         }
       alert("请选择图片格式！");
     }
	 else
	 {
		 userLogo.src = url;	 
	 }
	
}

var xmlHttp;
function updateUserInformation()
{
	ajaxUpdateUserInformation("/corporation/manager/apply_updateUserInfor");
}
function ajaxUpdateUserInformation(url)
{
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlHttp = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var changeUserLogo = document.getElementById("changeUserLogo");
	var formData = new FormData();
	if(changeUserLogo.value!="")
		{
			formData.append("user_logo", changeUserLogo.files[0]);
		}
	var user_nickname  = document.getElementById("user_nickname");
	var user_telephone = document.getElementById("user_telephone");
	var user_address =document.getElementById("user_address");
	var user_birthday = document.getElementById("user_birthday");
	var user_introduce = document.getElementById("user_introduce");
	formData.append("user_nickname", user_nickname.value);
	formData.append("user_telephone",user_telephone.value);
	formData.append("user_address", user_address.value);
	formData.append("user_birthday", user_birthday.value);
	formData.append("user_introduce", user_introduce.value);
	xmlHttp.open("POST", url,true);
	xmlHttp.send(formData);
	xmlHttp.onreadystatechange = ajaxUpdateUserInformationBack;
}

function ajaxUpdateUserInformationBack()
{
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) 
		{
			window.location = "/corporation/manager/apply_getUserInfor";
		}
}