package Lab1_Preprocessing;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static common.CSVFileReader.readDataFile;
import static common.CU.*;

/**
 * The Lab2.Main class is used to load a csv file
 */
public class Main {


    public static void main(String args[]) {

        try {
            String[][] data = readDataFile("data/Data Mining - Spring 2018.csv", "\",\"", "-", false);

            //Print all the data
            /*for (String[] line : data) {
                System.out.println(line[1].replace(".", "").replace("Around", "").trim());
                //System.out.println(Arrays.toString(line));
            }*/

      //     processShoeSize(data);
            //Print a specific entry in the data
            //System.out.println("Number of tuples loaded: " + data.length);
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
/*
    public static double processShoeSize(String[][] data) {
        HashMap<String, List<String>> shoe = new HashMap<>();
        List<Double> shoes = new ArrayList<>();
        String shoeTitle = data[0][3];
        shoe.put(shoeTitle, new ArrayList<>());
        for (int i = 1; i < data.length; i++) {
            shoe.get(shoeTitle).add(data[i][1]);
        }
        int initialSize = shoe.get(shoeTitle).size();
        shoes.addAll(sortDigitsOut(data, 3, "\\d{2}(?:\\.?\\d*)?"));
        //shoeTitle.matches("\\d{2}\\.?\\d");
        //System.out.printf("Shoes:\nSize before: %d Size after: %d\nMean: %.1f  Median: %.1f\n", initialSize, shoes.size(),meanD(shoes), median(shoes));
        removeTwoPercent(shoes);
        //System.out.println("Size after remove:" + shoes.size() + " Mean: " + meanD(shoes));
        //modes(shoes).forEach(System.out::println);
        return meanD(shoes);
    }

    public static double processAge(String[][] data) {
        HashMap<String, List<String>> age = new HashMap<>();
        List<Double> ageProcessed = new ArrayList<>();
        String ageTitle = data[0][1];
        age.put(ageTitle, new ArrayList<>());
        for (int i = 1; i < data.length; i++)
            age.get(ageTitle).add(data[i][1]);
        int initialSize = age.get(ageTitle).size();
        ageProcessed.addAll(sortDigitsOut(data, 1, "\\d{2}"));
       // System.out.printf("Age:\nSize before: %d ;Size after: %d\nMean: %s ; Median: %s", initialSize, ageProcessed.size(), meanD(ageProcessed), median(ageProcessed));
        removeTwoPercent(ageProcessed);
        //System.out.println("Size after remove:" + ageProcessed.size() + " Mean: " + meanD(ageProcessed));
        //modes(ageProcessed).forEach(System.out::println);
        return meanD(ageProcessed);
    }*/

    public static double processAttribute(String[][] data, String regex, int column) {
        HashMap<String, List<String>> attribute = new HashMap<>();
        List<Double> attributeProcessed = new ArrayList<>();
        String attributeTitle = data[0][column];
        attribute.put(attributeTitle, new ArrayList<>());
        for (int i = 1; i < data.length; i++)
            attribute.get(attributeTitle).add(data[i][1]);
        attributeProcessed.addAll(sortDigitsOut(data, column, regex));
        removeTwoPercent(attributeProcessed);
        return meanD(attributeProcessed);
    }
}