package Lab4.kMean;

import Lab4.Utils.Cluster;
import Lab4.data.Iris;

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

}
