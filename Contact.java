import java.net.URL;
import java.util.UUID;

public class Contact {
	private String name;
	private String number; // has to be string as can start with 0, or have code like +44
	private UUID id;
	private URL profilePicture;
	
	// getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public URL getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(URL profilePicture) {
		this.profilePicture = profilePicture;
	}
	
}