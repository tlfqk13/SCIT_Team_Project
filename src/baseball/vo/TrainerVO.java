package baseball.vo;

public class TrainerVO {
	private int coachId;
	private String coachName;
	private int coachEffect1;
	private int coachEffect2;
	private int coachDecrease;
	
	public TrainerVO() {
		super();
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
		return "TrainerVO [coachId=" + coachId + ", coachName=" + coachName + ", coachEffect1=" + coachEffect1
				+ ", coachEffect2=" + coachEffect2 + ", coachDecrease=" + coachDecrease + "]";
	}
	
	
	
	
}
