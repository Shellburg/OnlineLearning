package cn.st.web;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import cn.st.entity.Notice;
import cn.st.query.Page;
import cn.st.service.NoticeService;

@Controller
@RequestMapping(value="notice")
public class NoticeMgController {
	NoticeService noticeService=null;
	/**
	 * 发布公告---打开添加公告栏
	 * @author 潘盛武
	 * @date 2017年12月11日23:27:16
	 */
	@RequestMapping(value="addNitce",method=RequestMethod.GET)
	public String addNitce() {
		
		return "notice/add";
	}
	/**
	 * 发布公告---发布公告
	 * @author 潘盛武
	 * @date 2017年12月11日23:27:16
	 */
	@RequestMapping(value="pubNitce",method=RequestMethod.POST)
	public String pubNitce(Notice notice,HttpSession session) {
		noticeService=new NoticeService();
		noticeService.pubNotice(notice,session);
		return "redirect:/notice/queryNotice";
	}
	
	/**
     * 查看公告信息	 
     * @param model
     * @param currentPage
     * @return
     * @author 潘盛武
	 * @date 2017年12月12日20:08:55
     */
	@RequestMapping(value="queryNotice",method=RequestMethod.GET)
	public String queryNotice(Model model,String currentPage) {
		noticeService=new NoticeService();
		Page<Notice> pagev=new Page<Notice>();
		pagev.setCurrentPage(currentPage==null? 1 :Integer.parseInt(currentPage));
		Page<Notice> page=noticeService.queryPage(pagev);
		model.addAttribute("page",page);
		return "notice/list";
	}
	/**
     * 修改公告------根据id绑定公告信息	 
     * @param model
     * @return
     * @author 潘盛武
	 * @date 2017年12月12日20:08:55
     */
	@RequestMapping(value="findNoticeById/{notice_id}",method=RequestMethod.GET)
	public String findNoticeById(Model model,@PathVariable("notice_id") int notice_id,Notice notice) {
		noticeService=new NoticeService();
		notice=noticeService.findNoticeById(notice_id);
		model.addAttribute("notice",notice);
		return "notice/update";
	}
	/**
     * 修改公告------根据id修改公告信息	 
     * @param model
     * @return
     * @author 潘盛武
	 * @date 2017年12月12日20:14:23
     */
	@RequestMapping(value="updateNoticeById",method=RequestMethod.POST)
	public String updateNoticeById(int notice_id,Notice notice,HttpSession session) {
		noticeService=new NoticeService();
		notice.setNotice_id(notice_id);
		noticeService.updateNotice(notice,session);
		return "redirect:/notice/queryNotice";
	}
	/**
     * 删除公告------根据id删除公告信息	 
     * @param model
     * @return
     * @author 潘盛武
	 * @date 2017年12月12日20:14:23
     */
	@RequestMapping(value="deleteNoticeById/{notice_id}",method=RequestMethod.GET)
	public String deleteNoticeById(Model model,@PathVariable("notice_id") int notice_id,Notice notice,HttpSession session) {
		noticeService=new NoticeService();
		notice.setNotice_id(notice_id);
		noticeService.deleteNoticeById(notice,session);
		return "redirect:/notice/queryNotice";
	}
	/**
     * 修改公告------根据id绑定公告信息	 
     * @param model
     * @return
     * @author 潘盛武
	 * @date 2017年12月12日20:08:55
     */
	@RequestMapping(value="noticeDetails/{notice_id}",method=RequestMethod.GET)
	public String noticeDetails(Model model,@PathVariable("notice_id") int notice_id,Notice notice) {
		noticeService=new NoticeService();
		notice=noticeService.findNoticeById(notice_id);
		model.addAttribute("notice",notice);
		return "notice/noticeDetails";
	}
}
