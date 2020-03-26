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
    	var loginname=document.getElementById("loginname").value;
    	var confirm_psw=document.getElementById("confirm_psw").value;
    	if(loginname==""){
    	   alert("请输入您的登录名称！！！");	
    	   return false;
    	}if(confirm_psw==""){
    	   alert("请输入的密码！！！");	
    	   return false;
    	}if(loginname!=""&&confirm_psw!=""){
    	   alert("恭喜你修改成功！！！");	
    	   return true;
    	}
    }
    /*   新旧密码验证 */
    function validatePSW(){
    	var new_psw=document.getElementById("new_psw").value;
    	var confirm_psw=document.getElementById("confirm_psw").value;
        if(new_psw!=confirm_psw){
		    alert("输入新密码与确认密码前后不一致！！！");	
		    document.getElementById("new_psw").value="";
		    document.getElementById("confirm_psw").value="";
		    return false;
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
   <li><b style="color:red"> ${name}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">管理员修改</a></li> 
  	</ul>
    </div> 
  	<div id="tab1" class="tabson">
    <form action="${ctx}/admin/update" method="post" onsubmit="return validate()">
    <input type="hidden" name="ad_id" value="${admin.ad_id}" />
    <ul class="forminfo">
    <li><label>登录名<b>*</b></label><input name="loginname" id="loginname" type="text" class="dfinput"   style="width:518px;" value="${admin.loginname}"/></li>
    <li><label>旧密码<b>*</b></label><input name="pwd" id="pwd" type="password" class="dfinput"   style="width:518px;" value="${admin.pwd}"/></li>
    <li><label>新密码<b>*</b></label><input name="new_psw" id="new_psw" type="password" class="dfinput"   style="width:518px;" /></li>
    <li><label>确认密码<b>*</b></label><input name="confirm_psw" id="confirm_psw" type="password" class="dfinput"   style="width:518px;" onblur="validatePSW()"/></li>
    <li ><label>管理级别<b>*</b></label> 
     系统管理员<input name="adminLevel" id="m_user_y" type="radio"  value="系统管理员"  ${admin.adminLevel eq '系统管理员'?'checked':''}  style="width:50px; margin-top:9px;"/>
           教师<input name="adminLevel" id="m_user_l" type="radio"    value="教师"  ${admin.adminLevel eq '教师'?'checked':''}  style="width:50px; margin-top:9px;"/>
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