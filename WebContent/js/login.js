$(function(){
	
	$('#switch_qlogin').click(function(){
		$('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_bottom').animate({left:'0px',width:'70px'});
		$('#qlogin').css('display','none');
		$('#web_qr_login').css('display','block');
		
		});
	$('#switch_login').click(function(){
		
		$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
		$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
		$('#switch_bottom').animate({left:'154px',width:'70px'});
		
		$('#qlogin').css('display','block');
		$('#web_qr_login').css('display','none');
		});
		if(getParam("a")=='0')
		{
			$('#switch_login').trigger('click');
		}

	});
	
/*function logintab(){
	scrollTo(0);
	$('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
	$('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
	$('#switch_bottom').animate({left:'154px',width:'96px'});
	$('#qlogin').css('display','none');
	$('#web_qr_login').css('display','block');
	
}*/


/*//根据参数名获得该参数 pname等于想要的参数名 
function getParam(pname) { 
    var params = location.search.substr(1); // 获取参数 平且去掉？ 
    var ArrParam = params.split('&'); 
    if (ArrParam.length == 1) { 
        //只有一个参数的情况 
        return params.split('=')[1]; 
    } 
    else { 
         //多个参数参数的情况 
        for (var i = 0; i < ArrParam.length; i++) { 
            if (ArrParam[i].split('=')[0] == pname) { 
                return ArrParam[i].split('=')[1]; 
            } 
        } 
    } 
}  */


/*var reMethod = "GET",pwdmin = 6;

$(document).ready(function() {

	$('#reg').click(function() {	

		$('#regUser').submit();
	});
	

});*/


function checkValue(){

	var oUser=document.getElementById("user").value;
	var oNickname=document.getElementById("nickname").value;
	var oUsername=document.getElementById("username").value;
	var oPw=document.getElementById("passwd").value;
	var oMobile=document.getElementById("mobile").value;
	var oUserTips=document.getElementById("user_tips");
	var oNicknameTips=document.getElementById("nickname_tips");
	var oUsernameTips=document.getElementById("username_tips");
	var oPasswdTips=document.getElementById("passwd_tips");
	var oMobileTips=document.getElementById("mobile_tips");
	//姓名的判断
	if(oUser.length==''){
		//alert("请输入姓名");
		oUserTips.innerHTML="<p style='color:red;font-size:12px;'>"+"请输入姓名"+"</p>";
	}
	//昵称的判断
	if(oNickname.length==''){
		//alert("请输入昵称");
		oNicknameTips.innerHTML="<p style='color:red;font-size:12px;'>"+"请输入昵称"+"</p>";
	}
	//用户名的判断
	if(oUsername.length==''){
		//alert("请输入账号");
		oUsernameTips.innerHTML="<p style='color:red;font-size:12px;'>"+"请输入账号"+"</p>";
	}
	if(oUsername.length<8){
		//alert("账号需大于8位小于11位");
		oUsernameTips.innerHTML="<p style='color:red;font-size:12px;'>"+"账号应大于8位小于11位"+"</p>";
	}
	if(oUsername.length>16){
		//alert("账号需大于8位小于11位")
		oUsernameTips.innerHTML="<p style='color:red;font-size:12px;'>"+"账号应大于8位小于11位"+"</p>";
	}
	
	//密码的判断
	if(oPw.length==''){
		//alert("请输入密码");
		oPasswdTips.innerHTML="<p style='color:red;font-size:12px;'>"+"请输入密码"+"</p>";
	}
	if(oPw.length<6){
		//alert("密码应该大于6位小于16位");
		oPasswdTips.innerHTML="<p style='color:red;font-size:12px;'>"+"密码应大于6位小于16位"+"</p>";
	}
	if(oPw.length>16){
		//alert("密码应该大于6位小于16位");
		oPasswdTips.innerHTML="<p style='color:red;font-size:12px;'>"+"密码应大于6位小于16位"+"</p>";
	}
	//电话号码的判断
	if(oMobile.length==''){
		//alert("请输入您的电话号码");
		oMobileTips.innerHTML="<p style='color:red;font-size:12px;'>"+"请输入您的电话号码"+"</p>";
	}
	if(oMobile.length<11){
		//alert("请输入正确的电话号码");
		oMobileTips.innerHTML="<p style='color:red;font-size:12px;'>"+"请输入正确的电话号码"+"</p>";
	}
	if(oMobile.length>11){
		//alert("请输入正确的电话号码");
		oMobileTips.innerHTML="<p style='color:red;font-size:12px;'>"+"请输入正确的电话号码"+"</p>";
	}
	return true;
}


