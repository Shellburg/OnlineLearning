<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>视频播放</title>
<style type="text/css">
   #box{
   margin:0 auto;
   width:450px;
   height:auto;
   border-radius:20px;
   //box-shadow:1px 1px 1px 1px;
   }
</style>
   
</head>
<body>
      <div id="box" >
       <h1 style="color:red;">${watchRecord}</h1>
        <c:forEach items="${vedioUploads}" var="item">
          <b>标题:</b>${item.title}<br>
               <video width="450px" height="300px"  controls="controls">
		             <source src="${item.picture_path}" type="video/mp4">
                </video>   
        </c:forEach>
      </div>
</body>
</html>