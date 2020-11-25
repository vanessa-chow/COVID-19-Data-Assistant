package model;

import exceptions.NoViewableNamesException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a list of person having a collection of persons
public class ListOfPerson implements Writable {
    private String name;
    private ArrayList<Person> persons;

    // EFFECTS: constructs list of person with a name and empty list of persons
    public ListOfPerson(String name) {
        this.name = name;
        persons = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: adds a person to the list
    public void addPerson(Person person) {
        persons.add(person);
    }

    // EFFECTS: returns an unmodifiable list of persons in this list of person
    public List<Person> getPersons() {
        return Collections.unmodifiableList(persons);
    }

    // EFFECTS: returns the size of the list of persons
    public int numPersons() {
        return persons.size();
    }

    // EFFECTS: returns the person at the given index
    public Person getPerson(int index) {
        return persons.get(index);
    }

    // EFFECTS: outputs names on the list, if there are no names on the list,
    //          throws NoViewableNamesException
    public String outputNames() throws NoViewableNamesException {
        String result = "";
        if (persons.size() == 0) {
            throw new NoViewableNamesException();
        } else {
            for (Person name : persons) {
                result += name.getName() + "\n";
            }
            return result;
        }
    }

    // REQUIRES: at least one person on the list
    // MODIFIES: this
    // EFFECTS: removes last person on the list
    public void removeLastPerson() {
        persons.remove(numPersons() - 1);
    }

    // EFFECTS: returns details of person on list, given a name
    public Person seeDetails(String name) {
        for (Person person : persons) {
            if (name.equals(person.getName())) {
                return person;
            }
        }
        return null;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("persons", personsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray personsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Person p : persons) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
