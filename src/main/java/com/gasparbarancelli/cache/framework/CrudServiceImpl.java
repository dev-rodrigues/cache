package com.gasparbarancelli.cache.framework;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static com.gasparbarancelli.cache.config.CachingConfiguration.CACHE_RESOLVER_NAME;

public abstract class CrudServiceImpl<T, ID> implements CrudService<T, ID> {

    private static final Logger LOGGER = Logger.getLogger(CrudServiceImpl.class.getName());

    public abstract JpaRepository<T, ID> getRepository();

    @Override
    @Cacheable(cacheResolver = CACHE_RESOLVER_NAME, unless="#result == null")
    public Optional<T> findById(ID id) {
        LOGGER.info("Método findById executado: [id=" + id + "]");
        return getRepository().findById(id);
    }

    @Override
    @CacheEvict(cacheResolver = CACHE_RESOLVER_NAME, allEntries = true)
    public void removeById(ID id) {
        LOGGER.info("Método delete executado: [id=" + id + "]");
        getRepository().deleteById(id);
    }

    @Override
    @Cacheable(cacheResolver = CACHE_RESOLVER_NAME)
    public List<T> findAll() {
        LOGGER.info("Método findAll executado");
        return getRepository().findAll();
    }

    @Override
    @CacheEvict(cacheResolver = CACHE_RESOLVER_NAME, allEntries = true)
    public void save(T entity) {
        LOGGER.info("Método save executado");
        getRepository().save(entity);
    }

    @Override
    @CacheEvict(cacheResolver = CACHE_RESOLVER_NAME, allEntries = true)
    public void saveAll(T... list) {
        LOGGER.info("Método saveAll executado");
        getRepository().saveAll(List.of(list));
    }
}
