package Assignment_Questionnaire;

import Assignment_Questionnaire.enums.*;
import Assignment_Questionnaire.enums.Number;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static Assignment_Questionnaire.Library.CSVFileReader.readDataFile;
import static Assignment_Questionnaire.Library.CU.*;
import static Assignment_Questionnaire.Library.DynamicEnum.addEnum;

/**
 * This class creates list of Students, based on data from questionaire.
 * All data is loaded using method 'readDataFile' from CSVFileReader.
 * For numeric values Age, ShoeSize, Height is calculates mean to use in filling missing values in data.
 * For the sake of consistency, all attributes are Enum classes and values are Enum values. A lot of values are
 * created dynamically, using method 'addEnum' from class 'DynamicEnum'.
 * To avoid code duplication some methods using generic types, and method 'loadTopic' using reflection.
 */
@SuppressWarnings("all")
public class StudentManager {
    private static String[][] data;
    private static ArrayList<Student> students;
    private static int maxGamesNumber;
    private static int maxFavGames;

    // test method
    public static void main(String[] args) {
        printStudentData();
    }

    public static void printStudentData() {
        System.out.printf("Student database:\n");
        if (data == null) loadData();
        String format = "%-32s %-5s %-8s %-10s %-7s %-15s %-40.35s %-88s %-10s %-30s %-30s %-30s %-30s %-30s %-30s " +
                "%-30s %-30s %-30s %-30s %-30s %-250s %-35s %-220s %-35s %-15s %-15s %-60s %-60s %-60s %-15s %-10s\n";
        String header = String.format(format, "Timestamp", "Age", "Gender", "ShoeSize", "Height", "Degree", "Why are you taking this course?",
                "Which programming languages do you know?", "PhoneOS", "Design efficient databases", "Create predictive models",
                "Define groups of similar obj", "Visualise data", "Study patterns on sets", "Study patterns on sequences",
                "Study patterns on graphs", "Study patterns on text", "Study patterns on images", "Code data mining algorithms",
                "Use off-the-shelf DM tools", "Which of these games have you played?", "How do you commute to ITU?", "In which order do you normally traverse these ITU locations?",
                "Four numbers", "therb", "a number", "Favorite Film", "Favorite TV-Show", "Favorite Game", "Row", "Seat");
        System.out.printf("%s", header);
        for (int i = 0; i < header.length(); i++) System.out.print("-");
        System.out.println();
        for (Student s : loadStudents()) {
            System.out.printf(format,
                    s.s_timeStamp, s.s_age, s.s_gender, s.s_shoeSize, s.s_height, s.s_degree, s.s_reasonTakingCourse,
                    Arrays.toString(s.s_programmingLanguages), s.s_phoneOS,
                    s.s_topicDED.getName(), s.s_topicCPM.getName(), s.s_topicDGS.getName(), s.s_topicVD.getName(), s.s_topicSPS.getName(),
                    s.s_topicSPSQ.getName(), s.s_topicSPG.getName(), s.s_topicSPT.getName(), s.s_topicSPI.getName(), s.s_topicCDMA.getName(),
                    s.s_topicUDMT.getName(), Arrays.toString(s.s_gamesPlayed), Arrays.toString(s.s_commuteToITU),
                    Arrays.toString(s.s_traverseITU), Arrays.toString(s.s_fourNumbers), s.s_therb,
                    s.s_number, Arrays.toString(s.s_favFilm), Arrays.toString(s.s_favTVshow), Arrays.toString(s.s_favGame), s.s_row, s.s_seat);
        }
        for (int i = 0; i < header.length(); i++) System.out.print("=");
        System.out.println("\n\n\n");
    }

    public static void loadData() {
        try {
            data = readDataFile("data/Data Mining - Spring 2018.csv", "\",\"", "-", false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double processAttribute(String[][] data, String regex, int column) {
        HashMap<String, List<String>> attribute = new HashMap<>();
        List<Double> attributeProcessed = new ArrayList<>();
        String attributeTitle = data[0][column];
        attribute.put(attributeTitle, new ArrayList<>());
        for (int i = 1; i < data.length; i++)
            attribute.get(attributeTitle).add(data[i][1]);
        attributeProcessed.addAll(sortDigitsOut(data, column, regex));
        removeTwoPercent(attributeProcessed);
        return meanD(attributeProcessed);
    }

    public static ArrayList<Student> loadStudents() {
        if (data == null) loadData();
        students = new ArrayList<>();
        double meanAge = processAttribute(data, "\\d{2}", 1);
        double meanShoeSize = processAttribute(data, "\\d{2}(?:\\.?\\d*)?", 3);
        double meanHeight = processAttribute(data, "\\d{3}(?:\\.?\\d*)?", 4);
        maxGamesNumber = 0;
        maxFavGames = 0;

        for (int i = 1; i < data.length; i++) {
            Student student = new Student();

            student.s_timeStamp = loadEnum(TimeStamp.class, data[i][0]);
            student.s_age = loadAge(data[i][1], meanAge);
            student.s_gender = loadGender(data[i][2], "male");
            student.s_shoeSize = loadShoeSize(data[i][3], meanShoeSize);
            student.s_height = loadHeight(data[i][4], meanHeight);
            student.s_degree = loadDegree(data[i][5]);
            student.s_reasonTakingCourse = loadReason(data[i][6]);
            student.s_programmingLanguages = loadProgLang(data[i][7]);
            student.s_phoneOS = loadPhoneOS(data[i][8]);
            student.s_topicDED = loadTopic(Topic_DED.class, data[i][9]);
            student.s_topicCPM = loadTopic(Topic_CPM.class, data[i][10]);
            student.s_topicDGS = loadTopic(Topic_DGS.class, data[i][11]);
            student.s_topicVD = loadTopic(Topic_VD.class, data[i][12]);
            student.s_topicSPS = loadTopic(Topic_SPS.class, data[i][13]);
            student.s_topicSPSQ = loadTopic(Topic_SPSQ.class, data[i][14]);
            student.s_topicSPG = loadTopic(Topic_SPG.class, data[i][15]);
            student.s_topicSPT = loadTopic(Topic_SPT.class, data[i][16]);
            student.s_topicSPI = loadTopic(Topic_SPI.class, data[i][17]);
            student.s_topicCDMA = loadTopic(Topic_CDMA.class, data[i][18]);
            student.s_topicUDMT = loadTopic(Topic_UDMT.class, data[i][19]);

            student.s_gamesPlayed = loadGamesPlayed(data[i][20]);
            maxGamesNumber = student.s_gamesPlayed.length > maxGamesNumber ? student.s_gamesPlayed.length : maxGamesNumber;
            student.s_commuteToITU = loadCommute(data[i][21]);
            // making array of traverse points for a student
            String[] traverse = new String[15];
            System.arraycopy(data[i], 22, traverse, 0, 15);
            student.s_traverseITU = loadTraverse(traverse);

            student.s_fourNumbers = loadFourNumbers(data[i][37]);
            student.s_therb = loadTherb(data[i][38]);
            student.s_number = loadNumber(data[i][39]);
            student.s_favFilm = loadFavorite(Film.class, data[i][40]);
            student.s_favTVshow = loadFavorite(TVShow.class, data[i][41]);
            student.s_favGame = loadFavorite(Game.class, data[i][42]);
            maxFavGames = student.s_favGame.length > maxFavGames ? student.s_favGame.length : maxFavGames;
            student.s_row = loadRow(data[i][43]);
            student.s_seat = loadSeat(data[i][44]);
            students.add(student);
        }
        //System.out.println(Arrays.toString(FourNumbers.class.getEnumConstants()));


        return students;
    }

    public static <T extends Enum<?>> T loadEnum(Class<T> tClass, String name) {
        addEnum(tClass, name);
        return (T) Enum.valueOf((Class) tClass, name);
    }

    public static Age loadAge(String age, double mean) {
        String ageProcessed = age.replaceAll("\\s+\\D+", "");
        if (!ageProcessed.matches("\\d{2}"))
            ageProcessed = String.valueOf((int) mean);
        try {
            Age.valueOf(ageProcessed);
        } catch (IllegalArgumentException e) {
            addEnum(Age.class, ageProcessed);
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
        try {
            ShoeSize.valueOf(shoeSizeProcessed);
        } catch (IllegalArgumentException e) {
            addEnum(ShoeSize.class, shoeSizeProcessed);
        }
        return ShoeSize.valueOf(shoeSizeProcessed);
    }

    public static Height loadHeight(String height, double mean) {
        String heightProcessed = height.replaceAll("\\s+\\D+", "").replaceAll(",", ".");
        if (!heightProcessed.matches("\\d{3}(?:\\.?\\d*)?"))
            heightProcessed = String.format("%.1f", mean).replace(",", ".");
        try {
            Height.valueOf(heightProcessed);
        } catch (IllegalArgumentException e) {
            addEnum(Height.class, heightProcessed);
        }
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
        String[] langsArray = progLang.replaceAll("\\s+", progLang.contains(",") ? "" : ",")
                .replaceAll("\\)|\\(", "")
                .replaceAll("/", ",")
                .split(",");
        ProgrammingLanguage[] languages = new ProgrammingLanguage[langsArray.length];
        for (int i = 0; i < langsArray.length; i++) {
            String language = langsArray[i].trim().toUpperCase();
            if (language.contains("PYTHON")) language = "PYTHON";
            try {
                ProgrammingLanguage.valueOf(language);
            } catch (IllegalArgumentException e) {
                addEnum(ProgrammingLanguage.class, language);
            }
            languages[i] = ProgrammingLanguage.valueOf(language);
        }

        return languages;
    }

    public static PhoneOS loadPhoneOS(String os) {
        PhoneOS phoneOS;
        try {
            phoneOS = PhoneOS.valueOf(os.toUpperCase().replaceAll("[-]", ""));
        } catch (IllegalArgumentException e) {
            phoneOS = PhoneOS.ANDROID;
        }
        return phoneOS;
    }

    public static <T extends Enum<?>> T loadTopic(Class<T> tClass, String name) {
        Object result = new Object();
        try { // reflection
            Method method = tClass.getMethod("fromString", String.class);
            result = method.invoke(tClass, name);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return (T) result;
    }

    public static GamesPlayed[] loadGamesPlayed(String gPlayed) {
        String[] games = gPlayed.split(";");
        GamesPlayed[] gamesPlayed = new GamesPlayed[games.length];
        for (int i = 0; i < games.length; i++) {
            GamesPlayed gamePlayed = GamesPlayed.fromString(games[i].trim());
            gamesPlayed[i] = gamePlayed;
        }
        return gamesPlayed;
    }

    public static CommuteToITU[] loadCommute(String commute) {
        String[] commuteArray = commute.split(";");
        CommuteToITU[] commuteToITU = new CommuteToITU[commuteArray.length];
        for (int i = 0; i < commuteArray.length; i++) {
            try {
                CommuteToITU.valueOf(commuteArray[i]);
            } catch (IllegalArgumentException e) {
                addEnum(CommuteToITU.class, commuteArray[i]);
            }
            commuteToITU[i] = CommuteToITU.valueOf(commuteArray[i]);
        }
        return commuteToITU;
    }

    public static TraverseITU[] loadTraverse(String[] traverse) {
        TraverseITU[] traverseITU = new TraverseITU[traverse.length];
        for (int i = 0; i < traverseITU.length; i++) {
            traverseITU[i] = TraverseITU.valueOf(traverse[i].replaceAll("\\s+|/", ""));
        }
        return traverseITU;
    }

    public static FourNumbers[] loadFourNumbers(String fNumbers) {
        String[] fNArray = fNumbers.trim()
                .replaceAll("\\s+", fNumbers.contains(",") ? "" : ",")
                .replaceAll("(\\)\\()", ",")
                .replaceAll("[()，]", "").split(",");
        FourNumbers[] fourNumbers = new FourNumbers[fNArray.length];
        for (int i = 0; i < fNArray.length; i++) {
            try {
                FourNumbers.valueOf(fNArray[i]);
            } catch (IllegalArgumentException e) {
                addEnum(FourNumbers.class, fNArray[i]);
            }
            fourNumbers[i] = FourNumbers.valueOf(fNArray[i]);
        }
        return fourNumbers;
    }

    public static Therb loadTherb(String therb) {
        try {
            Therb.valueOf(therb);
        } catch (IllegalArgumentException e) {
            addEnum(Therb.class, therb);
        }
        return Therb.valueOf(therb);
    }

    public static Number loadNumber(String number) {
        return Number.fromString(number);
    }

    public static <T extends Enum<?>> T[] loadFavorite(Class<T> tClass, String game) {
        String[] showArray = game.trim().split(",");
        T[] enums = null;
        if (tClass == Game.class) enums = (T[]) new Game[showArray.length];
        else if (tClass == TVShow.class) enums = (T[]) new TVShow[showArray.length];
        else if (tClass == Film.class) enums = (T[]) new Film[showArray.length];

        for (int i = 0; i < showArray.length; i++) {
            String show = showArray[i].trim().toUpperCase();
            if (show.matches("-|N/A|NON")) show = "NONE";
            try {
                Enum.valueOf((Class) tClass, show);
            } catch (IllegalArgumentException e) {
                addEnum(tClass, show);
            }
            if (enums != null) {
                enums[i] = (T) Enum.valueOf((Class) tClass, show);
            }
        }
        return enums;
    }

    public static Row loadRow(String row) {
        String r = row.replaceAll("\\s+|\\d+|\\p{Punct}", "").trim().toUpperCase();
        if (!r.matches("(?i:\\w)")) r = "unknown";
        try {
            Row.valueOf(r);
        } catch (IllegalArgumentException e) {
            addEnum(Row.class, r);
        }
        return Row.valueOf(r);
    }

    public static Seat loadSeat(String seat) {
        String s = seat.replaceAll("\\s+|\\D+|\\p{Punct}", "").trim().toUpperCase();
        if (!s.matches("\\d{1,2}")) s = "unknown";
        try {
            Seat.valueOf(s);
        } catch (IllegalArgumentException e) {
            addEnum(Seat.class, s);
        }
        return Seat.valueOf(s);
    }

    public static String[][] getData() {
        if (data == null) loadData();
        return data;
    }

    public static ArrayList<Student> getStudents() {
        if (students == null) loadStudents();
        return students;
    }

    public static double maxGames() {
        return maxGamesNumber;
    }

    public static int maxFavGames() {
        return maxFavGames;
    }

    /**
     * Method which might be helpful for calculating information gain. Counts the number of
     * a specific Class label (e.g. poison or edible) appears in given data, when a specific Attribute has a specific value.
     * The method assumes that the attribute is nominal.
     *
     * @param Data
     * @param Attribute      Attribute we are calculating information gain for
     * @param AttributeValue What value of the attribute
     * @param Classification
     * @return
     */
    public static int countAttributeCategories(Collection<Student> Data, Object Attribute, Object AttributeValue, Object mainClass, Object Classification) {
        return (int) Data.stream().filter(student -> student.getAttributeValue(Attribute).equals(AttributeValue) &&
                mainClass.equals(Classification)).count();
    }

    public static List<Student> listAttributeCategories(Collection<Student> Data, Object Attribute, Object AttributeValue, Object mainClass, Object Classification) {
        return Data.stream().filter(student -> student.getAttributeValue(Attribute).equals(AttributeValue) &&
                mainClass.equals(Classification)).collect(Collectors.toList());
    }

    public static List<Student> listAttributeCategories(Collection<Student> Data, Object Attribute, Object AttributeValue) {
        return Data.stream().filter(student -> student.getAttributeValue(Attribute).equals(AttributeValue)).collect(Collectors.toList());
    }
}
