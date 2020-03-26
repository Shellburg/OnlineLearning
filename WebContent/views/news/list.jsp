<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@taglib uri="http://gx.edu/dTags" prefix="d"%>
<html>
<head>
<style type="text/css">
a{
   margin:0 auto;
   text-decoration:none;
   text-align:center;
}
a:hover{
   color:red;
   margin:0 auto;
   text-decoration:underline;
}
body{
     background-color:white;
     color:#333333;
     margin:0px auto;
	 display:block;
	 padding:0px;
	 cellspacing:0px ;cellpadding:0px;
}
table{
    padding:0px;
  cellspacing:0px ;cellpadding:0px;
}
#webwaikuang{
  width:100%; height:100%;
  margin:0 auto;
  cellspacing:0px ;cellpadding:0px;
  padding:0px;
  
}
  /*中间内容*/
#centercontent{
   height:230px; width:90%;
   clear:both;
   align:center;
   margin:0 auto;
   list-style:none;
   list-style-position:inside;
   word-break:keep-all;
}

#centercontentleft{
   height:230px; 
   float:left;
	width:25%;
	align:center;
}
#centercontentcenter{
   height:230px;  
   float:left;
   width:50%;
   list-style:none;
   list-style-position:inside;
   word-break:keep-all;
}
#centercontentcenter table{
     background-image:url(Images/gg_02.gif);
	 background-repeat:no-repeat;
	 background-size:100% 100%;
	font-family:"楷体";
    font-size:20px;
	height:25px; 
	width:100%;
}
#centercontentright{
   height:230px; 
   float:left;
	width:25%;
}
/*中间内容*/

/*底部内容*/
#bottomcontent{
   height:280px; width:90%;
   align:center;
   margin:0 auto;
   border:1px solid #9D9D9D;
}
#bottomcontentleft{
   height:280px;
   float:left;
   width:25%;
}
#bottomcontentcenter{
   height:280px; 
   float:left;
   width:50%;
  
}
#bottomcontentcenter ul{ 
   float:left;
   width:25%;
   list-style:none;
   list-style-position:inside;
   word-break:keep-all;
}
#bottomcontentcenter ul li{
 float:left;
 background:url("Images/ico.gif") 
 no-repeat left center; 
 font-size:10px; 
 height:25px; 
 text-indent:18px;
 line-height:18px; 
}
#bottomcontentright{
   height:280px;
   float:left;
   width:25%;
}
#bottomcontentright ul{ 
   float:left;
   width:25%;
   list-style:none;
   list-style-position:inside;
   word-break:keep-all;
}
#bottomcontentright ul li{
 float:left;
background:url("Images/ico.gif") no-repeat left center; font-size:10px; 

height:25px; text-indent:18px; line-height:18px; 
}
  
/*底部内容*/

/*下拉框部分*/
#selecttable{
   clear:both;
   width:90%;height:35px;
   margin:0px auto;
   align:center;
}
/*下拉框部分*/

/*版权内容*/
  #footercontent{
     align:center;
     margin:0 auto;
     width:90%;height:50px;
	 clear:both;
	 font-family:"楷体";
     font-size:20px;
    text-align:center;
	border:1px solid #9D9D9D;
  }
 /*版权内容*/
 
 /*图片切换*/
      *{
       margin:0px;/*外边距*/padding:0px;/*内边距*/}
       #box{width:25%;height:185px;
	   margin:0px 20px;
	   background-position:50px 20px;
       border:0px solid #cc99ff;/*宽度 风格solid(实线) 颜色*/padding:0px;}
       #box .list_item{width:240px;height:185px;background:#ffffcc;
	   position:relative;/*相对定位参考对象*/
	   
	  }
       #box .list_item ul li
	   {
       list-style:none;/*去小圆点*/
	   width:240px;
	   height:185px;position:absolute;top:0px;left:0px;/*绝对定位*/;
       display:none;
	   }/*隐藏所有*/
       #box .list_item ul li p
	   {
	   font-size:12px;color:#fff;/*文字颜色*/
       font-family:"微软雅黑";width:100%;height:30px;
	   background:rgba(0,0,0,0.6);/*设置背景透明度*/
       text-align:center;/*设置对齐方式*/line-height:30px;/*行高*/
       position:absolute;/*绝对定位*/bottom:0px;left:0px;}
       #box .list_item ul li.first
	   {
	    display:block  /*显示块级元素*/
	   }
       #box .list_item a
	   {
	   width:40px;height:41px;
	   background-image:url("images/add_icon.png");
       display:block;position:absolute;top:150px;
	   }
	   
       #box .list_item a.prev
	   {background-position:-49px -151px;
	   left:10px;
	   }/*背景定位*//*左按钮*/
       #box .list_item a.next
	   {
	   background-position:-89px -151px;
	   right:10px;}/*右按钮*/
       #box .curr_item
	   {width:240px;height:185px;margin-top:20px;}/*设置上边距*/
       #box .curr_item ul li{width:240px;height:185px;list-style:none;float:left;
       margin-right:11px;position:relative;cursor:pointer;/*手指形状的指针*//*相

对定位 参考对象*/}
       #box .curr_item ul li.last{margin-right:0px;}
       #box .curr_item ul li span.hover{width:240px;height:185px;border:0px solid 

#59a18b;
       display:block; position:absolute;top:0px;left:0px;display:none;}

       #box .curr_item ul li i.tran{width:0px;height:0px;display:none;
       border-left:5px solid transparent;
       border-right:5px solid transparent;
       border-bottom:0px solid #59a18b;
       position:absolute;top:-5px;left:20%;margin-left:-5px;}
       
       #box .curr_item ul li.curr span.hover,#box .curr_item ul li.curr i.tran

{display:block;}
      .imagesize img{width:100%;height:100%;}
   /*图片切换*/
   /*日历样式*/
   .content{
   border:1px solid #ddd;
   width:250px;height:230px;
   margin:0px 0px;
   }
.datetable{border-top:1px solid #ccc;border-left:0px solid #ccc;background:#fff;}
.datetable td{border-bottom:1px solid #ccc;border-right:1px solid #ccc;text-

align:center;}
.datetable thead{background:#006600;}
.datetable thead td{padding:6px 5px;font:normal 12px/normal 'microsoft 

yahei';color:#fff;text-align:center;}
.datetable thead td span{padding:0 0px;}
.datetable tbody td{padding:0px 0px;font-size:8px;
}
    /*日历样式*/
</style>
  <!--引入日历的js*/-->
  <script language="javascript" src="${resourcePath}/js/lanrenzhijia.js"></script>
  <!--引入日历的js-->
  <title>欢迎进入教务处管理系统</title>
</head>

<body onload="initial();">
<div id="webwaikuang"  >
<div id="centercontent" > 
  <div  id="centercontentleft" >
      <table >
	     <tr>
		    <td >
	    <div id="box">
        <div class="list_item">
           <ul class="imagesize">
               <li class="first">
               <img src="${ctx}/resources/image/shiyuan01.jpg" border="0">
                     <p>黔南师院(1/16)</p>
               </li>
               <li>
                     <img src="${ctx}/resources/image/shiyuan02.jpg" border="0">
                     <p>黔南师院(2/16)</p>
               </li>
               <li>
                     <img src="${ctx}/resources/image/shiyuan03.jpg" border="0">
                     <p>黔南师院(3/16)</p>
               </li>
               <li>
                     <img src="${ctx}/resources/image/shiyuan04.jpg" border="0">
                     <p>黔南师院(4/16)</p>
               </li>
			   <li>
                     <img src="${ctx}/resources/image/shiyuan05.jpg" border="0">
                     <p>黔南师院(5/16)</p>
               </li>
			   <li>
                     <img src="${ctx}/resources/image/shiyuan06.jpg" border="0">
                     <p>黔南师院(6/16)</p>
               </li>
			   <li>
                     <img src="${ctx}/resources/image/shiyuan07.jpg" border="0">
                     <p>黔南师院(7/16)</p>
               </li>
			   <li>
                     <img src="${ctx}/resources/image/shiyuan08.jpg" border="0">
                     <p>黔南师院(8/16)</p>
               </li>
			   <li>
                     <img src="${ctx}/resources/image/shiyuan09.jpg" border="0">
                     <p>黔南师院(9/16)</p>
               </li>
			   <li>
                    <img src="${ctx}/resources/image/shiyuan10.jpg" border="0">
                     <p>黔南师院(10/16)</p>
               </li>
			   <li>
                     <img src="${ctx}/resources/image/shiyuan11.jpg" border="0">
                     <p>黔南师院(11/16)</p>
               </li>
			   <li>
                     <img src="${ctx}/resources/image/shiyuan12.jpg" border="0">
                     <p>黔南师院(12/16)</p>
               </li>
			   <li>
                     <img src="${ctx}/resources/image/shiyuan13.jpg" border="0">
                     <p>黔南师院(13/16)</p>
               </li>
			   <li>
                     <img src="${ctx}/resources/image/shiyuan14.jpg" border="0">
                     <p>黔南师院(14/16)</p>
               </li>
			   <li>
                     <img src="${ctx}/resources/image/shiyuan15.jpg" border="0">
                     <p>黔南师院(15/16)</p>
               </li>
			   <li>
                     <img src="${ctx}/resources/image/shiyuan16.jpg" border="0">
                     <p>黔南师院(16/16)</p>
               </li>		   
           </ul>
           <a href="javascript:void(0)" class="prev"></a>
           <a href="javascript:void(0)" class="next"></a>
        </div>
    </div>
			</td>
		 </tr>
	  </table>
  </div> 
  <div  id="centercontentcenter" >
      <table style="cellspacing:0px ;cellpadding:0px; padding:0px ">
	     <tr >
		    <td width="5px">
			   <img src="${ctx}/resources/image/sanjiao.png" border="0">
			</td>
		    <td  width="80%" align="left" style="color:blue;">教学动态</td>
			<td  width="60px" align="right">
			   <a href="#" target=_blank >
			       <img src="${ctx}/resources/image/more.png" border="0">
			   </a>
			</td>
		 </tr>
	  </table>
	     <marquee direction=up scrollamount=3 onmouseover=this.stop() onmouseout=this.start() align="left">
				   <ul >
				      <li ><a href="#">2016-2017学年第二学期新学期开学教学检查 </a></li>
					  <li><a href="#">数学系组织新学期教学检查 </a></li>
					  <li><a href="#">数学系举办首届学科教学·数学教育硕士学位论文答辩会</a></li>
					  <li><a href="#">数学系举行2015届教育硕士专业学位论文预答辩会</a></li>
					  <li><a href="#">我院学子在2015年美国大学生数学建模竞赛中获国际二等奖</a></li>
					  <li><a href="#">学院召开2014年全国大学生数学建模竞赛数学竞赛工作总结暨颁奖会 </a></li>
					  <li><a href="#">数学系在全国大学生数学建模竞赛再创佳绩 </a></li>
					  <li><a href="#">全国大学生数学竞赛贵州赛区组委会领导到我院巡视和指导竞赛情况</a></li>
					  <li><a href="#">数学系举行2013级教育硕士研究生开题报告会</a></li>
				   </ul>
	   </marquee >
  </div> 
  <div  id="centercontentright" >
     <table>
	   <tr>
	     <td align="center" width="180px">
		    <a href="http://www.sgmtu.edu.cn/" target=_blank>
			   <img src="${ctx}/resources/image/jiaowuguanlixitong.png" border="0">
			</a>
		 </td>
		  <td align="center" width="180px">
		    <a href="http://www.sgmtu.edu.cn/" target=_blank>
			   <img src="${ctx}/resources/image/gongwenbaoxitong.png" border="0">
			</a>
		 </td>
	   </tr>
		 <tr>
		  <td align="center" width="180px">
		    <a href="http://www.sgmtu.edu.cn/" target=_blank>
			   <img src="${ctx}/resources/image/shipinxuexi.png" border="0">
			</a>
		  </td>
		 
		  <td align="center" width="180px">
		    <a href="http://www.sgmtu.edu.cn/" target=_blank>
			   <img src="${ctx}/resources/image/xuexingwangchaxue.png" border="0">
			</a>
		  </td>
		</tr>
	 </table>
  </div>
</div>
</center>

<div id="bottomcontent" > 
  <div  id="bottomcontentleft" >
     <form name="CLD" class="content">
<table border="0" cellpadding="0" cellspacing="0" class="datetable">
    <thead>
    <tr>
      <td colSpan=7><span>公历</span>
        <select name="SY" onchange="changeCld();" style="font-SIZE: 9pt">
        <script>
            for(i=1900;i<2050;i++) document.write('<option>'+i);
        </script>
         </select><span>年</span>
         <select name="SM" onchange="changeCld();" style="font-SIZE: 9pt">
        <script>
            for(i=1;i<13;i++) document.write('<option>'+i);
        </script>
        </select><span>月</span>
        </font><span id="GZ"></span>
      </td>
    </tr>
    </thead>
    <tbody>
    <tr style="background:#eee;">
      <td width="54">日</td>
      <td width="54">一</td>
      <td width="54">二</td>
      <td width="54">三</td>
      <td width="54">四</td>
      <td width="54">五</td>
      <td width="54">六</td>
    </tr>            
    <script>
    var gNum;
    for(i=0;i<6;i++) {
       document.write('<tr align="center">');
       for(j=0;j<7;j++) {
          gNum = i*7+j;
          document.write('<td id="GD' + gNum +'"><font id="SD' + gNum +'" size=2 face="Arial Black"');
          if(j == 0) document.write('color="red"');
          if(j == 6) document.write('color="#000080"');
          document.write('></font><br/><font id="LD' + gNum + '" size=2 style="font-size:9pt"></font></td>');
       }
       document.write('</tr>');
    }
   </script>
   </tbody>
</table>
</form>
  </div> 
  <div  id="bottomcontentcenter" >
     <table >
	     <tr >
		    <td width="5px">
			  <img src="${ctx}/resources/image/sanjiao.png" border="0">
			</td>
		    <td  width="80%" align="left" style="color:blue;">通知公告</td>
			<td  width="60px" align="right">
			   <a href="http://www.sgmtu.edu.cn/" target=_blank >
			       <img src="${ctx}/resources/image/more.png" border="0">
			   </a>
			</td>
		 </tr>
	  </table>
              <ul >
			          <li ><a href="views/news/new1.jsp">我校召开图书馆智能化系统施工前供需双方沟通协调会</a></li>
					  <li><a href="http://www.sgmtu.edu.cn/">关于开展2017多媒体课件制作</a></li>
					  <li><a href="http://www.sgmtu.edu.cn/">计算机等级考试报名通知</a></li>
					  <li><a href="http://www.sgmtu.edu.cn/">关于2010届应用英语</a></li>
					  <li><a href="http://www.sgmtu.edu.cn/">2015-2016期末考试</a></li>
					  <li><a href="http://www.sgmtu.edu.cn/">关于2016下半年计算机软件培训</a></li>
					  <li><a href="http://www.sgmtu.edu.cn/">关于计算机信息学院院标设计</a></li>
					  <li><a href="http://www.sgmtu.edu.cn/">黔南民族师院</a></li>
	        </ul>			
  </div> 
  <div  id="bottomcontentright" >
     <table >
	     <tr align="left" height="35px">
		    <td align="left">
			  <img src="${ctx}/resources/image/sanjiao.png" border="0">
			</td>
		    <td style="color:blue;" width="80%">本站最新信息</td>
			<td >
			   <a href="#" target=_blank align="right">
			       <img src="${ctx}/resources/image/more.png" border="0">
			   </a>
			</td>
		 </tr>
	  </table>
				 <ul >
				      <li><a href="http://www.sgmtu.edu.cn/info/1010/3411.htm">2017博士招聘</a></li>
					  <li><a href="http://math.sgmtu.edu.cn/info/1073/1006.htm">数统学院2013届毕业公示</a></li>
					  <li><a href="http://math.sgmtu.edu.cn/info/1072/1301.htm">2016年硕士专业学位研究生招生简章</a></li>
					  <li><a href="http://math.sgmtu.edu.cn/info/1072/1114.htm">关于2013年招生计划</a></li>
					  <li><a href="http://math.sgmtu.edu.cn/info/1072/1005.htm">数学系2013年招生计划</a></li>
					  <li><a href="http://math.sgmtu.edu.cn/">更多...</a></li>
				</ul>
  </div>
</div>
<div id="selecttable">
    <table width="100%" align="center">
	  <tr>
	     <td style="color:blue;">考试查询:</td>
		 <td>
		    <select name="yourName" id="yourId" 
			onChange="window.open(this.value)">
             <option value="1" selected>--相关链接--</option>
             <option value="#">四六级成绩查询</option>
             <option value="#">青果教务系统管理</option>
             <option value="#">普通话成绩查询</option>
            </select>
		 </td>
		 
		 <td style="color:blue;">校内站点:</td>
		 <td align="center">
		    <select name="yourName" id="yourId" 
			onChange="window.open(this.value)">
             <option value="#" selected>--相关链接--</option>
             <option value="http://zwx.sgmtu.edu.cn/">中文系</option>
             <option value="http://csd.sgmtu.edu.cn/">计科系</option>
             <option value="http://tyx.sgmtu.edu.cn/">体育系</option>
			 <option value="http://wlx.sgmtu.edu.cn/">物理系</option>
             <option value="http://zfjjx.sgmtu.edu.cn/">历史系</option>
             <option value="http://wyx.sgmtu.edu.cn/">外语系</option>
            </select>
		 </td>
		 
		 <td style="color:blue;">兄弟院校:</td>
		 <td>
		    <select name="yourName" id="yourId" 
			onChange="window.open(this.value)">
             <option value="#" selected>--相关链接--</option>
             <option value="#">贵州财经大学</option>
             <option value="#">贵州师范大学</option>
             <option value="#">贵州民族大学</option>
             <option value="#">贵州大学</option>
            </select>
		 </td>
		 
		 <td style="color:blue;">上级部门:</td>
		 <td>
		    <select name="yourName" id="yourId" 
			onchange="self.location.href=options[selectedIndex].value">
             <option value="#" selected>--相关链接--</option>
             <option value="#" >中华人民共和国教育部</option>
             <option value="#">贵州省教育厅</option>
             <option value="#">贵州省招生办</option>
             <option value="#">黔南教育局</option>
            </select>
		 </td>
	  </tr>
	</table>
</div>
<div id="footercontent">
   @2012-2015 ALL Rights Reserved  xxxxxxxxxxxxxxxxx版权所有<br> 
     地址:贵州省都匀xxxxxxx 邮编:558000
</div>
</div>
</body>
<script language="javascript" src="${resourcePath}/script/jquery-1.11.1.min.js"></script>
<script language="javascript" src="${resourcePath}/script/tupianlunbo.js"></script>
</html>