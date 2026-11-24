package model;

public class Defect extends Element {
    private final RelatedElements<Task> tasks = new RelatedElements<>();
    private final RelatedElements<Story> stories = new RelatedElements<>();

    public Defect(String title, String description) {
        super(title, description);
    }

    public Defect() {
    }

    public RelatedElements<Task> getRelatedTasks() {
        return tasks;
    }

    public RelatedElements<Story> getRelatedStories() {
        return stories;
    }
}
