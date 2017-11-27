<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息托管系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="<%=basePath %>css/index.css" />
<link rel="stylesheet" href="<%=basePath %>css/navAndFooter.css" />
</head>

<body>

	<div class="wrapper">
	<jsp:include page="/header.jsp" flush="true"></jsp:include>	
		<div class="content">
				<article>
					<span>校内社团OA系统正式上线</span>
					<span>借助于云平台增强社团办公效率，减少繁杂工作，信息永久化保存</span>
					<span>打造校内一流办公系统，可兼容各类型社团组织使用</span>
					<a onclick="skipToCorporation()" class="btn-join">申请加入</a>
				</article>
		</div>
	<jsp:include page="/footer.jsp" flush="true"></jsp:include>		
	</div>
	
</body>
</html>
