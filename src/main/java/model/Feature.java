package model;

import java.util.List;

public class Feature extends Element {

    private List<Story> stories;

    public Feature(String title, String description) {
        super(title, description);
        this.stories = stories;
    }
}
