import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
public class Contact implements Serializable {

	private String name;
	private String username;
	private String number;
	private UUID id;
	private transient BufferedImage profilePicture;
	private Instant dateAdded;
	
	private ArrayList<Chat> chats;
    private Chat currentChat;
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
		
		chats = new ArrayList<Chat>();
    	currentChat = new Chat();
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        ImageIO.write(profilePicture, "png", out); 
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        setProfilePicture(ImageIO.read(in));
    }
	
	public void addChat(Chat chat) {
		chats.add(chat);
	}
	
	public ArrayList<Chat> searchChats(String searchWord) {
		
		Chat chat = new Chat();
		ArrayList<Chat> matches = new ArrayList<Chat>();
		
		for (int i = 0; i < getChatsSize(); i++) {
			chat = chats.get(i);
				
			if (chat.getChatName().toLowerCase().contains(searchWord.toLowerCase())) {
				matches.add(chat);
			}
		}		
		return matches;
	}
	
	public ArrayList<Message> searchMessages(String searchWord) {		
		
		ArrayList<Message> matches = new ArrayList<Message>();
		
		for (Chat chat : chats) {
			Message message = new Message();
			for (int i = 0; i < chat.getMessagesSize(); i++) {
				message = chat.getMessages().get(i);
				
				if (chat.getMessages().get(i).getContent().toLowerCase().contains(searchWord.toLowerCase())) {
					message.setTempChat(chat);
					matches.add(message);
					
				}
			}
		}
		return matches;
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
    
    public void updateOrder() {
    	Collections.sort(chats, Comparator.comparing(Chat::getLastChanged));
    	Collections.reverse(chats);
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
	
	public int getChatsSize() {
		if (chats == null) {
			return 0;
		}
		else {
			return chats.size();
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
	
	public Chat getCurrentChat() {
		return currentChat;
	}

	public void setCurrentChat(Chat currentChat) {
		this.currentChat = currentChat;
	}
	
	public ArrayList<Chat> getChats() {
		return chats;
	}
	
	public void setChats(ArrayList<Chat> chats) {
		this.chats = chats;
	}
}