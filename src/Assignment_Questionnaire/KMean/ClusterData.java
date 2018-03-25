package Assignment_Questionnaire.KMean;

import Assignment_Questionnaire.Library.Normalize;
import Assignment_Questionnaire.Student;

import java.util.ArrayList;

import static Assignment_Questionnaire.StudentManager.getStudents;

@SuppressWarnings("all")
public class ClusterData {
    private static ArrayList<ClusterObject> clusterObjects;

    public static void main(String[] args) {
        String path = "/data/Data Mining - Spring 2018.csv";
        ArrayList<Student> students = getStudents();
        printClusterData(students);

    }

    /**
     * Method creating a data set from chosen attributes to process in clustering algorithm
     *
     * @param list
     * @param attributes
     * @return
     */
    public static ArrayList<ClusterObject> createClusterData(ArrayList<Student> list, Object... attributes) {
        clusterObjects = new ArrayList<>();
        for (Student s : list) {
            ClusterObject.Builder clusterObject = ClusterObject.newBuilder();
            clusterObject.setDegree(s.s_degree);
            for (Object attribute : attributes) {
                String name = ((Class) attribute).getSimpleName();
                clusterObject.setAttribute(name, Normalize.attribute(name, s));
            }
            clusterObjects.add(clusterObject.build());
        }
        return clusterObjects;
    }

    /**
     * Method printing out cluster data
     *
     * @param list
     */
    public static void printClusterData(ArrayList<Student> list) {
        if (clusterObjects == null) createClusterData(list);
        clusterObjects.forEach(System.out::println);
    }
}