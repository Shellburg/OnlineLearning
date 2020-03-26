package cn.st.web;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.st.entity.DataOp;
import cn.st.entity.VedioUpload;
import cn.st.query.Page;
import cn.st.service.DataService;
import cn.st.service.VedioUploadService;

@Controller
public class DataMgController {
	
	/**
	 * 打开资料管理
	 * @author 潘盛武
	 * @date 2017年12月15日22:01:46
	 */
	@RequestMapping(value="/opList",method=RequestMethod.GET)
	public String list() {
		
		return "dataUpAndDow/list";
	}
	/**
	 * 打开上传资料
	 * @author 潘盛武
	 * @date 2017年12月15日22:01:46
	 */
	@RequestMapping(value="/addData",method=RequestMethod.GET)
	public String addData(Model model,HttpSession session) {
		//查询视频名称（绑定查询下拉框）
		VedioUploadService vedioUploadService=new VedioUploadService();
	    List<VedioUpload> vedioUploads=vedioUploadService.queryVedioAllInf();
	    model.addAttribute("vedioUploads", vedioUploads);
		return "dataUpAndDow/uploadData";
	}
	
	
	/**
	 * 学习资料上传
	 * @author 潘盛武  @RequestParam("data_type") String data_type,
			 @RequestParam("vedio_name") String vedio_name
	 * @date 2017年12月15日22:01:46
	 */
	 @RequestMapping(value="/fileUpload")
	 public String fileUpload(HttpServletRequest request,HttpSession session, 
			 @ModelAttribute DataOp dataOp,Model model,@RequestParam("data_type") String data_type,
			 @RequestParam("vedio_name") String vedio_name) throws Exception {
		//如果文件不为空，写入上传路径
		if(!dataOp.getUpFile().isEmpty()) {
			//获取工程名
			String conetextPath=request.getSession().getServletContext().getContextPath();
			//上传文件路径
			String path = request.getSession().getServletContext().getRealPath("/fileStorage/");
			//上传文件名  需要保存的文件名
			String filename = dataOp.getUpFile().getOriginalFilename();
		    File filepath = new File(path,filename);
		    String subStrPath=conetextPath+"/fileStorage/"+filename;
		    
			System.out.println("------文件需要保存的路径------："+subStrPath);
			//判断路径是否存在，如果不存在就创建一个
	        if (!filepath.getParentFile().exists()) { 
	        	filepath.getParentFile().mkdirs();
	        }
	        //将上传文件保存到一个目标文件当中
	        dataOp.getUpFile().transferTo(new File(path + File.separator + filename));
	        /**
	         *文件名,资料类型，上传人，更新时间，对应视频，文件上传路径等保存进数据
	         */
	        //实例化资料上传server
	        DataService dataService=new DataService();
	        dataOp.setFile_name(filename);//文件名称
	        dataOp.setData_type(data_type);//资料类型
	        String upload_name=(String) session.getAttribute("name");//通过seeing获取登录时的管理员的名字
	        dataOp.setUpload_name(upload_name);//上传人
	        dataOp.setUpload_path(subStrPath);//文件保存的路径
	        Date date=new Date();
	        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String upload_time=sdf.format(date);
	        dataOp.setUpload_time(upload_time);//保存系统时间
	        dataOp.setVedio_name(vedio_name);//对应的视频
	        dataService.uploadData(dataOp,session);//保存上传资料信息
	        return "redirect:/queryData";//返回到list这个页面
		} else {
			return "dataUpAndDow/error";
		}
	}
	 /**
	  * 文件下载
	  * @param request
	  * @param filename    @RequestParam("filename")表示请求的文件名称
	  * @param model
	  * byte[] 泛型（泛指任意一种类型）
	  * @return
	  * @throws Exception
	  * @author 潘盛武  
	  * @date 2017年12月16日18:39:37
	  */
	 @RequestMapping(value="/download")
	 public ResponseEntity<byte[]> download(HttpServletRequest request,
			 @RequestParam("filename") String filename,@RequestParam("data_id") int data_id,
			 Model model,DataOp dataOp)throws Exception {
		    request.setCharacterEncoding("UTF-8");
			//下载文件路径
		    String path = request.getSession().getServletContext().getRealPath("/fileStorage/");
			File file = new File(path + File.separator + filename);//文件路径分割
			System.out.println("file文件路径："+file);
	        HttpHeaders headers = new HttpHeaders();//http请求头 
	        //下载显示的文件名，解决中文名称乱码问题  
	        String downloadFielName = new String(filename.getBytes(),"iso-8859-1");
	        //String downloadFielName = new String(filename);
	        System.out.println("下载的文件名称："+downloadFielName);
	        //通知浏览器以attachment（下载方式）打开图片
	        headers.setContentDispositionFormData("attachment;filename", downloadFielName); 
	        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);//octet stream
	        //下载成功后更新下载次数
        	DataService dataService=new DataService();
	        dataOp.setData_id(data_id);
	        List<DataOp> dp=dataService.CountDowloadById(dataOp);
	        int download_count=dp.get(0).getDownload_count();//根据id获取下载次数
	        dataOp.setDownload_count(download_count+1);//下载成功后则在原先的基础上+1；
	        dataService.updateDownloadCount(dataOp);//更新下载次数
	        
           return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);  
          
	 }
	 
	 /**
	  * 资料查询  根据资料名称和资料类型查询     --管理员登录时下载页面控制
	  * @author 潘盛武   (提交数据用POST,否则用GET)
	  * @date 2017年12月16日18:40:53
	  */
	 @RequestMapping(value="/queryData")
	 public String queryData(Model model,String currentPage, String data_type,String file_name) {
	       DataService dataService=new DataService();
		   Page<DataOp> pagev=new Page<DataOp>();
			//分页判断
			pagev.setCurrentPage(currentPage==null? 1 :Integer.parseInt(currentPage));
			Page<DataOp> page=dataService.queryDataByType(pagev, data_type,file_name);
			 //根据学号查询
			model.addAttribute("page",page);
	   return "dataUpAndDow/list";
	}
    /**
     * 删除资料
     * @author 
	 * @date 2017年12月16日18:40:53
     */
	 @RequestMapping(value="/deleteDataById/{data_id}")
	 public String deleteDataById(@PathVariable("data_id") int data_id,DataOp dataOp,HttpSession session) {
		 DataService dataService=new DataService();
		 dataOp.setData_id(data_id);//获取id
		 dataService.deleteDataById(dataOp,session);
		 return "redirect:/queryData";//返回到list这个页面
		 
	}
	 /**
	  * 资料查询  根据资料名称和资料类型查询           ----学生登录时的下载页面控制
	  * @author 潘盛武   (提交数据用POST,否则用GET)
	  * @date 2017年12月16日18:40:53
	  */
	 @RequestMapping(value="/dowloadList")
	 public String dowloadList(Model model,String currentPage, String data_type,String file_name) {
	       DataService dataService=new DataService();
		   Page<DataOp> pagev=new Page<DataOp>();
			//分页判断
			pagev.setCurrentPage(currentPage==null? 1 :Integer.parseInt(currentPage));
			Page<DataOp> page=dataService.queryDataByType(pagev, data_type,file_name);
			 //根据学号查询
			model.addAttribute("page",page);
	   return "dataUpAndDow/dowloadList";
	}
	
	 
}
