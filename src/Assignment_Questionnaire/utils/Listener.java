package Assignment_Questionnaire.utils;

import Assignment_Questionnaire.interfaces.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static Assignment_Questionnaire.StudentManager.printStudentData;
import static Assignment_Questionnaire.utils.MenuController.openFile;
import static Assignment_Questionnaire.utils.PopUp.*;

public class Listener implements ActionListener, MouseListener {
    private static View view;
    private String buttonName = "";

    public Listener(View view) {
        Listener.view = view;
    }

    // ActionListener
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        switch (action) {
            // menu tab
            case "Open File":
                openFile(view);
                break;
            case "Apriori":
                System.out.println("Apriori");
                break;
            case "KMean":
                clustering();
                break;
            case "ID3":
                id3algorithm();
                break;
            case "Decision Tree":
                System.out.println("Decision Tree");
                break;
            case "Exit":
                System.exit(0);
                break;
            case "About":
                MenuHelper.showDialog(null, "Data Mining Tool\nby Sergiy Isakov", "About");
                break;
            // buttons on window
            case "Print data":
                try {
                    printStudentData();
                } catch (Exception e) {
                    openFile(view);
                    printStudentData();
                }
                break;
            case "Clear List":
                // GuiItems.clearAllWindows(view);
                // GuiItems.clearAllRecipes();
                break;
            // recipe context menu
            /*case "Options":
                id3algorithm();
                break;*/
            case "Add new recipe":
                PopUp.createRecipe(this, view);
                break;
            case "Remove recipe from list":
                if (!buttonName.isEmpty())
                    PopUp.deleteRecipe(buttonName, view);
                break;
        }

    }

    // MouseListener
    @Override
    public void mouseClicked(MouseEvent e) {
        int mod = e.getModifiers();
        String eName = e.getSource().getClass().getSimpleName();
        switch (mod) {
            case 16:

                break;
            case 4:
                switch (eName) {
                    case "JPanel":
                        view.getPanelPopUp().show(e.getComponent(), e.getX(), e.getY());
                        break;
                    case "JButton":
                        view.getRecipePopUp().show(e.getComponent(), e.getX(), e.getY());
                        buttonName = ((JButton) e.getSource()).getText();
                        break;
                }
                break;

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static View getView() {
        return view;
    }
}
