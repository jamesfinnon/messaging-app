import java.net.URL;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a contact in the messaging application.
 */
public class Contact {
	private String name;
	private String number; // has to be string as can start with 0, or have code like +44(Country code)
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
	public Contact(String name, String number, UUID id, URL profilePicture) {
		this.name = Objects.requireNonNull(name, "Name cannot be null");
		this.number = Objects.requireNonNull(number, "Number cannot be null");
		this.id = Objects.requireNonNull(id, "ID cannot be null");
		this.profilePicture = profilePicture;
	}
		/**
		 * Gets the contact's name.
		 *
		 * @return the contact's name
		 */
		public String getName() {
			return name;
		}
	
		/**
		 * Gets the contact's phone number.
		 *
		 * @return the contact's phone number
		 */
		public String getNumber() {
			return number;
		}
	
		/**
		 * Gets the contact's unique identifier.
		 *
		 * @return the contact's ID
		 */
		public UUID getId() {
			return id;
		}
	
		/**
		 * Gets the contact's profile picture URL.
		 *
		 * @return the contact's profile picture URL
		 */
		public URL getProfilePicture() {
			return profilePicture;
		}
	}
	