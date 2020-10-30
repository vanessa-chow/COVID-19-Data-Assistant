package persistence;

import model.ListOfPerson;
import model.Person;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Code modeled after https://github.com/stleary/JSON-java.git

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListOfPerson from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfPerson read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfPerson(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses lop from JSON object and returns it
    private ListOfPerson parseListOfPerson(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray persons = jsonObject.getJSONArray("persons");
        ListOfPerson lop = new ListOfPerson(name);

        for (int i = 0; i < persons.length(); i++) {
            JSONObject personObject = persons.getJSONObject(i);
            addPerson(lop, personObject);
        }
        return lop;
    }

    // MODIFIES: lop
    // EFFECTS: parses persons from JSON object and adds them to ListOfPerson
    private void addPersons(ListOfPerson lop, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("person");
        for (Object json : jsonArray) {
            JSONObject nextPerson = (JSONObject) json;
            addPerson(lop, nextPerson);
        }
    }

    // MODIFIES: lop
    // EFFECTS: parses person from JSON object and adds it to lop
    private void addPerson(ListOfPerson lop, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String phoneNumber = jsonObject.getString("phoneNumber");
        String placesVisited = jsonObject.getString("placesVisited");
        Person person = new Person(name, phoneNumber, placesVisited);
        // loop to add more than one person (write out as an array)
        lop.addPerson(person);
    }
}
