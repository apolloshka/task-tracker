package service;

import model.Task;

import java.io.IOException;
import java.util.List;

public class ConsoleService {
    private final InputService inputService;
    private final PrintService printService;
    private final TaskService taskService;
    private final FileService fileService;

    public ConsoleService(InputService inputService,
                          PrintService printService,
                          TaskService taskService,
                          FileService fileService) {
        this.inputService = inputService;
        this.printService = printService;
        this.taskService = taskService;
        this.fileService = fileService;
    }

    public void run() throws IOException {
        boolean running = true;

        while (running) {
            printService.printMenu();

            int command = inputService.readInt();

            switch (command) {

                case 1: {
                    createTask();

                    break;
                }

                case 2: {
                    showAllTasks();
                    break;
                }

                case 3: {
                    deleteTask();
                    break;
                }

                case 4: {
                    updateTask();
                    break;
                }

                case 5: {
                    findTaskById();
                    break;
                }

                case 6: {
                    findTasksByTitle();
                    break;
                }

                case 0: {
                    running = false;
                    break;
                }

                default: {
                    printService.printMessage("Неизвестная команда");
                }
            }
        }
    }

    private void showAllTasks() {
        printService.printAllTasks(taskService.getAllTasks());

    }

    private void createTask() throws IOException {
        printService.printEnterTaskTitle();
        String title = inputService.readString();

        printService.printEnterTaskDescription();
        String description = inputService.readString();

        Task task = new Task(title, description);

        boolean added = taskService.addTask(task);

        if (added) {
            fileService.saveTasks(taskService.getAllTasks());
            printService.printMessage("Задача добавлена");
        } else {
            printService.printMessage("Не удалось добавить задачу");
        }
    }

    private void deleteTask() throws IOException {
        List<Task> allTasks = taskService.getAllTasks();

        if (allTasks.isEmpty()) {
            printService.printMessage("Задач для удаления нет");
            return;
        }

        printService.printAllTasks(allTasks);
        printService.printEnterTaskIdToDelete();

        int numId = inputService.readInt();

        if (taskService.deleteTask(numId)) {
            fileService.saveTasks(taskService.getAllTasks());

            printService.printMessage("Задача с Id = " + numId + " удалена");
        } else {
            printService.printMessage("Задача с Id = " + numId + " не найдена");
        }
    }

    private void findTaskById() {
        printService.printEnterTaskIdToSearch();

        int id = inputService.readInt();

        Task task = taskService.findTaskById(id);

        if (task != null) {
            printService.printTask(task);
        } else {
            printService.printMessage("Задача с таким Id не найдена");
        }
    }


    private void findTasksByTitle() {
        printService.printEnterTaskTitleToSearch();

        String title = inputService.readString();

        while (title.isBlank()) {
            printService.printMessage("Пустое значение. Введите название задачи: ");
            title = inputService.readString();
        }

        List<Task> foundTasks = taskService.findTasksByTitle(title);

        if (foundTasks.isEmpty()) {
            printService.printMessage("Задачи с таким названием не найдены");
        } else {
            printService.printAllTasks(foundTasks);
        }
    }

    private void updateTask() throws IOException {
        List<Task> allTasks = taskService.getAllTasks();

        if (allTasks.isEmpty()) {
            printService.printMessage("Задач для изменения нет");
            return;
        }

        printService.printAllTasks(allTasks);
        printService.printEnterTaskIdToUpdate();

        int numId = inputService.readInt();

        if (taskService.findTaskById(numId) == null) {
            printService.printMessage("Задача с таким Id не найдена");
            return;
        }

        printService.printUpdateMenu();

        int num = inputService.readInt();

        String newTitle = null;
        String newDescription = null;

        if (num == 1) {
            printService.printMessage("Введите новое название:");
            newTitle = inputService.readString();

        } else if (num == 2) {
            printService.printMessage("Введите новое описание:");
            newDescription = inputService.readString();

        } else {
            printService.printMessage("Неизвестный вариант");
            return;
        }

        boolean updated = taskService.updateTask(numId, newTitle, newDescription);

        if (updated) {
            fileService.saveTasks(taskService.getAllTasks());
            printService.printMessage("Задача изменена");
        } else {
            printService.printMessage("Задача не была изменена");
        }
    }
}
