package Assignment_Questionnaire.KMean;

import Assignment_Questionnaire.enums.Degree;

import java.util.Map;
import java.util.TreeMap;

public class ClusterObject {
    private Map<String, Double> attributes;
    private Degree degree;

    private ClusterObject() {
        attributes = new TreeMap<>();
    }

    public static Builder newBuilder() {
        return new ClusterObject().new Builder();
    }

    @Override
    public String toString() {
        String result = String.format("Class: %-15s ", degree);
        for (Map.Entry attr : attributes.entrySet()) {
            result = result.concat(String.format("%s %.2f  ", attr.getKey(), (double) attr.getValue()));
        }
        return result;
    }

    public Degree getDegree() {
        return degree;
    }

    public Map<String, Double> getAttributes() {
        return attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClusterObject object = (ClusterObject) o;

        return attributes != null ? attributes.equals(object.attributes) : object.attributes == null;
    }

    @Override
    public int hashCode() {
        return attributes != null ? attributes.hashCode() : 0;
    }

    public class Builder {
        private Builder() {
        }

        public Builder setAttribute(String name, double value) {
            attributes.put(name, value);
            return this;
        }

        public Builder setDegree(Degree degree) {
            ClusterObject.this.degree = degree;
            return this;
        }

        public ClusterObject build() {
            return ClusterObject.this;
        }
    }
}