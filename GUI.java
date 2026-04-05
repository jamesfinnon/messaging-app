import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;
import java.util.Set;

/**
 * GUI class for the messaging application.
 * Manages the user interface and handles all screen navigation.
 * 
 * @author faisalsyero - made the GUI
 * @author jamesfinnon - added user interaction
 */
public class GUI {
	
    Font headerFont = new Font("Arial", Font.BOLD, 18);
    
    User activeUser;
    User secondUser;
    
    
    /**
     * @author jamesfinnon
     */
    private void swapUsers () {
    	User tempUser = activeUser;
    	activeUser = secondUser;
    	secondUser = tempUser;
    }
    
    Stack<String> history = new Stack<String>();
    
    private JFrame window;
    private CardLayout cardLayout;
    private JPanel mainP;
    private JPanel landingP;
    private JPanel chatView;
    private JPanel profilePage;
    private JPanel profileEdit;
    private JPanel contactsPage;
    private JPanel contactsDet;
    private JPanel contactsEdit;
    private JPanel contactsNew;
    private JPanel searchPage;
    private JPanel newChats;
    
    /**
     * Main entry point for the application.
     * @author faisalyero
     */
    public static void main(String[] args) {
        // use the Swing 'invokeLater' method to create a new
        // Runnable object to run on the EDT
        // uses an anonymous class to represent the thread to run
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            // override the 'run' method that any Runnable object has
            // put code here that needs to be run on a thread
            public void run() {
                // call separate method to set up the GUI and run it
                new GUI().mainFrame();
            }
        });

    }
    
    /**
     * Sets up and displays the main application window.
     * @author faisalyero
     */
    public void mainFrame() {
    	
    	activeUser = new User();
    	secondUser = new User();

    	activeUser.addContact(secondUser);
    	secondUser.addContact(activeUser);
    	
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(442, 874);
        window.setTitle("Messaging App");
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout());

        //hp
        JPanel headerP = new JPanel();
        headerP.setLayout(new BorderLayout());
        headerP.setMaximumSize(new Dimension(442, Integer.MAX_VALUE));
        headerP.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.LineBorder(Color.BLACK, 1), new javax.swing.border.EmptyBorder(10, 10, 10, 5)));

        //lhp
        JPanel headerL = new JPanel();
        headerL.setLayout(new BoxLayout(headerL, BoxLayout.Y_AXIS));
        headerP.add(headerL, BorderLayout.WEST);

        //rhp
        JPanel headerR = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        headerP.add(headerR, BorderLayout.EAST);

        //fp
        JPanel footerP = new JPanel();
        footerP.setLayout(new BorderLayout());
        footerP.setPreferredSize(new Dimension(442, 60));
        footerP.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.LineBorder(Color.BLACK, 1), new javax.swing.border.EmptyBorder(5, 5, 5, 5)));

        //mp 
        mainP = new JPanel(new CardLayout());
        cardLayout = (CardLayout) mainP.getLayout();
        mainP.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.LineBorder(Color.BLACK, 1), new javax.swing.border.EmptyBorder(15, 15, 15, 15)));

        //different mps
        landingP = new JPanel(new BorderLayout());
        mainP.add(landingP, "landing");
        
        chatView = new JPanel();
        chatView.setLayout(new BoxLayout(chatView, BoxLayout.Y_AXIS));
        mainP.add(chatView, "chat");
        
        profilePage = new JPanel();
        profilePage.setLayout(new BoxLayout(profilePage, BoxLayout.Y_AXIS));
        profilePage.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainP.add(profilePage, "profile");

        profileEdit = new JPanel(new BorderLayout());
        profileEdit.setLayout(new BoxLayout(profileEdit, BoxLayout.Y_AXIS));
        profileEdit.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainP.add(profileEdit, "Eprofile");

        contactsEdit = new JPanel(new BorderLayout());
        contactsEdit.setLayout(new BoxLayout(contactsEdit, BoxLayout.Y_AXIS));
        contactsEdit.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainP.add(contactsEdit, "contactsEdit");

        contactsPage = new JPanel(new BorderLayout());
        mainP.add(contactsPage, "contactsP");

        contactsDet = new JPanel(new BorderLayout());
        contactsDet.setLayout(new BoxLayout(contactsDet, BoxLayout.Y_AXIS));
        contactsDet.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainP.add(contactsDet, "contactsD");

        contactsNew = new JPanel(new BorderLayout());
        contactsNew.setLayout(new BoxLayout(contactsNew, BoxLayout.Y_AXIS));
        contactsNew.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainP.add(contactsNew, "contactsN");

        searchPage = new JPanel(new BorderLayout());
        mainP.add(searchPage, "search");

        newChats = new JPanel(new BorderLayout());
        mainP.add(newChats, "chatsN");

        window.add(headerP, BorderLayout.NORTH);
        window.add(mainP, BorderLayout.CENTER);
        window.add(footerP, BorderLayout.SOUTH);

        landingPage(headerL, headerR, footerP);

        window.setVisible(true);
        
    }
    
    /**
     * navigates to the previous screen
     * @author jamesfinnon
     */
    public void back (JPanel headerL, JPanel headerR, JPanel footerP) {
    	
    	history.pop();
    	
    	if (history.peek().equals("landing")) {   		
    		landingPage(headerL, headerR, footerP);
            history.pop();
    	}
    	else if (history.peek().equals("profile")) {	
    		profileP(headerL, headerR, footerP);
            history.pop();
    	}
    	else if (history.peek().equals("contactsP")) {   		
    		contactsP(headerL, headerR, footerP);
            history.pop();
    	}
    	else if (history.peek().equals("contactsN")) {  
    		contactsN(headerL, headerR, footerP);
            history.pop();
    	}
        else if (history.peek().equals("Eprofile")) {	
    		profileE(headerL, headerR, footerP);
            history.pop();
    	}
    	else if (history.peek().equals("search")) {
    		searchP(headerL, headerR, footerP);
            history.pop();
    	}
    	else if (history.peek().equals("chatsN")) {
    		chatsN(headerL, headerR, footerP);
            history.pop();
    	}
    	else if (history.peek().equals("chat")) {    		
    		chatP(headerL, headerR, footerP, activeUser.getCurrentChat());
            history.pop();
    	}
    	else if (history.peek().equals("contactsD")) {		
    		contactsD(headerL, headerR, footerP, activeUser.getTempContact());
            history.pop();
    	}
    	
    }
    
    /**
     * navigates to the previous screen where a contact is needed
     * @author jamesfinnon
     */
    public void back (JPanel headerL, JPanel headerR, JPanel footerP, Contact contact) {
    	
    	history.pop();

    	if (history.peek().equals("contactsD")) {		
    		contactsD(headerL, headerR, footerP, contact);
            history.pop();
    	}
        else if (history.peek().equals("contactsEdit")) {	
    		contactsE(headerL, headerR, footerP, contact);
            history.pop();
    	}
        
    	
    }
    
    
    /**
     * main landing page
     * @author faisalyero
     * @author jamesfinnon
     */
    public void landingPage(JPanel headerL, JPanel headerR, JPanel footerP) {
    
    	history.add("landing");
    	activeUser.updateOrder();
    	
        headerL.setLayout(new BoxLayout(headerL, BoxLayout.Y_AXIS));
        footerP.setLayout(new BorderLayout());

        headerL.removeAll();
        headerR.removeAll();
        footerP.removeAll();
        
        //lhp
        JLabel appName = new JLabel("Messages");
        appName.setFont(headerFont);
        headerL.add(Box.createVerticalStrut(3));
        headerL.add(appName);
        headerL.add(Box.createVerticalStrut(8));
        JLabel mainPName = new JLabel("Your name: "+ activeUser.getName());
        headerL.add(mainPName);
        
        //rhp
        JButton searchBut = new JButton("Search");
        searchBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchP(headerL, headerR, footerP);
            }
        });
        JButton profileBut = new JButton("Profile");
        profileBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profileP(headerL, headerR, footerP);
            }
        });
        JButton contactsBut = new JButton("Contacts");
        contactsBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contactsP( headerL,  headerR,  footerP);
            }
        });
        headerR.add(searchBut);
        headerR.add(profileBut);
        headerR.add(contactsBut);
        
        //mp
        landingP.removeAll();
        landingP.setLayout(new BorderLayout());
        
        JPanel resCon = new JPanel();
        resCon.setLayout(new BoxLayout(resCon, BoxLayout.Y_AXIS));
        
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(resCon, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(wrapper);
        
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // runs through all the chats, and adds them in order of interaction
        for (Chat chat : activeUser.getChats()) {
        	
        	chat.updateLastChanged();
        	
        	if (chat.getMessages().isEmpty()) {
        		JButton openChat = new JButton(chat.getChatName());;
        		openChat.setHorizontalAlignment(SwingConstants.LEFT);

        		openChat.setAlignmentX(Component.LEFT_ALIGNMENT);
        		openChat.setPreferredSize(new Dimension(0, 70));
        		openChat.setMinimumSize(new Dimension(0, 70));
        		openChat.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));

        		openChat.addActionListener(e -> {
                    chatP(headerL, headerR, footerP, chat);
                });
        		resCon.add(openChat);
        		continue;
        	}
        	
        	
        	
        	Message message = chat.getMessages().getLast();
        	
        	// builds chat button
            JButton chatPreview = new JButton();
            
            String sentBy = "";
            String read = "";
            
            if (message.getSentBy().equals(activeUser)) {
            	sentBy = "You";
            	if (message.isRead()) {
            		read = "read";
            	}
            	else {
            		read = "unread";
            	}
            }
            else {
            	sentBy = message.getSentBy().getName();
            }
            
            String timeSent = message.formatTime(message.getTimeOfMessage());
            
            chatPreview.setText("<html>" + chat.getChatName() +  "<br>" + sentBy +  ": " + message.getContent() + "<br>" + timeSent + "<br>" + read + "</html>");
            chatPreview.setHorizontalAlignment(SwingConstants.LEFT);

            chatPreview.setAlignmentX(Component.LEFT_ALIGNMENT);
            chatPreview.setPreferredSize(new Dimension(0, 70));
            chatPreview.setMinimumSize(new Dimension(0, 70));
            chatPreview.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));

            chatPreview.addActionListener(e -> {
                chatP(headerL, headerR, footerP, chat);
            });

            resCon.add(chatPreview);
        }

        landingP.add(scroll, BorderLayout.CENTER);
        
        //fp
        JButton newChatBut = new JButton("+ New Chat");
        newChatBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chatsN(headerL, headerR, footerP);
            }
        });
        footerP.add(newChatBut, BorderLayout.CENTER);
        
        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);
        revNrep(landingP);
        
        cardLayout.show(mainP, "landing");
    }

    /**
     * Displays the chat screen for a specific chat.
     * @author faisalyero
     * @author jamesfinnon
     */
    public void chatP(JPanel headerL, JPanel headerR, JPanel footerP, Chat chat) {
        
        history.add("chat");
        
        int maxWidth = (int)(chatView.getWidth() * 0.6);

        headerL.setLayout(new FlowLayout(FlowLayout.LEFT));
        footerP.setLayout(new BorderLayout());

        headerL.removeAll();
        headerR.removeAll();
        footerP.removeAll();

        JButton back = new JButton("← Back");
        headerL.add(back);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	chatView.removeAll();
                back(headerL, headerR, footerP);
            }
        });
        JLabel chatName = new JLabel(activeUser.getCurrentChat().getChatName());
        chatName.setFont(headerFont);
        headerL.add(chatName);

        JTextField messageB = new JTextField();
        footerP.add(messageB, BorderLayout.CENTER);
        
        JButton sendBut = new JButton("Send =>");
        sendBut.addActionListener(new ActionListener() {
            /**
             * sends a message to the chat
             * @author jamesfinnon
             */
            public void actionPerformed(ActionEvent e) {
            	String text = messageB.getText().trim();
                if (text.isEmpty()) return;

                Message msg = new Message();
                msg.setContent(text);
                msg.setSentBy(activeUser);
                msg.setTimeOfMessage(Instant.now());
                msg.setRead(false);
                
                chat.getMessages().add(msg);
                chat.updateLastChanged();
                
                // finds the chat for the other members and updates accordingly
                for (int i = 0; i < activeUser.getContactsSize(); i++) {               	
                	if (chat.getChatMembers().contains(activeUser.getContacts().get(i))) { 
                		if (activeUser.getContacts().get(i).equals(activeUser)) {
                			continue;
                		}
                		for (int j = 0; j < activeUser.getContacts().get(i).getChatsSize(); j++) {
                			if (activeUser.getContacts().get(i).getChats().get(j).equals(chat)) {
                				activeUser.getContacts().get(i).getChats().set(j, chat);
                			}
                		}
                		
                	}
                }

                messageB.setText("");
                
                
                
                // refresh chat panel
                chatView.removeAll();;
                history.pop();
                chat.updateLastChanged();
                chatP(headerL, headerR, footerP, chat);
            }
        });
        footerP.add(sendBut, BorderLayout.EAST);

        JButton searchBut = new JButton("Search");
        searchBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchP(headerL, headerR, footerP);
            }
        });
        headerR.add(searchBut);
        
        JPopupMenu menu = new JPopupMenu();

        JMenuItem deleteChatItem = new JMenuItem("Delete Chat");
        deleteChatItem.addActionListener(new ActionListener() {
        	
            /**
             * deletes the current chat
             * @author jamesfinnon
             */
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                    chatView,
                    "Are you sure you want to delete this chat?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                	
                	// deletes chat for other members
                	for (int i = 0; i < activeUser.getContactsSize(); i++) {               	
                    	if (chat.getChatMembers().contains(activeUser.getContacts().get(i))) { 
                    		if (activeUser.getContacts().get(i).equals(activeUser)) {
                    			continue;
                    		}
                    		for (int j = 0; j < activeUser.getContacts().get(i).getChatsSize(); j++) {
                    			if (activeUser.getContacts().get(i).getChats().get(j).equals(chat)) {
                    				activeUser.getContacts().get(i).getChats().remove(chat);
                    			}
                    		}
                    		
                    	}
                    }
                	
                    activeUser.getChats().remove(chat); 
                    chatView.removeAll();
                    back(headerL, headerR, footerP); 
                }
            }
        });
        menu.add(deleteChatItem);
        JMenuItem editChatName = new JMenuItem("Edit Chat Name");
        editChatName.addActionListener(new ActionListener() {
        	
            /**
             * edits the chat name
             * @author jamesfinnon
             */
            public void actionPerformed(ActionEvent e) {
            	String newChatName = JOptionPane.showInputDialog("Chat Name:");
            	
            	if (newChatName == null) {
            		return;
            	}
            	
            	// edits the name for other chat members
            	for (int i = 0; i < activeUser.getContactsSize(); i++) {               	
                	if (chat.getChatMembers().contains(activeUser.getContacts().get(i))) { 
                		if (activeUser.getContacts().get(i).equals(activeUser)) {
                			continue;
                		}
                		for (int j = 0; j < activeUser.getContacts().get(i).getChatsSize(); j++) {
                			if (activeUser.getContacts().get(i).getChats().get(j).equals(chat)) {
                				activeUser.getContacts().get(i).getChats().get(j).setChatName(newChatName);;
                			}
                		}
                		
                	}
                }
            	
            	chat.setChatName(newChatName);
            	
            	chatView.removeAll();;
                history.pop();
                chatP(headerL, headerR, footerP, chat);
            }
        });
        menu.add(editChatName);
        
        
        JButton conInfBut = new JButton(":");
        conInfBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	menu.show(conInfBut, conInfBut.getWidth()/2, conInfBut.getHeight()/2);
            }
        });
        headerR.add(conInfBut);
        
        JPanel messagesPanel = new JPanel();
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
        messagesPanel.add(Box.createVerticalGlue());

        JScrollPane scrollPane = new JScrollPane(messagesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        chatView.setLayout(new BorderLayout());
        chatView.add(scrollPane, BorderLayout.CENTER);

        // adds message bubbles to the chat page
        for (Message msg : chat.getMessages()) {

            boolean isMe = msg.getSentBy().equals(activeUser);
            
            if (!isMe) {
            	msg.setRead(true);
            }
            
            JPopupMenu messageMenu = new JPopupMenu();
            
            // delete option on right click
            JMenuItem deleteItem = new JMenuItem("Delete");
            deleteItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                	chat.getMessages().remove(msg);
                	
                	chatView.removeAll();;
                    history.pop();
                    chatP(headerL, headerR, footerP, chat);
                }
            });
            
            // edit option on right click
            JMenuItem editItem = new JMenuItem("Edit");
            editItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	String newText = JOptionPane.showInputDialog(chatView, "Edit message:", msg.getContent());
                        if (newText == null) {
                        	return;
                        }
 
                        msg.setContent(newText);
                        
                        chatView.removeAll();
                        history.pop();
                        chatP(headerL, headerR, footerP, chat);
                }
            });
            
            // react option on right click
            String[] emojis = {"👍", "❤️", "😂"};
            JMenu reactMenu = new JMenu("React");
            for (String emoji : emojis) {
                JMenuItem emojiItem = new JMenuItem(emoji);
                emojiItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	
                        msg.addReaction(emoji, activeUser);

                        chatView.removeAll();
                        history.pop();
                        chatP(headerL, headerR, footerP, chat);
                    }
                });
                reactMenu.add(emojiItem);
            }
            
            // only adds edit and delete options if the message is sent by you
            if (isMe) {
            	messageMenu.add(editItem);
            	messageMenu.add(deleteItem);
            }
            messageMenu.addSeparator();
            messageMenu.add(reactMenu);
            
            MouseAdapter rightClickListener = new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showMenu(e);
                    }
                }

                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        showMenu(e);
                    }
                }

                private void showMenu(MouseEvent e) {
                    messageMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            };

            JPanel messageBox = new JPanel();
            messageBox.setLayout(new BoxLayout(messageBox, BoxLayout.Y_AXIS));
            messageBox.setOpaque(false);
            messageBox.setMaximumSize(new Dimension(maxWidth, Short.MAX_VALUE));
            
            JPanel wrapper = new JPanel();
            wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));
            
            // aligns right for your messages, left for everyone else
            if (isMe) {
                wrapper.add(Box.createHorizontalGlue());
                wrapper.add(messageBox);
            } else {
                wrapper.add(messageBox);
                wrapper.add(Box.createHorizontalGlue());
            }
            wrapper.setOpaque(false);

            JLabel senderLabel = new JLabel(msg.getSentBy().getName());
            senderLabel.setFont(new Font("Arial", Font.PLAIN, 11));
            senderLabel.setForeground(Color.GRAY);
            if (isMe) {
            	senderLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            }
            else {
            	senderLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            }

            
            JPanel bubble = new JPanel(new BorderLayout());
            bubble.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
            if (isMe) {
            	bubble.setBackground(Color.green);
            }
            else {
            	bubble.setBackground(Color.gray);
            }
            
            bubble.setMaximumSize(new Dimension(maxWidth, Short.MAX_VALUE));  
            
            JTextArea msgArea = new JTextArea(msg.getContent());
            msgArea.setLineWrap(true);
            msgArea.setWrapStyleWord(true);
            msgArea.setEditable(false);
            msgArea.setOpaque(false);
            msgArea.setBorder(null);
            msgArea.setFont(new Font("Arial", Font.PLAIN, 13));
            
            msgArea.addMouseListener(rightClickListener);
            bubble.addMouseListener(rightClickListener);
            bubble.add(msgArea, BorderLayout.CENTER);

            String time = msg.formatTime(msg.getTimeOfMessage());
            JLabel timeLabel = new JLabel(time);
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 10));
            timeLabel.setForeground(Color.GRAY);
            if (isMe) {
            	timeLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
            }
            else {
            	timeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            }

            messageBox.add(senderLabel);
            messageBox.add(Box.createVerticalStrut(2));
            messageBox.add(bubble);
            messageBox.add(Box.createVerticalStrut(2));
            
            if (!msg.getReactions().isEmpty()) {
            	
            	// builds rection panel under message
                JPanel reactionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
                reactionsPanel.setOpaque(false);

                for (Map.Entry<String, Set<Contact>> entry : msg.getReactions().entrySet()) {
                    String emoji = entry.getKey();
                    int count = entry.getValue().size();

                    boolean reactedByMe = entry.getValue().contains(activeUser);

                    JButton reactionBtn = new JButton(emoji + " " + count);
                    reactionBtn.setMargin(new Insets(2, 6, 2, 6));
                    reactionBtn.setFocusable(false);

                    if (reactedByMe) {
                        reactionBtn.setBackground(Color.YELLOW);
                    } else {
                        reactionBtn.setBackground(Color.LIGHT_GRAY);
                    }

                    reactionBtn.addActionListener(e -> {
                        msg.addReaction(emoji, activeUser);

                        chatView.removeAll();
                        history.pop();
                        chatP(headerL, headerR, footerP, chat);
                    });

                    reactionsPanel.add(reactionBtn);
                }

                if (isMe) {
                    reactionsPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
                } else {
                    reactionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                }

                messageBox.add(Box.createVerticalStrut(3));
                messageBox.add(reactionsPanel);
            }
            messageBox.add(timeLabel);
   
            messagesPanel.add(wrapper);
        }

        
        SwingUtilities.invokeLater(() -> {
            JScrollBar vertical = scrollPane.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });

        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);

        cardLayout.show(mainP, "chat");
    }
    
    /**
     * Displays the user's profile page.
     * @author faisalyero
     * @author jamesfinnon
     */
    public void profileP(JPanel headerL, JPanel headerR, JPanel footerP) {
    	
    	history.add("profile");
    	
    	Font titleFont = new Font("Arial", Font.BOLD, 14);

        headerL.setLayout(new FlowLayout(FlowLayout.LEFT));
        footerP.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        profilePage.removeAll();
        headerL.removeAll();
        headerR.removeAll();
        footerP.removeAll();

        JButton back = new JButton("← Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	profilePage.removeAll();
                back(headerL, headerR, footerP);
            }
        });
        headerL.add(back);
        JLabel myProfile = new JLabel("My Profile");
        myProfile.setFont(headerFont);
        headerL.add(myProfile);
        
        JButton swap = new JButton("Switch Accounts");
        swap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	swapUsers();
            	
            	profilePage.removeAll();
                back(headerL, headerR, footerP);
            }
        });
        headerR.add(swap);       
        
        JPanel picWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        picWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        JLabel profilePic = new JLabel();
        profilePic.setPreferredSize(new Dimension(100,100));
        profilePic.setMaximumSize(profilePic.getPreferredSize());
        profilePic.setBorder(new javax.swing.border.LineBorder(Color.BLACK, 2));
        profilePic.setHorizontalAlignment(JLabel.CENTER);
        profilePic.setVerticalAlignment(JLabel.CENTER);
        
        BufferedImage img = activeUser.getProfilePicture();
        Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        profilePic.setIcon(new ImageIcon(scaled));
        
        profilePic.setForeground(Color.BLACK);

        picWrapper.add(profilePic);
        profilePage.add(picWrapper);

        JLabel profileH = new JLabel(activeUser.getName());
        profileH.setFont(headerFont);
        profileH.setAlignmentX(Component.CENTER_ALIGNMENT);
        profilePage.add(profileH);

        JSeparator separ = new JSeparator(JSeparator.HORIZONTAL);
        separ.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        profilePage.add(separ);
                
        JPanel userPanel = new JPanel(new GridLayout(2, 1));
        userPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        JLabel userT = new JLabel("Username: ");
        userT.setFont(titleFont);
        userPanel.add(userT);
        userPanel.add(new JLabel(activeUser.getUsername()));

        profilePage.add(userPanel);

        JSeparator separ1 = new JSeparator(JSeparator.HORIZONTAL);
        separ1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        profilePage.add(separ1);
        
        JPanel phonePanel = new JPanel(new GridLayout(2, 1));
        phonePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        JLabel phoneT = new JLabel("Phone No: ");
        phoneT.setFont(titleFont);
        phonePanel.add(phoneT);
        phonePanel.add(new JLabel(activeUser.getNumber()));

        profilePage.add(phonePanel);
        
        JSeparator separ2 = new JSeparator(JSeparator.HORIZONTAL);
        separ2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        profilePage.add(separ2);
        
        JPanel editPPanel = new JPanel(new GridLayout(1,1));
        editPPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JButton editProfile = new JButton("Edit Profile");
        editProfile.setMaximumSize(new Dimension(Integer.MAX_VALUE, editProfile.getPreferredSize().height));
        editProfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profileE(headerL, headerR, footerP);
            }
        });
        editPPanel.add(editProfile);
        profilePage.add(editPPanel);
        
        JButton save = new JButton("Save Account");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        
        save.addActionListener(new ActionListener() {
        	
            /**
             * calls for the program to be saved to disk
             * @author jamesfinnon
             */
            public void actionPerformed(ActionEvent e) {
            	
            	JFileChooser chooser = new JFileChooser();
                
            	// ensures saved file is correct file format/extension
                chooser.setFileFilter(new FileNameExtensionFilter("Data Files (*.dat)", "dat"));
                
                // saves file to pc
                int result = chooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String filePath = chooser.getSelectedFile().getAbsolutePath();
                    
                    save(filePath);
                    
                    JOptionPane.showMessageDialog(null, "Account saved successfully.");
                    
                    back(headerL, headerR, footerP);
                }
            }
        });
        
        footerP.add(save, gbc);
        
        JButton load = new JButton("Load Account");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        load.addActionListener(new ActionListener() {
        	
            /**
             * calls for the program to be loaded
             * @author jamesfinnon
             */
            public void actionPerformed(ActionEvent e) {
            	
            	// file chooser
            	JFileChooser chooser = new JFileChooser();
            	
            	
                int result = chooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                	
                    String filePath = chooser.getSelectedFile().getAbsolutePath();
                    load(filePath);
                    
                    JOptionPane.showMessageDialog(null, "Account loaded successfully.");
                    
                    back(headerL, headerR, footerP);
                }
                
            }
        });
        
        footerP.add(load, gbc);
       
        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);
        revNrep(profilePage);

        cardLayout.show(mainP, "profile");
    }

    /**
     * Displays the contacts list page.
     * @author faisalyero
     * @author jamesfinnon
     */
    public void contactsP(JPanel headerL, JPanel headerR, JPanel footerP) {

        history.add("contactsP");

        headerL.setLayout(new FlowLayout(FlowLayout.LEFT));
        footerP.setLayout(new BorderLayout());
        contactsPage.setLayout(new BorderLayout());

        contactsPage.removeAll();
        headerL.removeAll();
        headerR.removeAll();
        footerP.removeAll();

        JButton back = new JButton("← Back");
        back.addActionListener(e -> {
            contactsPage.removeAll();
            back(headerL, headerR, footerP);
        });

        headerL.add(back);

        JLabel header = new JLabel("Contacts");
        header.setFont(headerFont);
        headerL.add(header);

        JButton searchBut = new JButton("Search");
        searchBut.addActionListener(e -> searchP(headerL, headerR, footerP));
        headerR.add(searchBut);

        JButton newCon = new JButton("Add Contacts");
        newCon.addActionListener(e -> contactsN(headerL, headerR, footerP));
        footerP.add(newCon, BorderLayout.CENTER);
        
        JPanel sortH = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        JButton alphaSort = new JButton("Alphabetical");
        alphaSort.addActionListener(e -> {
            activeUser.sortContactsAlphabetically();
            history.pop();
            contactsP(headerL, headerR, footerP);
        });
        
        JButton chronSort = new JButton("Recent");
        chronSort.addActionListener(e -> {
            activeUser.sortContactsRecent();
            history.pop();
            contactsP(headerL, headerR, footerP);
        });

        sortH.add(alphaSort);
        sortH.add(chronSort);
        contactsPage.add(sortH, BorderLayout.NORTH);
        
        JPanel resCon = new JPanel();
        resCon.setLayout(new BoxLayout(resCon, BoxLayout.Y_AXIS));
        
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(resCon, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(wrapper);
        
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // adds list of contacts to the display
        for (int i = 0; i < activeUser.getContactsSize(); i++) {

            Contact contactObj = activeUser.getContacts().get(i); 

            JButton contactBtn = new JButton(contactObj.getName());

            contactBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
            contactBtn.setPreferredSize(new Dimension(0, 40));
            contactBtn.setMinimumSize(new Dimension(0, 40));
            contactBtn.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));

            contactBtn.addActionListener(e -> {
                contactsD(headerL, headerR, footerP, contactObj);
            });

            resCon.add(contactBtn);
        }

        contactsPage.add(scroll, BorderLayout.CENTER);

        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);
        revNrep(contactsPage);

        cardLayout.show(mainP, "contactsP");
    }
    
    /**
     * Displays details for a specific contact.
     * @author faisalyero
     * @author jamesfinnon
     */
    public void contactsD(JPanel headerL, JPanel headerR, JPanel footerP, Contact contact) {

        history.add("contactsD");

        Font titleFont = new Font("Arial", Font.BOLD, 14);

        headerL.setLayout(new FlowLayout(FlowLayout.LEFT));
        footerP.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        contactsDet.removeAll();
        headerL.removeAll();
        headerR.removeAll();
        footerP.removeAll();

        JButton back = new JButton("← Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	contactsDet.removeAll();
                back(headerL, headerR, footerP);
            }
        });
        
        headerL.add(back);
        JLabel myProfile = new JLabel("Contact Details");
        myProfile.setFont(headerFont);
        headerL.add(myProfile);

        //profilepic
        JPanel picWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        picWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        JLabel profilePic = new JLabel();
        profilePic.setPreferredSize(new Dimension(100,100));
        profilePic.setMaximumSize(profilePic.getPreferredSize());
        profilePic.setBorder(new javax.swing.border.LineBorder(Color.BLACK, 2));
        profilePic.setHorizontalAlignment(JLabel.CENTER);
        profilePic.setVerticalAlignment(JLabel.CENTER);
        
        BufferedImage img = contact.getProfilePicture();
        Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        profilePic.setIcon(new ImageIcon(scaled));
        
        profilePic.setForeground(Color.BLACK);

        picWrapper.add(profilePic);
        contactsDet.add(picWrapper);

        JLabel profileH = new JLabel(contact.getName());
        profileH.setFont(headerFont);
        JPanel profileHWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        profileHWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, profileH.getPreferredSize().height));
        profileHWrapper.add(profileH);
        contactsDet.add(profileHWrapper);

        JLabel profNo = new JLabel(contact.getNumber());
        profNo.setAlignmentX(Component.CENTER_ALIGNMENT);
        contactsDet.add(profNo);

        JSeparator separ = new JSeparator(JSeparator.HORIZONTAL);
        separ.setMaximumSize(new Dimension(Integer.MAX_VALUE, 4));
        contactsDet.add(separ);

        JPanel RCMP = new JPanel(new GridLayout(1, 1));
        RCMP.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        JLabel RCM = new JLabel("Recent Messages");
        RCM.setFont(titleFont);
        RCMP.add(RCM);

        contactsDet.add(RCMP);

        JSeparator separ0 = new JSeparator(JSeparator.HORIZONTAL);
        separ0.setMaximumSize(new Dimension(Integer.MAX_VALUE, 4));
        contactsDet.add(separ0);
        
        
        activeUser.setTempContact(contact);
    	
    	boolean found = false;
    	
    	// looks for 1 to 1 chat with user
    	for (int i = 0; i < activeUser.getChatsSize(); i++) {
    		if (activeUser.getChats().get(i).getChatMembers().contains(contact) && activeUser.getChats().get(i).getChatMembers().size() == 2) {
    			activeUser.setCurrentChat(activeUser.getChats().get(i));
    			found = true;
    			break;
    		}
    	}
    	
    	// display 3 most recent messages
    	if (found) {
    		int numMessages = activeUser.getCurrentChat().getMessagesSize();
    		if (numMessages > 0 ) {
    			
    			JPanel mess = new JPanel(new GridLayout(1, 1));
    			mess.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
    			
    			Chat chat = activeUser.getCurrentChat();
    			Message message = activeUser.getCurrentChat().getMessages().getLast();
            	
                JButton goToChat = new JButton();
                
                String sentBy = "";
                String read = "";
                
                if (message.getSentBy().equals(activeUser)) {
                	sentBy = "You";
                	if (message.isRead()) {
                		read = "read";
                	}
                	else {
                		read = "unread";
                	}
                }
                else {
                	sentBy = message.getSentBy().getName();
                }
                
                String timeSent = message.formatTime(message.getTimeOfMessage());
                
                goToChat.setText("<html>" + sentBy +  ": " + message.getContent() + "<br>" + timeSent + "<br>" + read + "</html>");
                goToChat.setHorizontalAlignment(SwingConstants.LEFT);

                goToChat.setAlignmentX(Component.LEFT_ALIGNMENT);
                goToChat.setPreferredSize(new Dimension(0, 70));
                goToChat.setMinimumSize(new Dimension(0, 70));
                goToChat.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));

                goToChat.addActionListener(e -> {
                    chatP(headerL, headerR, footerP, chat);
                });

                mess.add(goToChat);
    			  			
    			
    			contactsDet.add(mess);
    			
    			JSeparator separ1 = new JSeparator(JSeparator.HORIZONTAL);
    	        separ1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
    	        contactsDet.add(separ1);
    	        
    	        
    		}
    		if (numMessages > 1) {
    			JPanel mess = new JPanel(new GridLayout(1, 1));
    			mess.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
    			
    			Chat chat = activeUser.getCurrentChat();
    			Message message = activeUser.getCurrentChat().getMessages().get(numMessages - 2);
            	
                JButton goToChat = new JButton();
                
                String sentBy = "";
                String read = "";
                
                if (message.getSentBy().equals(activeUser)) {
                	sentBy = "You";
                	if (message.isRead()) {
                		read = "read";
                	}
                	else {
                		read = "unread";
                	}
                }
                else {
                	sentBy = message.getSentBy().getName();
                }
                
                String timeSent = message.formatTime(message.getTimeOfMessage());
                
                goToChat.setText("<html>" + sentBy +  ": " + message.getContent() + "<br>" + timeSent + "<br>" + read + "</html>");
                goToChat.setHorizontalAlignment(SwingConstants.LEFT);

                goToChat.setAlignmentX(Component.LEFT_ALIGNMENT);
                goToChat.setPreferredSize(new Dimension(0, 70));
                goToChat.setMinimumSize(new Dimension(0, 70));
                goToChat.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));

                goToChat.addActionListener(e -> {
                    chatP(headerL, headerR, footerP, chat);
                });

                mess.add(goToChat);
    			  			
    			
    			contactsDet.add(mess);
    			
    			JSeparator separ1 = new JSeparator(JSeparator.HORIZONTAL);
    	        separ1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
    	        contactsDet.add(separ1);
    	        
    	        
    		}
    		
    		if (numMessages > 2) {
    			JPanel mess = new JPanel(new GridLayout(1, 1));
    			mess.setMaximumSize(new Dimension(Integer.MAX_VALUE, 70));
    			
    			Chat chat = activeUser.getCurrentChat();
    			Message message = activeUser.getCurrentChat().getMessages().get(numMessages - 3);
            	
                JButton goToChat = new JButton();
                
                String sentBy = "";
                String read = "";
                
                if (message.getSentBy().equals(activeUser)) {
                	sentBy = "You";
                	if (message.isRead()) {
                		read = "read";
                	}
                	else {
                		read = "unread";
                	}
                }
                else {
                	sentBy = message.getSentBy().getName();
                }
                
                String timeSent = message.formatTime(message.getTimeOfMessage());
                
                goToChat.setText("<html>" + sentBy +  ": " + message.getContent() + "<br>" + timeSent + "<br>" + read + "</html>");
                goToChat.setHorizontalAlignment(SwingConstants.LEFT);

                goToChat.setAlignmentX(Component.LEFT_ALIGNMENT);
                goToChat.setPreferredSize(new Dimension(0, 70));
                goToChat.setMinimumSize(new Dimension(0, 70));
                goToChat.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));

                goToChat.addActionListener(e -> {
                    chatP(headerL, headerR, footerP, chat);
                });

                mess.add(goToChat);
    			  			
    			
    			contactsDet.add(mess);
    			
    			JSeparator separ1 = new JSeparator(JSeparator.HORIZONTAL);
    	        separ1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
    	        contactsDet.add(separ1);
    		}
    	}
    	
        JButton message = new JButton("Send Message");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5; 
        gbc.insets = new Insets(2, 2, 2, 2);
        message.addActionListener(new ActionListener() {
        	
            /**
             * attempts to load active 1 to 1 chat with contact, creates new chat otherwise
             * @author jamesfinnon
             */
            public void actionPerformed(ActionEvent e) {
            	
            	activeUser.setTempContact(contact);
            	
            	boolean found = false;
            	
            	for (int i = 0; i < activeUser.getChatsSize(); i++) {
            		if (activeUser.getChats().get(i).getChatMembers().contains(contact) && activeUser.getChats().get(i).getChatMembers().size() == 2) {
            			activeUser.setCurrentChat(activeUser.getChats().get(i));
            			found = true;
            			break;
            		}
            	}
            		
            	if (!found) {         	
            		Chat newChat = new Chat();
            		newChat.addMember(activeUser);
            		newChat.addMember(contact);
            		activeUser.addChat(newChat);
            		contact.addChat(newChat);
            		
            		activeUser.setCurrentChat(newChat);
            		contact.setCurrentChat(newChat);
            	}
            	
            	chatP(headerL, headerR, footerP, activeUser.getCurrentChat());
            	
                
            }
        });
        footerP.add(message, gbc);

        JButton edit = new JButton("Edit");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contactsE(headerL, headerR, footerP, contact);
            }
        });
        footerP.add(edit, gbc);

        JButton delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int confirm = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this contact?","Confirm Delete",JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
            	
                    	activeUser.removeContact(contact);
                    	back(headerL, headerR, footerP);                    	
            	}
                    else {
                    	return;
            	}
            	
                
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        footerP.add(delete, gbc);

        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);
        revNrep(contactsDet);

        cardLayout.show(mainP, "contactsD");        
    }

    /**
     * Displays the new contact creation page.
     * @author faisal yero
     * @author jamesfinnon
     */
    public void contactsN(JPanel headerL, JPanel headerR, JPanel footerP) {

        history.add("contactsN");
        Contact tempContact = new Contact();

        Font titleFont = new Font("Arial", Font.BOLD, 14);

        headerL.setLayout(new FlowLayout(FlowLayout.LEFT));
        footerP.setLayout(new GridBagLayout());

        contactsNew.removeAll();
        headerL.removeAll();
        headerR.removeAll();
        footerP.removeAll();

        JButton back = new JButton("← Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	contactsNew.removeAll();
                back(headerL, headerR, footerP);
            }
        });
        
        headerL.add(back);
        JLabel header = new JLabel("Add Contact");
        header.setFont(headerFont);
        headerL.add(header);
        
        JTextField text1 = new JTextField();
        JTextField text2 = new JTextField();
        JTextField text3 = new JTextField();
        
        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	tempContact.setName(text1.getText());
            	tempContact.setUsername(text2.getText());
            	tempContact.setNumber(text3.getText());
            	
            	activeUser.addContact(tempContact);
        
                profileEdit.removeAll();
                back(headerL, headerR, footerP);
            }
        });
        headerR.add(save);

        //profilepic
        JPanel picWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        picWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        JLabel profilePic = new JLabel();
        profilePic.setPreferredSize(new Dimension(100,100));
        profilePic.setMaximumSize(profilePic.getPreferredSize());
        profilePic.setBorder(new javax.swing.border.LineBorder(Color.BLACK, 2));
        profilePic.setHorizontalAlignment(JLabel.CENTER);
        profilePic.setVerticalAlignment(JLabel.CENTER);
        
        BufferedImage img = tempContact.getProfilePicture();
        Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        profilePic.setIcon(new ImageIcon(scaled));
        
        profilePic.setForeground(Color.BLACK);

        picWrapper.add(profilePic);
        contactsNew.add(picWrapper);

        JButton addPhoto = new JButton("Add Photo");
        addPhoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	tempContact.chooseImage();
            	
            	BufferedImage img = tempContact.getProfilePicture();
                Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                profilePic.setIcon(new ImageIcon(scaled));
            }
        });
        
        
        addPhoto.setFont(headerFont);
        JPanel profileHWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        profileHWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, addPhoto.getPreferredSize().height));
        profileHWrapper.add(addPhoto);
        contactsNew.add(profileHWrapper);

        JPanel userPanel = new JPanel(new GridLayout(6, 1));
        userPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        
        JLabel nameF = new JLabel("Name");
        nameF.setFont(titleFont);
        userPanel.add(nameF);
        
        userPanel.add(text1);
        
        JLabel usNaF = new JLabel("Username");
        usNaF.setFont(titleFont);
        userPanel.add(usNaF);
        
        userPanel.add(text2);

        JLabel pNoF = new JLabel("Phone No");
        pNoF.setFont(titleFont);
        userPanel.add(pNoF);
        
        userPanel.add(text3);

        

        contactsNew.add(userPanel);

        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);
        revNrep(contactsNew);

        cardLayout.show(mainP, "contactsN");
    }

    /**
     * Displays the profile editing page.
     * @author faisalyero
     * @author jamesfinnon
     */
    public void profileE(JPanel headerL, JPanel headerR, JPanel footerP) {

        history.add("Eprofile");
        User tempUser = new User();

        Font titleFont = new Font("Arial", Font.BOLD, 14);

        headerL.setLayout(new FlowLayout(FlowLayout.LEFT));
        footerP.setLayout(new GridBagLayout());

        profileEdit.removeAll();
        headerL.removeAll();
        headerR.removeAll();
        footerP.removeAll();

        JButton back = new JButton("← Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (0 == JOptionPane.showConfirmDialog(null, "Are you sure you want to discard all changes?")) {
            		profileEdit.removeAll();
                    back(headerL, headerR, footerP);
            	}
            	else {
            		return;
            	}
            
            }
        });
        
        headerL.add(back);
        JLabel header = new JLabel("Edit Profile");
        header.setFont(headerFont);
        headerL.add(header);
        
        JTextField text1 = new JTextField();
        text1.setText(activeUser.getName());
        
        JTextField text2 = new JTextField();
        text2.setText(activeUser.getUsername());
        
        JTextField text3 = new JTextField();
        text3.setText(activeUser.getNumber());
        
        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	activeUser.setProfilePicture(tempUser.getProfilePicture());
                activeUser.setName(text1.getText());
                activeUser.setUsername(text2.getText());
                activeUser.setNumber(text3.getText());
        
                profileEdit.removeAll();
                back(headerL, headerR, footerP);
            }
        });
        headerR.add(save);
        
        
        //profilepic
        JPanel picWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        picWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        JLabel profilePic = new JLabel();
        profilePic.setPreferredSize(new Dimension(100,100));
        profilePic.setMaximumSize(profilePic.getPreferredSize());
        profilePic.setBorder(new javax.swing.border.LineBorder(Color.BLACK, 2));
        profilePic.setHorizontalAlignment(JLabel.CENTER);
        profilePic.setVerticalAlignment(JLabel.CENTER);
        
        BufferedImage img = activeUser.getProfilePicture();
        Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        profilePic.setIcon(new ImageIcon(scaled));
        
        profilePic.setForeground(Color.BLACK);
        
        picWrapper.add(profilePic);
        profileEdit.add(picWrapper);

        JButton profileH = new JButton("Add Photo");
        profileH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	tempUser.chooseImage();
            	
            	BufferedImage img = tempUser.getProfilePicture();
                Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                profilePic.setIcon(new ImageIcon(scaled));
            }
        });
        profileH.setFont(headerFont);
        JPanel profileHWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        profileHWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, profileH.getPreferredSize().height));
        profileHWrapper.add(profileH);
        profileEdit.add(profileHWrapper);

        JPanel userPanel = new JPanel(new GridLayout(6, 1));
        userPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        
        JLabel nameF = new JLabel("Name");
        nameF.setFont(titleFont);
        userPanel.add(nameF);
        userPanel.add(text1);
        
        JLabel usNaF = new JLabel("Username");
        usNaF.setFont(titleFont);
        userPanel.add(usNaF);
        userPanel.add(text2);

        JLabel pNoF = new JLabel("Phone No");
        pNoF.setFont(titleFont);
        userPanel.add(pNoF);
        userPanel.add(text3);
        
        profileEdit.add(userPanel);

        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);
        revNrep(profileEdit);

        cardLayout.show(mainP, "Eprofile");
    }

    /**
     * Displays the contact editing page.
     * @author faisalyero
     * @author jamesfinnon
     */
    public void contactsE(JPanel headerL, JPanel headerR, JPanel footerP, Contact contact) {

        history.add("contactsEdit");
        Contact tempContact = new Contact();

        Font titleFont = new Font("Arial", Font.BOLD, 14);

        headerL.setLayout(new FlowLayout(FlowLayout.LEFT));
        footerP.setLayout(new GridBagLayout());

        contactsEdit.removeAll();
        headerL.removeAll();
        headerR.removeAll();
        footerP.removeAll();

        JButton back = new JButton("← Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (0 == JOptionPane.showConfirmDialog(null, "Are you sure you want to discard all changes?")) {
            		contactsEdit.removeAll();
                    back(headerL, headerR, footerP, contact);
            	}
            	else {
            		return;
            	}
            
            }
        });
        
        headerL.add(back);
        JLabel myProfile = new JLabel("Edit Contact");
        myProfile.setFont(headerFont);
        headerL.add(myProfile);
        
        JTextField text1 = new JTextField();
        text1.setText(contact.getName());
        
        JTextField text2 = new JTextField();
        text2.setText(contact.getUsername());
        
        JTextField text3 = new JTextField();
        text3.setText(contact.getNumber());

        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	contact.setProfilePicture(tempContact.getProfilePicture());
            	contact.setName(text1.getText());
            	contact.setUsername(text2.getText());
            	contact.setNumber(text3.getText());
        
                contactsEdit.removeAll();
                back(headerL, headerR, footerP, contact);
            }
        });
        headerR.add(save);

        //profilepic
        JPanel picWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        picWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        JLabel profilePic = new JLabel();
        profilePic.setPreferredSize(new Dimension(100,100));
        profilePic.setMaximumSize(profilePic.getPreferredSize());
        profilePic.setBorder(new javax.swing.border.LineBorder(Color.BLACK, 2));
        profilePic.setHorizontalAlignment(JLabel.CENTER);
        profilePic.setVerticalAlignment(JLabel.CENTER);
        
        BufferedImage img = contact.getProfilePicture();
        Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        profilePic.setIcon(new ImageIcon(scaled));
        
        profilePic.setForeground(Color.BLACK);

        picWrapper.add(profilePic);
        contactsEdit.add(picWrapper);

        JButton profileH = new JButton("Add Photo");
        profileH.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	tempContact.chooseImage();
            	
            	BufferedImage img = tempContact.getProfilePicture();
                Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                profilePic.setIcon(new ImageIcon(scaled));
            }
        });
        
        profileH.setFont(headerFont);
        JPanel profileHWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        profileHWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, profileH.getPreferredSize().height));
        profileHWrapper.add(profileH);
        contactsEdit.add(profileHWrapper);

        JPanel userPanel = new JPanel(new GridLayout(6, 1));
        userPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
        
        JLabel nameF = new JLabel("Name");
        nameF.setFont(titleFont);
        userPanel.add(nameF);
        
        userPanel.add(text1);
        
        JLabel usNaF = new JLabel("Username");
        usNaF.setFont(titleFont);
        userPanel.add(usNaF);
        
        userPanel.add(text2);

        JLabel pNoF = new JLabel("Phone No");
        pNoF.setFont(titleFont);
        userPanel.add(pNoF);
      
        userPanel.add(text3);

        

        contactsEdit.add(userPanel);

        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);
        revNrep(contactsEdit);

        cardLayout.show(mainP, "contactsEdit");
    }

    /**
     * Displays the new chat creation page.
     * @author faisalyero
     * @author jamesfinnon
     */
    public void chatsN(JPanel headerL, JPanel headerR, JPanel footerP) {
        
        Font titleFont = new Font("Arial", Font.BOLD, 14);
        history.add("chatsN");
        
        Chat newChat = new Chat();

        headerL.setLayout(new FlowLayout(FlowLayout.LEFT));
        footerP.setLayout(new BorderLayout());

        newChats.removeAll();
        headerL.removeAll();
        headerR.removeAll();
        footerP.removeAll();

        JButton back = new JButton("← Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	newChats.removeAll();
                back(headerL, headerR, footerP);
            }
        });
        headerL.add(back);
        JLabel header = new JLabel("New Chat");
        header.setFont(headerFont);
        headerL.add(header);

        JButton newCon = new JButton("Start Chat");
        newCon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	System.out.println(activeUser.getChatsSize());
            	
                if (newChat.getChatMembers().isEmpty()) {
                	JOptionPane.showMessageDialog(null, "No contacts selected.");
                	return;
                }
                
                
                
                for (int i = 0; i < activeUser.getChatsSize(); i++) {
                	if (activeUser.getChats().get(i).getChatMembers().equals(newChat.getChatMembers())) {
                		JOptionPane.showMessageDialog(null, "Chat already exists.");
                        return;
                	}
                }
                
                for (Contact contact : newChat.getChatMembers()) {
                	contact.addChat(newChat);
                	contact.setCurrentChat(newChat);
                }
                
                newChat.addMember(activeUser);
                
                activeUser.addChat(newChat);
                activeUser.setCurrentChat(newChat);
                	  	
                chatP(headerL, headerR, footerP, activeUser.getCurrentChat());
            }
        });
        footerP.add(newCon, BorderLayout.CENTER);

        JPanel sortH = new JPanel(new GridLayout(2, 1, 0, 6));
        

        JLabel direcInf = new JLabel("Select Contacts");
        direcInf.setFont(titleFont);
        sortH.add(direcInf);

        newChats.add(sortH, BorderLayout.NORTH);

        JPanel resCon = new JPanel();
        resCon.setLayout(new BoxLayout(resCon, BoxLayout.Y_AXIS));
        
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(resCon, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(wrapper);
        
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      
        for (int i = 0; i < activeUser.getContactsSize(); i++) {

        	Contact contactObj = activeUser.getContacts().get(i); 

            JButton contactBtn = new JButton(contactObj.getName());

            contactBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
            contactBtn.setPreferredSize(new Dimension(0, 40));
            contactBtn.setMinimumSize(new Dimension(0, 40));
            contactBtn.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));

            contactBtn.addActionListener(e -> {
            	
                if (newChat.getChatMembers().contains(contactObj)) {
                	newChat.removeMember(contactObj);
                	contactBtn.setBackground(null);
                }
                else {
                	newChat.addMember(contactObj);               	
                	contactBtn.setBackground(Color.lightGray);
                }
            });

            resCon.add(contactBtn);
        }

        newChats.add(scroll, BorderLayout.CENTER);

        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);
        revNrep(newChats);

        cardLayout.show(mainP, "chatsN");
    }

    /**
     * Displays the search page for chats and messages.
     * @author faisalyero
     * @author jamesfinnon
     */
    public void searchP(JPanel headerL, JPanel headerR, JPanel footerP) {  
    	
    	Font titleFont = new Font("Arial", Font.BOLD, 14);
        history.add("search");
        
        String keyword = activeUser.getLastSearch();
        
        headerL.setLayout(new FlowLayout(FlowLayout.LEFT));
        footerP.setLayout(new BorderLayout());

        searchPage.removeAll();
        headerL.removeAll();
        headerR.removeAll();
        footerP.removeAll();

        JButton back = new JButton("← Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	searchPage.removeAll();
                back(headerL, headerR, footerP);
            }
        });
        headerL.add(back);
        JLabel header = new JLabel("Search");
        header.setFont(headerFont);
        headerL.add(header);
        
        JTextField searchField = new JTextField();
        JPanel searchPanel = new JPanel(new BorderLayout());
        
        ArrayList<Contact> foundContactsName = new ArrayList<Contact>();
        ArrayList<Contact> foundContactsUser = new ArrayList<Contact>();
        ArrayList<Chat> foundChats = new ArrayList<Chat>();
        ArrayList<Message> foundMessages = new ArrayList<Message>();
        
        searchField.setText(keyword);    
        
        if (!keyword.isBlank())
        {
        	foundContactsName = activeUser.searchContacts(keyword, 0);
        	foundContactsUser = activeUser.searchContacts(keyword, 1);
        	foundChats = activeUser.searchChats(keyword);
        	foundMessages = activeUser.searchMessages(keyword);
        }
        
        JPanel resCon = new JPanel();
        resCon.setLayout(new BoxLayout(resCon, BoxLayout.Y_AXIS));
        
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(resCon, BorderLayout.NORTH);
        
        JScrollPane scroll = new JScrollPane(wrapper);
       
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        if (!foundContactsUser.isEmpty()) {
        
        	JLabel contactNameH = new JLabel("Contacts (By Name): ");
        	contactNameH.setFont(titleFont);
       		resCon.add(contactNameH);
       	
       		for (Contact contact : foundContactsName) { 
       		
       			JButton contactBtn = new JButton(contact.getName());

       			contactBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
       			contactBtn.setPreferredSize(new Dimension(0, 40));
       			contactBtn.setMinimumSize(new Dimension(0, 40));
       			contactBtn.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));

       			contactBtn.addActionListener(f -> {
       				contactsD(headerL, headerR, footerP, contact);
       			});

       			resCon.add(contactBtn);
       		}
       	
        }
       	
        if (!foundContactsUser.isEmpty()) {
        
        	JLabel contactUserH = new JLabel("Contacts (By Username): ");
        	contactUserH.setFont(titleFont);
        	resCon.add(contactUserH);
       
        	for (Contact contact : foundContactsUser) { 

        		JButton contactBtn = new JButton(contact.getName());

        		contactBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        		contactBtn.setPreferredSize(new Dimension(0, 40));
        		contactBtn.setMinimumSize(new Dimension(0, 40));
        		contactBtn.setMaximumSize(new Dimension(Short.MAX_VALUE, 40));

        		contactBtn.addActionListener(f -> {
        			contactsD(headerL, headerR, footerP, contact);
        		});

        		resCon.add(contactBtn);
        	}
        }
        
        if (!foundChats.isEmpty()) {
        
        	JLabel chatH = new JLabel("Chats: ");
        	chatH.setFont(titleFont);
        	resCon.add(chatH);
       	
        	for (Chat chat : foundChats) {
       	
        		chat.updateLastChanged();
       	
        		if (chat.getMessages().isEmpty()) {
        			JButton openChat = new JButton(chat.getChatName());;
        			openChat.setHorizontalAlignment(SwingConstants.LEFT);
       			
        			openChat.setAlignmentX(Component.LEFT_ALIGNMENT);
        			openChat.setPreferredSize(new Dimension(0, 70));
        			openChat.setMinimumSize(new Dimension(0, 70));
        			openChat.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));

        			openChat.addActionListener(g -> {
        				chatP(headerL, headerR, footerP, chat);
        			});
        			resCon.add(openChat);
        			continue;
        		}    
        		Message message = chat.getMessages().getLast();
       	
        		JButton chatPreview = new JButton();
           
        		String sentBy = "";
        		String read = "";
           
        		if (message.getSentBy().equals(activeUser)) {
        			sentBy = "You";
        			if (message.isRead()) {
        				read = "read";
        			}
        			else {
        				read = "unread";
        			}
        		}
        		else {
        			sentBy = message.getSentBy().getName();
        		}
           
        		String timeSent = message.formatTime(message.getTimeOfMessage());
           
        		chatPreview.setText("<html>" + chat.getChatName() +  "<br>" + sentBy +  ": " + message.getContent() + "<br>" + timeSent + "<br>" + read + "</html>");
        		chatPreview.setHorizontalAlignment(SwingConstants.LEFT);

        		chatPreview.setAlignmentX(Component.LEFT_ALIGNMENT);
        		chatPreview.setPreferredSize(new Dimension(0, 70));
        		chatPreview.setMinimumSize(new Dimension(0, 70));
        		chatPreview.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));

        		chatPreview.addActionListener(h -> {
        			chatP(headerL, headerR, footerP, chat);
        		});
       	
        		resCon.add(chatPreview);
        	}
        }
       	
        if (!foundMessages.isEmpty()) {
        
        	JLabel messageH = new JLabel("Messages: ");
        	messageH.setFont(titleFont);
        	resCon.add(messageH);
       	
        	for (Message message : foundMessages) {
           	
        		JButton chatPreview = new JButton();
           
        		String sentBy = "";
        		String read = "";
           
        		if (message.getSentBy().equals(activeUser)) {
        			sentBy = "You";
        			if (message.isRead()) {
        				read = "read";
        			}
        			else {
        				read = "unread";
        			}
        		}
        		else {
        			sentBy = message.getSentBy().getName();
        		}
           
        		String timeSent = message.formatTime(message.getTimeOfMessage());
           
        		chatPreview.setText("<html>" + message.getTempChat().getChatName() +  "<br>" + sentBy +  ": " + message.getContent() + "<br>" + timeSent + "<br>" + read + "</html>");
        		chatPreview.setHorizontalAlignment(SwingConstants.LEFT);

        		chatPreview.setAlignmentX(Component.LEFT_ALIGNMENT);
        		chatPreview.setPreferredSize(new Dimension(0, 70));
        		chatPreview.setMinimumSize(new Dimension(0, 70));
        		chatPreview.setMaximumSize(new Dimension(Short.MAX_VALUE, 70));

        		chatPreview.addActionListener(h -> {
        			chatP(headerL, headerR, footerP, message.getTempChat());
        		});
       	
        		resCon.add(chatPreview);
        	}
        }
       
       	
    
        
        
        searchPanel.add(searchField, BorderLayout.CENTER);
        
        searchPage.add(scroll);
        searchPage.add(searchPanel, BorderLayout.NORTH);
        
        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	activeUser.setLastSearch(searchField.getText());
            	
            	history.pop();
            	searchPage.removeAll();
            	searchP(headerL, headerR, footerP);
            	
            }
        });
        footerP.add(search, BorderLayout.CENTER);

        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);
        revNrep(searchPage);

        cardLayout.show(mainP, "search");
    }

    /**
     * Revalidates and repaints the given panel.
     * @author faisalyero
     */
    public void revNrep(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }
    
    /**
     * Saves user data to a file.
     * @author jamesfinnon
     */
    public void save(String filename) {
    	// try to set up output stream to filename
	    try (ObjectOutputStream out =
	             new ObjectOutputStream(new FileOutputStream(filename))) {
	    	
	    	ArrayList<User> users = new ArrayList<User>();
	    	users.add(activeUser);
	    	users.add(secondUser);
	        out.writeObject(users);
	    }
	    // catch errors
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    
    /**
     * Loads user data from a file.
     * @author jamesfinnon
     */
    public void load(String filename) {
    	try (ObjectInputStream in =
    			new ObjectInputStream(new FileInputStream(filename))) {
    		
    		ArrayList<User> users = new ArrayList<User>();
	    
    		users = (ArrayList<User>) in.readObject();
    		activeUser = users.getFirst();
    		secondUser = users.getLast();
	    }
	    // catch errors
	    catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
    }
}