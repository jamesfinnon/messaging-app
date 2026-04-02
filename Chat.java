import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

public class Chat {
    private LinkedList<Message> messages;
    
    // set as can be unordered
    private Set<Contact> chatMembers;

	public LinkedList<Message> getMessages() {
		return messages;
	}
	
	public ArrayList<Message> searchMessages(String searchWord, int searchBy) {
		
		Message message = new Message();
		ArrayList<Message> matches = new ArrayList<Message>();
		
		for (int i = 0; i < messages.size(); i++) {
			message = messages.get(i);
				
			if (messages.get(i).getContent().toLowerCase().contains(searchWord.toLowerCase())) {
				matches.add(message);
			}
		}
		return matches;
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
     
}
