function nextPage()
{
	var totalPage = document.getElementById("totalPage").innerHTML;
	var currPage = document.getElementById("currPage").value;
	var currCorporation  = document.getElementById("currCorporation").innerHTML;
	if(totalPage==currPage)
	{
		alert("已经是最后一页");
	}
	else
	{
		currPage++;
		window.location="/corporation/manager/manager_getApplyUser?member_corporation="+currCorporation+"&timeSort=desc&currPage="+currPage;
	}
}

function lastPage()
{
	var totalPage = document.getElementById("totalPage").innerHTML;
	var currPage = document.getElementById("currPage").value;
	var currCorporation  = document.getElementById("currCorporation").innerHTML;
	if(totalPage==currPage)
	{
		alert("已经是最后一页");
		window.location="/corporation/manager/manager_getApplyUser?member_corporation="+currCorporation+"&timeSort=desc&currPage="+currPage;
	}
	else
	{
		currPage--;
	}
}
function indexPage()
{
	var currCorporation  = document.getElementById("currCorporation").innerHTML;
	window.location="/corporation/manager/manager_getApplyUser?member_corporation="+currCorporation+"&timeSort=desc&currPage=1";
}
function endPage(){
	var totalPage = document.getElementById("totalPage").innerHTML;
	var currCorporation  = document.getElementById("currCorporation").innerHTML;
	window.location="/corporation/manager/manager_getApplyUser?member_corporation="+currCorporation+"&timeSort=desc&currPage="+totalPage;
}
function skipToPage()
{
	var totalPage = document.getElementById("totalPage").innerHTML;
	var currPage = document.getElementById("currPage").value;
	var currCorporation  = document.getElementById("currCorporation").innerHTML;
	if(currPage>totalPage||currPage<1)
		{
			alert("不存在该页");
		}
	else
		{
		window.location="/corporation/manager/manager_getApplyUser?member_corporation="+currCorporation+"&timeSort=desc&currPage="+totalPage;	
		}
	
}

//-----------------------------------------------------同意申请------------------------------------------------------
var xmlHttp;
function searchMember(event)
{
	var currCorporation = document.getElementById("currCorporation").innerHTML;
	var currUser = document.getElementById("currUser");
	currUser.value = event.value;
	var selectMember = document.getElementById("selectMember");
	selectMember.innerHTML="";
	ajaxSearchMembere("/corporation/manager/manager_getMemberByUser?member_corporation="+currCorporation);
}

function ajaxSearchMembere(url)
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
	xmlHttp.onreadystatechange = ajaxBack;
}

function ajaxBack()
{
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		var selectMember = document.getElementById("selectMember");
		var result  = xmlHttp.responseText;
		result = JSON.parse(result);
		for(var i=0;i<result.length;i++)
			{
				var option = document.createElement("option");
				option.innerHTML = result[i].member_name+"-->"+result[i].member_class;
				option.setAttribute("value", result[i].member_id);
				selectMember.appendChild(option);
			}
	}
}

function agreeApply()
{
	var selectMember = document.getElementById("selectMember");
	var currUser = document.getElementById("currUser");
	var currCorporation = document.getElementById("currCorporation").innerHTML;
	window.location = "/corporation/manager/manager_agreeApply?currMember="+selectMember.value+"&into_id="+currUser.value+"&member_corporation="+currCorporation;
}

//-----------------------------------------------------拒绝申请-------------------------------------------------------------
function rejectApply(event)
{
	var into_id = event.value;
	var currCorporation = document.getElementById("currCorporation").innerHTML;
	window.location="/corporation/manager/manager_rejectApply?into_id="+into_id+"&member_corporation="+currCorporation;
}
