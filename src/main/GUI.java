import model.ListOfPerson;
import model.Person;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// code modeled after: https://introcs.cs.princeton.edu/java/15inout/GUI.java.html
//                     https://beginnersbook.com/2015/07/java-swing-tutorial/
public class GUI extends JFrame implements ActionListener {

    private JFrame frame;
    private JPanel mainPanel;
    private JButton addPersonButton;
    private JButton viewNamesButton;
    private JButton deleteRecentButton;

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private String destination;
    private String source;

    private Person p1;
    private ListOfPerson database;

    Color c1 = new Color(176, 196, 222);
    Color c2 = new Color(100, 150, 190);

    public GUI() {
        frame = new JFrame();

        addPersonButton = new JButton("Add a person");
        addPersonButton.setActionCommand("addPerson");
        addPersonButton.addActionListener(this);

        viewNamesButton = new JButton("View the list of names of visitors");
        viewNamesButton.setActionCommand("viewNames");
        viewNamesButton.addActionListener(this);

        deleteRecentButton = new JButton("Delete the most recently added visitor");
        deleteRecentButton.setActionCommand("deletePerson");
        deleteRecentButton.addActionListener(this);

        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        mainPanel.setLayout(new GridLayout(10, 1));
        mainPanel.add(addPersonButton);
        mainPanel.add(viewNamesButton);
        mainPanel.add(deleteRecentButton);
        mainPanel.setBackground(c2);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Covid Data Assistant");
        frame.pack();
        frame.setVisible(true);
    }

    public void personPanel() {
        JPanel addPersonPanel = new JPanel(new GridLayout(0, 2));
        addPersonPanel.setBackground(c1);

        JFrame addPersonFrame = new JFrame();
        addPersonFrame.setSize(400, 400);

        JLabel nameLabel = new JLabel("Name (first and last):");
        nameLabel.setBounds(40, 20, 80, 25);
        addPersonPanel.add(nameLabel);

        JTextField nameField = new JTextField(20);
        nameField.setBounds(50, 20, 165, 25);
        addPersonPanel.add(nameField);

        JLabel phoneLabel = new JLabel("Phone number:");
        phoneLabel.setBounds(40, 50, 80, 25);
        addPersonPanel.add(phoneLabel);

        JTextField phoneField = new JTextField(20);
        nameField.setBounds(50, 50, 165, 25);
        addPersonPanel.add(phoneField);

        JLabel placesLabel = new JLabel("Places visited:");
        placesLabel.setBounds(40, 80, 80, 25);
        addPersonPanel.add(placesLabel);

        JTextField placesField = new JTextField(20);
        placesField.setBounds(50, 80, 165, 25);
        addPersonPanel.add(placesField);

        doneButton(addPersonFrame, addPersonPanel);
    }

    public void viewNamesPanel() {
        ListOfPerson database = new ListOfPerson("database");
        JFrame viewNamesFrame = new JFrame();
        viewNamesFrame.setSize(400, 400);
        viewNamesFrame.setResizable(false);

        JPanel viewNamesPanel = new JPanel();
        viewNamesPanel.setVisible(true);

        JTextArea output = new JTextArea(20, 20);
        output.setBackground(c1);

        JScrollPane pane = new JScrollPane(output);
        output.setText(database.outputNames());

        pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        viewNamesFrame.add(pane);

        viewNamesFrame.setVisible(true);
    }

    public void createPerson() {
        ListOfPerson database = new ListOfPerson("database");

        JTextField nameField = new JTextField(20);
        String name = nameField.getText();

        JTextField phoneField = new JTextField(20);
        String number = phoneField.getText();

        JTextField placesField = new JTextField(20);
        String places = placesField.getText();

        Person p1 = new Person(name, number, places);
        database.addPerson(p1);
    }

    public void doneButton(JFrame addPersonFrame, JPanel addPersonPanel) {
        JButton doneButton = new JButton("Done");
        doneButton.setActionCommand("done");
        doneButton.addActionListener(this);
        doneButton.setVisible(true);

        addPersonPanel.add(doneButton);
        addPersonFrame.add(addPersonPanel);
        addPersonFrame.pack();
        addPersonFrame.setVisible(true);

        createPerson();
    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addPerson")) {
            personPanel();
        } else if (e.getActionCommand().equals("viewNames")) {
            viewNamesPanel();
        } else if (e.getActionCommand().equals("done")) {
            //
        } else if (e.getActionCommand().equals("deletePerson")) {
            System.out.println("Success! Most recent person has been deleted.");
        }
    }

}
