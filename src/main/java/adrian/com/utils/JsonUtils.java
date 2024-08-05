package adrian.com.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public static JsonNode mapJsonObjectFromFile(String filePath) {
        Reader reader = null;
        JsonNode parser = null;
        try {
            reader = Files.newBufferedReader(Paths.get(filePath));
            parser = objectMapper.readTree(reader);
        } catch (Exception e) {
            System.out.println("File: " + filePath + " is not correct json, can not be parsed");
        }
        reader.close();
        return parser;
    }

    public static ObjectMapper instantiateObjectMapper() {
        return new ObjectMapper().configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
