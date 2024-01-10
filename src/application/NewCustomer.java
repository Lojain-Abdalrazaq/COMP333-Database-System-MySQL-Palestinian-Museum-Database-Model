package application;


public class NewCustomer {

	
	int Cid;
	String Cname;
	String birthdate;
	String address;
	String gender;
	int E_id;
	public NewCustomer(){
		
	}
	public NewCustomer(int cid, String cname, String birthdate, String address, String gender, int e_id) {
		super();
		Cid = cid;
		Cname = cname;
		this.birthdate = birthdate;
		this.address = address;
		this.gender = gender;
		E_id = e_id;
	}
	
	public int getCid() {
		return Cid;
	}

	public void setCid(int cid) {
		Cid = cid;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getE_id() {
		return E_id;
	}

	public void setE_id(int e_id) {
		E_id = e_id;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	
}
