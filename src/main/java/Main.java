import domain.Task;
import service.TaskService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Сколько задач хранить?");
        int capacity = scanner.nextInt();

        TaskService taskService = new TaskService(capacity);

        boolean running = true;

        while (running){
            System.out.println("1 - Создать задачу");
            System.out.println("2 - Показать все задачи");
            System.out.println("0 - Выход");
            System.out.println("Введите команду:");

            int command = scanner.nextInt();
            scanner.nextLine();

            switch(command){
                case 1:
                    Task task = new Task();

                    System.out.println("Введите название:");
                    String title = scanner.nextLine();
                    task.setTitle(title);

                    System.out.println("Введите описание:");
                    String description = scanner.nextLine();
                    task.setDescription(description);

                    boolean added = taskService.addTask(task);
                    if (added){
                        System.out.println("Задача добавлена");
                    } else {
                        System.out.println("Массив задач заполнен");
                    }
                    break;

                case 2:
                    Task[] allTasks = taskService.getAllTasks();

                    for (int i = 0; i < allTasks.length; i++){
                        if (allTasks[i] != null){
                            System.out.println("Номер задачи: " + (i+1) + " Название: " + allTasks[i].getTitle() + " Описание задачи: " + allTasks[i].getDescription());
                        } else{
                            break;
                        }
                    }

                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Неизвестная команда");
            }

        }

    }
}
