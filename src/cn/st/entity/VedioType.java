package cn.st.entity;

public class VedioType {
   
	/**
	 * 视频类型表
	 * @author 潘盛武
	 * @date 2017年11月27日23:07:50
	 * */
	private int type_id;
	private String type_name;//视频类型
     /**
      * 构造函数
      */
	public VedioType() {
		super();
	}
	
	public VedioType(int type_id, String type_name) {
		super();
		this.type_id = type_id;
		this.type_name = type_name;
	}
	/**
	 * set get 函数
	 * @return
	 */
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
	
	
	
	
	
}
