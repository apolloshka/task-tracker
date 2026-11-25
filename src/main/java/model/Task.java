package model;

import java.util.HashSet;
import java.util.Set;

public class Task extends Element implements Relationable {
    private final Set<Defect> defects = new HashSet<>();
    private final Set<Task> tasks = new HashSet<>();
    private final Set<Story> stories = new HashSet<>();

    public Task(String title, String description) {
        super(title, description);
    }

    public Task() {
    }

    public Set<Defect> getRelatedDefects() {
        return defects;
    }

    public Set<Task> getRelatedTasks() {
        return tasks;
    }

    public Set<Story> getRelatedStories() {
        return stories;
    }
}
