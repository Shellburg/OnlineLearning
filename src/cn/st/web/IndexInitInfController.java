package cn.st.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.st.entity.Comments;
import cn.st.entity.Notice;
import cn.st.entity.VedioUpload;
import cn.st.service.CommentsService;
import cn.st.service.NoticeService;
import cn.st.service.VedioUploadService;

@Controller
public class IndexInitInfController {

	/**
     * 初始化主页数据（公告信息、留言信息、热点视频）	 
     * @param model
     * @param currentPage
     * @return
     * @author 潘盛武
	 * @date 2017年12月18日20:44:36
     */
	@RequestMapping(value="/InitIndexInf",method=RequestMethod.GET)
	public String InitIndexInf(Model model) {
		//1、初始化查询通告信息
		NoticeService noticeService=new NoticeService();
		List<Notice>  InitNotice=noticeService.initNotice();
		//2、初始化查询留言信息
		CommentsService commentsService=new CommentsService();
		List<Comments>  InitComments=commentsService.initComments();
		//3、初始化查询热点视频（最新上传的4个视频作为热点视频）
		VedioUploadService vedioUploadService=new VedioUploadService();
		List<VedioUpload>  InitVedios=vedioUploadService.initVedios();
		model.addAttribute("InitNotice",InitNotice); 
		model.addAttribute("InitComments",InitComments); 
		model.addAttribute("InitVedios",InitVedios); 
		return "forward:/index.jsp";//将数据转发到index这个页面
	}
}
