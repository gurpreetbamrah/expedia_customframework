package api;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import runner.FrameWorkLauncher;

import java.util.ArrayList;
import java.util.List;


public class JsonHandler {

    public static List<String> foundValues;

    public static List<String> getValueFromJson(String field) {
        ObjectMapper mapper = new ObjectMapper();
        foundValues = new ArrayList<String>();
        try {
            JsonNode rootNode = mapper.readTree(RequestBuilder.httpResponse);
            foundValues = rootNode.findValuesAsText(field);
        } catch (Exception e) {
        }
        if (foundValues != null) {
            FrameWorkLauncher.result.add("getValueFromJson,Pass");
        } else {
            FrameWorkLauncher.result.add("getValueFromJson,Fail");
        }
        return foundValues;
    }


}






