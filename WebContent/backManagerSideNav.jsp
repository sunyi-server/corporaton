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
		<script type="text/javascript" src="<%=basePath %>js/backstage.js" ></script>
		<script>
			window.onload=function(){-				
				Click();
			};
		</script>
</head>	
<body>
			<div class="left">
				<h1>管理员</h1>
				<div class="content">
					<div id="funcList1" class="picManage">
						<img src="img/picMange.png" alt=""/>
						<span><a href="#">图片管理</a></span>
					</div>
					<div id="funcList2" class="CommunityMange"> 
						<img src="img/community.png" alt=""/>
						<span><a href="#">社团管理</a></span>
						<ul>
							<li><a href="#">新建社团</a></li>
							<li><a href="#">社团列表</a></li>
							<li><a href="#">社团类型</a></li>
							<li><a href="#">创建类别</a></li>
						</ul>
					</div>
					<div id="funcList3" class="herfMange">
						<img src="img/href.png" alt="" />
						<span><a href="#">链接管理</a></span>					
					</div>
					<div id="funcList4" class="Manager">
						<img src="img/manager.png" alt="" />
						<span><a href="#">管理员管理</a></span>
						<ul>
							<li><a href="#">新建管理员</a></li>
							<li><a href="#">管理员列表</a></li>
							<li><a href="#">管理员功能</a></li>							
						</ul>
					</div>
					<div id="funcList5" class="exit">
						<img src="img/exit.png" alt=""/>
						<span><a href="#">退出系统</a></span>
					</div>
				</div>	
			</div>
</body>
</html>