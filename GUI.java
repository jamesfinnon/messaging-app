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
                runProgram();
            }
        });

    }

    public static void runProgram() {
        //font
        Font headerFont = new Font("Arial", Font.BOLD, 16);


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
        headerP.setPreferredSize(new Dimension(442, 60));
        
        //lhp
        JPanel headerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel appName = new JLabel("Messages");
        appName.setFont(headerFont);
        headerLeft.add(appName);
        headerP.add(headerLeft, BorderLayout.WEST);
        
        //rhp
        JPanel headerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton searchBut = new JButton("Search");
        JButton profileBut = new JButton("Profile");
        JButton contactsBut = new JButton("Contacts");
        headerRight.add(searchBut);
        headerRight.add(profileBut);
        headerRight.add(contactsBut);
        headerP.add(headerRight, BorderLayout.EAST);
        
        JLabel mainPName = new JLabel("Your name: " + profileName);
        headerP.add(mainPName, BorderLayout.SOUTH);

        //mp
        JPanel mainP = new JPanel();
        mainP.setLayout(new BorderLayout());

        //fp
        JPanel footerP = new JPanel();
        footerP.setLayout(new BorderLayout());
        footerP.setPreferredSize(new Dimension(442, 60));
        
        JButton newChatBut = new JButton("+ New Chat");
        footerP.add(newChatBut, BorderLayout.CENTER);

        window.add(headerP, BorderLayout.NORTH);
        window.add(mainP, BorderLayout.CENTER);
        window.add(footerP, BorderLayout.SOUTH);

        window.setVisible(true);
    }

}
