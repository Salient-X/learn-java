
import java.util.*;

public class AC {

    final static int bigger = 1;
    final static int smaller = 2;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String size = input.nextLine();

        int n = Integer.parseInt(size);
        
        String[] in1 = input.nextLine().split(" ");
        String[] in2 = input.nextLine().split(" ");
        

        ArrayList pt = new ArrayList<Integer>(n);
        ArrayList ct = new ArrayList<Integer>(n);

        for (String v : in1) {
            pt.add(Integer.parseInt(v));
        }

        for (String v : in2) {
            ct.add(Integer.parseInt(v));
        }

        long total = 0;

        while (!pt.equals(ct)) {
            total += adjust(n, pt, ct);
        }
        System.out.println(total);
    }

    static int adjust(int n, ArrayList<Integer> pt, ArrayList<Integer> ct) {
        long total = 0;

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
                    now = 0;
                }
                continue;
            }

            if (mindiff == 0) {
                s = i;
                mindiff = Math.abs((int)diff.get(i));
            }
            now = Math.abs((int) diff.get(i));
            if (now > mindiff) {
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

    static void level(int s, int e, int k, ArrayList<Integer> ct, ArrayList<Integer> diff) {
        int mindiff = (int) diff.get(k);
        for (int i = s; i <= e; i++) {
            int val = ct.get(i);
            ct.set(i, val + mindiff);
        }
    }

}
