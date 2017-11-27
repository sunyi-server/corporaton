function activateFileInput()
{
	var changeLogo = document.getElementById("changeLogo");
	changeLogo.click();

}
function changeLogoChange()
{
	var  corporationLogo = document.getElementById("corporationLogo");
	var changeLogo = document.getElementById("changeLogo");
	//获得选择文件的url地址用于预览图片 
	var url=window.URL.createObjectURL(changeLogo.files[0]);
	 if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(corporationLogo.value))
     {
		    if (corporationLogo.outerHTML) {
		    	corporationLogo.outerHTML = corporationLogo.outerHTML;
	         } else { // FF(包括3.5)
	        	 corporationLogo.value = "";
	         }
       alert("请选择图片格式！");
     }
	 else
	 {
		 corporationLogo.src =url; 
	 }
}
var xmlHttp;
function uploadUpdateCorInfo()
{
	ajaxUploadUpdateCorInfo("/corporation/manager/manager_updateCorInfo");
}

function ajaxUploadUpdateCorInfo(url)
{
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlHttp = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var formData = new FormData();
	var corporation_id = document.getElementById("corporation_id");
	var corporation_name =document.getElementById("corporation_name");
	var corporation_introduce = document.getElementById("corporation_introduce");
	var corporation_apply_time = document.getElementById("corporation_apply_time");
	var corporation_logo = document.getElementById("changeLogo");
	if(corporation_logo.value!="")
		{
			formData.append("corporation_logo", corporation_logo.files[0]);
		}
	formData.append("corporation_id", corporation_id.value);
	formData.append("member_corporation", corporation_name.value);
	formData.append("corporation_introduce", corporation_introduce.value);
	formData.append("corporation_apply_time", corporation_apply_time.value);
	xmlHttp.open("POST", url, true);
	xmlHttp.send(formData);
	xmlHttp.onreadystatechange =ajaxUploadBack;
}

function ajaxUploadBack()
{
	if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
	var curCorporation = document.getElementById("currCorporation");
	window.location="/corporation/manager/manager_getCoporationInfor?member_corporation="+curCorporation.innerHTML;
	}
}
