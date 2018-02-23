package Lab2;

import Lab2.enums.Class_Label;

import java.util.ArrayList;
import java.util.List;

import static Lab2.enums.Class_Label.poisonous;

/**
 * Lab2.Main class to run program from.
 */
@SuppressWarnings("unchecked")
public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // First step - Load data and convert to Lab2.Mushroom objects.
        ArrayList<Mushroom> mushrooms = DataManager.LoadData();                      // data partition D
        List<Object> attributeList = Mushroom.getAttributeList();
        System.out.println("Lab2.DataManager loaded " + mushrooms.size() + " mushrooms");
        Algorithm id3 = new Algorithm(mushrooms);
        long edibleOccurrence = id3.occurrence(Class_Label.edible);
        long poisonousOccurrence = id3.occurrence(poisonous);
        long allOccurrences = mushrooms.size();

        double infoD = id3.infoD(edibleOccurrence, allOccurrences) + id3.infoD(poisonousOccurrence, allOccurrences);
        System.out.println(infoD);

        Node<Object> splittingAttribute = id3.generateDecisionTree(mushrooms, attributeList);
        System.out.printf("Label: %s\n", splittingAttribute.getLabel());
        for (Node<Object> node : splittingAttribute.getLeaves()) System.out.println(node);
        /*for (Map.Entry entry : attributesInfo.entrySet()) {
            System.out.printf("Attribute: %-25s    Info: %.4f\n", entry.getValue(), Double.valueOf(entry.getKey().toString()));
        }*/

    }

}
