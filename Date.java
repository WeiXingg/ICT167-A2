public class Date 
{
    private int day;
    private int month;
    private int year;
    
    public Date(int newDay, int newMonth, int newYear)
    {
        day = newDay;
        month = newMonth;
        year = newYear;
    }
    
    public String toString()
    {
        return day + "/" + month + "/" + year;
    }
    
    /*public static boolean isValid(int d, int m, int y)
    {
        if (d < 1 || d > 31)
            return false;
        if (m < 1 || m > 12)
            return false;
        if (y < 0)
            return false;
        
        switch(m)
        {
            case 1: return true;
            case 2: return (isLeap(y) ? d <= 29 : d <= 28);
            case 3: return true;
            case 4: return d < 31;
            case 5: return true;
            case 6: return d < 31;
            case 7: return true;
            case 8: return true;
            case 9: return d < 31;
            case 10: return true;
            case 11: return d < 31;
            default: return true; 
        }
    }
        
        public static boolean isLeap(int y)
        {
            if (y % 4 == 0)
            {
                return true;
            }
            return false;
        }*/
}