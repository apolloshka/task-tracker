package storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Feature;
import model.Relation;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonFeatureStorage {
    private ObjectMapper mapper;

    public JsonFeatureStorage(ObjectMapper mapper) {

        this.mapper = mapper;
    }

    public void saveFeatures(List<Feature> features) throws IOException {
        mapper.writeValue(new File("features.json"), features);
    }

    public List<Feature> loadFeatures() throws IOException {
        return mapper.readValue(new File("features.json"), new TypeReference<List<Feature>>() {
        });
    }
}
