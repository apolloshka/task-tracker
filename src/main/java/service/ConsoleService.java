package service;

import exception.InvalidTaskException;
import exception.TaskNotFoundException;
import model.Task;

import static CONSTANTS.UpdateConstants.TITLE;
import static CONSTANTS.UpdateConstants.DESCRIPTION;

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

    public void run() {
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

    private void createTask() {
        printService.printEnterTaskTitle();
        String title = inputService.readString();

        printService.printEnterTaskDescription();
        String description = inputService.readString();

        Task task = new Task(title, description);

        try {
            taskService.addTask(task);
            fileService.saveTasks(taskService.getAllTasks());
            printService.printMessage("Задача добавлена");
        } catch (InvalidTaskException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось сохранить задачу в файл");
        }
    }

    private void deleteTask() {
        List<Task> allTasks = taskService.getAllTasks();

        if (allTasks.isEmpty()) {
            printService.printMessage("Задач для удаления нет");
            return;
        }

        printService.printAllTasks(allTasks);
        printService.printEnterTaskIdToDelete();

        int numId = inputService.readInt();

        try {
            taskService.deleteTask(numId);
            fileService.saveTasks(taskService.getAllTasks());
            printService.printMessage("Задача с Id " + numId + " удалена");
        } catch (TaskNotFoundException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось удалить задачу из файла");
        }
    }

    private void findTaskById() {
        printService.printEnterTaskIdToSearch();

        int id = inputService.readInt();

        try {
            Task task = taskService.findTaskById(id);
            printService.printTask(task);
        } catch (TaskNotFoundException e) {
            printService.printMessage(e.getMessage());
        }
    }


    private void findTasksByTitle() {
        printService.printEnterTaskTitleToSearch();

        String title = inputService.readString();

        try {
            List<Task> foundTasks = taskService.findTasksByTitle(title);

            if (foundTasks.isEmpty()) {
                printService.printMessage("Задачи с таким названием не найдены");
            } else {
                printService.printAllTasks(foundTasks);
            }
        } catch (InvalidTaskException e) {
            printService.printMessage(e.getMessage());
        }
    }

    private void updateTask() {
        List<Task> allTasks = taskService.getAllTasks();

        if (allTasks.isEmpty()) {
            printService.printMessage("Задач для изменения нет");
            return;
        }

        printService.printAllTasks(allTasks);
        printService.printEnterTaskIdToUpdate();

        int numId = inputService.readInt();
        printService.printUpdateMenu();
        int num = inputService.readInt();

        if (num != TITLE && num != DESCRIPTION) {
            printService.printMessage("Неизвестный вариант");
            return;
        }

        String value = readTaskUpdateValue(num);

        String newTitle = null;
        String newDescription = null;

        if (num == TITLE) {
            newTitle = value;

        } else {
            newDescription = value;
        }

        try {
            taskService.updateTask(numId, newTitle, newDescription);
            fileService.saveTasks(taskService.getAllTasks());
            printService.printMessage("Задача изменена");
        } catch (TaskNotFoundException | InvalidTaskException e) {
            printService.printMessage(e.getMessage());
        } catch (IOException e) {
            printService.printMessage("Не удалось обновить задачу в файле");
        }
    }

    private String readTaskUpdateValue(int num) {
        if (num == TITLE) {
            printService.printMessage("Введите новое название:");
            return inputService.readString();

        } else if (num == DESCRIPTION) {
            printService.printMessage("Введите новое описание:");
            return inputService.readString();
        }
        return null;
    }
}


