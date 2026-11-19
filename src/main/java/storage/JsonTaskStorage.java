package storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Story;
import model.Task;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonTaskStorage {

    private ObjectMapper mapper;

    public JsonTaskStorage(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public void saveTasks(List<Task> tasks) throws IOException {
        mapper.writeValue(new File("tasks.json"), tasks);
    }

    public List<Task> loadTasks() throws IOException {
        return mapper.readValue(new File("tasks.json"), new TypeReference<List<Task>>() {
        });
    }
}

