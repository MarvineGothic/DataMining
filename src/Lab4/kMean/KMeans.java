package Lab4.kMean;

import Lab4.Utils.KMU;
import Lab4.data.Iris;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class KMeans {

    /**
     * Start by creating a List of cluster instances to make.
     * A set of randomly picked unique centers for clustering fills up to the size of k.
     * Then first initial cluster list fills up with new instances of clusters with initial centers.
     * In a while loop:
     * assign a boolean noChange which shows that we need to stop the loop if centers can't be changed any more
     * Two nested for loops reassign each object to the cluster to which the object is the most similar,
     * based of the mean value of the objects in the cluster. By comparing distances from object to all centers
     * we find the smallest one and use the index of the closest center to add an object to a cluster.
     * Then update the cluster means in the next for loop, using a temporary list of clusters.
     * if new calculated center differs from the previous, value of noChange become false. Temporary
     * list is filed up any way, in the case if at least one center must be updated, then we need to repeat
     * while loop again.
     * In the end we check if changes were made  - by noChange. If there are changes, we assign list of clusters
     * by a new list with new centers. In the other case job is done and we break the while loop.
     * Before returning the result we sort the list. Basically for the sake of testing.
     *
     * @param k    number of defined clusters
     * @param data data set of instances that has to be clustered
     * @return ArrayList of Cluster instances
     */
    public static ArrayList<KMeanCluster> KMeansPartition(int k, ArrayList<Iris> data) {
        ArrayList<KMeanCluster> clusters = new ArrayList<>();
        Set<Iris> randIris = new HashSet<>();

        while (randIris.size() < k)
            randIris.add(data.get((int) (Math.random() * data.size())));

        for (Iris iris : randIris)
            clusters.add(new KMeanCluster(iris));

        while (true) {
            boolean noChange = true;
            for (Iris iris : data) {
                int minClusterIndex = 0;
                for (int i = 1; i < clusters.size(); i++)
                    minClusterIndex = KMeans.euclidianDistance(iris, clusters.get(i).mainCluster) <
                            KMeans.euclidianDistance(iris, clusters.get(minClusterIndex).mainCluster) ?
                            i : minClusterIndex;
                clusters.get(minClusterIndex).ClusterMembers.add(iris);
            }

            ArrayList<KMeanCluster> tempCenters = new ArrayList<>();
            for (KMeanCluster cluster : clusters) {
                Iris newCenter = KMU.clusterMean(cluster.ClusterMembers);
                if (!newCenter.equals(cluster.mainCluster))
                    noChange = false;
                tempCenters.add(new KMeanCluster(newCenter));
            }

            if (!noChange)
                clusters = new ArrayList<>(tempCenters);
            else
                break;
        }
        Collections.sort(clusters);
        return clusters;
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
}
