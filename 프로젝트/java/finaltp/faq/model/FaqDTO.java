package finaltp.faq.model;

public class FaqDTO {

	private int faq_idx;
	private String question;
	private String answer;

	public FaqDTO() {
		super();
	}

	public FaqDTO(int faq_idx, String question, String answer) {
		super();
		this.faq_idx = faq_idx;
		this.question = question;
		this.answer = answer;
	}

	public int getFaq_idx() {
		return faq_idx;
	}

	public void setFaq_idx(int faq_idx) {
		this.faq_idx = faq_idx;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
