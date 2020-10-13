package model;

import java.util.*;

public class ListOfPerson {

    public ArrayList<Person> list1;

    public ListOfPerson() {
        list1 = new ArrayList<>();
    }

    public void addPerson(Person person) {
        list1.add(person);
    }

    public int getSize() {
        return list1.size();
    }

    public Person getIndex(int index) {
        return list1.get(index);
    }

    public String getNames() {
        String result = "";
        for (Person name : list1) {
            result += name.getName() + "\n";
        }
        return result;
    }

    public void removeLastPerson() {
        list1.remove(getSize() - 1);
    }
}