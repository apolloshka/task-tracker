package exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(int id){
        super("Задача с Id = " + id + " не найдена");
    }
}
