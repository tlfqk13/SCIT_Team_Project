package baseball.vo;

public class QuizVO {
	private int quizNumber;
	private String question;
	private String example1=null;
	private String example2=null;
	private String example3=null;
	private String example4=null;
	private String correct;
	
	public QuizVO() {
		super();
	}

	public QuizVO(int quizNumber, String question, String example1, String example2, String example3, String example4,
			String correct) {
		super();
		this.quizNumber = quizNumber;
		this.question = question;
		this.example1 = example1;
		this.example2 = example2;
		this.example3 = example3;
		this.example4 = example4;
		this.correct = correct;
	}

	public int getQuizNumber() {
		return quizNumber;
	}

	public void setQuizNumber(int quizNumber) {
		this.quizNumber = quizNumber;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getExample1() {
		return example1;
	}

	public void setExample1(String example1) {
		this.example1 = example1;
	}

	public String getExample2() {
		return example2;
	}

	public void setExample2(String example2) {
		this.example2 = example2;
	}

	public String getExample3() {
		return example3;
	}

	public void setExample3(String example3) {
		this.example3 = example3;
	}

	public String getExample4() {
		return example4;
	}

	public void setExample4(String example4) {
		this.example4 = example4;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

	@Override
	public String toString() {
		return "QuizVO [quizNumber=" + quizNumber + ", question=" + question + ", example1=" + example1 + ", example2="
				+ example2 + ", example3=" + example3 + ", example4=" + example4 + ", correct=" + correct + "]";
	}
	
	
	
	
}
