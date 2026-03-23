import java.net.URL;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a contact in the messaging application.
 * Stores contact details including name, phone number, unique ID, profile picture, and date added.
 */
public class Contact {
	private String name;
	private String number; // has to be string as can start with 0, or have code like +44(Country code)
	private UUID id;
	private URL profilePicture;
	private Instant dateAdded; // tracks when contact was added for sorting purposes

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
	public Contact(String name, String number, UUID id, URL profilePicture) {
		this.name = validateName(name);
		this.number = validatePhoneNumber(number);
		this.id = Objects.requireNonNull(id, "ID cannot be null");
		this.profilePicture = profilePicture;
		this.dateAdded = Instant.now();
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
	public Contact(String name, String number, UUID id, URL profilePicture, Instant dateAdded) {
		this.name = validateName(name);
		this.number = validatePhoneNumber(number);
		this.id = Objects.requireNonNull(id, "ID cannot be null");
		this.profilePicture = profilePicture;
		this.dateAdded = Objects.requireNonNull(dateAdded, "Date added cannot be null");
	}

	/**
	 * Private constructor used by the Builder pattern.
	 */
	private Contact(Builder builder) {
		this.name = validateName(builder.name);
		this.number = validatePhoneNumber(builder.number);
		this.id = Objects.requireNonNull(builder.id, "ID cannot be null");
		this.profilePicture = builder.profilePicture;
		this.dateAdded = builder.dateAdded != null ? builder.dateAdded : Instant.now();
	}

	// ============ Getters ============

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

	/**
	 * Gets the date and time when this contact was added.
	 * Used for sorting contacts by most recently added.
	 *
	 * @return the instant when the contact was added
	 */
	public Instant getDateAdded() {
		return dateAdded;
	}

	// ============ Setters ============

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
	public void setProfilePicture(URL profilePicture) {
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

	// ============ Formatted Output Methods ============

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

	// ============ Validation Methods ============

	/**
	 * Validates the contact's name.
	 * Name must be non-null, non-empty, and not exceed 100 characters.
	 *
	 * @param name the name to validate
	 * @return the validated name
	 * @throws NullPointerException if name is null
	 * @throws IllegalArgumentException if name is empty or exceeds 100 characters
	 */
	private String validateName(String name) {
		Objects.requireNonNull(name, "Name cannot be null");
		if (name.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty");
		}
		if (name.length() > 100) {
			throw new IllegalArgumentException("Name cannot exceed 100 characters");
		}
		return name;
	}

	/**
	 * Validates the contact's phone number.
	 * Phone number must be non-null, non-empty, and contain only digits, +, -, (), and spaces.
	 *
	 * @param number the number to validate
	 * @return the validated number
	 * @throws NullPointerException if number is null
	 * @throws IllegalArgumentException if number is empty, exceeds 20 characters, or contains invalid characters
	 */
	private String validatePhoneNumber(String number) {
		Objects.requireNonNull(number, "Number cannot be null");
		if (number.trim().isEmpty()) {
			throw new IllegalArgumentException("Phone number cannot be empty");
		}
		if (number.length() > 20) {
			throw new IllegalArgumentException("Phone number cannot exceed 20 characters");
		}
		// Allow digits, +, -, (), and spaces
		if (!number.matches("[0-9+\\-()\\s]+")) {
			throw new IllegalArgumentException("Phone number contains invalid characters. Only digits, +, -, (), and spaces are allowed");
		}
		return number;
	}

	/**
	 * Checks if the provided name is valid.
	 *
	 * @param name the name to check
	 * @return true if the name is valid, false otherwise
	 */
	public static boolean isValidName(String name) {
		try {
			if (name == null || name.trim().isEmpty() || name.length() > 100) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Checks if the provided phone number is valid.
	 *
	 * @param number the phone number to check
	 * @return true if the phone number is valid, false otherwise
	 */
	public static boolean isValidPhoneNumber(String number) {
		try {
			if (number == null || number.trim().isEmpty() || number.length() > 20) {
				return false;
			}
			return number.matches("[0-9+\\-()\\s]+");
		} catch (Exception e) {
			return false;
		}
	}

	// ============ Search & Query Support ============

	/**
	 * Checks if this contact matches the given keyword.
	 * Performs case-insensitive search across name and phone number.
	 * Useful for FR18 (search all chat names) and contact finding.
	 *
	 * @param keyword the search keyword
	 * @return true if the contact's name or number contains the keyword (case-insensitive), false otherwise
	 */
	public boolean matches(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
			return false;
		}
		String lowerKeyword = keyword.toLowerCase();
		return name.toLowerCase().contains(lowerKeyword) || 
		       number.toLowerCase().contains(lowerKeyword);
	}

	/**
	 * Checks if this contact matches the given keyword using regex.
	 * Performs case-insensitive regex search across name and phone number.
	 *
	 * @param pattern the regex pattern to match
	 * @return true if the contact's name or number matches the pattern (case-insensitive), false otherwise
	 */
	public boolean matchesRegex(String pattern) {
		if (pattern == null || pattern.trim().isEmpty()) {
			return false;
		}
		try {
			return name.matches("(?i)" + pattern) || 
			       number.matches("(?i)" + pattern);
		} catch (Exception e) {
			return false;
		}
	}

	// ============ Object Methods ============

	/**
	 * Compares this contact with another object for equality.
	 * Two contacts are equal if they have the same ID.
	 *
	 * @param o the object to compare with
	 * @return true if the contacts are equal, false otherwise
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Contact)) return false;
		Contact contact = (Contact) o;
		return Objects.equals(id, contact.id);
	}

	/**
	 * Returns a hash code value for the contact based on its ID.
	 *
	 * @return the hash code value
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Returns a string representation of the contact.
	 *
	 * @return a string representation including all contact details
	 */
	@Override
	public String toString() {
		return "Contact{" +
				"name='" + name + '\'' +
				", number='" + number + '\'' +
				", id=" + id +
				", profilePicture=" + profilePicture +
				", dateAdded=" + dateAdded +
				'}';
	}

	// ============ Builder Pattern ============

	/**
	 * Builder class for creating Contact instances with optional parameters.
	 * Makes construction more flexible, especially for loading from disk.
	 * 
	 * Usage:
	 * Contact contact = new Contact.Builder("John Doe")
	 *     .withNumber("+44123456789")
	 *     .withId(UUID.randomUUID())
	 *     .withProfilePicture(profileUrl)
	 *     .withDateAdded(Instant.now())
	 *     .build();
	 */
	public static class Builder {
		private String name;
		private String number;
		private UUID id;
		private URL profilePicture;
		private Instant dateAdded;

		/**
		 * Creates a new Builder with required name parameter.
		 *
		 * @param name the contact's name (required)
		 */
		public Builder(String name) {
			this.name = name;
		}

		/**
		 * Sets the phone number.
		 *
		 * @param number the contact's phone number
		 * @return this Builder instance for method chaining
		 */
		public Builder withNumber(String number) {
			this.number = number;
			return this;
		}

		/**
		 * Sets the contact ID.
		 *
		 * @param id the unique identifier
		 * @return this Builder instance for method chaining
		 */
		public Builder withId(UUID id) {
			this.id = id;
			return this;
		}

		/**
		 * Sets the profile picture URL.
		 *
		 * @param profilePicture the URL to the profile picture
		 * @return this Builder instance for method chaining
		 */
		public Builder withProfilePicture(URL profilePicture) {
			this.profilePicture = profilePicture;
			return this;
		}

		/**
		 * Sets the date when the contact was added.
		 *
		 * @param dateAdded the instant when added
		 * @return this Builder instance for method chaining
		 */
		public Builder withDateAdded(Instant dateAdded) {
			this.dateAdded = dateAdded;
			return this;
		}

		/**
		 * Builds and returns the Contact instance.
		 * Name and ID are required; other fields are optional.
		 *
		 * @return a new Contact instance
		 * @throws NullPointerException if name or id is not set
		 * @throws IllegalArgumentException if name or number fails validation
		 */
		public Contact build() {
			if (name == null) {
				throw new NullPointerException("Name is required for building a Contact");
			}
			if (id == null) {
				throw new NullPointerException("ID is required for building a Contact");
			}
			if (number == null) {
				throw new NullPointerException("Number is required for building a Contact");
			}
			return new Contact(this);
		}
	}
}
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Contact)) return false;
		Contact contact = (Contact) o;
		return Objects.equals(id, contact.id);
	}

	/**
	 * Returns a hash code value for the contact based on its ID.
	 *
	 * @return the hash code value
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	/**
	 * Returns a string representation of the contact.
	 *
	 * @return a string representation including all contact details
	 */
	@Override
	public String toString() {
		return "Contact{" +
				"name='" + name + '\'' +
				", number='" + number + '\'' +
				", id=" + id +
				", profilePicture=" + profilePicture +
				", dateAdded=" + dateAdded +
				'}';
	}

	// ============ Builder Pattern ============

	/**
	 * Builder class for creating Contact instances with optional parameters.
	 * Makes construction more flexible, especially for loading from disk.
	 * 
	 * Usage:
	 * Contact contact = new Contact.Builder("John Doe")
	 *     .withNumber("+44123456789")
	 *     .withId(UUID.randomUUID())
	 *     .withProfilePicture(profileUrl)
	 *     .withDateAdded(Instant.now())
	 *     .build();
	 */
	public static class Builder {
		private String name;
		private String number;
		private UUID id;
		private URL profilePicture;
		private Instant dateAdded;

		/**
		 * Creates a new Builder with required name parameter.
		 *
		 * @param name the contact's name (required)
		 */
		public Builder(String name) {
			this.name = name;
		}

		/**
		 * Sets the phone number.
		 *
		 * @param number the contact's phone number
		 * @return this Builder instance for method chaining
		 */
		public Builder withNumber(String number) {
			this.number = number;
			return this;
		}

		/**
		 * Sets the contact ID.
		 *
		 * @param id the unique identifier
		 * @return this Builder instance for method chaining
		 */
		public Builder withId(UUID id) {
			this.id = id;
			return this;
		}

		/**
		 * Sets the profile picture URL.
		 *
		 * @param profilePicture the URL to the profile picture
		 * @return this Builder instance for method chaining
		 */
		public Builder withProfilePicture(URL profilePicture) {
			this.profilePicture = profilePicture;
			return this;
		}

		/**
		 * Sets the date when the contact was added.
		 *
		 * @param dateAdded the instant when added
		 * @return this Builder instance for method chaining
		 */
		public Builder withDateAdded(Instant dateAdded) {
			this.dateAdded = dateAdded;
			return this;
		}

		/**
		 * Builds and returns the Contact instance.
		 * Name and ID are required; other fields are optional.
		 *
		 * @return a new Contact instance
		 * @throws NullPointerException if name or id is not set
		 * @throws IllegalArgumentException if name or number fails validation
		 */
		public Contact build() {
			if (name == null) {
				throw new NullPointerException("Name is required for building a Contact");
			}
			if (id == null) {
				throw new NullPointerException("ID is required for building a Contact");
			}
			if (number == null) {
				throw new NullPointerException("Number is required for building a Contact");
			}
			return new Contact(this);
		}
	}
}
	