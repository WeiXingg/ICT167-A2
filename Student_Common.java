/*
ICT167 Assignment 2
Ong Wei Xing 34444625
1/4/2022
Student_Common.java
Student_Common class
*/

public class Student_Common extends Student
{
    private char common;
    
    public Student_Common(String firstName, String lastName, long sID, Date DOB, char newCommon)
    {
        super(firstName, lastName, sID, DOB);
        common = newCommon;
    }
    
    public char getEnrolment()
    {
        return common;
    }
    
    public String reportGrade()
    {
        return common + "," + super.toString();
    }
    
    public String toString()
    {
        return common + "," + super.toString();
    }
}