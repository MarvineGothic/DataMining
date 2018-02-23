package Lab3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
public class ArraysSets {
    public static int[] intSetToArray(Set<Integer> set) {
        int[] array = new int[set.size()];
        int i = 0;
        for (Integer aSet : set) {
            array[i++] = aSet;
        }
        return array;
    }

    public static long[] longSetToArray(Set<Long> set) {
        long[] array = new long[set.size()];
        int i = 0;
        for (Long aSet : set) {
            array[i++] = aSet;
        }
        return array;
    }

    public static <T> Set<T> objectArrayToSet(T[] array) {
        return Arrays.stream(array).collect(Collectors.toSet());
    }

    public static <T> T[] objectSetToArray(Set<T> objectSet) {
        T[] result = (T[]) new Object[objectSet.size()];
        int i = 0;
        for (T t : objectSet) {
            result[i++] = t;
        }
        return result;
    }

    public static int[] differenceBetweenSets(int arr1[], int arr2[]) {
        Set<Integer> set = new HashSet<>();
        if (isSubset(arr1, arr2)) {
            for (int anArr1 : arr1) {
                set.add(anArr1);
            }
            for (int anArr2 : arr2) {
                set.remove(anArr2);
            }
        }
        return ArraysSets.intSetToArray(set);
    }

    public static boolean isSubset(int arr1[], int arr2[]) {
        int i;
        int j;
        for (i = 0; i < arr2.length; i++) {
            for (j = 0; j < arr1.length; j++) {
                if (arr2[i] == arr1[j])
                    break;
            }
            if (j == arr1.length)
                return false;
        }
        return true;
    }

    public static Set<ItemSet> intArrayToItemSet(int[] arr1) {
        int[] merged = new int[arr1.length];
        System.arraycopy(arr1, 0, merged, 0, arr1.length);
        Set<ItemSet> set = new HashSet<>();
        for (int aMerged : merged)
            set.add(new ItemSet(new int[]{aMerged}));
        return set;
    }

    public static Set<Integer> intArraysToSet(int[] arr1, int[] arr2) {
        int[] merged = new int[arr1.length + arr2.length];
        System.arraycopy(arr1, 0, merged, 0, arr1.length);
        System.arraycopy(arr2, 0, merged, arr1.length, arr2.length);
        Set<Integer> set = new HashSet<>();
        for (int aMerged : merged)
            set.add(aMerged);
        return set;
    }

    public static int[] mergeTwoArrays(int[] arr1, int[] arr2) {
        return ArraysSets.intSetToArray(intArraysToSet(arr1, arr2));
    }
}
