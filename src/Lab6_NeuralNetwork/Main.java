package Lab6_NeuralNetwork;

import Lab4_KMean.data.DataLoader;
import Lab4_KMean.data.Iris;

import java.util.ArrayList;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //First step load in iris data
        ArrayList<Iris> irisData = DataLoader.LoadAllIrisData();
        System.out.println("Successfully loaded " + irisData.size() + " iris flowers");
        irisData.forEach(System.out::println);

        /*NeuralNetwork neuralNetwork = new NeuralNetwork(, , new INeuralNetworkCallback() {
            @Override
            public void success(Result result) {

            }

            @Override
            public void failure(Error error) {

            }
        })*/
       // System.out.println(round(2.333333, 2));
        //Second step make perceptron or neural network
    }

}
