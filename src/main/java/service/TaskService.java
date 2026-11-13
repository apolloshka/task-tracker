
package service;

import exception.InvalidElementException;
import model.Task;
import exception.TaskNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private final List<Task> tasks;
    private int id = 1;

    public TaskService() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        if (newTask.getTitle() == null || newTask.getTitle().isBlank()) {
            throw new InvalidElementException("Название задачи не может быть пустым");
        }

        newTask.setId(id);
        id++;
        tasks.add(newTask);
    }

    public void deleteTask(int id) {
        Task task = findTaskById(id);
        tasks.remove(task);
    }

    //TODO подумать как оповестить пользака что он меняет значение N на значение N
    public void updateTask(
            int id,
            String newTitle,
            String newDescription) {

        Task task = findTaskById(id);

        if (newTitle == null && newDescription == null) {
            throw new InvalidElementException("Не указаны данные для изменения");
        }

        if (newTitle != null && newTitle.isBlank()) {
            throw new InvalidElementException("Название задачи не может быть пустым");
        }

        if (newDescription != null && newDescription.isBlank()) {
            throw new InvalidElementException("Описание задачи не может быть пустым");
        }

        if (newTitle != null) {
            task.setTitle(newTitle);
        }

        if (newDescription != null) {
            task.setDescription(newDescription);
        }
    }

    public Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        throw new TaskNotFoundException(id);
    }

    public List<Task> findTasksByTitle(String title) {

        if (title == null || title.isBlank()) {
            throw new InvalidElementException("Название для поиска не может быть пустым");
        }

        List<Task> foundTasks = new ArrayList<>();

        for (Task task : tasks) {

            if (task.getTitle().contains(title)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public void loadTasks(List<Task> loadedTasks) {
        id = 1;

        for (Task task : loadedTasks) {
            tasks.add(task);

            if (task.getId() >= id) {
                id = task.getId() + 1;
            }
        }
    }
}
