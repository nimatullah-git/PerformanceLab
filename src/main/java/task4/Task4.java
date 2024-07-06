package task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class that reads an array of integers from a file and calculates the minimum number of moves to make all elements equal.
 * Author: nimatullah
 */
public class Task4 {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Please provide the input file name.");
            return;
        }

        String inputFileName = args[0];

        try {
            int[] nums = readArrayFromFile(inputFileName);
            int minMoves = minMovesToEqualElements(nums);
            System.out.println(minMoves);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    /**
     * Reads an array of integers from a file in the resources folder.
     *
     * @param fileName The name of the file to read.
     * @return An array of integers.
     * @throws IOException If the file cannot be found or read.
     */
    private static int[] readArrayFromFile(String fileName) throws IOException {
        InputStream inputStream = Task4.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IOException("File not found: " + fileName);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<Integer> numbers = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            numbers.addAll(Arrays.asList(line.split("\\s+")).stream().map(Integer::parseInt).toList());
        }

        return numbers.stream().mapToInt(i -> i).toArray();
    }

    /**
     * Calculates the minimum number of moves to make all elements in the array equal.
     *
     * @param nums An array of integers.
     * @return The minimum number of moves.
     */
    private static int minMovesToEqualElements(int[] nums) {
        Arrays.sort(nums);
        int median = nums[nums.length / 2];
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        return moves;
    }
}
