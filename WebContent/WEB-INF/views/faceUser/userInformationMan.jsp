<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>个人信息管理</title>
		
		<link rel="stylesheet" href="<%=basePath %>css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=basePath %>css/bootstrap-datetimepicker.min.css" />
		<link rel="stylesheet" href="<%=basePath %>css/user-info.css" />
		<script type="text/javascript" src="<%=basePath %>js/data/userInformation.js"></script>
</head>
<body>

		<jsp:include page="/header.jsp" flush="true"></jsp:include>

		<div class="wrapper-user-info">
			<section class="user-info-title"><label>个人资料</label></section>
			<section class="user-info-content">
				<div class="user-img">
					<img id="userLogo" src="<%=basePath %>manager/apply_downloadImg?fileDirc=userLogo&filename=<s:property value="userInforVO.userInfor.user_logo"/>"/>
					<a onclick="stimulateFile()">更改头像</a>
					<input id="changeUserLogo" onchange="changeLogo()" type="file" style="display:none;">
				</div>
				<div class="user-info" >
					<form>
						<ul class="user-info-text">
						<!-- 	private user_information userInfor;
	private List<user_into_corporation> listInto;
						 -->
							<li><label>昵称</label><input id="user_nickname" type="text" value="<s:property value="userInforVO.userInfor.user_nickname"/>" /></li>
							<li><label>手机号</label><input id="user_telephone" type="text" value="<s:property value="userInforVO.userInfor.user_telephone" />"/></li>
							<li><label>地址</label><input id="user_address" type="text" value="<s:property value="userInforVO.userInfor.user_address"/>"/></li>
							<li><label style="margin-right: 5px;">社团</label><s:iterator value="userInforVO.listInto" id="list"> 
								<span><s:property value="#list.user_corporation_include"/></span>
							</s:iterator> </li>
							<li><label>生日</label><input id="user_birthday" style="cursor: pointer;" class="calender"  readonly="true" type="text" value="<s:property value="userInforVO.userInfor.user_birthday"/>" id="datetimeStart1" /></li>
							<li><label style="float:left;">个性签名</label><textarea id="user_introduce" class="form-control textarea" rows="8" id="corporation_introduce"><s:property value="userInforVO.userInfor.user_introduce"/></textarea></li>
						    <li><div class="submit" style="cursor: pointer;" onclick="updateUserInformation()">保存修改</div></li>
						</ul>
					</form>
				</div>
			</section>
		</div>
		<script type="text/javascript" src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
		<script>
			//日期设置
			$('#datetimeStart1').datetimepicker({
				language: 'zh-CN', //显示中文
				format: 'yyyy-mm-dd', //显示格式
				minView: "month", //设置只显示到月份
				initialDate: new Date(), //初始化当前日期
				autoclose: true, //选中自动关闭
				todayBtn: true //显示今日按钮
			})
		</script>
</body>
</html>