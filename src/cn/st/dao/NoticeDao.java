package cn.st.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import cn.st.entity.Notice;
import cn.st.query.Page;
import cn.st.util.ConnDB;
public class NoticeDao {
	
	/**
	 * 查询 公告信息 分页
	 * @param page
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月11日23:00:33
	 */
	public Page<Notice> queryPage(Page<Notice> page)  {
		   Notice notice=null;
		    Connection conn=null;
		     ResultSet rs=null;
		    Statement st = null;
		 List<Notice> list=new ArrayList<Notice>();
		try {
			conn=ConnDB.getConnection();
			StringBuffer sql =new StringBuffer("select * from tbl_notice t where 1=1");
			sql.append(" ORDER BY  t.create_time DESC");
			if (page.getCurrentPage() == 1) {
				sql.append(" limit " + page.getPageSize());
           } else {
           	//分页页大小为6
        	   sql.append(" limit " + (page.getCurrentPage()-1) * page.getPageSize() + "," + page.getPageSize() );
           }
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			while (rs.next()) {
				notice=new Notice();
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setNotice_tiltle(rs.getString("notice_tiltle"));
				notice.setNotice_content(rs.getString("notice_content"));
				notice.setPub_name(rs.getString("pub_name"));
				notice.setCreate_time(rs.getString("create_time"));
				list.add(notice);
			}
			 page.setResult(list);
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
     * 统计公告（用于分页）
     * @return
     * @author 潘盛武
	 * @date 2017年12月11日23:00:42
     */
    public long countNotice() {
        long count=01;
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try{
            conn = ConnDB.getConnection();;
            StringBuffer stringBuffer = new StringBuffer(" SELECT count(*) as c FROM tbl_notice "); 
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
     * 发布公告
     * @param notice
     * @author 潘盛武
	 * @date 2017年12月11日23:03:26
     */
    public boolean pubNotice(Notice notice) {
    	boolean bol=false;
		Connection conn=null;
		PreparedStatement ps=null;
		conn=ConnDB.getConnection();  
		String sql="insert into  tbl_notice(notice_tiltle,notice_content,pub_name,create_time) values(?,?,?,?)";
    	try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, notice.getNotice_tiltle());
			ps.setString(2, notice.getNotice_content());
			ps.setString(3, notice.getPub_name());
			ps.setString(4, notice.getCreate_time());
			ps.executeUpdate();
			bol=true;
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
    	return bol;
	}
    /**
     * 根据notice_id绑定公告的信息
     * @param notice_id
     * @return
     * @author 潘盛武
	 * @date 2017年12月11日23:05:20
     */
	public Notice findNoticeById(int notice_id ) {
		Notice notice=null;
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		conn=ConnDB.getConnection();
		String sql="select *from tbl_notice where notice_id='"+ notice_id + "'";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while (rs.next()) {
				notice=new Notice();
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setNotice_tiltle(rs.getString("notice_tiltle"));	
				notice.setNotice_content(rs.getString("notice_content"));
				notice.setPub_name(rs.getString("pub_name"));
				notice.setCreate_time(rs.getString("create_time"));
			}
		    rs.close();
	        st.close();
	        conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notice;
	}
	
	/**
	 * 修改公告信息
	 * @param admin
	 * @author 潘盛武
	 * @date 2017年12月11日23:08:15
	 */
	public boolean updateNotice(Notice notice) {
		boolean bol=false;
		Connection conn=null;
		PreparedStatement ps=null;
		conn=ConnDB.getConnection();
		try {
		    String sql="UPDATE tbl_notice SET notice_tiltle=?,notice_content=?,pub_name=?,create_time=? WHERE notice_id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, notice.getNotice_tiltle());
			ps.setString(2, notice.getNotice_content());
			ps.setString(3, notice.getPub_name());
			ps.setString(4, notice.getCreate_time());
			ps.setLong(5, notice.getNotice_id());//根据id修改
			ps.executeUpdate();
			bol=true;
			System.out.println("修改成功");
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
	 * 删除通告信息
	 * @param notice
	 * @author 潘盛武
	 * @date 2017年12月11日23:13:13
	 */
	public boolean deleteNoticeById(Notice notice) {
		boolean bol=false;
		Connection conn=null;
		PreparedStatement ps=null;
		conn=ConnDB.getConnection();
		String sql="delete from tbl_notice where notice_id=?";
		try {
			//预处理sql
			ps=conn.prepareStatement(sql);
			ps.setInt(1, notice.getNotice_id());
			ps.executeUpdate();
			bol=true;
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
		return bol;
	}
	
	/**
	 * 初始化公告信息  只显示最新的5条信息 降序排序(根据时间降序排序)。
	 * @param page
	 * @return  
	 * @author 潘盛武
	 * @date 2017年12月18日21:29:52
	 */
	public List<Notice> initNotice()  {
		   Notice notice=null;
		    Connection conn=null;
		    ResultSet rs=null;
		    Statement st = null;
		 List<Notice> list=new ArrayList<Notice>();
		try {
			conn=ConnDB.getConnection();
			StringBuffer sql =new StringBuffer("SELECT * FROM tbl_notice  ORDER BY tbl_notice.create_time  DESC LIMIT 0,8");
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			while (rs.next()) {
				notice=new Notice();
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setNotice_tiltle(rs.getString("notice_tiltle"));
				notice.setNotice_content(rs.getString("notice_content"));
				notice.setPub_name(rs.getString("pub_name"));
				notice.setCreate_time(rs.getString("create_time"));
				list.add(notice);
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
