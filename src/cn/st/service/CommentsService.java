package cn.st.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import cn.st.dao.CommentsDao;
import cn.st.dao.ManipulateLogDao;
import cn.st.entity.Comments;
import cn.st.entity.ManipulateLog;
import cn.st.query.Page;

public class CommentsService {
	
	CommentsDao dao=new CommentsDao();
	/**
     * 留言
     * @param comments
     * @author 潘盛武
	 * @date 2017年12月6日19:28:37
     */
    public boolean leaveMessage(Comments comments,HttpSession session){
    	boolean bol=false;
    	String stuName=(String) session.getAttribute("stuName");//获取登录学生 stu_name
    	Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ceateTime=sdf.format(date);//获取系统时间时间转字符串
		String ceateTime2=sdfss.format(date);//获取系统时间时间转字符串
		comments.setCeateTime(ceateTime);
		comments.setContentName(stuName);
		comments.setState("未审核");//默认未审核状态
		String title=comments.getC_title();
    	bol=dao.leaveMessage(comments);
    	 //日志管理类
	    ManipulateLogDao logDao=new ManipulateLogDao();
		ManipulateLog manipulateLog=new ManipulateLog();
		if (bol) {
			manipulateLog.setUserName("操作人:"+stuName);
			manipulateLog.setMpDescribe("学生:"+stuName+"发表了标题为"+title+"的留言信息");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}
    	return bol;
    }
    /**
     * 根据c_id找到评论的信息
     * @param c_id
     * @return
     * @author 潘盛武
	 * @date 2017年12月6日19:29:14
     */
	public Comments findById(long c_id ){
		return dao.findById(c_id);
	}
	/**
	 * 回复留言
	 * @param comments
	 *  @author 潘盛武
	 * @date 2017年12月6日19:29:55
	 */
	public boolean replyContent(Comments  comments,HttpSession session){
		boolean bol=false;
		String name=(String) session.getAttribute("name");//获取登录的管理员
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String ceateTime=sdf.format(date);//获取系统时间时间转字符串
		comments.setrDateTime(ceateTime);
		comments.setrContentName(name);
		comments.setState("已审核");//修改为审核状态
		bol=dao.replyContent(comments);
	    String rContent=comments.getrContent();
		//日志管理类
	    ManipulateLogDao logDao=new ManipulateLogDao();
		ManipulateLog manipulateLog=new ManipulateLog();
		SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
    	String ceateTime2=sdfss.format(date);//获取系统时间时间转字符串
		if (bol) {
			manipulateLog.setUserName("操作人:"+name);
			manipulateLog.setMpDescribe("回复审核了留言:回复内容为:"+rContent+"状态已审核");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}
		return bol;
	}
	/**
	 * 删除留言
	 * @param ad_id
	 * @author 潘盛武
	 * @date 2017年12月6日19:31:28
	 */
	public boolean deleteMessage(long c_id,HttpSession session){
		boolean bol=false;
		//日志管理类
	    ManipulateLogDao logDao=new ManipulateLogDao();
		String name=(String) session.getAttribute("name");
		ManipulateLog manipulateLog=new ManipulateLog();
		SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
    	String ceateTime=sdfss.format(date);//获取系统时间时间转字符串
    	Comments comments=dao.findById(c_id);
    	String tiltle=comments.getC_title();
		bol=dao.deleteMessage(c_id);
		if (bol) {
			manipulateLog.setUserName("操作人:"+name);
			manipulateLog.setMpDescribe("删除了留言:标题为"+tiltle+",的留言信息");
			manipulateLog.setCreateTime(ceateTime);
			logDao.addManipulateLog(manipulateLog);
		}
		return bol;
	}
	/**
	 * 查询 留言信息 分页 根据标题查询  游客查询审核通过的留言信息
	 * @param page
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月5日22:55:32
	 */
	public Page<Comments> queryPage(Page<Comments> page,String c_title){
		Page<Comments> qPage=dao.queryPage(page, c_title);
		 qPage.setTotal(dao.countContentApproved(c_title));
		 return qPage;
	}
	   /**
		 * 查询 留言信息 分页  根据标题查询
		 * 管理员根据学号查询留言信息进行审核审核通过的留言
		 * 信息才能显示在学生端的留言信息上。
		 * @param page
		 * @return
		 * @author 潘盛武
		 * @date 2017年12月7日19:29:49
		 */
		public Page<Comments> queryPageMG(Page<Comments> page,String c_title ){
			Page<Comments> qPage=dao.queryPageMG(page, c_title);
			 qPage.setTotal(dao.countrContent(c_title));
			 return qPage;
		}
		
		/**
		 * 学生只能查看自己的留言信息
		 * @param page
		 * @return
		 * @author 潘盛武
		 * @date 2017年12月16日22:32:17
		 */
		public List<Comments> queryMyMessages(String contentName){
			return dao.queryMyMessages(contentName);
		}
		/**
		 * 初始化留言信息  只显示最新的5条信息 降序排序。
		 * @return
		 * @author 潘盛武
		 * @date 2017年12月18日23:29:28
		 */
		public List<Comments> initComments(){
			return dao.initComments();
		}
}
