package application;

public class Itemm {

	int Item_id;
	int Cid;
	int E_id;
	String Item_Description;
	String Item_useage;
	String Item_name;
	String Artistes_Name;
	double Item_Price;

	public Itemm() {
		// TODO Auto-generated constructor stub
	}

	public Itemm(int item_id, int cid, int e_id, String item_Description, String item_usage, String item_name,
			String artistes_name, double item_Price) {
		super();
		Item_id = item_id;
		Cid = cid;
		E_id = e_id;
		Item_Description = item_Description;
		Item_useage = item_usage;
		Item_name = item_name;
		Artistes_Name = artistes_name;
		Item_Price = item_Price;
	}

	public int getItem_id() {
		return Item_id;
	}

	public void setItem_id(int item_id) {
		Item_id = item_id;
	}

	public int getCid() {
		return Cid;
	}

	public void setCid(int cid) {
		Cid = cid;
	}

	public int getE_id() {
		return E_id;
	}

	public void setE_id(int e_id) {
		E_id = e_id;
	}

	public String getItem_Description() {
		return Item_Description;
	}

	public void setItem_Description(String item_Description) {
		Item_Description = item_Description;
	}

	public String getItem_useage() {
		return Item_useage;
	}

	public void setItem_useage(String item_usage) {
		Item_useage = item_usage;
	}

	public String getItem_name() {
		return Item_name;
	}

	public void setItem_name(String item_name) {
		Item_name = item_name;
	}

	public String getArtistes_Name() {
		return Artistes_Name;
	}

	public void setArtistes_Name(String artistes_name) {
		Artistes_Name = artistes_name;
	}

	public double getItem_Price() {
		return Item_Price;
	}

	public void setItem_Price(double item_Price) {
		Item_Price = item_Price;
	}

}
