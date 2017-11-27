function goToManagerJsp()
{
	var curCorporation = document.getElementById("currCorporation");
	window.location="/corporation/manager/manager_showMember?queryMemberStatus=" +
	"&queryDepartment=&member_corporation="+curCorporation.innerHTML+"&queryStartTime=0000-00-00" +
	"&queryEndTime=9999-99-99&queryName=&timeSort=desc&currPage=1";
}
function goToApplyJsp()
{
	var curCorporation = document.getElementById("currCorporation");
	window.location="/corporation/manager/manager_getApplyUser?member_corporation="+curCorporation.innerHTML+"&timeSort=desc&currPage=1";
}
function goToDepartment()
{
	var curCorporation = document.getElementById("currCorporation");
	window.location="/corporation/manager/manager_skipToDepartment?member_corporation="+curCorporation.innerHTML;
}

function goToMemberMan()
{
	var curCorporation = document.getElementById("currCorporation");
	window.location="/corporation/manager/manager_showMemberManager?queryMemberStatus=" +
	"&queryDepartment=&member_corporation="+curCorporation.innerHTML+"&queryStartTime=0000-00-00" +
	"&queryEndTime=9999-99-99&queryName=&timeSort=desc&currPage=1";
}
function goToCorporationInfo()
{
	var curCorporation = document.getElementById("currCorporation");
	window.location="/corporation/manager/manager_getCoporationInfor?member_corporation="+curCorporation.innerHTML;
}

function goToCorporationInfoShow()
{
	var curCorporation = document.getElementById("currCorporation");
	window.location="/corporation/manager/manager_getCoporationInforShow?member_corporation="+curCorporation.innerHTML;
}