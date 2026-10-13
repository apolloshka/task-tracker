package service;

import domain.Task;

public class TaskService {
    private Task[] tasks;
    private int size = 0;
    private int id = 1;

    public TaskService(){
        tasks = new Task[1];
    }

    public boolean addTask(Task newTask){
        if (size == tasks.length) {
            newCapacity();
        }
        newTask.setId(id);
        id++;
        tasks[size] = newTask;
        size++;
        return true;
    }

    public boolean deleteTask(int id){
        int numForDelete = findTaskIdToDelete(id);

        if (numForDelete < 0){
            return false;
        }
        for (int i = numForDelete; i < size - 1; i++){
            tasks[i] = tasks[i+1];
        }
        tasks[size - 1] = null;
        size --;
        return true;
    }

    private int findTaskIdToDelete(int id){
        int numForDelete = -1;

        for (int i = 0; i < size; i++){
            if (tasks[i].getId() == id){
                numForDelete = i;
                return numForDelete;
            }
        }
        return numForDelete;
    }

    private void newCapacity(){
        Task[] biggerTasks = new Task[tasks.length * 2];

        for (int i = 0; i < tasks.length; i++) {
            biggerTasks[i] = tasks[i];
        }
        tasks = biggerTasks;
    }

    public Task[] getAllTasks(){
        Task[] result = new Task[size];

        for (int i = 0; i < size; i++){
            result[i] = tasks[i];
        }

        return result;
    }
}
