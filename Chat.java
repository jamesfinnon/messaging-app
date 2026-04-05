import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * class to handle the linked list of message objects
 * @author jamesfinnon
 */
public class Chat implements Serializable {
    private LinkedList<Message> messages;
    private String chatName;
    private Instant lastChanged;
    
    // set as can be unordered
    private Set<Contact> chatMembers;
    
    /**
     * default constructor
     * @author jamesfinnon
     */
    public Chat () {
    	messages = new LinkedList<Message>();
    	chatMembers = new HashSet<Contact>();
    	chatName = "";
    	lastChanged = Instant.now();
    }
    
    /**
     * @author jamesfinnon
     * @return the number of chat members
     */
    public int getChatMembersSize() {
		if (chatMembers == null) {
			return 0;
		}
		else {
			return chatMembers.size();
		}
		
	}
    
    /**
     * @author jamesfinnon
     * @return the number of messages in the chat
     */
    public int getMessagesSize() {
		if (messages == null) {
			return 0;
		}
		else {
			return messages.size();
		}
		
	}
    
	
	
	/**
	 * @author jamesfinnon
	 * @param contact to be added
	 */
	public void addMember (Contact contact) {
		chatMembers.add(contact);
		
		if (chatName.equals("")) {
			chatName = contact.getName();
		}
		else {
			chatName = chatName + ", " + contact.getName();
		}
	}
	
	
	/**
	 * @author jamesfinnon
	 * @param contact to be removed
	 */
	public void removeMember (Contact contact) {
		chatMembers.remove(contact);
	}
	
	
	
	
	/**
	 * updates the last changed instant
	 * @author jamesfinnon
	 */
	public void updateLastChanged () {
		setLastChanged(Instant.now());
	}
	
	// getters and setters
	public LinkedList<Message> getMessages() {
		return messages;
	}	
	
	public void setMessages(LinkedList<Message> messages) {
		this.messages = messages;
	}
	
	public Set<Contact> getChatMembers() {
		return chatMembers;
	}

	public void setChatMembers(Set<Contact> chatMembers) {
		this.chatMembers = chatMembers;
	}

	public String getChatName() {
		return chatName;
	}

	public void setChatName(String chatName) {
		this.chatName = chatName;
	}

	public Instant getLastChanged() {
		return lastChanged;
	}

	public void setLastChanged(Instant lastChanged) {
		this.lastChanged = lastChanged;
	}
     
}
