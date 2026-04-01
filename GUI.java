import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class GUI {
	
    //test variables
    Font headerFont = new Font("Arial", Font.BOLD, 18);
    String friend = new String("James Finnon");
    String profileName = new String("Faisal Yero");
    String userName = new String("faisalyero123");
    String phoneNo = new String("+44 482934 4289384");
    
    
    Stack<String> history = new Stack<String>();
    
    private JFrame window;
    private CardLayout cardLayout;
    private JPanel mainP;
    private JPanel landingP;
    private JPanel chatView;
    private JPanel profilePage;
    private JPanel contactsPage;
    private JPanel contactsDet;
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

        contactsPage = new JPanel(new BorderLayout());
        mainP.add(contactsPage, "contactsP");

        contactsDet = new JPanel(new BorderLayout());
        mainP.add(contactsDet, "contactsD");

        contactsNew = new JPanel(new BorderLayout());
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
    	}
    	else if (history.peek().equals("chat")) {    		
    		//chatP(landingP, headerL, headerR, footerP);
    	}
    	else if (history.peek().equals("profile")) {	
    		profileP(headerL, headerR, footerP);
    	}
    	else if (history.peek().equals("contactsP")) {   		
    		contactsP(headerL, headerR, footerP);
    	}
    	else if (history.peek().equals("contactsD")) {		
    		//contactsD(landingP, headerL, headerR, footerP);
    	}
    	else if (history.peek().equals("contactsN")) {  
    		//contactsN(landingP, headerL, headerR, footerP);
    	}
    	else if (history.peek().equals("search")) {
    		//searchP(landingP, headerL, headerR, footerP);
    	}
    	else if (history.peek().equals("chatsN")) {
    		//chatsN(landingP, headerL, headerR, footerP);
    	}
    	
    	
    }
    
    
    public void landingPage(JPanel headerL, JPanel headerR, JPanel footerP) {
    	
    	history.add("landing");
    	
        headerL.setLayout(new BoxLayout(headerL, BoxLayout.Y_AXIS));

        headerL.removeAll();
        headerR.removeAll();
        footerP.removeAll();
        
        //lhp
        JLabel appName = new JLabel("Messages");
        appName.setFont(headerFont);
        headerL.add(Box.createVerticalStrut(3));
        headerL.add(appName);
        headerL.add(Box.createVerticalStrut(8));
        JLabel mainPName = new JLabel("Your name: "+ profileName);
        headerL.add(mainPName);
        
        //rhp
        JButton searchBut = new JButton("Search");
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
                chatViewP(headerL, headerR, footerP);
            }
        });
        footerP.add(newChatBut, BorderLayout.CENTER);
        
        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);
    }

    public void chatViewP(JPanel headerL, JPanel headerR, JPanel footerP) {
        
        headerL.setLayout(new FlowLayout(FlowLayout.LEFT));

        headerL.removeAll();
        headerR.removeAll();
        footerP.removeAll();

        JButton back = new JButton("← Back");
        headerL.add(back);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                back(headerL, headerR, footerP);
            }
        });
        JLabel chatName = new JLabel(friend);
        chatName.setFont(headerFont);
        headerL.add(chatName);

        JTextField messageB = new JTextField();
        footerP.add(messageB, BorderLayout.CENTER);
        
        
        JButton sendBut = new JButton("Send =>");
        footerP.add(sendBut, BorderLayout.EAST);

        JButton searchBut = new JButton("Search ⌕");
        headerR.add(searchBut);

        JSeparator separ = new JSeparator(JSeparator.VERTICAL);
        separ.setMaximumSize(new Dimension(2, Integer.MAX_VALUE));
        chatView.add(separ);

        JButton conInfBut = new JButton(":");
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
        profilePic.setText("[Photo]");
        profilePic.setForeground(Color.BLACK);

        picWrapper.add(profilePic);
        profilePage.add(picWrapper);

        JLabel profileH = new JLabel(profileName);
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
        userPanel.add(new JLabel(userName));

        profilePage.add(userPanel);

        JSeparator separ1 = new JSeparator(JSeparator.HORIZONTAL);
        separ1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        profilePage.add(separ1);
        
        JPanel phonePanel = new JPanel(new GridLayout(2, 1));
        phonePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        
        JLabel phoneT = new JLabel("Phone No: ");
        phoneT.setFont(titleFont);
        phonePanel.add(phoneT);
        phonePanel.add(new JLabel(phoneNo));

        profilePage.add(phonePanel);
        
        JSeparator separ2 = new JSeparator(JSeparator.HORIZONTAL);
        separ2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        profilePage.add(separ2);
        
        JPanel editPPanel = new JPanel(new GridLayout(1,1));
        editPPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        JButton editProfile = new JButton("Edit Profile");
        editProfile.setMaximumSize(new Dimension(Integer.MAX_VALUE, editProfile.getPreferredSize().height));
        editPPanel.add(editProfile);
        profilePage.add(editPPanel);
        

        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);
        revNrep(profilePage);

        cardLayout.show(mainP, "profile");
    }

    public void contactsP(JPanel headerL, JPanel headerR, JPanel footerP) {
        
        headerL.setLayout(new FlowLayout(FlowLayout.LEFT));

        contactsPage.removeAll();
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
        JLabel header = new JLabel("Contacts");
        header.setFont(headerFont);
        headerL.add(header);

        JButton searchBut = new JButton("Search ⌕");
        headerR.add(searchBut);

        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);
        revNrep(contactsPage);

        cardLayout.show(mainP, "contactsP");
    }

    public void revNrep(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }
}