package algorithms;

/**
 * Given a time in 12-hour AM/PM format, convert it to military (24-hour) time.
 *
 * Note: Midnight is 12:00:00AM on a 12-hour clock, and 00:00:00 on a 24-hour clock. Noon is 12:00:00PM on a 12-hour clock,
 * and 12:00:00 on a 24-hour clock.
 *
 */
public class TimeConversion {

    public static void main(String[] args) {
        String time = "07:05:45PM";
        int hour = Integer.parseInt(time.substring(0,2));
        int minute = Integer.parseInt(time.substring(3,5));
        int second = Integer.parseInt(time.substring(6,8));

        String meridiem = time.substring(8,10);

        hour += meridiem.equals("PM") && hour != 12 ? 12 :0;
        hour -= (meridiem.equals("AM") && hour == 12) ? 12 : 0;

        String convertedTime = String.format("%02d",hour)+":"+String.format("%02d",minute)+":"+String.format("%02d",second);
        System.out.println(convertedTime);
    }
}
