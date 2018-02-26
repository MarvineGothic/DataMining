package Lab4.Utils;

import Lab4.data.Iris;
import Lab4.data.IrisClass;

import java.util.List;

import static Lab4.data.IrisClass.*;

public class KMU {
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
}
