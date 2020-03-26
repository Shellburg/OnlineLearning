package cn.st.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.st.entity.Admin;
import cn.st.query.Page;
import cn.st.util.ConnDB;
@Repository("adminDao")
public class AdminDao {
	/**
	 * spring注解
	 * @Repository用于标注数据访问组件，即DAO组件
	 */
	
	
	/**
	 * 查询 我的信息
	 * @param name
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日21:05:56
	 */
	public List<Admin> queryMyMsg(String name)  {
		    Connection conn=null;
		     ResultSet rs=null;
		    Statement st = null;
		 List<Admin> admin=new ArrayList<Admin>();
		try {
			conn=ConnDB.getConnection();
			String sql ="select * from tbl_admin  where name='"+name+"'";
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			while (rs.next()) {
				Admin  admin2=new Admin();
				admin2.setAd_id(rs.getLong("ad_id"));
				admin2.setLoginname(rs.getString("loginname"));
				admin2.setName(rs.getString("name"));
				admin2.setPwd(rs.getString("psw"));
				admin2.setAdminLevel(rs.getString("adminLevel"));
				admin.add(admin2);
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
		return admin;
	}
	/**
	 * 查询 管理员信息 分页
	 * @param page
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:23:14
	 */
	public Page<Admin> queryPage(Page<Admin> page)  {
		    Connection conn=null;
		     ResultSet rs=null;
		    Statement st = null;
		 List<Admin> admin=new ArrayList<Admin>();
		try {
			conn=ConnDB.getConnection();
			StringBuffer sql =new StringBuffer("select * from tbl_admin  where 1=1");
			if (page.getCurrentPage() == 1) {
				sql.append(" limit " + page.getPageSize());
           } else {
           	//分页页大小为6
        	   sql.append(" limit " + (page.getCurrentPage()-1) * page.getPageSize() + "," + page.getPageSize() );
           }
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			while (rs.next()) {
				Admin  admin2=new Admin();
				admin2.setAd_id(rs.getLong("ad_id"));
				admin2.setLoginname(rs.getString("loginname"));
				admin2.setName(rs.getString("name"));
				admin2.setPwd(rs.getString("psw"));
				admin2.setAdminLevel(rs.getString("adminLevel"));
				admin.add(admin2);
			}
			 page.setResult(admin);
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
		return page;
	}
	/**
     * 统计管理员（用于分页）
     * @param name
     * @param code
     * @return
     * @author 潘盛武
	 * @date 2017年11月27日22:23:14
     */
    public long countrAdmin() {
        long count=01;
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try{
            conn = ConnDB.getConnection();;
            StringBuffer stringBuffer = new StringBuffer(" SELECT count(*) as c FROM tbl_admin "); 
            System.out.println(stringBuffer);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(stringBuffer.toString());
            while(rs.next()){
            	count = rs.getLong("c");
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
        	 try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        return count;
    }
    /**
     * 添加管理员
     * @param admin
     * @author 潘盛武
	 * @date 2017年11月27日22:23:14
     */
    public void save(Admin admin) {
		Connection conn=null;
		PreparedStatement ps=null;
		conn=ConnDB.getConnection();
		String sql="insert into tbl_admin(name,loginname,psw,adminLevel)"
				+ "values(?,?,?,?)";
    	try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, admin.getName());
			ps.setString(2, admin.getLoginname());
			ps.setString(3, admin.getPwd());
			ps.setString(4, admin.getAdminLevel());
			ps.executeUpdate();
			System.out.println("添加成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    /**
     * 根据ad_id绑定管理员的信息
     * @param id
     * @return
     * @author 潘盛武
	 * @date 2017年11月27日22:23:14
     */
	public Admin findById(long ad_id ) {
		Admin admin=null;
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		conn=ConnDB.getConnection();
		String sql="select *from tbl_admin where ad_id='"+ ad_id + "'";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while (rs.next()) {
				admin=new Admin();
				admin.setAd_id(rs.getLong("ad_id"));
				admin.setName(rs.getString("name"));	
				admin.setLoginname(rs.getString("loginname"));
				admin.setPwd(rs.getString("psw"));
				admin.setAdminLevel(rs.getString("adminLevel"));
			}
		    rs.close();
	        st.close();
	        conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return admin;
	}
	
	/**
	 * 修改管理员信息
	 * @param admin
	 *  @author 潘盛武
	 * @date 2017年11月27日22:23:14
	 */
	public void update(Admin admin) {
		Connection conn=null;
		PreparedStatement ps=null;
		conn=ConnDB.getConnection();
		try {
		    String sql="UPDATE tbl_admin SET loginname=?,psw=?,adminLevel=? WHERE ad_id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, admin.getLoginname());
			ps.setString(2, admin.getPwd());
			ps.setString(3, admin.getAdminLevel());
			ps.setLong(4, admin.getAd_id());//根据id修改
			ps.executeUpdate();
			System.out.println("修改成功"+sql);
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
	 * 删除管理员
	 * @param ad_id
	 * @author 潘盛武
	 * @date 2017年11月27日22:23:14
	 */
	public void delete(long ad_id ) {
		Connection conn=null;
		PreparedStatement ps=null;
		conn=ConnDB.getConnection();
		String sql="delete from tbl_admin where ad_id=?";
		try {
			//预处理sql
			ps=conn.prepareStatement(sql);
			ps.setLong(1, ad_id);
			ps.executeUpdate();
			System.out.println("删除成功");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 管理员登录
	 * @param admin
	 * @return
	 * @date 2017年11月26日20:11:46
	 * @author 潘盛武
	 */
	public Admin loginUser(Admin admin) {
		Admin admin2=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection conn=null;
		conn=ConnDB.getConnection();
		String sql="select * from tbl_admin  where loginname=? and psw=? and adminLevel=?";
		try {
			ps=conn.prepareStatement(sql);//预处理sql语句
			ps.setString(1, admin.getLoginname());//登录名将admin get的数据set到ps中进行查询
			ps.setString(2, admin.getPwd());//密码
			ps.setString(3, admin.getAdminLevel());//管理级别
			rs=ps.executeQuery();//查询结果集存放到rs中
			while (rs.next()) {
				admin2=new Admin();
				admin2.setLoginname(rs.getString("loginname"));
				admin2.setPwd(rs.getString("psw"));
				admin2.setName(rs.getString("name"));
				admin2.setAdminLevel(rs.getString("adminLevel"));
				System.out.println("管理级别："+admin2.getAdminLevel()+"登录名："+admin2.getLoginname());
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
		return admin2;
	}
	/**
	 * 修改我的信息          ---教师修改自己的信息
	 * @param admin
	 *  @author 潘盛武
	 * @date 2017年12月26日20:27:40
	 */
	public void updateByteacher(Admin admin) {
		Connection conn=null;
		PreparedStatement ps=null;
		conn=ConnDB.getConnection();
		try {
		    String sql="UPDATE tbl_admin SET name=?,psw=? WHERE ad_id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, admin.getName());
			ps.setString(2, admin.getPwd());
			ps.setLong(3, admin.getAd_id());//根据id修改
			ps.executeUpdate();
			System.out.println("修改成功"+sql);
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
	 *异步刷新验证教师根据登录的登录名和输入的密码比对验证是否是本人修改
	 * @param admin
	 * @return
	 * @date 2017年12月26日20:31:08
	 * @author 潘盛武
	 */
	public Admin updateMyInfValidate(Admin admin) {
		Admin admin2=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection conn=null;
		conn=ConnDB.getConnection();
		String sql="select * from tbl_admin  where loginname=? and psw=?";
		try {
			ps=conn.prepareStatement(sql);//预处理sql语句
			ps.setString(1, admin.getLoginname());//登录名将admin get的数据set到ps中进行查询
			ps.setString(2, admin.getPwd());//密码
			rs=ps.executeQuery();//查询结果集存放到rs中
			while (rs.next()) {
				admin2=new Admin();
				admin2.setLoginname(rs.getString("loginname"));
				admin2.setPwd(rs.getString("psw"));
				System.out.println("登录名："+admin2.getLoginname());
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
		return admin2;
	}
}
