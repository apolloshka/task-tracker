package exception;

public class TaskNotFoundException extends AbstractElementNotFoundException {
    public TaskNotFoundException(int id) {
        super(id);
    }

    @Override
    public String getElementName() {
        return "Задача";
    }
}
