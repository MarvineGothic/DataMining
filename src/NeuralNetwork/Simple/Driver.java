package NeuralNetwork.Simple;

import Lab4_KMean.data.DataLoader;
import Lab4_KMean.data.Iris;
import Lab4_KMean.data.IrisClass;

import java.util.ArrayList;

import static NeuralNetwork.Simple.Perceptron.*;
import static Assignment_Questionnaire.Library.NU.round;

public class Driver {
    public static void main(String[] args) {

        ArrayList<Iris> irisData = DataLoader.LoadAllIrisData();
        ArrayList<Iris> toRemove = new ArrayList<>();
        irisData.forEach(iris -> {
            if (iris.Class.equals(IrisClass.Iris_virginica)) toRemove.add(iris);
        });
        irisData.removeAll(toRemove);

        double[][][] data = new double[irisData.size()][][];
        for (int x = 0; x < irisData.size(); x++) {
            Iris i = irisData.get(x);
            double clazz = (i.Class.equals(IrisClass.Iris_setosa)) ? 1 : 0;
            data[x] = new double[][]{new double[]{i.Sepal_Length, i.Sepal_Width, i.Petal_Length, i.Petal_Width}, {clazz}};

        }

        drive(data, Perceptron.INITIAL_WEIGHTS);

    }

    public static void drive(double[][][] data, double[] weights) {
        int epochNumber = 0;
        boolean errorFlag = true;
        double error = 0;
        double[] adjustedWeights = null;
        while (errorFlag) {
            printHeading(epochNumber++);
            errorFlag = false;
            for (int x = 0; x < data.length; x++) {
                double weightedSum = calculateWeightedSum(data[x][0], weights);
                int result = applyActivationFunction(weightedSum);
                error = data[x][1][0] - result;
                if (error != 0) errorFlag = true;
                adjustedWeights = adjustWeights(data[x][0], weights, error);
                printVector(data[x], weights, result, error, weightedSum, adjustedWeights);
                weights = adjustedWeights;
            }
        }
    }

    public static void printHeading(int epochNumber) {
        System.out.println("\n==============================================Epoch # " + epochNumber + "========================================================================================================================");
        System.out.println("    w1  |   w2   |    w3   |    w4   |    x1 |   x2 |   x3  |   x4  | Target Result |   Result  |  error  | Weighted Sum | adjusted w1 | adjusted w2 | adjusted w3 | adjusted w4 |");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void printVector(double[][] data, double[] weights, int result, double error, double weightedSum, double[] adjWeights) {
        System.out.printf("  %.2f  |  %.2f  |  %.2f  |  %.2f  | %s  | %s  | %s  | %s  |        %s      |      %d    |    %s   |     %.2f    |    %.2f    |    %.2f    |    %.2f    |    %.2f    |\n",
                weights[0], weights[1], weights[2], weights[3],
                round(data[0][0], 2), round(data[0][1], 2), round(data[0][2], 2), round(data[0][3], 2),
                data[1][0], result, error, weightedSum,
                adjWeights[0], adjWeights[1], adjWeights[2], adjWeights[3]);
    }
}
