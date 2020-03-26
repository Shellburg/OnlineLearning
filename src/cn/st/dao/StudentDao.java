package cn.st.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.st.entity.Student;
import cn.st.query.Page;
import cn.st.util.ConnDB;

public class StudentDao {
	
	/**
	 * 根据登录的学号只允许查看自己的信息
	 * @param loginname
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月4日20:17:45
	 */
	public List<Student> queryMyMsg(String stu_num)  {
		    Connection conn=null;
		     ResultSet rs=null;
		    Statement st = null;
		 List<Student> students=new ArrayList<Student>();
		try {
			conn=ConnDB.getConnection();
			String sql ="select * from tbl_stu  where stu_num='"+stu_num+"'";
			st=conn.createStatement();
			rs=st.executeQuery(sql.toString());
			while (rs.next()) {
				Student  stud=new Student();
				stud.setStu_id(rs.getInt("stu_id"));
				stud.setStu_num(rs.getString("stu_num"));
				stud.setStu_name(rs.getString("stu_name"));
				stud.setPsw(rs.getString("psw"));
				stud.setPhone(rs.getString("phone"));
				stud.setEamil(rs.getString("eamil"));
				stud.setDepartment(rs.getString("department"));
				stud.setClassT(rs.getString("classT"));
				stud.setDepartment(rs.getString("department"));
				stud.setGender(rs.getString("gender"));
				students.add(stud);
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
		return students;
	}
	
	
	/**
	 * 根据姓名查询学生信息
	 * @param page
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月28日19:49:09
	 */
	public Page<Student> queryStuInf(String stu_name,Page<Student> page) {
		List<Student> list=new ArrayList<Student>();//链表对象
		Student student2=null;//视频实体对象
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		conn=ConnDB.getConnection();//获取数据库连接
		String query="select * from tbl_stu where 1=1 ";
		StringBuffer sql=new StringBuffer(query);
		//添加查询条件（根据标题和类型查询视频信息）
	    if (stu_name !=null && !"".equals(stu_name)) {
			sql.append(" and stu_name like '%" + stu_name + "%' ");
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
				student2=new Student();
				student2.setStu_id(rs.getInt("stu_id"));
				student2.setStu_num(rs.getString("stu_num"));
				student2.setStu_name(rs.getString("stu_name"));
				student2.setGender(rs.getString("gender"));
				student2.setDepartment(rs.getString("department"));
				student2.setClassT(rs.getString("classT"));
				student2.setPsw(rs.getString("psw"));
				student2.setPhone(rs.getString("phone"));
				student2.setEamil(rs.getString("eamil"));
				list.add(student2);//将遍历的结果集添加到链表对象list中
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
	 * 统计分页条数（根据查询条件统计分页条数 查询条件学生姓名）
	 * @param stu_name
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月27日22:32:08
	 */
	public long countDate(String stu_name) {
		long countTotal=0;//统计值
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		conn=ConnDB.getConnection();//获取数据库连接
		String query="select count(*) as c  from tbl_stu where 1=1 ";
		StringBuffer sql=new StringBuffer(query);
		//添加查询条件（根据学号查询学生信息）
	    if (stu_name!=null&&!"".equals(stu_name)) {
			sql.append(" and stu_name like '%" + stu_name + "%' ");
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
	 * 学生注册（添加学生）
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年11月28日19:49:09
	 */
	public boolean  studentReg(Student student) {
		boolean bol=false;
		PreparedStatement ps=null;
		Connection conn=null;
		conn=ConnDB.getConnection();
		String sql="insert into tbl_stu(stu_num,stu_name,gender,department,classT,psw,phone,eamil)"
				+ "values(?,?,?,?,?,?,?,?)";
		try {
			ps=conn.prepareStatement(sql);//预处理sql 效率快
			ps.setString(1, student.getStu_num());
			ps.setString(2, student.getStu_name());
			ps.setString(3, student.getGender());
			ps.setString(4, student.getDepartment());
			ps.setString(5, student.getClassT());
			ps.setString(6, student.getPsw());
			ps.setString(7, student.getPhone());
			ps.setString(8, student.getEamil());
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
	 * 学生登录
	 * @param student
	 * @return
	 * @date 2017年11月28日21:18:40
	 * @author 潘盛武
	 */
	public Student loginUser(Student student) {
		Student stu=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection conn=null;
		conn=ConnDB.getConnection();
		String sql="select * from tbl_stu  where stu_num=? and psw=?";
		try {
			ps=conn.prepareStatement(sql);//预处理sql语句
			ps.setString(1, student.getStu_num());//登录名将admin get的数据set到ps中进行查询
			ps.setString(2, student.getPsw());//密码
			rs=ps.executeQuery();//查询结果集存放到rs中
			while (rs.next()) {
				stu=new Student();
				stu.setStu_id(rs.getInt("stu_id"));
				stu.setStu_num(rs.getString("stu_num"));
				stu.setPsw(rs.getString("psw"));
				stu.setStu_name(rs.getString("stu_name"));
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
		return stu;
	}
	
	
	/**
	 * 学生学号注册验证（只允许一个学号注册）
	 * @param student
	 * @return
	 * @date 2017年11月28日21:18:40
	 * @author 潘盛武
	 */
	public Student validateStuNum(Student student) {
		Student stu=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection conn=null;
		conn=ConnDB.getConnection();
		String sql="select * from tbl_stu  where stu_num=?";
		try {
			ps=conn.prepareStatement(sql);//预处理sql语句
			ps.setString(1, student.getStu_num());//登录名将admin get的数据set到ps中进行查询
			rs=ps.executeQuery();//查询结果集存放到rs中
			while (rs.next()) {
				stu=new Student();
				stu.setStu_id(rs.getInt("stu_id"));
				stu.setStu_num(rs.getString("stu_num"));
				stu.setPsw(rs.getString("psw"));
				stu.setStu_name(rs.getString("stu_name"));
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
		return stu;
	}
	
	/**
	 * 根据id删除学生信息
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月2日20:06:57
	 */
	public boolean deleteStuById(Student student) {
		boolean bol=false;
	    PreparedStatement ps=null;
	    Connection conn=null;
	    String sql="delete from tbl_stu where stu_id=?";
	    conn=ConnDB.getConnection();
	    try {
			ps=conn.prepareStatement(sql);//sql预处理
			ps.setInt(1, student.getStu_id());
			ps.executeUpdate();
			bol=true;
			System.out.println("学生删除成功");
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
	 * 根据id绑定学生信息（用于修改）
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月2日20:21:05
	 */
	public Student findStuInfById(Student student) {
		Student student2=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select *  from tbl_stu where stu_id=? ";
		conn=ConnDB.getConnection();
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, student.getStu_id());
			rs=ps.executeQuery();
			while (rs.next()) {
				student2=new Student();
				student2.setStu_id(rs.getInt("stu_id"));
				student2.setStu_num(rs.getString("stu_num"));
				student2.setStu_name(rs.getString("stu_name"));
				student2.setGender(rs.getString("gender"));
				student2.setDepartment(rs.getString("department"));
				student2.setClassT(rs.getString("classT"));
				student2.setPsw(rs.getString("psw"));
				student2.setPhone(rs.getString("phone"));
				student2.setEamil(rs.getString("eamil"));
			}
			System.out.println("学生信息绑定成功");
			
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
		return student2;
	}
	/**
	 * 修改学生信息（学号，姓名，性别系别，班级，密码，电话，邮箱）
	 * @param student
	 * @return
	 * @author 潘盛武
	 * @date 2017年12月2日20:28:53 
	 */
	public boolean updateStuInf(Student student) {
		boolean bol=false;
		Connection conn=null;
		PreparedStatement ps=null;
		String sql="update tbl_stu set  department=?,classT=?,psw=?,phone=?,eamil=?,stu_num=?,stu_name=?,gender=? where stu_id=?";
		conn=ConnDB.getConnection();
		try {
			ps=conn.prepareStatement(sql);//sql预处理
			ps.setString(1, student.getDepartment());
			ps.setString(2, student.getClassT());
			ps.setString(3, student.getPsw());
			ps.setString(4, student.getPhone());
			ps.setString(5, student.getEamil());
			ps.setString(6, student.getStu_num());
			ps.setString(7, student.getStu_name());
			ps.setString(8, student.getGender());
			ps.setInt(9, student.getStu_id());
			ps.executeUpdate();
			bol=true;
			System.out.println("学生信息修改成功");
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
}
