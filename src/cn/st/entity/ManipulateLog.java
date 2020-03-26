package cn.st.entity;


/**
 * 日志操作实体类
 * @author 潘盛武
 * @date 2017年11月27日23:07:00
 */
public class ManipulateLog {
    
	private int   id;
	private String userName;//操作人姓名
	private String mpDescribe;//操作描述
	private String createTime;//操作时间
	private String rem1;//备用字段
	
	//无参构造函数
	public ManipulateLog() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMpDescribe() {
		return mpDescribe;
	}

	public void setMpDescribe(String mpDescribe) {
		this.mpDescribe = mpDescribe;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRem1() {
		return rem1;
	}

	public void setRem1(String rem1) {
		this.rem1 = rem1;
	}
	
	
	
}
