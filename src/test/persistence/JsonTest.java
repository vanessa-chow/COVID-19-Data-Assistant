package persistence;

import model.Person;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Code modeled after https://github.com/stleary/JSON-java.git
public class JsonTest {
    protected void checkPerson(String name, String phoneNumber,String placesVisited, Person person) {
        assertEquals(name, person.getName());
        assertEquals(phoneNumber, person.getPhoneNumber());
        assertEquals(placesVisited, person.getPlacesVisited());
    }
}
