
import java.util.*;

public class GH {

    final static int G = 0;
    final static int H = 1;
    final static HashMap key = new HashMap<Character, Integer>();
    static {
        key.put('G', G);
        key.put('H', H);
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String size = input.nextLine();
        String data = input.nextLine();

        int n = Integer.parseInt(size);

        int total = 0;

        for (int i = 3; i <= n; i++) {
            total += scrape(i, data);
        }
        System.out.println(total);
    }

    static int ord(String input) {
        int sum = 0;
        
        for (int i = 0; i < input.length(); i++) {
            sum += (int) key.get((input.charAt(i)));
        }

        return sum;
    }
    
    static int scrape(int size, String data) {
        
        int total = 0;

        for (int i = 0; i <= data.length() - size; i++) {
            String sub = data.substring(i, size + i);
            int o = ord(sub);
            if (o == 1 || o == size - 1) {
                total++;
            }
        }

        return total;
    }
}
