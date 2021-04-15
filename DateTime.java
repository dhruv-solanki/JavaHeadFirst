import java.util.Date;
import java.util.Calendar;

public class DateTime {
    public static void main(String[] args) {
        Date today = new Date();
        System.out.println(String.format("%tc", today));
        
        Calendar cal = Calendar.getInstance();
        cal.set(2021, 3, 4, 15, 30);

        System.out.println(cal);
    }  
}