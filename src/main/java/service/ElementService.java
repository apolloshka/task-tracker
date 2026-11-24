package service;

import exception.InvalidElementException;
import model.Element;
import storage.JsonStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public interface ElementService<T extends Element> extends DeleteService<T>{

    JsonStorage<T> getStorage();

    void setNextId(int id);

    int NextId();

    ResourceBundle messages = ResourceBundle.getBundle("messages");

    default void addElement(T element) {
        if (element.getTitle() == null || element.getTitle().isBlank()) {
            throw new InvalidElementException("Название дефекта не может быть пустым");
        }
        element.setId(NextId());
        getAll().add(element);
    }

    default void delete(int id) {
        T element = findById(id);
        getAll().remove(element);
    }

    default void updateElement(int id, String newTitle, String newDescription) {
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
    default T findById(int id) {
        for (T element : getAll()) {
            if (element.getId() == id) {
                return element;
            }
        }
        throw new InvalidElementException("Элемент с id = " + id + " не найден");
    }

    default List<T> findByTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new InvalidElementException(messages.getString("element.title.empty"));
        }
        List<T> foundElements = new ArrayList<>();
        for (T element : getAll()) {
            if (element.getTitle().contains(title)) {
                foundElements.add(element);
            }
        }
        return foundElements;
    }

    default List<T> getAll() {
        return new ArrayList<>(getAll());
    }

    default void load() throws IOException {
        List<T> loadedElements = getStorage().load();
        int nextId = 1;
        for (T element : loadedElements) {
            if (element.getId() >= nextId) {
                nextId = element.getId() + 1;
            }
        }
        getAll().addAll(loadedElements);
        setNextId(nextId);
    }

    default void save() throws IOException {
        getStorage().save(getAll());
    }
}
