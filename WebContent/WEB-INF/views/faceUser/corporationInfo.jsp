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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="corporationInfo.corporation_name"/></title>
		<link rel="stylesheet" href="<%=basePath %>css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=basePath %>css/bootstrap-datetimepicker.min.css" />
		<link rel="stylesheet" href="<%=basePath %>css/user-info.css" />
		<script type="text/javascript" src="<%=basePath %>js/data/dataCorporationInfo.js"></script>
</head>
<body>
	<!--------------------------------------------------------- 隐藏内容开始 ----------------------------------------------------- -->
	<span id="currCorporation" style="display:none;"><s:property value="corporationInfo.corporation_name"/></span>
	<input type="text" id="corporation_id" value="<s:property value="corporationInfo.corporation_id"/>" style="display: none;">
	<!--------------------------------------------------------- 隐藏内容结束 ----------------------------------------------------- -->
		<jsp:include page="/header.jsp" flush="true"></jsp:include>
	
	<!--------------------------------------------------------- 主体内容----------------------------------------------------- -->
	
		<div class="wrapper-user-info">
	<!-- ---------------------------------------------------侧边栏------------------------------------------------------ -->
			<jsp:include page="/corporationSideNav.jsp" flush="true"></jsp:include>
			<div class="wrapper-1">
			<section class="user-info-title"><label>社团信息</label></section>
			<section class="user-info-content">
				<div class="user-img">
					<img id="corporationLogo" src="<%=basePath %>manager/apply_downloadImg?fileDirc=corporationLogo&filename=<s:property value="corporationInfo.corporation_logo"/>"/>
				</div>
				<div class="user-info">
					<form>
						<ul class="user-info-text">
							<li><label>社团名称</label><input id="corporation_name" readonly="true" type="text" value="<s:property value="corporationInfo.corporation_name"/>"/></li>
							<li><label>社团类别</label><input id="corporation_name"  readonly="true" type="text" value="<s:property value="corporationInfo.corporation_type"/>"/></li>
							<li><label>立社时间</label><input id="corporation_apply_time" class="calender" type="text" value="<s:property value="corporationInfo.corporation_apply_time"/>"/></li>
							<li class="summary"><label>编辑简介</label>
							<textarea readonly="true" class="form-control textarea1" rows="8" id="corporation_introduce"><s:property value="corporationInfo.corporation_introduce"/></textarea></li>
						</ul>
					</form>
				</div>
			</section>
		    </div>
		</div>
		<script type="text/javascript" src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
		<script>
			//日期设置
			$('#datetimeStart2').datetimepicker({
				language: 'zh-CN', //显示中文
				format: 'yyyy-mm-dd', //显示格式
				minView: "month", //设置只显示到月份
				initialDate: new Date(), //初始化当前日期
				autoclose: true, //选中自动关闭
				todayBtn: true //显示今日按钮
			})
			//导航条头像点击弹出框
			$('#datetimepicker').datetimepicker('update');
			$(function() {
				$('[data-toggle="popover"]').popover()
			})
		</script>
</body>
</html>