package Reader;

import java.io.IOException;
import java.util.Arrays;

import static Assignment_Questionnaire.Library.CSVFileReader.readDataFile;

public class Main {
    public static void main(String[] args) {
        try {
            String[][] data = readDataFile("data/Data Mining - Spring 2018.csv", "\",\"", "-", false);
            //Print all the data
            for (String[] line : data) {
                System.out.println(Arrays.toString(line));
            }
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

}
