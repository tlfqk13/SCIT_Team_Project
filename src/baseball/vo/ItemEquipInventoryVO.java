package baseball.vo;

public class ItemEquipInventoryVO {
	
	private int itemquitipid;
	private int characterid;
	private int itemid;
	private int itemlevel;
	
	private String itemName;
	private String Summary;
	private int price;
	
	private int quantity;
	
	//i.itemname, i.price, i.summary
	
	
	public int getItemquitipid() {
		return itemquitipid;
	}
	public ItemEquipInventoryVO(int characterid, int itemid) {
		super();
		this.characterid = characterid;
		this.itemid = itemid;
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
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "[ itemName=" + itemName + ", Summary=" + Summary
				+ ", price=" + price +", °³¼ö= " +quantity + "]";
	}

	
	
	
	


}
