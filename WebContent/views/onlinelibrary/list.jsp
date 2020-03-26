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
    	     	var up_id = $("#up_id").val();
    	     	var suffix = fileDir.substr(fileDir.lastIndexOf("."));
    	     	if("" == fileDir){
    	     		alert("选择需要导入的Excel文件！");
    	     		return false;
    	     	}if("" == up_id){
    	     		alert("选择需要对应的视频！");
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
    </script>

</head>


<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">题库管理</a></li>
    <li><a href="#">题库信息</a></li>
    <li><a href="#">基本内容</a></li>
    <li><b style="color:red"> ${name}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="rightinfo">
    <div class="tools">
    	<ul class="toolbar">
	         <li ><span><a href="${ctx}/onlinelibrary/addOp"> <img src="${pageContext.request.contextPath}/resources/images/t01.png" /></a></span>添加</li>
        	<li ><span><a href="javascript:void(0)" id="select-file-btn"> <img src="${pageContext.request.contextPath}/resources/images/ico04.png" width="24px" /></a></span>导入EXCELL</li>
        </ul>
        
    <!--  综合查询-->  
    <!-- <ul class="seachform">
	     <li><label>姓名查询</label><input name="stu_name" type="text" class="scinput" /></li>
	    <li><label>&nbsp;</label><input type=submit class="scbtn" value="查询" onclick="queryPage()"/></li>
    </ul> -->
   <div style="float:left;width:570px;height:45px;border:1px solid #F4F4F4;border-radius:10px;" >
		 <form method="POST" enctype="multipart/form-data" id="form1" action="uploadExcel/upload.do">
			 <table>
			 	 <tr>
			 	 	<td id="show-filename"></td>
			 	 	<td> <input id="upfile" style="display:none;" type="file" name="upfile" onchange="changefile()"></td>
			 	 	<td>
			 	 	   <select name="up_id" id="up_id" class="scinput">
						<option value="" style="sbackground-color:green;">---请选择对应视频---</option>
						<c:forEach items="${vedioes}" var="item">
							<option value="${item.up_id}" ${item.up_id eq up_id ? "selected='selected'":""} >${item.title}</option>	
						</c:forEach>
		             </select>
			 	 	</td>
			 	 	<td><input style="width:150px;margin-left:10px;margin-top:5px;"type="submit" class="scbtn" value="选择题导入" onclick="return checkData()"></td>
			 	 </tr>
			 </table>
         </form>
    </div>
    </div>
    <form  action="${ctx}/onlinelibrary" method="post" id="form">
    <div>总${page.total}条数据</div><d:pagination pages="${page}" />
	<input type="hidden" name="status" value=query />
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>问题</th>
        <th colspan="3">操作<i class="sort"><img src="${pageContext.request.contextPath}/resources/images/px.gif" /></i></th>
        </tr>
        </thead>
        <tbody>
        <!-- 以下数据应从数据表遍历而得 -->
		<c:forEach items="${page.result}" var="item">
				<tr id="1">
				    <td width="370">${item.question}</td>
					 <td width="20">
					   <a href='${ctx}/onlinelibrary/showOpDetail/${item.op_id}'> 
						  详细信息
					   </a>
					</td>
				    <td width="8" style="align:center;">
					   <a href='${ctx}/onlinelibrary/findOpById/${item.op_id}' > 
						  <img src="${resourcePath}/images/edit.gif" border="0" >
					   </a>
					</td>
					<td width="10" style="align:center;">
					    <a href='${ctx}/onlinelibrary/deleteOpById/${item.op_id}' onclick="javascript:if(!confirm('确认删除吗？')){return false};">
  					            X
					    </a>
					</td>
				</tr>
			   </c:forEach>
        </tbody>
    </table>
    </form>	
    </div>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	$("#select-file-btn").click(function(){
		$("#upfile").click();
		
	});
	function changefile(){
		$("#show-filename").text($("#upfile").val());
	}
	</script>

</body>
</html>
