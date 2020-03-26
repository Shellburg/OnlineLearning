package cn.st.entity;

public class VedioUpload {
	/**
	 * 视频上传表
	 * @author 潘盛武
	 * @date 2017年11月27日23:07:50
	 */
	private int up_id;
	private String picture_path;//视频存放路径
	private String title;//标题
	private String describes;//视频描述
	private String up_time;//视频上传时间
	
	private int type_id;//外键视频类型表

	//视频类型实体
	private VedioType vedioType;
	public VedioType getVedioType() {
		return vedioType;
	}
    //根据set函数
	public void setVedioType(VedioType vedioType) {
		this.vedioType = vedioType;
	}


	/**
	 * 构造函数
	 */
	public VedioUpload() {
		super();
		
	}
	
	
	//用于初始化函数 （存取数据）
	public VedioUpload( String title, String describes, String picture_path, String up_time, int type_id) {
		super();
		this.title = title;
		this.describes = describes;
		this.picture_path = picture_path;
		this.up_time = up_time;
		this.type_id = type_id;
	}
	/**
	 * get set 函数 
	 */
	public int getUp_id() {
		return up_id;
	}
	public void setUp_id(int up_id) {
		this.up_id = up_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	public String getPicture_path() {
		return picture_path;
	}
	public void setPicture_path(String picture_path) {
		this.picture_path = picture_path;
	}

	public String getUp_time() {
		return up_time;
	}
	public void setUp_time(String up_time) {
		this.up_time = up_time;
	}
	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	
	
	
	
	
	
}
