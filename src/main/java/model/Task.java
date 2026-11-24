package model;

import java.util.HashSet;
import java.util.Set;

public class Task extends Element {
    private final Set<Defect> defects = new HashSet<>();
    private final Set<Task> tasks = new HashSet<>();
    private final Set<Story> stories = new HashSet<>();

    public Task(String title, String description) {
        super(title, description);
    }

    public Task() {
    }

    // TODO Вариант обобщенного метода с дженериками в родителе - дубли остаются, кода не меньше
    public void addDefectToRelation(Defect defect) {
        addRelateElement(defects, defect);
    }

    public Set<Defect> getRelatedDefects() {
        return defects;
    }

    public void addTaskToRelation(Task task) {
        addRelateElement(tasks, task);
    }

    public Set<Task> getRelatedTasks() {
        return tasks;
    }

    public void addStoryToRelation(Story story) {
        addRelateElement(stories, story);
    }

    public Set<Story> getRelatedStories() {
        return stories;
    }
}
