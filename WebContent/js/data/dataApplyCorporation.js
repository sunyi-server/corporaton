
function verificationCorporation()
{
	var corporation_name = document.getElementById("corporation_name");
	var result = document.getElementById("resultName");
	ajaxVerificationCorporation("/corporation/manager/apply_corporationNameIsExsit?corporation_name="+corporation_name.value);
	
	if(result.value==1)
		{
		alert("协会名重复！");
		return false;	
		}
	else
		{
		if(document.getElementById("corporation_name").value==""||document.getElementById("corporation_name").value==null)
		{
			alert("不能为空！");
			return false;
		}
		else
			{
			return true;	
			}
		
		}
	
}
var xmlHttp;
function ajaxVerificationCorporation(url)
{
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlHttp = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlHttp.open("get", url, true);
	xmlHttp.send();
	xmlHttp.onreadystatechange = ajaxVerificationCorporationBack;
}
function ajaxVerificationCorporationBack()
{
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
		var result = document.getElementById("resultName");
		result.value= xmlHttp.responseText;
	}
}

function changeLogo()
{
	document.getElementById("corporation_logo").click();
}

function showLogoImg(event){
	var path = window.URL.createObjectURL(event.files[0]);
	 if(!/\.(gif|jpg|jpeg|png|GIF|JPG|PNG)$/.test(event.value))
     {
		    if (event.outerHTML) {
		    	event.outerHTML = event.outerHTML;
	         } else { // FF(包括3.5)
	        	 corporationLogo.value = "";
	         }
       alert("请选择图片格式！");
     }
	 else
	 {
		 document.getElementById("showLogo").src = path;
	 }
	
}