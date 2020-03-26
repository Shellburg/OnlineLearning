<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	function checkForm(){
	    
	}
   </script>
   
    <script type="text/javascript">
    function validate(){
    	var name=document.getElementById("name").value;
    	var loginname=document.getElementById("loginname").value;
    	var pwd=document.getElementById("pwd").value;
    	if(name==""){
    	   alert("请输入您的姓名！！！");	
    	   return false;
    	}if(loginname==""){
    	   alert("请输入您的登录名称！！！");	
    	   return false;
    	}if(pwd==""){
    	   alert("请输入的密码！！！");	
    	   return false;
    	}if(adminLevel!=""&&loginname!=""&&pwd!=""){
    	   alert("恭喜你添加成功！！！");	
    	   return true;
    	}
    }
  
  </script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">系统设置</a></li>
   <li><b style="color:red">${name}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">管理员添加</a></li> 
  	</ul>
    </div> 
  	<div id="tab1" class="tabson">
   <form  action="${ctx}/admin/save" method="post"  onsubmit="return validate()">
    <ul class="forminfo">
    <li><label>管理员姓名<b>*</b></label><input name="name" id="name" type="text" class="dfinput" placeholder="请填写管理员姓名"  style="width:518px;"/></li>
    <li><label>登录名<b>*</b></label><input name="loginname" id="loginname" type="text" class="dfinput" placeholder="请填写管理员登录名"  style="width:518px;"/></li>
    <li><label>密码<b>*</b></label><input name="pwd" id="pwd" type="password" class="dfinput" placeholder="请填写密码"  style="width:518px;"/></li>
    <li ><label>管理级别<b>*</b></label>
     系统管理员<input name="adminLevel"  type="radio"  checked="checked"  value="系统管理员"  style="width:50px; margin-top:9px;"/>
           教师<input name="adminLevel"  type="radio"    value="教师"  style="width:50px; margin-top:9px;"/>
    </li>
    <li style="margin-top:40px;"><label>&nbsp;</label><input name="" type="submit" class="btn" value="提交"/></li>
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