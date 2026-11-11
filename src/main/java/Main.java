
import model.Task;
import service.TaskService;
import service.PrintService;
import service.FileService;
import service.InputService;
import service.ConsoleService;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        InputService inputService = new InputService(scanner);

        TaskService taskService = new TaskService();
        PrintService printService = new PrintService();
        FileService fileService = new FileService();

        try {
            List<Task> loadedTasks = fileService.loadTasks();
            taskService.loadTasks(loadedTasks);
        } catch (IOException e) {
            printService.printMessage("Не удалось загрузить задачи из файла");

        }

        ConsoleService consoleService = new ConsoleService(inputService, printService, taskService, fileService);
        consoleService.run();
    }
}
