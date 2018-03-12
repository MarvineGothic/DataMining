package Assignment_Questionnaire;

import Assignment_Questionnaire.enums.*;
import Assignment_Questionnaire.enums.Number;

import java.util.ArrayList;

public class Student {
    public TimeStamp s_timeStamp;                                                        //n
    public Age s_age;                                                                   //y
    public Gender s_gender;                                                             //y
    public ShoeSize s_shoeSize;                                                         //y
    public Height s_height;                                                             //y
    public Degree s_degree;                                                             //y
    public ReasonTakeCourse s_reasonTakingCourse;                                       //yn
    public ProgrammingLanguage[] s_programmingLanguages;                                  //yn
    public PhoneOS s_phoneOS;                                                           //yn
    public Topics[] s_topics;                                                           //n
    public GamesPlayed[] gamesPlayed;                                                   //yn
    public CommuteToITU[] commuteToITU;                                                  //n
    public TraverseITU[] traverseITU;                                                    //n
    public FourNumbers[] fourNumbers;                                                   //n
    public Therb therb;                                                                 //n
    public Number number;                                                                //n
    public Film favFilm;                                                                 //n
    public TVShow favTVshow;                                                            //n
    public Game favGame;                                                                 //n
    public Row row;                                                                      //y
    public Seat seat;                                                                     //y

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
