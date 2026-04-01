import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import javax.imageio.ImageIO;

/**
 * Represents a contact in the messaging application.
 * 
 * Stores contact details including name, phone number, unique ID, profile picture, 
 * and date added. Provides validation, formatting, and search functionality.
 *
 * @author Sameer Kaushal
 */
public class Contact {

	// ============ FIELDS ============

	private String name;
	private String username;
	private String number;
	private UUID id;
	private BufferedImage profilePicture;
	private Instant dateAdded;
	
	private ArrayList<Chat> chats;
	
	// ============ CONSTRUCTORS ============
	
	public Contact() {
		name = "";
		setUsername("");
		number = "";
		id = UUID.randomUUID();
		setImage("/defaultUser.jpg");
		this.dateAdded = Instant.now();
		
	}
	
	/**
	 * Creates a new Contact with the specified details.
	 * Sets dateAdded to the current time.
	 *
	 * @param name the contact's name
	 * @param number the contact's phone number
	 * @param id the unique identifier for the contact
	 * @param profilePicture the URL to the contact's profile picture
	 * @throws NullPointerException if name, number, or id is null
	 * @throws IllegalArgumentException if name or number fails validation
	 */
	public Contact(String name, String username, String number, String profilePicturePath) {
		setName(name);
		setUsername(username);
		setNumber(number);
		id = UUID.randomUUID();
		setImage("/defaultUser.jpg");
	}

	/**
	 * Creates a new Contact with the specified details and a custom date added.
	 * Useful for loading contacts from disk.
	 *
	 * @param name the contact's name
	 * @param number the contact's phone number
	 * @param id the unique identifier for the contact
	 * @param profilePicture the URL to the contact's profile picture
	 * @param dateAdded the instant when this contact was added
	 * @throws NullPointerException if name, number, id, or dateAdded is null
	 * @throws IllegalArgumentException if name or number fails validation
	 */
	public Contact(String name, String username, String number, String profilePicturePath, Instant dateAdded) {
		setName(name);
		setUsername(username);
		setNumber(number);
		id = UUID.randomUUID();
		setImage("/defaultUser.jpg");
		this.dateAdded = Objects.requireNonNull(dateAdded, "Date added cannot be null");
	}

	// ============ GETTERS ============

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
	public BufferedImage getProfilePicture() {
		return profilePicture;
	}

	/**
	 * Gets the date and time when this contact was added.
	 * Used for sorting contacts by most recently added.
	 *
	 * @return the instant when the contact was added
	 */
	public Instant getDateAdded() {
		return dateAdded;
	}
	
	public ArrayList<Chat> getChats() {
		return chats;
	}

	public String getUsername() {
		return username;
	}
	
	// ============ SETTERS ============

	/**
	 * Sets the contact's name.
	 *
	 * @param name the contact's name
	 * @throws NullPointerException if name is null
	 * @throws IllegalArgumentException if name fails validation
	 */
	public void setName(String name) {
		this.name = validateName(name);
	}

	/**
	 * Sets the contact's phone number.
	 *
	 * @param number the contact's phone number
	 * @throws NullPointerException if number is null
	 * @throws IllegalArgumentException if number fails validation
	 */
	public void setNumber(String number) {
		this.number = validatePhoneNumber(number);
	}

	/**
	 * Sets the contact's profile picture URL.
	 *
	 * @param profilePicture the URL to the contact's profile picture
	 */
	public void setProfilePicture(BufferedImage profilePicture) {
		this.profilePicture = profilePicture;
	}

	/**
	 * Sets the date and time when this contact was added.
	 * Primarily used when loading contacts from disk.
	 *
	 * @param dateAdded the instant when the contact was added
	 * @throws NullPointerException if dateAdded is null
	 */
	public void setDateAdded(Instant dateAdded) {
		this.dateAdded = Objects.requireNonNull(dateAdded, "Date added cannot be null");
	}
	
	public void setChats(ArrayList<Chat> chats) {
		this.chats = chats;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	// ============ FORMATTED OUTPUT ============

	/**
	 * Returns a formatted string representation of the contact for UI display.
	 * Format: "Name (PhoneNumber)"
	 * Example: "John Doe (+44 1234 567890)"
	 *
	 * @return formatted contact string suitable for display
	 */
	public String getFormattedDisplay() {
		return name + " (" + number + ")";
	}

	/**
	 * Returns a short formatted string with just the name for list displays.
	 *
	 * @return the contact's name
	 */
	public String getFormattedName() {
		return name;
	}

	/**
	 * Returns a detailed formatted string with all contact information.
	 * Format: "Name | PhoneNumber | Added: DateAdded"
	 *
	 * @return detailed formatted contact string
	 */
	public String getFormattedDetail() {
		return name + " | " + number + " | Added: " + dateAdded;
	}

	/**
	 * Returns a formatted string with name and phone number on separate lines.
	 * Useful for card-style displays.
	 *
	 * @return multi-line formatted contact string
	 */
	public String getFormattedCard() {
		return name + "\n" + number;
	}

	// ============ SEARCH & QUERY SUPPORT ============

	/**
	 * Checks if this contact matches the given keyword.
	 * Performs case-insensitive search across name and phone number.
	 *
	 * @param keyword the search keyword
	 * @return true if the contact's name or number contains the keyword, false otherwise
	 */
	public boolean matches(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return false;
		}
		String lowerKeyword = keyword.toLowerCase();
		return name.toLowerCase().contains(lowerKeyword) || number.toLowerCase().contains(lowerKeyword);
	}
	
	
	
	
	/**
	 * @param imagePath
	 */
	public void setImage(String imagePath) {
		try 
		{
			setProfilePicture(ImageIO.read(new File(imagePath)));
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
	}
}