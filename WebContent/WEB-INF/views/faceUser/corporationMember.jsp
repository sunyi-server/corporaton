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
<script type="text/javascript">
	
	var top = document.documentElement.clientHeight/2;
	var left = document.documentElement.clientWidth/2-5;
	var loadDiv = "<div id='loadDiv' style='position: absolute; top:200px; left:"+left+"px;'><img src='../img/loading.gif'></div>";
	
	document.write(loadDiv);
	
	document.onreadystatechange = loadOver;
	
	function loadOver()
	{
		if(document.readyState == 'complete')
			{
				var load = document.getElementById("loadDiv");
				load.setAttribute("style", "display:none;");
				var allContent = document.getElementById("allContent");
				allContent.setAttribute("style", "");
			}
		
	}
</script>

</head>
<!-- 加上 overflow: initial;防止打开模态框时页面移动问题 -->
<body style="padding: 0; margin: 0;">
		<div id="allContent" style="display:none;">
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
						<button type="button" class="btn btn-success  btn-sm"><span class="glyphicon glyphicon glyphicon-download-alt"></span> 导入</button>
						<button type="button" class="btn btn-warning  btn-sm" data-toggle="modal" data-target="#exportModal"><span class="glyphicon glyphicon-share"></span> 导出</button>
						<button type="button" class="btn btn-primary  btn-sm" data-toggle="modal" data-target="#addModal"><span class="glyphicon glyphicon glyphicon-plus"></span> 追加</button>
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
									<th>操作</th>
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
										<s:if test="#list.member_role==0">
											普通用户
										</s:if>
										<s:elseif test="#list.member_role==1">
											管理员
										</s:elseif>
										<s:elseif test="#list.member_role==2">
											社团负责人
										</s:elseif>
									</td>
									<td><s:property value="#list.member_join_time"/></td>
									<td><s:if test="#list.member_status==0">
											退会
										</s:if>
										<s:else>
											在会
										</s:else>
									 </td>
									<td>
										<button value='<s:property value="#list.member_id"/>' onclick="deleteMember(this)" type="button" class="btn btn-danger  btn-sm"><span class="glyphicon glyphicon-remove " aria-hidden="true"></span>删除</button>
										<button value='<s:property value="#list.member_id"/>' type="button" data-toggle="modal" data-target="#updateModal" class="btn btn-primary  btn-sm" onclick="getMemberById(this)"><span class="glyphicon glyphicon-pencil " aria-hidden="true"></span>修改</button>
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





<!-- ---------------------------------------------------------------------------追加模态框------------------------------------------------------------------------------------------------------------------------------------------------------ -->		
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">添加会员</h4>
      </div>
      <div class="modal-body" style="margin:0 auto;">
        	<label>姓名</label><input name="member_name" id="member_name" type="text" class="modelInput"><br>
        	<label >性别</label>
        	<select name="member_sex" class="modelInput" id ="member_sex">
        		<option value="0">女</option>
        		<option value="1">男</option>
        	</select>
        	<br>
        	<label>电话</label><input name="member_telephone" id="member_telephone" type="text" class="modelInput"><br>
			<label >出生日期</label><input name="member_birthday" type="text" class="modelInput modelInputDate" value="0000-00-00" id="datetimeBirthday" readonly="true"/><br>
        	<label >学号</label><input name="member_num" id="member_num" type="text" class="modelInput"><br>
        	<label >会员学院</label><input name="member_college" id="member_college" type="text" class="modelInput"><br>
        	<label >会员专业</label><input name="member_major" id="member_major" type="text" class="modelInput"><br>
        	<label >会员班级</label><input name="member_class" id="member_class" type="text" class="modelInput"><br>
        	<label>会员部门</label>
        	<select name="member_department" class="modelInput" id ="member_department">
        		<s:iterator value="memberVO.department" id="department">
        			<option value="<s:property value="#department.department_name"/>"><s:property value="#department.department_name"/></option>
        		</s:iterator>
        	</select>
        	<br>
        	<label >会员职务</label><input name="member_position" id="member_position" type="text" class="modelInput"><br>
        	<label >会员家庭住址</label><input name="member_home_address" id="member_home_address" type="text" class="modelInput"><br>
        	<label >会员qq号</label><input name="member_qq" id="member_qq" type="text" class="modelInput"><br>
        	<label >会员加入时间</label><input name="member_join_time" type="text" class="modelInput modelInputDate" value="0000-00-00" id="datetimeJoin" readonly="true"><br>
       		<label >会员退会时间</label><input  name="member_member_quit_time" type="text" class="modelInput modelInputDate" value="9999-99-99" id="datetimeQuit" readonly="true"><br> 	
        	<label >会员状态</label>
        	<select name="member_status" class="modelInput" id="member_status_add" >
        		<option value="1">在会</option>
        		<option value="0">退会</option>
        	</select>
        	
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" onclick="ajaxUploadMember()">保存</button>
      </div>
    </div>
  </div>
</div>

<!-- ---------------------------------------------------------------------------追加模态框结束------------------------------------------------------------------------------------------------------------------------------------------------------ -->
<div class="modal fade" id="exportModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">按条件导出会员</h4>
      </div>
      <div class="modal-body" style="margin:0 auto;">
       <label>会员部门</label>
       	<select name="member_department" class="modelInput" id ="member_department_export">
        	<option></option>
        	<s:iterator value="memberVO.department" id="departmentExport">
        		<option value="<s:property value="#departmentExport.department_name"/>"><s:property value="#departmentExport.department_name"/></option>
        	</s:iterator>
        </select>
        <br>
       <label>性别</label>
        <select name="member_sex" class="modelInput" id ="member_sex_export">
        	<option></option>
        	<option value="0">女</option>
        	<option value="1">男</option>
        </select>
        <br>
        <label>在会状态</label>
      	<select name="member_status" id="member_status_export" class="modelInput">
      		<option></option>
        	<option value="0">退会</option>
        	<option value="1">在会</option>
        </select>
        <br>
        <label >搜索开始时间</label> <input name="startTime" type="text" class="modelInput modelInputDate" value="0000-00-00" id="startTimeExport" readonly="true"><br>
        <label >搜索结束时间</label><input name="endTime" type="text" class="modelInput modelInputDate" value="9999-99-99" id="endTimeExport" readonly="true"><br>
        <hr>
        <div id="selectedColumn">
        
        </div>
        <hr>
        <!-- class="glyphicon glyphicon-remove " -->
        <div id="unselectedColumn">
        	<button value="Member_telephone" name="unselectedColumn" onclick="chooseColumn(this)" type="button" style="margin-top:3px; height:30px;" class="btn btn-dafault  btn-sm">
        		<span  aria-hidden="true" class="glyphicon glyphicon-plus">会员电话</span>
        	</button>
        	<button value="Member_num" name="unselectedColumn" onclick="chooseColumn(this)" type="button" style="margin-top:3px; height:30px;" class="btn btn-dafault  btn-sm">
        		<span  aria-hidden="true" class="glyphicon glyphicon-plus">会员学号</span>
        	</button>
        	<button value="Member_birthday" name="unselectedColumn" onclick="chooseColumn(this)" type="button" style="margin-top:3px; height:30px;" class="btn btn-dafault  btn-sm">
        		<span  aria-hidden="true" class="glyphicon glyphicon-plus">会员生日</span>
        	</button>
        	<button value="Member_college" name="unselectedColumn" onclick="chooseColumn(this)" type="button" style="margin-top:3px; height:30px;" class="btn btn-dafault  btn-sm">
        		<span  aria-hidden="true" class="glyphicon glyphicon-plus">会员学院</span>
        	</button>
        	<button value="Member_major" name="unselectedColumn" onclick="chooseColumn(this)" type="button" style="margin-top:3px; height:30px;" class="btn btn-dafault  btn-sm">
        		<span  aria-hidden="true" class="glyphicon glyphicon-plus">会员专业</span>
        	</button>
        	<button value="Member_class" name="unselectedColumn" onclick="chooseColumn(this)" type="button" style="margin-top:3px; height:30px;" class="btn btn-dafault  btn-sm">
        		<span  aria-hidden="true" class="glyphicon glyphicon-plus">会员班级</span>
        	</button>
        	<button value="Member_department" name="unselectedColumn" onclick="chooseColumn(this)" type="button" style="margin-top:3px; height:30px;" class="btn btn-dafault  btn-sm">
        		<span  aria-hidden="true" class="glyphicon glyphicon-plus">会员部门</span>
        	</button>
        	<button value="Member_position" name="unselectedColumn" onclick="chooseColumn(this)" type="button" style="margin-top:3px; height:30px;" class="btn btn-dafault  btn-sm">
        		<span  aria-hidden="true" class="glyphicon glyphicon-plus">会员职位</span>
        	</button>
        	<button value="Member_home_address" name="unselectedColumn" onclick="chooseColumn(this)" type="button" style="margin-top:3px; height:30px;" class="btn btn-dafault  btn-sm">
        		<span  aria-hidden="true" class="glyphicon glyphicon-plus">会员家庭住址</span>
        	</button>
        	<button value="Member_qq" name="unselectedColumn" onclick="chooseColumn(this)" type="button" style="margin-top:3px; height:30px;" class="btn btn-dafault  btn-sm">
        		<span  aria-hidden="true" class="glyphicon glyphicon-plus">会员qq</span>
        	</button>
        	<button value="Member_join_time" name="unselectedColumn" onclick="chooseColumn(this)" type="button" style="margin-top:3px; height:30px;" class="btn btn-dafault  btn-sm">
        		<span  aria-hidden="true" class="glyphicon glyphicon-plus">会员入会时间</span>
        	</button>
        	<button value="Member_quit_time" name="unselectedColumn" onclick="chooseColumn(this)" type="button" style="margin-top:3px; height:30px;" class="btn btn-dafault btn-sm">
        		<span  aria-hidden="true" class="glyphicon glyphicon-plus">会员退会时间</span>
        	</button>
        	<button value="Member_status" name="unselectedColumn" onclick="chooseColumn(this)" type="button" style="margin-top:3px; height:30px;" class="btn btn-dafault  btn-sm">
        		<span  aria-hidden="true" class="glyphicon glyphicon-plus">会员状态</span>
        	</button>
        	<button value="Member_sex" name="unselectedColumn" onclick="chooseColumn(this)" type="button" style="margin-top:3px; height:30px;" class="btn btn-dafault  btn-sm">
        		<span  aria-hidden="true" class="glyphicon glyphicon-plus">会员性别</span>
        	</button>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" onclick="exportExcel()">导出</button>
      </div>
    </div>
  </div>
</div>
		
		
		
		
<!-- ---------------------------------------------------------------------------导出模态框结束------------------------------------------------------------------------------------------------------------------------------------------------------ -->
<!-- ---------------------------------------------------------------------------修改模态框开始------------------------------------------------------------------------------------------------------------------------------------------------------ -->



<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">修改会员</h4>
      </div>
      <div class="modal-body" style="margin:0 auto;">
      	<form method="post" action="<%=basePath %>manager/manager_updateMemberInfo" onsubmit="document.charset='utf-8';"  accept-charset="utf-8" >
        	<input name="member_name" id="member_name_update" type="text" class="modelInput"><label style="margin-left: 20px;">姓名</label><br>
        	<select name="member_sex" class="modelInput" id ="member_sex_update">
        		<option value="0">女</option>
        		<option value="1">男</option>
        	</select>
        	<label style="margin-left: 20px;">性别</label>
        	<br>
        	<input name="member_telephone" id="member_telephone_update" type="text" class="modelInput"><label style="margin-left: 20px;">电话</label><br>
			<input name="member_birthday" type="text" class="modelInput modelInputDate" value="0000-00-00" id="datetimeBirthday_update" readonly="true"/><label style="margin-left: 20px;">出生日期</label><br>
        	<input name="member_num" id="member_num_update" type="text" class="modelInput"><label style="margin-left: 20px;">学号</label><br>
        	<input name="member_college" id="member_college_update" type="text" class="modelInput"><label style="margin-left: 20px;">会员学院</label><br>
        	<input name="member_major" id="member_major_update" type="text" class="modelInput"><label style="margin-left: 20px;">会员专业</label><br>
        	<input name="member_class" id="member_class_update" type="text" class="modelInput"><label style="margin-left: 20px;">会员班级</label><br>
        	<select name="member_department" class="modelInput" id ="member_department_update">
        		<s:iterator value="memberVO.department" id="department">
        			<option value="<s:property value="#department.department_name"/>"><s:property value="#department.department_name"/></option>
        		</s:iterator>
        	</select>
        	<label style="margin-left: 20px;">会员部门</label><br>
        	<input name="member_position" id="member_position_update" type="text" class="modelInput"><label style="margin-left: 20px;">会员职务</label><br>
        	<input name="member_home_address" id="member_home_address_update" type="text" class="modelInput"><label style="margin-left: 20px;">会员家庭住址</label><br>
        	<input name="member_qq" id="member_qq_update" type="text" class="modelInput"><label style="margin-left: 20px;">会员qq号</label><br>
        	<input name="member_join_time" type="text" class="modelInput modelInputDate" value="0000-00-00" id="datetimeJoin_update" readonly="true"><label style="margin-left: 20px;">会员加入时间</label><br>
        	<input name="member_quit_time" type="text" class="modelInput modelInputDate" value="9999-99-99" id="datetimeQuit_update" readonly="true"><label style="margin-left: 20px;">会员退会时间</label><br>
        	<select name="member_status" id="member_status_update" class="modelInput">
        		<option value="1">在会</option>
        		<option value="0">退会</option>
        	</select>
        	<label style="margin-left: 20px;">会员状态</label><br>
        	<input name="member_user"  id="member_user_update" type="text" readonly="true" class="modelInput" style="display: none;">
        	<input name="member_role" id="member_role_update" type="text" readonly="true" class="modelInput"><label style="margin-left: 20px;">会员角色</label><br>
        	<input name="member_corporation" id="member_corporation_update" type="text" readonly="true"class="modelInput"><label >所属社团</label><br>
        	<input name="member_gmt_create"  id="member_gmt_create_update" type="text" style="display: none" readonly="true">
        	<input name="member_gmt_modified" id="member_gmt_modified_update" type="text" style="display: none" readonly="true">
        	<input name="member_id" type="text" id="member_id_update" style="display: none" readonly="true">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <input type="submit" class="btn btn-primary" value="保存">
        </form>
      </div>
    </div>
  </div>
</div>

</div>
<!-- ---------------------------------------------------------------------------修改模态框结束------------------------------------------------------------------------------------------------------------------------------------------------------ -->
		
		
		
<%-- 		<script type="text/javascript" src="<%=basePath %>js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/bootstrap.min.js"></script> --%>
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
			$('#datetimeBirthday').datetimepicker({
				language: 'zh-CN', //显示中文
				format: 'yyyy-mm-dd', //显示格式
				minView: "month", //设置只显示到月份
				initialDate: new Date(), //初始化当前日期
				autoclose: true, //选中自动关闭
				todayBtn: true //显示今日按钮
			})
			$('#datetimeJoin').datetimepicker({
				language: 'zh-CN', //显示中文
				format: 'yyyy-mm-dd', //显示格式
				minView: "month", //设置只显示到月份
				initialDate: new Date(), //初始化当前日期
				autoclose: true, //选中自动关闭
				todayBtn: true //显示今日按钮
			})
			$('#datetimeQuit').datetimepicker({
				language: 'zh-CN', //显示中文
				format: 'yyyy-mm-dd', //显示格式
				minView: "month", //设置只显示到月份
				initialDate: new Date(), //初始化当前日期
				autoclose: true, //选中自动关闭
				todayBtn: true //显示今日按钮
			})
			$('#datetimeBirthday_update').datetimepicker({
				language: 'zh-CN', //显示中文
				format: 'yyyy-mm-dd', //显示格式
				minView: "month", //设置只显示到月份
				initialDate: new Date(), //初始化当前日期
				autoclose: true, //选中自动关闭
				todayBtn: true //显示今日按钮
			})
			$('#datetimeJoin_update').datetimepicker({
				language: 'zh-CN', //显示中文
				format: 'yyyy-mm-dd', //显示格式
				minView: "month", //设置只显示到月份
				initialDate: new Date(), //初始化当前日期
				autoclose: true, //选中自动关闭
				todayBtn: true //显示今日按钮
			})
			$('#datetimeQuit_update').datetimepicker({
				language: 'zh-CN', //显示中文
				format: 'yyyy-mm-dd', //显示格式
				minView: "month", //设置只显示到月份
				initialDate: new Date(), //初始化当前日期
				autoclose: true, //选中自动关闭
				todayBtn: true //显示今日按钮
			})
			$('#startTimeExport').datetimepicker({
				language: 'zh-CN', //显示中文
				format: 'yyyy-mm-dd', //显示格式
				minView: "month", //设置只显示到月份
				initialDate: new Date(), //初始化当前日期
				autoclose: true, //选中自动关闭
				todayBtn: true //显示今日按钮
			})
			$('#endTimeExport').datetimepicker({
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