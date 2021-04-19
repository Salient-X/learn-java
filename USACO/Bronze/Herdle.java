import java.util.*;


public class Herdle {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        char[] ans = new char[9];

        char[] guess = new char[9];

        int green = 0;
        int yellow = 0;

        for (int i = 0; i < 3; i++) {
            char[] a1 = input.nextLine().toCharArray();
            for (int j = 0; j < 3; j++) {
                ans[3*i + j] = a1[j];
            }
        }

        for (int i = 0; i < 3; i++) {
            char[] a1 = input.nextLine().toCharArray();
            for (int j = 0; j < 3; j++) {
                guess[3*i + j] = a1[j];
            }
        }

        for (int i = 0; i < 9; i++) {
            if (ans[i] == guess[i]) {
                green++;
                ans[i] = 0;
                guess[i] = 0;
            }

        }

        for (int i = 0; i < 9; i++) {
            if (ans[i] == 0) {
                continue;
            }

            if (in(ans[i], guess)) {
                yellow++;
            }

        }

        System.out.println(green);
        System.out.println(yellow);


    }

    static boolean in(char a, char[] arr) {
        for (char x : arr) {
            if (x == a) {
                return true;
            }
        }
        return false;
    }

}
