package model;

import java.util.Collection;

//TODO пробное название
public interface Relationable {
    default <T extends Element> void addRelatedElement(Collection<T> collection, T element) {
        collection.add(element);
    }
}
