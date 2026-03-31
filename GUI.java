import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {  
    private JFrame window;
    private CardLayout cardLayout;
    private JPanel mainP;
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
        JPanel mainP = new JPanel(new CardLayout());
        mainP.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.LineBorder(Color.BLACK, 1), new javax.swing.border.EmptyBorder(15, 15, 15, 15)));

        //different mps
        JPanel landingP = new JPanel(new BorderLayout());
        mainP.add(landingP, "landing");
        
        JPanel chatView = new JPanel(new BorderLayout());
        mainP.add(chatView, "chat");
        
        JPanel profilePage = new JPanel(new BorderLayout());
        mainP.add(profilePage, "profile");

        JPanel contactsPage = new JPanel(new BorderLayout());
        mainP.add(contactsPage, "contactsP");

        JPanel contactsDet = new JPanel(new BorderLayout());
        mainP.add(contactsDet, "contactsD");

        JPanel contactsNew = new JPanel(new BorderLayout());
        mainP.add(contactsNew, "contactsN");

        JPanel searchPage = new JPanel(new BorderLayout());
        mainP.add(searchPage, "search");

        JPanel newChats = new JPanel(new BorderLayout());
        mainP.add(newChats, "chatsN");

        window.add(headerP, BorderLayout.NORTH);
        window.add(mainP, BorderLayout.CENTER);
        window.add(footerP, BorderLayout.SOUTH);

        landingPage(landingP, headerL, headerR, footerP);

        window.setVisible(true);
        
    }

    public void landingPage(JPanel landingP, JPanel headerL, JPanel headerR, JPanel footerP) {
        //font
        Font headerFont = new Font("Arial", Font.BOLD, 18);
        String profileName = new String("Faisal S Yero");

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
        Font headerFont = new Font("Arial", Font.BOLD, 18);
        String friend = new String("James Finnon");

        headerL.setLayout(new FlowLayout(FlowLayout.LEFT));

        headerL.removeAll();
        headerR.removeAll();
        footerP.removeAll();

        JButton back = new JButton("← Back");
        headerL.add(back);
        JLabel chatName = new JLabel(friend);
        chatName.setFont(headerFont);
        headerL.add(chatName);

        JTextField messageB = new JTextField();
        footerP.add(messageB, BorderLayout.CENTER);

        JButton sendBut = new JButton("Send =>");
        footerP.add(sendBut, BorderLayout.EAST);

        JButton searchBut = new JButton("Search ⌕");
        headerR.add(searchBut);

        JButton conInfBut = new JButton(":");
        headerR.add(conInfBut);

        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);

        cardLayout.show(mainP, "chat");
    }
    
    public void profileP(JPanel headerL, JPanel headerR, JPanel footerP) {
        Font headerFont = new Font("Arial", Font.BOLD, 18);

        headerL.setLayout(new FlowLayout(FlowLayout.LEFT));

        headerL.removeAll();
        headerR.removeAll();
        footerP.removeAll();

        JButton back = new JButton("← Back");
        headerL.add(back);
        JLabel myProfile = new JLabel("My Profile");
        myProfile.setFont(headerFont);
        headerL.add(myProfile);

        revNrep(headerL);
        revNrep(headerR);
        revNrep(footerP);

        cardLayout.show(mainP, "profile");
    }

    public void revNrep(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }
}
