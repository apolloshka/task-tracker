package storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Story;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonStoryStorage {
    private ObjectMapper mapper;

    public JsonStoryStorage(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public void saveStories(List<Story> stories) throws IOException {
        mapper.writeValue(new File("stories.json"), stories);
    }

    public List<Story> loadStories() throws IOException {
        return mapper.readValue(new File("stories.json"), new TypeReference<List<Story>>() {
        });
    }
}
