import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * class for individual messages within a chat
 * 
 * @author jamesfinnon
 */
public class Message {
    private String content;
    private boolean read;
    private Instant timeOfMessage;
    private Contact sentBy;

    private Map<String, Set<Contact>> reactions = new HashMap<>();

    // Add or toggle reaction
    public void addReaction(String emoji, Contact user) {
        reactions.putIfAbsent(emoji, new HashSet<>());
        
        Set<Contact> users = reactions.get(emoji);

        if (users.contains(user)) {
            users.remove(user); // toggle off
            if (users.isEmpty()) {
                reactions.remove(emoji);
            }
        } else {
            users.add(user);
        }
    }

    public int getReactionCount(String emoji) {
        return reactions.getOrDefault(emoji, Collections.emptySet()).size();
    }
	
	/**
	 * formats the time inputed to hours:minutes:seconds
	 * taken from the emergency system assignment
	 * 
	 * @author jamesfinnon
	 * @param time
	 * @return formatted time
	 */
	public String formatTime(Instant time) {
    	DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HH:mm")
                        .withZone(ZoneId.systemDefault());
    	
    	return formatter.format(time);
    }
	
	// getters and setters for fields
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	public Instant getTimeOfMessage() {
		return timeOfMessage;
	}
	public void setTimeOfMessage(Instant timeOfMessage) {
		this.timeOfMessage = timeOfMessage;
	}

	public Contact getSentBy() {
		return sentBy;
	}

	public void setSentBy(Contact sentBy) {
		this.sentBy = sentBy;
	}

	public Map<String, Set<Contact>> getReactions() {
		return reactions;
	}

	public void setReactions(Map<String, Set<Contact>> reactions) {
		this.reactions = reactions;
	}

}
