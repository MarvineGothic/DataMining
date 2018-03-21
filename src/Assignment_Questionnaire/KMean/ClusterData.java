package Assignment_Questionnaire.KMean;

import Assignment_Questionnaire.Library.Normalize;
import Assignment_Questionnaire.Student;

import java.util.ArrayList;

import static Assignment_Questionnaire.StudentManager.getStudents;

public class ClusterData {
    private static ArrayList<ClusterObject> clusterObjects;

    public static void main(String[] args) {

        ArrayList<Student> students = getStudents();
        printClusterData(students);

    }

    public static ArrayList<ClusterObject> createClusterData(ArrayList<Student> list, Object... attributes) {
        clusterObjects = new ArrayList<>();
        for (Student s : list) {
            ClusterObject.Builder clusterObject = ClusterObject.newBuilder();
            clusterObject.setDegree(s.s_degree);
            for (int i = 0; i < attributes.length; i++) {
                String name = ((Class) attributes[i]).getSimpleName();
                clusterObject.setAttribute(name, Normalize.gamesPlayed(name, s));
            }
            clusterObjects.add(/*ClusterObject.newBuilder()
                    .setAttribute("Games", minMax(s.getGamesPlayed(), 0, StudentManager.maxGames(), 0, 1))
                    .setAttribute("DED", minMax(s.s_topicDED.getRate(), 0, 3, 0, 1))
                    .setAttribute("CPM", minMax(s.s_topicCPM.getRate(), 0, 3, 0, 1))
                    .setAttribute("DGS", minMax(s.s_topicDGS.getRate(), 0, 3, 0, 1))
                    .setAttribute("VD", minMax(s.s_topicVD.getRate(), 0, 3, 0, 1))
                    .setAttribute("SPS", minMax(s.s_topicSPS.getRate(), 0, 3, 0, 1))
                    .setAttribute("SPSQ", minMax(s.s_topicSPSQ.getRate(), 0, 3, 0, 1))
                    .setAttribute("SPG", minMax(s.s_topicSPG.getRate(), 0, 3, 0, 1))
                    .setAttribute("SPT", minMax(s.s_topicSPT.getRate(), 0, 3, 0, 1))
                    .setAttribute("SPI", minMax(s.s_topicSPI.getRate(), 0, 3, 0, 1))
                    .setAttribute("CDMA", minMax(s.s_topicCDMA.getRate(), 0, 3, 0, 1))
                    .setAttribute("UDMT", minMax(s.s_topicUDMT.getRate(), 0, 3, 0, 1))
                    .setDegree(s.s_degree)
                    .build());*/
                    clusterObject.build());
        }
        return clusterObjects;
    }

    public static void printClusterData(ArrayList<Student> list) {
        if (clusterObjects == null) createClusterData(list);
        clusterObjects.forEach(System.out::println);
    }
}

/*.setGames(minMax(games, 0, StudentManager.maxGames(), 0, 1))
                    .setDED(minMax(s.s_topicDED.getRate(), 0, 3, 0, 1))
                    .setCPM(minMax(s.s_topicCPM.getRate(), 0, 3, 0, 1))
                    .setDGS(minMax(s.s_topicDGS.getRate(), 0, 3, 0, 1))
                    .setVD(minMax(s.s_topicVD.getRate(), 0, 3, 0, 1))
                    .setSPS(minMax(s.s_topicSPS.getRate(), 0, 3, 0, 1))
                    .setSPSQ(minMax(s.s_topicSPSQ.getRate(), 0, 3, 0, 1))
                    .setSPG(minMax(s.s_topicSPG.getRate(), 0, 3, 0, 1))
                    .setSPT(minMax(s.s_topicSPT.getRate(), 0, 3, 0, 1))
                    .setSPI(minMax(s.s_topicSPI.getRate(), 0, 3, 0, 1))
                    .setCDMA(minMax(s.s_topicCDMA.getRate(), 0, 3, 0, 1))
                    .setUDMT(minMax(s.s_topicUDMT.getRate(), 0, 3, 0, 1))*/