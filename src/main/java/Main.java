import domain.Task;
import service.TaskService;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TaskService taskService = new TaskService();

        boolean running = true;

        while (running) {
            taskService.getMenu();

            int command = scanner.nextInt();
            scanner.nextLine();

            switch (command) {
                case 1: {
                    System.out.println("Введите название:");
                    String title = scanner.nextLine();

                    System.out.println("Введите описание:");
                    String description = scanner.nextLine();

                    Task task = new Task(title, description);

                    boolean added = taskService.addTask(task);
                    if (added) {
                        System.out.println("Задача добавлена");
                    }
                    break;
                }

                case 2: {
                    taskService.getAllInfo(taskService.getAllTasks());
                    break;
                }
                case 3: {
                    ArrayList<Task> allTasks = taskService.getAllTasks();

                    if (allTasks.isEmpty()) {
                        System.out.println("Задач для удаления нет");
                        break;
                    }

                    System.out.println("Введите Id задачи, которую хотите удалить: ");
                    int numId = scanner.nextInt();
                    scanner.nextLine();

                    if (taskService.deleteTask(numId)) {
                        System.out.println("Задача с Id = " + numId + " удалена из списка");
                    } else {
                        System.out.println("Задача с Id = " + numId + " не найдена в списке");
                    }
                    break;
                }

                case 4: {
                    ArrayList<Task> allTasks = taskService.getAllTasks();

                    if (allTasks.isEmpty()) {
                        System.out.println("Задач для изменения нет");
                        break;
                    }

                    System.out.println("Введите Id задачи, которую хотите изменить:");
                    int numId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Что изменить?");
                    System.out.println("1 — название");
                    System.out.println("2 — описание");
                    int num = scanner.nextInt();
                    scanner.nextLine();

                    String newTitle = null;
                    String newDescription = null;

                    if (num == 1) {
                        System.out.println("Введите новое название:");
                        newTitle = scanner.nextLine();
                    } else if (num == 2) {
                        System.out.println("Введите новое описание:");
                        newDescription = scanner.nextLine();
                    } else {
                        System.out.println("Неизвестный вариант");
                        break;
                    }

                    if (taskService.updateTask(numId, newTitle, newDescription)) {
                        System.out.println("Задача изменена");
                    } else {
                        System.out.println("Задача с таким ID не найдена");
                    }
                    break;
                }

//                    if (taskService.findTaskById(numId) != null) {
//                        System.out.println("Введите новое название:");
//                        String newTitle = scanner.nextLine();
//
//                        if (taskService.updateTaskTitle(numId, newTitle)) {
//                            System.out.println("Название задачи изменено");
//                        }
//
//                        System.out.println("Введите новое описание:");
//                        String newDescription = scanner.nextLine();
//
//                        if (taskService.updateTaskDescription(numId, newDescription)) {
//                            System.out.println("Описание задачи изменено");
//                        }
//                    } else {
//                        System.out.println("Задачи на изменение с таким ID не найдено");
//                    }
//                    break;
//                }

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Неизвестная команда");
            }
        }
    }
}
