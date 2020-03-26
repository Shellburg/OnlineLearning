package cn.st.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.st.entity.Judge;
import cn.st.query.Page;
import cn.st.util.ConnDB;

public class JudgeDao {
   
	/**
	 * 导入判断题
	 */
	public boolean addJudge(Judge judge) {
    boolean bol=false;
		
		Connection conn=null;
		PreparedStatement ps=null;
		
		String sql="insert into tbl_judge(question,r_true,r_wrong,answer,parse)"
				+ " values(?,?,?,?,?)";
		conn=ConnDB.getConnection();//获取数据库连接
		
		try {
			ps=conn.prepareStatement(sql);//预处理sql语句
			ps.setString(1, judge.getQuestion());//实体对象通过get方法获取值set到数据表中
			ps.setInt(2, judge.getR_true());
			ps.setInt(3, judge.getR_wrong());
			ps.setInt(4, judge.getAnswer());
			ps.setString(5, judge.getParse());
		   
			
			ps.executeUpdate();
			bol=true;//数据添加成功后返回ture
			System.out.println("判断题导入成功！！！");
			
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
	 * 查看判断题
	 */
	public Page<Judge> queryAllJudgeIng(Page<Judge> page) {
		List<Judge> list=new ArrayList<Judge>();//实例化链表对象用于存取数据
		
		Judge judge=null;//声明实例化实体对象（判断题）
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		StringBuffer sql =new StringBuffer("select * from tbl_judge  where 1=1");
		
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
				judge=new Judge();
				judge.setQuestion(rs.getString("question"));
				judge.setR_true(rs.getInt("r_true"));
				judge.setR_wrong(rs.getInt("r_wrong"));
				judge.setAnswer(rs.getInt("answer"));
				judge.setParse(rs.getString("setParse"));
				
				
				judge.setParse(rs.getString("parse"));
				
				list.add(judge);//将实体对象的值添加到链表对象中
				
				System.out.println("判断题数据查询成功"+judge.getQuestion());
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
	 * 统计选择题
	 */
	
	public long countJudge() {
		long count=0;
		
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		String sql="select count(*) as c from tbl_judge";
		
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
	
}
