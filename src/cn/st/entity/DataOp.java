package cn.st.entity;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

//域对象，实现序列化接口
public class DataOp implements Serializable{
   
	/**
	 * 资料表
	 * @author 潘盛武
	 * @date 2017年12月12日22:27:42
	 * */
	//属性值
	private int  data_id;
	private String file_name;//文件名字
	private String upload_path;//文件上传路径 
	private String data_type;//资料类型
	private int download_count;//下载次数
	private String upload_name;//上传人
	private String upload_time;//上传时间
	private String vedio_name;//对应视频名称
	
	private MultipartFile upFile;//文件上传对象
	/**
	 * 构造函数
	 */
	public DataOp() {
		super();
		
	}
	/**
	 * get set 函数
	 * @return
	 */
	public int getData_id() {
		return data_id;
	}
	public void setData_id(int data_id) {
		this.data_id = data_id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getUpload_path() {
		return upload_path;
	}
	public void setUpload_path(String upload_path) {
		this.upload_path = upload_path;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public int getDownload_count() {
		return download_count;
	}
	public void setDownload_count(int download_count) {
		this.download_count = download_count;
	}
	public String getUpload_name() {
		return upload_name;
	}
	public void setUpload_name(String upload_name) {
		this.upload_name = upload_name;
	}
	public String getUpload_time() {
		return upload_time;
	}
	public void setUpload_time(String upload_time) {
		this.upload_time = upload_time;
	}
	public String getVedio_name() {
		return vedio_name;
	}
	public void setVedio_name(String vedio_name) {
		this.vedio_name = vedio_name;
	}
	public MultipartFile getUpFile() {
		return upFile;
	}
	public void setUpFile(MultipartFile upFile) {
		this.upFile = upFile;
	}
	
	
	
}
