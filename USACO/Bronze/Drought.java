import java.util.*;
public class Drought {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int n = Integer.parseInt(input.nextLine()); 

        List<List<Long>> lines = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int n1 = Integer.parseInt(input.nextLine());
            List<Long> line = new ArrayList<>();
            String[] parts = input.nextLine().split(" ");
            for (String str : parts) {
                line.add(Long.parseLong(str));
            }
            lines.add(line);
        }

        input.close();



        for (List<Long> line : lines) {

            if (line.size() <= 1) {
                System.out.println(0);
                continue;
            }

            if (line.size() == 2) {
                if (!(line.get(0).equals(line.get(1)))) {
                    System.out.println(-1);
                    continue;
                } else {
                    System.out.println(0);
                    continue;
                }
            }

            long count = 0;

            boolean altered = false;
            boolean failed = false;

            while (true) {
                altered = false;
                failed = false;
                for (int i = 0; i < line.size() - 2; i++) {

                    long[] a = new long[3];
                    a[0] = line.get(i);
                    a[1] = line.get(i + 1);
                    a[2] = line.get(i + 2);

                    if (fatMid(a)) {
                        failed = true;
                        System.out.println(-1);
                        break;
                    }

                    if (equal(a) || !valid(a)) {
                        continue;
                    }

                    long x = alter(a);
                    
                    count += x;

                    if (x != 0) {
                        altered = true;
                    }

                    line.set(i, a[0]);
                    line.set(i + 1, a[1]);
                    line.set(i + 2, a[2]);

                }

                if (!altered || failed) {
                    break;
                }

            }


            if (!failed) {
                boolean same = true;
                for (int i = 0; i < line.size() - 1; i++) {
                    if (!line.get(i).equals(line.get(i + 1))) {
                        same = false;
                        break;
                    }
                }

                if (same) {
                    System.out.println(count);
                } else {
                    System.out.println(-1);
                }
            }
        }

    }
    
    static boolean equal(long[] a) {
        return (a[0] == a[1] && a[0] == a[2]);
    }

    static boolean fatMid(long[] a) {
        long first = a[0];
        long mid = a[1];
        long last = a[2];

        return (mid - first > first && mid - last > last);
    }

    static long alter(long[] a) {
        long first = a[0];
        long mid = a[1];
        long last = a[2];

        long num = last - mid + first;

        a[0] = num;
        a[1] = num;
        a[2] = num;

        return first + mid + last - 3 * num;

    }

    static boolean valid(long[] a) {
        long first = a[0];
        long mid = a[1];
        long last = a[2];
        return (mid > first && mid > last);
    }

}
