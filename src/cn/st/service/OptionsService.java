package cn.st.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import cn.st.dao.ManipulateLogDao;
import cn.st.dao.OptionsDao;
import cn.st.entity.ManipulateLog;
import cn.st.entity.Options;
import cn.st.query.Page;

public class OptionsService {
	OptionsDao dao=new OptionsDao();
	
	/**
	 * 添加选择题(导入选择题)
	 * @param options
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:38:54
	 */
	public boolean addOptions(Options options,HttpSession session){
		//日志管理类
	    ManipulateLogDao logDao=new ManipulateLogDao();
		boolean bol=false;
		bol=dao.addOptions(options);
		String questions=options.getQuestion();
		if (bol) {
			String userName=(String) session.getAttribute("name");
			
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2=new Date();
	    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			manipulateLog.setMpDescribe("成功添加了问题为:"+questions+"的选择题！");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}else {
			String userName=(String) session.getAttribute("name");
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2=new Date();
	    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			manipulateLog.setMpDescribe("成功添加了问题为:"+questions+"的选择题！");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}
		return bol;
	}	

	/**
	 * 查看选择题
	 * @param page
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:39:40
	 */
	public Page<Options> queryAllOptionIng(Page<Options> page){
		
		 Page<Options> pageResult=dao.queryAllOptionIng(page);
	     pageResult.setTotal(dao.countOptions());
	     
		return pageResult;
	}
	
	/**
	 * 查看选择题 根据up_id
	 * @param up_id
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:39:40
	 */
	public List<Options> queryAllOptionIngByup_id(int up_id){
	     
		return dao.queryAllOptionIngByup_id(up_id);
	}
	
	/**
	 * 视频习题测试验证（计算结果）
	 * @param answer
	 * @param up_id
	 * @author 潘盛武
	 * @date 2017年11月27日22:43:46
	 */
	public int testRecords(String[] answer,int up_id) {
		List<Options> options=dao.queryAllOptionIngByup_id(up_id);//查询正确答案
		int count=0;//统计答对的题目
		int i;
	    for(i=0; i < answer.length; i++){
	    	//比较选择的单选框的值和数据库查询的正确答案比较如正确则加1
			if (options.get(i).getOp_answer().equals(answer[i])) {
				count++;
				System.out.println("选中："+answer[i]+"正确答案是："+options.get(i).getOp_answer());
			}
			else {
				System.out.println("回答错误");
			}
		}
		 return count;//
	}
	/**
	 * 根据id找到对应的选择题
	 * @param options
	 * @author 潘盛武
	 * @date 2017年12月10日18:09:56
	 */
	public Options findById(int op_id){
		return dao.findById(op_id);
	}
	/**
	 * 修改选择题信息
	 * @param options
	 * @author 潘盛武
	 * @date 2017年12月10日18:09:56
	 */
	public boolean updateOp(Options options,HttpSession session){
		boolean bol=false;
		bol=dao.updateOp(options);
		if (bol) {
			//日志管理类
		    ManipulateLogDao logDao=new ManipulateLogDao();
		    String userName=(String) session.getAttribute("name");
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2=new Date();
	    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			int id=options.getOp_id();
			manipulateLog.setMpDescribe("修改了题号为:"+id+"的选择题！状态:修改成功！");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		} else {
			//日志管理类
		    ManipulateLogDao logDao=new ManipulateLogDao();
		    String userName=(String) session.getAttribute("name");
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2=new Date();
	    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			int id=options.getOp_id();
			manipulateLog.setMpDescribe("修改了题号为:"+id+"的选择题！状态:修改失败！");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}
		
		return bol;
	}
	/**
	 * 删除选择题
	 * @param op_id
	 * @author 潘盛武
	 * @date 2017年12月10日18:16:23
	 */
	public boolean deleteOp(Options options,HttpSession session) {
		boolean bol=false;
		bol=dao.deleteOp(options);
		if (bol) {
			//日志管理类
		    ManipulateLogDao logDao=new ManipulateLogDao();
		    String userName=(String) session.getAttribute("name");
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2=new Date();
	    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			int id=options.getOp_id();
			manipulateLog.setMpDescribe("删除了题号为:"+id+"的选择题！状态:删除成功！");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		} else {
			//日志管理类
		    ManipulateLogDao logDao=new ManipulateLogDao();
		    String userName=(String) session.getAttribute("name");
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date2=new Date();
	    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			int id=options.getOp_id();
			manipulateLog.setMpDescribe("删除了题号为:"+id+"的选择题！状态:删除失败！");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}
		return bol;
	}
}
