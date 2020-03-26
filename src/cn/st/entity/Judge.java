package cn.st.entity;

public class Judge {
    
	private int  jd_id;
	private String question;//问题
	private int r_true;//正确 1
	private int r_wrong;//错误 0
	private int answer;//答案 1
	private String parse;//答案解析

	/**
	 * 构造函数
	 */
	public Judge() {
		super();
	}
	public Judge( String question, int r_true, int r_wrong, int answer, String parse) {
		super();
		this.question = question;
		this.r_true = r_true;
		this.r_wrong = r_wrong;
		this.answer = answer;
		this.parse = parse;
	}
	
	/**
	 * get set 函数
	 */
	public int getJd_id() {
		return jd_id;
	}
	public void setJd_id(int jd_id) {
		this.jd_id = jd_id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public int getR_true() {
		return r_true;
	}
	public void setR_true(int r_true) {
		this.r_true = r_true;
	}
	public int getR_wrong() {
		return r_wrong;
	}
	public void setR_wrong(int r_wrong) {
		this.r_wrong = r_wrong;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public String getParse() {
		return parse;
	}
	public void setParse(String parse) {
		this.parse = parse;
	}
	
	
	
	
	
	
	
} 
