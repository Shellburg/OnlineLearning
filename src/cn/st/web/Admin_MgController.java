package cn.st.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.st.entity.Admin;
import cn.st.query.Page;
import cn.st.service.AdminService;

@Controller
@RequestMapping(value="admin")
public class Admin_MgController {
	@Resource private AdminService  adminService;
	/**
	 * 查询 我的信息
	 * @param loginname
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日21:05:56
	 */
	@RequestMapping(value="myList",method=RequestMethod.GET)
	public String myList(Model model,String loginname,HttpServletRequest req){
		
		//获取登陆的用户名字信息然后根据用户登录名只能查询自己的信息
		String name=(String) req.getSession().getAttribute("name");
		List<Admin> mymsg=adminService.queryMyMsg(name);
		model.addAttribute("mymsg",mymsg);
		return "admin/myList";
	}
    /**
     * 查看管理员信息	 
     * @param model
     * @param currentPage
     * @return
     * @author 潘盛武
	 * @date 2017年11月27日21:05:56
     */
	@RequestMapping
	public String list(Model model,String currentPage,String name,HttpServletRequest req) {
		Page<Admin> pagev=new Page<Admin>();
		pagev.setCurrentPage(currentPage==null? 1 :Integer.parseInt(currentPage));
		Page<Admin> page=adminService.queryPage(pagev);
		name=(String) req.getSession().getAttribute("name");//名称
		model.addAttribute("page",page);
		return "admin/list";
	}
	/**
	 * 打开新增管理员窗口
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日21:05:56
	 */
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add() {
		return "admin/add";
	}
	/**
	 * 保存管理员信息
	 * @param admin
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日21:05:56
	 */
	@RequestMapping(value="save")
	public String  save(Admin admin,HttpSession session) {
		adminService.save(admin,session);
		//新增管理员后实现重定向
		return "redirect:/admin";
	}
	
	/**
	 * 点击修改时根据id来绑定数据(绑定管理员信息) 打开窗口用get
	 * 如果只是点连接的的话用method = RequestMethod.get
	 * 一般查询服务的时候用get，向后台提交数据时用post，如注册等等。
	 * 这个说白了就是http里面get和post的差别了，post安全点，提交的数据多点，
	 * 数据是放在http头信息里面的，get的参数是在URL后面的。
	 */
	/**
	 /**
     * 根据ad_id绑定管理员的信息   ----管理员修改教师的信息
     * @param ad_id
     * @return
     * @author 潘盛武
	 * @date 2017年11月27日22:23:14
     */
	@RequestMapping(value="update/{ad_id}",method=RequestMethod.GET)
	public String update(@PathVariable("ad_id") long ad_id,Model model) {
		Admin admin=adminService.findById(ad_id);
		model.addAttribute("admin",admin);
		return "admin/update";
	}
	/**
	 * 修改管理员信息   ----管理员修改教师的信息
	 * @param admin
	 *  @author 潘盛武
	 * @date 2017年11月27日22:23:14
	 */
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String update(Admin admin,@RequestParam("confirm_psw") String confirm_psw,HttpSession session) {
		admin.setPwd(confirm_psw);
		adminService.update(admin,session);
		return "redirect:/admin";
	}
	/**
	 * 删除管理员
	 * @param ad_id
	 * @author 潘盛武
	 * @date 2017年11月27日22:23:14
	 */
	@RequestMapping(value="delete/{ad_id}",method=RequestMethod.GET)
	public String delete(@PathVariable("ad_id")long ad_id,HttpSession session) {
		adminService.delete(ad_id,session);
		return "redirect:/admin";
	}
	
	/**
     * 根据ad_id绑定管理员的信息  --教师自己修改自己的信息
     * @param ad_id
     * @return
     * @author 潘盛武
	 * @date 2017年12月26日20:14:40
     */
	@RequestMapping(value="findByteacher/{ad_id}",method=RequestMethod.GET)
	public String findByteacher(@PathVariable("ad_id") long ad_id,Model model) {
		Admin admin=adminService.findById(ad_id);
		model.addAttribute("admin",admin);
		return "admin/updateByMy";//跳转到教师修改页面
	}
	/**
	 * 修改管理员信息   --教师自己修改自己的信息
	 * @param admin
	 * @author 潘盛武
	 * @date 2017年12月26日20:14:40
	 */
	@RequestMapping(value="updateByteacher",method=RequestMethod.POST)
	public String updateByteacher(Admin admin,@RequestParam("confirm_psw") String confirm_psw) {
		//AdminService service=new AdminService();
		admin.setPwd(confirm_psw);
		adminService.updateByteacher(admin);
		return "redirect:/admin/myList";//重定向到教师信息页面
	}
	/**
	 * ajax 异步刷新验证教师根据登录的登录名和输入的密码比对验证是否是本人修改
	 * @ResponseBody是ajax必要的注解
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月26日20:14:40
	 */
	@RequestMapping(value="updateMyInfValidate")
	@ResponseBody
	public Map<String, Object> updateMyInfValidate(
			Admin admin,HttpSession session,@RequestParam("pwd") String pwd) {
		Map<String, Object> map=new HashMap<String, Object> ();
		String loginname=(String) session.getAttribute("loginname");
		admin.setPwd(pwd);admin.setLoginname(loginname);
	    admin =adminService.updateMyInfValidate(admin);
		map.put("admin", admin);
		return map;
	}
}
