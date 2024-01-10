package application;


public class Masterpieces {
	
	int Item_id;
	int Mp_id;
	int Wight;
	String Meterial;
	String Masterpieces_Name;
	String Artistes_Name;
	
	
	public Masterpieces() {
		
	}
	
	public Masterpieces(int Item_id,int Mp_id,int Wight,String Meterial,String Masterpieces_Name,String Artistes_Name) {
		
		super();
		this.Item_id=Item_id;
		this.Mp_id=Mp_id;
		this.Wight=Wight;
		this.Meterial=Meterial;
		this.Masterpieces_Name=Masterpieces_Name;
		this.Artistes_Name=Artistes_Name;	
		
	}

	public int getItem_id() {
		return Item_id;
	}

	public void setItem_id(int item_id) {
		Item_id = item_id;
	}

	public int getMp_id() {
		return Mp_id;
	}

	public void setMp_id(int mp_id) {
		Mp_id = mp_id;
	}

	public int getWight() {
		return Wight;
	}

	public void setWight(int wight) {
		Wight = wight;
	}

	public String getMeterial() {
		return Meterial;
	}

	public void setMeterial(String meterial) {
		Meterial = meterial;
	}

	public String getMasterpieces_Name() {
		return Masterpieces_Name;
	}

	public void setMasterpieces_Name(String masterpieces_Name) {
		Masterpieces_Name = masterpieces_Name;
	}

	public String getArtistes_Name() {
		return Artistes_Name;
	}

	public void setArtistes_Name(String artistes_Name) {
		Artistes_Name = artistes_Name;
	}
	

}

