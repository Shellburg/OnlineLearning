package cn.st.service;
import cn.st.dao.WatchRecordDao;
import cn.st.entity.WatchRecord;
public class WatchRecordService {
	WatchRecordDao dao=null;
	
	/**
	 *  添加观看记录
	 * @param watchRecord
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月2日10:53:45
	 */
	public boolean addWatchRecord(WatchRecord watchRecord){
		dao=new WatchRecordDao();
		//根据学生id(stu_id)和视频id(up_id)确定该学生观看过该视频
		WatchRecord watchRecord2=null;
		watchRecord2=dao.findBystu_idAndup_id(watchRecord);//
		boolean bol=false;
		//如果不没有观看过该视频则插入记录
		if (watchRecord2==null) {
			bol=dao.addWatchRecord(watchRecord);
			if (bol) {
				System.out.println("添加成功");
			}else{
				System.out.println("添加失败");
			}
		}else {
			System.out.println("该视频您已经观看过");
		}
		return bol;
	}
	/**
	 * 根据学生id(stu_id)和视频id(up_id)确定该学生观看过该视频
	 * @param watchRecord
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月2日10:56:40
	 */
   public WatchRecord findBystu_idAndup_id(WatchRecord watchRecord) {
	   dao=new WatchRecordDao();
	   return dao.findBystu_idAndup_id(watchRecord);
   }
}
