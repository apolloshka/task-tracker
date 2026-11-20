package model;

import java.util.ArrayList;
import java.util.List;

public class Feature extends Element {
    private List<Story> stories = new ArrayList<>();

    public Feature(String title, String description) {
        super(title, description);
    }

    public Feature() {
    }

    //TODO подумать над названием и раположением
    public void addStoryToRelation(Story story) {
        stories.add(story);
    }

    public List<Story> getRelatedStories() {
        return stories;
    }
}
