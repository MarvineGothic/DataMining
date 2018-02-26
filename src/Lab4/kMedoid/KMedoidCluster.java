package Lab4.kMedoid;

import Lab4.Utils.Cluster;
import Lab4.data.Iris;

public class KMedoidCluster extends Cluster {
/*
    public ArrayList<Iris> ClusterMembers;
    public Iris Medoid;*/

    /*public KMedoidCluster(Iris medoid) {
        this.ClusterMembers = new ArrayList<>();
        this.Medoid = medoid;
    }*/

    public KMedoidCluster(Iris mainCluster) {
        super(mainCluster);
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

}
