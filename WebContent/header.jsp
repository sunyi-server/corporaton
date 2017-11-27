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
<link rel="stylesheet" href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=basePath %>css/navAndFooter.css" />
<script type="text/javascript" src="<%=basePath %>js/data/indexOurCor.js"></script>
<script type="text/javascript" src="<%=basePath %>js/data/header.js"></script>
</head>
<body>
<header>
			<nav>
			<a href="<%=basePath %>manager/apply_intoIndex"><img src="<%=basePath %>img/logo.png" /></a>
				<a id="dLabel" data-target="#" onclick="ajaxloadCorporation()" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">我的社团<div class="caret"></div></a>
				<a onclick="skipToCorporation()">社团入驻</a>
				<a href="<%=basePath %>manager/apply_skipToApply?queryName=&currPage=1">加入社团</a>
				<ul class="dropdown-menu" aria-labelledby="dLabel">
				    <!-- <li><a class="li-a" href="#">计算机协会</a></li>
				    <li role="separator" class="divider"></li> -->
			    </ul>
				<span>
					<%if(session.getAttribute("user_id")==null || session.getAttribute("user_id")=="") 
					{
					%>
					<a href="<%=basePath %>login.jsp">登录</a>
					<a href="<%=basePath %>login.jsp">注册</a>
					<%} %>
					<div class="info-img">
						<s:if test="userInVO.user_logo!=null">
						<img style=" cursor:pointer; height: 30px; width: 30px; border-radius: 30px;" src="<%=basePath %>manager/apply_downloadImg?fileDirc=userLogo&filename=<s:property value="userInVO.user_logo"/>" />
						</s:if>
						<s:else>
						<img  style="height: 30px; width: 30px;" src="<%=basePath %>img/headImg.png" />
						</s:else>
						<ul class="info-user">
						    <li style="cursor: pointer;" onclick="skipToUserInfor()">我的信息</li>
						    <li style="cursor: pointer;" onclick="logout()">退出</li>
					    </ul>
					</div>
				</span>
			</nav>
		</header>
		<script type="text/javascript" src="<%=basePath %>js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/Nav_infoAndAdmin_checkbox.js" ></script>
		<script>
			$(function() {
				$('[data-toggle="popover"]').popover()
			})
			
			Modal.prototype.show = function (_relatedTarget) {
  			  this.adjustBody_beforeShow();
 			   //...other code
			}

			Modal.prototype.hide = function (e) {
  			  this.adjustBody_afterShow();
  				  //...other code
			}
		</script>
</body>
</html>