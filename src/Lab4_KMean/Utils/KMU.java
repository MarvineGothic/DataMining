package Lab4_KMean.Utils;

import Lab4_KMean.data.Iris;
import Lab4_KMean.data.IrisClass;
import Lab4_KMean.kMean.KMeanCluster;
import Lab4_KMean.kMedoid.KMedoidCluster;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static Lab4_KMean.data.IrisClass.*;

public class KMU {

    /**
     * Randomly choosing unique Objects as cluster centers
     * @param k
     * @param data
     * @param cluster
     * @return
     */
    public static ArrayList<Cluster> randomCenter(int k, ArrayList<Iris> data, Cluster cluster) {
        ArrayList<Cluster> clusters = new ArrayList<>();
        Set<Iris> randIris = new HashSet<>();

        while (randIris.size() < k)
            randIris.add(data.get((int) (Math.random() * data.size())));

        for (Iris iris : randIris) {
            if (cluster instanceof KMeanCluster)
                clusters.add(new KMeanCluster(iris));
            else clusters.add(new KMedoidCluster(iris));
        }
        return clusters;
    }

    /**
     * Two nested for loops reassign each object to the cluster to which the object is the most similar,
     * based of the mean value of the objects in the cluster. By comparing distances from object to all centers
     * we find the closest one and add an object to the cluster.
     *
     * @param data
     * @param clusters
     */
    public static void makeClusters(List<Iris> data, List<Cluster> clusters){
        for (Iris iris : data) {
            Cluster cluster = clusters.get(0);
            for (int i = 1; i < clusters.size(); i++)
                cluster = manhattanDistance(iris, clusters.get(i).mainCluster) <
                        manhattanDistance(iris, cluster.mainCluster) ? clusters.get(i) : cluster;
            cluster.ClusterMembers.add(iris);
        }
    }

    /**
     * Calculate Average(mean) instance out of a list.
     *
     * @param ClusterMembers
     * @return
     */
    public static Iris clusterMean(List<Iris> ClusterMembers) {
        float avSL = (float) (ClusterMembers.stream().mapToDouble(a -> a.Sepal_Length)).average().orElse(-100);
        float avSW = (float) (ClusterMembers.stream().mapToDouble(a -> a.Sepal_Width)).average().orElse(-100);
        float avPL = (float) (ClusterMembers.stream().mapToDouble(a -> a.Petal_Length)).average().orElse(-100);
        float avPW = (float) (ClusterMembers.stream().mapToDouble(a -> a.Petal_Width)).average().orElse(-100);
        long setosa = ClusterMembers.stream().filter(a -> a.Class.equals(Iris_setosa)).count();
        long verticolor = ClusterMembers.stream().filter(a -> a.Class.equals(IrisClass.Iris_versicolor)).count();
        long virginica = ClusterMembers.stream().filter(a -> a.Class.equals(IrisClass.Iris_virginica)).count();
        IrisClass clazz = setosa > verticolor && setosa > virginica ? Iris_setosa : verticolor > setosa && verticolor > virginica ? Iris_versicolor :
                Iris_virginica;
        return new Iris(avSL, avSW, avPL, avPW, clazz);
    }

    /**
     * Method to calculate Euclidian distance for two objects with four parameters
     *
     * @param i1 first object
     * @param i2 second object
     * @return euclidian distance
     */
    public static double euclidianDistance(Iris i1, Iris i2) {
        double dXs = Math.pow(i1.Sepal_Length - i2.Sepal_Length, 2);
        double dYs = Math.pow(i1.Sepal_Width - i2.Sepal_Width, 2);
        double dXp = Math.pow(i1.Petal_Length - i2.Petal_Length, 2);
        double dYp = Math.pow(i1.Petal_Width - i2.Petal_Width, 2);
        return Math.sqrt(dXs + dYs + dXp + dYp);
    }

    public static double euclidianDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public static double manhattanDistance(Iris i1, Iris i2) {
        double dXs = Math.abs(i1.Sepal_Length - i2.Sepal_Length);
        double dYs = Math.abs(i1.Sepal_Width - i2.Sepal_Width);
        double dXp = Math.abs(i1.Petal_Length - i2.Petal_Length);
        double dYp = Math.abs(i1.Petal_Width - i2.Petal_Width);
        return dXs + dXp + dYs + dYp;
    }

    public static double medoidCost(List<Cluster> clusters) {
        double totalCost = 0;
        for (Cluster cluster : clusters)
            for (Iris clusterMember : cluster.ClusterMembers)
                totalCost += manhattanDistance(cluster.mainCluster, clusterMember);
        return totalCost;
    }
}
