/**
 * 
 */
function ajaxloadCorporation(){
	var ul=document.querySelector('.dropdown-menu');
	ul.innerHTML="";
	loadCorporation("/corporation/manager/apply_getCorporation");
}
var xmlhttp;
function loadCorporation(url){
	if(window.XMLHttpRequest)
	{
		xmlhttp=new XMLHttpRequest(); 
	}
	else
	{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");	
	}
	xmlhttp.open("GET",url,true);
	xmlhttp.send();
	xmlhttp.onreadystatechange=httpBackCorporation;
}
function httpBackCorporation(){
	if(xmlhttp.readyState==4&&xmlhttp.status==200){
		var result=xmlhttp.responseText;
		result=JSON.parse(result);
		for(var i=0;i<result.length;i++){
			var corporationName = result[i].user_corporation_include;
			var lia=document.createElement("li");
			var li1 = document.createElement("li");
			li1.setAttribute("role", "separator");
			li1.setAttribute("class", "divider");
			var li2 = document.createElement("li");
			li2.setAttribute("role", "separator");
			li2.setAttribute("class", "divider");
			lia.setAttribute("onclick","intoCorporation(\""+corporationName+"\")")
			lia.innerHTML="<a class='li-a'>"+result[i].user_corporation_include+"</a>";
			var ul=document.querySelector('.dropdown-menu');
			ul.appendChild(li2);
			ul.appendChild(lia);
			ul.appendChild(li1);
		}		
	}
}
function intoCorporation(corporationName)
{
	window.location="/corporation/manager/manager_getCoporationInforShow?&member_corporation="+corporationName;
	}