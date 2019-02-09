package Lab6_NeuralNetwork;

import Lab4_KMean.data.DataLoader;
import Lab4_KMean.data.Iris;
import Lab4_KMean.data.IrisClass;
import NeuralNetwork.Advanced.Entity.Error;
import NeuralNetwork.Advanced.Entity.Result;
import NeuralNetwork.Advanced.NeuralNetwork;
import NeuralNetwork.Advanced.callback.INeuralNetworkCallback;

import java.util.ArrayList;

import static NeuralNetwork.Simple.Perceptron.shuffleArray;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //First step load in iris data
        ArrayList<Iris> irisData = DataLoader.LoadAllIrisData();
        //System.out.println("Successfully loaded " + irisData.weightsSize() + " iris flowers");
        //irisData.forEach(System.out::println);

        shuffleArray(irisData);

        double[][][] data = new double[irisData.size()][][];
        float[][] trainX = new float[(int) (irisData.size() * 0.7)][];
        float[][] testX = new float[(int) (irisData.size() * 0.3)][];

        int[] trainT = new int[(int) (irisData.size() * 0.7)];
        int[] testT = new int[(int) (irisData.size() * 0.3)];

        for (int x = 0; x < irisData.size(); x++) {
            Iris i = irisData.get(x);
            int clazz = (i.Class.equals(IrisClass.Iris_setosa)) ? 2 : (i.Class.equals(IrisClass.Iris_virginica) ? 1 : 0);
            data[x] = new double[][]{new double[]{i.Sepal_Length, i.Sepal_Width, i.Petal_Length, i.Petal_Width}, {clazz}};
            if (x < trainX.length) {
                trainX[x] = new float[]{i.Sepal_Length, i.Sepal_Width, i.Petal_Length, i.Petal_Width};
                trainT[x] = clazz;
            } else {
                testX[x - trainX.length] = new float[]{i.Sepal_Length, i.Sepal_Width, i.Petal_Length, i.Petal_Width};
                testT[x - trainX.length] = clazz;
            }
        }

        NeuralNetwork neuralNetwork = new NeuralNetwork(trainX, trainT, new INeuralNetworkCallback() {
            @Override
            public void success(Result result) {

                System.out.println("Success percentage: " + result.getSuccessPercentage());
                // TODO: 21.06.2018 make multiclass prediction
                for (int i = 0; i < testX.length; i++)
                    System.out.println("Actual: " + testT[i] + " Predicted result: " + result.predictValue(testX[i]));
            }

            @Override
            public void failure(Error error) {
                System.out.println("Error: " + error.getDescription());
            }
        });

        neuralNetwork.startLearning();
        // System.out.println(round(2.333333, 2));
        //Second step make perceptron or NN network
    }
}
