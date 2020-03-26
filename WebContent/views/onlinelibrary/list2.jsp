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
   <!--  选择题提交验证  -->
   <script type="text/javascript">
     function checkOptions() {
    	 var option0= $('input:radio[name="option0"]:checked').val();
    	 var option1= $('input:radio[name="option1"]:checked').val();
    	 var option2= $('input:radio[name="option2"]:checked').val();
    	 var option3= $('input:radio[name="option3"]:checked').val();
    	 var option4= $('input:radio[name="option4"]:checked').val();
    	 if(option0!=null&&option1!=null&&option2!=null&&option3!=null&&option4!=null){
    		 alert("恭喜你完成答题！！！");
			 return true;
         }else{
        	 alert("您没有选择完请继续选择！！！");
        	 return false;
         }
     }       
   </script>
   
  <!--  显示解析答案 -->
   <script type="text/javascript">
   $(document).ready(function(){
       //写jquery代码
       $("#lookAnswer").click(function(){
    	   $(".lookParse").css('display','block');
       });
   });
    </script>
   <!--css样式 -->
    <style type="text/css">
      .lookParse{
              display: none;
      }
     /* 悬浮窗样式 */
     #floatWindow{
     width: 200px;
     height: 320px;
     position: fixed;
     right: 38px;
     top: 90px;
     border-radius:20px;
     border: 1px solid blue;
    }
   #floatWindow img{
       width: 200px;
       height: 260px;
       border-radius:20px;
   }
    </style>

</head>


<body>
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">题库管理</a></li>
    <li><a href="#">习题检测</a></li>
    <li><a href="#">基本内容</a></li>
    <li><b style="color:red">${stuName}</b>，欢迎您登录</li>
    </ul>
    </div>
    <div class="rightinfo">
    <form  action="${ctx}/onlinelibrary/testRecord" method="post" onsubmit="return checkOptions()" id="form" >
    <p>选择题部分</p><br>
	<c:if test="${options == null || options.size() == 0}">
	该视频没有习题
    </c:if> 
    <c:if test="${options != null || options.size()!= 0}">
    <c:forEach items="${options}" var="item" varStatus="i">
    <table class="tablelist">
    	<thead>
    	<tr>
        <th colspan="4">${item.question}</th>
        </tr>
        </thead>
        <tbody>
        <!-- 以下数据应从数据表遍历而得 -->
				<tr id="1">
					<td >
					<input type="hidden" name="op_answer" id="op_answer"  value="${item.op_answer}" />
					<input type="hidden" name="up_id" id="up_id"  value="${item.up_id}" />
					<input name="option${i.index}"   type="radio" value="A" />
					${item.op_a}
					</td>
					<td >
					<input name="option${i.index}"   type="radio" value="B" /> 
					${item.op_b}
					</td>
					<td >
					<input name="option${i.index}"   type="radio" value="C" />
					 ${item.op_c}
					</td>
					<td >
					<input name="option${i.index}"    type="radio" value="D" />
					${item.op_d}
					</td>
				</tr>
				<tr>
					<td class="lookParse">答案选项：${item.op_answer}</td>
					<td class="lookParse">解析:${item.parse}</td>
				</tr>
        </tbody>
    </table>
    </c:forEach>
    </c:if> 
    <div style="margin-left: 30px; margin-right: 0px">
			<table border="0" cellpadding="0" cellspacing="0" width="95%">
				<tr>
					<td width="10%">
						<input type="submit" value="提交" class="scbtn" />
					</td>
					<td width="10%">
					    <input type="button" class="scbtn" value="返回" onClick="history.go(-1);">
					</td>
				</tr>
			</table>
		</div>
		<div style="margin-top: 100px;border:1px solid gray;">
		    <input type="button" id="lookAnswer"  class="scbtn" value="查看答案"  />
		 </div>
    </form>	
    </div>
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>
</html>
