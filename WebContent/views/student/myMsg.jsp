<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
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
    	   alert("恭喜你注册成功！！！");	
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
	        }});   
    }  
  </script>
</head>

<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">学生管理</a></li>
    <li><a href="#">个人信息</a></li>
   <li><b style="color:red">${stuName}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">我的信息</a></li> 
  	</ul>
    </div> 
  	<div id="tab1" class="tabson">
    <c:forEach items="${myMsg}" var="item">
    <ul class="forminfo">
    <li><label>学号<b>*</b></label><input name="stu_num" id="stu_num" type="text" class="dfinput" value="${item.stu_num}" readonly="readonly"  style="width:518px;" /></li>
    <li><label>姓名<b>*</b></label><input name="stu_name" id="stu_name" type="text" class="dfinput" value="${item.stu_name}" readonly="readonly"   style="width:518px;"/></li>
    <li><label>密码<b>*</b></label><input name="psw" id="psw" type="password" class="dfinput" value="${item.psw}" readonly="readonly"  style="width:518px;"/></li>
    <li><label>系别<b>*</b></label><input name="department" id="department" type="text" class="dfinput" value="${item.department}"  readonly="readonly" style="width:518px;"/></li>
    <li><label>班级<b>*</b></label><input name="classT" id="classT" type="text" class="dfinput" value="${item.classT}" readonly="readonly"  style="width:518px;"/></li>
    <li><label>电话<b>*</b></label><input name="phone" id="phone" type="text" class="dfinput" value="${item.phone}" readonly="readonly" style="width:518px;"/></li>
    <li><label>邮箱<b>*</b></label><input name="eamil" id="eamil" type="text" class="dfinput" value="${item.eamil}"  readonly="readonly" style="width:518px;"/></li>
    <li><label>性别<b>*</b></label><input name="gender" id="gender" type="text" class="dfinput" value="${item.gender}" readonly="readonly"  style="width:518px;"/></li>
    <li style="margin-top:19px;"><label>&nbsp;</label>
	<form action="${ctx}/updateByMy/${item.stu_id}" name="form" method="get">
	  <input  type="submit" class="btn"  value="去修改我的信息"/>
	</form>
    </li>
    </ul>
     </c:forEach>
    </div>
	</div> 
    </div>
</body>
</html>