package application;


public class Clothes {
	
	int Item_id;
	int Clothes_id;
	String Clothes_Size;
	String Clothes_Color;
	String Gender;
	String Clothes_Description;
	
	public Clothes() {
		
	}
	
	public Clothes(int Item_id,int Clothes_id,String Clothes_Size,String Clothes_Color,String Gender,String Clothes_Description) {
		
		super();
		this.Item_id = Item_id;
		this.Clothes_id=Clothes_id;
		this.Clothes_Size=Clothes_Size;
		this.Clothes_Color=Clothes_Color;
		this.Gender=Gender;
		this.Clothes_Description=Clothes_Description;	
		
	}

	public int getItem_id() {
		return Item_id;
	}

	public void setItem_id(int item_id) {
		Item_id = item_id;
	}

	public int getClothes_id() {
		return Clothes_id;
	}

	public void setClothes_id(int clothes_id) {
		Clothes_id = clothes_id;
	}

	public String getClothes_Size() {
		return Clothes_Size;
	}

	public void setClothes_Size(String clothes_Size) {
		Clothes_Size = clothes_Size;
	}

	public String getClothes_Color() {
		return Clothes_Color;
	}

	public void setClothes_Color(String clothes_Color) {
		Clothes_Color = clothes_Color;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getClothes_Description() {
		return Clothes_Description;
	}

	public void setClothes_Description(String clothes_Description) {
		Clothes_Description = clothes_Description;
	}

}

