package model;

import java.util.ArrayList;
import java.util.List;

public class Story extends Element {
    //TODO хочу сохранять вложенные в истории таски и дефекты
    private List<Task> tasks = new ArrayList<>();
    private List<Defect> defects = new ArrayList<>();
    private List<Feature> features = new ArrayList<>();

    public Story(String title, String description) {
        super(title, description);
    }

    public Story() {
    }

    //TODO подумать над названием и раположением
    public void addTaskToRelation(Task task) {
        tasks.add(task);
    }

    public List<Task> getRelatedTasks() {
        return tasks;
    }

    //TODO подумать над названием и раположением
    public void addDefectToRelation(Defect defect) {
        defects.add(defect);
    }

    public List<Defect> getRelatedDefects() {
        return defects;
    }

    //TODO подумать над названием и раположением
    public void addFeatureToRelation(Feature feature) {
        features.add(feature);
    }

    public List<Feature> getRelatedFeatures() {
        return features;
    }
}
