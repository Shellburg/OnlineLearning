<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/resources/css/select.css" rel="stylesheet" type="text/css" />

<!-- 引入时间的css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/calendar.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/select-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/editor/kindeditor.js"></script>

   <script type="text/javascript">
     //表单提交验证
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
		   alert("发布公告成功！！！");
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
    <li><a href="#">发布公告</a></li>
    <li><b style="color:red">${name}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">发布公告信息</a></li> 
  	</ul>
    </div> 
  	<div id="tab1" class="tabson">
  	<form  id="form" action="${ctx}/notice/pubNitce" method="post" onsubmit="return validate()" >
    <ul class="forminfo">
    <li><label>公告标题<b>*</b></label>
         <input  maxlength="50" type="text" class="dfinput"  name="notice_tiltle" id ="notice_tiltle"  style="width:518px;"/>
    </li>
    <li><label>公告内容<b>*</b></label>
        <textarea maxlength="300" name="notice_content" id="notice_content"  class="dfinput" style="width:518px;height:140px;" ></textarea>
    </li>
    <li><label>&nbsp;</label><input type="submit" class="btn" value="提交"/></li>
    </ul>
    </form>
    </div> 
	</div> 
    </div>
</body>
</html>