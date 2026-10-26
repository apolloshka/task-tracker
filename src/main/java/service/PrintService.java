package service;

import model.Task;

import java.util.List;

public class PrintService {

    public void printMenu() {
        System.out.println("1 - Создать задачу");
        System.out.println("2 - Показать все задачи");
        System.out.println("3 - Удалить задачу");
        System.out.println("4 - Изменить задачу");
        System.out.println("5 - Найти задачу по Id");
        System.out.println("0 - Выход");
        System.out.print("Введите команду: ");
    }

    public void printEnterTaskTitle() {
        System.out.print("Введите название: ");

    }

    public void printEnterTaskDescription() {
        System.out.print("Введите описание: ");

    }

    public void printEnterTaskIdToDelete() {
        System.out.print("Введите Id задачи, которую хотите удалить: ");
    }

    public void printEnterTaskIdToUpdate() {
        System.out.print("Введите Id задачи, которую хотите изменить: ");
    }


    public void printAllTasks(List<Task> tasks) {
        System.out.println("Все задачи: ");

        for (Task task : tasks) {
            System.out.println("Id задачи: " + (task.getId())
                    + " Название: " + task.getTitle()
                    + " Описание задачи: "
                    + task.getDescription());
        }
    }

    public void printTask(Task task) {
        System.out.println("Id задачи: " + (task.getId())
                + " Название: " + task.getTitle()
                + " Описание задачи: "
                + task.getDescription());

    }

    public void printUpdateMenu() {
        System.out.println("Что изменить?");
        System.out.println("1 — название");
        System.out.println("2 — описание");
    }
}
