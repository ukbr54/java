package avaj;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Scanner;

public class JavaDateAndTime {
    public static String getDayOfWeek(int ch){
        switch (ch){
            case 0:
                return "SUNDAY";
            case 1:
                return "MONDAY";
            case 2:
                return "TUESDAY";
            case 3:
                return "WEDNESDAY";
            case 4:
                return "THURSDAY";
            case 5:
                return "FRIDAY";
            case 6:
                return "SATURDAY";
            default:
                return "NOT_EXIST";
        }
    }

    public static String findDay(int month,int day,int year){
        Calendar instance = Calendar.getInstance();
        instance.set(year,month - 1 ,day);
        final int dayOfWeek = instance.get(Calendar.DAY_OF_WEEK);
        return getDayOfWeek(dayOfWeek - 1);
    }

    public static String findDayUsingJava8(int month,int day,int year){
        final LocalDate localDate = LocalDate.of(year, month, day);
        final DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek.name();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the date with space (e.g: mm dd yyyy)");
        String date = sc.nextLine();
        final String[] strings = date.split("\\s+");
        //findDay(Integer.parseInt(strings[0]),Integer.parseInt(strings[1]),Integer.parseInt(strings[2]));
        final String day = findDayUsingJava8(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
        System.out.println("Day: "+day);
    }
}
