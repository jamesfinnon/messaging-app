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
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(442, 874);
        window.setTitle("Messaging App");
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        JLabel appName = new JLabel("Messages");
        appName.setBounds(10,10,70,20);
        window.add(appName);
        JButton searchBut = new JButton("Search");
        searchBut.setBounds(160, 10, 70, 20);
        window.add(searchBut);
        JButton profileBut = new JButton("Profile");
        profileBut.setBounds(245, 10, 70, 20);
        window.add(profileBut);
        JButton contactsBut = new JButton("Contacts");
        contactsBut.setBounds(320, 10, 70, 20);
        window.add(contactsBut);


        window.setVisible(true);
    }

}
