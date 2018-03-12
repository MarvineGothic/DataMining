package Assignment_Questionnaire;

import Assignment_Questionnaire.enums.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static Assignment_Questionnaire.DynamicEnumTest.addEnum;
import static Lab1_Preprocessing.Main.processAttribute;
import static common.CSVFileReader.readDataFile;

@SuppressWarnings("unchecked")
public class StudentManager {
    public static void main(String[] args) {
        ArrayList<Student> students = loadStudents();

    }

    public static ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String[][] data = readDataFile("data/Data Mining - Spring 2018.csv", "\",\"", "-", false);
            double meanAge = processAttribute(data, "\\d{2}", 1);
            double meanShoeSize = processAttribute(data, "\\d{2}(?:\\.?\\d*)?", 3);
            double meanHeight = processAttribute(data, "\\d{3}(?:\\.?\\d*)?", 4);
            for (int i = 1; i < data.length; i++) {
                Student student = new Student();

                student.s_timeStamp = loadEnum(TimeStamp.class, data[i][0]);
                student.s_age = loadAge(data[i][1], meanAge);
                //System.out.println(student.s_age);
                student.s_gender = loadGender(data[i][2], "male");
                //System.out.println(student.s_gender);
                student.s_shoeSize = loadShoeSize(data[i][3], meanShoeSize);
                //System.out.println(student.s_shoeSize);
                student.s_height = loadHeight(data[i][4], meanHeight);
                //System.out.println(student.s_height);
                student.s_degree = loadDegree(data[i][5]);
                //System.out.println(student.s_degree);
                student.s_reasonTakingCourse = loadReason(data[i][6]);
                student.s_programmingLanguages = loadProgLang(data[i][7]);
                System.out.println(Arrays.toString(student.s_programmingLanguages));


                students.add(student);
            }
            System.out.println(Arrays.toString(Age.class.getEnumConstants()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return students;
    }

    public static <T extends Enum<?>> T loadEnum(Class<T> tClass, String name) {
        addEnum(tClass, name, null, null);
        return (T) Enum.valueOf((Class) tClass, name);
    }


    public static Age loadAge(String age, double mean) {
        String ageProcessed = age.replaceAll("\\s+\\D+", "");
        if (!ageProcessed.matches("\\d{2}"))
            ageProcessed = String.valueOf((int) mean);
        try {
            Age.valueOf(ageProcessed);
        } catch (IllegalArgumentException e) {
            addEnum(Age.class, ageProcessed, null, null);
        }
        return Age.valueOf(ageProcessed);
    }

    public static Gender loadGender(String gender, String average) {
        if (gender.matches("^(?i:[fw]).*")) return Gender.female;
        else if (gender.matches("(^(?i:m)|(?:\\s+male\\s+)).*")) return Gender.male;
        else if (average.toLowerCase().equals("male"))
            return Gender.male;
        return Gender.female;
    }

    public static ShoeSize loadShoeSize(String shoeSize, double mean) {
        String shoeSizeProcessed = shoeSize.replaceAll("\\s+\\D+", "").replaceAll(",", ".");
        if (!shoeSizeProcessed.matches("\\d{2}(?:\\.?\\d*)?"))
            shoeSizeProcessed = String.format("%.1f", mean).replace(",", ".");
        addEnum(ShoeSize.class, shoeSizeProcessed, null, null);
        return ShoeSize.valueOf(shoeSizeProcessed);
    }

    public static Height loadHeight(String height, double mean) {
        String heightProcessed = height.replaceAll("\\s+\\D+", "").replaceAll(",", ".");
        if (!heightProcessed.matches("\\d{3}(?:\\.?\\d*)?"))
            heightProcessed = String.format("%.1f", mean).replace(",", ".");
        addEnum(Height.class, heightProcessed, null, null);
        return Height.valueOf(heightProcessed);
    }

    public static Degree loadDegree(String degree) {
        Degree d;
        try {
            d = Degree.valueOf(degree.toUpperCase().replaceAll("[- ]", "_"));
        } catch (IllegalArgumentException e) {
            d = Degree.GUEST_STUDENT;
        }
        return d;
    }

    public static ReasonTakeCourse loadReason(String name) {
        ReasonTakeCourse reasonTakeCourse = ReasonTakeCourse.fromString(name);
        if (reasonTakeCourse == null) {
            int nLength = name.length() > 3 ? 3 : name.length();
            String EName = name.replaceAll(" ", "").toUpperCase();
            EName = EName.substring(0, nLength).concat(EName.substring(EName.length() - nLength, EName.length() - 1));
            addEnum(ReasonTakeCourse.class, EName, new Class[]{String.class}, new Object[]{name});
            reasonTakeCourse = ReasonTakeCourse.valueOf(EName);
        }
        return reasonTakeCourse;
    }

    public static ProgrammingLanguage[] loadProgLang(String progLang) {
        String[] langsArray = progLang.replaceAll("\\s+", "").replaceAll("\\(\\)", "").split(",");
        ProgrammingLanguage[] languages = new ProgrammingLanguage[langsArray.length];
        for (int i = 0; i < langsArray.length; i++) {
            String language = langsArray[i].trim().toUpperCase();
            try {
                ProgrammingLanguage.valueOf(language);
            } catch (IllegalArgumentException e) {
                addEnum(ProgrammingLanguage.class, language, null, null);
            }
            languages[i] = ProgrammingLanguage.valueOf(language);
        }

        return languages;
    }
}
