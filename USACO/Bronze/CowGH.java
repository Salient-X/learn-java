

import java.util.*;

public class CowGH {

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

        ArrayList arr = new ArrayList<Integer>();

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
