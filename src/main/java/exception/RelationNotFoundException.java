package exception;

public class RelationNotFoundException extends RuntimeException {
    public RelationNotFoundException(int id) {
        super("Связь с Id = " + id + " не найдена");
    }
}
