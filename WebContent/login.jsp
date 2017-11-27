<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8">
		<title>登录注册表单</title>
		<link rel="stylesheet" href="<%=basePath %>css/login.css" />
		<script type="text/javascript" src="<%=basePath %>js/jquery-1.9.0.min.js" ></script>			 
		<script type="text/javascript" src="<%=basePath %>js/login.js" ></script>
		<script>
			window.onload=function(){
				var oBtn1=document.getElementById("btn1");				
				oBtn1.onclick=checkValue;				
			};
		</script>
	</head>
<body>
		<div class="login">
			<div class="header">
		        <div class="switch" id="switch">
		        	<a class="switch_btn_focus" id="switch_qlogin" href="javascript:void(0);" tabindex="7">快速登录</a>
					<a class="switch_btn" id="switch_login" href="javascript:void(0);" tabindex="8">快速注册</a>
					<div class="switch_bottom" id="switch_bottom" style="position: absolute; width: 64px; left: 0px;"></div>
		        </div>
	   		</div>
	   		
			<div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">
		        <!--登录-->
		        <div class="web_login" id="web_login"> 
		        	<div class="login-box">        
						<div class="login_form">
							<form action="<%=basePath %>user_login" name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="post">
								<input type="hidden" name="did" value="0"/>
				              	<input type="hidden" name="to" value="log"/>
				              	<!--登录时候的提示显示位置-->
				              	<div class="tips" style="height:30px;font-size:14px;"></div>
				                <div class="uinArea" id="uinArea">
				                	<label class="input-tips" for="u">账号：</label>
				                	<div class="inputOuter" id="uArea">	                    
				                    	<input type="text" id="u" name="user_username" class="inputstyle"/>
				               		</div>
				                </div>
				                <div class="pwdArea" id="pwdArea">
					                <label class="input-tips" for="p">密码：</label> 
					                <div class="inputOuter" id="pArea">                    
					                    <input type="password" id="p" name="user_password" class="inputstyle"/>
					                </div>
				                </div>	               
				                <div style="padding-left:50px;margin-top:20px;">
				                	<input type="submit" value="登 录" style="width:150px;" class="button_blue"/>
				                </div>
				            </form>
				      	</div>           
		    		</div>
		           
		    	</div>
		        <!--登录end-->
	  		</div>
	  		
	  		<!--注册-->
		    <div class="qlogin" id="qlogin" style="display: none;">   
			    <div class="web_login">
			    	<form name="form2" id="regUser" accept-charset="utf-8"  action="user_registration" method="post">
				      	<input type="hidden" name="to" value="reg"/>
					    <input type="hidden" name="did" value="0"/>
			        	<ul class="reg_form" id="reg-ul">
			        		<div id="userCue" class="cue">快速注册请注意格式</div>			        		
			                <li>                	
			                    <label for="user"  class="input-tips2">姓名<em>*</em></label>
			                    <div class="inputOuter2">
			                        <input type="text" id="user" name="user_name" maxlength="16" class="inputstyle2"
			                        placeholder="请输入你的姓名" required  />
			                        	
			                    </div>
			                    <div id="user_tips">
			                    	<!--data-rule-name="true" data-msg-required="不能为空"
			                         data-msg-name="请输入正确的数字"-->
			                    </div>
			                </li> 
			                <li>                	
			                    <label for="nickname"  class="input-tips2">昵称<em>*</em></label>
			                    <div class="inputOuter2">
			                        <input type="text" id="nickname" name="user_nickname" maxlength="16" class="inputstyle2"
			                        	placeholder="请输入你的昵称" required />
			                    </div> 
			                    <div id="nickname_tips">
			                    	<!--data-rule-nickname="true" data-msg-required="不能为空"
			                         data-msg-nickname="请输入正确的数字"-->
			                    </div>
			                </li>
			                <li>                	
			                    <label for="username"  class="input-tips2">账号<em>*</em></label>
			                    <div class="inputOuter2">
			                        <input type="text" id="username" name="user_username" maxlength="11" class="inputstyle2"
			                        required data-rule-username="true"  />
			                    </div>  
			                    <div id="username_tips">
			                    	<!--data-msg-required="不能为空" data-msg-username="请输入正确的数字"
			                        data-msg-minlength="请输入最小8位" minlength="8"-->
			                    </div>
			                </li>
			                <li>
			                <label for="password" class="input-tips2">密码<em>*</em></label>
			                    <div class="inputOuter2">
			                        <input type="password" id="passwd"  name="user_password" maxlength="16" class="inputstyle2"
			                        required data-rule-password="true" />
			                    </div> 
			                    <div id="passwd_tips">
			                    	<!--data-msg-required="不能为空" data-msg-password="请输入正确的数字"
			                        data-msg-minlength="请输入最小6位" minlength="6" maxlength="16"-->
			                    </div>
			                </li>
			                <li class="bl-form-group bl-form-text">
			                	<label for="sex"  class="input-tips2">性别</label>
			                	<div class="inputOuter2"   class="controls">
			                		<label><input class="radio1" type="radio" name="user_sex" value="1" required />男 </label>
			                		<label><input class="radio2" type="radio" name="user_sex" value="0" required />女 </label>
			                		<span for="radio1" class="error"></span>
			                	</div>
			                </li>
			                <li>
			                	<label for="birthday" class="input-tips2">出生年月</label>
			                	<!--<div>-->
			                		<div id="ymd" name="reg_testdate" class="inputOuter2">
			                			<!--<select name="YYYY" onchange="YYYYDD(this.value)" class="ymd">
										    <option value="">年</option>
										</select>
										<select name="MM" onchange="MMDD(this.value)" class="ymd">
										    <option value="">月</option>
										</select>
										<select name="DD" class="ymd">
										    <option value="">日</option>
										</select>-->
										<select class="gbiaps_birthday_year" name="birthday_year" id="year"></select>
										<select class="gbiaps_birthday_month" name="birthday_month" id="month"></select>
										<select class="gbiaps_birthday_day" name="birthday_day" id="day"></select>
			                		</div>
			                	<!--</div>-->
			                </li>
			                <li>
			                	<label for="birthday"  class="input-tips2">地址</label>
			                	<div class="inputOuter2">
			                        <input type="text" id="address" name="user_address" class="inputstyle2"/>
			                    </div>
			                </li>
			                <li>
			                	<label for="introduce"  class="input-tips2">签名</label>
			                	<div class="inputOuter2">
			                        <input type="text" id="introduce" name="user_introduce" class="inputstyle2"/>
			                    </div>
			                </li>
			                <li>
			                	<label for="phone" class="input-tips2">手机号码<em>*</em></label>
			                	<div class="inputOuter2">			                		
			                		<input type="text"  id="mobile" name="user_telephone" class="inputstyle2"  value=""  class="fn-tinput" 
			                		required data-rule-mobile="true"  />
			                	</div>
			                	<div id="mobile_tips">
			                    	<!--data-msg-required="不能为空" data-msg-mobile="请输入正确的数字"
			                        data-msg-minlength="请输入最小11位" minlength="11"-->
			                    </div>
			                </li>
			                <li>
			                	<input type="submit" value="提交" style="width:100px;" class="button_blue" id="btn1"/>
			            		<input type="reset" value="重置" style="width:100px;" class="button_blue" id="btn2"/>
			                </li>
			            </ul>			            
			    	</form>  
			    </div>  
		    </div>    
	  		
	  		 <script src="js/jquery-1.9.0.min.js"></script>	
	  		 <script type="text/javascript" src="js/jquery.js" ></script>
	  		<!--<script>
	  			//配置错误提示的节点，默认为label，这里配置成 span （errorElement:'span'）
				$.validator.setDefaults({
					errorElement:'span'
				});
				
				//配置通用的默认提示语
				$.extend($.validator.messages, {
					required: '必填',
				    equalTo: "请再次输入相同的值"
				});
				//姓名的验证
				jQuery.validator.addMethod("name",function(value,element){
					var name= /^[\u4E00-\u9FFF]+$/;
					return this.optional(element)||(name.test(value));
				},"姓名输入有误");
				
				//昵称的验证
				jQuery.validator.addMethod("nickname",function(value,element){
					var nickname= /^[\u4E00-\u9FFF]+$/;
					return this.optional(element)||(nickname.test(value));
				},"姓名输入有误");
				//密码的验证
				jQuery.validator.addMethod("password",function(){
//					var password=/[^\w\.\/]/ig;
					var password= /(?!^\d+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]{4,23}/;
					return this.optional(element)||(password.test(value));
				},"密码格式错误");
	  			
				//手机验证规则  ,有问题
				jQuery.validator.addMethod("mobile", function (value, element) {
				    var mobile = /^1[3|4|5|7|8]\d{9}$/;
				    /*if(mobile<11){
				    	$(element).data('error-msg','长度不能少于6位'); 
				    	return flase;
				    }*/
					return this.optional(element) || (mobile.test(value));
				}, "手机格式不对");
				
				//账号验证规则  
				jQuery.validator.addMethod("username", function (value, element) {
				    var username = /^\d{7,8}$/;
				    return this.optional(element) || (username.test(value));
				}, "账号如：17569845");
	  		</script>-->
	  		<!--出生日期的-->
	  		<script type="text/javascript">
	  			jQuery.noConflict();  
                jQuery(function ($) {  
                    var $birthYear = $('.gbiaps_birthday_year');  
                    var year = new Date().getFullYear();  
                    $('<option value="' + (year - 1) + '" selected="selected" >' + (year - 1) + '</option>').appendTo($birthYear);  
                    for (var i = 2; i <= 30; i++) {  
                        var y = year - i;  
                        $('<option value="' + y + '" >' + y + '</option>').appendTo($birthYear);  
                    }  
                  
                    var $birthMonth = $('.gbiaps_birthday_month');  
                    $('<option value="1" selected="selected">1</option>').appendTo($birthMonth);  
                    for (var m = 2; m <= 12; m++) {  
                        $('<option value="' + m + '">' + m + '</option>').appendTo($birthMonth);  
                    }  
                  
                    var $birthDay = $('.gbiaps_birthday_day');  
                    $('<option value="1" selected="selected">1</option> ').appendTo($birthDay);  
                    for (var d = 2; d <= 31; d++) {  
                        $('<option value="' + d + '" >' + d + '</option> ').appendTo($birthDay);  
                    }  
                  
                    //$birthYear.change(onBirthChange);  
                    //$birthMonth.change(onBirthChange);  
                    //$birthDay.change(onBirthChange);  
                      
                    $birthYear.bind('change',onBirthChange);  
                    $birthMonth.bind('change',onBirthChange);  
                    $birthDay.bind('change',onBirthChange);  
                  
                    function onBirthChange() {  
                          
                        var year = $birthYear.find('option:selected').val();  
                        var month = $birthMonth.find('option:selected').val();  
                        var day = $birthDay.find('option:selected').val();  
                          
                        switch (month - 0) {  
                            case 4:  
                            case 6:  
                            case 9:  
                            case 11:  
                                if (day > 30) {  
                                    setBirthDate(year, month, 30);  
                                }  
                                showData(29,'show');  
                                showData(30,'show');  
                                showData(31,'hide');  
                                break;  
                            case 2:  
                  
                                if (!isLeapYear(year)) {  
                                    showData(29,'hide');  
                                    if (day > 28)  
                                        setBirthDate(year, 2, 28);  
                                }else{  
                                    if (day > 29)  
                                        setBirthDate(year, 2, 29);  
                                    showData(29,'show');  
                                }  
                                showData(30,'hide');  
                                showData(31,'hide');  
                                break;  
                            case 1:  
                            case 3:  
                            case 5:  
                            case 7:  
                            case 8:  
                            case 10:  
                            case 12:  
                                showData(29,'show');  
                                showData(30,'show');  
                                showData(31,'show');  
                                break;  
                        }  
                  
                    }  
                      
                    function showData(i,value){  
                        if(value == 'show'){  
                            //console.log($birthDay.find('option[value="'+ i +'"]').is('option'));  
                            if(!$birthDay.find('option[value="'+ i +'"]').is('option'))  
                                $('<option value="' + i + '" >' + i + '</option> ').appendTo($birthDay);  
                        }  
                        if(value == 'hide'){  
                            if($birthDay.find('option[value="'+ i +'"]'))  
                                $birthDay.find('option[value="'+ i +'"]').remove();  
                        }  
                    }  
                  
                    //setBirthDate(1985, 9, 19);  
                    var birthDate = $birthYear.attr('data-birthday').split('-');  
                      
                    setBirthDate(parseInt(birthDate[0]), parseInt(birthDate[1]), parseInt(birthDate[2]));  
                  
                    /**  
                  判断year是否闰年  
                     */  
                    function isLeapYear(year) {  
                        return(0 == year % 4 && (year % 100 != 0 || year % 400 == 0));  
                }  
                  
                    function setBirthDate(year, month, day) {  
                        $birthYear.val(year);  
                        $birthMonth.val(month);  
                        $birthDay.val(day);  
                    }  
                  
                });  
	  		</script>
			
		</div>
	</body>
</html>