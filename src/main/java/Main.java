
import model.Task;
import service.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        InputService inputService = new InputService(scanner);

        //TODO применима ли тут какая-нибудь ленивая инициализация? где должны создаваться эти объекты (не main?)
        TaskService taskService = new TaskService();
        PrintService printService = new PrintService();
        FileService fileService = new FileService();
        RelationService relationService = new RelationService();
        StoryService storyService = new StoryService();
        DefectService defectService = new DefectService();
        FeatureService featureService = new FeatureService();
        ModelService modelService = new ModelService();

        try {
            List<Task> loadedTasks = fileService.loadTasks();
            taskService.loadTasks(loadedTasks);
        } catch (IOException e) {
            printService.printMessage("Не удалось загрузить задачи из файла");

        }

        ConsoleService consoleService = new ConsoleService(inputService, printService, taskService, fileService, relationService, storyService, defectService, featureService, modelService);
        consoleService.run();
    }
}
