import java.lang.Math;

public class CountDigit2 {

    public static void main(String[] args) {

        CountDigit2 cd = new CountDigit2();

        long startTime = System.nanoTime();

        cd.findNthDigit(1000000000);

        long endTime = System.nanoTime();
        System.out.println("Took "+(endTime - startTime)/1e9 + " s");
    }

    public int findNthDigit(int n) {
        
        if (n < 10) {
            return n;
        }
        
        int count = 1;
        int num = 1;
        int prevlen = 1;
        
        
        while (true) {
            int num1 = num;
            
            
            while (true) {
                
                Value p = findFirst(num1);
                
                if (prevlen - p.len > 1) {
                    for (int i = 1; i < prevlen - p.len ; i++) {
                        
                        count++;
                            
                        if (count == n) {
                            return 0;
                        }
                        
                    }
                }
                
                if (count == n) {
                    return p.first;
                }
                
                count++;
                
                if (p.len == 0) {
                    break;
                }
       
                num1 = p.remainder;
                
                prevlen = p.len;
            
            }
            
            num++;
            
        } 

    }
    
    public Value findFirst(int num) {
        int len = 1;
        int num1 = num;
        while (num >= 10) {
            num /= 10;                
            len++;
        }
 
        return new Value(num, num1 % (int)(Math.pow(10,len-1)), len-1);
    }
}

class Value {
    int first;
    int remainder; 
    int len;
    
    Value(int a, int b, int c) {
        this.first = a;
        this.remainder = b;
        this.len = c;
    }
    
}

