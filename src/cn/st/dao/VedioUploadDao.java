package cn.st.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.st.entity.VedioUpload;
import cn.st.query.Page;
import cn.st.util.ConnDB;

public class VedioUploadDao {
	/**
	 * 视频上传 （添加视频上传信息）
	 * @param vedioUpload
	 * @author 潘盛武
	 * @date 2017年11月27日22:31:08
	 */
	public void addVedioInf(VedioUpload vedioUpload) {
		Connection conn=null;
		PreparedStatement ps=null;//预处理对象
		conn=ConnDB.getConnection();//获取数据库连接
		String sql="insert into tbl_upload(picture_path,title,describes,up_time,type_id) values(?,?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, vedioUpload.getPicture_path());
			ps.setString(2, vedioUpload.getTitle());
			ps.setString(3, vedioUpload.getDescribes());
			ps.setString(4, vedioUpload.getUp_time());
			ps.setInt(5, vedioUpload.getType_id());//外键 视频类型
			ps.executeUpdate();//执行预处理对象（新增视频上传信息）
			System.out.println("新增成功");
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
	}
	/**
	 * 查询所有的视频信息
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:32:08
	 */
	public List<VedioUpload> queryVedioAllInf() {
		List<VedioUpload> list=new ArrayList<VedioUpload>();
		VedioUpload vedioUpload=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select *from tbl_upload";
		conn=ConnDB.getConnection();//获取数据库连接
		try {
			ps=conn.prepareStatement(sql);//预处理SQL语句
			rs=ps.executeQuery();
			while (rs.next()) {
				vedioUpload=new VedioUpload();
				//将结果集set到实体对象 vedioUpload中
				vedioUpload.setUp_id(rs.getInt("up_id"));
				vedioUpload.setPicture_path(rs.getString("picture_path"));
				vedioUpload.setTitle(rs.getString("title"));
				vedioUpload.setDescribes(rs.getString("describes"));
				vedioUpload.setUp_time(rs.getString("up_time"));
				vedioUpload.setType_id(rs.getInt("type_id"));
				//将实体对象的结果集添加到链表对象list中
				list.add(vedioUpload);
			}
            System.out.println("查询数据成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//关闭资源流
				rs.close();
				ps.close();
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	/**
	 * 视频信息查询（根据标题和视频类型查询）
	 * Page<VedioUpload> 泛型
	 * * @author 潘盛武
	 * @date 2017年11月27日22:32:08
	 */
	public Page<VedioUpload> queryByTittleAndType(String title,Integer type_id,Page<VedioUpload> page) {
		List<VedioUpload> list=new ArrayList<VedioUpload>();//链表对象
		VedioUpload vedioUpload=null;//视频实体对象
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		conn=ConnDB.getConnection();//获取数据库连接
		String query="select * from tbl_upload where 1=1 ";
		StringBuffer sql=new StringBuffer(query);
		//添加查询条件（根据标题和类型查询视频信息）
	    if (title !=null && !"".equals(title)) {
			sql.append(" and title like '%" + title + "%' ");
		}	
		//根据系别查询
		 if (type_id != null && !"".equals(type_id)) {
         	sql.append(" and type_id = '" + type_id + "' ");
            }	
		 //分页限制
         if (page.getCurrentPage() ==1) {
				sql.append(" limit " + page.getPageSize());
			}else {
				sql.append(" limit " + (page.getCurrentPage()-1) * page.getPageSize() + "," + page.getPageSize());
			}
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			while (rs.next()) {
				vedioUpload=new VedioUpload();
				vedioUpload.setUp_id(rs.getInt("up_id"));
				vedioUpload.setPicture_path(rs.getString("picture_path"));
				vedioUpload.setTitle(rs.getString("title"));
				vedioUpload.setDescribes(rs.getString("describes"));
				vedioUpload.setUp_time(rs.getString("up_time"));
				vedioUpload.setType_id(rs.getInt("type_id"));
				list.add(vedioUpload);//将遍历的结果集添加到链表对象list中
			}
			page.setResult(list);//为泛型类Page 设置链表的结果集 分页
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
		return page;
	}
	/**
	 * 统计分页条数（根据查询条件统计分页条数 查询条件 标题和类型）
	 * @param title
	 * @param type_id
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:32:08
	 */
	public long countDate(String title,Integer type_id) {
		long countTotal=0;//统计值
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		conn=ConnDB.getConnection();//获取数据库连接
		String query="select count(*) as c  from tbl_upload where 1=1 ";
		StringBuffer sql=new StringBuffer(query);
		//添加查询条件（根据标题和类型查询视频信息）
	    if (title !=null && !"".equals(title)) {
			sql.append(" and title like '%" + title + "%' ");
		}	
		//根据系别查询
		 if (type_id != null && !"".equals(type_id)) {
         	sql.append(" and type_id = '" + type_id + "' ");
            }	
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
	 * 找到上传视频的id值
	 * @param up_id
	 * @return
	 * @throws SQLException
	 * @author 潘盛武
	 * @date 2017年11月27日22:34:25
	 */
	public VedioUpload findById(int up_id) {
		VedioUpload vedioUpload = null;
		Connection conn=null;
		ResultSet rs=null;
		Statement st=null;
		conn=ConnDB.getConnection();
		String sql="select * from  tbl_upload where up_id='"+up_id+"'";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
	   		while (rs.next()) {
	   			vedioUpload  =new VedioUpload();
				vedioUpload.setUp_id(rs.getInt("up_id"));//获得id
				vedioUpload.setTitle(rs.getString("title"));//获得id
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   		//关闭资源
   		try {
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vedioUpload;
	}
	/**
	 * 删除视频资源
	 * @param up_id
	 * @author 潘盛武
	 * @date 2017年11月27日22:34:25
	 */
	public void deleteVedioById(int up_id) {
		Connection conn=null;
		PreparedStatement ps=null;
		String sql="delete from tbl_upload where up_id=?";
	    conn=ConnDB.getConnection();//获取数据库连接
	    try {
			ps=conn.prepareStatement(sql);//预处理sql
			ps.setLong(1, up_id);
			ps.executeUpdate();//执行操作
			System.out.println("删除成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//关闭流资源
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		List<VedioUpload> list=new ArrayList<VedioUpload>();//实例化链表对象用于存取数据
		VedioUpload vedioUpload=null;//声明实例化实体对象（选择题）
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		StringBuffer sql =new StringBuffer("select * from tbl_upload  where up_id='"+up_id+"'");
		conn=ConnDB.getConnection();//获取数据库连接
		try {
			ps=conn.prepareStatement(sql.toString());
			rs=ps.executeQuery();
			while (rs.next()) {
				vedioUpload=new VedioUpload();
				vedioUpload.setUp_id(rs.getInt("up_id"));
				vedioUpload.setPicture_path(rs.getString("picture_path"));
				vedioUpload.setTitle(rs.getString("title"));
				vedioUpload.setDescribes(rs.getString("describes"));
				vedioUpload.setUp_time(rs.getString("up_time"));
				vedioUpload.setType_id(rs.getInt("type_id"));
				list.add(vedioUpload);//将实体对象的值添加到链表对象中
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//关闭资源流
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	/**
	 * 初始化信息  只显示最新的5条信息 按时间降序排序 显示最新的视频
	 * @param 
	 * @return  
	 * @author 潘盛武
	 * @date 2017年12月19日19:53:27
	 */
	public List<VedioUpload> initVedios()  {
		    Connection conn=null;
		    ResultSet rs=null;
		    Statement st = null;
		 List<VedioUpload> list=new ArrayList<VedioUpload>();
		try {
			conn=ConnDB.getConnection();
			StringBuffer sql =new StringBuffer("SELECT * FROM  tbl_upload t ORDER BY t.up_time DESC LIMIT 0,6");
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			while (rs.next()) {
				VedioUpload vedioUpload=new VedioUpload();
				vedioUpload.setUp_id(rs.getInt("up_id"));
				vedioUpload.setPicture_path(rs.getString("picture_path"));
				vedioUpload.setTitle(rs.getString("title"));
				vedioUpload.setDescribes(rs.getString("describes"));
				vedioUpload.setUp_time(rs.getString("up_time"));
				vedioUpload.setType_id(rs.getInt("type_id"));
				list.add(vedioUpload);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				 try {
					 rs.close();
					 st.close();
			         conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return list;
	}
	
}
