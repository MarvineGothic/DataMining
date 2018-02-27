package Lab4.Test;


import Lab4.Utils.Cluster;
import Lab4.data.Iris;
import Lab4.data.IrisClass;
import Lab4.kMean.KMeans;

import java.util.ArrayList;
import java.util.List;

public class KMeanTest {
    static java.io.PrintStream orgout;
    static RecordingOutputStream record;
    @org.junit.BeforeClass
    public static void setUpStdOut() {
        orgout = System.out;
        record = new RecordingOutputStream(orgout);
        System.setOut(new java.io.PrintStream(record));
    }
    @org.junit.AfterClass
    public static void tearDownStdOut() {
        System.setOut(orgout);
        record = null;
    }
    @org.junit.Test
    public void test1() {
        ArrayList<Iris> list = new ArrayList<>();
        list.add(new Iris(0.0f, 0.0f, 1.0f, 1.0f, IrisClass.Iris_versicolor));
        list.add(new Iris(0.0f, 0.0f, 1.0f, 1.0f, IrisClass.Iris_virginica));
        list.add(new Iris(0.0f, 0.0f, 1.0f, 1.0f, IrisClass.Iris_setosa));
        list.add(new Iris(0.0f, 0.0f, 1.0f, 1.0f, IrisClass.Iris_setosa));

        list.add(new Iris(0.1f, 0.0f, 1.0f, 1.0f, IrisClass.Iris_setosa));
        list.add(new Iris(0.15f, 0.0f, 1.0f, 1.0f, IrisClass.Iris_versicolor));
        list.add(new Iris(0.15f, 0.1f, 1.0f, 1.0f, IrisClass.Iris_setosa));
        list.add(new Iris(0.1f, 0.15f, 1.0f, 1.0f, IrisClass.Iris_setosa));

        list.add(new Iris(0.2f, 0.0f, 1.0f, 1.0f, IrisClass.Iris_setosa));
        record.start();
        List<Cluster> clusters = KMeans.KMeansPartition(3, list);
        clusters.forEach(System.out::println);
        org.junit.Assert.assertEquals("-----------------------------------CLUSTER START------------------------------------------\n" +
                "Center:\n" +
                "Iris Object --> | Sepal_Length = 0.0 | Sepal_Width = 0.0 | Petal_Length = 1.0 | Petal_Width = 1.0 | Class = Iris_setosa\n" +
                "------------------------------------------------------------------------------------------\n" +
                "Iris Object --> | Sepal_Length = 0.0 | Sepal_Width = 0.0 | Petal_Length = 1.0 | Petal_Width = 1.0 | Class = Iris_versicolor\n" +
                "Iris Object --> | Sepal_Length = 0.0 | Sepal_Width = 0.0 | Petal_Length = 1.0 | Petal_Width = 1.0 | Class = Iris_virginica\n" +
                "Iris Object --> | Sepal_Length = 0.0 | Sepal_Width = 0.0 | Petal_Length = 1.0 | Petal_Width = 1.0 | Class = Iris_setosa\n" +
                "Iris Object --> | Sepal_Length = 0.0 | Sepal_Width = 0.0 | Petal_Length = 1.0 | Petal_Width = 1.0 | Class = Iris_setosa\n" +
                "-----------------------------------CLUSTER END-------------------------------------------\n" +
                "\n" +
                "-----------------------------------CLUSTER START------------------------------------------\n" +
                "Center:\n" +
                "Iris Object --> | Sepal_Length = 0.15 | Sepal_Width = 0.0 | Petal_Length = 1.0 | Petal_Width = 1.0 | Class = Iris_setosa\n" +
                "------------------------------------------------------------------------------------------\n" +
                "Iris Object --> | Sepal_Length = 0.1 | Sepal_Width = 0.0 | Petal_Length = 1.0 | Petal_Width = 1.0 | Class = Iris_setosa\n" +
                "Iris Object --> | Sepal_Length = 0.15 | Sepal_Width = 0.0 | Petal_Length = 1.0 | Petal_Width = 1.0 | Class = Iris_versicolor\n" +
                "Iris Object --> | Sepal_Length = 0.2 | Sepal_Width = 0.0 | Petal_Length = 1.0 | Petal_Width = 1.0 | Class = Iris_setosa\n" +
                "-----------------------------------CLUSTER END-------------------------------------------\n" +
                "\n" +
                "-----------------------------------CLUSTER START------------------------------------------\n" +
                "Center:\n" +
                "Iris Object --> | Sepal_Length = 0.125 | Sepal_Width = 0.125 | Petal_Length = 1.0 | Petal_Width = 1.0 | Class = Iris_setosa\n" +
                "------------------------------------------------------------------------------------------\n" +
                "Iris Object --> | Sepal_Length = 0.15 | Sepal_Width = 0.1 | Petal_Length = 1.0 | Petal_Width = 1.0 | Class = Iris_setosa\n" +
                "Iris Object --> | Sepal_Length = 0.1 | Sepal_Width = 0.15 | Petal_Length = 1.0 | Petal_Width = 1.0 | Class = Iris_setosa\n" +
                "-----------------------------------CLUSTER END-------------------------------------------\n\n", record.stop().replaceAll("\r",""));
    }

    static class RecordingOutputStream extends java.io.OutputStream {
        private final java.io.OutputStream out;
        private byte[] buffer = new byte[1 << 10];
        private int size = -1;

        public RecordingOutputStream(java.io.OutputStream out) {
            this.out = out;
        }

        @Override
        public void write(int b) throws java.io.IOException {
            if (size >= 0) {
                if (size == buffer.length) {
                    buffer = java.util.Arrays.copyOf(buffer, size * 2);
                }
                buffer[size++] = (byte) b;
            } else {
                out.write(b);
            }
        }

        public void start() {
            size = 0;
        }

        public String stop() {
            String retval = new String(buffer, 0, size);
            size = -1;
            return retval;
        }
    }
    public static void main(String[] args) {
        org.junit.runner.Result result = org.junit.runner.JUnitCore.runClasses(KMeanTest.class);
        for (org.junit.runner.notification.Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
            break;
        }
        System.out.printf("Tests run: %d,  Failures: %d\n", result.getRunCount(), result.getFailureCount());
    }
}
