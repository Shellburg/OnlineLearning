package cn.st.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.st.entity.DataOp;
import cn.st.query.Page;
import cn.st.util.ConnDB;

public class DataDao {
	/**
	 * 上传资料
	 * @param dataOp
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月12日22:33:50
	 */
	public boolean uploadData(DataOp dataOp) {
        boolean bol=false;
		Connection conn=null;
		PreparedStatement ps=null;
		
		String sql="insert into tbl_data(file_name,upload_path,data_type,upload_name,upload_time,vedio_name)"
				+ " values(?,?,?,?,?,?)";
		conn=ConnDB.getConnection();//获取数据库连接
		try {
			ps=conn.prepareStatement(sql);//预处理sql语句
			ps.setString(1, dataOp.getFile_name());//实体对象通过get方法获取值set到数据表中
			ps.setString(2, dataOp.getUpload_path());
			ps.setString(3, dataOp.getData_type());
			ps.setString(4, dataOp.getUpload_name());
			ps.setString(5, dataOp.getUpload_time());
			ps.setString(6, dataOp.getVedio_name());//视频名称
			ps.executeUpdate();
			bol=true;//数据添加成功后返回ture
			System.out.println("资料上传成功！！！");
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
	 * 查询资料 根据类型查询
	 * @param data_type
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月12日22:59:44
	 */
	public Page<DataOp> queryDataByType(Page<DataOp> page,String data_type,String file_name) {
		List<DataOp> list=new ArrayList<DataOp>();//实例化链表对象用于存取数据
		DataOp dataOp=null;//声明实例化实体对象
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		StringBuffer sql =new StringBuffer("select * from tbl_data  where 1=1");
	    if (file_name !=null && !"".equals(file_name)) {
				sql.append(" and file_name like '%" + file_name + "%' ");
		}	
		if (data_type!=null&&!"".equals(data_type)) {
			sql.append(" and data_type ='" + data_type + "'");
		}
		//追加查询条件根据留言时间降序排序
	    sql.append("  order by upload_time desc");//根据时间降序 
		//分页限制
		if (page.getCurrentPage() == 1) {
			sql.append(" limit " + page.getPageSize());
       } else {
    	   sql.append(" limit " + (page.getCurrentPage()-1) * page.getPageSize() + "," + page.getPageSize() );
       }
		conn=ConnDB.getConnection();//获取数据库连接
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			while (rs.next()) {
				dataOp=new DataOp();
				dataOp.setData_id(rs.getInt("data_id"));
				dataOp.setFile_name(rs.getString("file_name"));
				dataOp.setUpload_path(rs.getString("upload_path"));
				dataOp.setData_type(rs.getString("data_type"));
				dataOp.setDownload_count(rs.getInt("download_count"));
				dataOp.setUpload_name(rs.getString("upload_name"));
				dataOp.setUpload_time(rs.getString("upload_time"));
				dataOp.setVedio_name(rs.getString("vedio_name"));
				list.add(dataOp);//将实体对象的值添加到链表对象中
				System.out.println("文件名:"+dataOp.getFile_name());
			}
			page.setResult(list);//将链表的结果集分页
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//关闭资源流
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return page;
	}
	
	/**
	 * 统计资料的总数
	 * @param data_type
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月12日22:59:44
	 */
	public long countDate(String data_type,String file_name) {
		long countTotal=0;//统计值
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		conn=ConnDB.getConnection();//获取数据库连接
		String query="select count(*) as c  from tbl_data where 1=1 ";
		StringBuffer sql=new StringBuffer(query);
		//添加查询条件（根据资料类型查询资料信息）
		if (file_name !=null && !"".equals(file_name)) {
				sql.append(" and file_name like '%" + file_name + "%' ");
		}
		if (data_type!=null&&!"".equals(data_type)) {
			sql.append(" and data_type ='" + data_type + "'");
		}	
		 sql.append("  order by upload_time desc");//根据时间降序 
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			while (rs.next()) {
				countTotal=rs.getLong("c");//将统计的值赋值给countTotal
			}
			 System.out.println("查询数据成功");
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
	 * 根据id删除资料信息
	 * @param dataOp
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月12日23:07:53
	 */
	public boolean deleteDataById(DataOp dataOp) {
		boolean bol=false;
	    PreparedStatement ps=null;
	    Connection conn=null;
	    String sql="delete from tbl_data where data_id=?";
	    conn=ConnDB.getConnection();
	    try {
			ps=conn.prepareStatement(sql);//sql预处理
			ps.setInt(1, dataOp.getData_id());
			ps.executeUpdate();
			bol=true;
			System.out.println("资料删除成功");
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
	 * 根据id绑定资料信息（用于修改）
	 * @param dataOp
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月2日20:21:05
	 */
	public DataOp findDataInfById(DataOp dataOp) {
		DataOp dataOp2=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select *  from tbl_data where data_id=? ";
		conn=ConnDB.getConnection();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, dataOp.getData_id());
			rs=ps.executeQuery();
			while (rs.next()) {
				dataOp2=new DataOp();
				dataOp.setData_id(rs.getInt("data_id"));
				dataOp.setFile_name(rs.getString("file_name"));
				dataOp.setUpload_path(rs.getString("upload_path"));
				dataOp.setData_type(rs.getString("data_type"));
				dataOp.setDownload_count(rs.getInt("download_count"));
				dataOp.setUpload_name(rs.getString("upload_name"));
				dataOp.setUpload_time(rs.getString("upload_time"));
				dataOp.setVedio_name(rs.getString("vedio_name"));
			}
			System.out.println("资料信息绑定成功");
			
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
		return dataOp2;
	}
	/**
	 * 修改资料信息
	 * @param dataOp
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月2日20:28:53  
	 */
	public boolean updateDataInf(DataOp dataOp) {
		boolean bol=false;
		Connection conn=null;
		PreparedStatement ps=null;
		String sql="update tbl_data set file_name=?,upload_path=?,data_type=?,download_count=?,upload_name=?"
				+ "upload_time=?,vedio_name=? where data_id=?";
		conn=ConnDB.getConnection();
		try {
			ps=conn.prepareStatement(sql);//sql预处理
			ps.setString(1, dataOp.getFile_name());
			ps.setString(2, dataOp.getUpload_path());
			ps.setString(3, dataOp.getData_type());
			ps.setInt(4, dataOp.getDownload_count());
			ps.setString(5, dataOp.getUpload_name());
			ps.setString(6, dataOp.getUpload_name());
			ps.setString(7, dataOp.getUpload_time());
			ps.setInt(8, dataOp.getData_id());
			ps.executeUpdate();
			bol=true;
			System.out.println("资料信息修改成功");
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
	 * 更新下载次数
	 * @param dataOp
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月17日17:31:26
	 */
	public boolean updateDownloadCount(DataOp dataOp) {
		boolean bol=false;
		Connection conn=null;
		PreparedStatement ps=null;
		String sql="update tbl_data set download_count=? where data_id=?";
		conn=ConnDB.getConnection();
		try {
			ps=conn.prepareStatement(sql);//sql预处理
			ps.setInt(1, dataOp.getDownload_count());
			ps.setInt(2, dataOp.getData_id());
			ps.executeUpdate();
			bol=true;
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
	 * 根据id找到下载次数
	 * @param dataOp
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月18日19:23:28
	 */
	public List<DataOp>  CountDowloadById(DataOp dataOp) {
		List<DataOp> listDataOp=new ArrayList<DataOp>();
		DataOp dataOp2=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select *  from tbl_data where data_id=? ";
		conn=ConnDB.getConnection();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, dataOp.getData_id());
			rs=ps.executeQuery();
			while (rs.next()) {
				dataOp2=new DataOp();
				dataOp2.setData_id(rs.getInt("data_id"));
				dataOp2.setFile_name(rs.getString("file_name"));
				dataOp2.setUpload_path(rs.getString("upload_path"));
				dataOp2.setData_type(rs.getString("data_type"));
				dataOp2.setDownload_count(rs.getInt("download_count"));
				dataOp2.setUpload_name(rs.getString("upload_name"));
				dataOp2.setUpload_time(rs.getString("upload_time"));
				dataOp2.setVedio_name(rs.getString("vedio_name"));
				listDataOp.add(dataOp2);
			}
			System.out.println("资料信息绑定成功");
			
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
		return listDataOp;
	}
	
	
}
