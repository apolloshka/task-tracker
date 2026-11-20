import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import exception.LoadRelationException;
import model.*;
import service.*;
import storage.*;

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
        JsonTaskStorage jsonTaskStorage = new JsonTaskStorage(mapper);
        JsonStoryStorage jsonStoryStorage = new JsonStoryStorage(mapper);
        JsonDefectStorage jsonDefectStorage = new JsonDefectStorage(mapper);
        JsonFeatureStorage jsonFeatureStorage = new JsonFeatureStorage(mapper);
        JsonRelationStorage jsonRelationStorage = new JsonRelationStorage(mapper);
        TaskService taskService = new TaskService(jsonTaskStorage);
        StoryService storyService = new StoryService(jsonStoryStorage);
        DefectService defectService = new DefectService(jsonDefectStorage);
        RelationService relationService = new RelationService(jsonRelationStorage);
        FeatureService featureService = new FeatureService(jsonFeatureStorage);
        PrintService printService = new PrintService(taskService, storyService, relationService);
        try {
            List<Task> loadTasks = jsonTaskStorage.loadTasks();
            taskService.getTaskFromJson(loadTasks);
        } catch (IOException e) {
            printService.printMessage("Не удалось загрузить задачи из файла");
        }
        try {
            List<Story> loadStories = jsonStoryStorage.loadStories();
            storyService.getStoryFromJson(loadStories);
        } catch (IOException e) {
            printService.printMessage("Не удалось загрузить истории из файла");
        }
        try {
            List<Defect> loadDefects = jsonDefectStorage.loadDefects();
            defectService.getDefectFromJson(loadDefects);
        } catch (IOException e) {
            printService.printMessage("Не удалось загрузить дефекты из файла");
        }
        try {
            List<Feature> loadFeatures = jsonFeatureStorage.loadFeatures();
            featureService.getFeatureFromJson(loadFeatures);
        } catch (IOException e) {
            printService.printMessage("Не удалось загрузить фичи из файла");
        }
        try {
            List<Relation> loadRelations = jsonRelationStorage.loadRelations();
            relationService.getRelationFromJson(loadRelations);
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
            relationService.restoreRelatedTaskRelations(taskService, taskService);
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
        ConsoleService consoleService = new ConsoleService(inputService, printService, taskService, relationService, storyService, defectService, featureService, modelService);
        consoleService.run();
    }
}
