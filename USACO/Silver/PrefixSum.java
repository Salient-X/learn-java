import java.util.*;
import java.io.*;


public class PrefixSum{
    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); 

        char[] arr = new char[n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine();
        }

        int[] hPre = new int[n];
        int[] sPre = new int[n];
        int[] pPre = new int[n];

        for (int i = 1; i < n; i++) {

            if (i == 1 && arr[0] == 'S') {
                hPre[0] = 1;
            }

            if (arr[i] == 'S') {
                hPre[i] = hpre[i-1] + 1;
            }
        }

        for (int i = 1; i < n; i++) {

            if (i == 1 && arr[0] == 'H') {
                pPre[0] = 1;
            }

            if (arr[i] == 'H') {
                pPre[i] = ppre[i-1] + 1;
            }
// GIVE ME A BREAK
        }

        for (int i = 1; i < n; i++) {

            if (i == 1 && arr[0] == 'P') {
                sPre[0] = 1;
            }

            if (arr[i] == 'P') {
                sPre[i] = spre[i-1] + 1;
            }
        }

    }
}

