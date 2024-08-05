package adrian.com.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.List;

public class ConvertUtils {

    @SneakyThrows
    public static List<HashMap<String, String>> convertToHasMapList(String filePath) {
        JsonNode jsonNode = JsonUtils.mapJsonObjectFromFile(filePath);
        return JsonUtils.instantiateObjectMapper().readValue(jsonNode.traverse(), new TypeReference<>() {
        });
    }
}
