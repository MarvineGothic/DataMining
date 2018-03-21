package Assignment_Questionnaire.KMean;

import Assignment_Questionnaire.Student;

import java.util.ArrayList;

public abstract class Cluster implements Comparable<Cluster> {
    public ClusterObject mainCluster;
    public ArrayList<ClusterObject> ClusterMembers;

    public Cluster() {
        this.ClusterMembers = new ArrayList<>();
    }

    public Cluster(ClusterObject mainCluster) {
        this();
        this.mainCluster = mainCluster;
    }

    public ClusterObject getMainCluster() {
        return mainCluster;
    }

    public ArrayList<ClusterObject> getClusterMembers() {
        return ClusterMembers;
    }


}
