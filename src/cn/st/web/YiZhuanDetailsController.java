package cn.st.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.st.entity.Comments;
import cn.st.entity.DataOp;
import cn.st.entity.Notice;
import cn.st.entity.VedioType;
import cn.st.entity.VedioUpload;
import cn.st.query.Page;
import cn.st.service.CommentsService;
import cn.st.service.DataService;
import cn.st.service.NoticeService;
import cn.st.service.VedioTypeService;
import cn.st.service.VedioUploadService;

@Controller
@RequestMapping(value="yiZhuanDetails")
public class YiZhuanDetailsController {
	
	/**
     * 查看单条公告信息	 
     * @param model
     * @return
     * @author 潘盛武
	 * @date 2018年3月9日22:29:16
     */
	@RequestMapping(value="findNoticeById/{notice_id}",method=RequestMethod.GET)
	public String findNoticeById(Model model,@PathVariable("notice_id") int notice_id,Notice notice) {
		NoticeService noticeService=new NoticeService();
		notice=noticeService.findNoticeById(notice_id);
		model.addAttribute("notice",notice);
		return "yiZhuanQianTai/Notice";
	}
    /**
     * 公告详细信息
     * @author 潘盛武
     * @date 2017年12月23日20:06:11
     * @return
     */
	@RequestMapping(value="noticeDtails",method=RequestMethod.GET)
	public String noticeDtails(Model model,String currentPage) {
		NoticeService noticeService=new NoticeService();
		Page<Notice> pagev=new Page<Notice>();
		pagev.setCurrentPage(currentPage==null? 1 :Integer.parseInt(currentPage));
		Page<Notice> page=noticeService.queryPage(pagev);
		model.addAttribute("page",page);
		return "yiZhuanQianTai/Notice";
	}
	/**
     * 教师详细信息
     * @author 潘盛武
     * @date 2017年12月23日20:06:11
     * @return
     */
	@RequestMapping(value="teachersDetails",method=RequestMethod.GET)
	public String teachersDetails() {
		return "yiZhuanQianTai/TeachersDeatails";
	}
	/**
     * 跳转到首页
     * @author 潘盛武
     * @date 2017年12月23日22:09:54
     * @return
     */
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String index() {
		return "redirect:/index.jsp";
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
		return "yiZhuanQianTai/LeaveMsgDeatails";
	}
	
	/**
     * 留言详细信息  --- 首页查看审核通过的留言信息
     * @author 潘盛武
     * @date 2017年12月23日22:09:54
     * @return
     */
	@RequestMapping(value="leaveMsg",method=RequestMethod.GET)
	public String leaveMsg(Model model,String currentPage, String c_title) {
		CommentsService service=new CommentsService();
		Page<Comments> pagev=new Page<Comments>();
		//分页判断
		pagev.setCurrentPage(currentPage==null? 1 :Integer.parseInt(currentPage));
		Page<Comments> page=service.queryPage(pagev, c_title);
		 //根据学号查询留言信息
		model.addAttribute("c_title",c_title);
		model.addAttribute("page",page);
		return "yiZhuanQianTai/LeaveMsgDeatails";
	}
	/**
     * 视频欣赏
     * @author 潘盛武
     * @date 2017年12月23日23:01:50
     * @return
     */
	@RequestMapping(value="videoAppreciation")
	public String videoAppreciation(Model model,String title,Integer type_id,String currentPage) {
		//查询视频类型（绑定查询下拉框）
		VedioTypeService vedioTypeService=new VedioTypeService();
	    List<VedioType> vedioTypes=vedioTypeService.queryAllVedioTypeInf();
	    model.addAttribute("vedioTypes", vedioTypes);
		//查询所有视频信息
		VedioUploadService vedioUploadService=new VedioUploadService();
		Page<VedioUpload> pagev=new Page<VedioUpload>();
		//分页判断
		pagev.setCurrentPage(currentPage==null ? 1 : Integer.parseInt(currentPage));
		Page<VedioUpload> page=vedioUploadService.queryByTittleAndType(title, type_id, pagev);
		List<VedioUpload> vedioUploads=page.getResult();
		//视频表和类型表连接查询
		for (VedioUpload vedioUpload : vedioUploads) {
			vedioUpload.setVedioType(vedioTypeService.findById(vedioUpload.getType_id()));
		}
	    //根据系别查询
		model.addAttribute("title",title);
		//根据类型查询
		model.addAttribute("type_id",type_id);
		//分页查询
		model.addAttribute("page",page);
		return "yiZhuanQianTai/VideoAppreciation";
	}
	/**
     * 资料下载
     * @author 潘盛武
     * @date 2017年12月24日09:14:49
     * @return
     */
	@RequestMapping(value="dataDowload")
	 public String dataDowload(Model model,String currentPage, String data_type,String file_name) {
	       DataService dataService=new DataService();
		   Page<DataOp> pagev=new Page<DataOp>();
			//分页判断
			pagev.setCurrentPage(currentPage==null? 1 :Integer.parseInt(currentPage));
			Page<DataOp> page=dataService.queryDataByType(pagev, data_type,file_name);
			model.addAttribute("page",page);
	   return "yiZhuanQianTai/DataDowload";
	}
	
	/**
     * 项目介绍
     * @author 潘盛武
     * @date 2017年12月24日19:00:17
     * @return
     */
	@RequestMapping(value="projectIntroduction",method=RequestMethod.GET)
	 public String projectIntroduction() {
		
	   return "yiZhuanQianTai/projectionInstroduction";
	}
	
	
}
