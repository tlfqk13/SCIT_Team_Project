package baseball.vo;

public class ItemInventoryVO {
	private int characterid;
	private int itemid;
	private int quantity;
	private String characterName;
	private String itemName;
	
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Override
	public String toString() {
		return "ItemInventoryVO [ characterName=" + characterName + 
									", itemName=" + itemName +
									", quantity= " + quantity
									+"  ] ";
								
	}
	
	
	
	
}
