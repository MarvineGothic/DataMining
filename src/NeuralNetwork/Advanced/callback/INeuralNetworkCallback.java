package NeuralNetwork.Advanced.callback;


import NeuralNetwork.Advanced.Entity.Error;
import NeuralNetwork.Advanced.Entity.Result;

/**
 * Callback for NN network
 * @author jlmd
 */
public interface INeuralNetworkCallback {
    /**
     * This method is called when NN network finish his work and all is good
     * @param result Entity to save obtained values
     */
    void success(Result result);

    /**
     * This method is called when NN network finish his work and something is not good
     * @param error Entity to save obtained error
     */
    void failure(Error error);
}