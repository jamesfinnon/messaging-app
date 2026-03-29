import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {  
    public static void main(String[] args) {
        // use the Swing 'invokeLater' method to create a new
        // Runnable object to run on the EDT
        // uses an anonymous class to represent the thread to run
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            // override the 'run' method that any Runnable object has
            // put code here that needs to be run on a thread
            public void run() {
                // call separate method to set up the GUI and run it
                landingPage();
            }
        });

    }

    public static void landingPage() {
        //font
        Font headerFont = new Font("Arial", Font.BOLD, 18);


        String profileName = new String("Faisal S Yero");
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(442, 874);
        window.setTitle("Messaging App");
        window.setLocationRelativeTo(null);
        window.setLayout(new BorderLayout());

        //hp
        JPanel headerP = new JPanel();
        headerP.setLayout(new BorderLayout());
        headerP.setPreferredSize(new Dimension(442, 70));
        headerP.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.LineBorder(Color.BLACK, 1), new javax.swing.border.EmptyBorder(10, 10, 10, 5)));
        
        //lhp
        JPanel headerLeft = new JPanel();
        headerLeft.setLayout(new BoxLayout(headerLeft, BoxLayout.Y_AXIS));
        JLabel appName = new JLabel("Messages");
        appName.setFont(headerFont);
        headerLeft.add(Box.createVerticalStrut(3));
        headerLeft.add(appName);
        headerLeft.add(Box.createVerticalStrut(8));
        JLabel mainPName = new JLabel("Your name: "+ profileName);
        headerLeft.add(mainPName);
        headerP.add(headerLeft, BorderLayout.WEST);
        
        //rhp
        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        JButton searchBut = new JButton("Search");
        JButton profileBut = new JButton("Profile");
        JButton contactsBut = new JButton("Contacts");
        headerRight.add(searchBut);
        headerRight.add(profileBut);
        headerRight.add(contactsBut);
        headerP.add(headerRight, BorderLayout.EAST);
        
        //mp
        JPanel mainP = new JPanel();
        mainP.setLayout(new BorderLayout());
        mainP.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.LineBorder(Color.BLACK, 1), new javax.swing.border.EmptyBorder(15, 15, 15, 15)));

        //fp
        JPanel footerP = new JPanel();
        footerP.setLayout(new BorderLayout());
        footerP.setPreferredSize(new Dimension(442, 60));
        footerP.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.LineBorder(Color.BLACK, 1), new javax.swing.border.EmptyBorder(5, 5, 5, 5)));
        JButton newChatBut = new JButton("+ New Chat");
        footerP.add(newChatBut, BorderLayout.CENTER);

        window.add(headerP, BorderLayout.NORTH);
        window.add(mainP, BorderLayout.CENTER);
        window.add(footerP, BorderLayout.SOUTH);

        window.setVisible(true);
    }

}
