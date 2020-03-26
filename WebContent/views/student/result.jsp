<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
  </head>
  <body>
   <div> 
        <h1>Excel文件一共导入${countTotal}条学生信息</h1>
        <h2>Excel文件导入成功${countSuceess}条学生信息</h2>
        <h3>Excel文件导入失败${countFail}条数据学生信息</h3>
         <c:forEach items="${listStu}" var="listStus" varStatus="status">
           <p>失败原因是:该学号${listStus}已存在或者数据格式错误</p><br>
		</c:forEach>
   </div>
  </body>
</html>
