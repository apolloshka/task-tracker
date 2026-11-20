package service;

import exception.DefectNotFoundException;
import exception.InvalidElementException;
import model.Defect;
import storage.JsonDefectStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefectService {
    private List<Defect> defects;
    private int id = 1;
    private JsonDefectStorage jsonDefectStorage;

    public DefectService(JsonDefectStorage jsonDefectStorage) {
        defects = new ArrayList<>();
        this.jsonDefectStorage = jsonDefectStorage;
    }

    public void addDefect(Defect newDefect) {
        if (newDefect.getTitle() == null || newDefect.getTitle().isBlank()) {
            throw new InvalidElementException("Название дефекта не может быть пустым");
        }
        newDefect.setId(id);
        id++;
        defects.add(newDefect);
    }

    public void deleteDefect(int id) {
        Defect defect = findDefectById(id);
        defects.remove(defect);
    }

    public void updateDefect(
            int id,
            String newTitle,
            String newDescription) {
        Defect defect = findDefectById(id);
        if (newTitle == null && newDescription == null) {
            throw new InvalidElementException("Не указаны данные для изменения");
        }
        if (newTitle != null && newTitle.isBlank()) {
            throw new InvalidElementException("Название дефекта не может быть пустым");
        }
        if (newDescription != null && newDescription.isBlank()) {
            throw new InvalidElementException("Описание дефекта не может быть пустым");
        }
        if (newTitle != null) {
            defect.setTitle(newTitle);
        }
        if (newDescription != null) {
            defect.setDescription(newDescription);
        }
    }

    public Defect findDefectById(int id) {
        for (Defect defect : defects) {
            if (defect.getId() == id) {
                return defect;
            }
        }
        throw new DefectNotFoundException(id);
    }

    public List<Defect> findDefectByTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new InvalidElementException("Название для поиска не может быть пустым");
        }
        List<Defect> foundDefects = new ArrayList<>();
        for (Defect defect : defects) {
            if (defect.getTitle().contains(title)) {
                foundDefects.add(defect);
            }
        }
        return foundDefects;
    }

    public List<Defect> getAllDefects() {
        return new ArrayList<>(defects);
    }

    public void saveDefectToJson(List<Defect> defects) throws IOException {
        jsonDefectStorage.saveDefects(defects);
    }

    public void getDefectFromJson(List<Defect> loadDefects) throws IOException {
        id = 1;
        for (Defect defect : loadDefects) {
            defects.add(defect);
            if (defect.getId() >= id) {
                id = defect.getId() + 1;
            }
        }
    }
}

