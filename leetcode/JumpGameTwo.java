import java.util.ArrayList;
import java.util.List;

public class JumpGameTwo {
    public static void main(String args[]) {

        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(2);
        nums.add(3);
        nums.add(1);
        nums.add(1);
        nums.add(4);

        System.out.println(countJumps(nums));
    }


    public static int countJumps(List<Integer> nums) {
        if (nums.size() <= 1) {
            return 0;
        }
    
    
        if (nums.size() == 2) {
            return 1;
        }

        int jumps = 0;
        int idx = -1;
        int minJumps = -1;
        
        
            
            if (nums.get(i) + i >= nums.size() - 1) {
                
                if (i < nums.size() - 1) {
                    jumps ++;
                }
            }

            int start = i - 1;
                
            //System.out.println("Found = " + nums.get(i) + "  Jumps = " + jumps + "  Start = " + start);
                
            while (true) {
                for (int j = start; j > -1; j--) {
                    if (nums.get(j) + j > start) {
                            
                        //System.out.println("Found next position: " + j);
                           
                        idx = j;
                    }
                }
                
                if (idx != -1) {
                    jumps ++;
                }
                    
                //System.out.println("jumps is "+ jumps+ "idx is "+ idx);
                    
                if (idx < 0) {
                    jumps = 0;
                    break;
                }
                    
                start = idx - 1;
                idx = -1;
                 
                //System.out.print(" Start: " + start);
                 
                if (start < 0) {
                    if (minJumps == -1 || minJumps > jumps) {
                        minJumps = jumps;
                    }
                    break;
                }
            }       
        }

        return minJumps;

    }
}

