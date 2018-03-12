package NeuralNetwork.Simple;

public class Perceptron {

    public static final double[][][] andData = /*{{{0, 0, 0}, {0}},
            {{0, 1, 0}, {0}},
            {{1, 0, 0}, {0}},
            {{1, 1, 1}, {1}}};*/
                    {{{0.64, 0.44, 0.17, 0.01}, {1}},
                    {{0.88, 0.4, 0.59, 0.17}, {0}}};
    public static final double LEARNING_RATE = 0.05;
    public static final double[] INITIAL_WEIGHTS = {Math.random(), Math.random(), Math.random(),Math.random()};

    public static double calculateWeightedSum(double[] data, double[] weights) {
        double weightedSum = 0;
        for (int x = 0; x < data.length; x++)
            weightedSum += data[x] * weights[x];
        return weightedSum;
    }

    public static int applyActivationFunction(double weightedSum) {
        if (weightedSum > 1) return 1;
        return 0;
    }

    public static double[] adjustWeights(double[] data, double[] weights, double error) {
        double[] adjustedWeights = new double[weights.length];
        for (int x = 0; x < weights.length; x++) adjustedWeights[x] = LEARNING_RATE * error * data[x] + weights[x];
        return adjustedWeights;
    }
}
