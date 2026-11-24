package service;

import exception.InvalidElementException;
import model.Element;
import storage.JsonStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public abstract class AbstractElementService<T extends Element> implements DeleteService<T> {
    protected final List<T> elements = new ArrayList<>();
    private int id = 1;
    private static final ResourceBundle messages = ResourceBundle.getBundle("messages");
    protected final JsonStorage<T> storage;

    protected AbstractElementService(JsonStorage<T> storage) {
        this.storage = storage;
    }

    public void add(T element) {
        if (element.getTitle() == null || element.getTitle().isBlank()) {
            throw new InvalidElementException(messages.getString("element.title.empty"));
        }
        element.setId(id);
        id++;
        elements.add(element);
    }

    public void delete(int id) {
        T element = findById(id);
        elements.remove(element);
    }

    //TODO подумать как оповестить пользака что он меняет значение N на значение N
    public void update(
            int id,
            String newTitle,
            String newDescription) {
        T element = findById(id);
        validateUpdate(newTitle, newDescription);
        if (newTitle != null) {
            element.setTitle(newTitle);
        }
        if (newDescription != null) {
            element.setDescription(newDescription);
        }
    }

    private void validateUpdate(String newTitle, String newDescription) {
        if (newTitle == null && newDescription == null) {
            throw new InvalidElementException(messages.getString("element.update.empty"));
        }
        if (newTitle != null && newTitle.isBlank()) {
            throw new InvalidElementException(messages.getString("element.title.empty"));
        }
        if (newDescription != null && newDescription.isBlank()) {
            throw new InvalidElementException(messages.getString("element.description.empty"));
        }
    }

    //TODO подумать над исключениями - как определять, какое исключение по модели надо выбросить?
    public T findById(int id) {
        for (T element : elements) {
            if (element.getId() == id) {
                return element;
            }
        }
        throw new InvalidElementException("Элемент с id = " + id + " не найден");
    }

    public List<T> findByTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new InvalidElementException(messages.getString("element.search.empty"));
        }
        List<T> foundElements = new ArrayList<>();
        for (T element : elements) {
            if (element.getTitle().contains(title)) {
                foundElements.add(element);
            }
        }
        return foundElements;
    }

    public List<T> getAll() {
        return new ArrayList<>(elements);
    }

    public void load() throws IOException {
        List<T> loadedElements = storage.load();
        int nextId = 1;
        for (T element : loadedElements) {
            if (element.getId() >= nextId) {
                nextId = element.getId() + 1;
            }
        }
        elements.addAll(loadedElements);
        id = nextId;
    }

    public void save() throws IOException {
        storage.save(elements);
    }
}


