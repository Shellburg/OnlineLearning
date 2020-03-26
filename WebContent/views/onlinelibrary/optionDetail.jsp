<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib uri="http://gx.edu/dTags" prefix="d"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
<%-- <link href="${pageContext.request.contextPath}/resources/css/select.css" rel="stylesheet" type="text/css" /> --%>

<!-- 引入时间的css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/calendar.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/select-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/editor/kindeditor.js"></script>

   <script type="text/javascript">
     //表单提交验证
	function validate(){
	   var question=document.getElementById("question").value;
	   var up_id=document.getElementById("up_id").value;
	   var op_a=document.getElementById("op_a").value;
	   var op_b=document.getElementById("op_b").value;
	   var op_c=document.getElementById("op_c").value;
	   var op_d=document.getElementById("op_d").value;
	   var parse=document.getElementById("parse").value;
	   if(question==""){
		   alert("请输入题目的问题");
		   return false;
	   }if(up_id==""){
		   alert("请选择对应的视频");
		   return false;
	   }if(op_a==""){
		   alert("请输入选项A");
		   return false;
	   }if(op_b==""){
		   alert("请输入选项B");
		   return false;
	   }if(op_c==""){
		   alert("请输入选项C");
		   return false;
	   }if(op_d==""){
		   alert("请输入选项D");
		   return false;
	   }if(parse==""){
		   alert("答案的解析");
		   return false;
	   }if(question!=""&&up_id!=""&&op_a!=""&&op_b!=""&&op_c!=""&&op_d!=""&&parse!=""){
		   alert("修改题库成功！！！");
		   return true;
	   }
	   
	}
     /*jquery根据后台传值判断是否选中*/
     $(document).ready(function(){
    	 $("#btn_p").click(function(){
    		
    	 });
    	
     });
     
     
     
    </script>
</head>
<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">题库管理</a></li>
    <li><a href="#">题库信息</a></li>
    <li><b style="color:red">${name}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">题目详细信息</a></li> 
  	</ul>
    </div> 
  	<div id="tab1" class="tabson">
    <ul class="forminfo">
    <li ><label>问题<b>*</b></label>
     <textarea   class="dfinput"  name="question" id ="question"  style="width:100%;height:100px;">${options.question}</textarea>
    </li>
    <li >选项A<b>*</b>
         <input  type="text" class="dfinput"  name="op_a" id ="op_a"  style="width:180px;" value="${options.op_a}"/>
    </li>
    <li>选项B<b>*</b>
         <input  type="text" class="dfinput"  name="op_b" id ="op_b"  style="width:180px;" value="${options.op_b}"/>
    </li>
    <li>选项C<b>*</b>
         <input  type="text" class="dfinput"  name="op_c" id ="op_c"  style="width:180px;" value="${options.op_c}"/>
    </li>
    <li>选项D<b>*</b>
         <input  type="text" class="dfinput"  name="op_d" id ="op_d"  style="width:180px;" value="${options.op_d}"/><br>
    </li>
     <li><label>正确项<b>*</b></label>
           A<input name="op_answer" type="radio"  value="A" ${options.op_answer eq'A'?'checked':''}  style="width:50px;margin-top:8px;"/>
           B<input name="op_answer" type="radio"  value="B" ${options.op_answer eq'B'?'checked':''}  style="width:50px;margin-top:8px;"/>
           C<input name="op_answer" type="radio"  value="C" ${options.op_answer eq'C'?'checked':''}  style="width:50px;margin-top:8px;"/>
           D<input name="op_answer" type="radio"  value="D" ${options.op_answer eq'D'?'checked':''}  style="width:50px;margin-top:8px;"/>
    </li>
    <li style="display:inline;"><label>解析<b>*</b></label>
          <textarea   class="dfinput"  name="parse" id ="parse"  style="width:100%;height:100px;">${options.parse}</textarea>
    </li>
    <li style="margin-top:10px;"><b style="color:black;">请选择对应视频*</b>
         <select name="up_id" id="up_id" class="dfinput" >
						<c:forEach items="${vedioes}" var="item" >
							<option value="${options.up_id}" ${item.up_id eq options.up_id ? "selected='selected'":""} >${item.title}</option>	
						</c:forEach>
		</select>
    </li>
    <li style="margin-top:20px;"><label>&nbsp;</label><input type="button" onClick="history.go(-1);" class="btn" value="返回"/></li>
    </ul>
    </div> 
	</div> 
    </div>
</body>
</html>