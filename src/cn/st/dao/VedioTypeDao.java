package cn.st.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.st.entity.VedioType;
import cn.st.util.ConnDB;

public class VedioTypeDao {
	/**
	 * 根据id连接数据（作为外键连接） 用类名作方法返回，即返回该类的实体类对象
	 * @param type_id
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:28:15
	 */
	public VedioType findById(int type_id) {
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		VedioType vedioType=null;
		conn=ConnDB.getConnection();//获取数据库连接
		String sql="select * from tbl_type where type_id ='"+type_id+"'";
		try {
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			while (rs.next()) {
				vedioType=new VedioType();
				vedioType.setType_id(rs.getInt("type_id"));
				vedioType.setType_name(rs.getString("type_name"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try {
			    //关闭资源流
				rs.close();
				st.close();
				conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vedioType;
	}
	/**
	 * 查询 全部的视频类型信息 （绑定下拉框 获取值）
	 * @return
	 *  @author 潘盛武
	 * @date 2017年11月27日22:28:15
	 */
	public List<VedioType> queryAllVedioTypeInf() {
		List<VedioType> list=new ArrayList<VedioType>();
		VedioType vedioType=null;//实体对象
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select *from tbl_type";
	    conn=ConnDB.getConnection();//获取数据库连接
	    try {
			ps=conn.prepareStatement(sql);//获取预处理对象
			rs=ps.executeQuery();//执行操作
			//将结果集遍历进实体对象中
			while (rs.next()) {
				vedioType=new VedioType();
				vedioType.setType_id(rs.getInt("type_id"));
				vedioType.setType_name(rs.getString("type_name"));
				list.add(vedioType);//将实体对象的结果添加到链表中
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//关闭流资源
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
	
	
	
}

