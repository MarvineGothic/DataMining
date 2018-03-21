package Lab4_KMean;

import Lab4_KMean.Utils.Cluster;
import Lab4_KMean.data.DataLoader;
import Lab4_KMean.data.Iris;
import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static Lab4_KMean.kMean.KMeans.KMeansPartition;

//import Lab4_KMean.kMean.KMeans;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //First step load in iris data
        ArrayList<Iris> irisData = DataLoader.LoadAllIrisData();

        //Second step --> do the clustering using k-means!
        ArrayList<Cluster> FoundClusters_KMeans = KMeansPartition(3, irisData);
        FoundClusters_KMeans.forEach(System.out::println);
        FoundClusters_KMeans.forEach(k -> System.out.println(k.getMainCluster()));


        //Third step --> do the clustering using k-medoids!
        /*ArrayList<Cluster> FoundClusters_KMedoids = KMedoid.KMedoidPartition(3, irisData);
        FoundClusters_KMedoids.forEach(System.out::println);*/
    }

    public static void exampleJavaML() {
        /* Load a dataset */
        Dataset data = null;
        try {
            data = FileHandler.loadDataset(new File(System.getProperty("user.dir") + "/src/Lab4_KMean/data/iris.csv"), 4, ",");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
         * Create a new instance of the KMeans algorithm, with no options
         * specified. By default this will generate 4 clusters.
         */
        Clusterer km = new KMeans();
        /*
         * Cluster the data, it will be returned as an array of data sets, with
         * each dataset representing a cluster
         */
        Dataset[] clusters = km.cluster(data);
        System.out.println("Cluster count: " + clusters.length);
    }

}
