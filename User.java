import java.util.ArrayList;

public class User extends Contact {
    private ArrayList<Contact> contacts;
    
    public User() {
    	ArrayList<Contact> contacts = new ArrayList<Contact>();    	   	
    }
    
    // getters and setters
	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
}