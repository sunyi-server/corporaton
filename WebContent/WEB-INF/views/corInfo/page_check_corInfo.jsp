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
<!---------------------------------------------------------------------------------------------------->
<script type="text/javascript"
	src="<%=basePath%>js/sCorInfo/model_search_check_corInfo.js"></script>
<!---------------------------------------------------------------------------------------------------->
<title>社团审核</title>
<style type="text/css">
#model_disagree_corInfo {
	top: 20%;
}
</style>
</head>
<script type="text/javascript"
	src="<%=basePath%>js/sCorInfo/agreeORdisagree_corporation.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/sCorInfo/check_search_and_sqrt_corInfo.js"></script>
<body>
	<jsp:include page="/WEB-INF/views/corInfo/navbar.jsp" flush="true"></jsp:include>

	<!--  -->
	<!--  -->
	<!--  -->
	<!--  -->
	<!--  -->
	<div style="margin: 50px 0 0 260px; width: calc(100% - 260px);">
		<span id="span_sqrt" style="display: none;"><s:property
				value="page_list_corInfo.search.sqrt" /></span>
		<!--  -->
		<span id="span_sqrt_sc" style="display: none;"><s:property
				value="page_list_corInfo.search.sqrt_sc" /></span>
		<!--  -->
		<!--  -->
		<table style="width: 95%; margin: 0 auto; text-align: center;">
			<tr>
				<td style="height: 50px;"><button class="btn btn-primary"
						onclick="search_corInfo()"
						style="float: right; margin: 8px 0 0 0;">搜索</button> <input
					placeholder="输入社团名称" type="text" class="form-control"
					id="input_name"
					value="<s:property
value="page_list_corInfo.search.name" />"
					style="width: 200px; float: right; margin: 8px 10px 8px 50px;" />
				</td>
			</tr>
		</table>
		<table id="table"
			style="width: 95%; margin: 20px auto; text-align: center;"
			class="table-bordered">
			<tr>
				<!--  -->
				<th>社团名称</th>
				<th>社团申请人</th>
				<th>申请人ID</th>
				<th>社团Logo</th>
				<th>社团类型</th>
				<th value="" id="select_corporation_status">社团状态</th>
				<th id="corporation_apply_time" onclick="click_sqrt(this)">申请时间</th>
				<th id="corporation_gmt_create" onclick="click_sqrt(this)">创建时间</th>
				<th id="corporation_gmt_modified" onclick="click_sqrt(this)">修改时间</th>
				<th colspan="2">编辑</th>

			</tr>
			<s:iterator value="page_list_corInfo.corInfoDTOList" id="NACACDTOL">
				<tr>
					<td><s:property value="#NACACDTOL.corInfo.corporation_name"
							escape="false" /></td>
					<td><s:property
							value="#NACACDTOL.corInfo.corporation_apply_man" escape="false" /></td>

					<td><s:property
							value="#NACACDTOL.corInfo.corporation_leader_id" escape="false" /></td>
					<td><img style="height: 100px; width: 100px;"
						style="height: 100px; width: 100px;"
						src="<%=basePath%>corInfo/corInfo_getCorporationLogo?imgName=<s:property value="#NACACDTOL.corInfo.corporation_logo" />"></td>
					<td><s:property value="#NACACDTOL.corInfo.corporation_type"
							escape="false" /></td>

					<td><s:if
							test="#NACACDTOL.corInfo.corporation_status== '0'.toString()">未审核</s:if>
						<s:elseif
							test="#NACACDTOL.corInfo.corporation_status== '1'.toString()">未通过</s:elseif>
						<s:else>已通过</s:else></td>

					<td><s:property
							value="#NACACDTOL.corInfo.corporation_apply_time" escape="false" /></td>
					<td><s:property
							value="#NACACDTOL.corInfo.corporation_gmt_create" /></td>
					<td><s:property
							value="#NACACDTOL.corInfo.corporation_gmt_modified" /></td>
					<td>
						<button class="btn btn-warning" style="margin: 5px;"
							id="<s:property
							value="#NACACDTOL.corInfo.corporation_id" />"
							data-toggle="modal" data-target="#model_agree_corInfo"
							class="agree" onclick="javascript:agree_corporation_id=this.id;">同意</button>
						<br>
						<button class="btn btn-danger" style="margin: 5px;"
							id="<s:property
							value="#NACACDTOL.corInfo.corporation_id" />"
							data-toggle="modal" data-target="#model_disagree_corInfo"
							onclick="javascript:disagree_corporation_id=this.id;">拒绝</button>
					</td>
					<td><input type="hidden"
						value="<s:property value="#NACACDTOL.corInfo.corporation_logo" />">
						<input type="hidden"
						value="<s:property value="#NACACDTOL.corInfo.corporation_introduce" />">
						<input type="hidden"
						value="<s:property value="#NACACDTOL.corInfo.corporation_id" />">
						<button type="button" class="btn modification btn-primary"
							onclick="showCorporationLogo(this)"
							value="<s:property value="#NACACDTOL.corInfo.corporation_id" />"
							data-toggle="modal" data-target=".bs-example-modal-sm">查看</button>

					</td>
				</tr>

			</s:iterator>

		</table>
		<!------------------------------------------------------------------------------------------------------------------->
		<div style="margin: 0 auto; width: 400px; text-align: center;">
			<button class="btn btn-primary"
				onclick="window.location='<%=basePath%>corInfo/corInfo_page_list_checkCorInfo?searchCorInfoList.sqrt=corporation_gmt_create&searchCorInfoList.sqrt_sc=asc&searchCorInfoList.check=0&searchCorInfoList.name=&page_list_corInfo.pageIndex=1'">首页</button>
			<s:if test="page_list_corInfo.HavePrePage">
				<button class="btn btn-primary"
					onclick="window.location='<%=basePath%>corInfo/corInfo_page_list_checkCorInfo?searchCorInfoList.sqrt=corporation_gmt_create&searchCorInfoList.sqrt_sc=asc&searchCorInfoList.check=0&searchCorInfoList.name=&page_list_corInfo.pageIndex=<s:property value="page_list_corInfo.pageIndex-1" />'">上一页</button>
			</s:if>
			<s:else>
				<button class="btn btn-primary"
					onclick="javascript:toastr.warning('已经是第一页了');">上一页</button>
			</s:else>
			<s:if test="page_list_corInfo.HaveNextPage">
				<button class="btn btn-primary"
					onclick="window.location='<%=basePath%>corInfo/corInfo_page_list_checkCorInfo?searchCorInfoList.sqrt=corporation_gmt_create&searchCorInfoList.sqrt_sc=asc&searchCorInfoList.check=0&searchCorInfoList.name=&page_list_corInfo.pageIndex=<s:property value="page_list_corInfo.pageIndex+1" />'">下一页</button>
			</s:if>
			<s:else>

				<button class="btn btn-primary"
					onclick="javascript:toastr.warning('已经是最后一页了');">下一页</button>
			</s:else>
			<button class="btn btn-primary"
				onclick="window.location='<%=basePath%>corInfo/corInfo_page_list_checkCorInfo?searchCorInfoList.sqrt=corporation_gmt_create&searchCorInfoList.sqrt_sc=asc&searchCorInfoList.check=0&searchCorInfoList.name=&page_list_corInfo.pageIndex=<s:property value="page_list_corInfo.totalPages" />'">尾页</button>
		</div>
		<!------------------------------------------------------------------------------------------------------------------->
		<div style="margin: 20px auto 20px; width: 200px; text-align: center;">
			第
			<s:property value="page_list_corInfo.pageIndex" />
			页<br>共
			<s:property value="page_list_corInfo.totalPages" />
			页<br>共
			<s:property value="page_list_corInfo.totalRecords" />
			条记录
		</div>
		<!------------------------------------------------------------------------------------------------------------------->
</body>
<!--  -->
<!--  -->
<!--  -->
<!--  -->
<!--  -->
<!--  -->
<!--  -->
<!-- 同意社团入驻的模态框 -->
<div class="modal fade" id="model_agree_corInfo">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h3 class="modal-title">确认信息</h3>
			</div>
			<div class="modal-body">

				<h4 id="h4_delete">同意入驻？</h4>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary" onclick="agree_corporation()">确定</button>
				<button class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>
<!--  -->
<!--  -->
<!--  -->
<!--  -->
<!--  -->
<!--  -->
<!--  -->
<!-- 不同意社团入驻的模态框 -->
<div class="modal fade" id="model_disagree_corInfo">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<b><h3 class="modal-title">拒绝社团入驻</h3></b>
			</div>
			<div class="modal-body">
				<h4 id="h4_delete">输入拒绝社团入驻备注：</h4>
				<form role="form">
					<div class="form-group">
						<!--  <table>
					   <tbody>

							<tr>
								<th>社团名称</th>
								<td><input class="form-control" disabled="disabled"
									type="text" name="corporation_name"> </td>
							</tr>
							<tr>
								<th>社团介绍：</th>
								<td><textarea class="form-control" cols="60" rows="3"
										type="text" name="input_message_content"></textarea>
								</td>
							</tr>
							</tbody>
							</table> -->
						<td><textarea id="input_message_content" cols="70" rows="3"
								align="center"></textarea></td>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button class="btn btn-danger" onclick="disagree_corporation()">确定</button>
				<button class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>
<!--  -->
<!--  -->
<!-- 查看社团信息的模态框 -->
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
	aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog " role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="gridSystemModalLabel">查看审核信息</h4>
			</div>
			<div class="modal-body">
				<form action="">
					<table style="width: 95%; margin: 20px auto; text-align: center;"
						class="table-bordered  table-striped table-hover">
						<tbody>
							<tr>
								<th>社团名称</th>
								<td><input class="form-control" type="text"
									name="corporation_name"> <input type="hidden"
									name="corporation_id" /></td>
							</tr>
							<tr>
								<th>申请人</th>
								<td><input class="form-control" type="text"
									name="corporation_apply_man">
							</tr>
							<tr>
								<th>申请时间</th>
								<td><input class="form-control" type="text"
									name="corporation_apply_time">
							</tr>
							<tr>
								<th>负责人id</th>
								<td><input class="form-control" type="text"
									name="corporation_leader_id">
							</tr>
							<tr>
								<th>社团Logo</th>
								<td><img style="height: 200px; width: 300px; float: left"
									id="Show_Corporation_Logo" src="" />
							</tr>
							<tr>
								<th>社团介绍：</th>
								<td><textarea class="form-control" cols="60" rows="3"
										type="text" name="corporation_introduce"></textarea>
							</tr>
							<tr>
								<th>社团类型：</th>
								<td><input class="form-control" type="text"
									name="corporation_type"></td>
							</tr>
							<tr>
								<th>社团申请状态：</th>
								<td><input class="form-control" type="text"
									name="corporation_status"></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<div class="modal-footer">
				<!-- <button id="sure_modification" type="button" class="btn btn-primary">确定修改</button> -->
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>

<!--  -->
<!--  -->
<script type="text/javascript">
$('.modification').click(
		function() {
			$('input[name=corporation_id]').val($($(this).prev()).val());
			$('input[name=corporation_name]').val(
					$($($($(this).parent()).prevAll())[9]).text());
			$('input[name=corporation_apply_man]').val(
					$($($($(this).parent()).prevAll())[8]).text());
			$('input[name=corporation_apply_time]').val(
					$($($($(this).parent()).prevAll())[3]).text());
			$('input[name=corporation_leader_id]').val(
					$($($($(this).parent()).prevAll())[7]).text());

			$('input[name=corporation_type]').val(
					$($($($(this).parent()).prevAll())[5]).text());
			$('textarea[name=corporation_introduce]').val(
					$($($(this).prev()).prev()).val());

			var corporation_status = $($($($(this).parent()).prevAll())[4])
					.text();

			corporation_status = $.trim(corporation_status);
			$('input[name=corporation_status]').val(corporation_status);

		});
</script>
</html>