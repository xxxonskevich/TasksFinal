package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Usage: java Main <valuesPath> <testsPath> <reportPath>");
            return;
        }

        String valuesPath = args[0];
        String testsPath = args[1];
        String reportPath = args[2];

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode testsNode = objectMapper.readTree(new File(valuesPath));
            JsonNode valuesNode = objectMapper.readTree(new File(testsPath));

            processTests(testsNode, valuesNode);

            // Сохранение обновленной структуры в report.json
            objectMapper.writeValue(new File(reportPath), testsNode);
            System.out.println("Report successfully generated!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processTests(JsonNode rootNode, JsonNode valuesNode) {
        if (rootNode.isObject()) {

            JsonNode testsArrayNode = rootNode.get("tests");
            if (testsArrayNode != null && testsArrayNode.isArray()) {
                Iterator<JsonNode> testIterator = testsArrayNode.elements();
                while (testIterator.hasNext()) {
                    JsonNode testNode = testIterator.next();
                    getValueTest(testNode, valuesNode);
                }
            }
        } else if (rootNode.isArray()) {
            Iterator<JsonNode> testIterator = rootNode.elements();
            while (testIterator.hasNext()) {
                JsonNode testNode = testIterator.next();
                getValueTest(testNode, valuesNode);
            }
        }
    }


    private static void getValueTest(JsonNode testNode, JsonNode valuesNode) {
        int testId = testNode.get("id").asInt();
        JsonNode resultNode = findResultNode(testId, valuesNode);

        if (resultNode != null) {
            String testResult = resultNode.get("value").asText();
            ((ObjectNode) testNode).put("value", testResult);

            JsonNode valuesArray = testNode.get("values");
            if (valuesArray != null) {
                processTests(valuesArray, valuesNode);
            }
        } else {
            JsonNode valuesArray = testNode.get("values");
            if (valuesArray != null) {
                processTests(valuesArray, valuesNode);
            }
        }
    }

    private static JsonNode findResultNode(int testId, JsonNode valuesNode) {
        JsonNode valuesArrayNode = valuesNode.get("values");

        if (valuesArrayNode != null && valuesArrayNode.isArray()) {
            Iterator<JsonNode> iterator = valuesArrayNode.elements();
            while (iterator.hasNext()) {
                JsonNode node = iterator.next();
                if (node.get("id").asInt() == testId) {
                    return node;
                }
            }
        }
        return null;
    }

}
