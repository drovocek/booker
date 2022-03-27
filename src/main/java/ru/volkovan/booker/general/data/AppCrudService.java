package ru.volkovan.booker.general.data;

import java.util.List;

public interface AppCrudService<T extends HasId, F> {

    List<T> findByFilter(F filter);

    void delete(T deleted);

    T save(T persist);
}
