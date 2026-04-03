import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.util.Set;

public class GUI {
	
    //test variables
    Font headerFont = new Font("Arial", Font.BOLD, 18);
    String friend = new String("James Finnon");
    String profileName = new String("Faisal Yero");
    String userName = new String("faisalyero123");
    String phoneNo = new String("+44 482934 4289384");
    String message0 = new String("HI");
    String message1 = new String("hello");
    String message2 = new String("goodbye");
    
    ArrayList<User> users = new ArrayList<User>();
    User activeUser;
    		
    
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
    
    public void mainFrame() {
    	
    	User newUser = new User();
    	activeUser = newUser;
    	
    	addUser(newUser);
    	
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
    
    public void addUser(User user) {
    	users.add(user);
    }
    
    /**
     * @author jamesfinnon
     * 
     * @param landingP
     * @param headerL
     * @param headerR
     * @param footerP
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
    	
    	
    }
    
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
        else if (history.peek().equals("chat")) {    		
    		chatP(headerL, headerR, footerP, activeUser.getCurrentChat());
            history.pop();
    	}
    	
    }
    
    
    public void landingPage(JPanel headerL, JPanel headerR, JPanel footerP) {
    	
    	history.add("landing");
    	
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
    }

    public void chatP(JPanel headerL, JPanel headerR, JPanel footerP, Chat chat) {
        
        history.add("chat");

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
        footerP.add(sendBut, BorderLayout.EAST);

        JButton searchBut = new JButton("Search ⌕");
        searchBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchP(headerL, headerR, footerP);
            }
        });
        headerR.add(searchBut);

        JButton conInfBut = new JButton(":");
        conInfBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
      //          contactsD(headerL, headerR, footerP);
            }
        });
        headerR.add(conInfBut);

        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);

        cardLayout.show(mainP, "chat");
    }
    
    public void profileP(JPanel headerL, JPanel headerR, JPanel footerP) {
    	
    	history.add("profile");
    	
    	Font titleFont = new Font("Arial", Font.BOLD, 14);

        headerL.setLayout(new FlowLayout(FlowLayout.LEFT));
        footerP.setLayout(new BorderLayout());

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
        

        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);
        revNrep(profilePage);

        cardLayout.show(mainP, "profile");
    }

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

        JButton searchBut = new JButton("Search ⌕");
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
      
        for (int i = 0; i < activeUser.getContactsSize(); i++) {

            var contactObj = activeUser.getContacts().get(i); 

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
        
        JPanel userPanel = new JPanel(new GridLayout(1, 1));
        userPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        JLabel mess0 = new JLabel(message0);
        userPanel.add(mess0);

        contactsDet.add(userPanel);

        JSeparator separ1 = new JSeparator(JSeparator.HORIZONTAL);
        separ1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        contactsDet.add(separ1);
        
        JPanel phonePanel = new JPanel(new GridLayout(1, 1));
        phonePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        JLabel mess1 = new JLabel(message1);
        phonePanel.add(mess1);

        contactsDet.add(phonePanel);
        
        JSeparator separ2 = new JSeparator(JSeparator.HORIZONTAL);
        separ2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        contactsDet.add(separ2);

        JPanel mess2P = new JPanel(new GridLayout(1, 1));
        mess2P.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        JLabel mess2 = new JLabel(message2);
        mess2P.add(mess2);

        contactsDet.add(mess2P);
        
        JSeparator separ3 = new JSeparator(JSeparator.HORIZONTAL);
        separ3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        contactsDet.add(separ3);

        JButton message = new JButton("Send Message");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.5; 
        gbc.insets = new Insets(2, 2, 2, 2);
        message.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	boolean found = false;
            	
            	for (int i = 0; i < activeUser.getChatsSize(); i++) {
            		if (activeUser.getChats().get(i).getChatMembers().contains(contact) && activeUser.getChats().get(i).getChatMembers().size() == 1) {
            			activeUser.setCurrentChat(activeUser.getChats().get(i));
            			found = true;
            			break;
            		}
            	}
            		
            	if (!found) {         	
            		Chat newChat = new Chat();
            		newChat.addMember(contact);
            		activeUser.addChat(newChat);
            		activeUser.setCurrentChat(newChat);
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
            	
            	if (0 == JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this contact?")) {
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
        
        BufferedImage img = activeUser.getProfilePicture();
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
     * page for editing your profile
     * 
     * @author faisalsyero
     * @author jamesfinnon
     * 
     * @param headerL
     * @param headerR
     * @param footerP
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
                	
                activeUser.addChat(newChat);
                activeUser.setCurrentChat(newChat);
                	  	
                chatP(headerL, headerR, footerP, activeUser.getCurrentChat());
            }
        });
        footerP.add(newCon, BorderLayout.CENTER);

        JPanel sortH = new JPanel(new GridLayout(2, 1, 0, 6));
        JTextField alphaSort = new JTextField();
        sortH.add(alphaSort);

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

        	var contactObj = activeUser.getContacts().get(i); 

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

    public void searchP(JPanel headerL, JPanel headerR, JPanel footerP) {

        
        
        history.add("search");

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

        JButton newCon = new JButton("Search");
        newCon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //where the search for contacts method will be called
            }
        });
        footerP.add(newCon, BorderLayout.CENTER);

        JPanel sortH = new JPanel(new BorderLayout());
        JTextField alphaSort = new JTextField();

        sortH.add(alphaSort, BorderLayout.CENTER);
        newChats.add(sortH, BorderLayout.NORTH);

        searchPage.add(sortH, BorderLayout.NORTH);

        JPanel resCon = new JPanel(new GridLayout(20,1));
        searchPage.add(resCon);

        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);
        revNrep(searchPage);

        cardLayout.show(mainP, "search");
    }

    public void revNrep(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }
}