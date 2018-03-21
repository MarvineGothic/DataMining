package Assignment_Questionnaire.Library;

import Assignment_Questionnaire.Student;
import Assignment_Questionnaire.StudentManager;

import static Assignment_Questionnaire.Library.NU.minMax;

public class Normalize {
    public static double gamesPlayed(String name, Student student) {
        double value = student.getAnyAttribute(name);
        double minValue = 0;
        double maxValue = 0;
        if (name.equals("GamesPlayed")) {
            maxValue = StudentManager.maxGames();
        }
        return minMax(value, minValue, maxValue, 0, 1);
    }
    /*public static double topics(){
        return minMax(,0,,0,1);
    }*/
}
