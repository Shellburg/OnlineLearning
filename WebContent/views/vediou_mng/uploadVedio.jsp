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

<script type="text/javascript">
window.onload=function(){
    var head = document.getElementById("headImg").value;
	var img = document.getElementById("imageid"); 
	if(head.length==0){
		 img.src="${ctx}/resources/images/love.jpg";
	}else{
		img.src=head;
		/* if(img.style.display=="none"){
	     		alert("66666");
	     		img.style.color="red";
	     } 
		 */
	    //alert(img.src);
		
	} 
}
</script>
   <!--表单提交验证 -->
    <script type="text/javascript">
			function validate()
			{
		    var describes = document.getElementById("describes").value;
			var title = document.getElementById("title").value;
			var headImg = document.getElementById("headImg").value;
			var type_id = document.getElementById("type_id").value;
		    if(headImg=="")
			{
			alert('视频未上传!!!');
			return false;
			}
			else if(title=="")
			{    
				 alert('请填写视频标题!!!');
			     return false;
			}
			else if(describes=="")
			{    
				 alert('请填写视频描述!!!');
			     return false;
			}
			else if(type_id=="")
			{
			alert('请选择视频类型!!!');
			return false;
			}
			else if(describes!=""&&headImg !=""&&title!=""&&type_id!="")
			{
			alert('视频上传成功!!!');
			return true;
			}
			return true;
			}
</script>
  <script type="text/javascript">
    	     //JS校验form表单信息
    	     function checkData(){
    	     	if("" == fileDir){
    	     		alert("选择需要上传的视频！");
    	     		return false;
    	     	}
    	     	return true;
    	     }
    	 
    	    
    </script>
  <!--   视频上传的样式 -->
  <style type="text/css">
   #vedioUpload{
      width:340px;
      height:280px;
      border:2px solid #F0F0F0;
      border-radius:10px;
      position:absolute;
      left:660px;
      top:100px;
  }
  
  </style>
</head>
    
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">视频管理</a></li>
    <li><a href="#">上传视频</a></li>
    <li><b style="color:red">${name}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="formbody">
    <div id="usual1" class="usual"> 
    <div class="itab">
  	<ul> 
    <li><a href="#tab1" class="selected">视频上传</a></li> 
  	</ul>
    </div> 
  	<div id="tab1" class="tabson">
  	<form  id="form" action="${ctx}/uploadVedio?" method="post" onsubmit="return validate()" >
    <ul class="forminfo">
    <li><label>视频标题<b>*</b></label>
         <input  type="text" class="dfinput"  name="title" id ="title"  style="width:518px;"/>
    </li>
    <li><label>视频内容<b>*</b></label>
        <textarea name="describes" id="describes"  class="dfinput" style="width:518px;height:140px;" ></textarea>
    </li>
     <li><label>视频类型<b>*</b></label>
           <select name="type_id" id="type_id" class="dfinput" >
						<option value="" >---请选择---</option>
						<c:forEach items="${vedioTypes}" var="item">
							<option value="${item.type_id}" ${item.type_id eq type_id ? "selected='selected'":""} >${item.type_name}</option>	
						</c:forEach>
			 </select>
    </li>
    <li style="margin-top:30px;"><label>&nbsp;</label><input type="submit" class="btn" value="提交" /></li>
    </ul>
    </form>
      <div id="vedioUpload">
           <!-- 提交整个表单 (先提交图片)之后才调价照片名称及照片描述-->
	       <form action="${ctx}/uploadVedio?type=uploadimg" method="post" enctype="multipart/form-data">
	         <input type="file" name="file" id="upfile" />
	          <video width="340" height="280px" >
				             <source src="${headImg}" type="video/mp4">
               </video>
                <%--  <img  src="${headImg}" width="180" height="230" id="imageid" style="position:absolute;left:5px;top:40px;border:0px;">    --%>
                <input type="hidden" id="headImg" value="${headImg}">
		     <input type="submit" value="上传视频" onclick="return checkData()"
		     style="position:absolute;left:190px;top:5px;" class="btn"  >
	       </form>
      </div>
    </div> 
	</div> 
    </div>
</body>
</html>