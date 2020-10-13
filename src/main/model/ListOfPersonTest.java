package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class ListOfPersonTest {
    public ListOfPerson list1;
    public Person p1;
    public Person p2;

    @BeforeEach
    void runBefore() {
        list1 = new ListOfPerson();
        p1 = new Person("Alicia Chow", "604-786-1061", "Lougheed Mall, SFU");
        p2 = new Person("Megan Wong", "604-716-0161", "ICBC, Burnaby City Hall");
    }

    @Test
    void testMakeList() {
        assertEquals(0, list1.getSize());
    }

    @Test
    void testAddToList() {
        list1.addPerson(p1);
        assertEquals(1, list1.getSize());
        assertEquals(p1, list1.getIndex(0));
    }

    @Test
    void testAddAnotherPerson() {
        list1.addPerson(p1);
        list1.addPerson(p2);
        assertEquals(2, list1.getSize());
        assertEquals(p1, list1.getIndex(0));
        assertEquals(p2, list1.getIndex(1));
    }

    @Test
    void testGetNames() {
        list1.addPerson(p1);
        list1.addPerson(p2);
        assertEquals("Alicia Chow\nMegan Wong\n",list1.getNames());
    }

    @Test
    void removeLastPerson() {
        list1.addPerson(p1);
        list1.addPerson(p2);
        list1.removeLastPerson();
        assertEquals("Alicia Chow\n", list1.getNames());
        assertEquals(1, list1.getSize());
        assertEquals(p1, list1.getIndex(0));
    }
}
