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
<title><s:property value="userApplyVO.corporationInfo.corporation_name"/></title>
<link rel="stylesheet" href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath %>css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath %>css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="<%=basePath %>css/admin.css" />
<script type="text/javascript" src="<%=basePath %>js/data/dataApplyUser.js"></script>
</head>
<body>
	<!--------------------------------------------------------- 隐藏内容开始 ----------------------------------------------------- -->
	<span id="currCorporation" style="display:none;"><s:property value="userApplyVO.corporationInfo.corporation_name"/></span>

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
				
				</div>
			<!-- 表单 -->	
				<div class="form">
					<table class="table table-striped table-hover">
							<thead class="Table-header">
								<tr>
									<th>姓名</th>
									<th class="intoTime">申请时间</th><span style="display:none;" name="sortType">desc</span><!-- asc -->
									<th>审核状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<s:iterator value="userApplyVO.listUser" id="list">
								<tr>
									<td><s:property value="#list.user_name"/></td>
									<td><s:property value="#list.into_time"/></td>
									<td>待审核</td>
									<td>
										<button value='<s:property value="#list.into_id"/>' onclick="rejectApply(this)" type="button" class="btn btn-danger  btn-sm"><span class="glyphicon glyphicon-remove " aria-hidden="true"></span>驳回</button>
										<button value='<s:property value="#list.into_id"/>'  data-toggle="modal" data-target="#chooseModel" type="button" class="btn btn-primary  btn-sm" onclick="searchMember(this)"><span class="glyphicon glyphicon-pencil " aria-hidden="true"></span>通过</button>
									</td>
								</tr>
							</s:iterator>
							</tbody>
						</table>
				</div>	
			<!-- 底部 -->	
				<div class="page-go">共有<span><s:property value="userApplyVO.totalSize"/></span>条记录
					<span>共<span id="totalPage"><s:property value="userApplyVO.totalPage"/></span>页</span>
					<input type="text" id="currPage" value='<s:property value="userApplyVO.currPage"/>'><a class="go" onclick="skipToPage()">跳转</a>
					<a class="go" onclick="indexPage()">首页</a><a class="go" onclick="lastPage()">上一页</a><a class="go" onclick="nextPage()">下一页</a><a class="go" onclick="endPage()">末页</a></div>
			</article>
		</div>
		
		
		
		
	<!-- --------------------------------------------------------------------------- -->
		
<div class="modal fade" id="chooseModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">选择绑定的会员</h4>
      </div>
      <div class="modal-body" style="margin:0 auto;">
      		<label style="margin-right: 20px">选择绑定的会员</label>
      		<input type="text" id="currUser"  style="display:none;">
			<select class="inputModel" id="selectMember">
				
			</select>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="agreeApply()">保存</button>
      </div>
    </div>
  </div>
</div>
		
		
		
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