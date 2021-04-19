/*
 * GradesByLetter2DUneven.java
 * by <name>
 * date <date>
 * period <period number>
 */

import java.util.Scanner;
import java.util.Arrays;

public class GradesByLetter2DUneven {
   
   public static void main(String[] args){
      // Create the 2D array without knowing how many entries each row 
      // is to have. Each row is to contain the scores for that grade.
      // Row 0 is for A, grades > 90
      // Row 1 is for B, grades > 80
      // Row 2 is for C, grades > 70
      // Row 4 is for D, grades > 60
      // Row 5 is for N, grades <= 60
      
	  // Part 1: complete the following declaration
      int[][]gradeDistribution = new int[5][1];
      
      // get how many total grades are to be entered
      Scanner kbReader = new Scanner(System.in);
      System.out.print("How many grades will you enter? ");
      int numberOfGrades = kbReader.nextInt();
      
      // Part 2: Create the 1D array to hold the grades as they are entered.
      int[] rawGrades = new int[numberOfGrades];
	  
      // Create the variables to hold how many of each grade
      int numberOfA = 0;
      int numberOfB = 0;
      int numberOfC = 0;
      int numberOfD = 0;
      int numberOfN = 0;
      
      // collect the grades and update the counters. 
	  // 91 and above is an A
	  // 81 to 90 is a B
	  // 71 to 80 is a C
	  // 61 to 70 is a D
	  // 60 and below is an N
	  
	  // Part 3: complete the if/else inside this loop
      for(int i = 0; i < numberOfGrades; i++){
         System.out.print("Enter grade number " + (i + 1) + ": ");
         rawGrades[i] = kbReader.nextInt();
         if(rawGrades[i] > 90){
            numberOfA++;
         } else if (rawGrades[i] > 80) {
            numberOfB++;
         } else if (rawGrades[i] > 70) {
            numberOfC++;
         } else if (rawGrades[i] > 60) {
            numberOfD++;
         } else {
            numberOfN++;
         }
      }
      
      System.out.println("Thank you. ");
      
      // create the array for each row of gradeDistribution
	  // Part 4: Provide the missing statements
      gradeDistribution[0] = new int[numberOfA];
      gradeDistribution[1] = new int[numberOfB];
      gradeDistribution[2] = new int[numberOfC];
      gradeDistribution[3] = new int[numberOfD];
      gradeDistribution[4] = new int[numberOfN];
      
      
      // need counters for each row
      int counterA = 0;
      int counterB = 0;
      int counterC = 0;
      int counterD = 0;
      int counterN = 0;
      
      // assign the values to the 2D array
	  // Part 5: complete the if/else inside the for loop
      for(int i = 0; i < rawGrades.length; i++){
         if(rawGrades[i] > 90) {
            gradeDistribution[0][counterA++] = rawGrades[i];
         } else if (rawGrades[i] > 80) {
            gradeDistribution[1][counterB++] = rawGrades[i];
         } else if (rawGrades[i] > 70) {
            gradeDistribution[2][counterC++] = rawGrades[i];
         } else if (rawGrades[i] > 60) {
            gradeDistribution[3][counterD++] = rawGrades[i];
         } else if (rawGrades[i] > 60) {
            gradeDistribution[4][counterN++] = rawGrades[i];
         }
      }
      
      // Print results
	  // Part 6: print the contents of each row of gradeDistribution
      System.out.println("A grades: " + Arrays.toString(gradeDistribution[0]));
      System.out.println("B grades: " + Arrays.toString(gradeDistribution[1]));
      System.out.println("C grades: " + Arrays.toString(gradeDistribution[2]));
      System.out.println("D grades: " + Arrays.toString(gradeDistribution[3]));
      System.out.println("N grades: " + Arrays.toString(gradeDistribution[4]));
      
      // Calculate average
	  // Part 7: using a nested for loop calculate and print the
	  // average number grade followed by its letter.
      int total = 0;
      for(int row = 0; row < gradeDistribution.length; row++){
         for(int col = 0; col < gradeDistribution[row].length; col++){
            total += gradeDistribution[row][col];
         }
      }
      
      System.out.print("Average number grade is " + (total/gradeDistribution.length));
	  
   }
   

}
