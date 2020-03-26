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
    <!-- 视频样式 -->
<style type=text/css>
    #vedio_list{
      width:100%;
      height:100%;
      border:1px solid white;
      
    }
    #vedioBox{
      width:340px;
      height:320px;
      border:2px solid #F0F0F0;
      border-radius:10px;
      margin-left:23px;
    }
    .linkA{
      width:45px;
      height:26px;
      border:2px solid #0065FF;
      border-radius:2px;
      background-color: #0065FF;
      color:white;
      margin-bottom:-10px;
    }
</style>
</head>


<body>
    <form  action="${ctx}/vediou_mng" method="post" id="form">
    <input type="hidden" name="status" value=query />
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">视频管理</a></li>
    <li><a href="#">视频信息</a></li>
    <li><a href="#">基本内容</a></li>
    <li><b style="color:red"> ${name}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="rightinfo">
    <div class="tools">
    	<ul class="toolbar">
	         <li ><span><a href="${ctx}/vediou_mng/uploadVedio"> <img src="${pageContext.request.contextPath}/resources/images/t01.png" /></a></span>视频上传</li>
        </ul>
        
    <!--  综合查询-->  
    <ul class="seachform">
	     <li><label>视频标题</label><input name="title" type="text"  class="scinput" value="${title}"/></li>
	    <li><label>视频类型查询</label>
	    
	    <select name="type_id" id="type_id" class="scinput">
						<option value="" >---请选择---</option>
						<c:forEach items="${vedioTypes}" var="item">
							<option value="${item.type_id}" ${item.type_id eq type_id ? "selected='selected'":""} >${item.type_name}</option>	
						</c:forEach>
		</select>
	    <input type=submit class="scbtn" value="查询" onclick="queryPage()"/></li>
    </ul>
    </div>
    <div>总${page.total}条数据</div><d:pagination pages="${page}" />
<!--视频列表-->
 <c:forEach items="${page.result}" var="item">
<div id="vedio_list">
	<ul >
		<li style="float:left;">
		<div id="vedioBox" style="float:left;width:100%;height:100%;border:1px solid #F4F4F4;border-radius:10px;">
	             <video width="350" height="200"  controls="controls" >
		             <source src="${item.picture_path}" type="video/mp4">
                  </video>   
		     <p style="font:宋体;">描述:${item.describes}</p>
		     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='${ctx}/onlinelibrary/list2/${item.up_id}' class="linkA">我要做题</a>
		    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='${ctx}/vediou_mng/play/${item.up_id}'  target="_blank"  class="linkA"> 我要观看 </a> 
		     &nbsp;&nbsp;&nbsp;&nbsp;<%-- <a href='${ctx}/vediou_mng/update/${item.up_id}' class="linkA"> 
					 修改视频
		     </a> --%>
		    &nbsp;&nbsp;&nbsp;&nbsp;<a  class="linkA"
				   href='${ctx}/vediou_mng/deleteVedio/${item.up_id}'
				   onclick="javascript:if(!confirm('确认删除吗？'))
	               {return false};" > 删除视频
		     </a>
		</div>
      </li>
    </ul>
</div>
</c:forEach>
</div>
</form>	
</body>
</html>
