package service;

import model.Task;
import storage.JsonStorage;

public class TaskService extends AbstractElementService<Task> {
    public TaskService(JsonStorage<Task> storage) {
        super(storage);
    }
}
