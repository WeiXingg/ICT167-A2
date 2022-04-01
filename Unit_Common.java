/*
ICT167 Assignment 2
Ong Wei Xing 34444625
1/4/2022
Unit_Common.java
Unit_Common class
*/

public class Unit_Common extends Unit
{
    private String unitID;
    private int level;
    private int firstAssignment;
    private int secondAssignment;
    private int weeklyPrac;
    private int finalExam;
    private int mark;
    
    public Unit_Common(char enrolment, String newUnitID, int newLevel, 
                       int newFirstAssignment, int newSecondAssignment, 
                       int newWeeklyPrac, int newFinalExam)
    {
        super(enrolment);
        unitID = newUnitID;
        level = newLevel;
        firstAssignment = newFirstAssignment;
        secondAssignment = newSecondAssignment;
        weeklyPrac = newWeeklyPrac;
        finalExam = newFinalExam;
        overallMark();
    }

    public void overallMark()
    {
        double markCount;
        markCount = ((firstAssignment + secondAssignment) / 200.0) * 60.0;
        markCount += weeklyPrac;
        markCount += (finalExam / 100.0) * 25.0;
        mark = (int) markCount; //convert double to int
    }
    
    public int getMark()
    {
        return mark;
    }
    
    public String finalGrade()
    {
        if (mark >= 80)
        {
            return "HD";
        }
        else if (mark >= 70 && mark < 80)
        {
            return "D";
        }
        else if (mark >= 60 && mark < 70)
        {
            return "C";
        }
        else if (mark >= 50 && mark < 60)
        {
            return "P";
        }
            return "N";
    }
    
    public String toString()
    {
        return unitID + "," + mark + "," + finalGrade();
    }
}