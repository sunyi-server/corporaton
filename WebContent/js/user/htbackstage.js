function Click(){
	var oList1=document.getElementById('funcList1');				
	var oList2=document.getElementById('funcList2');
	var oList4=document.getElementById('funcList4');
	
	var oSpan1=oList1.getElementsByTagName('span')[0];
	var oSpan2=oList2.getElementsByTagName('span')[0];
	var oSpan4=oList4.getElementsByTagName('span')[0];
	var oUl2=oList2.getElementsByTagName('ul')[0];
	var aLi2=oUl2.getElementsByTagName('li');
	var oUl4=oList4.getElementsByTagName('ul')[0];
	var aLi4=oUl4.getElementsByTagName('li');
	var oRight=document.getElementById('right');
	var oInfo=document.getElementById('Info');
	
	var oForm=document.getElementById('createCommunity');
	var oManForm=document.getElementById('ManagerForm');
	//点击图片管理，暂时还未设置				
	//点击社团管理		
	
	var onOff=true;					
	oSpan2.onclick=function(){
		if(onOff){						
			oList2.style.height='200px';						
			oUl2.style.display='block';						
			oInfo.style.display='block';
		}else{
			oList2.style.height='46px';
			oUl2.style.display='none';
			oInfo.style.display='none';
			oForm.style.display='none';
		}
		onOff=!onOff;					
	};
	
	oSpan4.onclick=function(){
		if(onOff){						
			oList4.style.height='200px';						
			oUl4.style.display='block';
			oManForm.style.display='block';
			oForm.style.display='none';
		}else{			
			oList4.style.height='46px';
			oUl4.style.display='none';
			oManForm.style.display='none';
			oForm.style.display='none';
		}
		onOff=!onOff;					
	};
	
	aLi2[0].onclick=function(){
		oInfo.style.display='none';
		oForm.style.display='block';
		
	};
				
}


function overlay(){
	var oUserchange=document.getElementById("modal_userchange");
	oUserchange.style.visibility=(oUserchange.style.visibility=="visible") ? "hidden":"visible";
	
	
//	-webkit-filter: blur(4px);
}
/*添加用户的*/
function overlay1(){
	var oAdduser=document.getElementById("modal_addUser");
	oAdduser.style.visibility=(oAdduser.style.visibility=="visible") ? "hidden":"visible";
}
function overlay2(){
	var oComm=document.getElementById("modal_comm");
	oComm.style.visibility=(oComm.style.visibility=="visible") ? "hidden":"visible";
}
function hide(){
	var oUserchange=document.getElementById("modal_userchange");
	oUserchange.style.visibility="hidden";
	
	
//	-webkit-filter: blur(4px);
}
function hide1(){
	var oAdduser=document.getElementById("modal_addUser");
	oAdduser.style.visibility="hidden";
	
}
function hide2(){
	var oComm=document.getElementById("modal_comm");
	oComm.style.visibility="hidden";
}

