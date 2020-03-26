package cn.st.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.st.entity.Comments;
import cn.st.query.Page;
import cn.st.service.CommentsService;

@Controller
public class CommentsController {
     
	/**
	 * 打开留言窗口
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月6日19:51:45
	 */
	@RequestMapping(value="/addleaveMsg",method=RequestMethod.GET)
	public String leaveMsg() {
		
		return "message/leaveMsg";
	}
	/**
     * 留言
     * @param comments
     * @author 潘盛武
	 * @date 2017年12月6日19:28:37
     */
	@RequestMapping(value="/leaveMessage",method=RequestMethod.POST)
	public String leaveMessage(Model model,Comments comments,HttpSession session) {
		CommentsService service=new CommentsService();
		boolean bol=service.leaveMessage(comments, session);
		if (bol) {
			model.addAttribute("message", "留言成功！");
		}else {
			model.addAttribute("message", "留言失败！");
		}
		
		return "redirect:/addleaveMsg";//重定向到查询页面
	}
	
	/**
	 * 根据学生标题查询学生留言
	 * @param model           查询自己审核通过的留言信息（学生登录查询）
	 * @param currentPage
	 * @param stu_num
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:51:28
	 */
	@RequestMapping(value="/queryCommentInf")
	public String queryCommentInf(Model model,String currentPage,String c_title) {
		CommentsService service=new CommentsService();
		Page<Comments> pagev=new Page<Comments>();
		//分页判断
		pagev.setCurrentPage(currentPage==null? 1 :Integer.parseInt(currentPage));
		Page<Comments> page=service.queryPage(pagev, c_title);
		//根据标题查询留言信息
		model.addAttribute("c_title",c_title);
		model.addAttribute("page",page);
		return "message/list";
	}
	
	/**
	 * 根据id删除留言信息
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月6日21:00:01
	 */
	@RequestMapping(value="/deleteMSG/{c_id}",method=RequestMethod.GET)
	public String deleteMSG(@PathVariable("c_id") long c_id,HttpSession session) {
		CommentsService service=new CommentsService();
		service.deleteMessage(c_id,session);
		return "redirect:/queryCommentInfMG";
	}
	/**
	 * 打开回复留言窗口
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月6日19:51:45
	 */
	@RequestMapping(value="/replyMsg/{c_id}",method=RequestMethod.GET)
	public String replyMsg(@PathVariable("c_id")long c_id,Model model,Comments comments){
		CommentsService service=new CommentsService();
		comments=service.findById(c_id);
		model.addAttribute("comments", comments);
		return "message/replyMsg";
	}
	/**
	 * 回复留言
	 * @param comments
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月2日20:28:53
	 */
	@RequestMapping(value="/replyMessage",method=RequestMethod.POST)
	public String replyMessage(Comments comments,HttpSession session,@RequestParam("rContent") String rContent){
		CommentsService service=new CommentsService();
		comments.setrContent(rContent);
		service.replyContent(comments, session);
		return "redirect:/queryCommentInfMG";
	}
	
	
	/**
	 * 查询 留言信息 分页  根据学号查询
	 * 管理员根据学号查询留言信息进行审核审核通过的留言    （管理员登录）
	 * 信息才能显示在学生端的留言信息上。
	 * @param model
	 * @param currentPage
	 * @param stu_num
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:51:28
	 */
	@RequestMapping(value="/queryCommentInfMG")
	public String queryCommentInfMG(Model model,String currentPage, String c_title) {
		CommentsService service=new CommentsService();
		Page<Comments> pagev=new Page<Comments>();
		//分页判断
		pagev.setCurrentPage(currentPage==null? 1 :Integer.parseInt(currentPage));
		Page<Comments> page=service.queryPageMG(pagev, c_title);
		 //根据学号查询留言信息
		model.addAttribute("c_title",c_title);
		model.addAttribute("page",page);
		return "message/msgMg";
	}
	
	/**
	 * 学生只能查看自己的留言信息
	 * @param page
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月16日22:32:17
	 */
	@RequestMapping(value="/queryMyMessages")
	public String queryMyMessages(HttpSession session,Model model){
		CommentsService service=new CommentsService();
		String contentName=(String)session.getAttribute("stuName");//获取学生姓名根据学生信息查看对应的留言信息
		System.out.println("学生姓名:"+contentName);
		List<Comments> comments=service.queryMyMessages(contentName);
		model.addAttribute("comments", comments);
		return "message/msgStu";
	}
    /**
     * 显示详细留言信息	
     * @param c_id
     * @param model
     * @return
     * @author 潘盛武
     * @date 2018年1月2日21:10:37
     */
	@RequestMapping(value="/showMsgDetails/{c_id}")
	public String showMsgDetails(@PathVariable("c_id")long c_id,Model model){
		CommentsService service=new CommentsService();
	     Comments comments=service.findById(c_id);
		 model.addAttribute("comments", comments);
		return "message/commentDetails";
	}
}
