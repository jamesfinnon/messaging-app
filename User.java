import java.util.ArrayList;

public class User extends Contact {
    private ArrayList<Contact> contacts;
    
    public User() {
    	ArrayList<Contact> contacts = new ArrayList<Contact>();    	   	
    }
    
	public void addContact(Contact contact) {
		contacts.add(contact);
	}
    
	public ArrayList<Contact> searchContacts(String searchWord, int searchBy) {
		
		Contact contact = new Contact();
		ArrayList<Contact> matches = new ArrayList<Contact>();
		
		if (searchBy == 0) {
			for (int i = 0; i < contacts.size(); i++) {
				contact = contacts.get(i);
				
				if (contacts.get(i).getName().contains(searchWord)) {
					matches.add(contact);
				}
			}
		}
		else if (searchBy == 1) {
			for (int i = 0; i < contacts.size(); i++) {
				contact = contacts.get(i);
				
				if (contacts.get(i).getUsername().contains(searchWord)) {
					matches.add(contact);
				}
			}
		}
		return matches;
		
		
		
	}
	
    // getters and setters
	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
}