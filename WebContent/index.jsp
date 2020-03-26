<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="cn.st.entity.*"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>猿来入此英语教研室/情景英语平台</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<link href="${resourcePath}/css/styles2.css" rel="stylesheet">
<link rel="shortcut icon" href="./favicon.ico">

 <!--  初始化页面公告信息 -->
  <!-- <script type="text/javascript">
      window.onload=function(){
	  window.location.href="${ctx}/InitIndexInf"; 
	  alert("加载ing...");  
	  return;//结束循环
	}
  </script> -->
</head>
<body>
<div class="t-line"></div>
<div class="header" style="">
  <div class="tools clearfix" style="width:100%;background-color:#5CACEE;">
      <p class="welcome">
         <img alt="" src="${resourcePath}/images/slogo.png" height="72" width="72px;"
          style="position:absolute;left:178px;top:-15px; border-radius:80px;border:1px solid white;"/>
       </p>
       <h1  style="position:absolute;left:20%;top:-15px; font-size:21pt;color:white;">猿来入此 英 语 情 景 教 学 平 台</h1>
       <h4  style="position:absolute;left:20%;top:5px; font-size:10pt;color:white;">YUAN &nbsp;LAI  &nbsp;RU CI&nbsp; YINGYU&nbsp; QINGJING &nbsp;&nbsp;JIAOXUE PINGTAI</h4>
  </div>
  <div class="nav-bg" style="margin-top:-18px;">
    <ul class="nav">
      <li style="margin-left:100px;font-size:12pt;"><a class="active" href="/OnlineLearning/InitIndexInf">学院首页</a></li>
	  <li style="margin-left:56px;font-size:12pt;"><a href="#projectIntroduction">项目介绍</a></li>
      <li style="margin-left:56px;font-size:12pt;"><a href="${ctx}/yiZhuanDetails/teachersDetails">教师风采</a></li>
      <li style="margin-left:56px;font-size:12pt;"><a href="${ctx}/yiZhuanDetails/videoAppreciation">情景教学欣赏</a></li>
     <!--  <li style="margin-left:45px;font-size:12pt;"><a href="javascript:;">在线题库</a></li> -->
      <li style="margin-left:56px;font-size:12pt;"><a href="${ctx}/yiZhuanDetails/dataDowload">下载专区</a></li>
    </ul>
  </div>
</div>
<div class="warp" style="margin-top:-17px;">
  <div class="banner" style="margin-top:-10px;">
    <div class="change-box">
      <div class="img-box ads-one active"><img alt="" src="${resourcePath}/images/banner.jpg" width="100%" /></div>
      <div class="img-box ads-two"><img alt="" src="${resourcePath}/images/banner.jpg" width="100%"/></div>
      <div class="img-box ads-three"><img alt="" src="${resourcePath}/images/banner.jpg" width="100%" /></div>
    </div>
    <ul class="changebtn clearfix">
      <li><a class="banbtn active" href="javascript:;"></a></li>
      <li><a class="banbtn" href="javascript:;"></a></li>
      <li><a class="banbtn" href="javascript:;"></a></li>
    </ul>
  </div>
  <div class="news-moudle clearfix">
    <div class="news-pic mr15" >
      <ul class="pic-box">  
        <li class="act"><img src="${resourcePath}/images/temp.jpg" width="324" height="191" /></li>
        <li><img src="${resourcePath}/images/school.jpg" width="324" height="191" /></li>
        <li><img src="${resourcePath}/images/school04.jpg" width="324" height="191" /></li>
      </ul>
      <p class="pic-box-pagenum">
        <span class="act">1</span>
        <span>2</span>
        <span>3</span>
      </p>
    </div>
    <div class="news-info mr15">
      <div class="title">公告栏</div>
      <div style="position:relative;left:278px;top:-26px;"><a href="${ctx}/yiZhuanDetails/noticeDtails">更多公告</a></div>
      <ul style="margin-top:-6px;margin-left:0px;">
        <c:forEach items="${InitNotice}" var="item"> 
        <li style="margin-top:0px;margin-left:8px;">
            <a href="${ctx}/yiZhuanDetails/findNoticeById/${item.notice_id}">${item.notice_tiltle}<img src="${resourcePath}/images/hot.gif" /><P style="position:relative;left:240px;top:-25px;">${item.create_time}</P></a>
        </li>
       </c:forEach>
      </ul>
    </div>
    <div class="news-info">
	 <div class="quick-box">
      <div class="title">快速通道</div>
	  <ul class="quick">
          <li style="float:left;border:1px solid #F4F4F4;width:120px;height:100%; margin-left:10px;">
                <a class="cc7e1f0" href="login_users.jsp">
                    <img src="${resourcePath}/images/jiaowuguanlixitong.png" style="width:56%;"/>
                </a>
          </li>
          <li style="float:left;border:1px solid #F4F4F4;width:130px;height:73px;margin-left:14px;">
          <a class="c96c4e6" href="${ctx}/yiZhuanDetails/videoAppreciation">
                             <img src="${resourcePath}/images/shipinxuexi.png" style="width:130px;height:73px;"/>
          </a>
          <li style="float:left;border:1px solid #F4F4F4;width:130px;height:73px;margin-left:14px;">
          <a class="c96c4e6" href="${ctx}/yiZhuanDetails/videoAppreciation">
                             <img src="${resourcePath}/images/timg.jpg" style="width:130px;height:73px;"/>
          </a>
        </ul>
     </div>
	 </div>
  </div>
  <div class="clearfix">
    <div class="left">
      <div class="title" >项目介绍</div>
      <div class="bbs-img">
        <img src="${resourcePath}/images/banner.jpg" width="100%" />
      </div>
      <div class="bbs">
        <a name="projectIntroduction">本课题旨在实现英语情景系统，通过该系统，减轻教务人员的工作负担，提高教学质量，提高教学资源利用率，实现测试标准化，建立教学资源库，形成特色英语考试、学习、辅导平台。
        </a>
      </div>
    </div>
    <div class="right">
      
  </div>
  <div class="show-box" style="height:280px;">
<!-- 	<h3 >热点视频</h3> -->
    <div class="box-list" style="width:68%;height:268px;">
      <ul style="width:100%;height:260px;float:left;">
         <c:forEach items="${InitVedios}" var="item"> 
         <li style="height:138px;width:220px;float:left;margin-left:2px;">
            <video width="100%" height="130px"  controls="controls"  ><!-- autoplay="autoplay" -->
		             <source src="${item.picture_path}" type="video/mp4">
            </video>
         <%--  <p>视频标题:${item.title}</P>  --%>
        </li>
         </c:forEach>
      </ul>
    </div>
  </div>
  <div class="show-box"  style="height:260px;">
    <div class="title">名师风采</div>
    <div class="box-list" id="Marquee_x" style="width:60%;height:230px;float:left;">
      <ul class="img-lists clearfix" style="width:60%;height:230px;">
        <li style="width:150px;height:230px;">
          <img alt="" src="${resourcePath}/images/lishuangmiao.jpg" width="100%" height="100%" />
          <a href="${ctx}/yiZhuanDetails/teachersDetails" class="">教师1</a>
		  <p>简介:独特的教学方法</P>
        </li>
        <li style="width:150px;height:230px;">
          <img alt="" src="${resourcePath}/images/luojing.jpg" width="100%" height="100%"/>
          <a href="${ctx}/yiZhuanDetails/teachersDetails" class="">教师2</a>
		  <p>简介:是学习让我变得快乐</P>
        </li>
        <li style="width:150px;height:230px;">
          <img alt="" src="${resourcePath}/images/huanglinqing.jpg" width="100%" height="100%"/>
          <a href="${ctx}/yiZhuanDetails/teachersDetails" class="">教师3</a>
		  <p>简介:与学生打成一片</P>
        </li>
        <li style="width:150px;height:230px;">
          <img alt="" src="${resourcePath}/images/daigenghua.jpg" width="100%" height="100%"/>
          <a href="${ctx}/yiZhuanDetails/teachersDetails" class="">教师4</a>
		  <p>简介:因才施教</P>
        </li>
        <li style="width:150px;height:230px;">
          <img alt="" src="${resourcePath}/images/liufei.jpg" width="100%" height="100%" />
          <a href="${ctx}/yiZhuanDetails/teachersDetails" class="">教师5</a>
		  <p>简介:独特的教学方法</P>
        </li>
        <li style="width:150px;height:230px;">
          <img alt="" src="${resourcePath}/images/wangqun.jpg" width="100%" height="100%"/>
          <a href="${ctx}/yiZhuanDetails/teachersDetails" class="">教师6</a>
		  <p>简介:是学习让我变得快乐</P>
        </li>
        <li style="width:150px;height:230px;">
          <img alt="" src="${resourcePath}/images/yurong.jpg" width="100%" height="100%"/>
          <a href="#" class="">教师7</a>
		  <p>简介:与学生打成一片</P>
        </li>
        <li style="width:150px;height:230px;">
          <img alt="" src="${resourcePath}/images/zhangyanlin.jpg" width="100%" height="100%"/>
          <a href="${ctx}/yiZhuanDetails/teachersDetails" class="">教师8</a>
		  <p>简介:因才施教</P>
        </li>
        <li style="width:150px;height:230px;">
          <img alt="" src="${resourcePath}/images/zhoujuanjuan.jpg" width="100%" height="100%"/>
          <a href="${ctx}/yiZhuanDetails/teachersDetails" class="">教师9</a>
		  <p>简介:与学生打成一片</P>
        </li>
        <li style="width:150px;height:230px;">
          <img alt="" src="${resourcePath}/images/zhusiming.jpg" width="100%" height="100%"/>
          <a href="${ctx}/yiZhuanDetails/teachersDetails" class="">教师10</a>
		  <p>简介:因才施教</P>
        </li>
      </ul>
    </div>
	<div class="news-info" style="width:39%;height:230px;float:left;">
      <div class="title">留言信息</div>
      <div style="position:relative;left:340px;top:-26px;"><a href="${ctx}/yiZhuanDetails/leaveMsg">更多留言</a></div>
      <ul style="margin-top:-6px;margin-left:0px;">
       <c:forEach items="${InitComments}" var="item"> 
        <li style="margin-top:0px;margin-left:8px;">
            <a href="${ctx}/yiZhuanDetails/showMsgDetails/${item.c_id}" >${item.c_title}<img src="${resourcePath}/images/hot.gif" />
           <P style="position:relative;left:263px;top:-20px;">${item.ceateTime}</P>
           </a>
        </li>
       </c:forEach>
      </ul>
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
    <p class="mt15">Copyright	© 2019  猿来入此英语教研室/情景英语平台 版权所有</p>
    
  </div>
</div>
</div>
<script type="text/javascript" src="${resourcePath}/js/jquery.min.js"></script>
<script src="${resourcePath}/js/jquery.kxbdmarquee.js"></script>
<script type="text/javascript">
$(function(){
    $('#Marquee_x').kxbdMarquee({ 'direction':'left','isEqual':'true','loop':0 });
    $('#Marquee_xx').kxbdMarquee({ 'direction':'left','isEqual':'true','loop':0 });
    $('#Marquee_xxx').kxbdMarquee({ 'direction':'left','isEqual':'true','loop':0 });
});
var changeBox = $(".change-box"),
    imgBox = $(".img-box"),
    changeBtn = $(".changebtn"),
    banBtn = $(".banbtn"),
    picBox = $('.pic-box li'),
    picBoxPagenum = $('.pic-box-pagenum span'),
    speed = 4000;
// 定时器
var timeout = setInterval(autorun,speed);
// banner 滚动逻辑
function run(index){
      imgBox.removeClass('active').eq(index).addClass('active');
      banBtn.removeClass('active').eq(index).addClass('active');
      clearInterval(timeout);
      timeout = setInterval(autorun,speed);
}
function autorun(){
  var _indexDiv = changeBox.find('.active').index(),
      _indexBtn = changeBtn.find('.active').index(),
      _lenDiv = imgBox.length,
      _lenBtn = banBtn.length,
      autoindex = _indexDiv+1;
  autoindex = autoindex > 2? 0 : autoindex;
  imgBox.removeClass('active').eq(autoindex).addClass('active');
  banBtn.removeClass('active').eq(autoindex).addClass('active');
}
// 点击切换
banBtn.each(function(){
  $(this).on("click",function(){
    var index = banBtn.index();
    run(index);
  });
});

// 新闻区域图文切换
picBoxPagenum.each(function(){
  $(this).on('click',function(){
    $(this).siblings('span').removeClass('act');
    $(this).addClass('act');
    var index = $(this).index();
    $(this).parents('.news-pic').find('.pic-box li').removeClass('act');
    $(this).parents('.news-pic').find('.pic-box li').eq(index).addClass('act');
  });
});

</script>
</body>
</html>