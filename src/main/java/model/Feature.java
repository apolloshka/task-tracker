package model;

import java.util.HashSet;
import java.util.Set;

public class Feature extends Element implements RelatedElement<Story> {
    private final Set<Story> stories = new HashSet<>();

    public Feature(String title, String description) {
        super(title, description);
    }

    public Feature() {
    }

    //TODO интерфейс с дженериками
    @Override
    public void addRelatedElement(Story story) {
        stories.add(story);
    }

    public Set<Story> getRelatedStories() {
        return stories;
    }
}
