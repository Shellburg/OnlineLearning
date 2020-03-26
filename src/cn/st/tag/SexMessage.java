package cn.st.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class SexMessage extends TagSupport{
	
	private static final long serialVersionUID = 2709961244657704454L;
	
	
	private int sex;

	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		
		try {
			String message = "";
			
			if (sex == 0) {
				message = "女";
			} else if (sex == 1) {//1为真(男)0为假(女)
				message = "男";
			} else {
				message = "未知";
			}
					
			out.write(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return TagSupport.EVAL_PAGE;
	}
	/*public void setSex(Boolean sex) {
		this.sex = sex;
	}*/
	public void setSex(int sex) {
		this.sex = sex;
	}
}
