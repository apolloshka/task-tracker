package service;

import domain.Task;

import java.util.ArrayList;


public class TaskService {
    private final ArrayList<Task> tasks;
    private int id = 1;
//    private static final int CONST = -1;

    public TaskService() {
        tasks = new ArrayList<Task>();
    }

    public boolean addTask(Task newTask) {
        newTask.setId(id);
        id++;
        tasks.add(newTask);
        return true;
    }

    public boolean deleteTask(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            int taskId = task.getId();
            if (taskId == id) {
                tasks.remove(i);
                return true;
            }
        }
        return false;
    }

//    public boolean updateTaskTitle(int id, String newTitle) {
//        Task task = findTaskById(id);
//
//        if (task == null) {
//            return false;
//        }
//
//        task.setTitle(newTitle);
//        return true;
//    }


    public boolean updateTask(int id, String newTitle, String newDescription) {
        Task task = findTaskById(id);

        if (task == null) {
            return false;
        }

        if (newTitle != null) {
            task.setTitle(newTitle);
        }

        if (newDescription != null) {
            task.setDescription(newDescription);
        }
        return true;
    }

    public void getMenu() {
        System.out.println("1 - Создать задачу");
        System.out.println("2 - Показать все задачи");
        System.out.println("3 - Удалить задачу");
        System.out.println("4 - Изменить задачу");
        System.out.println("0 - Выход");
        System.out.println("Введите команду:");
    }

//    private int findTaskId(int id) {
//        for (int i = 0; i < tasks.size(); i++) {
//            Task task = tasks.get(i);
//
//            if (task.getId() == id) {
//                return id;
//            }
//        }
//        return CONST;
//    }


    public Task findTaskById(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public void getAllInfo(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("Id задачи: " + (task.getId())
                    + " Название: " + task.getTitle()
                    + " Описание задачи: "
                    + task.getDescription());
        }
    }
}
