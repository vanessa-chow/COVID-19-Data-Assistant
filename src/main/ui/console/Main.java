package ui.console;

import exceptions.NoViewableNamesException;
import ui.console.CovidDataAssistant;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, NoViewableNamesException {
        new CovidDataAssistant();
    }
}
