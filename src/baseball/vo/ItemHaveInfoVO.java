package baseball.vo;

public class ItemHaveInfoVO {
	private int characterid;
	private int itemid;
	private int quantity;
	private String characterName;
	private String itemName;
	
	public ItemHaveInfoVO() {
		super();
	}
	
	public ItemHaveInfoVO(int charaterid,int itemid,int quantity) {
		this.characterid=charaterid;
		this.itemid=itemid;
		this.quantity=quantity;
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
