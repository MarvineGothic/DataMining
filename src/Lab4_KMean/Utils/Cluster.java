package Lab4_KMean.Utils;

import Lab4_KMean.data.Iris;

import java.util.ArrayList;

public abstract class Cluster implements Comparable<Cluster> {
    public Iris mainCluster;
    public ArrayList<Iris> ClusterMembers;

    public Cluster() {
        this.ClusterMembers = new ArrayList<>();
    }

    public Cluster(Iris mainCluster) {
        this();
        this.mainCluster = mainCluster;
    }

    public Iris getMainCluster() {
        return mainCluster;
    }

    public ArrayList<Iris> getClusterMembers() {
        return ClusterMembers;
    }


}
