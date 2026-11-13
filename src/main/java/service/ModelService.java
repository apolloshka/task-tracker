package service;

import enums.Models;

import java.util.ArrayList;
import java.util.List;

public class ModelService {
    public final List<Models> models;

    public ModelService() {
        models = new ArrayList<>();
    }

    public List<Models> getModels() {
        List<Models> models = new ArrayList<>();

        for (Models model : Models.values()) {
            models.add(model);
        }
        return models;
    }
}
