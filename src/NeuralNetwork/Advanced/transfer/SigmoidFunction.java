package NeuralNetwork.Advanced.transfer;

/**
 * Sigmoid implementation of transfer function that limit the value between 0 and 1
 *
 * @author jlmd
 */
public class SigmoidFunction implements ITransferFunction {
    public static double sigmoid(double x) {
        return 1 / (1 + Math.pow(Math.E, (-x)));
    }

    @Override
    public float transfer(float value) {
        return (float) (1 / (1 + Math.exp(-value)));
    }
}