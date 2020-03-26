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
    	var psw=document.getElementById("psw").value;
    	var phone=document.getElementById("phone").value;
    	var eamil=document.getElementById("eamil").value;
    	var classT=document.getElementById("classT").value;
    	var department=document.getElementById("department").value;
    	var emailPat=/^(.+)@(.+)$/;/*正则表达式验证特殊符号 @和. 邮件的验证*/
    	var reg = new RegExp("^[0-9]*$");/*正则表达式验证数字的验证*/
    	var confirm_psw=document.getElementById("confirm_psw").value;
    	if(psw==""){
    	    alert("请输入原密码！！！");	
 		    return false;
    	}
        if(reg.test(phone) == false){
		    alert("请输入数字!");	
		    return false;
		}if(confirm_psw==""){
    	   alert("请输入的需要修改的密码！！！");	
    	   return false;
    	}
		if(classT==""){
    	   alert("请输入的班级！！！");	
    	   return false;
    	}if(department==""){
    	   alert("请输入学生系别！！！");	
    	   return false;
    	}if(phone==""||phone.length!=11){
    	   alert("请输入正确的号码！！！");	
    	   return false;
    	}if(eamil==""||!emailPat.test(eamil)){
    	   alert("电子邮件地址必须包括 ( @ 和 . )！！！");	
    	   return false;
    	}
    	if(psw!=""&&department!=""&&classT!=""&&confirm_psw!=""&&phone!=""&&eamil!=""){
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
	   var psw=document.getElementById("psw").value;
	   $.ajax({  
	        data:"psw="+psw, 
	        type:"POST",  
	        dataType:'json',
	        url:"${ctx}/updateMyInfValidate", 
	        success:function(data){  
        	 if(data.student==null){
            	 alert("您输入的密码不正确！！！"); 
            	 document.getElementById("psw").value="";//清空输入框
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
    <li><a href="#">学生管理</a></li>
    <li><a href="#">学生信息</a></li>
   <li><b style="color:red">${stu_name}</b>，欢迎您登录</li>
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
    <form  action="${ctx}/updateByMy" method="post"  onsubmit="return validate()">
	<input type="hidden" name="stu_id" value="${student.stu_id}" />
    <ul class="forminfo" style="float:left;">
    <li style="display:inline;">姓名<b>*</b><input readonly="readonly" name="stu_name" id="stu_name" type="text" class="dfinput"style="width:200px;" value="${student.stu_name}" /></li>
    <li style="display:inline;">学号<b>*</b><input readonly="readonly" maxlength="11" name="stu_num" id="stu_num" type="text" class="dfinput"style="width:200px;" value="${student.stu_num}" /></li>
    <li style="display:inline;">性别<b>*</b>
        男<input name="gender" id="gender" type="radio" readonly="readonly" class="dfinput"style="width:30px;" value="男" ${student.gender eq'男'?'checked':''}  />
        女<input name="gender" id="gender" type="radio"  readonly="readonly" class="dfinput"style="width:30px;" value="女" ${student.gender eq'女'?'checked':''} />
    </li></br></br>
    <li style="display:inline;">系别<b>*</b><input readonly="readonly" name="department" id="department" type="text" class="dfinput"style="width:200px;" value="${student.department}" /></li>
    <li style="display:inline;">班级<b>*</b><input readonly="readonly" name="classT" id="classT" type="text" class="dfinput"style="width:200px;" value="${student.classT}"/></li></br></br>
    <li style="display:inline;">电话<b>*</b><input name="phone" id="phone" type="text" class="dfinput"style="width:200px;" value="${student.phone}" /></li>
    <li style="display:inline;">邮箱<b>*</b><input name="eamil" id="eamil" type="text" class="dfinput"style="width:200px;" value="${student.eamil}" /></li></br></br>
    
    <li >学生密码<b>*</b><input name="psw" id="psw" type="password" class="dfinput"style="width:200px;" onblur="updateMyInfValidate()"/></li>
    <li >新的密码<b>*</b><input name="new_psw" id="new_psw" type="password" class="dfinput"style="width:200px;" /></li>
    <li >确认密码<b>*</b><input name="confirm_psw" id="confirm_psw" type="password" class="dfinput"style="width:200px;"  onblur="validatePSW()"/></li>
    <li style="margin-top:20px;"><label>&nbsp;</label><input name="" type="submit" class="btn" value="提交"/></li>
    </ul>
    </form>
    </div> 
	</div> 
    </div>
</body>
</html>