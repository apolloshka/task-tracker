package service;

import exception.DefectNotFoundException;
import exception.FeatureNotFoundException;
import exception.InvalidElementException;
import model.Defect;
import model.Feature;

import java.util.ArrayList;
import java.util.List;

public class FeatureService {
    private List<Feature> features;
    private int id = 1;

    public FeatureService() {
        features = new ArrayList<>();
    }

    public void addFeature(Feature newFeature) {
        if (newFeature.getTitle() == null || newFeature.getTitle().isBlank()) {
            throw new InvalidElementException("Название дефекта не может быть пустым");
        }

        newFeature.setId(id);
        id++;
        features.add(newFeature);
    }

    public void deleteFeature(int id) {
        Feature feature = findFeatureById(id);
        features.remove(feature);
    }


    public void updateFeature(
            int id,
            String newTitle,
            String newDescription) {

        Feature defect = findFeatureById(id);

        if (newTitle == null && newDescription == null) {
            throw new InvalidElementException("Не указаны данные для изменения");
        }

        if (newTitle != null && newTitle.isBlank()) {
            throw new InvalidElementException("Название фичи не может быть пустым");
        }

        if (newDescription != null && newDescription.isBlank()) {
            throw new InvalidElementException("Описание фичи не может быть пустым");
        }

        if (newTitle != null) {
            defect.setTitle(newTitle);
        }

        if (newDescription != null) {
            defect.setDescription(newDescription);
        }
    }

    public Feature findFeatureById(int id) {
        for (Feature feature : features) {
            if (feature.getId() == id) {
                return feature;
            }
        }
        throw new FeatureNotFoundException(id);
    }

    public List<Feature> findFeatureByTitle(String title) {

        if (title == null || title.isBlank()) {
            throw new InvalidElementException("Название для поиска не может быть пустым");
        }

        List<Feature> foundDefects = new ArrayList<>();

        for (Feature feature : features) {

            if (feature.getTitle().contains(title)) {
                foundDefects.add(feature);
            }
        }
        return foundDefects;
    }

    public List<Feature> getAllFeatures() {
        return new ArrayList<>(features);
    }
}
