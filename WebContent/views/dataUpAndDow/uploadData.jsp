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

<!-- 引入时间的css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/calendar.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/select-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/editor/kindeditor.js"></script>
<script language="javascript" src="${resourcePath}/script/main.js"></script>
<script language="javascript" src="${resourcePath}/js/jquery-2.1.4.min.js"></script>
<script language="javascript" src="${resourcePath}/js/jquery.form.js"></script>

   <!--表单提交验证 -->
    <script type="text/javascript">
			function validate()
			{
		    var upfile = document.getElementById("upfile").value;
			var data_type = document.getElementById("data_type").value;
			var vedio_name = document.getElementById("vedio_name").value;
			
			 if(data_type=="")
			{    
				 alert('请选择资料类型!!!');
			     return false;
			}
			else if(vedio_name=="")
			{    
				 alert('请选择对应的视频!!!');
			     return false;
			}else if(upfile=="")
			{
				alert('资料未上传!!!');
				return false;
			}
			else if(upfile!=""&&data_type !=""&&vedio_name!="")
			{
				alert('资料上传成功!!!');
				return true;
			}
			    return true;
			}
</script>
  <!--   视频上传的样式 -->
  <style type="text/css">
  #tab1{
      width:680px;
      height:280px;
      border:2px solid #F0F0F0;
      border-radius:10px;
      position:absolute;
      left:280px;
      top:100px;
  }
  </style>
</head>
    
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">资料管理</a></li>
    <li><a href="#">上传资料</a></li>
    <li><b style="color:red">${name}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">资料上传</a></li> 
  	</ul>
    </div> 
  	<div id="tab1" class="tabson">
  	<form  id="form" action="${ctx}/fileUpload" enctype="multipart/form-data" method="post" onsubmit="return validate()" >
    <ul class="forminfo">
    <li style="margin-left:20px;"><label>资料类型<b>*</b></label>
        <select name=data_type id="data_type" class="dfinput" >
							<option value="" >---请选择---</option>
							<option value="课后练习" style="sbackground-color:green;">课后练习</option>
							<option value="复习资料" style="sbackground-color:green;">复习资料</option>
							<option value="精题精炼" style="sbackground-color:green;">精题精炼</option>
							<option value="其他" style="sbackground-color:green;">其他</option>
		</select>
    </li>
     <li style="margin-left:20px;"><label>对应视频<b>*</b></label>
           <select name="vedio_name" id="vedio_name" class="dfinput" >
						<option value="" >---请选择---</option>
						<c:forEach items="${vedioUploads}" var="item">
							<%-- <option value="${item.title}" ${item.up_id eq up_id ? "selected='selected'":""} >${item.title}</option>	 --%>
							<option value="${item.title}">${item.title}</option>	
						</c:forEach>
		   </select>
    </li>
    <li style="margin-left:20px;"><label>资料上传<b>*</b></label>
         <input type="file" class="dfinput"  name="upFile" id="upfile" style="margin-top:10px;"/>
    </li>
    <li style="position:absolute;left:80px;top:180px;"><label>&nbsp;</label><input type="submit" class="btn" value="提交" /></li>
    <li style="position:absolute;left:300px;top:180px;"><label>&nbsp;</label><input type="button" onClick="history.go(-1);" class="btn" value="返回" /></li>
    </ul> 
    </form>
    </div> 
	</div> 
    </div>
</body>
</html>