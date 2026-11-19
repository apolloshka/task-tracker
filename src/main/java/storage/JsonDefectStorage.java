package storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Defect;
import model.Story;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonDefectStorage {

    private ObjectMapper mapper;

    public JsonDefectStorage(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public void saveDefects(List<Defect> defects) throws IOException {
        mapper.writeValue(new File("defects.json"), defects);
    }

    public List<Defect> loadDefects() throws IOException {
        return mapper.readValue(new File("defects.json"), new TypeReference<List<Defect>>() {
        });
    }
}
