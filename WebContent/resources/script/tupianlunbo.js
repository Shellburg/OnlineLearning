var  _index=0;
     var  _clearTime=null;
     $("#box .curr_item ul li").click(function(){
         clearInterval(_clearTime);//停止计时器
         _index=$(this).index();/*索引 序列号*/
         $(this).addClass("curr").siblings().removeClass("curr");/*this表示当前*/
          /*找到位置fadeIn()是淡入的意思siblings().fadeOut()是淡出的意思*/ 
         $("#box .list_item ul li").eq(_index).fadeIn(500).siblings().fadeOut();        
     });
     //点击左边按钮时发生的事件
     $("#box .prev").click(function(){
      clearInterval(_clearTime);//停止计时器
      _index --;
      //判断如果_index到达最左边的一张照片就返回到第三张照片
      if(_index <0){
            _index=16;
       }
        getCommon();
     });
     //点击右边按钮时发生的事件
     $("#box .next").click(function(){
      clearInterval(_clearTime);//停止计时器
      _index ++;
      if(_index >16){
            _index=0;
       }
       getCommon();
     });

      //判断当鼠标进入下方小图片时不自动播放移开时自动播放
      $("#box .curr_item ul li").hover(function(){
          clearInterval(_clearTime);//停止计时器
      },function(){
         autoPlay();//调用自动播放的功能
          
     })
      //判断当鼠标经过左边按钮时不自动播放移开时自动播放
      $("#box .prev").hover(function(){
          clearInterval(_clearTime);//停止计时器
      },function(){
         autoPlay();//调用自动播放的功能
          
     })
     //判断当鼠标经过右边按钮时不自动播放移开时自动播放
      $("#box .next").hover(function(){
          clearInterval(_clearTime);//停止计时器
      },function(){
         autoPlay();//调用自动播放的功能
          
     })

       //公共方法 切换动画效果
      function getCommon(){
            $("#box .curr_item ul li").eq(_index).addClass("curr").siblings().removeClass("curr");
            $("#box .list_item ul li").eq(_index).fadeIn(500).siblings().fadeOut();
     }
       //定义定时器
       function autoPlay(){
       _clearTime=setInterval(function(){
       _index ++;
       if(_index >16){
            _index=0;
       }
       getCommon()//调用公共方法
       },1800);  
}
