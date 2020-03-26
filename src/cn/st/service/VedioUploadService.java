package cn.st.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.st.dao.ManipulateLogDao;
import cn.st.dao.VedioUploadDao;
import cn.st.entity.ManipulateLog;
import cn.st.entity.VedioUpload;
import cn.st.query.Page;

public class VedioUploadService {
    
	//实例化视频上传信息dao
	VedioUploadDao dao=new VedioUploadDao();
	
	/**
	 * 视频上传 （添加视频上传信息）
	 * @param vedioUpload
	 * @author 潘盛武
	 * @date 2017年11月27日22:31:08
	 */
	public void addVedioInf(VedioUpload vedioUpload,HttpServletRequest request){
		ManipulateLogDao logDao=new ManipulateLogDao();
	    String userName=(String) request.getSession().getAttribute("name");
	    String vedioTitle=vedioUpload.getTitle();
		ManipulateLog manipulateLog=new ManipulateLog();
		SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date2=new Date();
    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
		if (vedioUpload!=null) {
			dao.addVedioInf(vedioUpload);
			manipulateLog.setUserName("操作人:"+userName);
			manipulateLog.setMpDescribe("上传了视频，标题为："+vedioTitle+"状态:上传成功！");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
			
		}
		
	}
	/**
	 * 查询所有的视频信息
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:32:08
	 */
	public List<VedioUpload> queryVedioAllInf(){
		return dao.queryVedioAllInf();
	}
	/**
	 * 视频信息查询（根据标题和视频类型查询）
	 * Page<VedioUpload> 泛型
	 * * @author 潘盛武
	 * @date 2017年11月27日22:32:08
	 */
	public Page<VedioUpload> queryByTittleAndType(String title,Integer type_id,Page<VedioUpload> page){
		Page<VedioUpload> pageResult=dao.queryByTittleAndType(title, type_id, page);
		pageResult.setTotal(dao.countDate(title, type_id));
		return pageResult;
	}
	
	/**
	 * 找到上传视频的id值
	 * @param up_id
	 * @return
	 * @throws SQLException
	 * @author 潘盛武
	 * @date 2017年11月27日22:34:25
	 */
	public VedioUpload findById(int up_id){
		
		return dao.findById(up_id);
	}
	
	/**
	 * 删除视频资源
	 * @param up_id
	 * @author 潘盛武
	 * @date 2017年11月27日22:34:25
	 */
	public void deleteVedioById(int up_id,HttpSession session) {
		VedioUpload vedioUpload=dao.findById(up_id);
		String title=vedioUpload.getTitle();
		ManipulateLogDao logDao=new ManipulateLogDao();
	    String userName=(String) session.getAttribute("name");
		ManipulateLog manipulateLog=new ManipulateLog();
		SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date2=new Date();
    	String ceateTime2=sdfss.format(date2);//获取系统时间时间转字符串
		if (up_id>0) {
			dao.deleteVedioById(up_id);
			manipulateLog.setUserName("操作人:"+userName);
			manipulateLog.setMpDescribe("删除了视频，视频标题为："+title+"状态:删除成功！");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}else {
			manipulateLog.setUserName("操作人:"+userName);
			manipulateLog.setMpDescribe("删除了视频，视频标题为："+title+"状态:删除失败！");
			manipulateLog.setCreateTime(ceateTime2);
			logDao.addManipulateLog(manipulateLog);
		}
		
	}
	
	/**
	 * 查看视频根据up_id
	 * @param up_id
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:35:35
	 */
	public List<VedioUpload> queryVedioByup_id(int up_id) {
		return dao.queryVedioByup_id(up_id);
	}
	/**
	 * 初始化信息  只显示最新的6条信息 按时间降序排序 显示最新的视频
	 * @param 
	 * @return  
	 * @author 潘盛武
	 * @date 2017年12月19日19:53:27
	 */
	public List<VedioUpload> initVedios(){
		return dao.initVedios();
	}
}
