package exception;

public class StoryNotFoundException extends RuntimeException {
    public StoryNotFoundException(int id) {
        super("История с Id = " + id + " не найдена");
    }

}
