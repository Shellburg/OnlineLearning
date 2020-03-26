package cn.st.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.st.entity.WatchRecord;
import cn.st.util.ConnDB;
public class WatchRecordDao {
	/**
	 *  添加观看记录
	 * @param watchRecord
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月2日10:53:45
	 */
	public boolean addWatchRecord(WatchRecord watchRecord) {
		boolean bol=false;
		PreparedStatement ps=null;
		Connection conn=null;
		conn=ConnDB.getConnection();
		String sql="insert into tbl_watchrecord(stu_num,up_id,watchTime) values(?,?,?)";
		try {
			ps=conn.prepareStatement(sql);//预处理sql语句
			ps.setString(1, watchRecord.getStu_num());
			ps.setInt(2, watchRecord.getUp_id());
			ps.setString(3, watchRecord.getWatchTime());
			ps.executeUpdate();//执行sql语句
			bol=true;
			System.out.println("观看记录添加成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	  WatchRecord watchRecord2=null;
	  Connection conn=null;
	  PreparedStatement ps=null;
	  ResultSet rs=null;//结果集
	  conn=ConnDB.getConnection();
	  String sql="select * from tbl_watchrecord where stu_num=? and up_id=?";
	try {
      ps=conn.prepareStatement(sql);//预处理sql语句
	  ps.setString(1, watchRecord.getStu_num());
	  ps.setInt(2, watchRecord.getUp_id());
	  rs=ps.executeQuery();
	  while (rs.next()) {
		  watchRecord2=new WatchRecord();
		  watchRecord2.setStu_num(rs.getString("stu_num"));
		  watchRecord2.setUp_id(rs.getInt("up_id"));
		  watchRecord2.setWatchTime(rs.getString("watchTime"));
		  
		  System.out.println("学号："+watchRecord2.getStu_num());
	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	  return watchRecord2;
	   
}
	
	
}
