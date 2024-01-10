package application;


public class Event_s {

	int E_id;
	String Location;
	String Dates;
	
	public Event_s() {
		// TODO Auto-generated constructor stub
	}

	
	public Event_s(int e_id, String location, String dates) {
		super();
		E_id = e_id;
		Location = location;
		Dates = dates;
	}


	public int getE_id() {
		return E_id;
	}

	public void setE_id(int e_id) {
		E_id = e_id;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getDates() {
		return Dates;
	}

	public void setDates(String dates) {
		Dates = dates;
	}
	
	

}
