package model;

import java.util.HashSet;
import java.util.Set;

public class Story extends Element implements Relationable {
    private final Set<Task> tasks = new HashSet<>();
    private final Set<Defect> defects = new HashSet<>();
    private final Set<Feature> features = new HashSet<>();

    public Story(String title, String description) {
        super(title, description);
    }

    public Story() {
    }
    //TODO default-метод интерфейса

    public Set<Task> getRelatedTasks() {
        return tasks;
    }

    public Set<Defect> getRelatedDefects() {
        return defects;
    }

    public Set<Feature> getRelatedFeatures() {
        return features;
    }
}
