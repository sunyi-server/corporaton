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
<title>Insert title here</title>
<link rel="stylesheet" href="<%=basePath %>css/BackStage.css" />
<%-- 		<script type="text/javascript" src="<%=basePath %>js/backstage.js" ></script>
		<script>
			window.onload=function(){-				
				Click();
			};
		</script> --%>
</head>	
<body>
	<div class="wrap">
		<!-- 下面为导航 -->
		<jsp:include page="/backManagerSideNav.jsp" flush="true"></jsp:include>
		
		<div class="right">
			<!-- 此处为内容  -->
		
		</div>
	</div>
</body>
</html>