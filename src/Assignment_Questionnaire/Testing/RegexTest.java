package Assignment_Questionnaire.Testing;

import Assignment_Questionnaire.Student;
import Assignment_Questionnaire.enums.Degree;
import Assignment_Questionnaire.enums.Game;
import Assignment_Questionnaire.enums.ProgrammingLanguage;

import java.util.ArrayList;
import java.util.Arrays;

import static Assignment_Questionnaire.StudentManager.getStudents;
import static Assignment_Questionnaire.StudentManager.loadEnum;

public class RegexTest {
    public static void main(String[] args) {
        String gameOfThr = "FIFA 18 ASD";
        gameOfThr = gameOfThr.replaceAll("\\d+.*","");
        System.out.println(gameOfThr);
    }

    public static void regex() {
        String[] female = "Female female f;lk Fwer F f woman Woman wlkj Wsadf w W ".split(" ");
        for (int i = 0; i < female.length; i++)
            System.out.println(female[i].matches("^(?i:[fw]).*"));
        String[] male = "Male, male, Man, man, m, M, masdf, Masd, Alfa male".split(", ");
        for (int i = 0; i < male.length; i++)
            System.out.println(male[i].matches("^(?i:m)?(?:\\s+male\\s+)?.*"));
    }

    public static void test01() {
        String progLang = "C, C++, C#, Java, Javascript, HTML, CSS, PHP, F#, XML, jQuery";
        String[] langsArray = progLang.replaceAll("\\s+", "").replaceAll("\\(\\)", "").split(",");
        ProgrammingLanguage[] languages = new ProgrammingLanguage[langsArray.length];
        for (int i = 0; i < langsArray.length; i++) {
            String language = langsArray[i].trim().toUpperCase();
            languages[i] = loadEnum(ProgrammingLanguage.class, language);
        }
        System.out.println(Arrays.toString(languages));
    }

    public static void test02() {
        String none = "I have not played any of these games";
        Object[] o = new Object[]{none};
        System.out.println(Arrays.toString(o).matches("\\[I have not played any of these games]|\\[NONE]"));
    }

    public static void test03() {
        // id3
        String path = "/data/Data Mining - Spring 2018.csv";
        ArrayList<Student> students = getStudents();
        Object classValue = students.get(0).getAttributeValue(Game.class);
        Object[] values = Game.values();
        System.out.println(classValue.equals(values[0]));
        System.out.println(students.get(0).getAttributeValue(Degree.class));

        for (int i = 0; i < ((Object[]) classValue).length; i++) {
            if (((Object[]) classValue)[i].equals(values[0])) {
                break;
            }
        }
    }
}
