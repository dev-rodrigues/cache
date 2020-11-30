package com.gasparbarancelli.cache.framework;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {

    Optional<T> findById(ID id);

    void removeById(ID id);

    List<T> findAll();

    void save(T entity);

    void saveAll(T... entity);

}
