<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cn.st.entity.*"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib uri="http://gx.edu/dTags" prefix="d"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

}); 
</script>
   <script type="text/javascript">
   
   
	</script>

</head>


<body>
 <form  action="${ctx}/queryMyMessages" method="post" id="form">
	<input type="hidden" name="status" value=query />
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">留言管理</a></li>
    <li><a href="#">我的留言</a></li>
    <li><b style="color:red">${stuName}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="rightinfo">
    <div class="tools">
  	<ul class="toolbar">
	         <li ><span><a href="${ctx}/addleaveMsg"> <img src="${pageContext.request.contextPath}/resources/images/t01.png" /></a></span>我要留言</li>
    </ul>
        
    </div>
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>留言人</th>
        <th>标题</th>
        <th>内容</th>
        <th>留言时间</th>
        <th>回复人</th>
        <th>回复内容</th>
        <th>回复时间</th>
        <th>是否审核</th>
        </tr>
        </thead>
        <tbody>
        <!-- 以下数据应从数据表遍历而得 -->
		<c:forEach items="${comments}" var="item">
				<tr id="1">
				    <td width="30">${item.contentName}</td>
				    <td width="40">${item.c_title}</td>
					<td width="120">${item.content}</td>
					<td width="40">${item.ceateTime}</td>
					<td width="40">${item.rContentName}</td>
					<td width="120">${item.rContent}</td>
					<td width="40">${item.rDateTime}</td>
					<td width="40">${item.state}</td>
				</tr>
			   </c:forEach>
        </tbody>
    </table>
    </div>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</form>	
</body>
</html>
