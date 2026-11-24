package service;

import model.Element;

import java.io.IOException;
import java.util.List;

//TODO можно использовать этот интерфейс также для добавления/изменения и переименовать его
public interface DeleteService<T extends Element> {
    List<T> getAll();

    void delete(int id);

    void save() throws IOException;
}
