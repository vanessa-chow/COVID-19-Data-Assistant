package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PersonTest {
    public Person p1;
    public Person p2;

    @BeforeEach
    void runBefore() {
        p1 = new Person("Alicia Chow", "604-786-1061", "Lougheed Mall, SFU");
        p2 = new Person("Megan Wong", "604-716-0161", "ICBC, Burnaby City Hall");
    }

    @Test
    void testConstructor() {
        assertEquals("Alicia Chow", p1.getName());
        assertEquals("604-786-1061", p1.getPhoneNumber());
        assertEquals("Lougheed Mall, SFU", p1.getPlacesVisited());
    }

    @Test
    void testPersonTwo() {
        assertEquals("Megan Wong", p2.getName());
        assertEquals("604-716-0161", p2.getPhoneNumber());
        assertEquals("ICBC, Burnaby City Hall", p2.getPlacesVisited());
    }

    @Test
    void testToString() {
        assertEquals(p1.toString(), "name:Alicia Chow:\n" +
                "phone number:604-786-1061\n" +
                " places visited:Lougheed Mall, SFU");
    }

}