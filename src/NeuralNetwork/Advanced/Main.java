package NeuralNetwork.Advanced;

import NeuralNetwork.Advanced.Entity.*;
import NeuralNetwork.Advanced.Entity.Error;
import NeuralNetwork.Advanced.Utils.DataUtils;
import NeuralNetwork.Advanced.callback.INeuralNetworkCallback;

/**
 * @author jlmd
 */
public class Main {
    public static void main(String [] args){
        System.out.println("Starting NN network sample... ");

        float[][] x = DataUtils.readInputsFromFile(System.getProperty("user.dir") + "/src/NeuralNetwork/Advanced/data/x.txt");
        int[] t = DataUtils.readOutputsFromFile(System.getProperty("user.dir") + "/src/NeuralNetwork/Advanced/data/t.txt");

        NeuralNetwork neuralNetwork = new NeuralNetwork(x, t, new INeuralNetworkCallback() {
            @Override
            public void success(Result result) {
                float[] valueToPredict = new float[] {-0.205f, 0.780f};
                System.out.println("Success percentage: " + result.getSuccessPercentage());
                System.out.println("Predicted result: " + result.predictValue(valueToPredict));
            }

            @Override
            public void failure(Error error) {
                System.out.println("Error: " + error.getDescription());
            }
        });

        neuralNetwork.startLearning();
    }
}