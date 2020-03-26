package cn.st.service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import cn.st.dao.DataDao;
import cn.st.dao.ManipulateLogDao;
import cn.st.entity.DataOp;
import cn.st.entity.ManipulateLog;
import cn.st.query.Page;
public class DataService {
  DataDao dao=new DataDao();
	/**
	 * 上传资料
	 * @param dataOp
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月12日22:33:50
	 */
	public boolean uploadData(DataOp dataOp,HttpSession session) {
		boolean bol=false;
		bol=dao.uploadData(dataOp);
		//日志管理类
	    ManipulateLogDao logDao=new ManipulateLogDao();
		if (bol) {
			String userName=(String) session.getAttribute("name");
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date=new Date();
	    	String ceateTime=sdfss.format(date);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			String fileName=dataOp.getFile_name();
			manipulateLog.setMpDescribe("上传了资料名称为:"+fileName+"上传成功");
			manipulateLog.setCreateTime(ceateTime);
			logDao.addManipulateLog(manipulateLog);
		}else {
			String userName=(String) session.getAttribute("name");
			ManipulateLog manipulateLog=new ManipulateLog();
			SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date=new Date();
	    	String ceateTime=sdfss.format(date);//获取系统时间时间转字符串
			manipulateLog.setUserName("操作人:"+userName);
			String fileName=dataOp.getFile_name();
			manipulateLog.setMpDescribe("上传了资料名称为:"+fileName+"上传失败");
			manipulateLog.setCreateTime(ceateTime);
			logDao.addManipulateLog(manipulateLog);
			bol=false;
		}
		
		return bol;
	}
	
	/**
	 * 查询资料 根据类型查询
	 * @param data_type
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月12日22:59:44
	 */
	public Page<DataOp> queryDataByType(Page<DataOp> page,String data_type,String file_name) {
		Page<DataOp> pagev=dao.queryDataByType(page, data_type,file_name);
		pagev.setTotal(dao.countDate(data_type,file_name));
		return pagev;
	}
	
	/**
	 * 根据id删除资料信息
	 * @param dataOp
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月12日23:07:53
	 */
	public boolean deleteDataById(DataOp dataOp,HttpSession session) {
		boolean bol=false;
		DataOp daOp=dao.findDataInfById(dataOp);
		String fileName=daOp.getFile_name();
		String fileName2=dataOp.getFile_name();
		System.out.println("fileName"+fileName+",fileName2"+fileName2);
		//日志管理类
	    ManipulateLogDao logDao=new ManipulateLogDao();
		bol=dao.deleteDataById(dataOp);
		String userName=(String) session.getAttribute("name");
		ManipulateLog manipulateLog=new ManipulateLog();
		SimpleDateFormat sdfss=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
    	String ceateTime=sdfss.format(date);//获取系统时间时间转字符串
		if (bol) {
			manipulateLog.setUserName("操作人:"+userName);
			manipulateLog.setMpDescribe("删除了资料名称为:"+fileName2+"删除成功！");
			manipulateLog.setCreateTime(ceateTime);
			logDao.addManipulateLog(manipulateLog);
		}else {
			manipulateLog.setUserName("操作人:"+userName);
			manipulateLog.setMpDescribe("删除了资料名称为:"+fileName2+"删除失败！");
			manipulateLog.setCreateTime(ceateTime);
			logDao.addManipulateLog(manipulateLog);
		}
		
		return bol;
	}
	/**
	 * 根据id绑定资料信息（用于修改）
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月2日20:21:05
	 */
	public DataOp findDataInfById(DataOp dataOp) {
		return dao.findDataInfById(dataOp);
	}
	/**
	 * 修改资料信息
	 * @param dataOp
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月2日20:28:53  
	 */
	public boolean updateDataInf(DataOp dataOp) {
		return dao.updateDataInf(dataOp);
	}
	/**
	 * 更新下载次数
	 * @param dataOp
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月17日17:31:26
	 */
	public boolean updateDownloadCount(DataOp dataOp){
		return dao.updateDownloadCount(dataOp);
	}
	
	/**
	 * 根据id找到下载次数
	 * @param dataOp
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月18日19:23:28
	 */
	public List<DataOp>  CountDowloadById(DataOp dataOp) {
		return dao.CountDowloadById(dataOp);
	}
}
