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
    	//document.getElementById("pwd").value="";//清空输入框
    	var pwd=document.getElementById("pwd").value;
    	var name=document.getElementById("name").value;
    	var confirm_psw=document.getElementById("confirm_psw").value;
    	if(pwd==""){
    	    alert("请输入原密码！！！");	
 		    return false;
    	}if(name==""){
    	    alert("请输入需要改变的姓名！！！");	
 		    return false;
    	}if(confirm_psw==""){
    	   alert("请输入的需要修改的密码！！！");	
    	   return false;
    	}if(pwd!=""&&loginname!=""&&classT!=""&&confirm_psw!=""){
    	   alert("修改信息成功！！！");	
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
   /*ajax验证账号和输入的密码是否正确才允许修改学生自己的信息 */
   function updateMyInfValidate(){
	   /* var psw=$("psw").val(); */
	   var pwd=document.getElementById("pwd").value;
	   $.ajax({  
	        data:"pwd="+pwd, 
	        type:"POST",  
	        dataType:'json',
	        url:"${ctx}/admin/updateMyInfValidate", 
	        success:function(data){  
        	 if(data.admin==null){
            	 alert("您输入的密码不正确！！！"); 
            	 document.getElementById("pwd").value="";//清空输入框
            }
	        }
	        });   
   }
  </script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">管理员管理</a></li>
    <li><a href="#">教师信息</a></li>
   <li><b style="color:red"> ${name}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">修改我的信息</a></li> 
  	</ul>
    </div> 
  	<div id="tab1" class="tabson">
    <form  action="${ctx}/admin/updateByteacher" method="post"  onsubmit="return validate()">
	<input type="hidden" name="ad_id" value="${admin.ad_id}" />
    <ul class="forminfo" style="float:left;">
    <li >教师密码<b>*</b><input name="pwd" id="pwd" type="password" class="dfinput"style="width:200px;" onblur="updateMyInfValidate()"/></li>
    <li >新的密码<b>*</b><input name="new_psw" id="new_psw" type="password" class="dfinput"style="width:200px;" /></li>
    <li >确认密码<b>*</b><input name="confirm_psw" id="confirm_psw" type="password" class="dfinput"style="width:200px;"  onblur="validatePSW()"/></li>
    <li >名称<b>*</b><input name="name" id="name" type="text" class="dfinput"  value="${admin.name}"style="width:200px;" /></li>
    <li style="margin-top:20px;"><label>&nbsp;</label><input name="" type="submit" class="btn" value="提交"/></li>
    </ul>
    </form>
    </div> 
	</div> 
    </div>
</body>
</html>