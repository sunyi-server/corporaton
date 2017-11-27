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
<link rel="stylesheet" href="<%=basePath %>css/admin.css" />
<script type="text/javascript" src="<%=basePath %>js/data/sideNav.js"></script>
</head>
<body>
<!-- 通过member_informationVO类判断权限，来 -->
<aside class="side-nav">
				<div class="nav">
				
					<h3 class="nav-first"><img src="<%=basePath %>img/corporation.png">社团</h3>
						<a onclick="goToCorporationInfoShow()"><span class="nav-second"><img src="<%=basePath %>img/secondnav.png">信息查看</span></a>
					<s:if test="currMember.member_role>=1">
						<a><span onclick="goToCorporationInfo()" class="nav-second" onclick=""><img src="<%=basePath %>img/secondnav.png">修改信息</span></a>
					</s:if>
						<a><span class="nav-second"><img src="<%=basePath %>img/secondnav.png">公告查看</span></a>
					<s:if test="currMember.member_role>=1">
						<a onclick="goToDepartment()"><span class="nav-second"><img src="<%=basePath %>img/secondnav.png">部门管理</span></a>
					</s:if>
				</div>
				<div class="nav">
					<h3 class="nav-first"><img src="<%=basePath %>img/member.png">社员</h3>
					<s:if test="currMember.member_role>=1">
						<a><span class="nav-second"><img src="<%=basePath %>img/secondnav.png">公告管理</span></a>
					</s:if>
					<s:if test="currMember.member_role>=1">
						<a><span class="nav-second" onclick="goToManagerJsp()"><img src="<%=basePath %>img/secondnav.png">信息管理</span></a>
					</s:if>
					<s:if test="currMember.member_role>=1">
						<a><span class="nav-second" onclick="goToApplyJsp()"><img src="<%=basePath %>img/secondnav.png">入会审核</span></a>
					</s:if>
					<s:if test="currMember.member_role>=1">
						<a onclick="goToMemberMan()"><span class="nav-second"><img src="<%=basePath %>img/secondnav.png">管理员管理</span></a>
					</s:if>
				</div>
			</aside>
</body>
</html>