package Lab3;

import java.util.*;

@SuppressWarnings("unchecked")
public class Apriori {
    /***
     * The TRANSACTIONS 2-dimensional array holds the full data set for the lab
     */
    static final int[][] TRANSACTIONS = new int[][]{{1, 2, 3, 4, 5}, {1, 3, 5}, {2, 3, 5}, {1, 5}, {1, 3, 4},
            {2, 3, 5}, {2, 3, 5}, {3, 4, 5}, {4, 5}, {2}, {2, 3}, {2, 3, 4}, {3, 4, 5}};

    static final int[][] BOOK_TRANSACTIONS = new int[][]{{1, 2, 5}, {2, 4}, {2, 3}, {1, 2, 4}, {1, 3}, {2, 3}, {1, 3},
            {1, 2, 3, 5}, {1, 2, 3}};

    public static void main(String[] args) {
        // TODO: Select a reasonable support threshold via trial-and-error. Can either be percentage or absolute value.
        final int supportThreshold = 2;
        List<ItemSet> aprioriResult = apriori(TRANSACTIONS, supportThreshold);
    }

    /**
     *
     * @param transactions
     * @param supportThreshold
     * @return
     */
    public static List<ItemSet> apriori(int[][] transactions, int supportThreshold) {
        Hashtable<ItemSet, Integer> allFrequentItemSets = new Hashtable<>();
        List<ItemSet> result = new ArrayList<>();
        int k;
        Hashtable<ItemSet, Integer> frequentItemSets = generateFrequentItemSetsLevel1(transactions, supportThreshold);
        for (k = 1; frequentItemSets.size() > 0; k++) {
            System.out.print("Finding frequent itemsets of length " + (k + 1) + "…");
            frequentItemSets = generateFrequentItemSets(supportThreshold, transactions, frequentItemSets);
            allFrequentItemSets.putAll(frequentItemSets);
            result.addAll(frequentItemSets.keySet());
            System.out.println(" found " + frequentItemSets.size());
            frequentItemSets.forEach((key, value) -> System.out.println(Arrays.toString(key.set) + " " + value));
        }
        // TODO: create association rules from the frequent itemsets
        confidence(result.get(result.size() - 1), allFrequentItemSets, transactions);
        return result;
    }

    /**
     *
     * @param resultItemSet
     * @param allFrequentItemSets
     * @param transactions
     * @return
     */
    public static double confidence(ItemSet resultItemSet, Hashtable<ItemSet, Integer> allFrequentItemSets, int[][] transactions) {
        double supportA;
        double confidence = 0;

        Set<ItemSet> subsetsOfItemSet = new HashSet<>(ArraysSets.intArrayToItemSet(resultItemSet.set));

        for (ItemSet i : allFrequentItemSets.keySet())
            if (ArraysSets.isSubset(resultItemSet.set, i.set) && i.set.length < resultItemSet.set.length)
                subsetsOfItemSet.add(i);

        List<ItemSet> listOfSubsets = new ArrayList<>(subsetsOfItemSet);
        Collections.sort(listOfSubsets);
        System.out.println("\nConfidence for " + Arrays.toString(resultItemSet.set));
        for (ItemSet i : listOfSubsets) {
            supportA = countSupport(i.set, transactions);
            confidence = countSupport(resultItemSet.set, transactions) / supportA;
            System.out.printf("%s => %s confidence = %.2f\n", Arrays.toString(i.set),
                    Arrays.toString(ArraysSets.differenceBetweenSets(resultItemSet.set, i.set)), confidence);
        }
        return confidence;
    }

    /**
     * Generates unique ItemSets for next levels based on previous ItemSets
     * and then sorts out those which doesn't satisfy supportThreshold condition
     *
     * @param supportThreshold   a limit below which support value is not relevant
     * @param transactions       a set of all transactions
     * @param lowerLevelItemSets the set of previous ItemSets
     * @return new map of ItemSets and support values
     */
    public static Hashtable<ItemSet, Integer> generateFrequentItemSets(int supportThreshold,
                                                                       int[][] transactions, Hashtable<ItemSet, Integer> lowerLevelItemSets) {
        Hashtable<ItemSet, Integer> result = new Hashtable<>();

        List<ItemSet> lowerLevelItemList = new ArrayList<>((lowerLevelItemSets.keySet()));
        int itemSetLength = lowerLevelItemList.get(0).set.length + 1;
        int countSupport;

        for (int i = 0; i < lowerLevelItemList.size(); i++)
            for (int j = i + 1; j < lowerLevelItemList.size(); j++) {
                ItemSet aSet = joinSets(lowerLevelItemList.get(i), lowerLevelItemList.get(j));
                if (aSet.set.length == itemSetLength && (countSupport = countSupport(aSet.set, transactions)) >= supportThreshold)
                    result.put(aSet, countSupport);

            }
        return result;
    }

    /**
     * Simple join of two ItemSet without duplicates
     *
     * @param first  ItemSet
     * @param second ItemSet
     * @return set of two ItemSet
     */
    public static ItemSet joinSets(ItemSet first, ItemSet second) {
        return new ItemSet(ArraysSets.mergeTwoArrays(first.set, second.set));
    }

    /**
     * Generator of ItemSets for level 1
     * i.e. set of all single elements from transactions that fulfill requirement of supportThreshold
     *
     * @param transactions     a set of sets in which we look up for itemSet elements
     * @param supportThreshold a limit below which support value is not relevant
     * @return set of frequent elements for level 1
     */
    public static Hashtable<ItemSet, Integer> generateFrequentItemSetsLevel1(int[][] transactions, int supportThreshold) {
        Hashtable<ItemSet, Integer> result = new Hashtable<>();
        int count;

        Set<ItemSet> setOfTransactElements = new HashSet<>();
        for (int[] transaction : transactions) setOfTransactElements.addAll(ArraysSets.intArrayToItemSet(transaction));

        for (ItemSet element : setOfTransactElements)
            if ((count = countSupport(element.set, transactions)) >= supportThreshold)
                result.put(element, count);
        return result;
    }

    /**
     * Counts support (frequency) of itemSet in transactions
     *
     * @param itemSet      a set which frequency needs to be count
     * @param transactions a set of sets in which we look up for itemSet occurrence
     * @return frequency of itemSet in transactions
     */
    public static int countSupport(int[] itemSet, int[][] transactions) {
        // Assumes that items in ItemSets and transactions are both unique
        int count = 0;
        for (int[] sets : transactions)
            if (ArraysSets.isSubset(sets, itemSet))
                count++;
        return count;
    }
}
