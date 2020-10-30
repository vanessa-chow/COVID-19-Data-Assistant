package ui;

import model.ListOfPerson;

import model.Person;

import java.util.Scanner;

//COVID-19 Data Assistant
public class CovidDataAssistant {
    private ListOfPerson database;
    private Scanner input;

    // EFFECTS: runs the COVID-19 Data Assistant
    public CovidDataAssistant() {
        runDataAssistant();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runDataAssistant() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayOptions();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("d")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nThank you for using the COVID-19 Data Assistant!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {

        if (command.equals("a")) {
            newPerson();
        } else if (command.equals("n")) {
            viewNames();
        } else if (command.equals("r")) {
            removeLastPerson();
        } else if (command.equals("c")) {
            System.out.println("Please enter the name of the person whose details you would like to see.");
            input.nextLine();
            System.out.print("Enter the person's first and last name:");
            String name = input.nextLine();
            Person psn = database.seeDetails(name);
            System.out.println("Name: " + psn.getName());
            System.out.println("Phone number: " + psn.getPhoneNumber());
            System.out.println("Places visited: " + psn.getPlacesVisited());
        } else {
            System.out.println("Selection not valid, please try again...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes lists
    private void init() {
        database = new ListOfPerson("list1");
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayOptions() {
        System.out.println("\nPlease select from:");
        System.out.println("\na -> Add a new person");
        System.out.println("\nn -> View names on today's list");
        System.out.println("\nr -> Remove the last person (if you made a mistake entering their information)");
        System.out.println("\nc -> Check details on of a person whose name is on the list");
        System.out.println("\nd -> Done! Quit Covid Data Assistant");
    }

    // MODIFIES: this
    // EFFECTS: conducts addition of another person
    private void newPerson() {
        input.nextLine();
        System.out.print("Enter the person's first and last name:");
        String name = input.nextLine();
        System.out.print("Enter the person's phone number:");
        String number = input.nextLine();
        System.out.print("Enter a comma separated list of the places the person has been:");
        String places = input.nextLine();
        Person person = new Person(name, number, places);
        database.addPerson(person);
        System.out.print("Person has successfully been added.");

    }

    // REQUIRES: list consists of at least one person
    // EFFECTS: view names on list
    private void viewNames() {
        System.out.print(database.outputNames());
        input.nextLine();
    }

    // MODIFIES: this
    // EFFECTS: removes the last person from the list
    private void removeLastPerson() {
        database.removeLastPerson();
        System.out.print("Person has been removed.");
    }
}
