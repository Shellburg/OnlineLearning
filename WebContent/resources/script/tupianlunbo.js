var  _index=0;
     var  _clearTime=null;
     $("#box .curr_item ul li").click(function(){
         clearInterval(_clearTime);//ֹͣ��ʱ��
         _index=$(this).index();/*���� ���к�*/
         $(this).addClass("curr").siblings().removeClass("curr");/*this��ʾ��ǰ*/
          /*�ҵ�λ��fadeIn()�ǵ������˼siblings().fadeOut()�ǵ�������˼*/ 
         $("#box .list_item ul li").eq(_index).fadeIn(500).siblings().fadeOut();        
     });
     //�����߰�ťʱ�������¼�
     $("#box .prev").click(function(){
      clearInterval(_clearTime);//ֹͣ��ʱ��
      _index --;
      //�ж����_index��������ߵ�һ����Ƭ�ͷ��ص���������Ƭ
      if(_index <0){
            _index=16;
       }
        getCommon();
     });
     //����ұ߰�ťʱ�������¼�
     $("#box .next").click(function(){
      clearInterval(_clearTime);//ֹͣ��ʱ��
      _index ++;
      if(_index >16){
            _index=0;
       }
       getCommon();
     });

      //�жϵ��������·�СͼƬʱ���Զ������ƿ�ʱ�Զ�����
      $("#box .curr_item ul li").hover(function(){
          clearInterval(_clearTime);//ֹͣ��ʱ��
      },function(){
         autoPlay();//�����Զ����ŵĹ���
          
     })
      //�жϵ���꾭����߰�ťʱ���Զ������ƿ�ʱ�Զ�����
      $("#box .prev").hover(function(){
          clearInterval(_clearTime);//ֹͣ��ʱ��
      },function(){
         autoPlay();//�����Զ����ŵĹ���
          
     })
     //�жϵ���꾭���ұ߰�ťʱ���Զ������ƿ�ʱ�Զ�����
      $("#box .next").hover(function(){
          clearInterval(_clearTime);//ֹͣ��ʱ��
      },function(){
         autoPlay();//�����Զ����ŵĹ���
          
     })

       //�������� �л�����Ч��
      function getCommon(){
            $("#box .curr_item ul li").eq(_index).addClass("curr").siblings().removeClass("curr");
            $("#box .list_item ul li").eq(_index).fadeIn(500).siblings().fadeOut();
     }
       //���嶨ʱ��
       function autoPlay(){
       _clearTime=setInterval(function(){
       _index ++;
       if(_index >16){
            _index=0;
       }
       getCommon()//���ù�������
       },1800);  
}
