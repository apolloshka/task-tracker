package service;

import model.Defect;
import storage.JsonStorage;

import java.util.ArrayList;
import java.util.List;

public class DefectService implements ElementService<Defect> {
    private final List<Defect> defects;
    private int id = 1;
    private final JsonStorage<Defect> storage;

    public DefectService(JsonStorage<Defect> storage) {
        defects = new ArrayList<>();
        this.storage = storage;
    }

    @Override
    public void setNextId(int id) {
        this.id = id;
    }

    @Override
    public List<Defect> getAll() {
        return defects;
    }

    @Override
    public int NextId() {
        return id++;
    }

    @Override
    public JsonStorage<Defect> getStorage() {
        return storage;
    }
}

