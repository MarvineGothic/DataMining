package Assignment_Questionnaire.ID3;

import Assignment_Questionnaire.Student;
import Assignment_Questionnaire.enums.Degree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static Assignment_Questionnaire.ID3.Algorithm.attributeSelection;
import static Assignment_Questionnaire.ID3.Algorithm.getData_partition;
import static Assignment_Questionnaire.StudentManager.countAttributeCategories;
import static Assignment_Questionnaire.StudentManager.listAttributeCategories;
import static Assignment_Questionnaire.enums.Gender.female;
import static Assignment_Questionnaire.enums.Gender.male;
import static Lab2_ID3.enums.Class_Label.edible;
import static Lab2_ID3.enums.Class_Label.poisonous;

@SuppressWarnings("all")
public class DecisionTreeGenerator {
    private static Collection<Student> data_partition;
    private static Collection<Object> editedAttributeList;

    static {
        data_partition = getData_partition();
        editedAttributeList = Student.getAttributeList();
    }

    public int occurrence(Enum attributeName) {
        int count = 0;
        for (Student m : data_partition)
            if (m.getAttributeValue(Degree.class).equals(attributeName)) count++;
        return count;
    }

    /*public Enum majority(int edibleCount, int poisonousCount) {
        if (edibleCount > poisonousCount)
            return;
        return;
    }*/

    /**
     * @param attributes
     * @return
     */
    public static Class selectBestAttribute(Map<Double, Class> attributes) {
        double minInfoD = Collections.min(attributes.keySet());
        return attributes.get(minInfoD);
    }

    public static Node<Object> generateDecisionTree(Collection<Student> data_partitionD, Collection<Object> attributeList, Object clazz) {
        Node<Object> node = new Node(null, null, null);
        int edibleCount = countAttributeCategories(data_partitionD, clazz, male, clazz, male);
        int poisonousCount = countAttributeCategories(data_partitionD, clazz, female, clazz, female);

        if (attributeList.isEmpty() && poisonousCount > edibleCount || edibleCount == 0) {
            node.addLeaf(new Node<>(female, node, null));
            //node = new Node(poisonous, null, null);
            return node;
        }
        if (attributeList.isEmpty() && edibleCount > poisonousCount || poisonousCount == 0) {
            node.addLeaf(new Node<>(male, node, null));
            //node = new Node(edible, null, null);
            return node;
        }

        Object bestSplittingCriterion = selectBestAttribute(attributeSelection(attributeList, clazz, false));  // class of ENUM
        Object[] splittCritOutcomes = ((Class) bestSplittingCriterion).getEnumConstants();                   // ENUM attributes of best splitting criterion
        node.setLabel(bestSplittingCriterion);

        editedAttributeList.remove(bestSplittingCriterion);

        for (Object outcome_j : splittCritOutcomes) {     // for each outcome j of splittingCriterion   .. almond, anise, creosote...
            Collection<Student> Dj = new ArrayList<>(listAttributeCategories(data_partitionD, bestSplittingCriterion, outcome_j));  // for almond 211
            // System.out.println(Dj.size() + " " + outcome_j);
            if (Dj.size() <= 0) {            //let Dj be the set of data tuples in D satisfying outcome j;   // a partition
                Node endNode = new Node(node + ":" + outcome_j, outcome_j, node);
                endNode.addLeaf(new Node("no info", null, null));
                node.addLeaf(endNode);                                                              // if Dj is empty then
            } else {
                Node leaf = generateDecisionTree(Dj, editedAttributeList, clazz);
                leaf.addParent(node);
                leaf.setLabel(node + ":" + outcome_j);
                node.addLeaf(leaf);   // else parent.addLeaf(generateDecisionTree(Dj, editedAttributeList));
            }
        }
        return node;
    }
}
