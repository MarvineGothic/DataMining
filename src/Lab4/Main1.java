package Lab4;

import Lab4.data.Iris;
import Lab4.data.IrisClass;
import Lab4.kMean.KMeanCluster;
import Lab4.kMean.KMeans;

import java.util.ArrayList;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        ArrayList<Iris> list = new ArrayList<>();
        list.add(new Iris(0.0f, 0.0f, 1.0f, 1.0f, IrisClass.Iris_versicolor));
        list.add(new Iris(0.0f, 0.0f, 1.0f, 1.0f, IrisClass.Iris_virginica));
        list.add(new Iris(0.0f, 0.0f, 1.0f, 1.0f, IrisClass.Iris_setosa));
        list.add(new Iris(0.0f, 0.0f, 1.0f, 1.0f, IrisClass.Iris_setosa));

        list.add(new Iris(0.1f, 0.0f, 1.0f, 1.0f, IrisClass.Iris_setosa));
        list.add(new Iris(0.15f, 0.0f, 1.0f, 1.0f, IrisClass.Iris_versicolor));
        list.add(new Iris(0.15f, 0.1f, 1.0f, 1.0f, IrisClass.Iris_setosa));
        list.add(new Iris(0.1f, 0.15f, 1.0f, 1.0f, IrisClass.Iris_setosa));


        list.add(new Iris(0.2f, 0.0f, 1.0f, 1.0f, IrisClass.Iris_setosa));

        List<KMeanCluster> clusters = KMeans.KMeansPartition(3, list);
        clusters.forEach(System.out::println);
    }
}
