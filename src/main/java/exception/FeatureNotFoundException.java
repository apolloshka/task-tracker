package exception;

public class FeatureNotFoundException extends AbstractElementNotFoundException {
    public FeatureNotFoundException(int id) {
        super(id);
    }

    @Override
    public String getElementName() {
        return "Фича";
    }
}