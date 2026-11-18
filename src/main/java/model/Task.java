package model;

import java.util.ArrayList;
import java.util.List;

public class Task extends Element {
    private List<Defect> defects = new ArrayList<>();
    private List<Task> tasks = new ArrayList<>();
    private List<Story> stories = new ArrayList<>();

    public Task(String title, String description) {
        super(title, description);
    }

    public Task() {
    }

    //TODO подумать над названием и раположением
    public void addDefectToRelation(Defect defect) {
        defects.add(defect);
    }

    public List<Defect> getRelatedDefects() {
        return defects;
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
