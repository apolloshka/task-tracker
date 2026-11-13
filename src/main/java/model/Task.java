package model;

import java.util.List;

public class Task extends Element {

    private List<Defect> defects;
    private List<Task> tasks;

    public Task(String title, String description) {
        super(title, description);
        this.defects = defects;
        this.tasks = tasks;
    }
}
