<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
   <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>

</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    </ul>
    </div>
    <div class="mainindex">
      <c:if test="${adminLevel==null}">
		    <div class="welinfo">
		    <span><img src="${resourcePath}/images/sun.png" alt="天气" /></span>
		    <b><a href="${ctx}/quitSystem" target="_parent" style="color:red;">亲您没有登录请跳转至登录页面</a></b>
		    </div>
      </c:if>
      <c:if test="${adminLevel!=null}">
		    <div class="welinfo">
		    <span><img src="${resourcePath}/images/sun.png" alt="天气" /></span>
		    <b>欢迎进入本英语情景教学系统</b>
		    </div>
      </c:if>
    </div>
</body>
</html>
