package cn.st.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.st.entity.Fill;
import cn.st.query.Page;
import cn.st.util.ConnDB;

public class FillDao {
   
	/**
	 * 导入 填空题
	 */
	public boolean addFill(Fill fill) {
    boolean bol=false;
		
		Connection conn=null;
		PreparedStatement ps=null;
		
		String sql="insert into tbl_fill(question,fill,answer,parse)"
				+ " values(?,?,?,?)";
		conn=ConnDB.getConnection();//获取数据库连接
		try {
			ps=conn.prepareStatement(sql);//预处理sql语句
			ps.setString(1, fill.getQuestion());//实体对象通过get方法获取值set到数据表中
			ps.setString(2, fill.getFill());
			ps.setString(3, fill.getAnswer());
			ps.setString(4, fill.getParse());
			ps.executeUpdate();
			bol=true;//数据添加成功后返回ture
			System.out.println("填空题题导入成功！！！");
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
	public Page<Fill> queryAllFillIng(Page<Fill> page) {
		List<Fill> list=new ArrayList<Fill>();//实例化链表对象用于存取数据
		Fill fill=null;//声明实例化实体对象（判断题）
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		StringBuffer sql =new StringBuffer("select * from tbl_fill  where 1=1");
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
				fill=new Fill();
				fill.setQuestion(rs.getString("question"));
				fill.setFill(rs.getString("fill"));
				fill.setAnswer(rs.getString("answer"));
				fill.setParse(rs.getString("parse"));
				list.add(fill);//将实体对象的值添加到链表对象中
				System.out.println("判断题数据查询成功"+fill.getQuestion());
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
	
	public long countFill() {
		long count=0;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select count(*) as c from tbl_fill";
		
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
