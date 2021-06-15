package baseball.vo;

public class ItemEquipInventoryVO {
	
	private int itemquitipid;
	private int characterid;
	private int itemid;
	private int itemlevel;
	
	private String itemName;
	private String Summary;
	private int price;
	
	//i.itemname, i.price, i.summary
	
	
	public int getItemquitipid() {
		return itemquitipid;
	}
	public void setItemquitipid(int itemquitipid) {
		this.itemquitipid = itemquitipid;
	}
	public int getCharacterid() {
		return characterid;
	}
	public void setCharacterid(int characterid) {
		this.characterid = characterid;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public int getItemlevel() {
		return itemlevel;
	}
	public void setItemlevel(int itemlevel) {
		this.itemlevel = itemlevel;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getSummary() {
		return Summary;
	}
	public void setSummary(String summary) {
		Summary = summary;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ItemEquipInventoryVO [itemlevel=" + itemlevel + ", itemName=" + itemName + ", Summary=" + Summary
				+ ", price=" + price + "]";
	}
	
	
	
	


}
