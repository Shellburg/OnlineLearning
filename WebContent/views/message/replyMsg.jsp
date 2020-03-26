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
	   var rContent=document.getElementById("rContent").value;
	   if(rContent==""){
		   confirm("什么都不给他回复吗?"); //在页面上弹出确认对话框
		   return false;
	   }
	}
    </script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">留言管理</a></li>
    <li><a href="#">回复留言(审核信息)</a></li>
    <li><b style="color:red">${name}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">回复留言</a></li> 
  	</ul>
    </div> 
  	<div id="tab1" class="tabson">
  	<form id="form" action="${ctx}/replyMessage" method="post" onsubmit="return validate()" >
  	<input name="c_id" value="${comments.c_id}" type="hidden"/>
    <ul class="forminfo">
    <li><label>留言内容<b>*</b></label>
        <textarea name="rContent" id="rContent"  class="dfinput" style="width:518px;height:140px;" ></textarea>
    </li>
    <li><label>&nbsp;</label><input type="submit" class="btn" value="提交"/></li>
    </ul>
    </form>
    </div> 
	</div> 
    </div>
</body>

     <!--选择时间 -->
   <script type="text/javascript" src="../js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="../js/calendar.js"></script>
	<script>
		$('.hhmmss').each(function() {
			var a = new Calendar({
				targetCls: $(this),
				type: 'yyyy-mm-dd HH:MM:SS',
				wday: 2
			});
		});
		$('.yyyy-mm-dd').each(function() {
			var b = new Calendar({
				targetCls: $(this),
				type: 'yyyy-mm-dd',
				wday: 2
			});
		});
		$('.hhmm').each(function() {
			var b = new Calendar({
				targetCls: $(this),
				type: 'HH:MM',
				wday: 2
			});
		});	    
		$('.hhmmssOnly').each(function() {
			var b = new Calendar({
				targetCls: $(this),
				type: 'HH:MM:SS',
				wday: 2
			});
		});
		$('.hhmmYear').each(function() {
			var b = new Calendar({
				targetCls: $(this),
				type: 'yyyy-mm-dd HH:MM',
				wday: 2
			});
		});
	</script>
</html>