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
<title>左边菜单栏</title>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active");
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
});	
</script>


</head>

<body style="background:#f0f9fd;">
     <!--教师权限 教师除了管理学生外其他都可以管理-->
    <c:if test="${adminLevel=='教师'}">
         <div class="lefttop"><span></span>系统菜单</div>
    <dl class="leftmenu"> 
    <dd>
    <div class="title">
    <span><img src="${pageContext.request.contextPath}/resources/images/leftico01.png" /></span>管理中心
    </div>
    	<ul class="menuson">
         <li class="active"><cite></cite><a href="${ctx}/admin/myList" target="rightFrame">我的信息</a><i></i></li>
             <li><cite></cite><a href="${ctx}/queryCommentInfMG" target="rightFrame">留言管理</a><i></i></li>
             <li><cite></cite><a href="${ctx}/notice/queryNotice" target="rightFrame">公告管理</a><i></i></li>
        </ul> 
    <div class="title">
    <span><img src="${pageContext.request.contextPath}/resources/images/leftico01.png" /></span>资源管理
    </div>
    	<ul class="menuson">
         <li class="active"><cite></cite><a href="${ctx}/queryData" target="rightFrame">资料管理</a><i></i></li>
         <li><cite></cite><a href="${ctx}/onlinelibrary" target="rightFrame">题库管理</a><i></i></li>
         <li><cite></cite><a href="${ctx}/vediou_mng" target="rightFrame">视频管理</a><i></i></li>
       </ul> 
    </dd>
    </dl>
    </c:if>
    <!--管理员管理权限-->
     <c:if test="${adminLevel=='系统管理员'}">
	<div class="lefttop"><span></span>系统菜单</div>
    <dl class="leftmenu"> 
    <dd>
    <div class="title">
    <span><img src="${pageContext.request.contextPath}/resources/images/leftico01.png" /></span>管理中心
    </div>
    	<ul class="menuson">
         <li class="active"><cite></cite><a href="${ctx}/admin" target="rightFrame">角色管理</a><i></i></li>
             <li><cite></cite><a href="${ctx}/queryStuInf" target="rightFrame">学生管理</a><i></i></li>
             <li><cite></cite><a href="${ctx}/queryCommentInfMG" target="rightFrame">留言管理</a><i></i></li>
             <li><cite></cite><a href="${ctx}/notice/queryNotice" target="rightFrame">公告管理</a><i></i></li>
        </ul> 
    <div class="title">
    <span><img src="${pageContext.request.contextPath}/resources/images/leftico01.png" /></span>资源管理
    </div>
    	<ul class="menuson">
         <li class="active"><cite></cite><a href="${ctx}/queryData" target="rightFrame">资料管理</a><i></i></li>
         <li><cite></cite><a href="${ctx}/onlinelibrary" target="rightFrame">题库管理</a><i></i></li>
         <li><cite></cite><a href="${ctx}/vediou_mng" target="rightFrame">视频管理</a><i></i></li>
       </ul> 
       <div class="title">
    <span><img src="${pageContext.request.contextPath}/resources/images/leftico01.png" /></span>日志管理
    </div>
    	<ul class="menuson">
         <li class="active"><cite></cite><a href="${ctx}/listLog" target="rightFrame">日志管理</a><i></i></li>
       </ul> 
    </dd>
    </dl>
     </c:if>
</body>
</html>

