package model;

import java.util.HashSet;
import java.util.Set;

public class RelatedElements<T extends Element> {
    private final Set<T> elements = new HashSet<>();

    public void add(T element) {
        elements.add(element);
    }

    public Set<T> getAll() {
        return elements;
    }
}
