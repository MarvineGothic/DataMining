package Assignment_Questionnaire.utils;

import Assignment_Questionnaire.DecisionTree.DecisionTree;
import Assignment_Questionnaire.ID3.Algorithm;
import Assignment_Questionnaire.ID3.DecisionTreeGenerator;
import Assignment_Questionnaire.ID3.Node;
import Assignment_Questionnaire.Student;
import Assignment_Questionnaire.StudentManager;
import Assignment_Questionnaire.enums.*;
import Assignment_Questionnaire.interfaces.View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import static Assignment_Questionnaire.KMean.ClusterData.createClusterData;
import static Assignment_Questionnaire.KMean.KMeans.KMeansPartition;
import static Assignment_Questionnaire.utils.MenuController.openFile;
import static java.awt.BorderLayout.CENTER;

/**
 * Utility class(static) for making popup windows
 */
class PopUp {
    private static ArrayList<Student> students;
    private static JPanel gui;
    private static JPanel labelFields;
    private static JPanel fields;
    private static JPanel guiCenter;
    private static JPanel buttonConstrain;

    private static String btnString1 = "Enter";
    private static String btnString2 = "Cancel";

    static void clustering() {
        gui = new JPanel(new BorderLayout(2, 2));

        fields = new JPanel(new GridLayout(0, 1, 5, 5));

        guiCenter = new JPanel(new BorderLayout(2, 2));
        guiCenter.setBorder(new TitledBorder("KMean"));

        // check boxes
        String[] attr = new String[Student.getAttributeList().size()];
        for (int i = 0; i < attr.length; i++) {
            attr[i] = ((Class) Student.getAttributeList().get(i)).getSimpleName();
        }
        JPanel check = new JPanel(new GridLayout(0, 1, 5, 5));
        check.setBorder(new TitledBorder(""));
        JLabel clLabel = new JLabel("Chose number of clusters");
        JTextField clustersNum = new JTextField(10);
        JComboBox classes = new JComboBox<>(attr);
        Object[] classesArray = new Object[]{GamesPlayed.class, Topic_DED.class, Topic_CDMA.class,
                Topic_CPM.class, Topic_DGS.class, Topic_SPG.class, Topic_SPI.class, Game.class};
        check.add(clLabel);
        check.add(clustersNum);
        check.add(classes);

        guiCenter.add(check, CENTER);
        guiCenter.add(fields, BorderLayout.EAST);

        gui.add(guiCenter, CENTER);
        int dialog = JOptionPane.showConfirmDialog(null, gui, "KMean", JOptionPane.OK_CANCEL_OPTION);
        if (dialog == JOptionPane.YES_OPTION) {
            try {
                students = StudentManager.getStudents();
            } catch (Exception e) {
                openFile(Listener.getView());
                students = StudentManager.getStudents();
            }
            KMeansPartition(3, createClusterData(students, classesArray), true);
        }
    }

    static void id3algorithm() {
        gui = new JPanel(new BorderLayout(2, 2));

        fields = new JPanel(new GridLayout(0, 1, 5, 5));

        guiCenter = new JPanel(new BorderLayout(2, 2));
        guiCenter.setBorder(new TitledBorder("ID3"));

        // check boxes
        String[] attr = new String[Student.getAttributeList().size()];
        for (int i = 0; i < attr.length; i++) {
            attr[i] = ((Class) Student.getAttributeList().get(i)).getSimpleName();
        }
        JPanel check = new JPanel(new GridLayout(0, 1, 5, 5));
        check.setBorder(new TitledBorder(""));
        JComboBox classes = new JComboBox<>(attr);
        JCheckBox tree = new JCheckBox("Decision Tree");
        check.add(classes);
        check.add(tree);

        guiCenter.add(check, CENTER);
        guiCenter.add(fields, BorderLayout.EAST);

        gui.add(guiCenter, CENTER);
        int dialog = JOptionPane.showConfirmDialog(null, gui, "ID3", JOptionPane.OK_CANCEL_OPTION);
        if (dialog == JOptionPane.YES_OPTION) {
            Object clazz = Student.getAttributeList().get(classes.getSelectedIndex());
            try {
                students = StudentManager.getStudents();
            } catch (Exception e) {
                openFile(Listener.getView());
                students = StudentManager.getStudents();
            }
            new Algorithm(students);
            Algorithm.attributeSelection(Student.getAttributeList(), clazz, true);
            if (tree.isSelected()) {
                Node<Object> node = DecisionTreeGenerator.generateDecisionTree(students, Student.getAttributeList(), clazz);
                new DecisionTree(node, "Students").run();
            }
        }
    }

    /**
     * A popup window from context menu "Add new recipe"
     *
     * @param listener
     * @param view
     */
    static void createRecipe(ActionListener listener, View view) {
        //1. Create GUI
        gui = new JPanel(new BorderLayout(2, 2));

        // panel for fields name, author....
        labelFields = new JPanel(new BorderLayout(2, 2));
        labelFields.setBorder(new TitledBorder("Create new groceries"));
        fields = new JPanel(new GridLayout(0, 1, 1, 1));
        fields.setBorder(new TitledBorder(""));
        JTextField name = (JTextField) addLabelField("Name", fields, 200, 20, true);
        JTextField author = (JTextField) addLabelField("Author", fields, 200, 20, true);
        JTextField description = (JTextField) addLabelField("Description", fields, 200, 20, true);
        JTextField time = (JTextField) addLabelField("Time", fields, 200, 20, true);
        JTextField servings = (JTextField) addLabelField("Servings", fields, 200, 20, true);
        servings.setToolTipText("digits only");
        JTextField calories = (JTextField) addLabelField("Calories", fields, 200, 20, true);
        calories.setToolTipText("digits only");
        labelFields.add(fields, CENTER);

        // another panel for Ingredients and Directions
        JPanel labelFields2 = new JPanel(new BorderLayout(2, 2));
        labelFields2.setBorder(new TitledBorder(""));
        JPanel fields2 = new JPanel(new GridLayout(0, 1, 1, 1));
        fields2.setBorder(new TitledBorder(""));
        // labels and text fields
        JTextPane ingredients = (JTextPane) addLabelField("Ingredients", fields2, 200, 100, false);
        ingredients.setToolTipText("Input format:  amount measure name");
        JTextPane directions = (JTextPane) addLabelField("Directions", fields2, 200, 100, false);
        labelFields2.add(fields2, CENTER);

        gui.add(labelFields, BorderLayout.NORTH);
        gui.add(labelFields2, CENTER);
        // END of GUI creation

        // 2. cancel auto-closing using PropertyChangeListener() https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html#stayup
        Object[] options = {btnString1, btnString2};
        JOptionPane optionPane = new JOptionPane(gui, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION,
                null, options, options[0]);
        JDialog dialog = new JDialog((Frame) null, "Create groceries", true);
        dialog.setContentPane(optionPane);
        dialog.setLocationRelativeTo(null);
        /*dialog.setDefaultCloseOperation(
                JDialog.DO_NOTHING_ON_CLOSE);*/
        optionPane.addPropertyChangeListener(
                new PropertyChangeListener() {
                    public void propertyChange(PropertyChangeEvent e) {
                        String prop = e.getPropertyName();

                        if (dialog.isVisible() && (e.getSource() == optionPane) && (JOptionPane.VALUE_PROPERTY.equals(prop)
                                || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
                            Object value = optionPane.getValue();
                            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                                return;
                            }
                            optionPane.setValue(
                                    JOptionPane.UNINITIALIZED_VALUE);

                            if (btnString1.equals(value)) {     // btn "Enter"
                                if (name.getText().isEmpty() || author.getText().isEmpty() || description.getText().isEmpty()
                                        || time.getText().isEmpty() || calories.getText().isEmpty() ||
                                        ingredients.getText().isEmpty() || directions.getText().isEmpty()) {
                                    MenuHelper.showDialog(null, "some fields are empty or wrong format", "Error");
                                          /* dialog = JOptionPane.showConfirmDialog(null, gui, "Create groceries", JOptionPane.OK_CANCEL_OPTION);*/
                                } else {
                                    String text = String.format("Name: %s\nAuthor: %s\nDescription: %s\nTime: %s" +
                                                    "\nServings: %s\nCalories: %s\n\nIngredients:\n%s\n\nDirections: \n%s\n",
                                            name.getText(), author.getText(), description.getText(), time.getText(),
                                            servings.getText(), calories.getText(), ingredients.getText(), directions.getText());
                                    //MenuController.saveDocumentAs(text, view);
                                    dialog.setVisible(false);
                                    /*if (MenuController.getFileChooser() != null) {
                                        //GuiItems.clearAllWindows(view);
                                       // MenuController.createGroceries(view);
                                    }*/
                                }
                            } else dialog.setVisible(false);   // btn "Cancel"
                        }
                    }
                });

        dialog.pack();
        dialog.setVisible(true);
    }

    /**
     * Method deletes recipe from list or also from a hard disk
     *
     * @param buttonName - name of button and a recipe to delete
     * @param view       - main window GUI
     */
    static void deleteRecipe(String buttonName, View view) {
        //"Do you want to remove this item?"
        gui = new JPanel(new BorderLayout(2, 2));
        JLabel howToDelete = new JLabel("How do you want to remove recipe?");
        JRadioButton hardDisk = new JRadioButton("Permanently delete also from hard disk", false);
        JRadioButton recipeList = new JRadioButton("Only from this list", true);
        ButtonGroup group = new ButtonGroup();
        group.add(hardDisk);
        group.add(recipeList);
        gui.add(howToDelete, BorderLayout.NORTH);
        gui.add(hardDisk, BorderLayout.CENTER);
        gui.add(recipeList, BorderLayout.SOUTH);

        int dialog = JOptionPane.showConfirmDialog(null, gui, "Remove recipe from list", JOptionPane.YES_NO_OPTION);
        if (dialog == JOptionPane.YES_OPTION) {
            if (hardDisk.isSelected()) {
                int confirm = JOptionPane.showConfirmDialog(null, "You will permanently delete chosen files from disk.\nAre you sure?", "Confirmation", JOptionPane.YES_NO_OPTION);
               /* if (confirm == JOptionPane.YES_OPTION)
                    //GuiItems.deleteFromDirectory(buttonName);
                else
                    return;*/
            }
            // GuiItems.removeRecipe(buttonName);
            // GuiItems.clearAllWindows(view);
            // GuiItems.refreshRecipeList(view);
            JOptionPane.showConfirmDialog(null, "Recipe successfully removed", "Confirmation!", JOptionPane.DEFAULT_OPTION);
        }
    }


    /**
     * Helping method, adds a label and a JTextField/JTextPane (depends on choice) to a JPanel(FlowLayout)
     *
     * @param label     a label of a text field
     * @param parent    - JPanel, which gets all content
     * @param width     - width of a text field
     * @param height    - height of a text field
     * @param textField - choice of a JTextField/JTextPane
     * @return component - is a JTextField or a JTextPane depending on choice implementation
     */
    private static Component addLabelField(String label, JPanel parent, int width, int height, boolean textField) {
        Component component;
        JPanel flow = new JPanel(new FlowLayout(0, 2, 2));
        JPanel border = new JPanel(new BorderLayout(2, 2));
        flow.add(border);

        JLabel lb = new JLabel(label);
        lb.setPreferredSize(new Dimension(100, 20));
        border.add(lb, BorderLayout.WEST);
        if (textField) {
            JTextField field = new JTextField();
            field.setPreferredSize(new Dimension(width, height));
            border.add(field, CENTER);
            component = field;
        } else {
            JTextPane textPane = new JTextPane();
            JScrollPane scrollPane = new JScrollPane(textPane);
            scrollPane.setPreferredSize(new Dimension(width, height));
            border.add(scrollPane, CENTER);
            component = textPane;
        }
        parent.add(flow);
        return component;
    }
    /*
    template
    gui = new JPanel(new BorderLayout(2, 2));

        labelFields = new JPanel(new BorderLayout(2, 2));
        labelFields.setBorder(new TitledBorder("BorderLayout"));

        labels = new JPanel(new GridLayout(0, 1, 1, 1));
        labels.setBorder(new TitledBorder("GridLayout"));
        fields = new JPanel(new GridLayout(0, 1, 1, 1));
        fields.setBorder(new TitledBorder("GridLayout"));

        for (int ii = 1; ii < 4; ii++) {
            labels.add(new JLabel("Label " + ii));
            // if these were of different size, it would be necessary to
            // constrain them using another panel
            fields.add(new JTextField(10));
        }

        labelFields.add(labels, BorderLayout.CENTER);
        labelFields.add(fields, BorderLayout.EAST);

        guiCenter = new JPanel(new BorderLayout(2, 2));
        guiCenter.setBorder(new TitledBorder("BorderLayout"));
        buttonConstrain = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonConstrain.setBorder(new TitledBorder("FlowLayout"));
        buttonConstrain.add(new JButton("Click Me"));
        guiCenter.add(buttonConstrain, BorderLayout.NORTH);

        guiCenter.add(new JScrollPane(new JTextArea(5, 30)));

        gui.add(labelFields, BorderLayout.NORTH);
        gui.add(guiCenter, BorderLayout.CENTER);
        popUp("Create groceries");
    * */
}
