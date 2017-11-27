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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:property value="VO.currCorporation"/></title>
<link rel="stylesheet" href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath %>css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath %>css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="<%=basePath %>css/admin.css" />
</head>
<style type="text/css">
header{
	width: 100%;
	position: fixed;
	top: 0px;
	height: 60px;
	z-index:1;
	background: #000000;
}
</style>
<body style="padding: 0; margin: 0; overflow: initial;">

	<!-- ----------------------------------------- 隐藏信息开始------------------------------------------------------------------  -->
	<span id="currCorporation" style="display:none;"></span>
	<input id="queryName" readonly="true"  style="display: none;">
	<input id="querySort" readonly="true"  style="display: none;">
	<input id="queryStartTime" readonly="true"  style="display: none;">
	<input id="queryEndTime" readonly="true"  style="display: none;">
	
	<!-- ----------------------------------------- 隐藏信息结束------------------------------------------------------------------  -->
	<jsp:include page="/header.jsp" flush="true"></jsp:include>
	
	
	<!-- -------------------------------------------------主体内容-----------------------------------------------------            -->
	
	<div class="wrapper">
	
		<!-- 侧边栏 -->
		<jsp:include page="/corporationSideNav.jsp" flush="true"></jsp:include>
			
		<article class="info">
			<!-- 顶部 -->
				<section class="form-show">
										<div class="select-large">
						<input type="text" placeholder="请输入姓名" id="queryName" value='<s:property value="memberVO.queryName"/>'/>
						<span>从</span>
						<input type="text"  id="datetimeStart" readonly="true"/>
						<span>到</span>
						<input type="text"  id="datetimeEnd" readonly="true"/>
					     <input type="submit" onclick="" value="搜索"/>
					</div>
					<div class="select-small">
						<button type="button" class="btn btn-primary  btn-sm" data-toggle="modal" data-target="#announceModal"><span class="glyphicon glyphicon glyphicon-plus"></span>发布</button>
					</div>
				</section>
			<!-- 中部 -->
			<!-- 表单 -->	
				<div class="form">
					<table class="table table-striped table-hover">
							<thead class="Table-header">
								<tr>
									<th>公告名</th>
									<th>公告发布人</th>
									<th>是否置顶</th>
									<th>公告发布时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="" id="list">
								<tr>
									
								</tr>
							</s:iterator>
							</tbody>
						</table>
				</div>	
			<!-- 底部 -->	
				<div class="page-go">共有<span><s:property value="VO.totalSize"/></span>条记录
					<span>共<span id="totalPage"><s:property value="VO.totalPage"/></span>页</span>
					<input type="text" id="currPage" value='<s:property value="VO.currPage"/>'><a class="go" onclick="jumpToPage()">跳转</a>
					<a class="go" onclick="indexPage()">首页</a><a class="go" onclick="lastPage()">上一页</a><a class="go" onclick="nextPage()">下一页</a><a class="go" onclick="finalPage()">末页</a></div>
			</article>	
	
	</div>


<!--------------------------------------------------------------------发布模态框开始--------------------------------------------------------------  -->




<!--------------------------------------------------------------------发布模态框结束--------------------------------------------------------------  -->





	<script type="text/javascript" src="<%=basePath %>js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/admin.js"></script>
		<script>
			$('#datetimeStart').datetimepicker({
				language: 'zh-CN', //显示中文
				format: 'yyyy-mm-dd', //显示格式
				minView: "month", //设置只显示到月份
				initialDate: new Date(), //初始化当前日期
				autoclose: true, //选中自动关闭
				todayBtn: true //显示今日按钮
			})
			$('#datetimeEnd').datetimepicker({
				language: 'zh-CN', //显示中文
				format: 'yyyy-mm-dd', //显示格式
				minView: "month", //设置只显示到月份
				initialDate: new Date(), //初始化当前日期
				autoclose: true, //选中自动关闭
				todayBtn: true //显示今日按钮
			})
			$('#datetimepicker').datetimepicker('update');
		</script>
</body>
</html>