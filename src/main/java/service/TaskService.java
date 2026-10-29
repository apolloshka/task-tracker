
package service;

import model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskService {

    private final List<Task> tasks;
    private int id = 1;

    public TaskService() {
        tasks = new ArrayList<>();
    }

    public boolean addTask(Task newTask) {
        if (newTask.getTitle() == null || newTask.getTitle().isEmpty()) {
            return false;
        }

        newTask.setId(id);
        id++;
        tasks.add(newTask);

        return true;
    }

    public boolean deleteTask(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            if (task.getId() == id) {
                tasks.remove(i);
                return true;
            }
        }

        return false;
    }

    //TODO подумать как оповестить пользака что он меняет значение N на значение N
    public boolean updateTask(
            int id,
            String newTitle,
            String newDescription) {

        Task task = findTaskById(id);

        if (task == null) {
            return false;
        }

        if (newTitle == null && newDescription == null) {
            return false;
        }

        if (newTitle != null && newTitle.isEmpty()) {
            return false;
        }

        if (newDescription != null &&
                newDescription.isEmpty()) {
            return false;
        }

        if (newTitle != null) {
            task.setTitle(newTitle);
        }

        if (newDescription != null) {
            task.setDescription(newDescription);
        }

        return true;
    }

    public Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public List<Task> findTasksByTitle(String title) {
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
