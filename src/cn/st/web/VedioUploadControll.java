package cn.st.web;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.st.entity.VedioType;
import cn.st.entity.VedioUpload;
import cn.st.entity.WatchRecord;
import cn.st.query.Page;
import cn.st.service.VedioTypeService;
import cn.st.service.VedioUploadService;
import cn.st.service.WatchRecordService;

@Controller
@RequestMapping(value="vediou_mng")
public class VedioUploadControll {
	/**
	 * 视频信息查询（根据标题和视频类型查询） --管理员登录的视频管理界面
	 * @param model
	 * @param title
	 * @param type_id
	 * @param currentPage
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:55:52
	 */
	@RequestMapping
	public String vedio_lists(Model model,String title,Integer type_id,String currentPage) {
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
		return "vediou_mng/vedio_lists";
	}
	/**
	 * 跳转视频上传页面
	 * @param model
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:55:52
	 */
	@RequestMapping(value="uploadVedio", method=RequestMethod.GET)
	public String uploadVedio(Model model) {
		//查询视频类型（绑定查询下拉框）
		VedioTypeService vedioTypeService=new VedioTypeService();
	    List<VedioType> vedioTypes=vedioTypeService.queryAllVedioTypeInf();
	    model.addAttribute("vedioTypes", vedioTypes);
		return "vediou_mng/uploadVedio";
	}
	/**
	 * 视频资源删除
	 * @param up_id
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:55:52
	 */
	@RequestMapping(value="deleteVedio/{up_id}", method=RequestMethod.GET)
	public String deleteVedio(@PathVariable("up_id") int up_id,HttpSession session) {
		VedioUploadService vedioUploadService=new VedioUploadService();
		vedioUploadService.deleteVedioById(up_id,session);
		return "redirect:/vediou_mng";//重定向到主页面
	}
	/**
	 * 1、查看对应视频 
	 * 2、提示是否观看过
	 * 3、没有观看过则添加到观看记录(只添加一次观看记录)
	 * @param model
	 * @param up_id
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:55:52
	 */
	@RequestMapping(value="play/{up_id}", method=RequestMethod.GET)
	public String play(WatchRecord watchRecord,HttpSession session,Model model,@PathVariable("up_id") int up_id){
		//视频service
		VedioUploadService vedioUploadService=new VedioUploadService();
		List<VedioUpload> vedioUploads= vedioUploadService.queryVedioByup_id(up_id);
		WatchRecordService service=new WatchRecordService();
		String stu_num=(String) session.getAttribute("stu_num");//通过session获取登录的学号
		watchRecord.setStu_num(stu_num);
		watchRecord.setUp_id(up_id);
		/*-------------根据学号和视频id唯一确定一条数据(观看记录)------------*/
		watchRecord=service.findBystu_idAndup_id(watchRecord);
		if (watchRecord!=null) {
			model.addAttribute("watchRecord","您已观看过该视频");
		}else{
		    model.addAttribute("watchRecord","恭喜您又要学到新知识了！！！");
		}
		/*-------------添加观看记录------------*/
		WatchRecord watchRecord2=new WatchRecord();
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String watchTime=sdf.format(date);//日期转String
		watchRecord2.setWatchTime(watchTime);
		watchRecord2.setStu_num(stu_num);
		watchRecord2.setUp_id(up_id);
		service.addWatchRecord(watchRecord2);
		
		model.addAttribute("vedioUploads",vedioUploads);
		return "vediou_mng/play";
	}
	
	/**
	 * 视频信息查询（根据标题和视频类型查询）--学生登录时查询视频，观看视频，做题等
	 * @param model
	 * @param title
	 * @param type_id
	 * @param currentPage
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月4日21:01:39
	 */
	@RequestMapping(value="vedio_listsStu")
	public String vedio_listsStu(Model model,String title,Integer type_id,String currentPage) {
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
		//根据学号查询
		model.addAttribute("type_id",type_id);
		//分页查询
		model.addAttribute("page",page);
		return "vediou_mng/vedio_listsStu";
	}
	
}
