package Assignment_Questionnaire.Testing;

import Assignment_Questionnaire.enums.ProgrammingLanguage;

import java.util.Arrays;

import static Assignment_Questionnaire.StudentManager.loadEnum;

public class RegexTest {
    public static void main(String[] args) {
       String progLang = "C, C++, C#, Java, Javascript, HTML, CSS, PHP, F#, XML, jQuery";
            String[] langsArray = progLang.replaceAll("\\s+", "").replaceAll("\\(\\)", "").split(",");
            ProgrammingLanguage[] languages = new ProgrammingLanguage[langsArray.length];
            for (int i = 0; i < langsArray.length; i++) {
                String language = langsArray[i].trim().toUpperCase();
                languages[i] = loadEnum(ProgrammingLanguage.class, language);

            }
        System.out.println(Arrays.toString(languages));


    }
    public static void regex(){
        String[] female = "Female female f;lk Fwer F f woman Woman wlkj Wsadf w W ".split(" ");
        for (int i = 0; i < female.length; i++)
            System.out.println(female[i].matches("^(?i:[fw]).*"));
        String[] male = "Male, male, Man, man, m, M, masdf, Masd, Alfa male".split(", ");
        for (int i = 0; i < male.length; i++)
            System.out.println(male[i].matches("^(?i:m)?(?:\\s+male\\s+)?.*"));
    }
}
