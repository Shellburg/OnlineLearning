package cn.st.entity;
public class Comments {
	   private long c_id;
	   //用户留言
	   private String c_title;//留言标题
	   private String content;//留言内容
	   private String ceateTime;//留言时间
	   private String contentName;//留言人
	   
	   private String rContent;//回复内容
	   private String rDateTime;//回复时间
	   private String rContentName;//回复人
	   private String state;//状态（1表示审核通过0表示未审核 默认未审核）
			
		//无参函数
	   public Comments () {
			
	   }
		public long getC_id() {
			return c_id;
		}
		public void setC_id(long c_id) {
			this.c_id = c_id;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public String getCeateTime() {
			return ceateTime;
		}
		public void setCeateTime(String ceateTime) {
			this.ceateTime = ceateTime;
		}
		public String getContentName() {
			return contentName;
		}
		public void setContentName(String contentName) {
			this.contentName = contentName;
		}
		public String getrContent() {
			return rContent;
		}
		public void setrContent(String rContent) {
			this.rContent = rContent;
		}
		public String getrDateTime() {
			return rDateTime;
		}
		public void setrDateTime(String rDateTime) {
			this.rDateTime = rDateTime;
		}
		public String getrContentName() {
			return rContentName;
		}
		public void setrContentName(String rContentName) {
			this.rContentName = rContentName;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getC_title() {
			return c_title;
		}
		public void setC_title(String c_title) {
			this.c_title = c_title;
		}
		
}
