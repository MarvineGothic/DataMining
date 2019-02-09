package Lab7_Clustering;

/**
 * @author alanter
 */
public class SOMNode {
    private SOMVector weights;
    private int xp, yp;

    /**
     * Creates a new instance of SOMNode
     */
    public SOMNode(int numWeights) {
        weights = new SOMVector();
        for (int x = 0; x < numWeights; x++)
            weights.addElement(Math.random());
    }

    public int getX() {
        return xp;
    }

    public void setX(int xpos) {
        xp = xpos;
    }

    public int getY() {
        return yp;
    }

    public void setY(int ypos) {
        yp = ypos;
    }

    /**
     * Computes the distance to another node.  Used for
     * neighborhood determination.  Returns the SQUARE of the distance
     */
    public double distanceTo(SOMNode n2) {
        return Math.pow(getX() - n2.getX(), 2) + Math.pow(getY() - n2.getY(), 2);
    }

    public double euclidDist(SOMVector vector) {
        int distance = 0;
        for (int i = 0; i < weights.size(); i++)
            distance += Math.pow((Double) vector.elementAt(i) - (Double) weights.elementAt(i), 2);
        return Math.sqrt(distance);
    }

    public void setWeight(int w, double value) {
        if (w >= weights.size())
            return;
        weights.setElementAt(value, w);
    }

    public double getWeight(int w) {
        if (w >= weights.size())
            return 0;

        return (Double) weights.elementAt(w);
    }

    public SOMVector getVector() {
        return weights;
    }

    public void adjustWeights(SOMVector input, double learningRate, double distanceInfluence) {
        SOMVector vector = SOMVector.add(weights, SOMVector.scale(distanceInfluence * learningRate, (SOMVector.subtract(input, weights))));
        for (int i = 0; i < weights.size(); i++) {
            setWeight(i, (double) vector.elementAt(i));
        }
    }
}