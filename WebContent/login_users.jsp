<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${resourcePath}/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${resourcePath}/js/jquery.js"></script>
<script src="${resourcePath}/js/cloud.js" type="text/javascript"></script>


<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    });  
});  
</script> 
    <script type="text/javascript">
    //表单提交验证
	function checkForm(){
	    var stu_num = document.getElementById("stu_num").value;
	    var psw = document.getElementById("psw").value;
		var reg = new RegExp("^[0-9]*$");/*正则表达式验证数字的验证*/
	    if(stu_num==""){
			alert("请输入学号!");	
			return false;
		}if(reg.test(stu_num)== false){
		    alert("请输入数字!");	
		    return false;
		}if(psw==""){
			alert("请输入密码!")	
			return false;
		}if(stu_num!=""&&psw!=""){
			 return true;
		}
		 return true;
	}
		 /*ajax验证 验证添加学生学号时是否存在*/
	    function validateStuNum(){  
		   var stu_num=document.getElementById("stu_num").value;
		   $.ajax({  
		        data:"stu_num="+stu_num, 
		        type:"POST",  
		        dataType:'json',
		        url:"${ctx}/validateStuNum", 
		        success:function(data){  
		            if(data.student==null){
		            	 alert("该学号不存在！！！"); 
		            	 document.getElementById("stu_num").value="";//清空输入框
		            }/* else{
		                alert("该学号已存在请重新输入！！！"); 
		                document.getElementById("stu_num").value="";//清空输入框
		            } */
		        }});   
	    }  
	    /* 跳转到管理员登录页面 */
	    function adminLogin(){
	    	 if (confirm("你确定要管理员登录吗？是－选择确定，否-选择取消")){
	    	   window.location.href="login_admin.jsp?act=adminLogin"
	    	 }
	    	 }
   </script>
</head>

<body style="background-color:#1c77ac; background-image:url(${resourcePath}/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
    <ul>
    <li><a href="${ctx}/InitIndexInf">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
    <div class="loginbox">
    <form action="${ctx}/studentLogin" method="post" onsubmit="return checkForm()">
    <ul>
    <li>
         <input name="stu_num" id="stu_num" type="text" class="loginuser" placeholder="请输入学号" onblur="validateStuNum()" />
         <span style="color:red;">${message}</span>
    </li>
    <li><input id="psw" name="psw" id="randomCode" type="password" onblur="validateCode()" class="loginpwd" placeholder="请输入密码" /></li>
    <li style="float:left;display:inline;"><input  type="submit" class="loginbtn"  value="登录"/>
    <li style="float:left;display:inline;margin-left:30px;"><input  type="button" class="loginbtn"  value="管理员登录" onclick="adminLogin()"/>
    </ul>
    </form>
    </div>
    
    </div>
    
    <div class="loginbm">【猿来入此】版权所有  </div>
</body>
</html>
