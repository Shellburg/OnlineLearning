package cn.st.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import cn.st.entity.VedioType;
import cn.st.entity.VedioUpload;
import cn.st.service.VedioTypeService;
import cn.st.service.VedioUploadService;

@MultipartConfig
public class UploadVedioServelt extends HttpServlet{
	
	/**
	 * 视频上传
	 * @author 潘盛武
	 * @date 2017年11月27日23:11:20
	 */
	String scRealPath =null;
	private String imageDictonry;
	//定义一个常量类共享
	public static final String TYPE_UPLOADIMAG = "uploadimg";
	private String imgDicFilePath;
	public static String DEFAULT_HEADIMG;
	String    picture_path=null;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	
	@Override
	public void init() throws ServletException {
		//1.获取项目根目录
		 scRealPath = this.getServletContext().getRealPath("/");
		System.out.println("获取项目根目录:"+scRealPath);
		//2.通过servletConfig去获取初始化参数 
		ServletConfig sconfig = this.getServletConfig();
		
		this.imgDicFilePath = sconfig.getInitParameter("imgPath");
		//图片的保存路径
		this.imageDictonry = scRealPath + this.imgDicFilePath;
		System.out.println(scRealPath);
		//判断是否已有了文件夹headImage没有的话就创建文件夹
		File file = new File(imageDictonry);
		if(!file.exists()){
			file.mkdirs();
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    System.out.println("读取文件");
		    req.setCharacterEncoding("UTF-8");
		  //查询视频类型（绑定查询下拉框）
			VedioTypeService vedioTypeService=new VedioTypeService();
		    List<VedioType> vedioTypes=vedioTypeService.queryAllVedioTypeInf();
		    req.setAttribute("vedioTypes", vedioTypes);
		    
		    //获取参数type
		    String type = req.getParameter("type");
		    if (TYPE_UPLOADIMAG.equals(type)) {
		    	//该part中保存了上传的文件的类型，路径，文件名等信息。为register.jsp中上传的input的属性为
				Part part = req.getPart("file");
				if(part == null){
					return;
				}
				//获取文件头信息
				String value = part.getHeader("content-disposition");
				//对文件进行分割 图片名字分割
				String values[] = value.split(";");
				//取出文件名字部分
				String fileNameStr = values[values.length-1];
				//再次对文件进行分割
				String[] fn = fileNameStr.split("=");
				//取出文件名字
				String fileName = fn[fn.length-1].substring(1, fn[fn.length-1].length()-1);
				
				//保存图片 separator相当于分隔符/
				part.write(imageDictonry+File.separator+fileName);
				//获取文件存放的路径
				String cp = req.getContextPath();//  bbs/headImag/hello.png
				System.out.println("文件存放的路径:"+cp);
				
				//文件路径分割()
				String fileInPROPath=cp+File.separator+imgDicFilePath+File.separator+fileName;
				System.out.println("============="+fileInPROPath);
				req.setAttribute("headImg", fileInPROPath);
			    picture_path = (String) req.getAttribute("headImg");//   /alumni_information\headImge\012.png
				System.out.println("====="+fileInPROPath);
			    System.out.println(cp);
				System.out.println("imgpath"+picture_path);
				//上传图片后显示图片在views/cosmorama/upload_img.jsp这个页面
				req.getRequestDispatcher("views/vediou_mng/uploadVedio.jsp").forward(req, resp);
			}else {
			//实例化VedioUploadService一个对象
			VedioUploadService service=new VedioUploadService();
			
			//实例化一个VedioUpload实体对象
			VedioUpload vedioUpload=new VedioUpload();
			//获取参数
			String describes = req.getParameter("describes");
			String title = req.getParameter("title");
			String type_id = req.getParameter("type_id");
			//字符串转换成int 
			 int ty_id=Integer.parseInt(type_id);
			 
			
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String up_time = sdf.format(date);
			vedioUpload.setTitle(title);
			vedioUpload.setDescribes(describes);
			vedioUpload.setPicture_path(picture_path);
			vedioUpload.setUp_time(up_time);
			vedioUpload.setType_id(ty_id);
			
			//调用新增方法保存
			service.addVedioInf(vedioUpload,req);
			//服务器重定向
			resp.sendRedirect("views/vediou_mng/result.jsp");
			
			}
	}
	
}
