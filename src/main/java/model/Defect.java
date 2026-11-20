package model;

import java.util.ArrayList;
import java.util.List;

public class Defect extends Element {
    private List<Task> tasks = new ArrayList<>();
    private List<Story> stories = new ArrayList<>();

    public Defect(String title, String description) {
        super(title, description);
    }

    public Defect() {
    }

    //TODO подумать над названием и раположением
    public void addTaskToRelation(Task task) {
        tasks.add(task);
    }

    public List<Task> getRelatedTasks() {
        return tasks;
    }

    //TODO подумать над названием и раположением
    public void addStoryToRelation(Story story) {
        stories.add(story);
    }

    public List<Story> getRelatedStories() {
        return stories;
    }
}
