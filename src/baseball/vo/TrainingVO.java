package baseball.vo;

public class TrainingVO {
	private int trainingId;
	private String trainingName;
	private String trainingClass;
	private int coachId;
	private String coachName;
	private int coachEffect1;
	private int coachEffect2;
	private int coachDecrease;
	public TrainingVO() {
		super();
	}
	
	
	
	public int getTrainingId() {
		return trainingId;
	}
	public void setTrainingId(int trainingId) {
		this.trainingId = trainingId;
	}
	public String getTrainingName() {
		return trainingName;
	}
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	public String getTrainingClass() {
		return trainingClass;
	}
	public void setTrainingClass(String trainingClass) {
		this.trainingClass = trainingClass;
	}
	public int getCoachId() {
		return coachId;
	}
	public void setCoachId(int coachId) {
		this.coachId = coachId;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public int getCoachEffect1() {
		return coachEffect1;
	}
	public void setCoachEffect1(int coachEffect1) {
		this.coachEffect1 = coachEffect1;
	}
	public int getCoachEffect2() {
		return coachEffect2;
	}
	public void setCoachEffect2(int coachEffect2) {
		this.coachEffect2 = coachEffect2;
	}
	public int getCoachDecrease() {
		return coachDecrease;
	}
	public void setCoachDecrease(int coachDecrease) {
		this.coachDecrease = coachDecrease;
	}
	@Override
	public String toString() {
		return "TrainingVO [trainingId=" + trainingId + ", trainingName=" + trainingName + ", trainingClass="
				+ trainingClass + ", coachId=" + coachId + ", coachName=" + coachName + ", coachEffect1=" + coachEffect1
				+ ", coachEffect2=" + coachEffect2 + ", coachDecrease=" + coachDecrease + "]";
	}
	
	
	
	
}
