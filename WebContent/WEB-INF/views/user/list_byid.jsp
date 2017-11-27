<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
request.setAttribute("path", basePath);  
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>后台管理</title>
		<link rel="stylesheet" href="css/BackStage.css" />
		<script type="text/javascript" src="js/backstage.js" ></script>
		<script type="text/javascript" src="<%=basePath%>js/user/userManager.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/user/toastr.js"></script>
		<link rel="stylesheet"
		href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=basePath%>css/BackStage.css" />
		<script type="text/javascript" src="<%=basePath%>js/user/user.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/user/load.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/user/delete_user.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/backstage.js"></script>
		
		<script>
			window.onload=function(){				
				Click();
				
			};
			
		</script>
	</head>
<body>
		<div class="wrap">
			<div class="left">
				<h1>管理员</h1>
				<div class="content">
					<div id="funcList1" class="picManage">
						<img src="img/picMange.png" alt=""/>
						<span><a href="#">图片管理</a></span>
					</div>
					
					<div id="funcList2" class="CommunityMange"> 
						<img src="img/community.png" alt=""/>
						<span><a href="#">社团管理</a></span>
						<ul>
							<li><a href="#">新建社团</a></li>
							<li><a href="#">社团列表</a></li>
							<li><a href="#">社团类型</a></li>
							<li><a href="#">创建类别</a></li>
						</ul>
					</div>
					<div id="funcList3" class="herfMange">
						<img src="img/href.png" alt="" />
						<span><a href="#">链接管理</a></span>					
					</div>
					<div id="funcList4" class="Manager">
						<img src="img/manager.png" alt="" />
						<span><a href="#">用户管理</a></span>
						<ul>
							<li><a href="#">新建用户</a></li>
							<li><a href="user_page_list_userInfo">用户列表</a></li>
							<li><a href="#">用户功能</a></li>							
						</ul>
					</div>
					<div id="funcList5" class="exit">
						<img src="img/exit.png" alt=""/>
						<span><a href="#">退出系统</a></span>
					</div>
				</div>
				
			</div>
		
			<div id="right" class="right">				
					<div id="right" class="right">				
				<form class="search" action="user_findByUser_name" method="post">
					<input id="text" type="text" placeholder="计算机" name="user_username"/>
					<input type="submit" value="检索" id="btn"/>
					
					
				</form>
				<button class="searchbtn"  onclick="overlay1()" id="add" data-toggle="modal"
						data-target="#useradd">添加</button>
				<div id="Info" class="Info">
					<table>
						<tr class="head">
							<th>发布</th>
							<th>推荐</th>
							<th>所属类别</th>
							<th>社团名称</th>
							<th>关键词</th>
							<th>社团logo</th>
							<th class="peo">阅 读 人 数</th>
							<th class="peo">喜 欢 人 数</th>
							<th class="peo">社 团 人 数</th>
							<th>创建时间</th>
							<th class="in">操作</th>
						</tr>
						<tr class="detail">
							<td>
								<img src="img/icon.png" alt="" />
							</td>
							<td>
								<img src="img/icon.png" alt="" />
							</td>
							<td>理工类</td>
							<td>IT协会</td>
							<td>计算机</td>
							<td>
								<img src="img/logo.png" alt="" />
							</td>
							<td class="people">100</td>
							<td class="people">200</td>
							<td class="people">300</td>
							<td>2015-12-16</td>
							<td class="in">
								<button class="btn1" type="button" onclick="overlay2()">修改</button>
								<button class="btn2" type="button" >删除</button>
							</td>
						</tr>
						
						<tr class="detail">
							<td>
								<img src="img/icon.png" alt="" />
							</td>
							<td>
								<img src="img/icon.png" alt="" />
							</td>
							<td>理工类</td>
							<td>IT协会</td>
							<td>计算机</td>
							<td>
								<img src="img/logo.png" alt="" />
							</td>
							<td class="people">100</td>
							<td class="people">200</td>
							<td class="people">300</td>
							<td>2015-12-16</td>
							<td class="in">
								<button class="btn1" type="button" onclick="overlay2()">修改</button>
								<button class="btn2" type="button">删除</button>
							</td>
						</tr>
						<tr class="detail">
							<td>
								<img src="img/icon.png" alt="" />
							</td>
							<td>
								<img src="img/icon.png" alt="" />
							</td>
							<td>理工类</td>
							<td>IT协会</td>
							<td>计算机</td>
							<td>
								<img src="img/logo.png" alt="" />
							</td>
							<td class="people">100</td>
							<td class="people">200</td>
							<td class="people">300</td>
							<td>2015-12-16</td>
							<td class="in">
								<button class="btn1" type="button" onclick="overlay2()">修改</button>
								<button class="btn2" type="button">删除</button>
							</td>
						</tr>
						<tr class="detail">
							<td>
								<img src="img/icon.png" alt="" />
							</td>
							<td>
								<img src="img/icon.png" alt="" />
							</td>
							<td>理工类</td>
							<td>IT协会</td>
							<td>计算机</td>
							<td>
								<img src="img/logo.png" alt="" />
							</td>
							<td class="people">100</td>
							<td class="people">200</td>
							<td class="people">300</td>
							<td>2015-12-16</td>
							<td class="in">
								<button class="btn1" type="button" onclick="overlay2()">修改</button>
								<button class="btn2" type="button">删除</button>
							</td>
						</tr>						
						<tr class="detail">
							<td>
								<img src="img/icon.png" alt="" />
							</td>
							<td>
								<img src="img/icon.png" alt="" />
							</td>
							<td>理工类</td>
							<td>IT协会</td>
							<td>计算机</td>
							<td>
								<img src="img/logo.png" alt="" />
							</td>
							<td class="people">100</td>
							<td class="people">200</td>
							<td class="people">300</td>
							<td>2015-12-16</td>
							<td class="in">
								<button class="btn1" type="button" onclick="overlay2()">修改</button>
								<button class="btn2" type="button">删除</button>
							</td>
						</tr>
						<tr class="detail">
							<td>
								<img src="img/icon.png" alt="" />
							</td>
							<td>
								<img src="img/icon.png" alt="" />
							</td>
							<td>理工类</td>
							<td>IT协会</td>
							<td>计算机</td>
							<td>
								<img src="img/logo.png" alt="" />
							</td>
							<td class="people">100</td>
							<td class="people">200</td>
							<td class="people">300</td>
							<td>2015-12-16</td>
							<td class="in">
								<button class="btn1" type="button" onclick="overlay2()">修改</button>
								<button class="btn2" type="button">删除</button>
							</td>
						</tr>
						<tr class="detail">
							<td>
								<img src="img/icon.png" alt="" />
							</td>
							<td>
								<img src="img/icon.png" alt="" />
							</td>
							<td>理工类</td>
							<td>IT协会</td>
							<td>计算机</td>
							<td>
								<img src="img/logo.png" alt="" />
							</td>
							<td class="people">100</td>
							<td class="people">200</td>
							<td class="people">300</td>
							<td>2015-12-16</td>
							<td class="in">
								<button class="btn1" type="button" onclick="overlay2()">修改</button>
								<button class="btn2" type="button">删除</button>
							</td>
						</tr>
						
					</table>
					<div class="fy1">
						<span><input class="former" type="button" value="上一页"/></span>
						<span><a>1</a></span>
						<span><a>2</a></span>
						<span><a>3</a></span>
						<span><a>...</a></span>						
						<span><input class="latter" type="button" value="上一页"/></span>
						<span><input class="tail" type="button" value="上一页"/></span>
						<span>跳转到<input id="tarPage" type="text"/>页</span>
						<span><input class="tz" type="button" value="跳转"/></span>
					</div>
				</div>
				<form id="createCommunity" class="createCommunity">
					<table>
						<tr>
							<td>社团名称:</td>
							<td><input id="CommName" type="text"/></td>
						</tr>
						<tr>
							<td>所属类别:</td>
							<td>
								<select>
									<option>理工类</option>
									<option>文学类</option>
									<option>古学类</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>关键词:</td>
							<td><input id="CommKey" type="text" /></td>
						</tr>
						<tr>
							<td>社团logo:</td>
							<td>
								<img src="" alt="" />
							</td>
						</tr>							
					</table>
					<button id="cre" type="button">创建</button>
					<button id="reset" type="button">重置</button>
				</form>


<!--usermanager-->
				<s:if
				test="#request.oneUser == null || #request.oneUser.size() == 0">
						没有该用户
				</s:if>
				<s:else>
				<form id="ManagerForm" class="ManagerForm">
					<table>
							<td>姓名</td>
							<td>昵称</td>
							<td>账号</td>
							<td>出生日期</td>
							<td>签名</td>
							<td>性别</td>
							<td>手机号</td>
							<td>地址</td>
							<td>角色</td>
							<th class="in">操作</th>
						</tr>
						
						<s:iterator value="#request.oneUser">
						<tr>
									<td>${user_name }</td>
								<td>${user_nickname }</td>
								<td>${user_username }</td>
								<td>${user_birthday }</td>
								<td>${user_introduce }</td>
								<td>${user_sex }</td>
								<td>${user_telephone }</td>
								<td>${user_address }</td>
								<td>${user_role }</td>
							<td class="in">
								<!-- <button class="btn1" type="button" onclick="overlay()">修改</button> -->
							
								<button value="${user_id}" onclick="updateUser(this)"
										class="btn1" data-toggle="modal"
										data-target="#useredit">修改</button>
								<%-- <button class="btn2" type="button"><a href="user_delete?user_id=<s:property value="#NACACDTOL.userInfo.user_id"
							escape="false" />"><font
											color=white>删除</a></button> --%>
											
											<button class="btn2" id="${user_id}"
													data-toggle="modal" data-target="#model_delete_userInfo"
													onclick="javascript:delete_user_id=this.id;">删除</button>
							</td>							
						</tr>
						</s:iterator>
					</table>
			</form>
			</s:else>
				
					



<!--修改的模态框-->
				
<!-- 修改的模态框 -->
		<div class="modal fade" id="useredit" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">修改用户</h4>
					</div>

					<form action="user_save" method="post"
						enctype="multipart/form-data">
						<div class="modal-body">
								<input name="user_id" id="user_id_update" type="text"
									   style="display: none;" readonly="true"> 
							用户姓名:<input
									type="text" name="user_name" id="user_name_update"></br> </br>
							用户昵称:<input type="text" name="user_nickname"
									id="user_nickname_update"></br> </br>
						           用户账号:<input type="text"
									name="user_username" id="user_username_update"></br> </br> 
						          用户密码:<input
									type="password" name="user_password" id="user_password_update"></br> </br> 
						           出生日期:<input type="text" name="user_birthday"
									id="user_birthday_update"></br> </br> 
						           用户签名:<input type="text"
									name="user_introduce" id="user_introduce_update"></br> </br> 
							用户性别:<input
									type="radio" name="user_sex" id="user_sex_man" value="1">男
								   <input type="radio" name="user_sex" id="user_sex_woman" value="0">女</br> </br> 
						           手机号码:<input type="text" name="user_telephone"
									id="user_telephone_update"></br> </br> 
							用户地址:<input type="text"
								    name="user_address" id="user_address_update"></br> </br> 
						           用户角色:<input
									type="text" name="user_role" id="user_role_update"></br> </br>
							用户头像:<input type="file" name="logo"  id="user_logo_update">
							<img  id="user_logo_show" alt="" src="" style="width: 300px; height:300px;">
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<%-- <submit class="btn btn-primary">确定</submit> --%>
							<input type="submit" class="btn btn-primary" value="确定">
						</div>
					</form>
				</div>
			</div>
		</div>



<!--添加用户的模块-->
<!-- //添加的模态框 -->
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="useradd" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">添加用户</h4>
					</div>

					<form action="user_save" method="post"
						enctype="multipart/form-data" name="form1"
						onsubmit="return check()">
						<div class="modal-body">
							用户姓名:<input 
									type="text" name="user_name"></br> </br>
						          用户昵称:<input 
						            type="text" name="user_nickname"></br> </br>
						          用户账号:<input
								    type="text" name="user_username"></br> </br> 
						          用户密码:<input
								    type="password" name="user_password"></br> </br> 
						          出生日期:<input
								    type="text" name="user_birthday"></br> </br> 
						          用户签名:<input
								    type="text" name="user_introduce"></br> </br> 
						          用户性别:<input
								    type="radio" name="user_sex" value="1">男 
								  <input
								    type="radio" name="user_sex" value="0">女</br> </br> 
						          手机号码:<input
								    type="text" name="user_telephone"></br> </br> 
							用户地址:<input
								    type="text" name="user_address"></br> </br> 
						           用户角色:<input
								    type="text" name="user_role"></br> </br> 
					                      用户头像:<input 
					                type="file" name="logo">
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭</button>
							<%-- <submit class="btn btn-primary">确定</submit> --%>
							<input type="submit" class="btn btn-primary" value="确定">
						</div>
					</form>
				</div>
			</div>
		</div>
		
		
		<!-- 删除的莫太框 -->
		<div class="modal fade" id="model_delete_userInfo">
		<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">确认信息</h4>
			</div>
			<div class="modal-body">
				<h4 id="h4_delete">删除此用户？</h4>
			</div>
			<div class="modal-footer">
				<button class="btn btn-danger" onclick="delete_user()">删除</button>
				<button class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>
		
		
			</div>
		</div>
		<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>js/bootstrap-datetimepicker.min.js"></script>
	</body>
</html>