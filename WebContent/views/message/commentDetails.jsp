<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/select.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/select-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/editor/kindeditor.js"></script>


  
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 345			  
	});
	
});
</script>

   <script type="text/javascript">
    //表单提交验证
	
   </script>
   
    <script type="text/javascript">
    function validate(){
    	 var notice_tiltle=document.getElementById("notice_tiltle").value;
  	   var notice_content=document.getElementById("notice_content").value;
  	   if(notice_tiltle==""){
  		   alert("请输入公告标题");
  		   return false;
  	   }if(notice_content==""){
  		   alert("请输入公告内容");
  		   return false;
  	   }if(notice_tiltle!=""&&notice_content!=""){
  		   alert("修改公告成功！！！");
  		   return true;
  	   }
    }
  </script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">留言管理</a></li>
    <li><a href="#">留言详细信息</a></li>
    <li><b style="color:red">${name}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">留言详细信息</a></li> 
  	</ul>
    </div> 
  	<div id="tab1" class="tabson">
    <form action="${ctx}/notice/updateNoticeById" method="post" onsubmit="return validate()">
    <input type="hidden" name="notice_id" value="${comments.c_id}" />
    <ul class="forminfo">
    <li><label>留言标题<b>*</b></label>
         <input  type="text" class="dfinput"  name="c_title" id ="c_title" value="${comments.c_title}" style="width:518px;"/>
    </li>
    <li><label>留言人<b>*</b></label>
         <input  type="text" class="dfinput"  name="contentName" id ="contentName" value="${comments.contentName}" style="width:518px;"/>
    </li>
     <li><label>留言时间<b>*</b></label>
         <input  type="text" class="dfinput"  name="ceateTime" id ="ceateTime" value="${comments.ceateTime}" style="width:518px;"/>
    </li>
    <li><label>留言内容<b>*</b></label>
        <textarea rows="8" cols="86" class="dfinput" style="width:518px;height:140px;">${comments.content}</textarea>
    </li>
     <li><label>回复人<b>*</b></label>
         <input  type="text" class="dfinput"  name="rContentName" id ="rContentName" value="${comments.rContentName}" style="width:518px;"/>
    </li>
    <li><label>回复时间<b>*</b></label>
         <input  type="text" class="dfinput"  name="rDateTime" id ="rDateTime" value="${comments.rDateTime}" style="width:518px;"/>
    </li>
    <li><label>回复内容<b>*</b></label>
        <textarea rows="8" cols="86" class="dfinput" style="width:518px;height:140px;">${comments.rContent}</textarea>
    </li>
     <li><label>审核状态<b>*</b></label>
         <input  type="text" class="dfinput"  name="state" id ="state" value="${comments.state}" style="width:518px;"/>
    </li>
    <li><label>&nbsp;</label><input type="button" onClick="history.go(-1);" class="btn" value="返回"/></li>
    </ul>
    </form>
    </div> 
	</div> 
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
    </div>
</body>
</html>