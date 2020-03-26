package cn.st.web;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.st.entity.Student;
import cn.st.query.Page;
import cn.st.service.StudentService;
@Controller
public class Student_MgController {
	/**
	 * 根据登录的学号只允许查看自己的信息
	 * @param loginname
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月4日20:17:45
	 */
	@RequestMapping(value="/queryMyMsg")
	public String queryMyMsg(Model model,HttpSession session) {
		StudentService service=new StudentService();
		String stu_num=(String)session.getAttribute("stu_num");//获取到学生登录的session值stu_num学号
		System.out.println("学号"+stu_num);
		
		List<Student> myMsg=service.queryMyMsg(stu_num);
		model.addAttribute("myMsg",myMsg);
		return "student/myMsg";//返回到我的页面
	}
	
	/**
	 * 根据学生学号查询学生
	 * @param model
	 * @param currentPage
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:51:28
	 */
	@RequestMapping(value="/queryStuInf")
	public String queryStuInf(Model model,String currentPage, String stu_name) {
		StudentService service=new StudentService();
		Page<Student> pagev=new Page<Student>();
		//分页判断
		pagev.setCurrentPage(currentPage==null? 1 :Integer.parseInt(currentPage));
		Page<Student> page=service.queryStuInf(pagev, stu_name);
		 //根据学生姓名查询
		model.addAttribute("stu_name",stu_name);
		model.addAttribute("page",page);
		return "student/queryStuInf";
	}
	/**
	 * 打开学生注册窗口
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月28日19:49:09
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add() {
		return "student/add";
	}
	/**
	 * 添加学生
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月28日19:49:09
	 */
	@RequestMapping(value="/studentReg",method=RequestMethod.POST)
	public String studentReg(Model model,Student student,HttpSession session) {
		StudentService service=new StudentService();
		boolean bol=service.studentReg(student,session);
		if (bol) {
			return "redirect:login_user.jsp";
		}else {
		    model.addAttribute("message", "系统错误请重新输入");//将取出的数据存放到model中
			return "forward:login_user.jsp";
		}
	}
	/**
	 * 打开新增学生窗口
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月3日16:02:15
	 */
	@RequestMapping(value="/add2",method=RequestMethod.GET)
	public String add2() {
		return "student/add2";
	}
	/**
	 * 添加学生
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月28日19:49:09
	 */
	@RequestMapping(value="/studentAdd",method=RequestMethod.POST)
	public String studentAdd(Model model,Student student,HttpSession session) {
		StudentService service=new StudentService();
		boolean bol=service.studentReg(student,session);
		if (bol) {
			return "redirect:/queryStuInf";//重定向到查询页面
		}else {
		    model.addAttribute("message", "系统错误请重新输入");//将取出的数据存放到model中
			return "redirect:/queryStuInf";
		}
	}
	/**
	 * 学生登录
	 * @param student,stu_num
	 * @return
	 * @author 潘盛武    
	 * @date 2017年11月28日19:49:09
	 */
	@RequestMapping(value="/studentLogin",method=RequestMethod.POST)
	public String studentLogin(Model model,@RequestParam("stu_num") String stu_num,Student student,HttpSession session) {
		StudentService service=new StudentService();
		student=service.loginUser(student,session);
		if (student!=null) {
			String stuName=student.getStu_name();
			session.setAttribute("stu_num", stu_num);//登录的学号
			session.setAttribute("stuName", stuName);//学生姓名
			System.out.println("学号:"+stu_num+"姓名:"+stuName);
			return "redirect:main_stu.jsp";
		}else {
		    model.addAttribute("message", "学号或者密码错误");//将取出的数据存放到model中
			return "forward:login_users.jsp";
		}
	}
	
	/**
	 * 学生学号注册验证
	 * @param student,stu_num
	 * @return
	 * @ResponseBody 是ajax方式提交数据必须的注解
	 * @author 潘盛武 
	 * @date 2017年11月28日19:49:09
	 */
	@RequestMapping(value="/validateStuNum")
	@ResponseBody
	public  Map<String,Object>  validateStuNum(Model model,Student student,
			@RequestParam("stu_num") String stu_num) {
		System.out.println("传入的参数："+stu_num);  
		 Map<String,Object> map = new HashMap<String,Object>();  
		StudentService service=new StudentService();
		student=service.validateStuNum(student);
		 map.put("student", student); //student对应的是ajax的student
		 return map;  
	}
	
	/**
	 * 根据id删除学生信息
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月2日20:06:57
	 */
	@RequestMapping(value="/delete/{stu_id}",method=RequestMethod.GET)
	public String delete(@PathVariable("stu_id") int stu_id,Student student,HttpSession session) {
		StudentService service=new StudentService();
		student.setStu_id(stu_id);
		service.deleteStuById(student,session);
		return "redirect:/queryStuInf";
	}
	/**
	 * 根据id绑定学生信息（用于修改） ---管理员修改
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月2日20:21:05
	 */
	@RequestMapping(value="/update/{stu_id}",method=RequestMethod.GET)
	public String update(@PathVariable("stu_id") int stu_id,Model model,Student stu) {
		StudentService service=new StudentService();
		stu.setStu_id(stu_id);
		Student student=service.findStuInfById(stu);
		model.addAttribute("student",student);
		return "student/updateStu";
	}
	/**
	 * 修改学生信息（密码，电话，邮箱） ---管理员修改
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月2日20:28:53
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(Student student,@RequestParam("confirm_psw") String confirm_psw,HttpSession session) {
		StudentService service=new StudentService();
		student.setPsw(confirm_psw);
		service.updateStuInf(student,session);
		return "redirect:/queryStuInf";
	}
	
	/**
	 * 根据id绑定学生信息（用于修改） ---学生登录修改自己的信息
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月4日21:13:00
	 */
	@RequestMapping(value="/updateByMy/{stu_id}",method=RequestMethod.GET)
	public String updateByMy(@PathVariable("stu_id") int stu_id,Model model,Student stu) {
		StudentService service=new StudentService();
		stu.setStu_id(stu_id);
		Student student=service.findStuInfById(stu);
		model.addAttribute("student",student);
		return "student/updateByMy";
	}
	/**
	 * 修改学生信息（密码，电话，邮箱） ---学生登录修改自己的信息
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月4日21:12:57
	 */
	@RequestMapping(value="/updateByMy",method=RequestMethod.POST)
	public String updateByMy(Student student,@RequestParam("confirm_psw") String confirm_psw,HttpSession session) {
		StudentService service=new StudentService();
		student.setPsw(confirm_psw);//要修改的密码
		service.updateStuInf(student,session);
		return "redirect:/queryMyMsg";
	}
	/**
	 *学生信息修改  ajax 验证（登录的账号密码验证）
	 * @param student,stu_num,psw
	 * @return
	 * @author 潘盛武    
	 * @date 2017年12月24日20:00:07
	 */
	@RequestMapping(value="/updateMyInfValidate")
	@ResponseBody
	public Map<String, Object> updateMyInfValidate(Model model,
			@RequestParam("psw") String psw,Student student,HttpSession session) {
		Map<String, Object> map=new HashMap<String, Object>();
		StudentService service=new StudentService();
		String stu_num=(String) session.getAttribute("stu_num");//通过session值获取登录的学号
		student.setStu_num(stu_num);student.setPsw(psw);
		student=service.loginUser(student,session);
		map.put("student", student);//将整个对象传递过去判断输入的密码是否正确
		return map;
	}
	
}
