package baseball.vo;

public class ItemInventoryVO {
	private int characterid;
	private int itemid;
	private int quantity;
	
	private ItemInventoryVO myitemInventoryVO;//쿼리로 조인할 vo
	
	
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
	
	@Override
	public String toString() {
		return "ItemInventory [characterid=" + characterid + ", itemid=" + itemid + ", quantity=" + quantity + "]";
	}
	
	
	
}
