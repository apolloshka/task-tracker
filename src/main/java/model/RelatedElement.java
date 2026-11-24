package model;

public interface RelatedElement<T extends Element> {
    void addRelatedElement(T element);
}