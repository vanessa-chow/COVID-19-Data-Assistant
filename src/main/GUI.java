import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// code modeled after: https://introcs.cs.princeton.edu/java/15inout/GUI.java.html
//                     https://beginnersbook.com/2015/07/java-swing-tutorial/
public class GUI extends JFrame implements ActionListener {

    private int count = 0;
    private JLabel welcomeLabel;
    private JFrame frame;
    private JPanel panel;
    private JButton addPersonButton;
    private JButton viewNamesButton;
    private JButton deleteRecentButton;

    public GUI() {

        this.setResizable(true);

        frame = new JFrame();

        JLabel welcomeLabel = new JLabel("Welcome! Please select one of the options below...");

        addPersonButton = new JButton("Add a person");
        addPersonButton.addActionListener(this);
        viewNamesButton = new JButton("View the list of names of visitors");
        viewNamesButton.addActionListener(this);
        deleteRecentButton = new JButton("Delete the most recently added visitor");
        deleteRecentButton.addActionListener(this);

        panel = new JPanel();
        panel.add(welcomeLabel);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new GridLayout(10, 1));
        panel.add(addPersonButton);
        panel.add(viewNamesButton);
        panel.add(deleteRecentButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Covid Data Assistant");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button clicked");
    }
}
