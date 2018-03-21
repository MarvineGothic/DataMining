package Assignment_Questionnaire;

import Assignment_Questionnaire.enums.*;

import java.util.ArrayList;

import static Assignment_Questionnaire.Apriori.Apriori.*;
import static Assignment_Questionnaire.KMean.ClusterData.createClusterData;
import static Assignment_Questionnaire.KMean.KMeans.KMeansPartition;
import static Assignment_Questionnaire.StudentManager.getStudents;
import static Assignment_Questionnaire.StudentManager.printStudentData;

@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        printStudentData();
        ArrayList<Student> students = getStudents();

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
        apriori(supportTreshold, false);
        // print out association rules:
        associationRules(confidenceLimit, definingClass, definedClass);

        // Clustering KMean
        KMeansPartition(6, createClusterData(students, GamesPlayed.class), true);

        //FoundClusters_KMeans.forEach(k -> System.out.println(k.getMainCluster()));
    }
}
