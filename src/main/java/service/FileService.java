package service;

import model.Task;

import java.util.List;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class FileService {
    public void saveTasks(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter("tasks.txt");
        PrintWriter pw = new PrintWriter(writer);

        for (Task task : tasks) {
            pw.println("Id задачи: " + task.getId());
            pw.println("Название задачи: " + task.getTitle());
            pw.println("Описание задачи: " + task.getDescription());
        }

        pw.close();

    }
}
