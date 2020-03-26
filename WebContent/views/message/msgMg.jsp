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
 <form  action="${ctx}/queryCommentInfMG" method="post" id="form">
	<input type="hidden" name="status" value=query />
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">留言管理</a></li>
    <li><a href="#">留言信息</a></li>
    <li><a href="#">基本内容</a></li>
    <li><b style="color:red"> ${name}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="rightinfo" style="width:100%;height:100%;">
    <div class="tools">
    <!--  综合查询-->  
    <ul class="seachform">
	     <li><label>标题查询</label><input name="c_title" type="text"  value="${c_title}" class="scinput" /></li>
	     <li><label>&nbsp;</label><input type=submit class="scbtn" value="查询" onclick="queryPage()"/></li>
    </ul>
    </div>
    <div>总${page.total}条数据</div><d:pagination pages="${page}" />
    <table class="tablelist" style="width:100%;height:100%;">
    	<thead>
    	<tr>
        <th>留言人</th>
        <th>标题</th>
        <th>留言时间</th>
        <th>回复人</th>
        <th>回复时间</th>
        <th>是否审核</th>
        <th colspan="3" align="center">操作<i class="sort"><img src="${pageContext.request.contextPath}/resources/images/px.gif" /></i></th>
        </tr>
        </thead>
        <tbody>
        <!-- 以下数据应从数据表遍历而得 -->
		<c:forEach items="${page.result}" var="item">
				<tr id="1">
				    <td width="30">${item.contentName}</td>
				    <td width="80">${item.c_title}</td>
					<td width="40">${item.ceateTime}</td>
					<td width="30">${item.rContentName}</td>
					<td width="40">${item.rDateTime}</td>
					<td width="38">${item.state}</td>
				    <td width="40">
					   <a href='${ctx}/showMsgDetails/${item.c_id}'> 
						     详细信息
					   </a>
					</td>
				    <td width="40">
					   <a href='${ctx}/replyMsg/${item.c_id}'> 
						     点击审核
					   </a>
					</td>
					<td width="40">
			             <a
						   href='${ctx}/deleteMSG/${item.c_id}'
						   onclick="javascript:if(!confirm('确认删除留言信息吗？'))
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
