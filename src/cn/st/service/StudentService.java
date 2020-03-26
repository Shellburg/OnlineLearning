package cn.st.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import cn.st.dao.ManipulateLogDao;
import cn.st.dao.StudentDao;
import cn.st.entity.ManipulateLog;
import cn.st.entity.Student;
import cn.st.query.Page;

public class StudentService {
    
	StudentDao dao=new StudentDao();
	/**
	 * 根据登录的学号只允许查看自己的信息
	 * @param loginname
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月4日20:17:45
	 */
	public List<Student> queryMyMsg(String stu_num){
		return dao.queryMyMsg(stu_num);
	}
	 /** 根据学号查询学生信息
	 * @param page
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月28日19:49:09
	 */
	public Page<Student> queryStuInf(Page<Student> page,String stu_name){
		Page<Student> pageResult=dao.queryStuInf(stu_name,page);
		pageResult.setTotal(dao.countDate(stu_name));
		return pageResult;
	}
	
	
	/**
	 * 学生注册
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月28日19:49:09
	 */
	public boolean  studentReg(Student student,HttpSession session) {
		boolean bol=false;
	    String stuName=student.getStu_name();
		bol=dao.studentReg(student);
		if (bol) {
			//日志管理类
		    ManipulateLogDao logDao=new ManipulateLogDao();
		    String userName=(String) session.getAttribute("name");
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2=new Date();
	    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			manipulateLog.setMpDescribe("添加了学生,姓名为："+stuName+"，的学生信息,状态:添加成功！");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}else {
			//日志管理类
		    ManipulateLogDao logDao=new ManipulateLogDao();
		    String userName=(String) session.getAttribute("name");
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2=new Date();
	    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			manipulateLog.setMpDescribe("添加了学生,姓名为："+stuName+"，的学生信息,状态:添加失败！");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}
		return bol;
	}
	
	/**
	 * 学生登录
	 * @param student
	 * @return
	 * @date 2017年11月28日21:18:40
	 * @author 潘盛武
	 */
	public Student loginUser(Student student,HttpSession session){
		
		Student stu=dao.loginUser(student);
		Student student2=dao.validateStuNum(student);
		String stuName=student2.getStu_name();
		
		System.out.println("stuName:"+stuName);
		//日志管理类
	    ManipulateLogDao logDao=new ManipulateLogDao();
		ManipulateLog manipulateLog=new ManipulateLog();
		SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date2=new Date();
    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
		if (stu!=null) {
			manipulateLog.setUserName("操作人:"+stuName);
			manipulateLog.setMpDescribe("登录了本系统！状态:登录成功");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}else {
			manipulateLog.setUserName("操作人:"+stuName);
			manipulateLog.setMpDescribe("登录了本系统！状态:登录失败");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}
		return stu;
	}
	
	/**
	 * 学生学号注册验证（只允许一个学号注册）
	 * @param student
	 * @return
	 * @date 2017年11月28日21:18:40
	 * @author 潘盛武
	 */
	public Student validateStuNum(Student student){
		return dao.validateStuNum(student);
	}
	/**
	 * 根据id删除学生信息
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月2日20:06:57
	 */
	public boolean deleteStuById(Student student,HttpSession session) {
		boolean bol=false;
	    Student stuName=dao.findStuInfById(student);
		bol=dao.deleteStuById(student);
		if (bol) {
			//日志管理类
		    ManipulateLogDao logDao=new ManipulateLogDao();
		    String userName=(String) session.getAttribute("name");
		    String stu=stuName.getStu_name();
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2=new Date();
	    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			manipulateLog.setMpDescribe("删除了学生,姓名为："+stu+"，的学生信息,状态:删除成功！");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}else {
			//日志管理类
		    ManipulateLogDao logDao=new ManipulateLogDao();
		    String userName=(String) session.getAttribute("name");
		    String stu=stuName.getStu_name();
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2=new Date();
	    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			manipulateLog.setMpDescribe("删除了学生,姓名为："+stu+"，的学生信息,状态:删除失败！");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}
		return bol;
	}
	/**
	 * 根据id绑定学生信息（用于修改）
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月2日20:21:05
	 */
	public Student findStuInfById(Student student){
		return dao.findStuInfById(student);
	}
	/**
	 * 修改学生信息（学号，姓名，性别系别，班级，密码，电话，邮箱）
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月2日20:28:53
	 */
	public boolean updateStuInf(Student student,HttpSession session) {
		boolean bol=false;
		bol=dao.updateStuInf(student);
		ManipulateLog manipulateLog=new ManipulateLog();
		//日志管理类
	    ManipulateLogDao logDao=new ManipulateLogDao();
	    SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date2=new Date();
		String stuName2=student.getStu_name();
		if (bol) {
			/*---------------------管理员修改学生信息-----------------------*/
		    String name=(String) session.getAttribute("name");//管理员登录修改
		    if (name!=null&&!name.equals("")) {
		    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
				manipulateLog.setUserName("操作人:"+name);
				manipulateLog.setMpDescribe("修改了学生信息,姓名为："+stuName2+",状态:修改成功！");
				manipulateLog.setCreateTime(ceateTime2);
				logDao.addManipulateLog(manipulateLog);
			}/*else {
				String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
				manipulateLog.setUserName("操作人:"+name);
				manipulateLog.setMpDescribe("修改了学生信息,姓名为："+stuName2+",状态:修改失败！");
				manipulateLog.setCreateTime(ceateTime2);
				logDao.addManipulateLog(manipulateLog);
			}*/
		    /*---------------------学生登录修改学生信息-----------------------*/
		    String stuName=(String) session.getAttribute("stuName");//学生登录自己修改
		    if (stuName!=null&&!stuName.equals("")) {
		    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
				manipulateLog.setUserName("操作人:"+stuName);
				manipulateLog.setMpDescribe("修改了学生信息,姓名为："+stuName2+",状态:修改成功！");
				manipulateLog.setCreateTime(ceateTime2);
				logDao.addManipulateLog(manipulateLog);
			}/*else {
				String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
				manipulateLog.setUserName("操作人:"+name);
				manipulateLog.setMpDescribe("修改了学生信息,姓名为："+stuName2+",状态:修改失败！");
				manipulateLog.setCreateTime(ceateTime2);
				logDao.addManipulateLog(manipulateLog);
			}*/
		}
		return bol;
	}
}
