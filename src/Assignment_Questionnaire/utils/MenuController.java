package Assignment_Questionnaire.utils;// C:\Users\Admin\IdeaProjects\DinnerPlanner\src

import Assignment_Questionnaire.interfaces.View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static Assignment_Questionnaire.StudentManager.*;
import static Assignment_Questionnaire.utils.MenuHelper.showDialog;


/**
 * Class having methods for menu tab, such as openFile, openDocument...
 * Created by Sergiy on 11.12.2017.
 */
public class MenuController {
    private static JFileChooser fileChooser;
    private static String path;
    private static File currentFile;

    public static void openFile(View view) {
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        if (fileChooser.showOpenDialog((Component) view) == JFileChooser.APPROVE_OPTION) {
            try {
                path = fileChooser.getSelectedFile().toString();
                loadData(path);
                loadStudents();
            } catch (Exception e) {
                showDialog(null, "File or file path is wrong","File Error");
            }
        }
    }

    /*public static void openDocument(View view) {
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setMultiSelectionEnabled(true);

        if (fileChooser.showOpenDialog((Component) view) == JFileChooser.APPROVE_OPTION) {
            createGroceries(view);
        }
    }*/

    /*public static void createGroceries(View view) {
        File[] files = fileChooser.getSelectedFiles();
        //if (files.length >= 2)
            //GuiItems.createRecipeList(Arrays.asList(files), view);
       // else
            //GuiItems.createRecipeList(fileChooser.getSelectedFile().toString(), view);
    }*/

   /* public static void saveDocument(View view) {
        if (currentFile != null) {
            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                fileWriter.write(view.getTextArea().getText());
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } else saveDocumentAs(view.getTextArea().getText(), view);
    }

    public static void saveDocumentAs(String text, View view) {
        JFileChooser fileChooser = new JFileChooser();
        int n = fileChooser.showSaveDialog((Component) view);
        if (n == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try (FileWriter fileWriter = new FileWriter(currentFile)) {
                fileWriter.write(text);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }*/

    /*public static JFileChooser getFileChooser() {
        return fileChooser;
    }*/
}
