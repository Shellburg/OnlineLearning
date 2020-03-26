package cn.st.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="news")
public class See_NewsMgController {
	
   /**
    * 新闻查看
    * @param model
    * @return
    * @author 潘盛武
	* @date 2017年11月27日22:55:52
    */
   @RequestMapping
   public String  list(Model model) {
	
    return "news/list";
}
}
