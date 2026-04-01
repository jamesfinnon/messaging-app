import java.util.LinkedList;
import java.util.Set;

public class Chat {
    private LinkedList<Message> messages;
    private Set<Contact> chatMembers;

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
     
}

