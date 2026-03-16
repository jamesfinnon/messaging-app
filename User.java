import java.util.ArrayList;

public class User extends Contact {
    private ArrayList<Chat> chats;
    private ArrayList<Contact> contacts;
    
    // getters and setters
	public ArrayList<Chat> getChats() {
		return chats;
	}

	public void setChats(ArrayList<Chat> chats) {
		chats = chats;
	}

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
}