package persistence;

import model.ListOfPerson;
import model.Person;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfPerson lop = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testReaderEmptyListOfPerson() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyListOfPerson.json");
        try {
            ListOfPerson lop = reader.read();
            assertEquals("My list of person", lop.getName());
            assertEquals(0, lop.numPersons());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralListOfPerson() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralListOfPerson.json");
        try {
            ListOfPerson lop = reader.read();
            assertEquals("My list of person", lop.getName());
            List<Person> persons = lop.getPersons();
            assertEquals(1, persons.size());
            checkPerson("person1", "12345", "place 1, place 2", persons.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

