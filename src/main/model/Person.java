package model;

public class Person {
    // Represents a peron having a name, phone number, and places they've visited on a given day
    private String name;                       // first and last name of the person
    private String phoneNumber;                // the person's phone number
    private String placesVisited;              // places the person has been on a given day

    // REQUIRES: person's first and last name, their phone number, and a list of places they've visited on a given day
    // EFFECTS: creates a person with a name, phone number, and list of places they've visited
    public Person(String name, String phoneNumber,String placesVisited) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.placesVisited = placesVisited;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPlacesVisited() {
        return placesVisited;
    }
}