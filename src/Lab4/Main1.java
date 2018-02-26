package Lab4;

import Lab4.data.Iris;
import Lab4.data.IrisClass;
import Lab4.kMean.KMeanCluster;
import Lab4.kMean.KMeans;
import Lab4.kMedoid.KMedoid;
import Lab4.kMedoid.KMedoidCluster;

import java.util.ArrayList;
import java.util.List;

import static Lab4.data.IrisClass.Iris_setosa;

public class Main1 {
    public static void main(String[] args) {
        ArrayList<Iris> list = new ArrayList<>();
        list.add(new Iris(0.0f, 0.0f, 1.0f, 1.0f, IrisClass.Iris_versicolor));
        list.add(new Iris(0.0f, 0.0f, 1.0f, 1.0f, IrisClass.Iris_virginica));
        list.add(new Iris(0.0f, 0.0f, 1.0f, 1.0f, Iris_setosa));
        list.add(new Iris(0.0f, 0.0f, 1.0f, 1.0f, Iris_setosa));

        list.add(new Iris(0.1f, 0.0f, 1.0f, 1.0f, Iris_setosa));
        list.add(new Iris(0.15f, 0.0f, 1.0f, 1.0f, IrisClass.Iris_versicolor));
        list.add(new Iris(0.15f, 0.1f, 1.0f, 1.0f, Iris_setosa));
        list.add(new Iris(0.1f, 0.15f, 1.0f, 1.0f, Iris_setosa));

        list.add(new Iris(0.2f, 0.0f, 1.0f, 1.0f, Iris_setosa));

        //List<KMeanCluster> clusters = KMeans.KMeansPartition(3, list);

        ArrayList<Iris> medoidList = new ArrayList<>();
        medoidList.add(new Iris(2f, 6f, 0f,0f, Iris_setosa));
        medoidList.add(new Iris(3f, 4f, 0f,0f, Iris_setosa));
        medoidList.add(new Iris(3f, 8f, 0f,0f, Iris_setosa));
        medoidList.add(new Iris(4f, 7f, 0f,0f, Iris_setosa));
        medoidList.add(new Iris(6f, 2f, 0f,0f, Iris_setosa));
        medoidList.add(new Iris(6f, 4f, 0f,0f, Iris_setosa));
        medoidList.add(new Iris(7f, 3f, 0f,0f, Iris_setosa));
        medoidList.add(new Iris(7f, 4f, 0f,0f, Iris_setosa));
        medoidList.add(new Iris(8f, 5f, 0f,0f, Iris_setosa));
        medoidList.add(new Iris(7f, 6f, 0f,0f, Iris_setosa));


        List<KMedoidCluster> clusters = KMedoid.KMedoidPartition(2,medoidList);
        clusters.forEach(System.out::println);
    }
}
