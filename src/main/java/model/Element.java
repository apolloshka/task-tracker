package model;

import java.util.Collection;

public abstract class Element {
    private int id;
    private String title;
    private String description;

    public Element(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Element() {
    }

    //TODO обобшенный метод родительского класса - для разных типов
    protected <T extends Element> void addRelateElement(Collection<T> collection, T element) {
        collection.add(element);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
