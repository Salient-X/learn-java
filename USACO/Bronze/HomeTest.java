
import java.util.*;

public class HomeTest {
    static int count = 0;
    static int turns = 0;
    final static int down = 0;
    final static int right = 1;
    final static int H = 1;
    final static int D = 0;
    final static HashMap<Integer, String> key = new HashMap<Integer, String>();
    static {
        key.put(H, "H");
        key.put(D, ".");
    }
    public static void main(String[] args) {
        
        int n = 1;
        ArrayList<Integer> meta = genMeta();
        ArrayList<List<String>> grid = genGrid(meta.get(0));


        System.out.println(meta);
        System.out.println(grid);

        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            walk(meta, grid);
            System.out.println(count);
            count = 0;
            turns = 0;
        }
        System.out.println("Time: " + (System.currentTimeMillis() - start));

    }

    static ArrayList<Integer> genMeta() {

        Random rand = new Random();

        int size = rand.nextInt(1) + 3;

        int k = rand.nextInt(1) + 3;

        ArrayList arr = new ArrayList<Integer>();

        arr.add(size);
        arr.add(k);

        return arr;

    }

    static ArrayList<List<String>> genGrid(int size) {
        Random rand = new Random();
        int linelim = 3;
        int linecount = 0;
        ArrayList<List<String>> arr = new ArrayList<List<String>>();
        for (int i = 0; i < size; i++) {
            rand.setSeed(System.currentTimeMillis());
            List<String> lst = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                int num = rand.nextInt(1);
                if (num == 1) {
                    linecount++;
                }
                if (linecount >= linelim) {
                    num = 0;
                } 
                lst.add(key.get(num));
            }
            linecount = 0;
            arr.add(lst);
        }
        arr.get(0).set(0, ".");
        arr.get(arr.size()-1).set(arr.size()-1, ".");
        return arr;

    }

    static void walk(ArrayList<Integer> meta, ArrayList<List<String>> grid) {
        ArrayList pres = new ArrayList<Node>();
        mark(pres, new Node(0,0), meta.get(0) - 1, meta.get(1), grid);
    }

    static void mark(ArrayList<Node> pres, Node cur, int n, int k, ArrayList<List<String>> grid) {
        String scur = "cur("+cur.x+","+cur.y+")";

        if (cur.x == n && cur.y == n) {
            count++;
            return;
        }

        if (pres.size() == 0) {
            pres.add(cur);
            if (grid.get(cur.x + 1).get(cur.y).equals("H")) {
                // System.out.println(scur + " right has a wall, turn down");
            } else {
                // System.out.println("from:" + scur + " to:(" + (cur.x + 1) + "," + cur.y + ") turns =" + turns);
                mark(pres, new Node(cur.x + 1, cur.y), n, k, grid);
            }

            if (grid.get(cur.x).get(cur.y + 1).equals("H")) {
                // System.out.println(scur + " down has a wall, turn right");
            } else {
                // System.out.println("from:" + scur + "to:(" + (cur.x) + "," + (cur.y + 1) + ") turns = " + turns);
                mark(pres, new Node(cur.x, cur.y + 1), n, k, grid);
            }
            return;
        }

        if (direction(pres.get(pres.size() - 1), cur) == down) {
            if (cur.y + 1 > n) {
                if (turns >= k) {
                    return;
                }
                if (grid.get(cur.x+1).get(cur.y).equals("H")) {
                    // System.out.println(scur+" right has a wall when force turn right from down");
                    return;
                }
                turns++;
                pres.add(cur);
                // System.out.println("=>force right from:"+scur+" to:(" + (cur.x+1) + "," + cur.y + ") turns = " + turns);
                Node node = new Node(cur.x+1, cur.y);
                mark(pres, node, n, k, grid);
                turns--;
                pres.remove(cur);
                return;
            } 

            // keep down.
            if (grid.get(cur.x).get(cur.y+1).equals("H")) {
                // System.out.println(scur + " down has a wall when keep down");
            } else {
                pres.add(cur);
                Node node = new Node(cur.x, cur.y + 1);
                // System.out.println("=>keep down from:" + scur + " to:(" + cur.x + "," + (cur.y + 1) + ") turns = " + turns);
                mark(pres, node, n, k, grid);
                pres.remove(cur);
            }
            
            if (cur.x + 1 > n) {
                return;
            }

            // turn right.
            if (grid.get(cur.x+1).get(cur.y).equals("H")) {
                    // System.out.println(scur+" right has a wall when free turn right");
                return;
            }
            if (turns >= k) {
                return;
            }
            turns++;
            pres.add(cur);
            Node node = new Node(cur.x+1, cur.y);
            // System.out.println("=>turn right from:"+scur+" to:("+(cur.x+1)+","+cur.y+") turns = " + turns);
            mark(pres, node, n, k, grid);
            turns--;
            pres.remove(cur);
        } 

        //----------------------------------------==============================================-------------------------------------------------------------------------//

        if (direction(pres.get(pres.size() - 1), cur) == right) {
            if (cur.x + 1 > n) {
                if (turns >= k) {
                    return;
                }
                if (grid.get(cur.x).get(cur.y+1).equals("H")) {
                    // System.out.println(scur+" down has a wall when force turn down from right");
                    return;
                }
                turns++;
                pres.add(cur);
                Node node = new Node(cur.x,cur.y+1);
                // System.out.println("=>force down from:"+scur+ " to:("+cur.x+","+(cur.y+1)+") turns = " + turns);
                mark(pres, node, n, k, grid);
                turns--;
                pres.remove(cur);
                return;
            }

            if (grid.get(cur.x+1).get(cur.y).equals("H")) {
                // System.out.println(scur+ " right has a wall when keep right");
            } else {
                pres.add(cur);
                Node node = new Node(cur.x + 1, cur.y);
                // System.out.println("=>keep right from:" + scur + " to:(" + (cur.x + 1) + "," + cur.y + ") turns = " + turns);
                mark(pres, node, n, k, grid);
                pres.remove(cur);
            }
            
            if (cur.y + 1 > n) {
                return;
            }

            if (grid.get(cur.x).get(cur.y+1).equals("H")) {
                // System.out.println(scur+ " down has a wall when free turn down");
                return;
            }
            if (turns >= k) {
                return;
            }
            turns++;
            pres.add(cur);
            Node node = new Node(cur.x,cur.y+1);
            // System.out.println("=>turn down from:"+scur+" to:("+(cur.x)+","+(cur.y+1)+") turns = " + turns);
            mark(pres, node, n, k, grid);
            turns--;
            pres.remove(cur);
        } 
    }

    static int direction(Node pre, Node cur) {
        if (cur.x > pre.x) {
            return right;
        }

        return down;
    }

}

class Node {
    int x;
    int y;
    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
