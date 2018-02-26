package Lab4.data;


public class Iris {

    public float Sepal_Length;
    public float Sepal_Width;
    public float Petal_Length;
    public float Petal_Width;
    public IrisClass Class;

    public Iris(float sepal_length, float sepal_width, float petal_length, float petal_width, String iris_class) {
        this(sepal_length, sepal_width, petal_length, petal_width, ResolveIrisClass(iris_class));
    }

    public Iris(float sepal_length, float sepal_width, float petal_length, float petal_width, IrisClass iris_class) {
        this.Sepal_Length = sepal_length;
        this.Sepal_Width = sepal_width;
        this.Petal_Length = petal_length;
        this.Petal_Width = petal_width;
        this.Class = iris_class;
    }

    private static IrisClass ResolveIrisClass(String iris_class) {
        switch (iris_class) {
            case "Iris-setosa":
                return IrisClass.Iris_setosa;
            case "Iris-versicolor":
                return IrisClass.Iris_versicolor;
            case "Iris-virginica":
                return IrisClass.Iris_virginica;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Iris Object --> | Sepal_Length = " + this.Sepal_Length + " | Sepal_Width = " +
                this.Sepal_Width + " | Petal_Length = " + this.Petal_Length + " | Petal_Width = " +
                this.Petal_Width + " | Class = " + this.Class;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Iris iris = (Iris) o;
        return Float.compare(iris.Sepal_Length, Sepal_Length) == 0 && Float.compare(iris.Sepal_Width, Sepal_Width) == 0
                && Float.compare(iris.Petal_Length, Petal_Length) == 0 && Float.compare(iris.Petal_Width, Petal_Width) == 0;
    }

    @Override
    public int hashCode() {
        int result = (Sepal_Length != +0.0f ? Float.floatToIntBits(Sepal_Length) : 0);
        result = 31 * result + (Sepal_Width != +0.0f ? Float.floatToIntBits(Sepal_Width) : 0);
        result = 31 * result + (Petal_Length != +0.0f ? Float.floatToIntBits(Petal_Length) : 0);
        result = 31 * result + (Petal_Width != +0.0f ? Float.floatToIntBits(Petal_Width) : 0);
        //result = 31 * result + (Class != null ? Class.hashCode() : 0);
        return result;
    }
}
