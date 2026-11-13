package exception;

public class FeatureNotFoundException extends RuntimeException {
    public FeatureNotFoundException(int id) {
        super("Фича с Id = " + id + " не найдена");
    }

}
