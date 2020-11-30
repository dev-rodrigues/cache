package com.gasparbarancelli.cache.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.SimpleCacheResolver;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class RuntimeCacheResolver extends SimpleCacheResolver {

    RuntimeCacheResolver(CacheManager cacheManager) {
        super(cacheManager);
    }

    @Override
    protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
        String className = context.getTarget().getClass().getSimpleName();

        if (context.getMethod().isAnnotationPresent(CacheEvict.class)) {
            return Stream.of(context.getTarget().getClass().getMethods())
                    .filter(it -> it.isAnnotationPresent(Cacheable.class))
                    .map(it -> cacheName.apply(className, it.getName()))
                    .collect(Collectors.toList());
        } else {
            return List.of(cacheName.apply(className, context.getMethod().getName()));
        }
    }

    private final BiFunction<String, String, String> cacheName = (classe, method) -> classe + "-" + method;

}
