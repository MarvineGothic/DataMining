package Lab4_KMean.kMean;

import Lab4_KMean.Utils.Cluster;
import Lab4_KMean.data.Iris;

public class KMeanCluster extends Cluster {

    public KMeanCluster() {
        super();
    }

    public KMeanCluster(Iris clusterCenter) {
        super(clusterCenter);
    }

    @Override
    public String toString() {
        String toPrint = "-----------------------------------CLUSTER START------------------------------------------"
                + System.getProperty("line.separator") + "Center:\n" + mainCluster.toString() +
                "\n------------------------------------------------------------------------------------------\n";

        for (Iris i : this.ClusterMembers) {
            toPrint = toPrint.concat(i.toString() + System.getProperty("line.separator"));
        }
        toPrint = toPrint.concat("-----------------------------------CLUSTER END-------------------------------------------"
                + System.getProperty("line.separator"));

        return toPrint;
    }

    @Override
    public int compareTo(Cluster o) {
        return Float.compare(this.mainCluster.Sepal_Length + this.mainCluster.Sepal_Width +
                        this.mainCluster.Petal_Length + this.mainCluster.Petal_Width,
                o.mainCluster.Sepal_Length + o.mainCluster.Sepal_Width +
                        o.mainCluster.Petal_Length + o.mainCluster.Petal_Width);
    }

}
