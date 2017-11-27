function changeInfo()
{
	var currPage = document.getElementById("currPage");
	var totalPage  = document.getElementById("totalPage");
	if(currPage.value>=totalPage.value)
		{
			currPage.value = 1;
		}
	else
		{
			currPage.value++;
		}
	var queryName =document.getElementById("queryname");
	window.location="/corporation/manager/apply_skipToApply?queryName="+queryName.value+"&currPage="+currPage.value;
}
function queryInfo()
{	
	var queryName = document.getElementById("queryName");
	window.location="/corporation/manager/apply_skipToApply?queryName="+queryName.value+"&currPage=1";
}
function applyCorporation(corporationId)
{	
	window.location="/corporation/manager/apply_applyCorporation?corporation_id="+corporationId;
}

//使用xmlHttp进行非异步跳转，传送post请求