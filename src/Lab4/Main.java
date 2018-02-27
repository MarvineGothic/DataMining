package Lab4;

import Lab4.Utils.Cluster;
import Lab4.data.DataLoader;
import Lab4.data.Iris;
import Lab4.kMean.KMeanCluster;
import Lab4.kMean.KMeans;
import Lab4.kMedoid.KMedoid;
import Lab4.kMedoid.KMedoidCluster;

import java.util.ArrayList;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //First step load in iris data
        ArrayList<Iris> irisData = DataLoader.LoadAllIrisData();

        //Second step --> do the clustering using k-means!
        ArrayList<Cluster> FoundClusters_KMeans = KMeans.KMeansPartition(3, irisData);
        //FoundClusters_KMeans.forEach(System.out::println);

        //Third step --> do the clustering using k-medoids!
        ArrayList<Cluster> FoundClusters_KMedoids = KMedoid.KMedoidPartition(3, irisData);
        FoundClusters_KMedoids.forEach(System.out::println);
    }

}
