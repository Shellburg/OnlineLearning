<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>猿来入此后台管理系统</title>
</head>
<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="${ctx}/layout_admin/top.jsp" name="topFrame"  noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
    <frame src="${ctx}/layout_admin/left.jsp" name="leftFrame"  noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="${ctx}/layout_admin/index.jsp" name="rightFrame" id="rightFrame" title="rightFrame" />
  </frameset>
</frameset>

</html>
