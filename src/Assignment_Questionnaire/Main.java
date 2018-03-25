package Assignment_Questionnaire;

import Assignment_Questionnaire.DecisionTree.DecisionTree;
import Assignment_Questionnaire.ID3.Algorithm;
import Assignment_Questionnaire.ID3.DecisionTreeGenerator;
import Assignment_Questionnaire.ID3.Node;
import Assignment_Questionnaire.enums.Degree;
import Assignment_Questionnaire.enums.Gender;

import java.util.ArrayList;
import java.util.Map;

import static Assignment_Questionnaire.StudentManager.getStudents;
import static Assignment_Questionnaire.StudentManager.printStudentData;

@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {
        printStudentData();
        ArrayList<Student> students = getStudents();
        // Apriori
        // parameters:
       /* int supportTreshold = 5;
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
        KMeansPartition(3, createClusterData(students, GamesPlayed.class,*//* Topic_DED.class, Topic_CDMA.class,
                Topic_CPM.class, Topic_DGS.class, Topic_SPG.class, Topic_SPI.class,*//* Game.class), true);*/

        //FoundClusters_KMeans.forEach(k -> System.out.println(k.getMainCluster()));

        // ID3
        Algorithm id3 = new Algorithm(students);
        //Map<Double, Class> attributes = id3.attributeSelection(Student.getAttributeList(), Degree.class, true);
        Node<Object> node = DecisionTreeGenerator.generateDecisionTree(students, Student.getAttributeList(), Gender.class);
        System.out.println(node);

    }
}
