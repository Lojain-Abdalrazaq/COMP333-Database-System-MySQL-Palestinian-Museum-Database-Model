package application;


public class Book {

	int Item_id;
	int Books_id;
	int Page_num;
	String Edition;
	String location;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	

	public Book(int item_id, int books_id, int page_num, String edition, String location) {
		super();
		Item_id = item_id;
		Books_id = books_id;
		Page_num = page_num;
		Edition = edition;
		this.location = location;
	}


	public int getItem_id() {
		return Item_id;
	}

	public void setItem_id(int item_id) {
		Item_id = item_id;
	}

	public int getBooks_id() {
		return Books_id;
	}

	public void setBooks_id(int books_id) {
		Books_id = books_id;
	}

	public int getPage_num() {
		return Page_num;
	}

	public void setPage_num(int page_num) {
		Page_num = page_num;
	}

	public String getEdition() {
		return Edition;
	}

	public void setEdition(String edition) {
		Edition = edition;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
}
