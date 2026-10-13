import domain.Task;
import service.TaskService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TaskService taskService = new TaskService();

        boolean running = true;

        while (running){
            System.out.println("1 - Создать задачу");
            System.out.println("2 - Показать все задачи");
            System.out.println("3 - Удалить задачу");
            System.out.println("0 - Выход");
            System.out.println("Введите команду:");

            int command = scanner.nextInt();
            scanner.nextLine();

            switch(command){
                case 1:
                    System.out.println("Введите название:");
                    String title = scanner.nextLine();

                    System.out.println("Введите описание:");
                    String description = scanner.nextLine();

                    Task task = new Task(title, description);

                    boolean added = taskService.addTask(task);
                    if (added){
                        System.out.println("Задача добавлена");
                    } else {
                        break;
                    }
                    break;

                case 2:
                    Task[] allTasks = taskService.getAllTasks();

                    for (int i = 0; i < allTasks.length; i++){
                            System.out.println("Id задачи: " + (allTasks[i].getId())
                                    + " Название: " + allTasks[i].getTitle()
                                    + " Описание задачи: "
                                    + allTasks[i].getDescription());
                    }
                    break;

                case 3:
                    Task[] allsTasks = taskService.getAllTasks();

                    if (allsTasks.length == 0){
                        System.out.println("Задач для удаления нет, массив пуст");
                        break;
                    }
                    for (int i = 0; i < allsTasks.length; i++){
                        System.out.println("Id задачи: " + (allsTasks[i].getId())
                                + " Название: " + allsTasks[i].getTitle()
                                + " Описание задачи: "
                                + allsTasks[i].getDescription());
                    }
                    System.out.println("Введите Id задачи, которую хотите удалить: ");
                    int numId = scanner.nextInt();
                    if (taskService.deleteTask(numId)){
                        System.out.println("Задача с Id = " + numId + " удалена из списка");
                    } else {
                        System.out.println("Задача с Id = " + numId + " не найдена в списке");
                    }
                    break;


                case 0:
                    running = false;
                    break;

                default:
                    System.out.println("Неизвестная команда");
            }

        }

    }
}
