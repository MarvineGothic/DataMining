package Lab7_Clustering;

import java.util.Vector;

/**
 * @author alanter
 */
public class SOMVector extends Vector {

    /**
     * Creates a new instance of VariantVector
     */
    public SOMVector() {
    }
    public SOMVector(int size) {
        this.setSize(size);
    }

    public static SOMVector subtract(Vector v1, Vector v2) {
        if (v2.size() != v1.size())
            return null;
        SOMVector v = new SOMVector(v1.size());
        for (int i = 0; i < v1.size(); i++)
            v.setElementAt((Double) v1.elementAt(i) - (Double) v2.elementAt(i), i);
        return v;
    }

    public static SOMVector add(Vector v1, Vector v2) {
        if (v2.size() != v1.size())
            return null;
        SOMVector v = new SOMVector(v1.size());
        for (int i = 0; i < v1.size(); i++)
            v.setElementAt((Double) v1.elementAt(i) + (Double) v2.elementAt(i), i);
        return v;
    }

    public static SOMVector scale(double c, Vector v1) {
        SOMVector v = new SOMVector(v1.size());
        for (int i = 0; i < v1.size(); i++)
            v.setElementAt((Double) v1.elementAt(i) * c, i);
        return v;
    }

    /**
     * Calculates the distance between this vector and
     * v2.  Returns -999 if the vectors so not contain the
     * same number of elements, otherwise returns the
     * square of the distance.
     */
    public double euclideanDist(SOMVector v2) {
        if (v2.size() != size())
            return -999;

        double summation = 0, temp;
        for (int x = 0; x < size(); x++) {
            temp = (Double) elementAt(x) - (Double) v2.elementAt(x);
            temp *= temp;
            summation += temp;
        }

        return summation;
    }
}
