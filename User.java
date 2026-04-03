import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class User extends Contact {
    private ArrayList<Contact> contacts;
    private boolean alphaSort;
    
    private Contact tempContact;
    
    public User() {
    	contacts = new ArrayList<Contact>();   
    	
    	alphaSort = false;
    	
    	tempContact = new Contact();
    }
    
    
    
	public void addContact(Contact contact) {
		contacts.add(contact);
		
		if (alphaSort) {
			sortContactsAlphabetically();
		}
		else {
			sortContactsRecent();
		}
		
	}
	
	public void removeContact(Contact contact) {
		for (int i = 0; i < getContactsSize(); i++) {
			if (contact == contacts.get(i)) {
				contacts.remove(i);
				return;
			}
		}
	}
	
	public void sortContactsAlphabetically() {
		Collections.sort(contacts, Comparator.comparing(Contact::getName));
	}
	
	public void sortContactsRecent() {
		Collections.sort(contacts, Comparator.comparing(Contact::getDateAdded));
		Collections.reverse(contacts);
	}
	
	
    
	/**
	 * @param searchWord
	 * @param searchBy
	 * @return
	 */
	public ArrayList<Contact> searchContacts(String searchWord, int searchBy) {
		
		Contact contact = new Contact();
		ArrayList<Contact> matches = new ArrayList<Contact>();
		
		if (searchBy == 0) {
			for (int i = 0; i < getContactsSize(); i++) {
				contact = contacts.get(i);
				
				if (contacts.get(i).getName().toLowerCase().contains(searchWord.toLowerCase())) {
					matches.add(contact);
				}
			}
		}
		else if (searchBy == 1) {
			for (int i = 0; i < getContactsSize(); i++) {
				contact = contacts.get(i);
				
				if (contacts.get(i).getUsername().contains(searchWord)) {
					matches.add(contact);
				}
			}
		}
		return matches;
	}
	
	public int getContactsSize() {
		if (contacts == null) {
			return 0;
		}
		else {
			return contacts.size();
		}
		
	}	
	
    // getters and setters
	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}

	public boolean isAlphaSort() {
		return alphaSort;
	}

	public void setAlphaSort(boolean alphaSort) {
		this.alphaSort = alphaSort;
	}



	public Contact getTempContact() {
		return tempContact;
	}

	public void setTempContact(Contact tempContact) {
		this.tempContact = tempContact;
	}
}
