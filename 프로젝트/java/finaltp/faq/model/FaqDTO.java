package finaltp.faq.model;

public class FaqDTO {

	private String question;
	private String answer;

	public FaqDTO() {
		super();
	}

	public FaqDTO(String question, String answer) {
		super();
		this.question = question;
		this.answer = answer;
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
