import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
                DateTimeFormatter.ofPattern("HH:mm:ss")
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

}
