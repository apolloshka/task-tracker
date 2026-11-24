package exception;

public abstract class AbstractElementNotFoundException extends RuntimeException {
    private int id;

    public AbstractElementNotFoundException(int id) {
        this.id = id;
    }

    public abstract String getElementName();

    public String getMessage() {
        return "Элемент " + getElementName() + " с id = " + id + " не найден";
    }
}
