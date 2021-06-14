package baseball.vo;

public class TrainingVO {
	private int trainingId;
	private String trainingName;
	private String trainingClass;
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
	@Override
	public String toString() {
		return "TrainingVO [trainingId=" + trainingId + ", trainingName=" + trainingName + ", trainingClass="
				+ trainingClass + "]";
	}
	
	
}
