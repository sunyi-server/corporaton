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

<!---------------------------------------------------------------------------------------------------->
<title>社团列表</title>
</head>
<script type="text/javascript"
	src="<%=basePath%>js/sCorInfo/delete_corporation.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/sCorInfo/search_and_sqrt_message.js"></script>
<body>
	<jsp:include page="/WEB-INF/views/corInfo/navbar.jsp" flush="true"></jsp:include>

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
				<th>序号</th>
				<th>社团名称</th>
				<th id="message_gmt_create" onclick="click_sqrt(this)">创建时间</th>
				<th id="message_gmt_modified" onclick="click_sqrt(this)">修改时间</th>
				<th>编辑</th>
			</tr>

			<s:iterator value="page_list_corInfo.messageDTOList" id="NACACDTOL"
				status="status">
				<tr>
					<td><s:property
							value="%{#status.index+(page_list_corInfo.pageSize)*(page_list_corInfo.pageIndex-1)+1}" /></td>
					<td><s:property value="#NACACDTOL.message.corporation_name"
							escape="false" /></td>

					<%-- <td><s:property value="#NACACDTOL.message.message_content"
							escape="false" /></td>
 --%>
					<td><s:property value="#NACACDTOL.message.message_gmt_create" /></td>
					<td><s:property
							value="#NACACDTOL.message.message_gmt_modified" /></td>
					<td>
						<%-- <button class="btn btn-primary" style="margin: 5px;"
							onclick="window.location='<%=basePath%>corInfo/corInfo_updateMessagePage?message.message_id=<s:property value="#NACACDTOL.message.message_id" />'">修改</button> --%>
						<input type="hidden"
						value="<s:property value="#NACACDTOL.message.message_content" />">
						<input type="hidden"
						value="<s:property value="#NACACDTOL.message.message_id" />">
						<button type="button" class="btn modification btn-warning"
							data-toggle="modal" data-target=".bs-example-modal-sm">修改</button>
						<button class="btn btn-danger" style="margin: 5px;"
							id="<s:property
							value="#NACACDTOL.message.message_id" />"
							data-toggle="modal" data-target="#model_delete_message"
							onclick="javascript:delete_message_id=this.id;">删除</button>
					</td>
				</tr>

			</s:iterator>

		</table>
		<div style="margin: 0 auto; width: 400px; text-align: center;">
			<button class="btn btn-primary" onclick="search_corInfo(1)">首页</button>
			<s:if test="page_list_corInfo.HavePrePage">
				<button class="btn btn-primary"
					onclick="search_corInfo(<s:property value="page_list_corInfo.pageIndex-1" />)">上一页</button>
			</s:if>
			<s:else>
				<button class="btn btn-primary"
					onclick="javascript:toastr.warning('已经是第一页了');">上一页</button>
			</s:else>
			<s:if test="page_list_corInfo.HaveNextPage">
				<button class="btn btn-primary"
					onclick="search_corInfo(<s:property value="page_list_corInfo.pageIndex+1" />)">下一页</button>
			</s:if>
			<s:else>

				<button class="btn btn-primary"
					onclick="javascript:toastr.warning('已经是最后一页了');">下一页</button>
			</s:else>
			<button class="btn btn-primary"
				onclick="search_corInfo(<s:property value="page_list_corInfo.totalPages" />)">尾页</button>
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
</body>
<div class="modal fade" id="model_delete_message">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title">确认信息</h4>
			</div>
			<div class="modal-body">
				<h4 id="h4_delete">删除此审核消息？</h4>
			</div>
			<div class="modal-footer">
				<button class="btn btn-danger" onclick="delete_message()">删除</button>
				<button class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>
<!--  -->
<!--  -->
<!--  -->

<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
	aria-labelledby="mySmallModalLabel">
	<div class="modal-dialog " role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="gridSystemModalLabel">修改审核信息</h4>
			</div>
			<div class="modal-body">
				<form action="">
					<table style="width: 95%; margin: 20px auto; text-align: center;"
						class="table-bordered  table-striped table-hover">
						<tbody>
							<tr>
								<th>社团名</th>
								<td><input class="form-control" disabled="disabled"
									type="text" name="corporation_name"> <input
									type="hidden" name="message_id" /></td>

							</tr>
							<tr>
								<th>修改审核备注：</th>
								<td><textarea class="form-control" cols="60" rows="3"
										type="text" name="message_content"></textarea>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<div class="modal-footer">
				<button id="sure_modification" type="button" class="btn btn-primary">确定修改</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			</div>
		</div>
	</div>
</div>

<!--  -->
<!--  -->
<!--  -->

<script type="text/javascript">
$('.modification').click(
		function() {
			$('input[name=message_id]').val($($(this).prev()).val());
			$('input[name=corporation_name]').val($($($($(this).parent()).prevAll())[2]).text());
			$('textarea[name=message_content]').val($($($(this).prev()).prev()).val());
		});	
$('#sure_modification').click(function() {
	//做验证
	$.ajax({
		type : 'post',
		url : '/corporation/corInfo/corInfo_modifiedMessage',
		data : $("form").serialize(),
		success : function(data) {
			// your code
			//提示修改成功
			//跳转http:
			location.href = "corporation/corInfo/corInfo_page_list_message?page_list_corInfo.pageIndex=1&searchCorInfoList.sqrt=message_gmt_modified&searchCorInfoList.sqrt_sc=desc&searchCorInfoList.name=";
		}
	});
});
</script>
</html>