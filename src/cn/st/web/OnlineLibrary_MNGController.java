package cn.st.web;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.st.entity.Options;
import cn.st.entity.VedioUpload;
import cn.st.query.Page;
import cn.st.service.OptionsService;
import cn.st.service.VedioUploadService;
@Controller
@RequestMapping(value="onlinelibrary")
public class OnlineLibrary_MNGController{
    
	/**
	 * 查看选择题部分
	 * @param model
	 * @param currentPage
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:51:28
	 */
	@RequestMapping
	public String list(Model model,String currentPage) {
		OptionsService optionsService=new OptionsService();
		Page<Options> pagev=new Page<Options>();
		pagev.setCurrentPage(currentPage==null? 1 :Integer.parseInt(currentPage));
		Page<Options> page=optionsService.queryAllOptionIng(pagev);
		
		//绑定视频的下拉框值
		VedioUploadService  vedioUploadService=new VedioUploadService();
	    List<VedioUpload> vedioes=vedioUploadService.queryVedioAllInf();
	    model.addAttribute("vedioes", vedioes);
		model.addAttribute("page",page);
		return "onlinelibrary/list";
	}
	/**
	 * 查看对应视频的选择题部分（根据视频来做题）对应的视频id
	 * @param model
	 * @param up_id
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:51:28
	 */
	@RequestMapping(value="list2/{up_id}", method=RequestMethod.GET)
	public String list2(
			Model model,@PathVariable("up_id") int up_id){
		//选择题service
		OptionsService optionsService=new OptionsService();
		List<Options> options=optionsService.queryAllOptionIngByup_id(up_id);
		model.addAttribute("options",options);
		return "onlinelibrary/list2";
	}
	/**
	 * 视频习题测试
	 * @param model
	 * @param option0
	 * @param option1
	 * @param option2
	 * @param option3
	 * @param option4
	 * @param up_id
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:51:28
	 */
	@RequestMapping(value="testRecord", method=RequestMethod.POST)
	public String testRecord(Model model,
	    @RequestParam("option0") String option0,
	    @RequestParam("option1") String option1,
	    @RequestParam("option2") String option2,
	    @RequestParam("option3") String option3,
	    @RequestParam("option4") String option4,
		@RequestParam("up_id")int up_id){
		//选择题service
		OptionsService optionsService=new OptionsService();
		String [] answer={option0,option1,option2,option3,option4};
		//统计答对的题目
		int optionsRecord=optionsService.testRecords(answer, up_id);
		model.addAttribute("options",optionsRecord);
		return "onlinelibrary/testRecord";
	}
	/**
	 * 添加选择题-打开添加窗口
	 * @param model
	 * @param options
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月10日21:46:10
	 */
	@RequestMapping(value="addOp", method=RequestMethod.GET)
	public String addOp(Model model,Options options,Integer up_id) {
		//绑定视频的下拉框值
		VedioUploadService  vedioUploadService=new VedioUploadService();
	    List<VedioUpload> vedioes=vedioUploadService.queryVedioAllInf();
	    model.addAttribute("vedioes", vedioes);
	    model.addAttribute("up_id", up_id);
		return "onlinelibrary/addOption";
	}
	/**
	 * 添加选择题-保存数据
	 * @param model
	 * @param options
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月10日21:46:10
	 */
	@RequestMapping(value="addOpSave", method=RequestMethod.POST)
	public String addOpSave(Model model,Options options,Integer up_id,HttpSession session){
		OptionsService optionsService=new OptionsService();
		options.setUp_id(up_id);
		optionsService.addOptions(options,session);
		return "redirect:/onlinelibrary";//添加数据后刷界面
	}
	
	/**
	 * 绑定选择题数据
	 * @param model
	 * @param options
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月11日18:55:52
	 */
	@RequestMapping(value="findOpById/{op_id}", method=RequestMethod.GET)
	public String findOpById(Model model,@PathVariable("op_id") int op_id,Options options){
		OptionsService optionsService=new OptionsService();
		options=optionsService.findById(op_id);
		//绑定视频的下拉框值
		VedioUploadService  vedioUploadService=new VedioUploadService();
	    List<VedioUpload> vedioes=vedioUploadService.queryVedioAllInf();
	    model.addAttribute("vedioes", vedioes);
		model.addAttribute("options", options);
		return "onlinelibrary/updateOption";//
	}
	/**
	 * 修改选择题数据
	 * @param model
	 * @param options
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月11日18:55:52
	 */
	@RequestMapping(value="updateOpById", method=RequestMethod.POST)
	public String updateOpById(Model model,Options options,HttpSession session){
		OptionsService optionsService=new OptionsService();
		optionsService.updateOp(options,session);
		return "redirect:/onlinelibrary";//修改数据后刷界面
	}
	/**
	 * 删除选择题数据
	 * @param model
	 * @param options
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月11日18:55:52
	 */
	@RequestMapping(value="deleteOpById/{op_id}", method=RequestMethod.GET)
	public String deleteOpById(Model model,Options options,@PathVariable("op_id")int op_id,HttpSession session){
		OptionsService optionsService=new OptionsService();
		options.setOp_id(op_id);
		optionsService.deleteOp(options,session);
		return "redirect:/onlinelibrary";//删除数据后刷界面
	}
	/**
	 * 显示题目详细信息
	 * @param model
	 * @param options
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月11日19:45:36
	 */
	@RequestMapping(value="showOpDetail/{op_id}", method=RequestMethod.GET)
	public String showOpDetail(Model model,@PathVariable("op_id") int op_id,Options options){
		OptionsService optionsService=new OptionsService();
		options=optionsService.findById(op_id);
		//绑定视频的下拉框值
		VedioUploadService  vedioUploadService=new VedioUploadService();
	    List<VedioUpload> vedioes=vedioUploadService.queryVedioAllInf();
	    model.addAttribute("vedioes", vedioes);
		model.addAttribute("options", options);
		return "onlinelibrary/optionDetail";//跳转到题目详细信息
	}
}
