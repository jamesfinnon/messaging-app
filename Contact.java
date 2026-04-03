import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Represents a contact in the messaging application.
 * 
 * Stores contact details including name, phone number, unique ID, profile picture, 
 * and date added. Provides validation, formatting, and search functionality.
 *
 * @author Sameer Kaushal
 */
public class Contact {

	private String name;
	private String username;
	private String number;
	private UUID id;
	private BufferedImage profilePicture;
	private Instant dateAdded;
	
	/**
	 * default constructor
	 * 
	 * @author jamesfinnon
	 */
	public Contact() {
		
		name = "John Doe";
		username = "johndoe123";
		number = "+44 7123 456789";
		id = UUID.randomUUID();
		setImage("defaultUser.jpg");
		this.dateAdded = Instant.now();
		
	}
	
	/**
	 * creates a new Contact with the specified details
	 * sets dateAdded to the current time
	 * 
	 * @author sameerkashaul
	 * @author jamesfinnon
	 *
	 * @param name the contact's name
	 * @param username the contact's username
	 * @param number the contact's phone number
	 * @param profilePicture the path to the contact's profile picture
	 */
	public Contact(String name, String username, String number, String profilePicturePath) {
		
		setName(name);
		setUsername(username);
		setNumber(number);
		id = UUID.randomUUID();
		setImage("defaultUser.jpg");
	}

	/**
	 * used for loading from disk
	 * 
	 * @author sameerkaushal
	 * @author jamesfinnon
	 *
	 * @param name the contact's name
	 * @param username the contact's username
	 * @param number the contact's phone number
	 * @param profilePicture the path to the contact's profile picture
	 * @param dateAdded the instant when this contact was added
	 */
	public Contact(String name, String username, String number, String profilePicturePath, Instant dateAdded) {
		
		setName(name);
		setUsername(username);
		setNumber(number);
		id = UUID.randomUUID();
		setImage("defaultUser.jpg");
		this.dateAdded = Objects.requireNonNull(dateAdded, "Date added cannot be null");
	}
	
    /**
     * @author sameerkashaul
     * 
     * @param name
     * @return
     */
    private static String validateName(String name) {
        String trimmedName = validateAndTrim(name, "Name");
        if (trimmedName.length() > 100)
            throw new IllegalArgumentException("Name cannot exceed 100 characters");
        return trimmedName;
    }

    /**
     * @author sameerkashaul
     * 
     * @param number
     * @return validated number
     */
    private String validateNumber(String number) {
        String trimmedNumber = validateAndTrim(number, "Phone number");
        if (!trimmedNumber.matches("[0-9+\\-()\\s]+")) {
            JOptionPane.showMessageDialog(null, "Phone number contains invalid characters.");
        	return getNumber();
        }
        
        else {
        	long digitCount = trimmedNumber.chars().filter(Character::isDigit).count();
        	if (digitCount < 7 || digitCount > 15) {
        		JOptionPane.showMessageDialog(null, "Phone number must contain 7-15 digits");
        		return getNumber();
        	}
        	return trimmedNumber;
        }
        		
        
    }
    
    /**
     * @author sameerkashaul
     * 
     * @param value
     * @param fieldName
     * @return trimmed string
     */
    private static String validateAndTrim(String value, String fieldName) {
        if (value == null || value.trim().isEmpty())
        	JOptionPane.showMessageDialog(null, fieldName + " cannot be blank");
        return value.trim();
    }

	/**
	 * @author jamesfinnon
	 * 
	 * @param imagePath
	 */
	public void setImage(String imagePath) {
		try 
		{
			setProfilePicture(ImageIO.read(getClass().getResource(imagePath)));
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
	}
	
	public void chooseImage() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select Profile Picture");

		// Optional: restrict to images only
		fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
				"Image files", "jpg", "jpeg", "png", "gif"
		));

		int result = fileChooser.showOpenDialog(null);
		
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();

		    try {
		        BufferedImage img = ImageIO.read(selectedFile);

		        // store in model
		        setProfilePicture(img);

		    } catch (IOException ex) {
		        ex.printStackTrace();
		    }
		}
	 }
	
	// getters and setters
	public String getName() {
		return name;
	}
	
	public String getNumber() {
		return number;
	}

	public UUID getId() {
		return id;
	}

	public BufferedImage getProfilePicture() {
		return profilePicture;
	}

	public Instant getDateAdded() {
		return dateAdded;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setName(String name) {
		this.name = validateName(name);
	}

	public void setNumber(String number) {
		this.number = validateNumber(number);
	}
	
	public void setProfilePicture(BufferedImage profilePicture) {
		this.profilePicture = profilePicture;
	}

	public void setDateAdded(Instant dateAdded) {
		this.dateAdded = dateAdded;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}
}