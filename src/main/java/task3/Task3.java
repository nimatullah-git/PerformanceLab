package task3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A class that reads values and tests data from JSON files, updates the test values with corresponding values from the values file,
 * and writes the updated data to a report JSON file.
 * Author: nimatullah
 */
public class Task3 {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("Please provide three arguments: values file, tests file, and report file.");
            return;
        }

        String valuesFileName = args[0];
        String testsFileName = args[1];
        String reportFileName = args[2];

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Read values.json
            Map<Integer, String> valuesMap = readValues(objectMapper, valuesFileName);

            // Read and update tests.json
            JsonNode testsNode = readTestsAndUpdateValues(objectMapper, testsFileName, valuesMap);

            // Write the updated data to report.json
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportFileName), testsNode);

            System.out.println("Report generated successfully: " + reportFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<Integer, String> readValues(ObjectMapper objectMapper, String valuesFileName) throws IOException {
        InputStream valuesStream = Task3.class.getClassLoader().getResourceAsStream(valuesFileName);
        if (valuesStream == null) {
            throw new IllegalArgumentException("File not found: " + valuesFileName);
        }
        JsonNode valuesNode = objectMapper.readTree(valuesStream);
        Map<Integer, String> valuesMap = new HashMap<>();
        for (JsonNode valueNode : valuesNode.get("values")) {
            int id = valueNode.get("id").asInt();
            String value = valueNode.get("value").asText();
            valuesMap.put(id, value);
        }
        return valuesMap;
    }

    private static JsonNode readTestsAndUpdateValues(ObjectMapper objectMapper, String testsFileName, Map<Integer, String> valuesMap) throws IOException {
        InputStream testsStream = Task3.class.getClassLoader().getResourceAsStream(testsFileName);
        if (testsStream == null) {
            throw new IllegalArgumentException("File not found: " + testsFileName);
        }
        JsonNode testsNode = objectMapper.readTree(testsStream);
        updateValues(testsNode.get("tests"), valuesMap);
        return testsNode;
    }

    private static void updateValues(JsonNode testsNode, Map<Integer, String> valuesMap) {
        if (testsNode.isArray()) {
            for (JsonNode testNode : testsNode) {
                int id = testNode.get("id").asInt();
                if (valuesMap.containsKey(id)) {
                    ((ObjectNode) testNode).put("value", valuesMap.get(id));
                }
                if (testNode.has("values")) {
                    updateValues(testNode.get("values"), valuesMap);
                }
            }
        }
    }
}
