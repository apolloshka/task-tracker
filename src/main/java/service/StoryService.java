package service;

import model.Story;
import storage.JsonStorage;

import java.io.IOException;
import java.util.List;

public class StoryService implements DeleteService<Story> {
    private final ElementManager<Story> manager;
    private JsonStorage<Story> storage;

    public StoryService(JsonStorage<Story> storage) {
        this.manager = new ElementManager<>(storage);
    }

    public void add(Story newStory) {
        manager.addElement(newStory);
    }

    public void update(int id, String newTitle, String newDescription) {
        manager.update(id, newTitle, newDescription);
    }

    public Story findById(int id) {
        return manager.findById(id);
    }

    public List<Story> findByTitle(String title) {
        return manager.findByTitle(title);
    }

    @Override
    public List<Story> getAll() {
        return manager.getAll();
    }

    @Override
    public void delete(int id) {
        manager.delete(id);
    }

    @Override
    public void save() throws IOException {
        manager.save();
    }

    public void load() throws IOException {
        manager.load();
    }
}
