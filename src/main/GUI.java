import model.ListOfPerson;
import model.Person;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// code modeled after: https://introcs.cs.princeton.edu/java/15inout/GUI.java.html
//                     https://beginnersbook.com/2015/07/java-swing-tutorial/
public class GUI extends JFrame implements ActionListener {

    private JFrame frame;
    private JFrame addPersonFrame;
    private JFrame viewNamesFrame;
    private JFrame deletePersonFrame;
    private JFrame cannotLoadFrame;
    private JFrame cannotSaveFrame;

    private JPanel mainPanel;
    private JPanel addPersonPanel;
    private JPanel viewNamesPanel;
    private JPanel deletePersonPanel;

    private JButton addPersonButton;
    private JButton viewNamesButton;
    private JButton deleteRecentButton;
    private JButton doneButton;
    private JButton loadButton;
    private JButton saveButton;

    private JLabel nameLabel;
    private JLabel phoneLabel;
    private JLabel placesLabel;
    private JLabel deletePersonLabel;
    private JLabel namesLabel;
    private JLabel cannotLoadLabel;
    private JLabel cannotSaveLabel;

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField placesField;

    private String name;
    private String number;
    private String places;
    private String output;

    private Clip clip;

    private ListOfPerson database;

    private static final String JSON_STORE = "./data/ListOfPerson.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;


    Color c1 = new Color(176, 196, 222);
    Color c2 = new Color(100, 150, 190);

    public GUI() {
        frame = new JFrame();

        makeMainFrameButtons();

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(10, 1));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        mainPanel.add(addPersonButton);
        mainPanel.add(viewNamesButton);
        mainPanel.add(deleteRecentButton);
        mainPanel.add(saveButton);
        mainPanel.add(loadButton);
        mainPanel.setBackground(c2);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setTitle("Covid Data Assistant");
        frame.pack();
        frame.setVisible(true);
    }

    // EFFECTS: assigns buttons to action commands and action listeners
    public void makeMainFrameButtons() {
        addPersonButton = new JButton("Add a person");
        addPersonButton.setActionCommand("addPerson");
        addPersonButton.addActionListener(this);

        viewNamesButton = new JButton("View the list of names of visitors");
        viewNamesButton.setActionCommand("viewNames");
        viewNamesButton.addActionListener(this);

        deleteRecentButton = new JButton("Delete the most recently added visitor");
        deleteRecentButton.setActionCommand("deletePerson");
        deleteRecentButton.addActionListener(this);

        saveButton = new JButton("Save");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(this);

        loadButton = new JButton("Load");
        loadButton.setActionCommand("load");
        loadButton.addActionListener(this);
    }

    // EFFECTS: creates a window which allows the user to add a new person with a name, phone number,
    // and list of places the person has been
    public void personPanel() {
        addPersonPanel = new JPanel(new GridLayout(0, 2));
        addPersonPanel.setBackground(c1);

        addPersonFrame = new JFrame();
        addPersonFrame.setSize(400, 400);

        nameLabel = new JLabel("Name (first and last):");
        nameLabel.setBounds(40, 20, 80, 25);
        addPersonPanel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setBounds(50, 20, 165, 25);
        addPersonPanel.add(nameField);

        phoneLabel = new JLabel("Phone number:");
        phoneLabel.setBounds(40, 50, 80, 25);
        addPersonPanel.add(phoneLabel);

        phoneField = new JTextField(20);
        nameField.setBounds(50, 50, 165, 25);
        addPersonPanel.add(phoneField);

        placesLabel = new JLabel("Places visited:");
        placesLabel.setBounds(40, 80, 80, 25);
        addPersonPanel.add(placesLabel);

        placesField = new JTextField(20);
        placesField.setBounds(50, 80, 165, 25);
        addPersonPanel.add(placesField);

        doneButton(addPersonFrame, addPersonPanel);
    }

    // EFFECTS: makes a new window which displays names of saved visitors
    public void viewNamesPanel() {
        viewNamesFrame = new JFrame("Names");
        viewNamesFrame.setSize(200, 400);
        viewNamesFrame.setLayout(new GridLayout(10, 1));
        viewNamesFrame.setResizable(false);

        viewNamesPanel = new JPanel();

        output = database.outputNames();
        namesLabel = new JLabel(database.outputNames());
        namesLabel.setVisible(true);
        System.out.println(database.outputNames());

        viewNamesPanel.add(namesLabel);
        viewNamesFrame.add(namesLabel);

        viewNamesFrame.setVisible(true);
        viewNamesPanel.setVisible(true);
        // only showing one name
    }

    // MODIFIES: this
    // EFFECTS: creates a person given the text the user has entered into the nameField, phoneField, and placesField,
    //          adds the new person to the database
    public void createPerson() {
        addPersonPanel = new JPanel();
        database = new ListOfPerson("database");

        name = nameField.getText();
        number = phoneField.getText();
        places = placesField.getText();

        Person p1 = new Person(name, number, places);

        database.addPerson(p1);
    }

    // EFFECTS: creates a new done button on the window where users can add a new person
    public void doneButton(JFrame addPersonFrame, JPanel addPersonPanel) {
        doneButton = new JButton("Done");
        doneButton.setActionCommand("done");
        doneButton.addActionListener(this);
        doneButton.setVisible(true);

        addPersonPanel.add(doneButton);
        addPersonFrame.add(addPersonPanel);
        addPersonFrame.pack();
        addPersonFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: alerts the user that the last person has been removed
    public void deletePersonPanel() {
        deletePersonFrame = new JFrame("Success");
        deletePersonFrame.setSize(400, 100);
        deletePersonFrame.setBackground(c1);
        deletePersonFrame.setResizable(false);

        deletePersonPanel = new JPanel();
        deletePersonPanel.setBackground(c1);
        deletePersonPanel.setVisible(true);

        deletePersonFrame.add(deletePersonPanel);
        deletePersonFrame.setVisible(true);

        database.removeLastPerson();

        deletePersonLabel = new JLabel("Person has been deleted!");
        deletePersonPanel.add(deletePersonLabel);
    }

    // EFFECTS: plays audio clip
    // code from http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    // EFFECTS: creates a new window alerting the user that the information could not be saved
    public void cannotSaveListPanel() {
        cannotSaveFrame = new JFrame();
        cannotSaveFrame.setBackground(c1);
        cannotSaveLabel = new JLabel("Unable to write to file" + JSON_STORE);
        cannotSaveFrame.add(cannotSaveLabel);
        cannotSaveFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: saves entries the user has entered
    public void saveListOfPerson() {
        jsonWriter = new JsonWriter(JSON_STORE);
        try {
            jsonWriter.open();
            jsonWriter.write(database);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            cannotSaveListPanel();
        }
    }

    // EFFECTS: creates a new window alerting the user that information could not be loaded
    public void cannotLoadPanel() {
        cannotLoadFrame = new JFrame();
        cannotLoadFrame.setBackground(c1);
        cannotLoadLabel = new JLabel("Unable to read from file:" + JSON_STORE);
        cannotLoadFrame.add(cannotLoadLabel);
        cannotLoadFrame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: loads list of saved person from before
    private void loadListOfPerson() {
        jsonReader = new JsonReader(JSON_STORE);
        try {
            database = jsonReader.read();
        } catch (IOException e) {
            cannotLoadPanel();
        }
    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    // EFFECTS: calls certain methods based on the given action command, plays sound for buttons
    //button11.wav file from http://www.pachd.com/button-sounds-2.html
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addPerson")) {
            personPanel();
            playSound("button11.wav");
        } else if (e.getActionCommand().equals("viewNames")) {
            viewNamesPanel();
            playSound("button11.wav");
        } else if (e.getActionCommand().equals("done")) {
            createPerson();
            playSound("button11.wav");
        } else if (e.getActionCommand().equals("deletePerson")) {
            deletePersonPanel();
            playSound("button11.wav");
        } else if (e.getActionCommand().equals("save")) {
            saveListOfPerson();
            playSound("button11.wav");
        } else if (e.getActionCommand().equals("load")) {
            loadListOfPerson();
            playSound("button11.wav");
        }
    }
}
