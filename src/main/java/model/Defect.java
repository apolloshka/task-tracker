package model;

import java.util.HashSet;
import java.util.Set;

public class Defect extends Element implements Relationable {
    private final Set<Task> tasks = new HashSet<>();
    private final Set<Story> stories = new HashSet<>();

    public Defect(String title, String description) {
        super(title, description);
    }

    public Defect() {
    }

    public Set<Task> getRelatedTasks() {
        return tasks;
    }

    public Set<Story> getRelatedStories() {
        return stories;
    }
}
