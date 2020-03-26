package cn.st.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import cn.st.entity.Comments;
import cn.st.query.Page;
import cn.st.util.ConnDB;

public class CommentsDao {
	/**
     * 留言
     * @param comments
     * @param content 留言内容
     * @param ceateTime 留言时间
     * @param contentName 留言人
     * @author 潘盛武
	 * @date 2017年12月5日22:30:50
     */
    public boolean leaveMessage(Comments comments) {
    	boolean bol=false;
		Connection conn=null;
		PreparedStatement ps=null;
		conn=ConnDB.getConnection();
		String sql="insert into tbl_comment(c_title,content,ceateTime,contentName,state)"
				+ "values(?,?,?,?,?)";
    	try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, comments.getC_title());
			ps.setString(2, comments.getContent());
			ps.setString(3, comments.getCeateTime());
			ps.setString(4, comments.getContentName());
			ps.setString(5, comments.getState());
			ps.executeUpdate();
			System.out.println("添加成功");
			bol=true;
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
     * 根据c_id找到评论的信息 --显示详细信息
     * @param c_id
     * @return
     * @author 潘盛武
	 * @date 2017年12月5日22:53:47
     */
	public Comments findById(long c_id ) {
		Comments comments=null;
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		conn=ConnDB.getConnection();
		String sql="select *from tbl_comment where c_id='"+ c_id + "'";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while (rs.next()) {
				comments=new Comments();
				comments.setC_id(rs.getLong("c_id"));
				comments.setContentName(rs.getString("contentName"));;
				comments.setC_title(rs.getString("c_title"));
				comments.setrContentName(rs.getString("rContentName"));
				comments.setCeateTime(rs.getString("ceateTime"));
				comments.setContent(rs.getString("content"));
				comments.setrContentName(rs.getString("rContentName"));
				comments.setrContent(rs.getString("rContent"));
				comments.setrDateTime(rs.getString("rDateTime"));
				comments.setState(rs.getString("state"));
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
		return comments;
	}
	
	/**
	 * 回复留言
	 * @param comments
	 * @param rContent 回复内容
	 * @param rDateTime 回复时间
	 * @param crContentName 回复人
	 * @param state    状态（1表示审核通过0表示未审核 默认未审核）
	 * @author 潘盛武
	 * @date 2017年12月5日22:38:13
	 */
	public boolean replyContent(Comments  comments) {
		boolean bol=false;
		Connection conn=null;
		PreparedStatement ps=null;
		conn=ConnDB.getConnection();
		try {
		    String sql="UPDATE tbl_comment SET rContent=?,rDateTime=?,rContentName=?,state=? WHERE c_id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, comments.getrContent());
			ps.setString(2, comments.getrDateTime());
			ps.setString(3, comments.getrContentName());
			ps.setString(4, comments.getState());
			ps.setLong(5, comments.getC_id());//根据id修改
			ps.executeUpdate();
			System.out.println("修改成功"+sql);
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
	 * 删除留言
	 * @param ad_id
	 * @author 潘盛武
	 * @date  2017年12月6日19:31:28
	 */
	public boolean deleteMessage(long c_id ) {
		boolean bol=false;
		Connection conn=null;
		PreparedStatement ps=null;
		conn=ConnDB.getConnection();
		String sql="delete from tbl_comment where c_id=?";
		try {
			//预处理sql
			ps=conn.prepareStatement(sql);
			ps.setLong(1, c_id);
			ps.executeUpdate();
			System.out.println("删除成功");
			bol=true;
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
	 * 查询 留言信息 分页  根据标题查询    ---首页显示更多的留言信息
	 * @param page
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月5日22:55:32  where 1=1
	 */
	public Page<Comments> queryPage(Page<Comments> page,String c_title){
		    Connection conn=null;
		     ResultSet rs=null;
		    Statement st = null;
		 List<Comments> ctList=new ArrayList<Comments>();
		try {
			conn=ConnDB.getConnection();
			StringBuffer sql =new StringBuffer("select * from tbl_comment  where  state='已审核'"  );
			//添加查询条件（根据标题查询留言信息） 
		    if (c_title !=null && !"".equals(c_title)) {
				sql.append(" and c_title like '%" + c_title + "%'");
			}	
		   //追加查询条件根据留言时间降序排序
		    sql.append("  order by ceateTime desc");//根据时间降序 
			if (page.getCurrentPage() == 1) {
				sql.append(" limit " + page.getPageSize());
           } else {
           	//分页页大小为6
        	   sql.append(" limit " + (page.getCurrentPage()-1) * page.getPageSize() + "," + page.getPageSize() );
           }
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			while (rs.next()) {
				Comments  comments=new Comments();
				comments.setC_id(rs.getLong("c_id"));
				comments.setC_title(rs.getString("c_title"));
				comments.setContentName(rs.getString("contentName"));
				comments.setCeateTime(rs.getString("ceateTime"));
				comments.setContent(rs.getString("content"));
				
				comments.setrContent(rs.getString("rContent"));
				comments.setrContentName(rs.getString("rContentName"));
				comments.setrDateTime(rs.getString("rDateTime"));
				comments.setState(rs.getString("state"));
				ctList.add(comments);
			}
			 page.setResult(ctList);
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
     * 统计（用于分页） 统计审核通过的留言条数
     * @return
     * @author 潘盛武
	 * @date 2018年1月6日13:55:39
     */
    public long countContentApproved(String c_title) {
        long count=01;
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try{
            conn = ConnDB.getConnection();;
           StringBuffer stringBuffer = new StringBuffer("SELECT COUNT(*) AS c FROM tbl_comment WHERE  state='已审核' "); 
            if (c_title !=null && !"".equals(c_title)) {
            	stringBuffer.append(" and c_title like '%" + c_title + "%'");
			}
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
     * 统计（用于分页）
     * @return
     * @author 潘盛武
	 * @date 2017年12月5日22:59:33
     */
    /*public long countrContent(String c_title) {
        long count=01;
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;
        try{
            conn = ConnDB.getConnection();;
            StringBuffer sql = new StringBuffer(" SELECT count(*) as c FROM tbl_comment "); 
          //添加查询条件（根据标题和类型查询视频信息）
		    if (c_title !=null && !"".equals(c_title)) {
				sql.append(" and c_title like '%" + c_title + "%'");
			}
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql.toString());
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
    }*/
    /**
	 * 统计留言的总条数
	 * @param c_title--留言标题 
	 * @return
	 * @author 潘盛武
	 * @date 2018年1月13日18:21:42
	 */
	public long countrContent(String c_title) {
		long countTotal=0;//统计值
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		conn=ConnDB.getConnection();//获取数据库连接
		String query="select count(*) as c  from tbl_comment where 1=1 ";
		StringBuffer sql=new StringBuffer(query);
		//操作人查询
		if (c_title !=null && !"".equals(c_title)) {
			sql.append(" and c_title like '%" + c_title + "%' ");
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
	 * 查询 留言信息 分页   --管理员登录
	 * 管理员根据学号查询留言信息进行审核审核通过的留言
     * 信息才能显示在学生端的留言信息上。
	 * @param page
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月7日19:29:49
	 */
	public Page<Comments> queryPageMG(Page<Comments> page,String c_title){
		    Connection conn=null;
		     ResultSet rs=null;
		    Statement st = null;
		 List<Comments> ctList=new ArrayList<Comments>();
		try {
			conn=ConnDB.getConnection();
			StringBuffer sql =new StringBuffer("SELECT * FROM tbl_comment   WHERE 1=1 "  );
			//添加查询条件（根据标题和类型查询视频信息）
		    if (c_title !=null && !"".equals(c_title)) {
				sql.append(" and c_title like '%" + c_title + "%'");
			}	
		   //追加查询条件根据留言时间降序排序
		    sql.append("  order by ceateTime desc");//根据时间降序 
			if (page.getCurrentPage() == 1) {
				sql.append(" limit " + page.getPageSize());
           }else {
           	//分页页大小为6
        	   sql.append(" limit " + (page.getCurrentPage()-1) * page.getPageSize() + "," + page.getPageSize() );
           }
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			while (rs.next()) {
				Comments  comments=new Comments();
				comments.setC_id(rs.getLong("c_id"));
				comments.setC_title(rs.getString("c_title"));
				comments.setContentName(rs.getString("contentName"));
				comments.setCeateTime(rs.getString("ceateTime"));
				comments.setContent(rs.getString("content"));
				
				comments.setrContent(rs.getString("rContent"));
				comments.setrContentName(rs.getString("rContentName"));
				comments.setrDateTime(rs.getString("rDateTime"));
				comments.setState(rs.getString("state"));
				ctList.add(comments);
			}
			 page.setResult(ctList);
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
	 * 学生只能查看自己的留言信息  --根据登录的学生姓名查询信息
	 * @param page
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月16日22:32:17
	 */
	public List<Comments> queryMyMessages(String contentName){
		    Connection conn=null;
		     ResultSet rs=null;
		    Statement st = null;
		 List<Comments> ctList=new ArrayList<Comments>();
		try {
			conn=ConnDB.getConnection();
			StringBuffer sql =new StringBuffer("select * from tbl_comment  where state='已审核'");
			//添加查询条件（根据标题和类型查询视频信息）
		    if (contentName !=null && !"".equals(contentName)) {
				sql.append(" and contentName = '" + contentName + "'");
			}	
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			while (rs.next()) {
				Comments  comments=new Comments();
				comments.setC_id(rs.getLong("c_id"));
				comments.setC_title(rs.getString("c_title"));
				comments.setContentName(rs.getString("contentName"));
				comments.setCeateTime(rs.getString("ceateTime"));
				comments.setContent(rs.getString("content"));
				
				comments.setrContent(rs.getString("rContent"));
				comments.setrContentName(rs.getString("rContentName"));
				comments.setrDateTime(rs.getString("rDateTime"));
				comments.setState(rs.getString("state"));
				ctList.add(comments);
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
		return ctList;
	}
	
	/**
	 * 初始化留言信息  只显示最新已审核的的5条信息 降序排序。
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月18日23:29:28
	 */
	public List<Comments> initComments()  {
		    Connection conn=null;
		    ResultSet rs=null;
		    Statement st = null;
		 List<Comments> list=new ArrayList<Comments>();
		try {
			conn=ConnDB.getConnection();
			StringBuffer sql =new StringBuffer("SELECT * FROM tbl_comment WHERE tbl_comment.state='已审核'  ORDER BY tbl_comment.c_id  DESC LIMIT 0,5");
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			while (rs.next()) {
				Comments  comments=new Comments();
				comments.setC_id(rs.getLong("c_id"));
				comments.setC_title(rs.getString("c_title"));
				comments.setContentName(rs.getString("contentName"));
				comments.setCeateTime(rs.getString("ceateTime"));
				comments.setContent(rs.getString("content"));
				
				comments.setrContent(rs.getString("rContent"));
				comments.setrContentName(rs.getString("rContentName"));
				comments.setrDateTime(rs.getString("rDateTime"));
				comments.setState(rs.getString("state"));
				list.add(comments);
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
