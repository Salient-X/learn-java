import java.util.*;
import java.io.*;

public class PrefixSum{
    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); 

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] arr1 = new int[n];

        arr1[0] = arr[0];


        for (int i = 1; i < arr.length; i++) {
            arr1[i] = arr1[i-1] + arr[i];
        }

        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i]);
        }

    }
}

