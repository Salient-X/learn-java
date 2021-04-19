import java.util.*;

public class ACTest {

    public static void main(String[] args) {

        int n = 10; 
        
        // ArrayList pt = gen(n);
        List<Integer> pt = Arrays.asList(9, 1, 5, 4, 3, 4, 7, 1, 8, 4);
        List<Integer> ct = Arrays.asList(4 ,1, 1, 5, 1, 1, 2, 1, 9, 3);
        // ArrayList ct = gen(n);

        System.out.println(pt + "\n" + ct);

        long total = 0;

        long start = System.currentTimeMillis();

        while (!pt.equals(ct)) {
            total += adjust(n, pt, ct);
        }
        System.out.println(total);
        System.out.println("Time: " + (System.currentTimeMillis() - start));
    }

    static ArrayList<Integer> gen(int n) {

        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr.add(random.nextInt(10 + 1));
        }
    
        return arr;
     
    }

    static int adjust(int n, List<Integer> pt, List<Integer> ct) {
        int total = 0;

        ArrayList<Integer> diff = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            diff.add(pt.get(i) - ct.get(i));
        }

        int mindiff = 0;
        int now = 0;
        int e = -1;
        int s = -1;
        int k = -1;

        for (int i = 0; i < n; i++) {
            if (diff.get(i) == 0 || (i != 0 && diff.get(i) > 0 && diff.get(i-1) < 0 ) || (i != 0 && diff.get(i) < 0 && diff.get(i-1) > 0)) {
                if (mindiff != 0) {
                    e = i - 1;
                    level(s, e, k, ct, diff);
                    total += Math.abs(mindiff);
                    mindiff = 0; 
                    e = -1;
                    s = -1;
                    k = -1;
                }
                continue;
            }

            if (mindiff == 0) {
                s = i;
                mindiff = Math.abs((int)diff.get(i));
            }
            now = Math.abs((int) diff.get(i));
            if (now <= mindiff) {
                mindiff = now;
                k = i;
            }
        }
        
        if (mindiff != 0) {
            e = diff.size() - 1;
            level(s, e, k, ct, diff);
            total += Math.abs(mindiff);
        }

        return total;
    }

    static void level(int s, int e, int k, List<Integer> ct, List<Integer> diff) {
        int mindiff = (int) diff.get(k);
        for (int i = s; i <= e; i++) {
            int val = ct.get(i);
            ct.set(i, val + mindiff);
        }
    }

}
