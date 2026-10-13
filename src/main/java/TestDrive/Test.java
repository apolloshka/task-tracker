package TestDrive;

import domain.Task;
import service.TaskService;

public class Test {
    public static void main(String[] args) {
        TaskService taskService = new TaskService();

        Task task1 = new Task("Изучить Java", "Хочу уметь программировать!");
        Task task2 = new Task("Повторить массивы", "Разобрать цикл for-each");

        taskService.addTask(task1);
        taskService.addTask(task2);

        Task[] allTasks = taskService.getAllTasks();

        for (int i = 0; i < allTasks.length; i++){
            System.out.println("Номер задачи: " + (i+1)
                    + " Название: " + allTasks[i].getTitle()
                    + " Описание задачи: "
                    + allTasks[i].getDescription());
        }
    }
}