# Messaging App

A Java-based messaging application that supports contact management, 1-to-1 and group chats, and a Swing-based GUI.

## What’s in this repo

- **GUI.java** - Swing UI for chats, contacts, search, and profile pages
- **Chat.java** - Chat model (members, messages, name, last-changed timestamp, basic message searching)
- **Contact.java** - Contact model (name/username/number, profile picture handling, chats, validation helpers)
- **User.java** - Extends Contact; manages contacts list and sorting/search helpers
- **Message.java** - Message model (content, sender, read state, timestamp formatting, emoji reactions)
- **defaultUser.jpg** - Default profile image
- **README.md** - This file

---

## Running the app

This project is pure Java (no build tool included). To run:

1. Compile:
   ```bash
   javac *.java
   ```
2. Run:
   ```bash
   java GUI
   ```

> Note: `defaultUser.jpg` must be available on the runtime classpath. The code loads it via `getClass().getResource(...)`.

---

## Core Models

### Chat

The `Chat` class represents a conversation.

**Key fields**
- `LinkedList<Message> messages`
- `Set<Contact> chatMembers`
- `String chatName`
- `Instant lastChanged`

**Key methods**
- `addMember(Contact)` / `removeMember(Contact)`
- `getMessages()` / `getMessagesSize()`
- `getChatMembers()` / `getChatMembersSize()`
- `updateLastChanged()`
- `searchMessages(String searchWord, int searchBy)` *(currently searches message content)*

### Message

The `Message` class represents an individual message in a chat.

**Key fields**
- `String content`
- `Contact sentBy`
- `Instant timeOfMessage`
- `boolean read`
- `Map<String, Set<Contact>> reactions` *(emoji -> users who reacted)*

**Notable behavior**
- `formatTime(Instant)` formats time as `HH:mm`
- `addReaction(String emoji, Contact user)` toggles a user reaction on/off

### Contact

The `Contact` class represents a person in the app.

**Key fields**
- `String name`
- `String username`
- `String number`
- `UUID id`
- `BufferedImage profilePicture`
- `Instant dateAdded`
- `ArrayList<Chat> chats`
- `Chat currentChat`

**Profile pictures**
- `setImage(String imagePath)` loads an image from resources via `getClass().getResource(imagePath)`
- `chooseImage()` opens a `JFileChooser` so the user can select an image

**Validation**
- Name: non-blank, max 100 chars
- Number: must match `[0-9+\-()\s]+` and contain **7–15 digits** (shows a dialog on invalid input)

### User

`User` extends `Contact` and manages a list of saved contacts.

**Key behavior**
- `addContact(Contact)` and `removeContact(Contact)`
- Sort contacts:
  - `sortContactsAlphabetically()`
  - `sortContactsRecent()` *(sorts by `dateAdded`, most recent first)*
- `searchContacts(String searchWord, int searchBy)`
  - `searchBy == 0`: search by name (case-insensitive)
  - `searchBy == 1`: search by username

---

## GUI features (GUI.java)

The Swing UI includes:
- Landing page with chat previews
- Chat view with:
  - Send messages
  - Edit/delete your own messages
  - Emoji reactions
  - Delete chat / edit chat name
- Contacts page with alphabetical/recent sorting
- Contact details + quick access to recent messages
- Profile view and profile editing (including changing profile picture)

---

## Notes / limitations

- There is currently no persistence layer (data is in-memory only).
- Some search hooks in the GUI are placeholders (e.g., search button action).
