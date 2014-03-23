package bid;

public class Item {

	private int ID;
	private String description;
	
	// constructor
	public Item(int iD, String description) {
		ID = iD;
		this.description = description;
	}

	// setter
	public void setId(int id) {
	     ID = id;
	}

	// getter
	public int getId() {
	     return ID;
	}
	
	// setter
	public void setDescription(String info) {
	     description = info;
	}

	// getter
	public String getDescription() {
	     return description;
	}
}
