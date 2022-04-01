/*
ICT167 Assignment 2
Ong Wei Xing 34444625
1/4/2022
Unit_Major.java
Unit_Major class
*/

public class Unit_Major extends Unit
{
    private String unitID;
    private int level;
    private int assignment;
    private int project;
    private int weeklyProjWork;
    private int finalExam;
    private int mark;
    
    public Unit_Major(char enrolment, String newUnitID, int newLevel, 
                      int newAssignment, int newProject, int newWeeklyProjWork, 
                      int newFinalExam)
    {
        super(enrolment);
        unitID = newUnitID;
        level = newLevel;
        assignment = newAssignment;
        project = newProject;
        weeklyProjWork = newWeeklyProjWork;
        finalExam = newFinalExam;
        overallMark();
    }
    
    public void overallMark()
    {
        double markCount;
        markCount = (assignment / 100.0) * 15.0;
        markCount += (project / 100.0) * 35.0;
        markCount += weeklyProjWork;
        markCount += (finalExam / 100.0) * 40.0;
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