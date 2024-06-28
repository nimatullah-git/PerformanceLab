package task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A class that reads circle data and points data from files,
 * calculates the distance of each point from the circle center,
 * and determines whether the point lies inside, outside, or on the circle.
 * Author: nimatullah
 */
public class Task2 {
    public static void main(String[] args) {
        // Variables to store circle data
        double centerX = 0, centerY = 0, radius = 0;

        // Read circle data
        try (BufferedReader circleReader = getResourceFileReader("circle_data.txt")) {
            String[] centerCoords = circleReader.readLine().trim().split("\\s+");
            if (centerCoords.length != 2) {
                throw new IOException("Invalid circle data format.");
            }
            centerX = Double.parseDouble(centerCoords[0]);
            centerY = Double.parseDouble(centerCoords[1]);
            radius = Double.parseDouble(circleReader.readLine().trim());
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading circle data: " + e.getMessage());
            return;
        }

        // Read points data
        try (BufferedReader pointsReader = getResourceFileReader("points.txt")) {
            String line;
            while ((line = pointsReader.readLine()) != null) {
                String[] coordinates = line.trim().split("\\s+");
                if (coordinates.length != 2) {
                    System.err.println("Invalid point data format.");
                    continue;
                }
                double x = Double.parseDouble(coordinates[0]);
                double y = Double.parseDouble(coordinates[1]);

                // Calculate distance from point to circle center
                double distance = Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2));

                // Determine the position of the point relative to the circle
                int position;
                if (Double.compare(distance, radius) == 0) {
                    position = 0; // Point is on the circle
                } else if (distance < radius) {
                    position = 1; // Point is inside the circle
                } else {
                    position = 2; // Point is outside the circle
                }

                // Print result
                System.out.println(position);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading points data: " + e.getMessage());
        }
    }

    /**
     * Utility method to read a file from the resources folder.
     *
     * @param fileName The name of the file to read.
     * @return A BufferedReader for the file.
     * @throws IOException If the file cannot be found or read.
     */
    private static BufferedReader getResourceFileReader(String fileName) throws IOException {
        InputStream inputStream = Task2.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IOException("File not found: " + fileName);
        }
        return new BufferedReader(new InputStreamReader(inputStream));
    }
}
