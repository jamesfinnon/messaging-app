import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author jamesfinnon
 */
public class Chat implements Serializable {
    private LinkedList<Message> messages;
    private String chatName;
    private Instant lastChanged;
    
    // set as can be unordered
    private Set<Contact> chatMembers;
    
    public Chat () {
    	messages = new LinkedList<Message>();
    	chatMembers = new HashSet<Contact>();
    	chatName = "";
    }
    
    public int getChatMembersSize() {
		if (chatMembers == null) {
			return 0;
		}
		else {
			return chatMembers.size();
		}
		
	}
    
    public int getMessagesSize() {
		if (messages == null) {
			return 0;
		}
		else {
			return messages.size();
		}
		
	}
    
	public LinkedList<Message> getMessages() {
		return messages;
	}	
	
	public void addMember (Contact contact) {
		chatMembers.add(contact);
		
		if (chatName.equals("")) {
			chatName = contact.getName();
		}
		else {
			chatName = chatName + ", " + contact.getName();
		}
	}
	
	public void removeMember (Contact contact) {
		chatMembers.remove(contact);
	}

	public void setMessages(LinkedList<Message> messages) {
		this.messages = messages;
	}
	
	public void updateLastChanged () {
		setLastChanged(Instant.now());
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
