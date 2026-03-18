import java.net.URL;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a contact in the messaging application.
 */
public class Contact {
	private String name;
	private String number; // has to be string as can start with 0, or have code like +44
	private UUID id;
	private URL profilePicture;
	
	/**
	 * Creates a new Contact with the specified details.
	 *
	 * @param name the contact's name
	 * @param number the contact's phone number
	 * @param id the unique identifier for the contact
	 * @param profilePicture the URL to the contact's profile picture
	 */
	
	