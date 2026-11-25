import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import exception.LoadRelationException;
import model.*;
import service.*;
import storage.JsonRelationStorage;
import storage.JsonStorage;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputService inputService = new InputService(scanner);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); //настройка mapper'а
        //TODO применима ли тут какая-нибудь ленивая инициализация? где должны создаваться эти объекты (не main?)
        ModelService modelService = new ModelService();
        JsonStorage<Task> jsonTaskStorage = new JsonStorage<>(mapper, "tasks.json", new TypeReference<List<Task>>() {
        });
        JsonStorage<Story> jsonStoryStorage = new JsonStorage<>(mapper, "stories.json", new TypeReference<List<Story>>() {
        });
        JsonStorage<Defect> jsonDefectStorage = new JsonStorage<>(mapper, "defects.json", new TypeReference<List<Defect>>() {
        });
        JsonStorage<Feature> jsonFeatureStorage = new JsonStorage<>(mapper, "features.json", new TypeReference<List<Feature>>() {
        });
        JsonRelationStorage jsonRelationStorage = new JsonRelationStorage(mapper);
        RelationService relationService = new RelationService(jsonRelationStorage);
        UniversalModelService<Task> taskService = new UniversalModelService<>(jsonTaskStorage);
        UniversalModelService<Story> storyService = new UniversalModelService<>(jsonStoryStorage);
        UniversalModelService<Defect> defectService = new UniversalModelService<>(jsonDefectStorage);
        UniversalModelService<Feature> featureService = new UniversalModelService<>(jsonFeatureStorage);
        PrintService printService = new PrintService(relationService);
        try {
            taskService.load();
        } catch (IOException e) {
            printService.printMessage("Не удалось загрузить задачи из файла");
        }
        try {
            storyService.load();
        } catch (IOException e) {
            printService.printMessage("Не удалось загрузить истории из файла");
        }
        try {
            defectService.load();
        } catch (IOException e) {
            printService.printMessage("Не удалось загрузить дефекты из файла");
        }
        try {
            featureService.load();
        } catch (IOException e) {
            printService.printMessage("Не удалось загрузить фичи из файла");
        }
        try {
            List<Relation> loadRelations = jsonRelationStorage.loadRelations();
            relationService.load(loadRelations);
        } catch (IOException e) {
            printService.printMessage("Не удалось загрузить связи из файла");
        }
        try {
            relationService.restoreFeatureStoryRelations(featureService, storyService);
        } catch (LoadRelationException message) {
            printService.printMessage("Не удалось восстановить связь фич с историями из файла");
        }
        try {
            relationService.restoreStoryTaskRelations(storyService, taskService);
        } catch (LoadRelationException message) {
            printService.printMessage("Не удалось восстановить связь историй с задачами из файла");
        }
        try {
            relationService.restoreStoryDefectRelations(storyService, defectService);
        } catch (LoadRelationException message) {
            printService.printMessage("Не удалось восстановить связь историй с дефектами из файла");
        }
        try {
            relationService.restoreParentTaskRelations(taskService, taskService);
        } catch (LoadRelationException message) {
            printService.printMessage("Не удалось восстановить связь родительской и дочерней задач из файла");
        }
        try {
            relationService.restoreBlockTaskRelations(taskService, taskService);
        } catch (LoadRelationException message) {
            printService.printMessage("Не удалось восстановить связь блокирующей и блокируемой задач из файла");
        }
        try {
            relationService.restoreRelatedToTaskRelations(taskService, taskService);
        } catch (LoadRelationException message) {
            printService.printMessage("Не удалось восстановить связь двух задач из файла");
        }
        try {
            relationService.restoreRelatedTaskRelations(taskService, defectService);
        } catch (LoadRelationException message) {
            printService.printMessage("Не удалось восстановить связь задачи с существующей по ней дефектом из файла");
        }
        try {
            relationService.restoreFixesTaskRelations(taskService, defectService);
        } catch (LoadRelationException message) {
            printService.printMessage("Не удалось восстановить связь задачи с фикс-дефектом из файла");
        }
        ConsoleService consoleService = new ConsoleService(inputService, printService, taskService, relationService, storyService, defectService, featureService);
        consoleService.run();
    }
}
