
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.*;
import service.*;
import storage.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        InputService inputService = new InputService(scanner);
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); //настройка mapper'а


        //TODO применима ли тут какая-нибудь ленивая инициализация? где должны создаваться эти объекты (не main?)
        PrintService printService = new PrintService();
//        FileService fileService = new FileService();
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


        try {
            List<Task> loadTasks = jsonTaskStorage.loadTasks();
            taskService.getTaskFromJson(loadTasks);
            List<Story> loadStories = jsonStoryStorage.loadStories();
            storyService.getStoryFromJson(loadStories);
            List<Defect> loadDefects = jsonDefectStorage.loadDefects();
            defectService.getDefectFromJson(loadDefects);
            List<Feature> loadFeatures = jsonFeatureStorage.loadFeatures();
            featureService.getFeatureFromJson(loadFeatures);
            List<Relation> loadRelations = jsonRelationStorage.loadRelations();
            relationService.getRelationFromJson(loadRelations);


        } catch (IOException e) {
            printService.printMessage("Не удалось загрузить элементы из файла");
        }

        ConsoleService consoleService = new ConsoleService(inputService, printService, taskService, relationService, storyService, defectService, featureService, modelService);
        consoleService.run();
    }
}
