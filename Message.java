import java.time.Instant;

public class Message {
	private String content;
	private boolean read;
	private Instant timeOfMessage;
	
	
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