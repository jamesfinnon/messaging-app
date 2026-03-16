import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Message {
	private String content;
	private boolean read;
	private Instant timeOfMessage;
	
	
	/**
	 * formats the time inputed to hours:minutes:seconds
	 * taken from the emergency system assignment
	 * 
	 * @author jamesfinnon
	 * @param time
	 * @return
	 */
	public String formatTime(Instant time) {
    	DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HH:mm:ss")
                        .withZone(ZoneId.systemDefault());
    	
    	return formatter.format(time);
    }	
	
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

}