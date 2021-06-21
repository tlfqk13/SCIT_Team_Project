package baseball.vo;

public class QuizScoreVO {
	private int quizscorenumber;
	private String userId;
	private int characterId;
	private String characterName;
	private int correctAnswer;
	private int wrongAnswer;
	private int correctPercent;
	public QuizScoreVO() {
		super();
	}
	

	public QuizScoreVO(String userId, int characterId, String characterName) {
		super();
		this.userId = userId;
		this.characterId = characterId;
		this.characterName = characterName;
	}


	public int getQuizscorenumber() {
		return quizscorenumber;
	}
	public void setQuizscorenumber(int quizscorenumber) {
		this.quizscorenumber = quizscorenumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCharacterId() {
		return characterId;
	}
	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public int getWrongAnswer() {
		return wrongAnswer;
	}
	public void setWrongAnswer(int wrongAnswer) {
		this.wrongAnswer = wrongAnswer;
	}
	public int getCorrectPercent() {
		return correctPercent;
	}
	public void setCorrectPercent(int correctPercent) {
		this.correctPercent = correctPercent;
	}
	@Override
	public String toString() {
		return "QuizScoreVO [quizscorenumber=" + quizscorenumber + ", userId=" + userId + ", characterId=" + characterId
				+ ", characterName=" + characterName + ", correctAnswer=" + correctAnswer + ", wrongAnswer="
				+ wrongAnswer + ", correctPercent=" + correctPercent + "]";
	}
	
	
	
	
	
}
