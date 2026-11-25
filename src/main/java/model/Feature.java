package model;

import java.util.HashSet;
import java.util.Set;

public class Feature extends Element implements Relationable {
    private final Set<Story> stories = new HashSet<>();

    public Feature(String title, String description) {
        super(title, description);
    }

    public Feature() {
    }

    public Set<Story> getRelatedStories() {
        return stories;
    }
}
