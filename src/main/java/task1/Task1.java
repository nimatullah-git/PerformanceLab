package task1;

import java.util.Arrays;

/**
 * A class that demonstrates array manipulation based on user input.
 * The array is filled with values from 1 to n, and prints elements based on a given interval m.
 * Author: nimatullah
 */
public class Task1 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please provide the array size (n) and interval (m) as command-line arguments.");
            return;
        }

        int n = Integer.parseInt(args[0]); // Number of elements in the array
        int m = Integer.parseInt(args[1]); // Interval length

        // Create and initialize array
        int[] array = new int[n];
        Arrays.setAll(array, i -> i + 1); // Fill array with values from 1 to n

        // Print array elements based on the interval
        int current = 0;
        do {
            System.out.print(array[current] + " "); // Print current element
            current = (current + m - 1) % n; // Calculate next index
        } while (current != 0); // Until we return to the beginning
    }
}
