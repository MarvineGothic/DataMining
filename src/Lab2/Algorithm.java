package Lab2;

import Lab2.enums.Class_Label;

import javax.swing.tree.TreeNode;
import java.util.*;

import static Lab2.enums.Class_Label.edible;
import static Lab2.enums.Class_Label.poisonous;
import static javax.swing.tree.DefaultMutableTreeNode.EMPTY_ENUMERATION;

@SuppressWarnings("unchecked")
public class Algorithm {
    //private int edibleCount = 0;
    //private int poisonousCount = 0;
    private Collection<Mushroom> dataPartition;
    private Collection<Object> editedAttributeList;


    public Algorithm(Collection<Mushroom> dataPartition) {
        this.dataPartition = dataPartition;
        this.editedAttributeList = Mushroom.getAttributeList();
        //this.edibleCount = DataManager.countAttributeCategories(dataPartition, Class_Label.class, edible, edible);
        //this.poisonousCount = DataManager.countAttributeCategories(dataPartition, Class_Label.class, poisonous, poisonous);
    }

    /**
     * Entropy of D
     *
     * @param caseOccurrence frequency of a case
     * @param allOccurrences all occurrences
     * @return entropy
     */
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
    public Map<Double, Class> attributeSelection(Collection<Mushroom> data_partition, Collection<Object> attributeList) {
        Map<Double, Class> result = new TreeMap<>();
        for (Object attribute : attributeList) {
            Class currentEnumClass = (Class) attribute;                                // getting ENUM Class
            Object[] categories = currentEnumClass.getEnumConstants();          // getting enumValues from ENUM Class
            double infoCurrAttribute = 0;                                       // main formula to be calculated

            for (Object category : categories) {
                long catEdible = DataManager.countAttributeCategories(data_partition, currentEnumClass, category, edible);
                long catPoisonous = DataManager.countAttributeCategories(data_partition, currentEnumClass, category, poisonous);
                long catCountTotal = catEdible + catPoisonous;
                infoCurrAttribute = infoCurrAttribute + ((double) catCountTotal / data_partition.size()) *
                        (infoD(catEdible, catCountTotal) + infoD(catPoisonous, catCountTotal));
            }
            result.put(infoCurrAttribute, currentEnumClass);
        }
        return result;
    }

    public Class selectBestAttribute(Map<Double, Class> attributes) {
        double minInfoD = Collections.min(attributes.keySet());
        return attributes.get(minInfoD);
    }

    public Enum majority(int edibleCount, int poisonousCount) {
        if (edibleCount > poisonousCount)
            return edible;
        return poisonous;
    }

    public Node<Object> generateDecisionTree(Collection<Mushroom> data_partitionD, Collection<Object> attributeList) {
        Node<Object> node = new Node(null, null, null);
        int edibleCount = DataManager.countAttributeCategories(data_partitionD, Class_Label.class, edible, edible);
        int poisonousCount = DataManager.countAttributeCategories(data_partitionD, Class_Label.class, poisonous, poisonous);

        if (attributeList.isEmpty() && poisonousCount > edibleCount || edibleCount == 0) {
            //node.addLeaf(new Node<>(poisonous, node));
            node = new Node(poisonous, null, null);
            return node;
        }
        if (attributeList.isEmpty() && edibleCount > poisonousCount || poisonousCount == 0) {
            //node.addLeaf(new Node<>(edible, node));
            node = new Node(edible, null, null);
            return node;
        }

        Object bestSplittingCriterion = selectBestAttribute(attributeSelection(data_partitionD, attributeList));  // class of ENUM
        Object[] splittCritOutcomes = ((Class) bestSplittingCriterion).getEnumConstants();                   // ENUM attributes of best splitting criterion
        node.setLabel(bestSplittingCriterion);
        /*for (Object category : categories)
            parent.addLeaf(new Node<>(category, parent));*/
        // TODO: 12.02.2018 if splittingCriterion is discrete-valued and multiway splits allowed
        editedAttributeList.remove(bestSplittingCriterion);

        for (Object outcome_j : splittCritOutcomes) {     // for each outcome j of splittingCriterion   .. almond, anise, creosote...
            Collection<Mushroom> Dj = new ArrayList<>(DataManager.listAttributeCategories(data_partitionD, bestSplittingCriterion, outcome_j));  // for almond 211
            // System.out.println(Dj.size() + " " + outcome_j);
            if (Dj.size() <= 0) {            //let Dj be the set of data tuples in D satisfying outcome j;   // a partition
                /*Node outcome = new Node(outcome_j, node);
                outcome.addLeaf(new Node(null, outcome));*/
                node.addLeaf(new Node(null, outcome_j, node));                                                              // if Dj is empty then
            } else {
                Node leaf = generateDecisionTree(Dj, editedAttributeList);
                leaf.addParent(node);
                leaf.setBranch(outcome_j);
                node.addLeaf(leaf);   // else parent.addLeaf(generateDecisionTree(Dj, editedAttributeList));
            }
        }
        return node;
    }
}

