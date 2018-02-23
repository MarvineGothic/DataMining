package Lab2;

import Lab2.enums.Class_Label;

import java.util.*;

import static Lab2.enums.Class_Label.edible;
import static Lab2.enums.Class_Label.poisonous;

@SuppressWarnings("unchecked")
public class Algorithm {
    private int eatableCount = 0;
    private int poisonousCount = 0;
    private Collection<Mushroom> dataPartition;
    private Collection<Object> editedAttributeList;

    public Algorithm(Collection<Mushroom> dataPartition) {
        this.dataPartition = dataPartition;
        this.editedAttributeList = Mushroom.getAttributeList();
        this.eatableCount = DataManager.countAttributeCategories(dataPartition, Class_Label.class, edible, edible);
        this.poisonousCount = DataManager.countAttributeCategories(dataPartition, Class_Label.class, poisonous, poisonous);
    }

    public double infoD(double caseOccurrence, double allOccurrences) {
        if (caseOccurrence == 0) return 0;
        double div = caseOccurrence / allOccurrences;
        return -(div) * (Math.log(div) / Math.log(2));
    }

    public int occurrence(Enum attributeName) {
        int count = 0;
        for (Mushroom m : dataPartition)
            if (m.getAttributeValue(Class_Label.class).equals(attributeName)) count++;
        return count;
    }

    /**
     * * Takes as a parameter list of attributes of analyzing objects
     * and for each category calculates info(D) and them sum them together
     * within each attribute.
     *
     * @param data_partition data partition D
     * @param attributeList  list of attributes for object
     * @return a map with class name and value of info(D)
     */
    public Class attributeSelection(Collection<Mushroom> data_partition, Collection<Object> attributeList) {
        Map<Double, Class> result = new TreeMap<>();
        for (Object attribute : attributeList) {
            Class currentEnumClass = (Class) attribute;                                // getting ENUM Class
            Object[] categories = currentEnumClass.getEnumConstants();          // getting enumValues from ENUM Class
            double infoCurrAttribute = 0;                                       // main formula to be calculated

            for (Object category : categories) {
                long catEdible = DataManager.countAttributeCategories(data_partition, currentEnumClass, category, edible);
                long catPoisonous = DataManager.countAttributeCategories(data_partition, currentEnumClass, category, poisonous);
                long catCount = catEdible + catPoisonous;
                infoCurrAttribute = infoCurrAttribute + ((double) catCount / data_partition.size()) *
                        (infoD(catEdible, catCount) + infoD(catPoisonous, catCount));
            }
            result.put(infoCurrAttribute, currentEnumClass);
        }
        double minInfoD = Collections.min(result.keySet());
        return result.get(minInfoD);
    }

    public Node<Object> generateDecisionTree(Collection<Mushroom> data_partition, Collection<Object> attributeList) {
        Node<Object> root;
        if (attributeList.isEmpty() && eatableCount < poisonousCount || eatableCount == 0)
            return new Node<>(poisonous, null);
        if (attributeList.isEmpty() && eatableCount > poisonousCount || poisonousCount == 0)
            return new Node<>(edible, null);

        Class splittingAttribute = attributeSelection(data_partition, attributeList);
        Object[] categories = splittingAttribute.getEnumConstants();
        root = new Node<>(splittingAttribute, null);
        /*for (Object category : categories)
            root.addLeaf(new Node<>(category, root));*/
        // TODO: 12.02.2018 if splittingCriterion is discrete-valued and multiway splits allowed
        editedAttributeList.remove(splittingAttribute);
        // for each outcome j of splittingCriterion
        //      partition the tuples and grow subtrees for each partition
        //      let Dj be the set of data tuples in D satisfying outcome j;   // a partition
        // if Dj is empty then
        // attach a leaf labeled with the majority class in D to node N;
        // else root.addLeaf(generateDecisionTree(Dj, attributeList), root);
        // end for
        return root;
    }

}

@SuppressWarnings("unchecked")
class Node<E> {
    private E label;
    private Node<E> root;
    private Collection<Node<E>> leafs = new ArrayList<>();

    Node(E element, Node<E> root) {
        this.label = element;
        this.root = root;

    }

    public void addLeaf(Node<E> leaf) {
        this.leafs.add(leaf);
    }

    public E getLabel() {
        if (label instanceof Class) return (E) ((Class) label).getSimpleName();
        return label;
    }

    public Node<E> getRoot() {
        return root;
    }

    public Collection<Node<E>> getLeaves() {
        return leafs;
    }

    @Override
    public String toString() {
        return "Node{" +
                "label = " + getLabel() +
                ", parent = " + getRoot() +
                '}';
    }
}
