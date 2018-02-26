package Lab4.Utils;

import Lab4.data.Iris;

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

    @Override
    public int compareTo(Cluster o) {
        return Float.compare(this.mainCluster.Sepal_Length + this.mainCluster.Sepal_Width +
                        this.mainCluster.Petal_Length + this.mainCluster.Petal_Width,
                o.mainCluster.Sepal_Length + o.mainCluster.Sepal_Width +
                        o.mainCluster.Petal_Length + o.mainCluster.Petal_Width);
    }
}
