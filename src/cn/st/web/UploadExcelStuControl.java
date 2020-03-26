package cn.st.web;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import cn.st.entity.Student;
import cn.st.poiexcel.util.ImportExcelUtil;
import cn.st.service.StudentService;
@Controller
@RequestMapping("/uploadStuExcel")  
public class UploadExcelStuControl {
	
	/**
	 * 描述：通过传统方式form表单提交方式导入excel文件
	 * @param request
	 * @throws Exception
	 * @author 潘盛武
	 * @date 2017年12月12日21:49:37
	 */
	@RequestMapping(value="uploadStu",method={RequestMethod.GET,RequestMethod.POST})
	public  String  uploadExcel(HttpSession session,HttpServletRequest request,@RequestParam("department") String department) throws Exception {
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
		List<String> listStu=new ArrayList<String>();
		int countTotal=listob.size();//统计导入的数据条数
		int countSuceess=0;//导入成功数
		//该处可调用service相应方法进行数据保存到数据库中(遍历表格数据然后保存到数据库中)
		for (int i = 0; i < listob.size(); i++) {
			//实例化服务类
			StudentService stuService=new StudentService();
			//实例化实体类
			Student student=new Student();
			List<Object> lo = listob.get(i);//泛型
			student.setStu_num(String.valueOf(lo.get(0)));
			student.setStu_name(String.valueOf(lo.get(1)));
			student.setPsw(String.valueOf(lo.get(2)));
			student.setPhone(String.valueOf(lo.get(3)));
			student.setEamil(String.valueOf(lo.get(4)));
			student.setGender(String.valueOf(lo.get(5)));
			student.setDepartment(department);
			student.setClassT(String.valueOf(lo.get(6)));
			Student student2=null;
			student2=stuService.validateStuNum(student);//验证导入学号是否重复
			//若student为空则表示该学号没有存在则插入数据否则不插入学生信息
			if (student2==null) {
				stuService.studentReg(student,session);//实体对象通过set方式获取到值然后新增数据到数据表中
				System.out.println("成功导入的-->学号:"+student.getStu_num());
				countSuceess++;//导入成功一条就自增1
			}else {
				System.out.println("该学号已存在-->学号:"+student.getStu_num());
				String stuNum=student.getStu_num();
				listStu.add(stuNum);//将不能导入的学号存入到list列表中
				session.setAttribute("listStu", listStu);//返回导入失败条数
			}
		}
		session.setAttribute("countTotal", countTotal);//返回导入总条数
		session.setAttribute("countSuceess", countSuceess);//返回导入成功条数
		session.setAttribute("countFail", countTotal-countSuceess);//返回导入失败条数
		//返回到上传成功的页面
		return "/student/result";
	}
}
