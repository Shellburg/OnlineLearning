package cn.st.entity;

public class WatchRecord {
	    /*观看记录表s
		 * @author 潘盛武
		 * @date 2017年11月28日19:44:25
		 * */
		//属性值
		private int  wr_id;
		private String  stu_num;//学号
		private int  up_id;
		private String watchTime;//观看时间
		
		public WatchRecord() {
		}
		
		public WatchRecord(String stu_num, int up_id, String watchTime) {
			super();
			this.stu_num = stu_num;
			this.up_id = up_id;
			this.watchTime = watchTime;
		}

		/**
		 * get set函数
		 * @return
		 */
		public int getWr_id() {
			return wr_id;
		}

		public void setWr_id(int wr_id) {
			this.wr_id = wr_id;
		}
		public String getStu_num() {
			return stu_num;
		}

		public void setStu_num(String stu_num) {
			this.stu_num = stu_num;
		}

		public int getUp_id() {
			return up_id;
		}

		public void setUp_id(int up_id) {
			this.up_id = up_id;
		}

		public String getWatchTime() {
			return watchTime;
		}

		public void setWatchTime(String watchTime) {
			this.watchTime = watchTime;
		}
		
		
		
		
}
