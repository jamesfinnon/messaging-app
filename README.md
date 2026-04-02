# Messaging App

A Java-based messaging application with contact management, message handling, and user profiles.

## Project Structure

- **Contact.java** - Contact management with validation, formatting, and search functionality
- **Message.java** - Message model for storing and managing message data
- **User.java** - User profile management
- **README.md** - This file

---

## Contact Class

The `Contact` class represents a contact in the messaging application with comprehensive validation, formatting, and search capabilities.

### Features

#### Contact Fields
- **name** - Contact's name (max 100 characters)
- **number** - Phone number (max 20 characters, supports digits, +, -, (), spaces)
- **id** - Unique UUID identifier
- **profilePicture** - URL to contact's profile picture (optional)
- **dateAdded** - Timestamp when contact was added

#### Constructors

**Standard Constructor**
```java
Contact contact = new Contact(name, number, uuid, profilePictureUrl);
// dateAdded is automatically set to current time
```

**Constructor with Custom Date**
```java
Contact contact = new Contact(name, number, uuid, profilePictureUrl, dateAdded);
// Useful for loading contacts from disk
```

**Builder Pattern**
```java
Contact contact = new Contact.Builder("John Doe")
    .withNumber("+44 1234 567890")
    .withId(UUID.randomUUID())
    .withProfilePicture(profileUrl)
    .withDateAdded(Instant.now())
    .build();
// Provides flexible object construction with method chaining
```

### Methods

#### Getters
- `getName()` - Get contact's name
- `getNumber()` - Get contact's phone number
- `getId()` - Get contact's unique ID
- `getProfilePicture()` - Get contact's profile picture URL
- `getDateAdded()` - Get when contact was added

#### Setters
- `setName(String name)` - Update contact's name (with validation)
- `setNumber(String number)` - Update phone number (with validation)
- `setProfilePicture(URL profilePicture)` - Update profile picture
- `setDateAdded(Instant dateAdded)` - Update date added

#### Formatted Output
- `getFormattedDisplay()` - Returns "Name (PhoneNumber)" for UI display
- `getFormattedName()` - Returns just the contact's name
- `getFormattedDetail()` - Returns "Name | PhoneNumber | Added: DateAdded"
- `getFormattedCard()` - Returns multi-line format with name and number

#### Validation
- `isValidName(String name)` - Static method to check if name is valid
  - Must be non-null, non-empty, max 100 characters
- `isValidPhoneNumber(String number)` - Static method to check if number is valid
  - Must be non-null, non-empty, max 20 characters
  - Only allows: digits, +, -, (), spaces

#### Search & Query
- `matches(String keyword)` - Case-insensitive keyword search in name and number
  - Returns true if contact name or number contains the keyword
- `matchesRegex(String pattern)` - Regex pattern matching (case-insensitive)
  - Searches name and number fields
  - Returns false for invalid regex patterns

#### Object Methods
- `equals(Object o)` - Compares contacts by ID (two contacts are equal if IDs match)
- `hashCode()` - Hash code based on contact ID
- `toString()` - Returns detailed string representation with all fields

### Validation Rules

**Name Validation**
- Cannot be null
- Cannot be empty
- Maximum 100 characters

**Phone Number Validation**
- Cannot be null
- Cannot be empty
- Maximum 20 characters
- Only valid characters: digits (0-9), +, -, (, ), spaces

### Exceptions

- `NullPointerException` - Thrown when required fields (name, number, id, dateAdded) are null
- `IllegalArgumentException` - Thrown when name or number fails validation

### Usage Examples

```java
// Create contact with standard constructor
Contact contact = new Contact("Alice Johnson", "+44 1234 567890", UUID.randomUUID(), null);

// Create contact using builder
Contact contact2 = new Contact.Builder("Bob Smith")
    .withNumber("+1-555-0123")
    .withId(UUID.randomUUID())
    .build();

// Display contact in different formats
System.out.println(contact.getFormattedDisplay());  // Alice Johnson (+44 1234 567890)
System.out.println(contact.getFormattedCard());     // Alice Johnson\n+44 1234 567890

// Search for contacts
if (contact.matches("Alice")) { ... }
if (contact.matchesRegex(".*123.*")) { ... }

// Validate input before creating contact
if (Contact.isValidName(input) && Contact.isValidPhoneNumber(phone)) {
    Contact newContact = new Contact(input, phone, UUID.randomUUID(), null);
}
```

---

## Message Class

*Documentation coming soon*

## User Class

*Documentation coming soon*