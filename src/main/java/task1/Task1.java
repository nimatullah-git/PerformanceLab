package task1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * A class that demonstrates array manipulation based on user input.
 * The array is filled with values from 1 to n, and prints elements based on a given interval m.
 * Author: nimatullah
 */
public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input data from user
        int n = scanner.nextInt();  // Number of elements in the array
        int m = scanner.nextInt();  // Interval length

        // Create and initialize array
        int[] array = new int[n];
        Arrays.setAll(array, i -> i + 1);  // Fill array with values from 1 to n

        // Print array elements based on the interval
        int current = 0;
        do {
            System.out.print(array[current] + " ");  // Print current element
            current = (current + m - 1) % n;  // Calculate next index
        } while (current != 0);  // Until we return to the beginning

        scanner.close();
    }
}
