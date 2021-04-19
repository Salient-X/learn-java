import java.util.*;

public class Home extends Thread {
    static int count = 0;
    static int turns = 0;
    final static int down = 0;
    final static int right = 1;
    final static Object lock = new Object();
    static void addCount() {
        synchronized (lock) {
            count++;
        }
    }
    static void resetCount() {
        synchronized (lock) {
            count = 0;
        }
    }
    static void addTurns() {
        synchronized (lock) {
            turns++;
        }
    }
    static void minusTurns() {
        synchronized(lock) {
            turns--;
        }
    }
    static int getTurns() {
        synchronized (lock) {
            return turns;
        }
    }
    

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String size = input.nextLine();

        int n = Integer.parseInt(size);
        ArrayList<Result> results = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> meta = new ArrayList<>(2);
            ArrayList<List<String>> grid = new ArrayList<>();

            String[] in1 = input.nextLine().split(" ");
            for (String j : in1) {
                meta.add(Integer.parseInt(j));
            }
            meta.add(i);
            for (int k = 0; k < (int)meta.get(0); k++) {
                String[] in2 = input.nextLine().split("");
                grid.add(Arrays.asList(in2));
            }
            
            Result r = new Result(i);
            results.add(r);
            Thread thiswork = new Thread(new RunIt(meta, grid, r));
            thiswork.start();
            // walk(meta, grid);
                // System.out.println(count.get(0));
                // count = 0;
                // turns = 0;
        }
        
        while (true) {
           boolean done = true;
           for (int i = 0; i < n; i++) {
               if (results.get(i).getCount() < 0) {
                   // System.out.println("test " + results.get(i).getTest() + " is still not ready");
                   done = false;
                   break;
               }
           }
           if (done) {
               break;
           }
        }
        
        for (int i = 0 ; i < results.size(); i++) {
            System.out.println(results.get(i).getCount());
        }
    }

    static void walk(ArrayList<Integer> meta, ArrayList<List<String>> grid) {
        ArrayList pres = new ArrayList<Node>();

        mark(pres, new Node(0,0), meta.get(0) - 1, meta.get(1), grid);
        System.out.println(count);
        count = 0;
        turns = 0;
    }

    static void mark(ArrayList<Node> pres, Node cur, int n, int k, ArrayList<List<String>> grid) {
        if (cur.x == n && cur.y == n) {
            // count++;
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

class Meta {
    int size;
    int turn;
    int test;
    Meta(int size, int turn, int test) {
        this.size = size;
        this.turn = turn;
        this.test = test;
    }
 }
 
 class RunIt implements Runnable {
    int count = 0;
    int turns = 0;
    final static int down = 0;
    final static int right = 1;

    ArrayList<Integer> meta;
    ArrayList<List<String>> grid;
    Result result;
    
    RunIt(ArrayList<Integer> meta, ArrayList<List<String>> grid, Result result) {
        this.meta = meta;
        this.grid = grid;
        this.result = result;
    }

    public void run() {
        ArrayList<Node> pres = new ArrayList<Node>();
        walk(pres, meta, grid);
        pres.clear();
        // System.out.println("Test " + meta.get(2) + " is done");
        result.test = meta.get(2);
        result.setCount(count);
        count = 0;
        turns = 0;
    }

    void walk(ArrayList<Node> pres, ArrayList<Integer> meta, ArrayList<List<String>> grid) {
        mark(pres, new Node(0,0), meta.get(0) - 1, meta.get(1), grid);
        // System.out.println(count);
        // count = 0;
        // turns = 0;
    }

    void mark(ArrayList<Node> pres, Node cur, int n, int k, ArrayList<List<String>> grid) {
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
 
 class Result {
     public int test;
     public int count;

     Result(int test) {
         this.test = test;
         this.count = -1;
     }

     public synchronized int getTest() {
         return test;
     }
     public synchronized int getCount() {
         return count;
     }

     public synchronized void setCount(int n) {
         this.count = n;
     }
 }