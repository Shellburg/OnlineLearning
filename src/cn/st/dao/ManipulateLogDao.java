package cn.st.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;
import cn.st.entity.ManipulateLog;
import cn.st.query.Page;
import cn.st.util.ConnDB;

/**
 * 日志操作类 
 * @author 潘盛武
 * @date 2018年1月12日19:06:57
 */
public class ManipulateLogDao {
     
	/**
	 * 记录操作日志
	 * @author 潘盛武
	 * @date 2018年1月12日19:11:01
	 * @return
	 */
	public boolean addManipulateLog(ManipulateLog manipulateLog) {
		boolean bol=false;
		Connection conn=null;
		PreparedStatement ps=null;
		conn=ConnDB.getConnection();
		String sql="INSERT INTO tbl_manipulatelog(userName,mpDescribe,createTime,rem1) VALUES(?,?,?,?);";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, manipulateLog.getUserName());
		    ps.setString(2, manipulateLog.getMpDescribe());
		    ps.setString(3, manipulateLog.getCreateTime());
			ps.setString(4, manipulateLog.getRem1());
			ps.executeUpdate();
			System.out.println("操作日志记录成功！！！");
			bol=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (ps!=null&&conn!=null) {
				try {
					conn.close();
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bol;
	}
	/**
	 * 根据开始时间和结束时间查询日志记录
	 * @param userName--操作人  startTime--查询日志的的开始时间,endTime--结束时间
	 * @author 潘盛武
	 * @date 2018年1月13日18:21:55
	 */
	public Page<ManipulateLog> queryMpLogByTime(Page<ManipulateLog> page,String userName,String starTime,String endTime) {
		List<ManipulateLog> list = new ArrayList<ManipulateLog>();
		Connection conn = null;
		Statement st = null;
		ResultSet result = null;
		conn = ConnDB.getConnection();
		StringBuffer sql =new StringBuffer("SELECT * FROM tbl_manipulatelog where 1=1");
		try {
			//操作人查询
			if (userName !=null && !"".equals(userName)) {
				sql.append(" and userName like '%" + userName + "%' ");
		    }
			//时间查询(开始时间~结束时间)
			if (starTime != null && !"".equals(starTime) && endTime != null && !"".equals(endTime)) {
				sql.append(" and  DATE_FORMAT(createTime,'%Y-%m-%d') BETWEEN '" + starTime + "' AND '" + endTime + "' ");
			}
			sql.append("  order by createTime desc");//根据时间降序 
			//分页处理
			if (page.getCurrentPage() == 1) {
				sql.append(" limit " + page.getPageSize());
			} else {
				sql.append(" limit " + (page.getCurrentPage() - 1) * page.getPageSize() + "," + page.getPageSize());
			}
			System.out.println("SQL语句:"+sql);
			st = conn.createStatement();
			result = st.executeQuery(sql.toString());
			while (result.next()) {
				ManipulateLog manipulateLog = new ManipulateLog();
				manipulateLog.setUserName(result.getString("userName"));
				manipulateLog.setMpDescribe(result.getString("mpDescribe"));
				manipulateLog.setCreateTime(result.getString("createTime"));
				manipulateLog.setRem1(result.getString("rem1"));
				list.add(manipulateLog);
			}
			page.setResult(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (result != null && conn != null && st != null) {
				try {
					result.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return page;
	}
	/**
	 * 根据开始时间和结束时间查询日志记录
	 * @author 潘盛武
	 * @date 2018年1月12日19:25:15
	 */
	public List<ManipulateLog> queryMpLogByTime2(String userName,String starTime,String endTime) {
		List<ManipulateLog> list = new ArrayList<ManipulateLog>();
		ManipulateLog manipulateLog = new ManipulateLog();
		Connection conn = null;
		Statement st = null;
		ResultSet result = null;
		conn = ConnDB.getConnection();
		StringBuffer sql =new StringBuffer("SELECT * FROM tbl_manipulatelog where 1=1");
		try {
			//操作人查询
			if (userName !=null && !"".equals(userName)) {
				sql.append(" and userName like '%" + userName + "%' ");
		    }
			//时间查询(开始时间~结束时间)
			if (starTime != null && !"".equals(starTime) && endTime != null && !"".equals(endTime)) {
				sql.append(" and  DATE_FORMAT(createTime,'%Y-%m-%d') BETWEEN '" + starTime + "' AND '" + endTime + "' ");
			}
			st = conn.createStatement();
			result = st.executeQuery(sql.toString());
			while (result.next()) {
				manipulateLog.setUserName(result.getString("userName"));
				manipulateLog.setMpDescribe(result.getString("mpDescribe"));
				manipulateLog.setCreateTime(result.getString("createTime"));
				manipulateLog.setRem1(result.getString("rem1"));
				list.add(manipulateLog);
				System.out.println("时间:"+list.get(0).getCreateTime());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (result != null && conn != null && st != null) {
				try {
					result.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	/**
	 * 统计日志的总条数
	 * @param userName--操作人  startTime--查询日志的的开始时间,endTime--结束时间
	 * @return
	 * @author 潘盛武
	 * @date 2018年1月13日18:21:42
	 */
	public long countDate(String userName,String startTime,String endTime) {
		long countTotal=0;//统计值
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		conn=ConnDB.getConnection();//获取数据库连接
		String query="select count(*) as c  from tbl_manipulatelog where 1=1 ";
		StringBuffer sql=new StringBuffer(query);
		//操作人查询
		if (userName !=null && !"".equals(userName)) {
			sql.append(" and userName like '%" + userName + "%' ");
	    }
		//时间查询(开始时间~结束时间)
		if (startTime != null && !"".equals(startTime) && endTime != null && !"".equals(endTime)) {
			sql.append(" and  DATE_FORMAT(createTime,'%Y-%m-%d') BETWEEN '" + startTime + "' AND '" + endTime + "' ");
		}
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			while (rs.next()) {
				countTotal=rs.getLong("c");//将统计的值赋值给countTotal
			}
			 System.out.println("查询数据成功数："+countTotal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭资源流
			 try {
				rs.close();
			    st.close();
		        conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return countTotal;
	}
	
	/**
	 * 清空日志表操作
	 * @author 潘盛武
	 * @date 2018年1月13日21:52:27  
	 */
	public boolean truncateMaLog() {
		boolean bol=false;
		Connection conn=null;
		PreparedStatement ps=null;
		conn=ConnDB.getConnection();
		StringBuffer sql=new StringBuffer("truncate table tbl_manipulatelog");
		try {
			ps=conn.prepareStatement(sql.toString());
			ps.executeUpdate();
			System.out.println("日志清空完成！！！");
			bol=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bol=false;
		}
		return bol;
	}
	
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		ManipulateLogDao dao=new ManipulateLogDao();
		dao.countDate("小高","2018-01-08", "2018-01-11");
	}
}
