package exception;

public class StoryNotFoundException extends AbstractElementNotFoundException {
    public StoryNotFoundException(int id) {
        super(id);
    }

    @Override
    public String getElementName() {
        return "История";
    }
}
