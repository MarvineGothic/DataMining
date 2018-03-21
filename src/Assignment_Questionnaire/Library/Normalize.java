package Assignment_Questionnaire.Library;

import Assignment_Questionnaire.Student;
import Assignment_Questionnaire.StudentManager;

import static Assignment_Questionnaire.Library.NU.minMax;

public class Normalize {
    public static double attribute(String name, Student student) {
        double value = student.getAnyAttribute(name);
        double minValue = 0;
        double maxValue = 0;
        if (name.equals("GamesPlayed")) {
            maxValue = StudentManager.maxGames();
        }
        if (name.contains("Topic_")) {
            maxValue = 3;
        }
        if (name.equals("Game")){
            maxValue = StudentManager.maxFavGames();
        }
        return minMax(value, minValue, maxValue, 0, 1);
    }
}
