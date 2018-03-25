package Lab2_ID3;

import Lab2_ID3.enums.Class_Label;
import Assignment_Questionnaire.DecisionTree.DecisionTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import static Lab2_ID3.enums.Class_Label.edible;
import static Lab2_ID3.enums.Class_Label.poisonous;

/**
 * Lab2_ID3.Main1 class to run program from.
 */
@SuppressWarnings("unchecked")
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // First step - Load data and convert to Lab2_ID3.Mushroom objects.
        ArrayList<Mushroom> mushrooms = DataManager.LoadData();                      // data partition D
        System.out.println(mushrooms.get(1).getAttributeValue(Class_Label.class));
        List<Object> attributeList = Mushroom.getAttributeList();
        Algorithm id3 = new Algorithm(mushrooms);
        long edibleOccurrence = id3.occurrence(edible);
        long poisonousOccurrence = id3.occurrence(poisonous);
        long allOccurrences = mushrooms.size();

        System.out.println("Lab2_ID3.DataManager loaded " + mushrooms.size() + " mushrooms");

        Map<Double, Class> attr = id3.attributeSelection(mushrooms,attributeList);
        double infoD = id3.infoD(edibleOccurrence, allOccurrences) + id3.infoD(poisonousOccurrence, allOccurrences);
        System.out.println(infoD);
        DecisionTree mushroomTree = new DecisionTree(id3.generateDecisionTree(mushrooms, attributeList), "Mushrooms");
        mushroomTree.run();

        // table of id3 values
/*for (Map.Entry entry : id3.attributeSelection(mushrooms, attributeList).entrySet()) {
            System.out.printf("Attribute: %-25s    Info: %.4f\n",
                    ((Class)entry.getValue()).getSimpleName(), Double.valueOf(entry.getKey().toString()));
        }*/
        // printing Decision Tree
        //printTree(id3.generateDecisionTree(mushrooms, attributeList), "");


        // check for solitary mushrooms
        /*List<Mushroom> solitaryList = DataManager.listAttributeCategories(mushrooms, Population.class, solitary);
        System.out.println("Size of solitary: " + solitaryList.size());
        solitaryList.sort(Comparator.comparing(mushroom -> mushroom.m_Class));
        for (Mushroom m: solitaryList){
            System.out.printf("Odor: %-15sSpore color: %-15sClass: %s\n", m.m_odor, m.m_spore_color, m.m_Class);
        }*/


    }

    public static <E> void printTree(Node<E> node, String offset) {
        System.out.printf("%s%s\n", offset, node.getLabel());
        if (node.getLeaves() != null)
            for (Node<E> leaf : node.getLeaves()) {
                Object branchName = leaf.getBranch();
                System.out.println("\t\t" + offset + branchName);
                Vector leaves = leaf.getLeaves();
                if (leaves == null || !leaves.isEmpty())
                    printTree(leaf, "\t\t\t\t" + offset);
                else
                    System.out.println("\t\t\t\t" + offset + leaf.getLabel());
            }
    }

}
