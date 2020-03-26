<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
<%-- <link href="${pageContext.request.contextPath}/resources/css/select.css" rel="stylesheet" type="text/css" /> --%>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/select-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/editor/kindeditor.js"></script>
    
    
    <script type="text/javascript">
    function validate(){
    	var stu_num=document.getElementById("stu_num").value;
    	var stu_name=document.getElementById("stu_name").value;
    	var psw=document.getElementById("psw").value;
    	var phone=document.getElementById("phone").value;
    	var classT=document.getElementById("classT").value;
    	var department=document.getElementById("department").value;
    	var eamil=document.getElementById("eamil").value;
    	var emailPat=/^(.+)@(.+)$/;/*正则表达式验证特殊符号 @和. 邮件的验证*/
    	var reg = new RegExp("^[0-9]*$");/*正则表达式验证数字的验证*/
        if(reg.test(stu_num) == false){
		    alert("请输入数字!");	
		    return false;
		}if(stu_num==""||stu_num.length!=11){
    	   alert("请输入11位学号！！！");	
    	   return false;
    	}if(stu_name==""){
    	   alert("请输入您的姓名！！！");	
    	   return false;
    	}if(psw==""){
    	   alert("请输入的密码！！！");	
    	   return false;
    	}if(phone==""||phone.length!=11){
    	   alert("请输入正确的号码！！！");	
    	   return false;
    	}if(department==""){
    	   alert("请输入该学生的系别！！！");	
    	   return false;
    	}
    	if(classT==""){
    	   alert("请输入该学生的班级！！！");	
    	   return false;
    	}if(eamil==""||!emailPat.test(eamil)){
    	   alert("电子邮件地址必须包括 ( @ 和 . )！！！");	
    	   return false;
    	}
    	if(stu_num!=""&&stu_name!=""&&psw!=""&&phone!=""&&department!=""&&classT!=""&&eamil!=""){
    	   alert("恭喜你添加学生成功！！！");	
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
	            	 //alert("该学号可以注册！！！"); 
	            }else{
	                alert("该学号已存在请重新输入！！！"); 
	                document.getElementById("stu_num").value="";//清空输入框
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
    <li><a href="#">学生信息</a></li>
    <li><a href="#">学生添加</a></li>
   <li><b style="color:red">${name}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">学生添加</a></li> 
  	</ul>
    </div> 
  	<div id="tab1" class="tabson">
 <form  action="${ctx}/studentAdd" method="post"  onsubmit="return validate()">
    <ul class="forminfo">
    <li><label>学号<b>*</b></label><input maxlength="11"  name="stu_num" id="stu_num" type="text" class="dfinput" placeholder="请填写学号"  style="width:518px;" onblur="validateStuNum()"/></li>
    <li><label>姓名<b>*</b></label><input name="stu_name" id="stu_name" type="text" class="dfinput" placeholder="请填写姓名"  style="width:518px;"/></li>
    <li><label>密码<b>*</b></label><input name="psw" id="psw" type="password" class="dfinput" placeholder="请填写密码"  style="width:518px;"/></li>
    <li><label>系别<b>*</b></label>
        <select name=department id="department" class="dfinput" style="width:518px;">
			<option value="" >---请选择---</option>
			<option value="数学系" style="sbackground-color:green;">数学系</option>
			<option value="体育系" style="sbackground-color:green;">体育系</option>
			<option value="计科系" style="sbackground-color:green;">计科系</option>
			<option value="外语系" style="sbackground-color:green;">外语系</option>
			<option value="物理系" style="sbackground-color:green;">物理系</option>
			<option value="化学系" style="sbackground-color:green;">化学系</option>
			<option value="生物系" style="sbackground-color:green;">生物系</option>
		</select>
    </li>
    <li><label>班级<b>*</b></label><input name="classT" id="classT" type="text" class="dfinput" placeholder="请填写班级"  style="width:518px;"/></li>
    <li><label>电话<b>*</b></label><input maxlength="11"  name="phone" id="phone" type="text" class="dfinput" placeholder="请填写电话"  style="width:518px;"/></li>
    <li><label>邮箱<b>*</b></label><input name="eamil" id="eamil" type="text" class="dfinput" placeholder="请填写邮箱"  style="width:518px;"/></li>
    <li ><label>性别<b>*</b></label>
     男<input name="gender" id="gender" type="radio"  checked="checked"  value="男"  style="width:50px; margin-top:9px;"/>
           女<input name="gender" id="gender" type="radio"    value="女"  style="width:50px; margin-top:9px;"/>
    </li>
    <li style="margin-top:20px;"><label>&nbsp;</label><input name="" type="submit" class="btn" value="提交"/></li>
    </ul>
    </form>
    </div>
	</div> 
    </div>
</body>
</html>