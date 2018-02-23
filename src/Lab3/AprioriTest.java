package Lab3;

import java.util.Arrays;

import static Lab3.ArraysSets.objectArrayToSet;
import static Lab3.ArraysSets.objectSetToArray;

public class AprioriTest<T> {
    static final int[][] TRANSACTIONS = new int[][]{{1, 2, 3, 4, 5}, {1, 3, 5}, {2, 3, 5}, {1, 5}, {1, 3, 4},
            {2, 3, 5}, {2, 3, 5}, {3, 4, 5}, {4, 5}, {2}, {2, 3}, {2, 3, 4}, {3, 4, 5}};
    static final int[][] BOOK_TRANSACTIONS = new int[][]{{1, 2, 5}, {2, 4}, {2, 3}, {1, 2, 4}, {1, 3}, {2, 3}, {1, 3},
            {1, 2, 3, 5}, {1, 2, 3}};

    public static void main(String[] args) {
        // test countSupport
        /*int count = countSupport(new int[]{3, 5}, BOOK_TRANSACTIONS);
        System.out.println("Test countSupport: " + count);
        System.out.println();
        // test joinSets
        ItemSet result = joinSets(new ItemSet(new int[]{1, 2, 3}), new ItemSet(new int[]{2, 3, 4}));
        System.out.println("Result joinSets: " + Arrays.toString(result.set) + "\n");
        // test generateFrequentItemSetsLevel1
        Hashtable<ItemSet, Integer> level1 = generateFrequentItemSetsLevel1(BOOK_TRANSACTIONS, 0);
        System.out.println("test generateFrequentItemSetsLevel1:");
        level1.forEach((s, i) -> System.out.println(Arrays.toString(s.set) + " " + i));
        System.out.println();

        Hashtable<ItemSet, Integer> level2 = generateFrequentItemSets(0, BOOK_TRANSACTIONS, level1);
        level2.forEach((itemSet, integer) -> System.out.println(Arrays.toString(itemSet.set) + " " + integer));
        System.out.println();
        Hashtable<ItemSet, Integer> level3 = generateFrequentItemSets(0, BOOK_TRANSACTIONS, level2);
        level3.forEach((itemSet, integer) -> System.out.println(Arrays.toString(itemSet.set) + " " + integer));*/
        //System.out.println(Arrays.toString(differenceBetweenSets(new int[]{1, 2, 3}, new int[]{3})));
        String[] strings = new String[]{"a", "b", "c"};
        Integer[] integers = new Integer[]{2, 3, 4};
        objectArrayToSet(strings).forEach(System.out::println);
        System.out.println(Arrays.toString(objectSetToArray(objectArrayToSet(integers))));

    }

    /*static <T> T[] intToObjectArray(int[] ints){

        return ;
    }*/



}
