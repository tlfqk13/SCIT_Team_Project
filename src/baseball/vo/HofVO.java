package baseball.vo;

public class HofVO {
	private int hallOfFrameId;
	private String userid;
	private String characterName;
	private int allStat;
	
	public HofVO() {
		super();
	}

	public HofVO(int hallOfFrameId, String userid, String characterName, int allStat) {
		super();
		this.hallOfFrameId = hallOfFrameId;
		this.userid = userid;
		this.characterName = characterName;
		this.allStat = allStat;
	}

	public int getHallOfFrameId() {
		return hallOfFrameId;
	}

	public void setHallOfFrameId(int hallOfFrameId) {
		this.hallOfFrameId = hallOfFrameId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public int getAllStat() {
		return allStat;
	}

	public void setAllStat(int allStat) {
		this.allStat = allStat;
	}

	@Override
	public String toString() {
		return "HofVO [hallOfFrameId=" + hallOfFrameId + ", userid=" + userid + ", characterName=" + characterName
				+ ", allStat=" + allStat + "]";
	}
	
	
	
}
