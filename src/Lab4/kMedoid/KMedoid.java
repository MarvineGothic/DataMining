package Lab4.kMedoid;

import Lab4.data.Iris;
import Lab4.data.IrisClass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class KMedoid {

    public static ArrayList<KMedoidCluster> KMedoidPartition(int k, ArrayList<Iris> data) {
        ArrayList<KMedoidCluster> clusters = new ArrayList<>();
        Set<Iris> randIris = new HashSet<>();

        while (randIris.size() < k)
            randIris.add(data.get((int) (Math.random() * data.size())));

        for (Iris iris : randIris)
            clusters.add(new KMedoidCluster(iris));



        while (true) {
            boolean noChange = true;
            for (Iris iris : data) {
                KMedoidCluster cluster = clusters.get(0);
                for (int i = 1; i < clusters.size(); i++)
                    cluster = manhattanDistance(iris, clusters.get(i).mainCluster) <
                            manhattanDistance(iris, cluster.mainCluster) ? clusters.get(i) : cluster;
                cluster.ClusterMembers.add(iris);
            }
            double totalCost = medoidCost(clusters);
            for (KMedoidCluster cluster : clusters) {
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
            }
            /*for (KMedoidCluster cluster : clusters) {
                Iris betterMedoid = cluster.Medoid;
                for (Iris member : cluster.ClusterMembers) {
                    cluster.Medoid = member;
                    double checkCost = medoidCost(clusters);
                    if (checkCost < totalCost) {
                        betterMedoid = member;
                        totalCost = checkCost;
                        noChange = false;
                    }
                }
                cluster.Medoid = betterMedoid;
            }*/

            if (!noChange) {
                for (KMedoidCluster cluster : clusters)
                    cluster.ClusterMembers = new ArrayList<>();
            } else
                break;
        }

        return clusters;
    }

    public static double manhattanDistance(Iris i1, Iris i2) {
        double dXs = Math.abs(i1.Sepal_Length - i2.Sepal_Length);
        double dYs = Math.abs(i1.Sepal_Width - i2.Sepal_Width);
        double dXp = Math.abs(i1.Petal_Length - i2.Petal_Length);
        double dYp = Math.abs(i1.Petal_Width - i2.Petal_Width);
        return dXs + dXp + dYs + dYp;
    }

    public static double medoidCost(List<KMedoidCluster> clusters) {
        double totalCost = 0;
        for (KMedoidCluster cluster : clusters)
            for (Iris clusterMember : cluster.ClusterMembers)
                totalCost += manhattanDistance(cluster.mainCluster, clusterMember);
        return totalCost;
    }

}
