package model;

import java.util.ArrayList;
import java.util.List;

public class Story extends Element {

    private List<Task> tasks;
    private List<Defect> defects;

    public Story(String title, String description) {
        super(title, description);
        this.tasks = tasks;
        this.defects = defects;
    }
}
