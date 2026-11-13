package exception;

public class DefectNotFoundException extends RuntimeException {
    public DefectNotFoundException(int id) {
        super("Дефект с Id = " + id + " не найден");
    }
}
