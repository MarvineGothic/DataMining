package Assignment_Questionnaire.Library;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Cleaning Utility Class
 */
public class CU {
    public static void main(String[] args) {
        int[] list = {1, -2, 4, -4, 9, -6, 16, -8, 25, -10};
        System.out.println(stdev(list));
        List<Double> list2 = new ArrayList<>();
        for (int i = 0; i < list.length; i++) list2.add((double) list[i]);
        System.out.println(stdevList(list2));

        /*System.out.println(minMax(73.6, 12, 98, 0.0, 1.0));
            System.out.println(zScoreD(73.6, 54, 16));
            System.out.println(decimalScaling(-986, -986, 917));*/
    }


    public static int meanI(Collection<Integer> data) {
        return (data.stream().mapToInt(Integer::intValue).sum()) / data.size();
    }

    public static double meanD(Collection<Double> data) {
        return (data.stream().mapToDouble(Double::doubleValue).sum()) / data.size();
    }

    public static double median(List<Double> data) {
        Collections.sort(data);
        return (data.size() % 2 == 1) ? data.get(data.size() / 2) : (data.get(data.size() / 2) + data.get(data.size() / 2 - 1)) / 2;
    }

    public static List<Double> modes(final List<Double> numbers) {
        final Map<Double, Long> countFrequencies = numbers.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        final long maxFrequency = countFrequencies.values().stream()
                .mapToLong(count -> count)
                .max().orElse(-1);

        return countFrequencies.entrySet().stream()
                .filter(tuple -> tuple.getValue() == maxFrequency)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static double meanAbsDevD(Collection<Double> data) {
        final double[] absSum = {0.0};
        data.forEach(v -> absSum[0] += Math.abs(v - meanD(data)));
        return absSum[0] / data.size();
    }

    /**
     * Standard deviation for integer array
     *
     * @param list
     * @return
     */
    public static double stdev(int[] list) {
        double sum = 0.0;
        double mean;
        double num = 0.0;
        double numi;

        for (int i : list)
            sum += i;
        mean = sum / list.length;
        for (int i : list) {
            numi = Math.pow((double) i - mean, 2);
            num += numi;
        }
        return Math.sqrt(num / list.length);
    }

    /**
     * Standard deviation for Collection of Double
     *
     * @param list
     * @return
     */
    public static double stdevList(Collection<Double> list) {
        final double[] num = {0.0};
        list.forEach(n -> num[0] += Math.pow(n - meanD(list), 2));
        return Math.sqrt(num[0] / list.size());
    }

    public static void removeTwoPercent(Collection<Double> doubles) {
        int twoProcent = doubles.size() * 2 / 100;
        for (int i = 0; i < twoProcent; i++) {
            doubles.remove(Collections.min(doubles));
            doubles.remove(Collections.max(doubles));
        }
    }

    /**
     * Can sort out digits from two dimension array
     * @param data
     * @param regex
     * @return
     */
    public static List<Double> sortDigitsOut(Object[][] data,int column, String regex) {
        List<Double> listProcessed = new ArrayList<>();
        for (int i = 1; i < data.length; i++) {
            String line = ((String) data[i][column]).replaceAll("\\s+\\D+", "")
                    .replaceAll(",",".");
            if (line.matches(regex))
                listProcessed.add(Double.valueOf(line));
        }
        return listProcessed;
    }


}
