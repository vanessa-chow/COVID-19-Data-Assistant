package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a peron having a name, phone number, and places they've visited on a given day
public class Person implements Writable {
    private String name;                       // first and last name of the person
    private String phoneNumber;                // the person's phone number
    private String placesVisited;              // places the person has been on a given day

    // REQUIRES: person's first and last name, their phone number, and places they've visited on a given day
    // EFFECTS: creates a person with a name, phone number, and list of places they've visited
    public Person(String name, String phoneNumber, String placesVisited) {
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

    // EFFECTS: returns string representation of this person
    public String toString() {
        return "name:" + name + ":\nphone number:" + phoneNumber + "\n places visited:" + placesVisited;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("phoneNumber", phoneNumber);
        json.put("placesVisited", placesVisited);
        return json;
    }
}