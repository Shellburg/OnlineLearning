package cn.st.web;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.st.entity.Options;
import cn.st.poiexcel.util.ImportExcelUtil;
import cn.st.service.OptionsService;
@Controller
@RequestMapping("/uploadExcel/*")  
public class UploadExcelControl {
	
	/**
	 * 描述：通过传统方式form表单提交方式导入excel文件
	 * @param request
	 * @throws Exception
	 * @author 潘盛武
	 * @date 2017年11月27日22:54:40
	 */
	@RequestMapping(value="upload.do",method={RequestMethod.GET,RequestMethod.POST})
	public  String  uploadExcel(HttpSession session,HttpServletRequest request,@RequestParam("up_id") int up_id) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		System.out.println("通过传统方式form表单提交方式导入excel文件！");
		InputStream in =null;
		List<List<Object>> listob = null;
		MultipartFile file = multipartRequest.getFile("upfile");//获取文件
		if(file.isEmpty()){
			throw new Exception("文件不存在！");
		}
 		in = file.getInputStream();
		listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename());
		in.close();
		int countTotal=listob.size();//统计导入的选择题条数
		//该处可调用service相应方法进行数据保存到数据库中(遍历表格数据然后保存到数据库中)
		for (int i = 0; i < listob.size(); i++) {
			//实例化服务类
			OptionsService opservice=new OptionsService();
			//实例化实体类
			Options options=new Options();
			List<Object> lo = listob.get(i);//泛型
			options.setQuestion(String.valueOf(lo.get(0)));
			options.setOp_a(String.valueOf(lo.get(1)));
			options.setOp_b(String.valueOf(lo.get(2)));
			options.setOp_c(String.valueOf(lo.get(3)));
			options.setOp_d(String.valueOf(lo.get(4)));
			options.setAnswer(String.valueOf(lo.get(5)));
			options.setParse(String.valueOf(lo.get(6)));
			options.setOp_answer(String.valueOf(lo.get(7)));
			options.setUp_id(up_id);//外键=》视频上传的id
			opservice.addOptions(options,session);//实体对象通过set方式获取到值然后新增数据到数据表中
			System.out.println("打印信息-->:选择题问题:"+options.getQuestion());
		}
		session.setAttribute("countTotal", countTotal);//返回导入总条数
		//返回到上传成功的页面
		return "/onlinelibrary/result";
	}
}
