package exception;

public class DefectNotFoundException extends AbstractElementNotFoundException {
    public DefectNotFoundException(int id) {
        super(id);
    }

    @Override
    public String getElementName() {
        return "Дефект";
    }
}
