package Assignment_Questionnaire;

import Assignment_Questionnaire.enums.*;

import java.lang.Number;
import java.util.ArrayList;

public class Student {
    private String timestamp;                                                            //n
    private Age s_age;                                                                   //y
    private Gender s_gender;                                                             //y
    private ShoeSize s_shoeSize;                                                         //y
    private Height s_height;                                                             //y
    private Degree s_degree;                                                             //y
    private String reasonTakingCourse;                                                   //yn
    private String[] programmingLanguages;                                               //yn
    private PhoneOS s_phoneOS;                                                           //yn
    private Topics[] s_topics;                                                           //n
    private String[] gamesPlayed;                                                        //yn
    private String[] commuteToITU;                                                       //n
    private String[] traverseITU = new String[15];                                       //n
    private String[] fourNumbers; //n
    private String therb;         //n
    private String number;        //n
    private String favFilm;       //n
    private String favTVshow;     //n
    private String favGame;       //n
    private String row;           //y
    private String seat;          //y

    public ArrayList<Object> getAttributeList() {
        ArrayList<Object> attributeList = new ArrayList<>();
        attributeList.add(TimeStamp.class);
        attributeList.add(Age.class);
        attributeList.add(Gender.class);
        attributeList.add(ShoeSize.class);
        attributeList.add(Height.class);
        attributeList.add(Degree.class);
        attributeList.add(ReasonTakeCourse.class);
        attributeList.add(ProgrammingLanguage.class);
        attributeList.add(PhoneOS.class);
        attributeList.add(Topics.class);
        // may be all topics

        attributeList.add(GamesPlayed.class);
        attributeList.add(CommuteToITU.class);
        attributeList.add(TraverseITU.class);
        attributeList.add(FourNumbers.class);
        attributeList.add(Therb.class);
        attributeList.add(Number.class);
        attributeList.add(Film.class);
        attributeList.add(TVShow.class);
        attributeList.add(Game.class);
        attributeList.add(Row.class);
        attributeList.add(Seat.class);
        return attributeList;
    }

}
