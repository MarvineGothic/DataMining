package Lab7_Clustering;

import java.util.Vector;

/**
 * @author alanter
 */
public class SOMTrainer implements Runnable {
    // These constants can be changed to play with the learning algorithm
    private static final double START_LEARNING_RATE = 0.1;
    private static final int NUM_ITERATIONS = 500;
    private static boolean running;
    // These two depend on the weightsSize of the lattice, so are set later
    private double LATTICE_RADIUS = 0;
    private double TIME_CONSTANT = 0;
    private LatticeRenderer renderer;
    private SOMLattice lattice;
    private Vector inputs;
    private Thread runner;

    /**
     * Creates a new instance of SOMTrainer
     */
    public SOMTrainer() {
        running = false;
    }


    // Train the given lattice based on a vector of input vectors
    public void setTraining(SOMLattice latToTrain, Vector in, LatticeRenderer latticeRenderer) {
        lattice = latToTrain;
        inputs = in;
        renderer = latticeRenderer;
    }

    public void start() {
        if (lattice != null) {
            runner = new Thread(this);
            runner.setPriority(Thread.MIN_PRIORITY);
            running = true;
            runner.start();
        }
    }

    /***
     * The run method is used to train/constuct the SOM. http://www.ai-junkie.com/ann/som/som1.html
     */
    public void run() {
        //Initialize constants (e.g. t etc. you will need for calculations
        LATTICE_RADIUS = Math.max(lattice.getHeight(), lattice.getWidth()) / 2;
        TIME_CONSTANT = NUM_ITERATIONS / Math.log(LATTICE_RADIUS);
        for (int iteration = 0; iteration < NUM_ITERATIONS && running; iteration++) {
            for (int i = 0; i < inputs.size(); i++) {
                SOMVector currentInput = (SOMVector) inputs.elementAt(i);
                SOMNode bmu = lattice.getBMU(currentInput);
                // We have the BMU for this input now, so adjust everything in it's neighborhood
                double dNeighbourhoodRadius = expFunction(LATTICE_RADIUS, iteration, TIME_CONSTANT);
                double dLearningRate = expFunction(START_LEARNING_RATE, iteration, NUM_ITERATIONS);

                for (int j = 0; j < lattice.getHeight(); j++) {
                    for (int k = 0; k < lattice.getWidth(); k++) {
                        double distToNodeSq = bmu.distanceTo(lattice.getNode(j, k));
                        double widthSq = Math.pow(dNeighbourhoodRadius, 2);
                        if (distToNodeSq < widthSq) {
                            double dInfluence = expFunction(1, distToNodeSq, (2 * widthSq));
                            lattice.getNode(j, k).adjustWeights(currentInput, dLearningRate, dInfluence);
                        }
                    }
                }
            }
            //Last thing to do, pass the updated lattice to the GUI to visualize it
            renderer.render(lattice, iteration);
        }

        //Used by the GUI
        running = false;
    }

    private double expFunction(double sigma, double iteration, double lambda) {
        return sigma * Math.exp(-iteration / lambda);
    }

    public void stop() {
        if (runner != null) {
            running = false;
            while (runner.isAlive()) {
            }
        }
    }
}
