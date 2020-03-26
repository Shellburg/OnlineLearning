package cn.st.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class SystemListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		System.out.println("=========SystemListener contextInitialized()============");
		//动态获取路径
		String conetextPath=event.getServletContext().getContextPath();
		
		//获取属性的路径
		event.getServletContext().setAttribute("ctx", conetextPath);
		//获取资源(images,css,js)路径
		event.getServletContext().setAttribute("resourcePath", conetextPath+"/resources");
	}

}
