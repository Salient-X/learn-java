import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BarnTree {


    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] hj = new int[N];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < N; i++) {
            int o = Integer.parseInt(st.nextToken());
            hj[i] = o;
        }
        // System.out.println("hj: " + Arrays.toString(hj));

        int[][] pairs = new int[N - 1][2];
        st = new StringTokenizer(reader.readLine());

        for (int j = 0; j < N - 1; j++) {
            for (int i = 0; i < 2; i++) {
                int o = Integer.parseInt(st.nextToken());
                pairs[j][i] = o;
            }
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            st = new StringTokenizer(line);
        }

        steps(hj, pairs, N);
    }

    public static String show(int[][] pairs) {
        String s = "";
        for (int[] k : pairs) {
            s += k[0] + ":" + k[1] + "\n";
        }
        return s;
    }

    public static void steps(int[] hj, int[][] pairs, int N) {
        int total = all(hj);
        int avg = total / N;
        List<String> moves = new ArrayList<String>();

        int sum = build(hj, pairs, avg, 0, moves);
        
        System.out.println(sum);
        for (String move : moves) {
            System.out.println(move);
        }
    }

    public static int[] minIdx(int[] hj, int[][] pairs, int avg) {
        int val = hj[0];
        int[] p = null;

        for (int i = 0; i < hj.length; i++) {
            if (hj[i] <= val) {
                p = findPair(i, hj, pairs, avg);
                if (p != null && p.length >= 2) {
                    val = hj[i];
                }
            }
        }
        return p;
    }

    public static int[] findPair(int idx, int[] hj, int[][] pairs, int avg) {
        for (int[] p : pairs) {
            if (p[0] - 1 == idx || p[1] - 1 == idx) {
                if (hj[p[0] - 1] < avg && hj[p[1] - 1] > avg) {
                    return p;
                }
                if (hj[p[1] - 1] < avg && hj[p[0] - 1] > avg) {
                    return p;
                }
            }
        }
        return null;
    }

    public static int build(int[] values, int[][] pairs, int avg, int sum, List<String> moves) {
        if (allavg(values, avg)) {
            return sum;
        }

        int[] p = minIdx(values, pairs, avg);
        // System.out.println("find pair: " + Arrays.toString(p));
        if (p == null) {
            return sum;
        }

        sum++;
        if (values[p[0] - 1] > avg) {
            int delta = values[p[0] - 1] - avg;
            values[p[1] - 1] = (values[p[1] - 1] + delta);
            values[p[0] - 1] = avg;
            moves.add(p[0] + " " + p[1] + " " + delta);
        }
        if (values[p[1] - 1] > avg) {
            int delta = values[p[1] - 1] - avg;
            values[p[0] - 1] = (values[p[0] - 1] + delta);
            values[p[1] - 1] = avg;
            moves.add(p[1] + " " + p[0] + " " + delta);
        }
        // System.out.println("values: " + Arrays.toString(values));
        return build(values, pairs, avg, sum, moves);
    }

    public static boolean allavg(int[] values, int avg) {
        for (int v : values) {
            if (v != avg) {
                return false;
            }
        }
        return true;
    }

    public static int all(int[] hj) {
        int s = 0;
        for (int i : hj) {
            s += i;
        }
        return s;
    }

}
