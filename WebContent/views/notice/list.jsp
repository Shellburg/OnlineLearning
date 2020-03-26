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
 <form  action="${ctx}/notice/queryNotice" method="post" id="form">
	<input type="hidden" name="status" value=query />
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">公告管理</a></li>
    <li><a href="#">公告信息</a></li>
    <li><a href="#">基本内容</a></li>
    <li><b style="color:red"> ${name}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="rightinfo" style="width:100%;height:100%;">
    <div class="tools">
    	<ul class="toolbar">
	         <li ><span><a href="${ctx}/notice/addNitce"> <img src="${pageContext.request.contextPath}/resources/images/t01.png" /></a></span>发布新公告</li>
        </ul>
        
    <!--  综合查询-->  
   <!--  <ul class="seachform">
	    <li><label>姓名查询</label><input name="m_user_name" type="text" class="scinput" /></li>
	     <li><label>电话查询</label><input name="m_user_phone" type="text" class="scinput" /></li>
	    <li><label>&nbsp;</label><input type=submit class="scbtn" value="查询"/></li>
    </ul> -->
    </div>
    <div>总${page.total}条数据</div><d:pagination pages="${page}" />
    <table class="tablelist" style="width:100%;height:100%;">
    	<thead>
    	<tr>
        <th>公告标题</th>
      <!--   <th>公告内容</th> -->
        <th>发布人</th>
        <th>发布时间</th>
         <th colspan="3">操作<i class="sort"><img src="${pageContext.request.contextPath}/resources/images/px.gif" /></i></th>
        </tr>
        </thead>
        <tbody>
        <!-- 以下数据应从数据表遍历而得 -->
		<c:forEach items="${page.result}" var="item">
				<tr id="1">
					<td width="150">${item.notice_tiltle}</td>
					<td width="100">${item.pub_name}</td>
					<td width="80">${item.create_time}</td>
					<td width="10">
					   <a href='${ctx}/notice/noticeDetails/${item.notice_id}'> 
					            查看详情
					   </a>
					</td>
					<td width="10" style="align:right;">
					   <a href='${ctx}/notice/findNoticeById/${item.notice_id}' > 
						 点击修改
					   </a>
					</td>
					<td width="10">
					    <a href='${ctx}/notice/deleteNoticeById/${item.notice_id}'
						   onclick="javascript:if(!confirm('确认删除吗？'))
			               {return false};"> X 
					    </a>
					</td>
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
