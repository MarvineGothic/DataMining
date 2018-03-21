package Lab4_KMean.kMedoid;

import Lab4_KMean.Utils.Cluster;
import Lab4_KMean.data.Iris;

public class KMedoidCluster extends Cluster {

    public KMedoidCluster(Iris mainCluster) {
        super(mainCluster);
    }

    public KMedoidCluster() {
    }

    @Override
    public String toString() {
        String toPrint = "-----------------------------------CLUSTER START------------------------------------------"
                + System.getProperty("line.separator") + "Medoid: " + this.mainCluster.toString()
                + "\n------------------------------------------------------------------------------------------\n";
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
