package cn.st.web;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.st.dao.ManipulateLogDao;
import cn.st.entity.Admin;
import cn.st.entity.ManipulateLog;
import cn.st.service.AdminService;

@Controller
public class Login_MgController {
	
	@Resource private AdminService  adminService;
	
	/**
	 *  管理员登录
	 * @param model
	 * @param loginname
	 * @param psw
	 * @param mglevel
	 * @return
	 * @date 2017年11月26日20:11:46
	 * @author 潘盛武
	 * forward与redirect的区别 forward数据转发可转发数据redirect重定向无法转发数据
	 */
	@RequestMapping(value="/loginAdmin",method=RequestMethod.POST)
	public String loginAdmin(Model model,@RequestParam("loginname") 
	String loginname,@RequestParam("psw") String psw,
	@RequestParam("mglevel") String mglevel,HttpServletRequest request,HttpSession session){
		Admin admin=new Admin();
		admin.setLoginname(loginname);
		admin.setPwd(psw);
		admin.setAdminLevel(mglevel);
		Admin datas =adminService.loginUser(admin);
       if(datas!=null) {
    	   String name=datas.getName();//取出名称
    	   String adminLevel=datas.getAdminLevel();//取出级别
    	   if(adminLevel.equals("系统管理员")||adminLevel.equals("教师")){
    		   session.setAttribute("adminLevel", adminLevel);//将级别设置到session中用于判断管理权限
    		   session.setAttribute("name", name);
    		   session.setAttribute("loginname", loginname);
    		   
    		   //日志管理类
    		    ManipulateLogDao logDao=new ManipulateLogDao();
    			String level=admin.getAdminLevel();//管理级别
    			String userName=(String) session.getAttribute("name");
    			ManipulateLog manipulateLog=new ManipulateLog();
    			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			Date date=new Date();
    	    	String ceateTime=sdfss.format(date);//获取系统时间时间转字符串
    			manipulateLog.setUserName("操作人:"+userName);
    			manipulateLog.setMpDescribe("管理员:"+userName+",登录了本系统,管理级别:"+level);
    			manipulateLog.setCreateTime(ceateTime);
    			
    			logDao.addManipulateLog(manipulateLog);
    		   
    		   
    		   return "redirect:main_admin.jsp";//跳转到系统管理员界面
		   }else {
			   return "redirect:login_admin.jsp";//
		}}else{
  			 System.out.println("账号密码或者管理级别出错！！！");
  		     model.addAttribute("message", "密码账户或者管理级别错误");//将取出的数据存放到model中
  			 return "forward:login_admin.jsp";
  			}
	   }
	
	/**
	 * 退出系统
	 * @author 潘盛武
	 * @date 2018年1月3日20:10:55
	 */
	@RequestMapping(value="/quitSystem",method=RequestMethod.GET)
	public String quitSystem(HttpSession session) {
		session.invalidate();//退出系统使session值无效
		return "redirect:login_users.jsp";
	}
}
