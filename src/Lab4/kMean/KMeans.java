package Lab4.kMean;

import Lab4.data.Iris;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

public class KMeans {

    /**
     * @param k    number of defined clusters
     * @param data data set of instances that has to be clustered
     * @return ArrayList of Cluster instances
     */
    public static ArrayList<KMeanCluster> KMeansPartition(int k, ArrayList<Iris> data) {
        Hashtable<Iris, KMeanCluster> clusters = new Hashtable<>();
        while (clusters.size() < k) {
            clusters.put(data.get((int) (Math.random() * data.size())), new KMeanCluster());
        }

        while (true) {
            boolean noChange = true;                                          // in case if all centers were untouched we'll come out of while loop
            // reassign each object to the cluster to which the object is the most similar, based of the mean value of the objects in the cluster
            for (Iris iris : data) {
                TreeMap<Double, Iris> distMin = new TreeMap<>();       // map with distances from iris to each center
                for (Map.Entry center : clusters.entrySet())
                    distMin.put(KMeans.euclidianDistance(iris, (Iris) center.getKey()), (Iris) center.getKey());
                Iris minCenter = distMin.get(distMin.firstKey());
                clusters.get(minCenter).ClusterMembers.add(iris);                                          // add Iris from data to the closest center
            }

            // update the cluster means, that is, calculate the mean value of the objects for each cluster
            Hashtable<Iris, KMeanCluster> tempCenters = new Hashtable<>();
            for (Map.Entry center : clusters.entrySet()) {
                Iris newCenter = ((KMeanCluster) center.getValue()).clusterMean();
                Iris oldCenter = (Iris) center.getKey();
                if (!newCenter.equals(oldCenter))
                    noChange = false;
                tempCenters.put(newCenter, new KMeanCluster());
            }
            if (!noChange)
                clusters = new Hashtable<>(tempCenters);   // start it with new centers
            else
                break;
        }
        return new ArrayList<>(clusters.values());
    }

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
