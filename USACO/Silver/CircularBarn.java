import java.util.StringTokenizer;


public class CircularBarn {
    
    public static void main(String args[]) {
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N; n++) {

        

            st = new StringTokenizer(reader.readLine());
            int rooms = Integer.parseInt(st.nextToken());
            int[] cowsPerRoom = new int[rooms];
            st = new StringTokenizer(reader.readLine());
            for (int i = 0; i < rooms; i++) {
                int cows = Integer.parseInt(st.nextToken());
                int numOfPrimes = 0;
                
                while 


            }


        }

    }

    public static boolean isPrime(int n) {

        if (n == 1) {
            return true;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static int findNextPrime(int n) {

        for (int i = n; i > 0; i--) {

            if (isPrime(i)) {
                return i;
            }           

        }

        return 0;

    }

}
