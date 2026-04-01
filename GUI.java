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
    String message0 = new String("HI");
    String message1 = new String("hello");
    String message2 = new String("goodbye");
    
    
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
        contactsDet.setLayout(new BoxLayout(contactsDet, BoxLayout.Y_AXIS));
        contactsDet.setAlignmentX(Component.LEFT_ALIGNMENT);
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
    		chatP(headerL, headerR, footerP);
    	}
    	else if (history.peek().equals("profile")) {	
    		profileP(headerL, headerR, footerP);
    	}
    	else if (history.peek().equals("contactsP")) {   		
    		contactsP(headerL, headerR, footerP);
    	}
    	else if (history.peek().equals("contactsD")) {		
    		contactsD(headerL, headerR, footerP);
    	}
    	else if (history.peek().equals("contactsN")) {  
    		//contactsN(headerL, headerR, footerP);
    	}
    	else if (history.peek().equals("search")) {
    		//searchP(headerL, headerR, footerP);
    	}
    	else if (history.peek().equals("chatsN")) {
    		//chatsN(headerL, headerR, footerP);
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
                chatP(headerL, headerR, footerP);
            }
        });
        footerP.add(newChatBut, BorderLayout.CENTER);
        
        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);
    }

    public void chatP(JPanel headerL, JPanel headerR, JPanel footerP) {
        
        history.add("chat");

        headerL.setLayout(new FlowLayout(FlowLayout.LEFT));

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
        conInfBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                contactsD(headerL, headerR, footerP);
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
        
        history.add("contactsP");

        headerL.setLayout(new FlowLayout(FlowLayout.LEFT));

        contactsPage.removeAll();
        headerL.removeAll();
        headerR.removeAll();
        footerP.removeAll();

        JButton back = new JButton("← Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	contactsPage.removeAll();
                back(headerL, headerR, footerP);
            }
        });
        headerL.add(back);
        JLabel header = new JLabel("Contacts");
        header.setFont(headerFont);
        headerL.add(header);

        JButton searchBut = new JButton("Search ⌕");
        headerR.add(searchBut);

        JButton newCon = new JButton("+ Add Contacts");
        newCon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //addConpage
            }
        });
        footerP.add(newCon, BorderLayout.CENTER);

        JPanel sortH = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
        JButton alphaSort = new JButton("Alphabetical");
        JButton chronSort = new JButton("Recent");

        sortH.add(alphaSort);
        sortH.add(chronSort);
        contactsPage.add(sortH, BorderLayout.NORTH);

        JPanel resCon = new JPanel(new GridLayout(20,1));
        JScrollPane scroll = new JScrollPane(resCon);
        contactsPage.add(scroll, BorderLayout.EAST);
        contactsPage.add(resCon);

        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);
        revNrep(contactsPage);

        cardLayout.show(mainP, "contactsP");
    }

    public void contactsD(JPanel headerL, JPanel headerR, JPanel footerP) {

        history.add("contactD");

        Font titleFont = new Font("Arial", Font.BOLD, 14);

        headerL.setLayout(new FlowLayout(FlowLayout.LEFT));

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
        profilePic.setText("[Photo]");
        profilePic.setForeground(Color.BLACK);

        picWrapper.add(profilePic);
        contactsDet.add(picWrapper);

        JLabel profileH = new JLabel(profileName);
        profileH.setFont(headerFont);
        JPanel profileHWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        profileHWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, profileH.getPreferredSize().height));
        profileHWrapper.add(profileH);
        contactsDet.add(profileHWrapper);

        JLabel profNo = new JLabel(phoneNo);
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

        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);
        revNrep(contactsDet);

        cardLayout.show(mainP, "contactsD");        
    }

    public void revNrep(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }
}