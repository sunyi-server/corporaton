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
<title>申请加入社团</title>
		<link rel="stylesheet" href="<%=basePath %>css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=basePath %>css/add-cor.css" />
		<script type="text/javascript" src="<%=basePath %>js/data/userApply.js"></script>
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
	
	<input type="text" value="<s:property value="applyVO.currPage"/>" id="currPage" style="display: none;" readonly="true">
	<input type="text" value="<s:property value="applyVO.totalPage"/>" id="totalPage" style="display: none;" readonly="true">
	<input type="text" value="<s:property value="applyVO.queryName"/>" id="queryname" style="display: none;">

	<!-- ----------------------------------------- 隐藏信息结束------------------------------------------------------------------  -->
	<jsp:include page="/header.jsp" flush="true"></jsp:include>
	<div class="wrapper1">
	<!--固定社团入驻按钮 start-->
	<!--固定社团入驻按钮 end-->
			<article class="join-explain">
				<p><h1>如何加入社团</h1></p>
				<ul class="progress1">
					<!-- <li class="step step-0">
						<p>注册登录</p>
					</li>
					<li class="step"></li>
					<li class="step step-1">
						<p>实名认证</p>
					</li> -->
					<!-- <li class="step"></li> -->
					<li class="step step-2">
						<p>提交申请</p>
					</li>
					<li class="step"></li>
					<li class="step step-3">
						<p>审批通过</p>
					</li>
					<li class="step"></li>
					<li class="step step-4">
						<p>加入成功</p>
					</li>
				</ul>
				<p>申请说明：完成百度云实名认证的企业用户及个人申请说明：完成百度云实名认证的企业用户及个人申请说明：完成百度云实名认证的企业用户及个人用户，需根据申请要求提供完整真实的相关信息，包括已有AI产品或计划中AI产品的介绍。 </p>
			</article>
			<figure class="cor-applyed">
				<p class="apply-info">您已经申请的社团信息：</p>
				<table class="table table-hover">
					<thead class="Table-header">
						<tr>
							<th style="text-align: center;">社团</th>
							<th style="text-align: center;">申请时间</th>
							<th style="text-align: center;">审核状态</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="applyVO.userInCorporation" id="user">
						<tr>
							<td style="text-align: center;"><s:property value="#user.user_corporation_include"/></td>
							<td style="text-align: center;"><s:property value="#user.into_time"/></td>
							<td style="text-align: center;"><s:if test="#user.into_status==0">
								<span style="color:green;">待审核</span>
							</s:if>
							<s:if test="#user.into_status==1">
								<span style="color:red;">不通过</span>
							</s:if>
							<s:if test="#user.into_status==2">
								<span style="color:#358af5;">通过</span>
							</s:if>
							</td>
						</tr>
					</s:iterator>
					</tbody>
				</table>
			</figure>
			<div class="cor-show">
				<div class="title">
					<p>社团</p>
					<!-- <button onclick="queryInfo()" style="float: right; position:relative; top:10px;" class="btn btn-primary">搜索</button> -->
					<input type="text" id="queryName" value="<s:property value="applyVO.queryName"/>"  placeholder="搜索社团" />
					<img onclick="queryInfo()" src="<%=basePath %>img/search.png" class="search-img"/>
				</div>
				<div class="cor-info">
				<s:iterator value="applyVO.corporationInfo" id="corporation">
					<div class="cor-box">
						<div class="cor">
							<img src="<%=basePath %>img/cor-logo.png" />
							<a><s:property value="#corporation.corporation_name"/></p>
								<p><s:property value="#corporation.corporation_introduce"/></p>
							</a>
							<a onclick="applyCorporation(<s:property value="#corporation.corporation_id"/>)">加入</a>
							<div class="line"></div>
							<div class="detail">全社共有156名社员</div>
						</div>
					</div>
				</s:iterator>
				</div>
				<a class="loading" onclick="changeInfo()" >换一批</a>
			</div>
		</div>
		<jsp:include page="/footer.jsp" flush="true"></jsp:include>		
		<!--社团入驻模态框 start-->
		
		<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">修改部门信息</h4>
					</div>
					<div class="modal-body" style="margin:0 auto;">
						<form class="bumen">
							<div class="bumen">
						        <label class="label1">社团名称:</label>
                                <input type="text" value="计算机协会"/>
						    </div>
						    <div class="bumen">
						        <label class="label1">社团logo上传：</label>
							    <div class="img-upload"></div>
						    </div>
							<label class="label1">编辑简介：</label>
							<textarea class="form-control textarea" rows="6"></textarea>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<input type="submit" class="btn btn-primary" value="保存">

					</div>
				</div>
			</div>

		</div>
	
		<!--社团入驻模态框 end-->
		<script type="text/javascript" src="js/jquery.min.js" ></script>
	    <script type="text/javascript" src="js/bootstrap.min.js" ></script>
</body>
</html>