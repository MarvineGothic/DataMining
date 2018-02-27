package Lab1_Preprocessing;


import java.io.IOException;
import java.util.Arrays;

import static common.CSVFileReader.readDataFile;

/**
 * The Lab2.Main class is used to load a csv file
 */
public class Main {


    public static void main(String args[]) {
        try {
            String[][] data = readDataFile("data/Data Mining - Spring 2018.csv", "\",\"", "-", false);

            //Print all the data
            for (String[] line : data) {
                System.out.println(line[6].replace(".", "").replace("Around", "").trim());
                //System.out.println(Arrays.toString(line));
            }

            //Print a specific entry in the data
            //System.out.println(Arrays.toString(data[1]));
            System.out.println("Number of tuples loaded: " + data.length);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}