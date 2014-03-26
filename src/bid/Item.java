package bid;

public class Item {

	private int ID;
	private String description;
	
	// constructor
	public Item(int id, String description) {
		ID = id;
		this.description = description;
	}

	// getter
	public int getId() {
	     return ID;
	}

	// getter
	public String getDescription() {
	     return description;
	}
}
