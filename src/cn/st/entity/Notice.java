package cn.st.entity;

public class Notice {
    /**
     * 公告表属性
     * @author 潘盛武
     * @date 2017年12月11日22:51:53
     */
	private int notice_id;
	private String notice_tiltle;//公告标题
	private String notice_content;//公告内容
	private String pub_name;//公告发布人
	private String create_time;//发布时间
	
	
	public Notice() {
	}


	public int getNotice_id() {
		return notice_id;
	}


	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}


	public String getNotice_tiltle() {
		return notice_tiltle;
	}


	public void setNotice_tiltle(String notice_tiltle) {
		this.notice_tiltle = notice_tiltle;
	}


	public String getNotice_content() {
		return notice_content;
	}


	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}


	public String getPub_name() {
		return pub_name;
	}


	public void setPub_name(String pub_name) {
		this.pub_name = pub_name;
	}


	public String getCreate_time() {
		return create_time;
	}


	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	
	
	
	
}
