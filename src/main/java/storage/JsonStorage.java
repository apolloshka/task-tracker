package storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Element;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonStorage<T extends Element> {
    private ObjectMapper mapper;
    private final String fileName;
    private final TypeReference<List<T>> typeReference;

    public JsonStorage(ObjectMapper mapper, String fileName, TypeReference<List<T>> typeReference) {
        this.mapper = mapper;
        this.fileName = fileName;
        this.typeReference = typeReference;
    }

    public void save(List<T> elements) throws IOException {
        ArrayNode array = mapper.createArrayNode();
        for (T element : elements) {
            ObjectNode object = mapper.createObjectNode();
            object.put("id", element.getId());
            object.put("title", element.getTitle());
            object.put("description", element.getDescription());
            array.add(object);
        }
        mapper.writeValue(new File(fileName), array);
    }

    public List<T> load() throws IOException {
        return mapper.readValue(new File(fileName), typeReference);
    }
}
