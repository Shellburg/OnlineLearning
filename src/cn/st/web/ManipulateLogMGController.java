package cn.st.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.st.entity.ManipulateLog;
import cn.st.query.Page;
import cn.st.service.ManipulateLogService;
@Controller
public class ManipulateLogMGController {
    
	/**
	 * 日志查询
	 * @author 潘盛武
	 * @date 2018年1月13日17:19:57
	 * @return
	 */
	@RequestMapping(value="/listLog")
	public String listLog(Model model,String currentPage,String userName,
			String starTime,String endTime) {
		ManipulateLogService mservice=new ManipulateLogService();
		Page<ManipulateLog> pagev=new Page<ManipulateLog>();
		//分页判断
		pagev.setCurrentPage(currentPage==null? 1 :Integer.parseInt(currentPage));
		Page<ManipulateLog> page=mservice.queryMpLogByTime(pagev, userName, starTime, endTime);
		//根据标题查询留言信息
	    model.addAttribute("page",page);
		return "manipulateLog/manipulateLoglist";
	}
	
	/**
	 * 清空日志表操作
	 * @author 潘盛武
	 * @date 2018年1月13日21:52:27  
	 */
	@RequestMapping(value="/truncateMaLog",method=RequestMethod.GET)
	public String truncateMaLog() {
		ManipulateLogService mservice=new ManipulateLogService();
		mservice.truncateMaLog();
		System.out.println("清空日志");
		return "redirect:/listLog"; 
		
	}
}
