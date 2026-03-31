import java.util.ArrayList;
import java.util.LinkedList;

public class Chat {
    private LinkedList<Message> messages;
    private ArrayList<Contact> chatMembers;

	public LinkedList<Message> getMessages() {
		return messages;
	}

	public void setMessages(LinkedList<Message> messages) {
		this.messages = messages;
	}

	public ArrayList<Contact> getChatMembers() {
		return chatMembers;
	}

	public void setChatMembers(ArrayList<Contact> chatMembers) {
		this.chatMembers = chatMembers;
	}
     
}
