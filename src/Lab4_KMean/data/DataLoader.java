package Lab4_KMean.data;

import Assignment_Questionnaire.Library.CSVFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static Assignment_Questionnaire.Library.NU.minMax;
import static Assignment_Questionnaire.Library.NU.round;

public class DataLoader {


    public static ArrayList<Iris> LoadAllIrisData() {
        ArrayList<Double> doubles = new ArrayList<>();
        ArrayList<Iris> data = new ArrayList<>();

        try {
            String[][] dataOrig = CSVFileReader.readDataFile(System.getProperty("user.dir") + "/src/Lab4_KMean/data/iris.csv", ",", "", true);
            for (int j = 0; j < dataOrig.length; j++) {
                for (int k = 0; k < 4; k++)
                    doubles.add(Double.parseDouble(dataOrig[j][k]));
            }
            double min = Collections.min(doubles);
            double max = Collections.max(doubles);
            for (int i = 0; i < dataOrig.length; i++) {
                double sepal_length = round(minMax(Double.parseDouble(dataOrig[i][0]), min, max, 0.0, 1.0), 2);
                double sepal_width = round(minMax(Double.parseDouble(dataOrig[i][1]), min, max, 0.0, 1.0),2);
                double petal_length = round(minMax(Double.parseDouble(dataOrig[i][2]), min, max, 0.0, 1.0),2);
                double petal_width = round(minMax(Double.parseDouble(dataOrig[i][3]), min, max, 0.0, 1.0),2);
                String iris_class = dataOrig[i][4];

                data.add(new Iris((float) sepal_length, (float) sepal_width, (float) petal_length, (float) petal_width, iris_class));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Loaded " + data.size() + " iris flowers.");
        return data;

    }

}
