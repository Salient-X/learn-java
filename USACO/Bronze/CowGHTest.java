import java.util.*;

public class CowGHTest {

    final static int G = 0;
    final static int H = 1;
    final static HashMap key = new HashMap<Character, Integer>();
    static {
        key.put('G', G);
        key.put('H', H);
    }
    public static void main(String[] args) {

        int n = 5;
        String data = gen(n);

        ArrayList arr = new ArrayList<Integer>();

        long start = System.currentTimeMillis();

        int total = 0;
        int sum = 0;
        for (int i = 0; i < data.length(); i++) {
            int v = (int)key.get(data.charAt(i));
            arr.add(i, v);
            sum += v;
        }

     
        for (int i = 3; i < n; i++) {
            total += scrape(i, arr);
        }

        total += sum == 1 || sum == n - 1 ? 1 : 0;

        System.out.println(total);
        System.out.println("Time: " + (System.currentTimeMillis() - start));

    }

    static String gen(int n) {
        int leftLimit = 71; 
        int rightLimit = 72; 
        int targetStringLength = n;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;

    }

    static int scrape(int size, ArrayList<Integer> data) {
        
        int total = 0;
        int sum = 0;

        for (int i = 0; i < size; i++) {
            sum += data.get(i);
        }
        
        for (int i = 0; i < data.size() - size; i++) {
            total += sum == 1 || sum == (size -1 ) ? 1 : 0;
            sum = sum - data.get(i) + data.get(i+size);
        }
        total += sum == 1 || sum == size -1 ? 1 : 0;
        return total;
    }

}
