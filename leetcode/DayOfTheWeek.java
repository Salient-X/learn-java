import java.util.Map;

class DayOfTheWeek {
    public String dayOfTheWeek(int day, int month, int year) {

        int firstTwoDigitsOfYear = year/100;
        System.out.println(firstTwoDigitsOfYear);
        int lastTwoDigitsOfYear = year%100;
        System.out.println(lastTwoDigitsOfYear);
        
        Map<Integer, String> daymap = new HashMap<>();
        
        daymap.put(1, "Monday");
        daymap.put(2, "Tuesday");
        daymap.put(3, "Wednesday");
        daymap.put(4, "Thursday");
        daymap.put(5, "Friday");
        daymap.put(6, "Saturday");
        daymap.put(0, "Sunday");

        Map<Integer, Integer> mkv = new HashMap<>();
        
        if (year%4 == 0 && year != 2100) {
            mkv.put(1, -1);
            mkv.put(2, 2);
        } else {
            mkv.put(1, 0);
            mkv.put(2, 3);
        }
        
        mkv.put(3, 3);
        mkv.put(4, 6);
        mkv.put(5, 1);
        mkv.put(6, 4);
        mkv.put(7, 6);
        mkv.put(8, 2);
        mkv.put(9, 5);
        mkv.put(10, 0);
        mkv.put(11, 3);
        mkv.put(12, 5);

        Map<Integer, Integer> CC = new HashMap<>();

        CC.put(0,6);
        CC.put(1,4);
        CC.put(2,2);
        CC.put(3,0);
        
        
        int CCval = CC.get(firstTwoDigitsOfYear%4);
        System.out.println(CCval);
        int YCval = (lastTwoDigitsOfYear + (lastTwoDigitsOfYear/4)) % 7;
        System.out.println(YCval);
        int d = (CCval + YCval + mkv.get(month) + day) % 7;
        return daymap.get(d);
    }
}