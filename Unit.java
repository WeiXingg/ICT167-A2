/*
ICT167 Assignment 2
Ong Wei Xing 34444625
1/4/2022
Unit.java
Unit class
*/

public class Unit
{
    private char enrolment;
    private int mark;
    
    public Unit(char newEnrolment)
    {
        enrolment = newEnrolment;
    }
    
    public int getMark()//get method for marks and will be overriden
    {
        return mark;
    }
    
    public String finalGrade()
    {
        return "NA";
    }
}