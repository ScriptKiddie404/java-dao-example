package modelo;

import java.util.List;

public interface Repositorio<T> {

    List<T> listAll();

    T findById(Long id);

    void save(T t);

    void saveAll(List<T> list);

    void update(T t);

    void delete(Long id);

}
