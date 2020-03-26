package cn.st.service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import cn.st.dao.ManipulateLogDao;
import cn.st.dao.NoticeDao;
import cn.st.entity.ManipulateLog;
import cn.st.entity.Notice;
import cn.st.query.Page;
public class NoticeService {

    NoticeDao dao=new NoticeDao();	
	/**
	 * 查询 公告信息 分页
	 * @param page
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月11日23:00:33
	 */
	public Page<Notice> queryPage(Page<Notice> page)  {
		 Page<Notice>  pagev=dao.queryPage(page);
		 pagev.setTotal(dao.countNotice());
		return page;
	}
    /**
     * 发布公告
     * @param notice
     * @author 潘盛武
	 * @date 2017年12月11日23:03:26
     */
    public boolean pubNotice(Notice notice,HttpSession session) {
    	boolean bol=false;
    	//日志管理类
	    ManipulateLogDao logDao=new ManipulateLogDao();
    	String name=(String) session.getAttribute("name");//获取登录的管理员的名字
    	Date date=new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String ceateTime=sdf.format(date);//获取系统时间时间转字符串
		notice.setPub_name(name);
		notice.setCreate_time(ceateTime);
		bol=dao.pubNotice(notice);
		if (bol) {
			String userName=(String) session.getAttribute("name");
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2=new Date();
	    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			String title=notice.getNotice_tiltle();
			manipulateLog.setMpDescribe("发布了公告标题为:"+title+"发布成功");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}else {
			String userName=(String) session.getAttribute("name");
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2=new Date();
	    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			String title=notice.getNotice_tiltle();
			manipulateLog.setMpDescribe("发布了公告标题为:"+title+"发布成功");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}
		
    	return bol;
	}
    /**
     * 根据notice_id绑定公告的信息
     * @param notice_id
     * @return
     * @author 潘盛武
	 * @date 2017年12月11日23:05:20
     */
	public Notice findNoticeById(int notice_id ) {
		
		return dao.findNoticeById(notice_id);
	}
	
	/**
	 * 修改公告信息
	 * @param admin
	 * @author 潘盛武
	 * @date 2017年12月11日23:08:15
	 */
	public boolean updateNotice(Notice notice,HttpSession session) {
		//日志管理类
	    ManipulateLogDao logDao=new ManipulateLogDao();
		String name=(String) session.getAttribute("name");//获取登录的管理员的名字
    	Date date=new Date();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String ceateTime=sdf.format(date);//获取系统时间时间转字符串
		notice.setPub_name(name);
		notice.setCreate_time(ceateTime);
		
		boolean bol=false;
		bol=dao.updateNotice(notice);
		if (bol) {
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2=new Date();
	    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+name);
			String title=notice.getNotice_tiltle();
			manipulateLog.setMpDescribe("修改公告标题为:"+title+"修改成功");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}else {
			String userName=(String) session.getAttribute("name");
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2=new Date();
	    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			String title=notice.getNotice_tiltle();
			manipulateLog.setMpDescribe("修改公告标题为:"+title+"修改失败");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}
		
		return bol;
	}
	
	/**
	 * 删除通告信息
	 * @param notice
	 * @author 潘盛武
	 * @date 2017年12月11日23:13:13
	 */
	public boolean deleteNoticeById(Notice notice,HttpSession session) {
		//日志管理类
	    ManipulateLogDao logDao=new ManipulateLogDao();
		boolean bol=false;
		Notice notice2= dao.findNoticeById(notice.getNotice_id());
		bol=dao.deleteNoticeById(notice);
		if (bol) {
			String userName=(String) session.getAttribute("name");
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2=new Date();
	    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			String title=notice2.getNotice_tiltle();
			manipulateLog.setMpDescribe("删除了公告标题为:"+title+"删除成功！");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}else {
			String userName=(String) session.getAttribute("name");
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2=new Date();
	    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			String title=notice2.getNotice_tiltle();
			manipulateLog.setMpDescribe("删除了公告标题为:"+title+"删除失败！");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}
		return bol;
	}
	
	/**
	 * 初始化公告信息  只显示最新的5条信息 降序排序。
	 * @param page
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月18日21:29:52
	 */
	public List<Notice> initNotice(){
		return dao.initNotice();
	}
}
