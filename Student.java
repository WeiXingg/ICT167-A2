/*
ICT167 Assignment 2
Ong Wei Xing 34444625
1/4/2022
Student.java
Student class
*/

public class Student 
{
    private String firstName;
    private String lastName;
    private long sID;
    private Date DOB;
    private Unit unit;
    
    public Student()
    {
        firstName = "";
        lastName = "";
        sID = 0;
        DOB = null;
    }
    
    public Student(String newFirstName, String newLastName, long newSID, 
                   Date newDOB)
    {
        firstName = newFirstName;
        lastName = newLastName;
        sID = newSID;
        DOB = newDOB;
    }
    
    public void setUnit(Unit newUnit) //set method to set unit class variables
    {
        unit = newUnit;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public Long getSID()
    {
        return sID;
    }
    
    public char getEnrolment()//method used to retrieve enrolment type and will be overridden
    {
        return 0;
    }
    
    public Unit getUnit()//get method for unit class variables
    {
        return unit;
    }
    
    public String reportGrade()
    {
        return "There is no grade here";
    }
    
    public boolean equals(Student tempStudent)
    {
        return sID == tempStudent.sID;
    }
    
    public String toString()
    {
        return firstName + "," + lastName + "," + sID + (unit != null ? "," + unit : ",No Grade"); //if statement to check for null in unit
    }

}