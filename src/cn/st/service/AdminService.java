package cn.st.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import cn.st.dao.AdminDao;
import cn.st.dao.ManipulateLogDao;
import cn.st.entity.Admin;
import cn.st.entity.ManipulateLog;
import cn.st.query.Page;
@Service("adminService")
public class AdminService {
	/**
	 * spring的注解
	 *  @Component 是一个泛化的概念，仅仅表示一个组件 (Bean) ，可以作用在任何层次。
	    @Service 通常作用在业务层，但是目前该功能与 @Component 相同。
	    @Constroller 通常作用在控制层，但是目前该功能与 @Component 相同。
	 */
	
	@Resource private AdminDao adminDao;
	/**
	 * 查询 我的信息
	 * @param name
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日21:05:56
	 */
	public List<Admin> queryMyMsg(String name)  {
		return adminDao.queryMyMsg(name);
	}
	/**
	 * 查询 管理员信息 分页
	 * @param page
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:23:14
	 */
	public Page<Admin> queryPage(Page<Admin> page) {
       Page<Admin> pageResult=adminDao.queryPage(page);
       pageResult.setTotal(adminDao.countrAdmin());
		return pageResult;
	}
	/**
     * 添加管理员
     * @param admin
     * @author 潘盛武
	 * @date 2017年11月27日22:23:14
     */
	public void save(Admin admin,HttpSession session) {
		String name=admin.getName();
		String level=admin.getAdminLevel();//管理级别
		if (admin!=null) {
			adminDao.save(admin);
			//日志管理类
		    ManipulateLogDao logDao=new ManipulateLogDao();
		    String userName=(String) session.getAttribute("name");
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2=new Date();
	    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			manipulateLog.setMpDescribe("添加了管理员,姓名为："+name+",管理级别为"+level+",状态:添加成功！");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		} 
	}
	/**
	/**
     * 根据ad_id绑定管理员的信息
     * @param id
     * @return
     * @author 潘盛武
	 * @date 2017年11月27日22:23:14
     */
	public Admin findById(long id) {
		return adminDao.findById(id);
	}
	/**
	 * 修改管理员信息
	 * @param admin
	 *  @author 潘盛武
	 * @date 2017年11月27日22:23:14
	 */
	public void update(Admin admin ,HttpSession session) {
		Admin admin2=adminDao.findById(admin.getAd_id());
		String name=admin2.getName();
		String userName=(String) session.getAttribute("name");
		ManipulateLog manipulateLog=new ManipulateLog();
		ManipulateLogDao logDao=new ManipulateLogDao();
		SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
    	String ceateTime=sdfss.format(date);//获取系统时间时间转字符串
		if (admin!=null) {
			adminDao.update(admin);
			manipulateLog.setUserName("操作人:"+userName);
			manipulateLog.setMpDescribe("修改了管理员:"+name+",的信息:");
			manipulateLog.setCreateTime(ceateTime);
			logDao.addManipulateLog(manipulateLog);
		} 
		
		
	}
	/**
	 * 删除管理员
	 * @param ad_id
	 * @author 潘盛武
	 * @date 2017年11月27日22:23:14
	 */
	public void delete(long ad_id,HttpSession session) {
		Admin admin2=adminDao.findById(ad_id);
		String name=admin2.getName();
		String level=admin2.getAdminLevel();
		String userName=(String) session.getAttribute("name");
		ManipulateLog manipulateLog=new ManipulateLog();
		ManipulateLogDao logDao=new ManipulateLogDao();
		SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
    	String ceateTime=sdfss.format(date);//获取系统时间时间转字符串
		if (ad_id>0) {
			adminDao.delete(ad_id);
			manipulateLog.setUserName("操作人:"+userName);
			manipulateLog.setMpDescribe("删除了管理员:"+name+"级别为:"+level);
			manipulateLog.setCreateTime(ceateTime);
			logDao.addManipulateLog(manipulateLog);
		} 
		
	}
	
	/**
	 * 管理员登录
	 * @param admin
	 * @return
	 * @date 2017年11月26日20:11:46
	 * @author 潘盛武
	 */
	public Admin loginUser(Admin admin) {
		return adminDao.loginUser(admin);
	}
	/**
	 * 修改我的信息          ---教师修改自己的信息
	 * @param admin
	 *  @author 潘盛武
	 * @date 2017年12月26日20:27:40
	 */
	public void updateByteacher(Admin admin) {
		adminDao.updateByteacher(admin);
	}
	/**
	 *异步刷新验证教师根据登录的登录名和输入的密码比对验证是否是本人修改
	 * @param admin
	 * @return
	 * @date 2017年12月26日20:31:08
	 * @author 潘盛武
	 */
	public Admin updateMyInfValidate(Admin admin) {
		return adminDao.updateMyInfValidate(admin);
	}
}
