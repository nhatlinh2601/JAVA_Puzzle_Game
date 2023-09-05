package DTO;

public class QuestionDTO {
	
	private String text,correct_ans,opt1,opt2,opt3,opt4;
	private int id;
	public QuestionDTO(String text, String correct_ans, String opt1, String opt2, String opt3, String opt4, int id) {
		super();
		this.text = text;
		this.correct_ans = correct_ans;
		this.opt1 = opt1;
		this.opt2 = opt2;
		this.opt3 = opt3;
		this.opt4 = opt4;
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public QuestionDTO() {
		
	}
	public String getCorrect_ans() {
		return correct_ans;
	}
	public void setCorrect_ans(String correct_ans) {
		this.correct_ans = correct_ans;
	}
	public String getOpt1() {
		return opt1;
	}
	public void setOpt1(String opt1) {
		this.opt1 = opt1;
	}
	public String getOpt2() {
		return opt2;
	}
	public void setOpt2(String opt2) {
		this.opt2 = opt2;
	}
	public String getOpt3() {
		return opt3;
	}
	public void setOpt3(String opt3) {
		this.opt3 = opt3;
	}
	public String getOpt4() {
		return opt4;
	}
	public void setOpt4(String opt4) {
		this.opt4 = opt4;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public QuestionDTO(String text, String correct_ans, String opt1, String opt2, String opt3, String opt4) {
		super();
		this.text = text;
		this.correct_ans = correct_ans;
		this.opt1 = opt1;
		this.opt2 = opt2;
		this.opt3 = opt3;
		this.opt4 = opt4;
	}
	

	
	

}
