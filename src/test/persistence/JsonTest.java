package persistence;

import model.Person;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPerson(String name, String phoneNumber,String placesVisited, Person person) {
        assertEquals(name, person.getName());
        assertEquals(phoneNumber, person.getPhoneNumber());
        assertEquals(placesVisited, person.getPlacesVisited());
    }
}
