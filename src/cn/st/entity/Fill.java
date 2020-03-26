package cn.st.entity;

public class Fill {
    
	private int  fi_id;
	private String question;//问题
	private String fill;//填空
	private String answer;//答案
	private String parse;//答案解析
	
	
	
	/**
	 * 构造函数
	 * @param question
	 * @param fill
	 * @param answer
	 * @param parse
	 */
	public Fill() {
		super();
		
	}
	
	public Fill(String question, String fill, String answer, String parse) {
		super();
		this.question = question;
		this.fill = fill;
		this.answer = answer;
		this.parse = parse;
	}


	/**
	 * get set 函数
	 * @return
	 */
	public int getFi_id() {
		return fi_id;
	}

	public void setFi_id(int fi_id) {
		this.fi_id = fi_id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}


	public String getFill() {
		return fill;
	}

	public void setFill(String fill) {
		this.fill = fill;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getParse() {
		return parse;
	}

	public void setParse(String parse) {
		this.parse = parse;
	}
	
	
	
	
	
}
