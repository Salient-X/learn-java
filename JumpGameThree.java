import java.util.ArrayList;
import java.util.List;

public class JumpGameThree {

    public static void main(String[] args) {
        List<int> nums = new ArrayList<>();
        //4,2,3,0,3,1,2
        nums.add(4);
        nums.add(2);
        nums.add(3);
        nums.add(0);
        nums.add(3);
        nums.add(1);
        nums.add(2);

        int[] arr = new int[nums.size()];
        arr = nums.toArray(arr);

        System.out.println(canReach(arr, 5));

    }


    public static boolean canReach(int[] arr, int start) {
        
        int beginIndex = -1;
            
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                 beginIndex = i;      
            }
        }


        while (true) {

            for (int i = 0; i < arr.length; i++) {
                
                if (beginIndex - i == arr[i] || beginIndex - i == arr[i] * -1) {
                    
                    if (i == start) {
                        return true; 
                    }

                    beginIndex = i;
                    break;

                }
         
            }

            return false;

        }

    }
}