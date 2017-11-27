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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath %>css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath %>css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="<%=basePath %>css/admin.css" />
<script type="text/javascript" src="<%=basePath %>js/data/dataDeparment.js"></script>
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
	

	
	<!-- ----------------------------------------- 隐藏信息结束------------------------------------------------------------------  -->
	<jsp:include page="/header.jsp" flush="true"></jsp:include>
	
	
	<!-- -------------------------------------------------主体内容-----------------------------------------------------            -->
	
	<div class="wrapper">
			<!-- 侧边栏 -->
			<jsp:include page="/corporationSideNav.jsp" flush="true"></jsp:include>
	
	</div>



		<script type="text/javascript" src="js/jquery.min.js" ></script>
	    <script type="text/javascript" src="js/bootstrap.min.js" ></script>
</body>
</html>