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
    <li><a href="#">学习资料管理</a></li>
    <li><a href="#">资料信息</a></li>
    <li><a href="#">基本内容</a></li>
    <li><b style="color:red"> ${stuName}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="rightinfo">
    <div class="tools">
    <!--  综合查询-->  
   <div style="float:left;width:570px;height:45px;border:1px solid #F4F4F4;border-radius:10px;" >
		 <form method="POST"  id="form" action="${ctx}/dowloadList">
		 <input type="hidden" name="status" value=query />
			 <table>
			 	 <tr>
			 	 	<td><label>名称查询</label><input name="file_name" type="text" class="scinput" /></td>
			 	 	<td><label>类型查询</label>
			 	 	    <select name=data_type id="data_type" class="scinput">
							<option value="" >---请选择---</option>
							<option value="课后练习" style="sbackground-color:green;">课后练习</option>
							<option value="复习资料" style="sbackground-color:green;">复习资料</option>
							<option value="精题精炼" style="sbackground-color:green;">精题精炼</option>
							<option value="其他" style="sbackground-color:green;">其他</option>
		                 </select>
			 	 	</td>
			 	 	<td><input style="width:150px;margin-left:10px;margin-top:5px;"type="submit" class="scbtn" value="查询"  onclick="queryPage()"></td>
			 	 </tr>
			 </table>
         </form>
    </div>
    </div>
    <div>总${page.total}条数据</div><d:pagination pages="${page}" />
    <table class="tablelist">
    	<thead>
    	<tr>
        <th>资料名称</th>
        <th>资料类型</th>
        <th>对应视频</th>
        <th>上传人</th>
        <th>下载次数</th>
        <th>更新时间</th>
        <th colspan="2">操作<i class="sort"><img src="${pageContext.request.contextPath}/resources/images/px.gif" /></i></th>
        </tr>
        </thead>
        <tbody>
        <!-- 以下数据应从数据表遍历而得   -->
		<c:forEach items="${page.result}" var="item">
				<tr id="1">
				    <td width="80">${item.file_name}</td>
				    <td width="60">${item.data_type}</td>
				    <td width="80">${item.vedio_name}</td>
				    <td width="80">${item.upload_name}</td>
				    <td width="60">${item.download_count}</td>
				    <td width="60">${item.upload_time}</td>
					<td width="60">
					<a href="${ctx}/download?filename=${item.file_name}&data_id=${item.data_id}">
                                                                              下载
                    </a>
					</td>
				</tr>
			   </c:forEach>
        </tbody>
    </table>
    </div>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>
</html>
