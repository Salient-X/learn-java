import java.io.*;
import java.util.*;

public class MergeSort {

	public static void main(String[] args) throws IOException {
		for (int i = 1; i <= 10; i++) {
			long start = System.currentTimeMillis();
			minNumberCross(i);
			long end = System.currentTimeMillis();
			System.out.println("Finished: " + (end - start) + " ms");
			Scanner input = new Scanner(new File(i + ".out"));
			long expectedResult = input.nextLong();
			input = new Scanner(new File("test.out"));
			long ourResult = input.nextLong();
			if (expectedResult == ourResult) {
				System.out.println(i + ": pass");
			} else {
				System.out.println(i + ": fail\nour result: " + ourResult + " expected result: " + expectedResult);
			}
			
			System.out.println("========================================");
		}
		
	}
	
	public static void minNumberCross(int fileName) throws IOException {
	    BufferedReader f = new BufferedReader(new FileReader(fileName + ".in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));
	    PrintWriter out2 = new PrintWriter(new BufferedWriter(new FileWriter("test2.out")));
	    StringTokenizer st = new StringTokenizer(f.readLine());
	    int n = Integer.parseInt(st.nextToken());
	    
	    int[] arr1 = new int[n];
	    int[] arr2 = new int[n];
	    
	    // O(N)
	    for (int i = 0; i < n; i++) {
	    	st = new StringTokenizer(f.readLine());
	    	int value = Integer.parseInt(st.nextToken());
	    	arr1[i] = value;
	    }
	    
	    // O(N)
	    for (int i = 0; i < n; i++) {
	    	st = new StringTokenizer(f.readLine());
	    	int value = Integer.parseInt(st.nextToken());
	    	arr2[i] = value;
	    }
	    
	    convertArray(arr1, arr2); // O(N)
	    
	    copy(arr1, arr2); // O(N)

	    // O(NlogN)
	    long currentInversion = getNumOfInversions(arr2);
	    
	    /**
	    // O(N^2*logN)
	    for (int i = 0; i < n - 1; i++) {
	    	// O(N)
	    	copy(arr1, arr2); // O(N)
	    	shift(arr2);
	    	copy(arr2, arr1);
	    	// O(NlogN)
	    	min = Math.min(min, getNumOfInversions(arr2));
	    }
	    */
	    
	    // O(N)
	    long min = currentInversion;
	    for (int i = 0; i < n; i++) {
	    	currentInversion = currentInversion - arr1[i] + (n - 1 - arr1[i]);
	    	min = Math.min(min, currentInversion);
	    }
	    
	    out.print(min);
	    out.close();
	}
	
	public static void copy(int[] source, int[] target) {
		for (int i = 0; i < source.length; i++) {
			target[i] = source[i];
    	}
	}

	public static void convertArray(int[] arr1, int[] arr2) {
		Map<Integer, Integer> mp = new HashMap<>();
		for (int i = 0; i < arr2.length; i++) {
			mp.put(arr2[i], i);
		}
		
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = mp.get(arr1[i]);
		}
	}

	// Time complexity is O(NlogN), Space complexity is O(N)
	public static long getNumOfInversions(int[] arr) {
		int[] extraStorge = new int[arr.length];
		return mergeSort(arr, 0, arr.length - 1, extraStorge);
	}

	// Overload method for mergeSort
	private static long mergeSort(int[] arr, int startIndex, int endIndex, int[] extraStorge) {
		if (startIndex == endIndex) {
			return 0L;
		}
		
		int middleIndex = (endIndex - startIndex) / 2 + startIndex;
		long result = mergeSort(arr, startIndex, middleIndex, extraStorge);
		result += mergeSort(arr, middleIndex + 1, endIndex, extraStorge);
		
		int i = startIndex;
		int j = middleIndex + 1;
		int extraIndex = startIndex;
		while (i <= middleIndex && j <= endIndex) {
			if (arr[i] <= arr[j]) {
				// result += j - (middleIndex + 1);
				extraStorge[extraIndex] = arr[i];
				i++;
			} else {
				extraStorge[extraIndex] = arr[j];
				j++;
				result += middleIndex - i + 1;
			}
			extraIndex++;
		}
		
		while (i <= middleIndex) {
			// result += j - (middleIndex + 1);
			extraStorge[extraIndex] = arr[i];
			i++;
			extraIndex++;
		}
		
		while (j <= endIndex) {
			extraStorge[extraIndex] = arr[j];
			j++;
			extraIndex++;
		}
		
		for (int index = startIndex; index <= endIndex; index++) {
			arr[index] = extraStorge[index];
		}
		
		return result;
	}
}
