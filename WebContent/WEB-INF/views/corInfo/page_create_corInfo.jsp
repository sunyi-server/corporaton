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
	src="<%=basePath%>js/sCorInfo/save_corporation.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/sCorInfo/page_create_corInfo.js"></script>
<!---------------------------------------------------------------------------------------------------->
<title>添加社团信息</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/corInfo/navbar.jsp" flush="true"></jsp:include>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->

	<div style="margin: 0 0 0 260px; width: calc(100% - 260px);">
		<span id="span_option" style="display: none;"><s:property
				value="option" /></span>
		<!-- 内容写在此处 -->
		<table style="width: 95%; margin: 40px auto 0;"
			class=" table-condensed table-bordered table-hover">
			<input type="hidden" id="input_corporation_id"
				value="<s:property
							value="corInfoDTO.corInfo.corporation_id" />">
			<tr>
				<th>社团名称：</th>
				<td><s:if
						test="corInfoDTO.corInfo.corporation_solidify == '0'.toString()">
						<input type="text" class="form-control"
							name="input_corporation_name"
							<s:if test="corInfoDTO.corInfo.corporation_name != null">value="<s:property
							value="corInfoDTO.corInfo.corporation_name"  />"</s:if>
							id="input_corporation_name" style="width: 500px;" disabled="true">
					</s:if> <s:else>
						<input type="text" class="form-control"
							name="input_corporation_name"
							<s:if test="corInfoDTO.corInfo.corporation_name != null">value="<s:property
							value="corInfoDTO.corInfo.corporation_name"  />"</s:if>
							id="input_corporation_name" style="width: 350px;">
					</s:else></td>
			</tr>
			<tr>
				<th>社团是否固化：</th>
				<td><s:if
						test="corInfoDTO.corInfo.corporation_solidify == '1'.toString()">
						<input type="checkbox" id="checkbox_corporation_solidify">
					</s:if> <s:else>
						<input type="checkbox" id="checkbox_corporation_solidify"
							checked="checked">
					</s:else></td>
			</tr>
			<tr>
				<th>社团负责人：</th>
				<td><input type="text" class="form-control"
					<s:if test="corInfoDTO.corInfo.corporation_apply_man != null">value="<s:property
							value="corInfoDTO.corInfo.corporation_apply_man" />"</s:if>
					id="input_corporation_apply_man" style="width: 500px;"></td>
			</tr>
			
			<tr>
				<th>社团负责人ID：</th>
				<td><input type="text" class="form-control"
					<s:if test="corInfoDTO.corInfo.corporation_leader_id != null">value="<s:property
							value="corInfoDTO.corInfo.corporation_leader_id" />"</s:if>
					id="input_corporation_leader_id" style="width: 500px;"></td>
			</tr>
			<tr>
				<th>社团类型：</th>
				<td><input type="text" class="form-control"
					<s:if test="corInfoDTO.corInfo.corporation_type != null">value="<s:property
							value="corInfoDTO.corInfo.corporation_type" />"</s:if>
					id="input_corporation_type" style="width: 300px;"></td>
			</tr>
			<tr>
				<th>社团Logo：</th>
				<td><img id="img"
					src="corInfo_getCorporationLogo?imgName=<s:property value="corInfoDTO.corInfo.corporation_logo" />"
					onclick="img_click()" style="height: 300px; width: 450px;" /> <input
					id="input_corporation_logo" type="file" onchange="img_change(this)"
					accept="image/*" style="display: none;" /></td>
			</tr>
			<tr>
				<th>社团介绍：</th>
				<td><textarea id="input_corporation_introduce" cols="108"
						rows="3" align="center"><s:if test="corInfoDTO.corInfo.corporation_introduce != null"><s:property
							value="corInfoDTO.corInfo.corporation_introduce" /></s:if></textarea></td>
			</tr>
			<tr>
				<th>社团申请时间：</th>
				<td><input type="text" class="form-control"
					value="<s:property
							value="corInfoDTO.corInfo.corporation_apply_time"
							/>"
					id="input_corporation_apply_time" style="width: 300px;"></td>
			</tr>



			<tr>
				<th>社团申请状态：</th>
				<td><s:if test="corInfoDTO.corInfo.corporation_status != null">
						<select class="selectpicker" data-live-search="false"
							disabled="true" id="select_corporation_status">
							<s:if
								test="corInfoDTO.corInfo.corporation_status== '0'.toString()">
								<option value="0" selected="selected">待审核</option>
								<option value="1">未通过</option>
								<option value="2">已通过</option>
							</s:if>
							<s:elseif
								test="corInfoDTO.corInfo.corporation_status== '1'.toString()">
								<option value="0">待审核</option>
								<option value="1" selected="selected">未通过</option>
								<option value="2">已通过</option>
							</s:elseif>
							<s:else>
								<option value="0">待审核</option>
								<option value="1">未通过</option>
								<option value="2" selected="selected">已通过</option>
							</s:else>
						</select>
					</s:if> <s:else>
						<select class="selectpicker" data-live-search="false"
							id="select_corporation_status">
							<option value="0" selected="selected">待审核</option>
							<option value="1">未通过</option>
							<option value="2">已通过</option>
						</select>
					</s:else></td>
			</tr>

			<td><s:if test="option == 'update'">
					<button class="btn btn-primary" onclick="update_corporation()">保存</button>
				</s:if> <s:else>
					<button class="btn btn-primary" onclick="save_corporation()">保存</button>
				</s:else></td>
			</tr>

		</table>

	</div>
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
</body>
<script type="text/javascript">
	/*上传图片的JS*/

	function img_click() {
		document.getElementById("input_corporation_logo").click();
	}

	function img_change(file) {
		var img = document.getElementById("img");
		var reader = new FileReader();
		reader.onload = function(evt) {
			img.src = evt.target.result;
		}

		reader.readAsDataURL(file.files[0]);
	}
</script>
</html>