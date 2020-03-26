package cn.st.entity;

public class Options {
   
	/**
	 * 选择题表
	 * @author 潘盛武
	 * @date 2017年11月27日23:07:50
	 * */
	//属性值
	private int  op_id;
	private String question;//问题
	private String op_a;//选项a 
	private String op_b;//选项b 
	private String op_c;//选项c 
	private String op_d;//选项d 
	private String answer;//答案 
	private String parse;//答案解析
	private String op_answer;//正确选择选项
	private int up_id;//对应视频的id
	
	
	//视频类实体
	private VedioUpload vedioUpload;
	public VedioUpload getVedioUpload() {
		return vedioUpload;
	}
	public void setVedioUpload(VedioUpload vedioUpload) {
		this.vedioUpload = vedioUpload;
	}

	/**
	 * 构造函数
	 */
	public Options() {
		super();
		
	}
	
	public Options(String question, String op_a, String op_b, String op_c, String op_d, String answer, String parse,
			String op_answer, int up_id) {
		super();
		this.question = question;
		this.op_a = op_a;
		this.op_b = op_b;
		this.op_c = op_c;
		this.op_d = op_d;
		this.answer = answer;
		this.parse = parse;
		this.op_answer = op_answer;
		this.up_id = up_id;
	}

	/**
	 * get set 函数
	 * @return
	 */

	public int getOp_id() {
		return op_id;
	}

	public void setOp_id(int op_id) {
		this.op_id = op_id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOp_a() {
		return op_a;
	}

	public void setOp_a(String op_a) {
		this.op_a = op_a;
	}

	public String getOp_b() {
		return op_b;
	}

	public void setOp_b(String op_b) {
		this.op_b = op_b;
	}

	public String getOp_c() {
		return op_c;
	}

	public void setOp_c(String op_c) {
		this.op_c = op_c;
	}

	public String getOp_d() {
		return op_d;
	}

	public void setOp_d(String op_d) {
		this.op_d = op_d;
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

	
	public String getOp_answer() {
		return op_answer;
	}
	public void setOp_answer(String op_answer) {
		this.op_answer = op_answer;
	}
	public int getUp_id() {
		return up_id;
	}
	public void setUp_id(int up_id) {
		this.up_id = up_id;
	}

	
	
}
