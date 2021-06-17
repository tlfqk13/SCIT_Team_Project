package baseball.vo;

public class ItemEquipinfoVO {
	private int charaterid;
	private int equipitem1;
	private int equipitem2;
	private int equipitem3;
	private int itemid;
	private int price;
	private String itemName;
	private String summary;
	
	
	public ItemEquipinfoVO(int purchaseId, int purchaseItemId) {
		super();
		this.charaterid=purchaseId;
		this.equipitem1=purchaseItemId;
	}
	public int getCharaterid() {
		return charaterid;
	}
	public void setCharaterid(int charaterid) {
		this.charaterid = charaterid;
	}
	public int getEquipitem1() {
		return equipitem1;
	}
	public void setEquipitem1(int equipitem1) {
		this.equipitem1 = equipitem1;
	}
	public int getEquipitem2() {
		return equipitem2;
	}
	public void setEquipitem2(int equipitem2) {
		this.equipitem2 = equipitem2;
	}
	public int getEquipitem3() {
		return equipitem3;
	}
	public void setEquipitem3(int equipitem3) {
		this.equipitem3 = equipitem3;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	@Override
	public String toString() {
		return "itemName=" + price + ", "
				+ "itemName=" + itemName + ", "
				+ "summary=" + summary + ", "
				 + "price" + price;
	}

	
}
