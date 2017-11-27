function addDepartment()
{
	var department_name = document.getElementById("department_name");
	var currCorporation = document.getElementById("currCorporation");
	validateDepartment("/corporation/manager/manager_validateDepartment?department_name="+department_name.value+"&member_corporation="+currCorporation.innerHTML);
	
}
var xmlHttp;
function validateDepartment(url)
{
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlHttp = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlHttp.open("GET",url,true);
	xmlHttp.send();
	xmlHttp.onreadystatechange=validateBack;
	
}
function validateBack()
{
	var department_name = document.getElementById("department_name");
	var currCorporation = document.getElementById("currCorporation");
	var department_introduce =document.getElementById("department_introduce");
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		var result = xmlHttp.responseText;
		if(result=="0")
			{
				window.location="/corporation/manager/manager_addDepartment?member_corporation="+currCorporation.innerHTML+"&department_name="+department_name.value+"&department_introduce="+department_introduce.value;
			}
		else
			{
				alert("部门名重复");
			}
	}
}

function getDepartmentInfo(event)
{
	var department_id = event.value;
	var currCorporation = document.getElementById("currCorporation");
	ajaxGetDepartmentInfor("/corporation/manager/manager_getDepartmentById?member_corporation="+currCorporation.innerHTML+"&department_id="+department_id);
}

function ajaxGetDepartmentInfor(url)
{
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlHttp = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlHttp.open("GET",url,true);
	xmlHttp.send();
	xmlHttp.onreadystatechange=ajaxGetDepartmentBack;
}

function ajaxGetDepartmentBack()
{
	var department_name_update = document.getElementById("department_name_update");
	var department_introduce_update = document.getElementById("department_introduce_update");
	var saveBtn =document.getElementById("saveBtn");
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		var result = xmlHttp.responseText;
		result = JSON.parse(result);
		department_name_update.value  = result.department_name;
		department_introduce_update.value =result.department_introduce;
		saveBtn.value = result.department_id;
	}

}

function updateDepartment(event)
{
	var currCorporation = document.getElementById("currCorporation").innerHTML;
	var department_name = document.getElementById("department_name_update");
	var department_introduce = document.getElementById("department_introduce_update");
	window.location="/corporation/manager/manager_updateDepartment?member_corporation="+currCorporation+"&department_introduce="+department_introduce.value+"&department_id="+event.value;
}

function deleteDepartment(event)
{
	var currCorporation = document.getElementById("currCorporation").innerHTML;
	window.location="/corporation/manager/manager_deleteDepartment?member_corporation="+currCorporation+"&department_id="+event.value;
	
}