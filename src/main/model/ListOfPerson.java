package model;

import java.util.*;

public class ListOfPerson {

    public ArrayList<Person> list1;

    public ListOfPerson() {
        list1 = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a person to the list
    public void addPerson(Person person) {
        list1.add(person);
    }

    public int getSize() {
        return list1.size();
    }

    public Person getPerson(int index) {
        return list1.get(index);
    }

    // REQUIRES: at least one name on the list
    // EFFECTS: outputs names on the list
    public String outputNames() {
        String result = "";
        for (Person name : list1) {
            result += name.getName() + "\n";
        }
        return result;
    }

    // REQUIRES: at least one person on the list
    // MODIFIES: this
    // EFFECTS: removes last person on the list
    public void removeLastPerson() {
        list1.remove(getSize() - 1);
    }

    // EFFECTS: returns details of person on list, given a name
    public Person seeDetails(String name) {
        for (Person person : list1) {
            if (name.equals(person.getName())) {
                return person;
            }
        }
        return null;
    }
}