package NeuralNetwork.Simple;

import Lab4_KMean.data.Iris;

import java.util.ArrayList;
import java.util.Random;

public class Perceptron {

    public static final double[][][] andData = {{{0, 0}, {0}},
            {{0, 1}, {0}},
            {{1, 0}, {0}},
    };
    /*{{{0.64, 0.44, 0.17, 0.01}, {1}},
    {{0.88, 0.4, 0.59, 0.17}, {0}}};*/
    public static final double LEARNING_RATE = 0.02;
    public static final double[] INITIAL_WEIGHTS = {Math.random(), Math.random(), Math.random(), Math.random()};

    public static double calculateWeightedSum(double[] data, double[] weights) {
        double weightedSum = 0;
        for (int x = 0; x < data.length; x++)
            weightedSum += data[x] * weights[x];
        return weightedSum;
    }

    public static int HeaviSide(double weightedSum) {
        if (weightedSum > 1) return 1;
        return 0;
    }

    public static double[] adjustWeights(double[] data, double[] weights, double error) {
        double[] adjustedWeights = new double[weights.length];
        for (int x = 0; x < weights.length; x++) adjustedWeights[x] = LEARNING_RATE * error * data[x] + weights[x];
        return adjustedWeights;
    }

    public static void shuffleArray(ArrayList<Iris> irises) {

        int n = irises.size();
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(irises, i, change);
        }
    }

    private static void swap(ArrayList<Iris> irises, int i, int change) {

        Iris helper = irises.get(i);
        irises.set(i, irises.get(change));
        irises.set(change, helper);

    }
}
