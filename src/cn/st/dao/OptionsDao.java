package cn.st.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cn.st.entity.Options;
import cn.st.query.Page;
import cn.st.util.ConnDB;

public class OptionsDao {
	/**
	 *添加选择题(导入选择题)
	 * @param options
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:38:54
	 */
	public boolean addOptions(Options options) {
		boolean bol=false;
		Connection conn=null;
		PreparedStatement ps=null;
		String sql="insert into tbl_option(question,op_a,op_b,op_c,op_d,answer,parse,op_answer,up_id)"
				+ " values(?,?,?,?,?,?,?,?,?)";
		conn=ConnDB.getConnection();//获取数据库连接
		try {
			ps=conn.prepareStatement(sql);//预处理sql语句
			ps.setString(1, options.getQuestion());//实体对象通过get方法获取值set到数据表中
			ps.setString(2, options.getOp_a());
			ps.setString(3, options.getOp_b());
			ps.setString(4, options.getOp_c());
			ps.setString(5, options.getOp_d());
		    ps.setString(6, options.getAnswer());
			ps.setString(7, options.getParse());
			ps.setString(8, options.getOp_answer());
			ps.setInt(9, options.getUp_id());
			ps.executeUpdate();
			bol=true;//数据添加成功后返回ture
			System.out.println("选择题导入成功！！！");
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
	 * 查看选择题
	 * @param page
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:39:40
	 */
	public Page<Options> queryAllOptionIng(Page<Options> page) {
		List<Options> list=new ArrayList<Options>();//实例化链表对象用于存取数据
		Options options=null;//声明实例化实体对象（选择题）
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		StringBuffer sql =new StringBuffer("select * from tbl_option  where 1=1");
		//分页限制
		if (page.getCurrentPage() == 1) {
			sql.append(" limit " + page.getPageSize());
       } else {
    	   sql.append(" limit " + (page.getCurrentPage()-1) * page.getPageSize() + "," + page.getPageSize() );

       }
		conn=ConnDB.getConnection();//获取数据库连接
		try {
			ps=conn.prepareStatement(sql.toString());
			rs=ps.executeQuery();
			while (rs.next()) {
				options=new Options();
				options.setOp_id(rs.getInt("op_id"));
				options.setQuestion(rs.getString("question"));
				options.setOp_a(rs.getString("op_a"));
				options.setOp_b(rs.getString("op_b"));
				options.setOp_c(rs.getString("op_c"));
				options.setOp_d(rs.getString("op_d"));
				options.setAnswer(rs.getString("answer"));
				options.setParse(rs.getString("parse"));
				options.setOp_answer(rs.getString("op_answer"));//正确选项
				options.setUp_id(rs.getInt("up_id"));//外键
				list.add(options);//将实体对象的值添加到链表对象中
			}
			page.setResult(list);//将链表的结果集分页
			
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
		return page;
	}
	/**
	 * 查看选择题 根据up_id
	 * @param up_id
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:39:40
	 */
	public List<Options> queryAllOptionIngByup_id(int up_id) {
		List<Options> list=new ArrayList<Options>();//实例化链表对象用于存取数据
		Options options=null;//声明实例化实体对象（选择题）
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		StringBuffer sql =new StringBuffer("select * from tbl_option  where up_id='"+up_id+"'");
		conn=ConnDB.getConnection();//获取数据库连接
		try {
			ps=conn.prepareStatement(sql.toString());
			rs=ps.executeQuery();
			while (rs.next()) {
				options=new Options();
				options.setQuestion(rs.getString("question"));
				options.setOp_a(rs.getString("op_a"));
				options.setOp_b(rs.getString("op_b"));
				options.setOp_c(rs.getString("op_c"));
				options.setOp_d(rs.getString("op_d"));
				options.setAnswer(rs.getString("op_a"));
				options.setParse(rs.getString("parse"));
				options.setOp_answer(rs.getString("op_answer"));//正确选项
				options.setUp_id(rs.getInt("up_id"));//外键=》视频的id
				list.add(options);//将实体对象的值添加到链表对象中
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
	 * 统计选择题
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:41:19
	 */
	public long countOptions() {
		long count=0;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select count(*) as c from tbl_option";
		conn=ConnDB.getConnection();//获取数据库连接
		try {
			ps=conn.prepareStatement(sql);//预处理sql语句
			rs=ps.executeQuery();
			while (rs.next()) {
				count=rs.getLong("c");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//关闭资源流
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return count;
	}
	 /**
     * 根据op_id绑定选择题的信息
     * @param op_id
     * @return
     * @author 潘盛武
	 * @date 2017年12月10日22:03:52
     */
	public Options findById(int op_id ) {
		Options options=null;//声明实例化实体对象（选择题）
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		StringBuffer sql =new StringBuffer("select * from tbl_option  where op_id='"+op_id+"'");
		conn=ConnDB.getConnection();//获取数据库连接
		try {
			ps=conn.prepareStatement(sql.toString());
			rs=ps.executeQuery();
			while (rs.next()) {
				options=new Options();
				options.setOp_id(rs.getInt("op_id"));
				options.setQuestion(rs.getString("question"));
				options.setOp_a(rs.getString("op_a"));
				options.setOp_b(rs.getString("op_b"));
				options.setOp_c(rs.getString("op_c"));
				options.setOp_d(rs.getString("op_d"));
				options.setAnswer(rs.getString("op_a"));
				options.setParse(rs.getString("parse"));
				options.setOp_answer(rs.getString("op_answer"));//正确选项
				options.setUp_id(rs.getInt("up_id"));//外键=》视频的id
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
		return options;
	}
	
	/**
	 * 修改选择题信息
	 * @param options
	 * @author 潘盛武
	 * @date 2017年12月10日18:09:56
	 */
	public boolean updateOp(Options options) {
		boolean bol=false;
		Connection conn=null;
		PreparedStatement ps=null;
		conn=ConnDB.getConnection();
		try {
		    String sql="UPDATE tbl_option SET question=?,op_a=?,op_b=?,op_c=?,op_d=?,parse=?,op_answer=?,up_id=? WHERE op_id=?";
			ps=conn.prepareStatement(sql);
			ps.setString(1, options.getQuestion());
			ps.setString(2, options.getOp_a());
			ps.setString(3, options.getOp_b());
			ps.setString(4, options.getOp_c());
			ps.setString(5, options.getOp_d());
			ps.setString(6, options.getParse());
			ps.setString(7, options.getOp_answer());
			ps.setInt(8, options.getUp_id());
			ps.setInt(9, options.getOp_id());//根据id修改
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
	 * 删除选择题
	 * @param op_id
	 * @author 潘盛武
	 * @date 2017年12月10日18:16:23
	 */
	public boolean deleteOp(Options options) {
		boolean bol=false;
		Connection conn=null;
		PreparedStatement ps=null;
		conn=ConnDB.getConnection();
		String sql="delete from tbl_option where op_id=?";
		try {
			//预处理sql
			ps=conn.prepareStatement(sql);
			ps.setInt(1, options.getOp_id());
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
	
}
