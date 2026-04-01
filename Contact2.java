import java.net.URL;
import java.time.Instant;
import java.util.UUID;

// Contact model class with validation
public class Contact {

    // Contact properties
    private String name;
    private String number;
    private final UUID id;
    private URL profilePicture;
    private Instant dateAdded;
    


    // Default constructor with generic values
    public Contact() {
        this("Unknown", "0000000000", UUID.randomUUID(), null);
    }

    // Constructor with main fields
    public Contact(String name, String number, UUID id, URL profilePicture) {
        this(name, number, id, profilePicture, Instant.now());
    }

    // Full constructor with all parameters and validation
    public Contact(String name, String number, UUID id, URL profilePicture, Instant dateAdded) {
        if (id == null) throw new IllegalArgumentException("ID cannot be null");
        this.name = validateName(name);
        this.number = validateNumber(number);
        this.id = id;
        this.profilePicture = profilePicture;
        this.dateAdded = dateAdded;
    }

    // Accessor methods for all properties
    public String getName()        { return name; }
    public String getNumber()      { return number; }
    public UUID getId()            { return id; }
    public URL getProfilePicture() { return profilePicture; }
    public Instant getDateAdded()  { return dateAdded; }

    // Mutator methods with validation
    public void setName(String name)                  { this.name = validateName(name); }
    public void setNumber(String number)              { this.number = validateNumber(number); }
    public void setProfilePicture(URL profilePicture) { this.profilePicture = profilePicture; }
    public void setDateAdded(Instant dateAdded)       { this.dateAdded = dateAdded; }

    // Check if name or number matches keyword
    public boolean matches(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) return false;
        String lc = keyword.toLowerCase();
        return name.toLowerCase().contains(lc) || number.contains(lc);
    }

    // Compare contacts by ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact other = (Contact) o;
        return id.equals(other.id);
    }

    // Generate hash code from ID
    @Override
    public int hashCode() { return id.hashCode(); }

    // Format contact info as string
    @Override
    public String toString() {
        return "Contact{name='" + name + "', number='" + number + "', id=" + id + "}";
    }

    // Validate and ensure name meets requirements
    private static String validateName(String name) {
        String trimmedName = validateAndTrim(name, "Name");
        if (trimmedName.length() > 100)
            throw new IllegalArgumentException("Name cannot exceed 100 characters");
        return trimmedName;
    }

    // Validate phone number format and digit count
    private static String validateNumber(String number) {
        String trimmedNumber = validateAndTrim(number, "Phone number");
        if (!trimmedNumber.matches("[0-9+\\-()\\s]+"))
            throw new IllegalArgumentException("Phone number contains invalid characters");
        long digitCount = trimmedNumber.chars().filter(Character::isDigit).count();
        if (digitCount < 7 || digitCount > 15)
            throw new IllegalArgumentException("Phone number must contain 7-15 digits");
        return trimmedNumber;
    }

    // Common validation: check null/empty and trim whitespace
    private static String validateAndTrim(String value, String fieldName) {
        if (value == null || value.trim().isEmpty())
            throw new IllegalArgumentException(fieldName + " cannot be blank");
        return value.trim();
    }


}