package Lab4.kMean;

import Lab4.Utils.Cluster;
import Lab4.data.Iris;

import java.util.ArrayList;
import java.util.Collections;

import static Lab4.Utils.KMU.*;

public class KMeans {

    /**
     * Start by creating a List of cluster instances to make using a randomCenter method from KMU utility class.
     * In a while loop:
     * assign a boolean noChange which shows that we need to stop the loop if centers can't be changed any more
     * Then update the cluster means in the next for loop, using a temporary list of clusters.
     * if new calculated center differs from the previous, value of noChange become false.
     * Temporary list is filed up any way, in the case if at least one center must be updated, then we need to repeat
     * while loop again.
     * In the end we check if changes were made  - by noChange. If there are changes, we assign list of clusters
     * by a new list with new centers. In the other case job is done and we break the while loop.
     * Before returning the result we sort the list. Basically for the sake of testing.
     *
     * @param k    number of defined clusters
     * @param data data set of instances that has to be clustered
     * @return ArrayList of Cluster instances
     */
    public static ArrayList<Cluster> KMeansPartition(int k, ArrayList<Iris> data) {

        ArrayList<Cluster> clusters = randomCenter(k, data, new KMeanCluster());

        while (true) {
            boolean noChange = true;

            makeClusters(data, clusters);

            ArrayList<Cluster> tempCenters = new ArrayList<>();
            for (Cluster cluster : clusters) {
                Iris newCenter = clusterMean(cluster.ClusterMembers);
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


}
