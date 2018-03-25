package Assignment_Questionnaire;

import Assignment_Questionnaire.DecisionTree.DecisionTree;
import Assignment_Questionnaire.ID3.Algorithm;
import Assignment_Questionnaire.ID3.DecisionTreeGenerator;
import Assignment_Questionnaire.ID3.Node;
import Assignment_Questionnaire.enums.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static Assignment_Questionnaire.Apriori.Apriori.apriori;
import static Assignment_Questionnaire.Apriori.Apriori.associationRules;
import static Assignment_Questionnaire.Apriori.Apriori.createTransactions;
import static Assignment_Questionnaire.KMean.ClusterData.createClusterData;
import static Assignment_Questionnaire.KMean.KMeans.KMeansPartition;
import static Assignment_Questionnaire.StudentManager.*;

@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        String path;
        ArrayList<Student> students;
       /* // Apriori
        // parameters:
       int supportTreshold = 0;
        double confidenceLimit = 0;
        String option;
        Class definingClass = null;
        Class definedClass = Degree.class;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            while (true) {
                boolean finish = false;
                path = br.readLine();
                try {
                    System.out.println("Type a file path to open:");
                    loadData(path);
                    students = getStudents(path);
                    finish = true;
                    System.out.println("Successfully loaded data!\nPress ENTER");
                } catch (IOException e) {
                    System.out.println("Wrong path or file.\nTry again");
                }
                if (finish) break;
            }

            System.out.println("Menu:\n" +
                    "0 - Print out a Student data\n" +
                    "1 - Apriori\n" +
                    "2 - ");

            while ((input = br.readLine()) != null) {
                input = input.toUpperCase();
                if (input.equals("exit")) break;
                if (input.equals("0")) printStudentData(path);
                if (input.equals("1")){
                    System.out.println("Type support treshold:");
                    supportTreshold = Integer.parseInt(br.readLine());
                    System.out.println("Type confidence limit:");
                    confidenceLimit = Double.parseDouble(br.readLine());
                    System.out.println("Choose an option:\n0 - defining class;\n1 - defined class;");
                    option = br.readLine();
                    System.out.println("Choose a class:");

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        path = System.getProperty("user.dir")+"/Resources/Data Mining - Spring 2018.csv";
        try {
            loadData(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        printStudentData();
        students = getStudents();
        // Apriori
        // parameters:
        int supportTreshold = 5;
        double confidenceLimit = 100;
        Class definingClass = null;
        Class definedClass = Degree.class;

        // creating array of attribute values:
        createTransactions(students, Degree.class, ReasonTakeCourse.class,
                Topic_DED.class, Topic_SPS.class, Topic_UDMT.class, Topic_CDMA.class, Topic_CPM.class);
        // algorithm
        apriori(supportTreshold, true);
        // print out association rules:
        associationRules(confidenceLimit, definingClass, definedClass);

        // Clustering KMean
        KMeansPartition(3, createClusterData(students, GamesPlayed.class, Topic_DED.class, Topic_CDMA.class,
                Topic_CPM.class, Topic_DGS.class, Topic_SPG.class, Topic_SPI.class, Game.class), true);

        //FoundClusters_KMeans.forEach(k -> System.out.println(k.getMainCluster()));

        // ID3
        Algorithm id3 = new Algorithm(students);
        //Map<Double, Class> attributes = id3.attributeSelection(Student.getAttributeList(), Degree.class, true);
        Node<Object> node = DecisionTreeGenerator.generateDecisionTree(students, Student.getAttributeList(), Degree.class);
        new DecisionTree(node, "Students").run();
    }
}
