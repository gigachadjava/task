
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;


public class Task3 {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java Main <values.json> <tests.json> <report.json>");
            System.exit(1);
        }

        String valuesPath = args[0];
        String testsPath = args[1];
        String reportPath = args[2];

        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode valuesNode = mapper.readTree(new File(valuesPath));
            JsonNode testsNode = mapper.readTree(new File(testsPath));
            updateValues(valuesNode, testsNode);
            ObjectWriter objectWriter = mapper.writer().with(SerializationFeature.INDENT_OUTPUT);
            objectWriter.writeValue(new File(reportPath), testsNode);
            System.out.println(testsNode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        

    }

    private static void updateValues(JsonNode nodeValues, JsonNode nodeTests){
        if (nodeValues.isObject()) {
            ObjectNode objectNode = (ObjectNode) nodeValues;
            if (objectNode.has("id")) {
                updateValues(nodeTests, objectNode.get("id").asInt(), objectNode.get("value").textValue());
            }
            if (objectNode.has("values")) {
                JsonNode values = objectNode.get("values");
                for (JsonNode jsonNode : values) {
                    updateValues(jsonNode, nodeTests);
                }
            }
        }
    }
    private static void updateValues(JsonNode node1, int idV, String value) {
        if (node1.isObject()) {
            ObjectNode objectNode1 = (ObjectNode) node1;
            if (objectNode1.has("id")) {
                int id = objectNode1.get("id").asInt();
                if (id == idV) {
                    objectNode1.put("value", value);
                }
            }
            if (objectNode1.has("tests")) {
                JsonNode tests = objectNode1.get("tests");
                for (JsonNode jsonNode1 : tests) {
                    updateValues(jsonNode1, idV, value);
                }
            } else if (objectNode1.has("values")) {
                JsonNode values = objectNode1.get("values");
                for (JsonNode jsonNode: values) {
                    updateValues(jsonNode, idV, value);
                }
            }

        }

    }
}
