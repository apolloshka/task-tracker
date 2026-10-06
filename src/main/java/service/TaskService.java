package service;

import domain.Task;

public class TaskService {
    private Task[] tasks;

    public TaskService(int capacity){
        tasks = new Task[capacity];
    }

    public boolean addTask(Task newTask){
        for (int i = 0; i < tasks.length; i++){
            if (this.tasks[i] == null){
                this.tasks[i] = newTask;
                return true;
            }
        }
        return false;
    }

    public Task[] getAllTasks(){
        return tasks;
    }
}
