package storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Relation;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonRelationStorage {
    private ObjectMapper mapper;

    public JsonRelationStorage(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public void saveRelations(List<Relation> relations) throws IOException {
        mapper.writeValue(new File("relations.json"), relations);
    }

    public List<Relation> loadRelations() throws IOException {
        return mapper.readValue(new File("relations.json"), new TypeReference<List<Relation>>() {
        });
    }
}
