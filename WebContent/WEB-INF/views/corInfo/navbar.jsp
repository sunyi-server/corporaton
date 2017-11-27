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
<!--------------------------------------------------------------------------------->
<script type="text/javascript" src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
<!--------------------------------------------------------------------------------->
<link rel="stylesheet" href="<%=basePath%>css/bootstrap-select.min.css">
<script type="text/javascript" src="<%=basePath%>js/bootstrap-select.js"></script>
<!--------------------------------------------------------------------------------->
<link rel="stylesheet"
	href="<%=basePath%><%=basePath%>css/navbar/chartist-custom.css">
<link rel="stylesheet" href="<%=basePath%>css/navbar/main.css">
<link rel="stylesheet"
	href="<%=basePath%>css/navbar/font-awesome.min.css">
<link rel="stylesheet" href="<%=basePath%>css/navbar/style.css">
<link rel="stylesheet" href="<%=basePath%>css/table.css">
<!--------------------------------------------------------------------------------->
<link rel="stylesheet" href="<%=basePath%>css/jquery.datetimepicker.css" />
<script type="text/javascript"
	src="<%=basePath%>js/jquery.datetimepicker.full.js"></script>
<!--------------------------------------------------------------------------------->
<link rel="stylesheet" href="<%=basePath%>css/flat/green.css" />
<script type="text/javascript" src="<%=basePath%>js/icheck.js"></script>
<!--------------------------------------------------------------------------------->
<!--------------------------------------------------------------------------------->
<link rel="stylesheet" href="<%=basePath%>css/toastr.css" />
<script type="text/javascript" src="<%=basePath%>js/toastr.js"></script>
<!--------------------------------------------------------------------------------->

<!--------------------------------------------------------------------------------->
<!--------------------------------------------------------------------------------->
<title>Insert title here</title>
</head>
<body>
	<!--顶部-->
	<%-- <div
		style="background-color: white; height: 125px; width: 100%; z-index: 10; position: fixed; top: 0; left: 0;">
		<img src="<%=basePath%>img/logo.jpg" class="img-responsive logo">
	</div> --%>

	<div id="wrapper" style="position: fixed; top: 0; left: 0;">
		<!--左侧-->
		<div id="sidebar-nav" class="sidebar"
			style="margin: 0 0 0 0; position: fixed; top: 0; left: 0;">

			<div class="sidebar-scroll">

				<ul class="nav">
					<li><a href="#slink" data-toggle="collapse" class="collapsed">
							<i class="lnr lnr-flag"></i> <span>社团管理</span> <i
							class="icon-submenu lnr lnr-chevron-left"></i>
					</a> <s:if
							test="page=='page_list_corInfo'||page=='page_modify_corInfo'">
							<div id="slink" class="collapse in">
						</s:if> <s:else>
							<div id="slink" class="collapse">
						</s:else>
						<ul class="nav">
							<!--  -->
							<li><a
								href="<%=basePath%>corInfo/corInfo_page_list_corInfo?page_list_corInfo.pageIndex=1">
									社团列表</a></li>
							
							<li><a
								href="<%=basePath%>corInfo/corInfo_page_list_checkCorInfo?page_list_corInfo.pageIndex=1&searchCorInfoList.sqrt=corporation_gmt_create&searchCorInfoList.sqrt_sc=desc&searchCorInfoList.check=0&searchCorInfoList.name=
">
									社团审核</a></li>
							<li><a
								href="<%=basePath%>corInfo/corInfo_page_list_message?page_list_corInfo.pageIndex=1">
									审核消息表</a></li>

							<!--  -->
							<!--  -->
						</ul>
			</div>
			</li>


			</ul>
		</div>
	</div>
	</div>
	<!--------------------------------------------------------------------------------------------->
	<!--------------------------------------------------------------------------------------------->
	<!--------------------------------------------------------------------------------------------->
	<!--------------------------------------------------------------------------------------------->
	<!--------------------------------------------------------------------------------------------->
	<!--------------------------------------------------------------------------------------------->
</body>
<script>
	$(document).ready(function() {
		$('input').iCheck({
			checkboxClass : 'icheckbox_flat-green',
			radioClass : 'iradio_flat-green'
		});
	});
</script>
</html>