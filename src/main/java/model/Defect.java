package model;

import java.util.List;

public class Defect extends Element {

    private List<Task> tasks;
    private List<Story> stories;

    public Defect(String title, String description) {
        super(title, description);
        this.tasks = tasks;
        this.stories = stories;
    }
}
