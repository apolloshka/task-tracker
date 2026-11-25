package exception;

public class RelationNotFoundException extends AbstractElementNotFoundException {
    public RelationNotFoundException(int id) {
        super(id);
    }

    @Override
    public String getElementName() {
        return "Связь";
    }
}
