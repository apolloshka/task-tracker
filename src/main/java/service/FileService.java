
package service;

import model.Task;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;


public class FileService {

    public void saveTasks(List<Task> tasks) throws IOException {

        try (PrintWriter pw = new PrintWriter(new FileWriter("tasks.txt"))) {

            for (Task task : tasks) {
                pw.println(task.getId());
                pw.println(task.getTitle());
                pw.println(task.getDescription());
            }

            if (pw.checkError()) {
                throw new IOException("Не удалось записать задачи");
            }
        }
    }

    //TODO метод чтения из файла возвращает все таски, записанные в файл
    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = new File("tasks.txt");

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line = br.readLine();

            while (line != null) {
                int id = Integer.parseInt(line);
                String title = br.readLine();
                String description = br.readLine();

                Task task = new Task(title, description);
                task.setId(id);
                tasks.add(task);

                line = br.readLine();
            }
            return tasks;
        }
    }
}