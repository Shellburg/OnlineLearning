<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="cn.st.entity.*"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib uri="http://gx.edu/dTags" prefix="d"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>猿来入此英语教研室/情景英语平台</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<link href="${resourcePath}/css/styles2.css" rel="stylesheet" type="text/css">
<%-- <link href="${resourcePath}/css/style.css" rel="stylesheet" type="text/css" /> --%>
<link rel="shortcut icon" href="./favicon.ico">

<style type="text/css">
  #data:hover td{
    background-color:#AEEEEE;
  }


</style>
</head>
<body>
<div class="t-line"></div>
<div class="header" style="">
  <div class="tools clearfix" style="width:100%;background-color:#5CACEE;">
      <p class="welcome">
         <img alt="" src="${resourcePath}/images/slogo.png" height="72" width="72px;"
          style="position:absolute;left:178px;top:-15px; border-radius:80px;border:1px solid white;"/>
       </p>
       <h1  style="position:absolute;left:20%;top:-15px; font-size:21pt;color:white;">猿 来 入 此 英 语 情 景 教 学 平 台</h1>
       <h4  style="position:absolute;left:20%;top:5px; font-size:10pt;color:white;">QIAN &nbsp;NAN  &nbsp;YI ZHUAN&nbsp; YINGYU&nbsp; QINGJING &nbsp;&nbsp;JIAOXUE PINGTAI</h4>
  </div>
  <div class="nav-bg">
    <ul class="nav">
      <li style="margin-left:100px;font-size:12pt;"><a  href="${ctx}/InitIndexInf">学院首页</a></li>
	  <li style="margin-left:56px;font-size:12pt;"><a href="${ctx}/yiZhuanDetails/projectIntroduction">项目介绍</a></li>
      <li style="margin-left:56px;font-size:12pt;"><a  href="${ctx}/yiZhuanDetails/teachersDetails">教师风采</a></li>
      <li style="margin-left:56px;font-size:12pt;"><a href="${ctx}/yiZhuanDetails/videoAppreciation">情景教学欣赏</a></li>
     <!--  <li style="margin-left:30px;font-size:12pt;"><a href="javascript:;">在线题库</a></li> -->
      <li style="margin-left:56px;font-size:12pt;"><a class="active" href="${ctx}/yiZhuanDetails/dataDowload">下载专区</a></li>
    </ul>
  </div>
</div>
<div class="warp">
  <div class="ads">
    <img alt="" src="${resourcePath}/images/banner.jpg" width="100%"/>
  </div>
  <div class="listpage clearfix">
    <div class="lmenu">
      <h2>分类导航</h2>
      <ul class="menus">
        <li><a class="menulink active" href="#">资料下载</a></li>
       <!--  <li><a class="menulink" href="#">信息资源</a></li>
        <li><a class="menulink" href="#">科技查新</a></li>
        <li><a class="menulink" href="#">查收查引</a></li>
        <li><a class="menulink" href="#">查新站简介</a></li>
        <li><a class="menulink" href="#">委托须知</a></li>
        <li><a class="menulink" href="#">业务流程</a></li>
        <li><a class="menulink" href="#">收费标准</a></li>
        <li><a class="menulink" href="#">规章制度</a></li>
        <li><a class="menulink" href="#">常见问题</a></li>
        <li><a class="menulink" href="#">信息资源</a></li> -->
      </ul>
    </div>
    <div class="rcon">
      <ol class="breadcrumb">
        <li><a href="#">首页</a></li>
        <li class="active"> - 资料 - 资料内容</li>
      </ol>
      <div class="newscon">
   <div class="tools" style="width:100%;">
    <!--  综合查询-->  
   <div style="float:left;width:570px;height:45px;border:1px solid #F4F4F4;border-radius:10px;" >
		 <form method="POST"  id="form" action="${ctx}/yiZhuanDetails/dataDowload">
		 <input type="hidden" name="status" value=query />
			 <table>
			 	 <tr>
			 	 	<td><label>名称查询</label><input name="file_name" type="text" style="border:1px solid #4876FF;border-radius:0px;width:120px;height:25px;background-color:#F4F4F4;"/></td>
			 	 	<td><label>类型查询</label>
			 	 	    <select name=data_type id="data_type" style="width:120px;border:1px solid gray;border-radius:10px;">
							<option value="" >---请选择---</option>
							<option value="课后练习" style="sbackground-color:green;">课后练习</option>
							<option value="复习资料" style="sbackground-color:green;">复习资料</option>
							<option value="精题精炼" style="sbackground-color:green;">精题精炼</option>
							<option value="其他" style="sbackground-color:green;">其他</option>
		                 </select>
			 	 	</td>
			 	 	<td>
			 	 	<input  style="width:60px;height:25px;border:1px solid #4876FF;background-color:#1E90FF;color:white;" type="submit"
			 	 	 value="查询"  onclick="queryPage()"></td>
			 	 </tr>
			 </table>
         </form>
    </div>
    </div><br>
    <div style="width:60%;">总${page.total}条数据</div><d:pagination pages="${page}" /><br>
    <table 
    style="width:100%;height:100%;border:1px solid #C6E2FF;border-radius:2px;
     cellspacing:0px;cellpadding:0px;border-collapse:collapse;">
    	<thead>
    	<tr style="background-color:#AEEEEE;">
        <th>资料名称</th>
        <th>资料类型</th>
        <th>对应视频</th>
        <th>上传人</th>
        <th>下载次数</th>
        <th>更新时间</th>
        <th colspan="2">操作<i class="sort"><img src="${pageContext.request.contextPath}/resources/images/px.gif" /></i></th>
        </tr>
        </thead>
        <tbody style="width:100%;height:100%;border:1px solid #F4F4F4;border-radius:0px;">
        <!-- 以下数据应从数据表遍历而得   -->
		<c:forEach items="${page.result}" var="item">
				<tr id="data" style="background-color:#F0FFF0;text-align:center;">
				    <td width="100">${item.file_name}</td>
				    <td width="60">${item.data_type}</td>
				    <td width="70">${item.vedio_name}</td>
				    <td width="80">${item.upload_name}</td>
				    <td width="60">${item.download_count}</td>
				    <td width="78">${item.upload_time}</td>
					<td width="70">
					<a href="${ctx}/download?filename=${item.file_name}&data_id=${item.data_id}">
                        <img src="${resourcePath}/images/down.gif" alt="点击下载" />
                    </a>
					</td>
				</tr>
			   </c:forEach>
        </tbody>
    </table>
    </div>
  </div>
</div>
<div class="footer-bg">
  <div class="footer wc1000">
    <ul class="flink">
      <li>
        <a class="tit" href="javascript:;">友情链接: </a>
      </li>
      <li>
        <a href="#">友情链接</a>
      </li>
      <li>
        <a href="#">友情链接</a>
      </li>
      <li>
        <a href="#">友情链接</a>
      </li>
      <li>
        <a href="#">友情链接</a>
      </li>
      <li>
        <a href="#">友情链接</a>
      </li>
      <li>
        <a href="#">友情链接</a>
      </li>
      <li>
        <a href="#">友情链接</a>
      </li>
      <li>
        <a href="#">友情链接</a>
      </li>
       <li>
        <a href="/OnlineLearning/login_users.jsp">我要登录</a>
      </li>
    </ul>
    <p class="mt15">Copyright	© 2019【猿来入此】</p>
  </div>
</div>
</div>
<script type="text/javascript" src="${resourcePath}/js/jquery.min.js"></script>
<script type="text/javascript">
</script>
</body>
</html>