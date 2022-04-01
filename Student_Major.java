/*
ICT167 Assignment 2
Ong Wei Xing 34444625
1/4/2022
Student_Major.java
Student_Major class
*/

public class Student_Major extends Student
{   
    private char major;
    
    public Student_Major(String firstName, String lastName, long sID, Date DOB, char newMajor)
    {
        super(firstName, lastName, sID, DOB);
        major = newMajor;
    }
    
    public char getEnrolment()
    {
        return major;
    }
    
    public String reportGrade()
    {
        return major + "," + super.toString();
    }
    
    public String toString()
    {
        return major + "," + super.toString();
    }
}