<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected");
		$(this).addClass("selected");
	});	
});	
</script>


</head>

<body style="background:url(${pageContext.request.contextPath}/resources/images/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="main.html" target="_parent"><img src="${pageContext.request.contextPath}/resources/images/logo.png" title="系统首页" /></a>
    </div>
        
    <ul class="nav">
   <%--  <li><a href="#" target="rightFrame" class="selected"><img src="${pageContext.request.contextPath}/resources/images/icon01.png" title="工作台" /><h2>工作台</h2></a></li>
    <li><a href="#"  target="rightFrame"><img src="${pageContext.request.contextPath}/resources/images/icon06.png" title="系统设置" /><h2>系统设置</h2></a></li>  --%>
    </ul>
            
    <div class="topright">    
    <ul>
    <!-- 
    <li><span><img src="${pageContext.request.contextPath}/resources/images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
     -->
    <li><a href="${ctx}/quitSystem" target="_parent">退出</a></li>
    </ul>
    
    </div>
</body>
</html>
