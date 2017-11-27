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
<title><s:property value="memberVO.currCorporation"/></title>
<link rel="stylesheet" href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath %>css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath %>css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="<%=basePath %>css/admin.css" />
<script type="text/javascript" src="<%=basePath %>js/data/dataMemberShow.js"></script>
<style type="text/css">

</style>
</head>
<!-- 加上 overflow: initial;防止打开模态框时页面移动问题 -->
<body style="padding: 0; margin: 0; overflow: initial;">
		<!-- 隐藏信息  -->
		
		
		<span id="currCorporation" style="display:none;"><s:property value="memberVO.currCorporation"/></span>
		<span id="currSort" style="display:none;"><s:property value="memberVO.querySort"/></span>
<input type="text" id="currDepartment" value="<%=request.getParameter("queryMemberStatus") %>" style="display:none;">
		<input type="text" id="currStatus" value="<%=request.getParameter("queryDepartment") %>"  style="display:none;">
		
		
		<!-- 隐藏信息结束 -->
		
		
		<jsp:include page="/header.jsp" flush="true"></jsp:include>
		<div class="wrapper">
			
		<!-- -----------------------------------------------侧边栏------------------------------------------------------------------------------ -->		
			
			<jsp:include page="/corporationSideNav.jsp" flush="true"></jsp:include>
			
			<article class="info">
				<section class="form-show">
					<div class="select-large">
						<input type="text" placeholder="请输入姓名" id="queryName" value='<s:property value="memberVO.queryName"/>'/>
						<span>从</span>
						<input type="text"  value="<s:property value="memberVO.queryStartTime"/>" id="datetimeStart" readonly="true"/>
						<span>到</span>
						<input type="text"  value="<s:property value="memberVO.queryEndTime"/>" id="datetimeEnd" readonly="true"/>
						<%-- <button type="button" onclick="queryMember()" class="btn btn-success  btn-sm"><span class="glyphicon glyphicon-search"></span> 搜索</button> --%>
					    <input type="submit" onclick="queryMember()" value="搜索"/>
					</div>
					<div class="select-small">
					<!-- 	<input type="checkbox" checked="checked" class="all-select" /><label class="label1">全选</label>
						<input type="checkbox" class="all-select" /><label class="label2">反选</label> -->
									<!-- <input type="text" placeholder="搜索关键字" /> -->
			        	<!-- 两个下拉使用js来进行搜索  -->
			        	<select name="queryDepartment" onchange="changeDepartment(this)" class="modelInput" style="width:140px;" id ="queryDepartment">
			        			<option></option>
			        		<s:iterator value="memberVO.department" id="department">
			        			<option value="<s:property value="#department.department_name"/>"><s:property value="#department.department_name"/></option>
			        		</s:iterator>
			        	</select>
			        	<label style="margin-left: 20px;">会员部门</label>
			        	<select name="member_status" onchange="changeStatus(this)" id="member_status" style="width:140px;" class="modelInput">
				        		<option></option>
				        		<option value="1">在会</option>
				        		<option value="0">退会</option>
				       	</select>
				        <label style="margin-left: 20px;">会员状态</label>
					</div>
					<div class="form">
						<table class="table table-striped table-hover">
							<thead class="Table-header">
								<tr>
									<th>姓名</th>
									<th>性别</th>
									<th>班级</th>
									<th>职务</th>
									<th>电话</th>
									<th>角色</th>
									<th class="intoTime" onclick="changeSort()">入会时间</th><span style="display:none;" name="sortType">desc</span><!-- asc -->
									<th>状态</th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="memberVO.list" id="list">
								<tr>
									<td><s:property value="#list.member_name"/> </td>
									<td><s:if test="#list.member_sex==1">
										男
									</s:if>
									<s:else>
										女
									</s:else>
									 </td>
									<td><s:property value="#list.member_class"/></td>
									<td><s:property value="#list.member_position"/></td>
									<td><s:property value="#list.member_telephone"/></td>
									<td>
									<select class="inputModel" name="<s:property value="#list.member_id"/>" onclick="changeMemberRole(this)">

										<s:if test="#list.member_role==0">
											<option value="0">普通用户</option>
											<option value="1">管理员</option>
										</s:if>
										<s:elseif test="#list.member_role==1">
											<option value="1">管理员</option>
											<option value="0">普通用户</option>
										</s:elseif>
										<s:elseif test="#list.member_role==2">
											<option value="2">
											社团负责人
											</option>
										</s:elseif>
									</select>
									</td>
									<td><s:property value="#list.member_join_time"/></td>
									<td><s:if test="#list.member_status==0">
											退会
										</s:if>
										<s:else>
											在会
										</s:else>
									 </td>
								</tr>
							</s:iterator>
							</tbody>
						</table>
					</div>
					<div class="page-go">共有<span><s:property value="memberVO.totalSize"/></span>条记录
					<span>共<span id="totalPage"><s:property value="memberVO.totalPage"/></span>页</span>
					<input type="text" id="currPage" value='<s:property value="memberVO.currPage"/>'><a class="go" onclick="jumpToPage()">跳转</a>
					<a class="go" onclick="indexPage()">首页</a><a class="go" onclick="lastPage()">上一页</a><a class="go" onclick="nextPage()">下一页</a><a class="go" onclick="finalPage()">末页</a></div>
				</section>
			</article>
		</div>
	
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