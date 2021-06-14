package baseball.vo;

public class ItemVO {
	private int itemId;
	private String itemClass;
	private String itemName;
	private String Summary;
	private int price;
	private int itemEffect1;
	private int itemEffect2;
	
	public ItemVO() {
		super();
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
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

	public int getItemEffect1() {
		return itemEffect1;
	}

	public void setItemEffect1(int itemEffect1) {
		this.itemEffect1 = itemEffect1;
	}

	public int getItemEffect2() {
		return itemEffect2;
	}

	public void setItemEffect2(int itemEffect2) {
		this.itemEffect2 = itemEffect2;
	}

	@Override
	public String toString() {
		return "ItemVO [itemId=" + itemId + ", itemClass=" + itemClass + ", itemName=" + itemName + ", Summary="
				+ Summary + ", price=" + price + ", itemEffect1=" + itemEffect1 + ", itemEffect2=" + itemEffect2 + "]";
	}
	
	
	
}
