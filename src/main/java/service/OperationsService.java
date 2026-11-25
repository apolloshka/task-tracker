package service;

import model.Element;

import java.io.IOException;
import java.util.List;

//TODO можно использовать этот интерфейс также для добавления/изменения и переименовать его
public interface OperationsService<T extends Element> {
    List<T> getAll();

    void add(T element);

    void delete(int id);

    void update(int id, String title, String description);

    void save() throws IOException;
}
