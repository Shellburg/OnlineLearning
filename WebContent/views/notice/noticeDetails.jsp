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
    <li><a href="#">公告管理</a></li>
    <li><a href="#">公告详细信息</a></li>
    <li><b style="color:red">${name}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">公告详细信息</a></li> 
  	</ul>
    </div> 
  	<div id="tab1" class="tabson">
    <form action="${ctx}/notice/updateNoticeById" method="post" onsubmit="return validate()">
    <input type="hidden" name="notice_id" value="${notice.notice_id}" />
    <ul class="forminfo">
    <li><label>公告标题<b>*</b></label>
         <input  type="text" class="dfinput"  name="notice_tiltle" id ="notice_tiltle" value="${notice.notice_tiltle}" style="width:518px;"/>
    </li>
     <li><label>发布时间<b>*</b></label>
         <input  type="text" class="dfinput"  name="notice_tiltle" id ="notice_tiltle" value="${notice.create_time}" style="width:518px;"/>
    </li>
    <li><label>发布人<b>*</b></label>
         <input  type="text" class="dfinput"  name="notice_tiltle" id ="notice_tiltle" value="${notice.pub_name}" style="width:518px;"/>
    </li>
    <li><label>公告内容<b>*</b></label>
        <textarea rows="8" cols="86" class="dfinput" style="width:518px;height:140px;">${notice.notice_content}</textarea>
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