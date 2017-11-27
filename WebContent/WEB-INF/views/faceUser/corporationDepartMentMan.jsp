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
<title><s:property value="departmentVO.corporationInfo.corporation_name"/></title>
<link rel="stylesheet" href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath %>css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath %>css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="<%=basePath %>css/admin.css" />
<script type="text/javascript" src="<%=basePath %>js/data/dataDeparment.js"></script>
</head>
<body>
	<!--------------------------------------------------------- 隐藏内容开始 ----------------------------------------------------- -->
	<span id="currCorporation" style="display:none;"><s:property value="departmentVO.corporationInfo.corporation_name"/></span>

	<!--------------------------------------------------------- 隐藏内容结束 ----------------------------------------------------- -->
		<jsp:include page="/header.jsp" flush="true"></jsp:include>
	
	<!--------------------------------------------------------- 主体内容----------------------------------------------------- -->
	
		<div class="wrapper">
	<!-- ---------------------------------------------------侧边栏------------------------------------------------------ -->
			<jsp:include page="/corporationSideNav.jsp" flush="true"></jsp:include>
			
			
			<article class="info">
			<!-- 顶部 -->
				<section class="form-show">
					
				</section>
			<!-- 中部 -->
				<div class="select-small">
				<button type="button" class="btn btn-primary  btn-sm" data-toggle="modal" data-target="#addModel"><span class="glyphicon glyphicon glyphicon-plus"></span> 追加</button>
				</div>
			<!-- 表单 -->	
				<div class="form">
					<table class="table table-striped table-hover">
							<thead class="Table-header">
								<tr>
									<th>部门名称</th>
									<th>创建时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="departmentVO.corporationDepart" id="list">
								<tr>
									<td><s:property value="#list.department_name"/></td>
									<td><s:property value="#list.department_create_time"/></td>
									<td>
										<button value='<s:property value="#list.department_id"/>' onclick="deleteDepartment(this)" type="button" class="btn btn-danger  btn-sm"><span class="glyphicon glyphicon-remove " aria-hidden="true"></span>删除</button>
										<button value='<s:property value="#list.department_id"/>' type="button" data-toggle="modal" data-target="#updateModel" class="btn btn-primary  btn-sm" onclick="getDepartmentInfo(this)"><span class="glyphicon glyphicon-pencil " aria-hidden="true"></span>修改</button>
									</td>
								</tr>
							</s:iterator>
							</tbody>
						</table>
				</div>	
			<!-- 底部 -->	
				<div class="page-go"></div>
			</article>
		</div>
		
		
		
		
	<!-- ---------------------------------------  追加部门 ------------------------------------ -->
		
<div class="modal fade" id="addModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">追加部门</h4>
      </div>
      <div class="modal-body" style="margin:0 auto;">
      		<!-- <label style="margin-right: 30px;">部门名</label>
      		<input type="text" class="inputModel" id="department_name"><br>
      		<label style="margin-right: 30px;">部门介绍</label>
      		<textarea style="height:200px;" class="form-control textarea" style="width:400px;" rows="6" id="department_introduce"></textarea> -->
      		<form class="bumen-add">
				<div class="bumen-add">
					 <label class="label-add">社团名称:</label>
                     <input id="department_name" type="text"/>
				</div>
					<label class="label-add">编辑简介：</label>
					<textarea id="department_introduce" class="form-control textarea-add"  rows="6"></textarea>
			</form>
			<div class="clear"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="addDepartment()">保存</button>
      </div>
    </div>
  </div>
</div>
		
	<!-- ---------------------------------------  追加部门结束 ------------------------------------ -->	
		
		
		<!-- ---------------------------------------  修改部门 ------------------------------------ -->
		
<div class="modal fade" id="updateModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
     	 <h4 class="modal-title" id="myModalLabel">修改部门信息</h4>
      </div>
      <div class="modal-body" style="margin:0 auto;">
      	 <div class="bumen">
  <!--     		<label>部门名</label>
      		<input type="text" class="inputModel" id="department_name_update" readonly="true"></div>
      		<div class="bumen">
      		<label style="margin-right: 30px;">部门介绍</label>
      		<textarea class="form-control textarea" style="width:400px;" rows="6" id="department_introduce_update"></textarea>
      		</div> -->
      		<form class="bumen">
				<div class="bumen">
					 <label class="label1">社团名称:</label>
                     <input  id="department_name_update" readonly="true" style="margin-left: 30px;" type="text"/>
				</div>
					<label class="label1">编辑简介：</label>
					<textarea id="department_introduce_update" class="form-control textarea" style="width:400px;" rows="6"></textarea>
			</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" id="saveBtn" onclick="updateDepartment(this)">保存</button>
      </div>
    </div>
  </div>
</div>
		
	<!-- ---------------------------------------  修改部门结束 ------------------------------------ -->	
	
	<!-- ------------------------------------------------------------------日期的js----------------------------------------------------- -->
		<script type="text/javascript">
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