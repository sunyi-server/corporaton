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
<title>申请入驻平台</title>
		<link rel="stylesheet" href="<%=basePath %>css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=basePath %>css/add-cor.css" />
		<script type="text/javascript" src="<%=basePath %>js/data/userApply.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/data/dataApplyCorporation.js"></script>
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
	<input id="resultName" type="text" style="display: none;">

	<!-- ----------------------------------------- 隐藏信息结束------------------------------------------------------------------  -->
	<jsp:include page="/header.jsp" flush="true"></jsp:include>
	<div class="wrapper1">
	<!--固定社团入驻按钮 start-->
			<a class="cor-join" data-toggle="modal" data-target="#myModal2">入驻</a>
	<!--固定社团入驻按钮 end-->
			<article class="join-explain">
				<h1>如何入驻平台</h1>
			  <ul class="progress1">
					<li class="step step-2">
						<p>提交申请</p>
					</li>
					<li class="step"></li>
					<li class="step step-3">
						<p>审批通过</p>
					</li>
					<li class="step"></li>
					<li class="step step-4">
						<p>入驻成功</p>
					</li>
				</ul>
				<p>申请说明：完成百度云实名认证的企业用户及个人申请说明：完成百度云实名认证的企业用户及个人申请说明：完成百度云实名认证的企业用户及个人用户，需根据申请要求提供完整真实的相关信息，包括已有AI产品或计划中AI产品的介绍。 </p>
			</article>
			<figure class="cor-applyed">
				<p class="apply-info">您正在申请的社团信息：</p>
				<table class="table table-hover">
					<thead class="Table-header">
						<tr>
							<th style="text-align: center;">社团</th>
							<th style="text-align: center;">申请时间</th>
							<th style="text-align: center;">审核状态</th>
						</tr>
					</thead>
					<tbody>
					<s:iterator value="corporationInfoVO" id="cor">
						<tr>
							<td style="text-align: center;"><s:property value="#cor.corporation_name"/></td>
							<td style="text-align: center;"><s:property value="#cor.corporation_apply_time"/></td>
							<td style="text-align: center;"><s:if test="#cor.corporation_status==0">
								<span style="color:green;">待审核</span>
							</s:if>
							<s:if test="#cor.corporation_status==1">
								<span style="color:red;">不通过</span>
							</s:if>
							<s:if test="#cor.corporation_status==2">
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
				</div>
				<div class="cor-info">
				</div>
			</div>
		</div>
		<jsp:include page="/footer.jsp" flush="true"></jsp:include>		
		<!--社团入驻模态框 start-->
		
		<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title" id="myModalLabel">填写社团信息</h4>
					</div>
					<div class="modal-body" style="margin:0 auto;">
						<form class="bumen" action="<%=basePath %>manager/apply_submitApplyCorporation" method="post" enctype="multipart/form-data" onsubmit="return verificationCorporation()">
							<div class="bumen">
						        <label class="label1">社团名称:</label>
                                <input type="text" id="corporation_name" name="corporation_name"/>
						    </div>
						 	<div class="bumen">
						        <label class="label1">社团类别:</label>
                                <input type="text" id="corporation_type" name="corporation_type"/>
						    </div>
						    <div class="bumen">
						        <label class="label1">社团logo上传：</label>
							    <div onclick="changeLogo()" class="img-upload" style="cursor: pointer;">
							    	<img id="showLogo" alt="" src="" style="height: 150px; width:150px;">
							    	<input type="file" onchange="showLogoImg(this)" id="corporation_logo" name="corporation_logo" style="display: none;">
							    </div>
						    </div>
							<label class="label1">编辑简介：</label>
							<textarea class="form-control textarea" rows="6" id="corporation_introduce" name="corporation_introduce"></textarea>	
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<input type="submit" class="btn btn-primary" value="保存">
					</div>
					</form>
				</div>
			</div>

		</div>
	
		<!--社团入驻模态框 end-->
		<script type="text/javascript" src="js/jquery.min.js" ></script>
	    <script type="text/javascript" src="js/bootstrap.min.js" ></script>
</body>
</html>