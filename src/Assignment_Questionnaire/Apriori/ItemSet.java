package Assignment_Questionnaire.Apriori;

import java.util.Arrays;

import static Assignment_Questionnaire.Library.ASU.isSubset;

/***
 * The ItemSet class is used to store information concerning a single transaction.
 * Should not need any changes.
 *
 */
public class ItemSet implements Comparable<ItemSet> {

    public final Object[] set;

    /***
     * Creates a new instance of the ItemSet class.
     * @param set Transaction content
     */
    public ItemSet(Object[] set) {
        this.set = set;
    }


    @Override
    public int hashCode() {
        if (set == null)
            return 0;

        int result = 1;

        for (Object element : set) {
            result += 31 * (element == null ? 0 : element.hashCode());
        }

        return result;
    }

    @Override
    /**
     * Used to determine whether two ItemSet objects are equal
     */
    public boolean equals(Object o) {
        if (!(o instanceof ItemSet)) {
            return false;
        }
        ItemSet other = (ItemSet) o;
        if (other.set.length != this.set.length) {
            return false;
        }

        if (isSubset(other.set, this.set)) return true;
        for (int i = 0; i < set.length; i++) {
            if (set[i] != other.set[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(set)
                .replaceAll("]|\\[", "")
                .replaceAll(",","\n");
    }

    @Override
    public int compareTo(ItemSet o) {
        return o.set.length - set.length;
    }
}
