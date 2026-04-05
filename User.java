import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * class for the program user
 * @author jamesfinnon
 */
public class User extends Contact implements Serializable{
    private ArrayList<Contact> contacts;
    private boolean alphaSort;
    private String lastSearch = "";
    private Contact tempContact;
    
    
    /**
     * default constructor
     * @author jamesfinnon
     */
    public User() {
    	contacts = new ArrayList<Contact>();   
    	
    	alphaSort = false;
    	
    	tempContact = new Contact();
    }
    
    
	/**
	 * @author jamesfinnon
	 * @param contact to be added
	 */
	public void addContact(Contact contact) {
		contacts.add(contact);
		
		if (alphaSort) {
			sortContactsAlphabetically();
		}
		else {
			sortContactsRecent();
		}
		
	}
	
	
	/**
	 * @author jamesfinnon
	 * @param contact to be removed
	 */
	public void removeContact(Contact contact) {
		for (int i = 0; i < getContactsSize(); i++) {
			if (contact == contacts.get(i)) {
				contacts.remove(i);
				return;
			}
		}
	}
	
	/**
	 * @author jamesfinnon
	 */
	public void sortContactsAlphabetically() {
		Collections.sort(contacts, Comparator.comparing(Contact::getName));
	}
	
	/**
	 * @author jamesfinnon
	 */
	public void sortContactsRecent() {
		Collections.sort(contacts, Comparator.comparing(Contact::getDateAdded));
		Collections.reverse(contacts);
	}
	
	
    
	/**
	 * basic linear search
	 * @author jamesfinnon
	 * @param searchWord
	 * @param searchBy 0 for name, 1 for username
	 * @return arraylist of contacts that match the keyword
	 */
	public ArrayList<Contact> searchContacts(String searchWord, int searchBy) {
		
		Contact contact = new Contact();
		ArrayList<Contact> matches = new ArrayList<Contact>();
		
		if (searchBy == 0) {
			for (int i = 0; i < getContactsSize(); i++) {
				contact = contacts.get(i);
				
				if (contact.getName().toLowerCase().contains(searchWord.toLowerCase())) {
					matches.add(contact);
				}
			}
		}
		else if (searchBy == 1) {
			for (int i = 0; i < getContactsSize(); i++) {
				contact = contacts.get(i);
				
				if (contact.getUsername().toLowerCase().contains(searchWord.toLowerCase())) {
					matches.add(contact);
				}
			}
		}
		return matches;
	}
	
	/**
	 * @author jamesfinnon
	 * @return the number of contacts
	 */
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



	public String getLastSearch() {
		return lastSearch;
	}



	public void setLastSearch(String lastSearch) {
		this.lastSearch = lastSearch;
	}
}
