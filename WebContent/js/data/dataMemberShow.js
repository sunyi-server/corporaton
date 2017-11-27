//---------------------------------------删除会员---------------------------------------------------------



var curCorporation;
function deleteMember(event){
	curCorporation = document.getElementById("currCorporation");
	ajaxDeleteMember("/corporation/manager/manager_deleteMember",event.value,curCorporation.innerHTML);
}
var xmlDeleteHttp;
function ajaxDeleteMember(url,id,currCorporation)
{
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlDeleteHttp = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlDeleteHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	var formData = new FormData();
	formData.append("memberId",id);
	formData.append("member_corporation",currCorporation);
	xmlDeleteHttp.open("POST", url, true);
	xmlDeleteHttp.send(formData);
	xmlDeleteHttp.onreadystatechange = deleteBack;
}
function deleteBack()
{
	if(xmlDeleteHttp.readyState == 4 && xmlDeleteHttp.status == 200)
		{
			window.location="/corporation/manager/manager_showMember?queryMemberStatus=" +
				"&queryDepartment=&member_corporation="+curCorporation.innerHTML+"&queryStartTime=0000-00-00" +
				"&queryEndTime=9999-99-99&queryName=&timeSort=desc&currPage=1";
		}
}

//----------------------------------------上传会员---------------------------------------------------------
function ajaxUploadMember()
{
	
	uploadMember("/corporation/manager/manager_addNewMember");

}


var xmlHttp;
function uploadMember(url)
{
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlHttp = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var corporationName = document.getElementById("currCorporation");
	var member_name = document.getElementById("member_name");
	var member_telephone = document.getElementById("member_telephone");
	var member_birthday = document.getElementById("datetimeBirthday");
	var member_num = document.getElementById("member_num");
	var member_college = document.getElementById("member_college");
	var member_major = document.getElementById("member_major");
	var member_class = document.getElementById("member_class");
	var member_department = document.getElementById("member_department");
	var member_position = document.getElementById("member_position");
	var member_home_address = document.getElementById("member_home_address");
	var member_qq = document.getElementById("member_qq");
	var member_join_time = document.getElementById("datetimeJoin");
	var member_quit_time =document.getElementById("datetimeQuit");
	var member_status = document.getElementById("member_status_add");
	var member_sex = document.getElementById("member_sex");
	var formData = new FormData();
	formData.append("member_birthday",member_birthday.value);
	formData.append("member_sex",member_sex.value);
	formData.append("member_corporation",corporationName.innerHTML);
	formData.append("member_name", member_name.value);
	formData.append("member_telephone", member_name.value);
	formData.append("member_num", member_num.value);
	formData.append("member_college", member_college.value);
	formData.append("member_major", member_major.value);
	formData.append("member_class", member_class.value);
	formData.append("member_department", member_department.value);
	formData.append("member_position", member_position.value);
	formData.append("member_home_address", member_home_address.value);
	formData.append("member_qq", member_qq.value);
	formData.append("member_join_time", member_join_time.value);
	formData.append("member_quit_time", member_quit_time.value);
	formData.append("member_status", member_status.value);
	xmlHttp.open("POST", url, true);
	xmlHttp.send(formData);
	xmlHttp.onreadystatechange = httpBackNews;
}
function httpBackNews()
{
	var currCorporation = document.getElementById("currCorporation");
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		window.location="/corporation/manager/manager_showMember?queryMemberStatus=" +
				"&queryDepartment=&member_corporation="+currCorporation.innerHTML+"&queryStartTime=0000-00-00" +
						"&queryEndTime=9999-99-99&queryName=&timeSort=desc&currPage=1";
	}
}

function changeSort()
{	var currCorporation = document.getElementById("currCorporation");
	var currSort  = document.getElementById("currSort");
	var datetimeStart = document.getElementById("datetimeStart");
	var datetimeEnd = document.getElementById("datetimeEnd");
	var queryDepartment = document.getElementById("queryDepartment");
	var member_status = document.getElementById("member_status");
	if(currSort.innerHTML=="desc")
		{
			currSort.innerHTML="asc";
			window.location="/corporation/manager/manager_showMember?queryMemberStatus=" +member_status.value+
					"&queryDepartment="+queryDepartment.value+"&member_corporation="+currCorporation.innerHTML+"" +
							"&queryStartTime="+datetimeStart.value+"&queryEndTime="+datetimeEnd.value+"" +
							"&queryName=&timeSort=asc&currPage=1";
		}
	else
		{
			currSort.innerHTML="desc";
			window.location="/corporation/manager/manager_showMember?" +
					"queryMemberStatus=" +member_status.value+
					"&queryDepartment="+queryDepartment.value+"&member_corporation="+currCorporation.innerHTML+"" +
							"&queryStartTime="+datetimeStart.value+"&queryEndTime="+datetimeEnd.value+"&queryName=" +
							"&timeSort=desc&currPage=1";
		}
}

function indexPage()
{	var currCorporation = document.getElementById("currCorporation");
	var currSort  = document.getElementById("currSort");
	var datetimeStart = document.getElementById("datetimeStart");
	var datetimeEnd = document.getElementById("datetimeEnd");
	var queryDepartment = document.getElementById("queryDepartment");
	var member_status = document.getElementById("member_status");
	var queryName = document.getElementById("queryName");
	window.location="/corporation/manager/manager_showMember?" +
			"queryMemberStatus="+member_status.value+"&queryDepartment="+queryDepartment.value+"&member_corporation="+currCorporation.innerHTML+"" +
					"&queryStartTime="+datetimeStart.value+"&queryEndTime="+datetimeEnd.value+
					"&queryName="+queryName.value+"&timeSort="+currSort.innerHTML+"&currPage=1";
}

function lastPage()
{	var currCorporation = document.getElementById("currCorporation");
	var currPage = document.getElementById("currPage");
	var currSort  = document.getElementById("currSort");
	var datetimeStart = document.getElementById("datetimeStart");
	var datetimeEnd = document.getElementById("datetimeEnd");
	var queryDepartment = document.getElementById("queryDepartment");
	var member_status = document.getElementById("member_status");
	var queryName = document.getElementById("queryName");
	if(currPage.value=="1")
		{
			alert("已经是第一页");
		}
	else
		{
			var curr = currPage.value-1;
			window.location="/corporation/manager/manager_showMember?" +
					"queryMemberStatus="+member_status.value+"&queryDepartment="+queryDepartment.value+"&member_corporation="+currCorporation.innerHTML+"" +
							"&queryStartTime="+datetimeStart.value+"&queryEndTime="+datetimeEnd.value+
							"&queryName="+queryName.value+"&timeSort="+currSort.innerHTML+"&currPage="+curr;
		}
}
	

function nextPage()
{	var currCorporation = document.getElementById("currCorporation");
	var totalPage = document.getElementById("totalPage");
	var currPage = document.getElementById("currPage");
	var currSort  = document.getElementById("currSort");
	var datetimeStart = document.getElementById("datetimeStart");
	var datetimeEnd = document.getElementById("datetimeEnd");
	var queryDepartment = document.getElementById("currDepartment");
	var member_status = document.getElementById("currStatus");
	var queryName = document.getElementById("queryName");
	if(totalPage.innerHTML==currPage.value)
		{
			alert("已经是最后一页");
		}
	else
		{
			var curr = parseInt(currPage.value)+1;
			window.location="/corporation/manager/manager_showMember" +
					"?queryMemberStatus="+member_status.value+"&queryDepartment="+queryDepartment.value+"&member_corporation="+currCorporation.innerHTML+"" +
							"&queryStartTime="+datetimeStart.value+"&queryEndTime="+datetimeEnd.value+
							"&timeSort="+currSort.innerHTML+"&currPage="+curr+"&queryName="+queryName.value;
		}
	
}

function finalPage()
{	var currCorporation = document.getElementById("currCorporation");
	var currSort  = document.getElementById("currSort");
	var datetimeStart = document.getElementById("datetimeStart");
	var datetimeEnd = document.getElementById("datetimeEnd");
	var totalPage = document.getElementById("totalPage");
	var queryDepartment = document.getElementById("currDepartment");
	var member_status = document.getElementById("currStatus");
	var queryName = document.getElementById("queryName");
	window.location="/corporation/manager/manager_showMember?queryMemberStatus=" +member_status.value+
			"&queryDepartment="+queryDepartment.value+"&member_corporation=" +
			""+currCorporation.innerHTML+"&queryStartTime="+datetimeStart.value+"" +
			"&queryEndTime="+datetimeEnd.value+"&queryName="+queryName.value+"&timeSort="+currSort.innerHTML+"&currPage="+totalPage.innerHTML;
}

function queryMember()
{
	var queryName= document.getElementById("queryName");
	var currCorporation = document.getElementById("currCorporation");
	var currSort  = document.getElementById("currSort");
	var datetimeStart = document.getElementById("datetimeStart");
	var datetimeEnd = document.getElementById("datetimeEnd");
	var queryDepartment = document.getElementById("currDepartment");
	var member_status = document.getElementById("currStatus");
	window.location="/corporation/manager/manager_showMember?queryMemberStatus=" +member_status.value+
	"&queryDepartment="+queryDepartment.value+"&member_corporation=" +
	""+currCorporation.innerHTML+"&queryStartTime="+datetimeStart.value+"" +
	"&queryEndTime="+datetimeEnd.value+"&queryName="+queryName.value+"&timeSort="+currSort.innerHTML+"&currPage=1";
}

function jumpToPage()
{
	var totalPage = document.getElementById("totalPage");
	var currPage = document.getElementById("currPage");
	var queryName= document.getElementById("queryName");
	var currCorporation = document.getElementById("currCorporation");
	var currSort  = document.getElementById("currSort");
	var datetimeStart = document.getElementById("datetimeStart");
	var datetimeEnd = document.getElementById("datetimeEnd");
	var queryDepartment = document.getElementById("currDepartment");
	var member_status = document.getElementById("currStatus");
	if(currPage.value>totalPage.innerHTML)
		{
			alert("不存在该页");
		}
	else
		{
		window.location="/corporation/manager/manager_showMember?queryMemberStatus=" +member_status.value+
		"&queryDepartment="+queryDepartment.value+"&member_corporation=" +
		""+currCorporation.innerHTML+"&queryStartTime="+datetimeStart.value+"" +
		"&queryEndTime="+datetimeEnd.value+"&queryName="+queryName.value+"&timeSort="+currSort.innerHTML+"&currPage="+currPage.value;
	
		}
}
var xmlHttpUpdate;
function getMemberById(event)
{
	var memberId = event.value;
	var currCorporation = document.getElementById("currCorporation");
	ajaxGetMemberById("/corporation/manager/manager_getMemberById?memberId="+memberId+"&member_corporation="+currCorporation.innerHTML);
}

function ajaxGetMemberById(url)
{
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlHttpUpdate = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlHttpUpdate = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlHttpUpdate.open("GET",url,true);
	xmlHttpUpdate.send();
	xmlHttpUpdate.onreadystatechange = ajaxGetMemberBack;
}

function ajaxGetMemberBack()
{
	if (xmlHttpUpdate.readyState == 4 && xmlHttpUpdate.status == 200)
	{
		var result = xmlHttpUpdate.responseText;
		result = JSON.parse(result);
		var member_name = document.getElementById("member_name_update");
		var member_telephone = document.getElementById("member_telephone_update");
		var member_birthday = document.getElementById("datetimeBirthday_update");
		var member_num = document.getElementById("member_num_update");
		var member_college = document.getElementById("member_college_update");
		var member_major = document.getElementById("member_major_update");
		var member_class = document.getElementById("member_class_update");
		var member_department = document.getElementById("member_department_update");
		var member_position = document.getElementById("member_position_update");
		var member_home_address = document.getElementById("member_home_address_update");
		var member_qq = document.getElementById("member_qq_update");
		var member_join_time = document.getElementById("datetimeJoin_update");
		var member_quit_time =document.getElementById("datetimeQuit_update");
		var member_status = document.getElementById("member_status_update");
		var member_sex = document.getElementById("member_sex_update");
		var member_gmt_create = document.getElementById("member_gmt_create_update");
		var member_role = document.getElementById("member_role_update");
		var member_corporation = document.getElementById("member_corporation_update");
		var member_gmt_modified = document.getElementById("member_gmt_modified_update");
		var member_user = document.getElementById("member_user_update");
		var member_id = document.getElementById("member_id_update");
		member_name.value = result.member_name;
		member_telephone.value = result.member_telephone;
		member_birthday.value = result.member_birthday;
		member_num.value =result.member_num;
		member_college.value = result.member_college;
		member_class.value = result.member_class;
		member_department.value = result.member_department;
		member_major.value = result.member_major;
		member_position.value = result.member_position;
		member_home_address.value = result.member_home_address;
		member_qq.value = result.member_qq;
		member_join_time.value = result.member_join_time;
		member_quit_time.value = result.member_quit_time;
		member_status.value = result.member_status;
		member_sex.value = result.member_sex;
		member_gmt_create.value = result.member_gmt_create;
		if(result.member_role=="1")
			{
			member_role.value = "普通会员";
			}
		else
			{
			member_role.value= "管理员";
			}
		
		member_corporation.value = result.member_corporation;
		member_gmt_modified.value = result.member_gmt_modified;
		member_user.value = result.member_user;
		member_id.value = result.member_id;
	}
}


function chooseColumn(event)
{
	var selectedColumn = document.getElementById("selectedColumn");
	var unselectedColumn  = document.getElementById("unselectedColumn");
	var btn = document.createElement("button");
	btn.value = event.value;
	btn.setAttribute("name", "selectedColumn");
	btn.setAttribute("class", "btn btn-primary  btn-sm");
	btn.setAttribute("style", "margin-top:3px; margin-right: 3px; height: 30px;");
	btn.setAttribute("onclick","minusColumn(this)");
	var sp = event.getElementsByTagName("span");
	sp[0].removeAttribute("class");
	sp[0].setAttribute("class", "glyphicon glyphicon-minus");
	btn.innerHTML="";
	btn.appendChild(sp[0]);
	selectedColumn.appendChild(btn);
	unselectedColumn.removeChild(event);
	
}

function minusColumn(event)
{
	var selectedColumn = document.getElementById("selectedColumn");
	var unselectedColumn  = document.getElementById("unselectedColumn");
	var btn = document.createElement("button");
	btn.value = event.value;
	btn.setAttribute("name", "unselectedColumn");
	btn.setAttribute("class", "btn btn-default  btn-sm");
	btn.setAttribute("style", "margin-top:3px; margin-right: 3px; height: 30px;");
	btn.setAttribute("onclick","chooseColumn(this)");
	btn.setAttribute("type","button");
	var sp = event.getElementsByTagName("span");
	sp[0].removeAttribute("class");
	sp[0].setAttribute("class", "glyphicon glyphicon-plus");
	btn.innerHTML="";
	btn.appendChild(sp[0]);
	unselectedColumn.appendChild(btn);
	selectedColumn.removeChild(event);
}

var xmlExportExcel;
function exportExcel()
{
	ajaxExportExcel("/corporation/manager/manager_exportExcel")
}
function ajaxExportExcel(url)
{
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlExportExcel = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlExportExcel = new ActiveXObject("Microsoft.XMLHTTP");
	}
	var formData = new FormData();
	formData.append("selectedColumn","Member_name");
	var selectedColumn = document.getElementsByName("selectedColumn");
	for(var i=0;i<selectedColumn.length;i++)
		{
			formData.append("selectedColumn", selectedColumn[i].value);
		}
	var startTimeExport = document.getElementById("startTimeExport");
	var endTimeExport = document.getElementById("endTimeExport");
	var member_status_export = document.getElementById("member_status_export");
	var member_sex_export = document.getElementById("member_sex_export");
	var member_department_export = document.getElementById("member_department_export");
	var currCorporation = document.getElementById("currCorporation");
	formData.append("member_corporation",currCorporation.innerHTML);
	formData.append("queryStartTime",startTimeExport.value);
	formData.append("queryEndTime",endTimeExport.value);
	formData.append("queryMemberStatus",member_status_export.value);
	formData.append("queryMemberSex",member_sex_export.value);
	formData.append("queryDepartment",member_department_export.value);
	xmlExportExcel.open("POST",url,true);
	xmlExportExcel.send(formData);
	xmlExportExcel.onreadystatechange = ajaxExportExcelBack;
}
function ajaxExportExcelBack()
{
	if (xmlExportExcel.readyState == 4 && xmlExportExcel.status == 200)
		{
			var result = xmlExportExcel.responseText;
			var currCorporation = document.getElementById("currCorporation");
			window.location = "/corporation/manager/manager_downExcel?filename="+result+"&member_corporation="+currCorporation.innerHTML;
		}
}

function changeMemberRole(event)
{
	var currCoproration = document.getElementById("currCorporation").innerHTML;
	var member_id = event.name;
	var member_role = event.value;
	ajaxChangeMemberRole("/corporation/manager/manager_changeMemberRole?member_corporation="+currCoproration+"&member_id="+member_id+"&member_role="+member_role);
}
function ajaxChangeMemberRole(url)
{
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlHttp = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlHttp.open("GET", url, true);
	xmlHttp.send();
}

function changeDepartment(event)
{
	var currDepartment  = document.getElementById("currDepartment");
	
	currDepartment.value =event.value;
	alert(event.value);
}

function changeStatus(event)
{
	var currStatus = document.getElementById("currStatus");
	currStatus.value = event.value;
}

function showQuit(event)
{
	var quitLabel = document.getElementById("quitLabel");
	var datetimeQuit = document.getElementById("datetimeQuit");
	if(event.value="0")
		{
			quitLabel.setAttribute("style", "display:block;");
			datetimeQuit.setAttribute("style", "display:block;");
		}
	else
		{
		quitLabel.setAttribute("style", "display:none;");
		datetimeQuit.setAttribute("style", "display:none;");
		}
}