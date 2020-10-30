package persistence;

import model.Person;
import model.ListOfPerson;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Code modeled after https://github.com/stleary/JSON-java.git
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfPerson lop = new ListOfPerson("list of persons");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyListOfPerson() {
        try {
            ListOfPerson lop = new ListOfPerson("My list of person");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyListOfPerson.json");
            writer.open();
            writer.write(lop);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyListOfPerson.json");
            lop = reader.read();
            assertEquals("My list of person", lop.getName());
            assertEquals(0, lop.numPersons());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralListOfPerson() {
        try {
            ListOfPerson lop = new ListOfPerson("My list of persons");
            lop.addPerson(new Person("person1", "12345", "place 1, place 2"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralListOfPerson.json");
            writer.open();
            writer.write(lop);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralListOfPerson.json");
            lop = reader.read();
            assertEquals("My list of persons", lop.getName());
            List<Person> persons = lop.getPersons();
            assertEquals(1, persons.size());
            checkPerson("person1", "12345", "place 1, place 2", persons.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
