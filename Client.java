/*
ICT167 Assignment 2
Ong Wei Xing 34444625
1/4/2022
Client.java
Main client for testing
*/

import java.util.*;
import java.io.*;

public class Client 
{
    public static void main(String[] args) 
    {
        studentInfo();
        menu();
    }
    
    public static void menu()
    {
        ArrayList<Student> students = new ArrayList<>();
        getStudent(students);
        Scanner input = new Scanner(System.in);
        System.out.println("1. Quit");
        System.out.println("2. Add all the marks information about a common or major student.");
        System.out.println("3. Enter a student number for removal.");
        System.out.println("4. Output all details for every student.");
        System.out.println("5. Display amount of common year students who obtained above and below average overall mark.");
        System.out.println("6. Enter a student number to view grade information.");
        System.out.println("7. Sort students in ascending order according to their student number.");
        System.out.println("8. Output sorted student to a CSV file.");
        boolean flag = true;
        while (flag)
        {
            System.out.println("\nPlease enter your choice from 1 to 8: ");
            int choice;
            while (true)
            {
                if (input.hasNextInt())
                {
                    choice = input.nextInt();
                    input.nextLine();
                }
                else
                {
                    System.out.println("\nYou have entered an invalid key.");
                    input.nextLine();
                    break;
                }
                
                while (choice < 1 || choice > 8)
                {
                    System.out.println("\nYou have entered an invalid key.");
                    break;
                }
                
                switch(choice)
                {
                    case 1:
                        System.out.println("\nThe program will end now.");
                        flag = false;
                        break;

                    case 2:
                        getMarks(students);
                        break;

                    case 3:
                        removeStudent(students);
                        break;

                    case 4:
                        System.out.println();
                        for (int i = 0 ; i < students.size() ; i++)
                        {
                            System.out.println(students.get(i).toString());
                        }
                        break;

                    case 5:
                        getNumberOfAVG(students);
                        break;

                    case 6:
                        gradeInformation(students);
                        break;

                    case 7:
                        sortArraylist(students);
                        break;

                    case 8:
                        boolean sorted = true;
                        for (int i = 1; i < students.size(); i++)
                        {
                            if (students.get(i - 1).getSID() > students.get(i).getSID())
                                sorted = false;
                        }
                        if (sorted == true)
                        {
                            saveStudents(students);
                        }
                        else
                        {
                            System.out.println("\nThe array is not sorted yet.");
                            break;
                        }
                }
                break;
            }
        }
        
    }
    
    public static void getStudent(ArrayList<Student> students)
    {
        String infileName = "studentinfo.txt";
        Scanner reader = null;
        try
        {
            reader = new Scanner(new File(infileName));
            while (reader.hasNext())
            {
                String oneLine = reader.nextLine();
                String [] split = oneLine.split(",");
                long sID = Integer.parseInt(split[2]); 
                int day = Integer.parseInt(split[3]);
                int month = Integer.parseInt(split[4]);
                int year = Integer.parseInt(split[5]);
                Date date = new Date(day, month, year); //line 108 to 112 is to convert data type
                char enrolment = Character.toUpperCase(split[6].charAt(0)); //convert character to upper case for easier checking
                if (enrolment == 'C')
                {
                    students.add(new Student_Common(split[0], split[1], 
                                sID, date, enrolment));
                }
                else if (enrolment == 'M')
                {
                    students.add(new Student_Major(split[0], split[1], 
                                sID, date, enrolment));
                }
                else
                throw new IllegalArgumentException("\nInvalid Enrolment: " + enrolment);
            }
        }
        catch (FileNotFoundException err)
        {
            System.out.println(err + " Please re-run the program with the file copied in directory or ensure that the file name is identical.\n");
        }
        finally
        {
            if (reader != null)
                reader.close();
        }
    }
    
    public static void getMarks(ArrayList<Student> student)
    {
        String infileName = "marks.txt";
        Scanner reader = null;
        int index = 0;
        try
        {
            reader = new Scanner(new File(infileName));
            while (reader.hasNext())
            {
                String oneLine = reader.nextLine();
                String [] split = oneLine.split(",");
                char enrolment = Character.toUpperCase(split[0].charAt(0));
                int level = Integer.parseInt(split[2]);
                int firstMark = Integer.parseInt(split[3]);
                int secondMark = Integer.parseInt(split[4]);
                int thirdMark = Integer.parseInt(split[5]);
                int fourthMark = Integer.parseInt(split[6]);
                Student students = student.get(index); //copy data at specific loc 
                if (enrolment == 'C')
                { 
                    Unit_Common common = new Unit_Common(enrolment, split[1], 
                                                         level, firstMark,
                                                         secondMark, thirdMark,
                                                         fourthMark); //retrieve info from unit class
                    students.setUnit(common); //setter method
                    student.set(index, students); //set info back to specific loc
                }
                else if (enrolment == 'M')
                {
                    Unit_Major major = new Unit_Major(enrolment, split[1], level, 
                                                      firstMark, secondMark, 
                                                      thirdMark, fourthMark);
                    students.setUnit(major);
                    student.set(index, students);
                }
                else
                {
                    throw new IllegalArgumentException("\nInvalid Enrolment: " + enrolment);
                }
                index++;
            }
            System.out.println("\nMarks information have been added.");
        }
        catch (FileNotFoundException err)
        {
            System.out.println("\n" + err + " Please copy the file containing unit information into directory or ensure that the file name is indentical.");
        }
        catch (IndexOutOfBoundsException err)
        {
            System.out.println("\n" + err + " There are no student or insufficient student available.");
        }
        finally
        {
            if (reader != null)
                reader.close();
        }
    }
    
    public static void removeStudent(ArrayList<Student> student)
    {
        Scanner input = new Scanner(System.in);
        long inputSID = 0;
        System.out.println("\nPlease enter a student number for removal: ");
        boolean flag = true;
        while (true)
        {
            if (input.hasNextLong())
            {
                inputSID = input.nextLong();
            }
            else
            {
                System.out.println("\nYou have entered any invalid key.");
                break;
            }
            for (int a = 0 ; a < student.size() ; a++)
            {
                if (student.get(a).getUnit() == null) //check if arraylist contains marks to prevent nullpointerexception
                {
                    System.out.println("\nNo marks allocated, student information cannot be removed before marks allocation.");
                    flag = false;
                    break;
                }
                else
                {
                    for (int i = 0 ; i < student.size() ; i++)
                    {
                        if (inputSID == student.get(i).getSID())
                        {
                            System.out.println("\nPlease confirm if you want to delete (Y/N): " + 
                                                student.get(i).getFirstName() + " " + 
                                                student.get(i).getLastName());
                            String choice = input.next();
                            while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) //check for y or n
                            {
                                System.out.println("\nInvalid option, please key either Y or N: ");
                                choice = input.next();
                            }
                            if (choice.equalsIgnoreCase("n"))
                            {
                                break;
                            }
                            else
                            {
                                System.out.println("\n" + student.get(i).getFirstName() + " " + 
                                                   student.get(i).getLastName() + 
                                                   " has been successfully removed.");
                                student.remove(i);
                                flag = false;
                                break;
                            }
                        }
                    }
                }
            }
            if (flag == true)
            {
                System.out.println("\nThere is no such student number.");
            }
            break;
        }
    }
    
    public static void getNumberOfAVG(ArrayList<Student> students)
    {
        for (int i = 0 ; i < students.size() ; i++)
        {
            if (students.get(i).getUnit() == null)
            {
                System.out.println("\nThere are no marks allocated yet.");
                break;
            }
            else
            {
                int averageMark;
                int counter = 0;
                int markCount = 0;
                int aboveAverage = 0;
                int belowAverage = 0;
                for (int a = 0 ; a < students.size() ; a++)
                {
                    if (students.get(a).getEnrolment() == 'C')
                    {
                        markCount += students.get(a).getUnit().getMark();
                        counter++;
                    }
                }
                averageMark = markCount / counter;
                for (int b = 0 ; b < students.size() ; b++)
                {
                    if (students.get(b).getEnrolment() == 'C')
                    {
                        if (students.get(b).getUnit().getMark() < averageMark )
                        {
                            belowAverage++;
                        }
                        else
                        {
                            aboveAverage++;
                        }
                    }
                }
                System.out.println("\nThere are " + aboveAverage + " student(s) who obtained an overall mark equal to or above the average.");
                System.out.println("There are " + belowAverage + " student(s) who obtained an overall mark below the average.");
            }
            break;
        }
    }
    
    public static void gradeInformation(ArrayList<Student> student)
    {
        Scanner input = new Scanner(System.in);
        long inputSID = 0;
        System.out.println("\nPlease enter a student number for grade information: ");
        while (true)
        {
            if (input.hasNextLong())
            {
                inputSID = input.nextLong();
            }
            else
            {
                System.out.println("\nYou have entered an invalid key.");
                break;
            }
            boolean flag = true;
            for (int i = 0 ; i < student.size() ; i++)
            {
                if (inputSID == student.get(i).getSID())
                {
                    flag = true;
                    System.out.println("\n" + student.get(i).reportGrade());
                    break;
                }
                flag = false;
            }
            if (flag == false)
            {
                System.out.println("\nThere is no such student number.");
            }
            break;
        }
    }
    
    public static void sortArraylist(ArrayList<Student> students)
    {
        boolean flag = true;
        for (int a = 0 ; a < students.size() ; a++)
        {
            if (students.get(a).getUnit() == null) //check if arraylist contains marks to prevent nullpointerexception
            {
                System.out.println("\nNo marks allocated, student information cannot be sorted before marks allocation.");
                flag = false;
                break;
            }
            else
            {
                for (int i = 1 ; i < students.size() ; i++)
                {
                    int j = i - 1;
                    Student tempStudent = students.get(i);
                    while (j >= 0 && (students.get(j).getSID() > tempStudent.getSID()))//loop through whole arraylist
                    {
                        students.set(j + 1, students.get(j));
                        j = j - 1;
                    }
                    students.set(j + 1, tempStudent);
                }
            }
        }
        if (flag == true)
        {
            System.out.println("\nStudents have been sorted according to their student ID.");
        }
    }
    
    public static void saveStudents(ArrayList<Student> students)
    {
        String outfileName = "fullstudentinfo.txt";
        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(outfileName);
            for (int i = 0 ; i < students.size() ; i++)
            {
                Student oneItem = students.get(i);          
                String dataLine = oneItem.getEnrolment() + "," +
                                  oneItem.getFirstName() + "," +
                                  oneItem.getLastName() + "," +
                                  oneItem.getSID() + "," +
                                  oneItem.getUnit();
                writer.println(dataLine);
            }
        }
        catch (FileNotFoundException err)
        {
            System.out.println(err);
        }
        finally
        {
            System.out.println("\n" + outfileName + " has been created.");
            if (writer != null)
                writer.close();
        }
    }
    
    public static void studentInfo()
    {
        System.out.println("Name: Ong Wei Xing\n" 
                + "Student number: 34444625\n"
                + "Online enrolment\n"
                + "Tutor Name: Chong Siew Cheong\n"
                + "Every tuesday 1830-2030hrs\n");
    }    
}