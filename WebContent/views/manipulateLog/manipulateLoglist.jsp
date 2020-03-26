<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="cn.st.entity.*"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib uri="http://gx.edu/dTags" prefix="d"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script  type="text/javascript" src="${pageContext.request.contextPath}/resources/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

}); 
</script>
   <script type="text/javascript">
    	     //JS校验form表单信息
    	     function checkData(){
    	     	var fileDir = $("#upfile").val();
    	     	var department = $("#department").val();
    	     	var suffix = fileDir.substr(fileDir.lastIndexOf("."));
    	     	if("" == fileDir){
    	     		alert("选择需要导入的Excel文件！");
    	     		return false;
    	     	}if("" == department){
    	     		alert("选择需要系别！");
    	     		return false;
    	     	}
    	     	if(".xls" != suffix && ".xlsx" != suffix ){
    	     		alert("选择Excel格式的文件导入！");
    	     		return false;
    	     	}
    	     	return true;
    	     }
    	     
    	     //ajax 方式下载文件操作
			 $(document).ready(function(){
        		$('#exportExcel').click(function(){
          			$('#form2').ajaxSubmit({  
          				dataType: 'text',
          				error: errorMsg
          			}); 
					function errorMsg(){ 
						alert("导出excel出错！");    
					}
        		});
    	     });
    	     
    	     
			//JS校验form表单信息--验证查询的起始时间
    	     function validateTime(){
    	     	var starTime = $("#starTime").val();
    	     	var endTime = $("#endTime").val();
    	     	if(starTime>endTime){
    	            alert("开始时间不能大于结束时间！");
    	            document.getElementById("starTime").focus();
    	            return false;
    	        }
    	     }
    	     
    </script>

</head>


<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">日志管理</a></li>
    <li><a href="#">日志信息信息</a></li>
    <li><a href="#">基本内容</a></li>
    <li><b style="color:red"> ${name}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="rightinfo">
    <div class="tools">
    	<ul class="toolbar">
	         <li ><span><a href="${ctx}/truncateMaLog" onclick="javascript:if(!confirm('确认清空日志吗？')){return false};">
	          <img src="${pageContext.request.contextPath}/resources/images/clear.png"
	         style="width:30px;height:28px;position:relative;top:-2px;"
	          /></a></span>清空日志</li>
        </ul>
    </div>
    <form  action="${ctx}/listLog" method="post" id="form" > <!-- onsubmit="return validateTime()" -->
	<input type="hidden" name="status" value=query />
	<!--  综合查询  -->  
    <ul class="seachform" >
         <li style="display:inline;"><label>姓名查询</label><input name="userName" id="userName"   value="${userName}" type="text" class="scinput" /></li>
	     <li style="display:inline;"><label>开始时间</label>
	         <input style="width: 140px;" readonly="readonly"
				onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})" 
				name="starTime"  id="starTime" type="text" class="scinput" />
		 </li>
	     <li style="display:inline;"><label>结束时间</label>
	           <input style="width: 140px;" readonly="readonly"
				onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"
				name="endTime" id="endTime" type="text" class="scinput" />
	      </li>
	     <li style="display:inline;"><label>&nbsp;</label><input type=submit class="scbtn" value="查询" onclick="queryPage()"/></li>
    </ul>
     <div style="position:fixed;left:0px;top:100px;width:500px;height:30px;margin-top:25px;">总${page.total}条数据<d:pagination pages="${page}" /></div>
    <table class="tablelist" style="margin-top:26px;" >
    	<thead>
    	<tr>
        <th>操作人</th>
        <th>描述</th>
        <th>时间</th>
        <th>备注</th>
        </tr>
        </thead>
        <tbody>
        <!-- 以下数据应从数据表遍历而得 -->
	        <c:forEach items="${page.result}" var="item">
				<tr id="1">
				    <td width="20%">${item.userName}</td>
					<td width="40%">${item.mpDescribe}</td>
					<td width="20%">${item.createTime}</td>
					<td width="10">${item.rem1}</td>
				</tr>
			</c:forEach>
        </tbody>
    </table>
     </form>	
    </div>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>
</body>
</html>
