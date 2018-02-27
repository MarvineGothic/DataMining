package Lab4.kMedoid;

import Lab4.Utils.Cluster;
import Lab4.data.Iris;

import java.util.ArrayList;

import static Lab4.Utils.KMU.*;


public class KMedoid {

    public static ArrayList<Cluster> KMedoidPartition(int k, ArrayList<Iris> data) {

        ArrayList<Cluster> clusters = randomCenter(k, data, new KMedoidCluster());

        while (true) {
            boolean noChange = true;
            makeClusters(data, clusters);

            double totalCost = medoidCost(clusters);
            /*for (KMedoidCluster cluster : clusters) {
                Iris betterMedoid = cluster.mainCluster;
                for (Iris member : data) {
                    cluster.mainCluster = member;
                    double checkCost = medoidCost(clusters);
                    if (checkCost < totalCost) {
                        betterMedoid = member;
                        totalCost = checkCost;
                        noChange = false;
                    }
                }
                cluster.mainCluster = betterMedoid;
            }*/
            for (Cluster cluster : clusters) {
                Iris betterMedoid = cluster.mainCluster;
                for (Iris member : cluster.ClusterMembers) {
                    cluster.mainCluster = member;
                    double checkCost = medoidCost(clusters);
                    if (checkCost < totalCost) {
                        betterMedoid = member;
                        totalCost = checkCost;
                        noChange = false;
                    }
                }
                cluster.mainCluster = betterMedoid;
            }

            if (!noChange) {
                for (Cluster cluster : clusters)
                    cluster.ClusterMembers = new ArrayList<>();
            } else
                break;
        }

        return clusters;
    }


}
