package com.gasparbarancelli.cache.service.impl;

import com.gasparbarancelli.cache.framework.CrudServiceImpl;
import com.gasparbarancelli.cache.model.Cliente;
import com.gasparbarancelli.cache.repository.ClienteRepository;
import com.gasparbarancelli.cache.service.ClienteService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl extends CrudServiceImpl<Cliente, Long> implements ClienteService {

    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public JpaRepository<Cliente, Long> getRepository() {
        return repository;
    }

}
