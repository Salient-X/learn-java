import java.lang.reflect.Array;
import java.util.*;

import javax.swing.plaf.ComboBoxUI;

public class NonTrasitiveDice {    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num  = Integer.parseInt(input.nextLine());
        int[][][] ins = new int[num][2][4];

        for (int asdf = 0; asdf < num; asdf++) {

            int[] a = new int[4];
            int[] b = new int[4];

            String[] words = input.nextLine().split(" ");
            for (int j = 0; j < 4; j++) {
                a[j] = Integer.parseInt(words[j]);
            }
            for (int k = 4; k < words.length; k++) {
                b[k - 4] = Integer.parseInt(words[k]);
            }
            ins[asdf][0] = a;
            ins[asdf][1] = b;
        }

        input.close();

        
        List<int[]> ln1 = new ArrayList<>();
        combanation(ln1, 1, 10, 0, new int[4]);
        //System.out.println(show(ln1) + "\nTotal: " + ln1.size());
        List<int[]> ln2 = new ArrayList<>();
        combanation(ln2, 1, 10, 0, new int[3]);
        ln2 = fill(ln2);
        //System.out.println(show(ln2) + "\nTotal: " + ln2.size());
        List<int[]> ln3 = new ArrayList<>();
        combanation(ln3, 1, 10, 0, new int[2]);
        ln3 = fill(ln3);
        //System.out.println(show(ln3) + "\nTotal: " + ln3.size());
        for (int[] g : ln2) {
            ln1.add(g);
        }

        for (int[] h : ln3) {
            ln1.add(h);
        }

        for (int q = 1; q <= 10; q++) {
            ln1.add(new int[] { q, q, q, q });
        }

        //System.out.println(show(ln1) + "\nTotal: " + ln1.size());

        for (int[][] entry : ins) {

            int[] a = entry[0];
            int[] b = entry[1];

            if (compare(a, b) == 0) {
                System.out.println("no");
                continue;
            }
            
            if (compare(a, b) == 1) {
                List<int[]> out = new ArrayList<>();
                for (int[] t : ln1) {
                    if (compare(t, a) == 1) {
                        out.add(t);
                    }
                }
                if (out.size() <= 0) {
                    System.out.println("no");
                    continue;
                }

                boolean exists = false;

                for (int[] t : out) {
                    if (compare(t, b) == -1) {
                        System.out.println("yes");
                        exists = true;
                        break;
                    }
                }

                if (!exists) {
                    System.out.println("no");
                }

            }

            if (compare(b, a) == 1) {
                List<int[]> out = new ArrayList<>();
                for (int[] t : ln1) {
                    if (compare(t, b) == 1) {
                        out.add(t);
                    }
                }
                if (out.size() <= 0) {
                    System.out.println("no");
                    continue;
                }

                boolean exists = false;

                for (int[] t : out) {
                    if (compare(t, a) == -1) {
                        System.out.println("yes");
                        exists = true;
                        break;
                    }
                }

                if (!exists) {
                    System.out.println("no");
                }

            }

        }
    }

    static String show(List<int[]> lst) {
        String s = "";
        for (int[] x : lst) {
            s = s.concat(Arrays.toString(x)) + "\n";
        }
        return s;
    }

    static int compare(int[] a, int[] b) {
        int aprob = 0;
        int bprob = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                if (a[i] > b[j]) {
                    aprob++;
                } else if(b[j] > a[i]) {
                    bprob++;
                } 
            }
        }

        if (aprob == bprob) {
            return 0;
        }

        return (aprob > bprob?1:-1);

    }

    static void combanation(List<int[]> result, int start, int end, int pos, int[] temp) {
        if (pos == temp.length) {
            result.add(temp.clone());
            return;
        }

        if (start <= end) {
            temp[pos] = start;
            combanation(result, start + 1, end, pos + 1, temp);
            combanation(result, start + 1, end, pos, temp);
        }

    }

    static List<int[]> fill(List<int[]> result) {

        List<int[]> all = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            int[] a = result.get(i);
            for (int j = 0; j < a.length; j++) {
                int[] b = new int[4];
    
                for (int k = 0; k < a.length; k++) {
                    b[k] = a[k];
                }

                for (int d = 0; d < 4 - a.length; d++) {
                    b[a.length + d] = a[j];
                }

                all.add(b);

            }


            
        }

        return all;

    }


}
