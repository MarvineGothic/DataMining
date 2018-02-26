package Lab4.kMean;

import Lab4.data.Iris;
import Lab4.data.IrisClass;

import java.util.ArrayList;

import static Lab4.data.IrisClass.*;


//ToDo: Compute cluster mean based on cluster members.
public class KMeanCluster {

    public Iris clusterCenter;
    public ArrayList<Iris> ClusterMembers;

    public KMeanCluster() {
        this.ClusterMembers = new ArrayList<>();
    }

    public Iris clusterMean() {
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


    @Override
    public String toString() {
        String toPrintString = "-----------------------------------CLUSTER START------------------------------------------" + System.getProperty("line.separator");
        // String center = clusterCenter.toString();


        for (Iris i : this.ClusterMembers) {
            toPrintString += i.toString() + System.getProperty("line.separator");
        }
        toPrintString += "-----------------------------------CLUSTER END-------------------------------------------" + System.getProperty("line.separator");

        return toPrintString;
    }

}
